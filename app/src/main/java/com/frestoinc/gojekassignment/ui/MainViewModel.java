package com.frestoinc.gojekassignment.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frestoinc.gojekassignment.BuildConfig;
import com.frestoinc.gojekassignment.api.base.BaseViewModel;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.data.DataManager;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainViewModel extends BaseViewModel {

    private MutableLiveData<AuthResource<List<GithubModel>>> source = new MutableLiveData<>();

    @Inject
    public MainViewModel(SchedulerProvider provider, DataManager appDataManager) {
        super(provider, appDataManager);
        getLocalRepo();
    }

    @Override
    public void setError(Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e("TAG", "Error: " + e);
            e.printStackTrace();
        }
        source.setValue(AuthResource.error(e.getMessage()));
    }

    LiveData<AuthResource<List<GithubModel>>> getSource() {
        return source;
    }

    void getOnlineRepo() {
        getCompositeDisposable().add(getDataManager().getRepo()
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSuccess(githubModels -> {
                    for (int i = 0; i < githubModels.size(); i++) {
                        githubModels.get(i).setId(new Random().nextLong());
                    }
                    insertIntoRoom(githubModels);
                }).doOnError(this::setError)
                .subscribe());
    }

    void getLocalRepo() {
        getCompositeDisposable().add(getDataManager().getRoomRepo()
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSuccess(githubModels -> {
                    if (githubModels.isEmpty()) {
                        getOnlineRepo();
                    } else {
                        source.setValue(AuthResource.success(githubModels));
                    }
                }).doOnError(this::setError)
                .subscribe());
    }

    private void insertIntoRoom(List<GithubModel> list) {
        getCompositeDisposable().add(getDataManager().insert(list)
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(this::getLocalRepo)
                .doOnError(this::setError)
                .subscribe());
    }

    private void deleteEntries() {
        getCompositeDisposable().add(getDataManager().deleteAll()
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(this::getOnlineRepo).doOnError(this::setError)
                .subscribe());
    }

    private void doOnLoading() {
        source.postValue(AuthResource.loading(null));
    }

}
