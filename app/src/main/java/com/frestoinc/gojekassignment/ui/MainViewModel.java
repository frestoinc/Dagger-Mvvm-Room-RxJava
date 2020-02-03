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

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainViewModel extends BaseViewModel {

    private MutableLiveData<AuthResource<List<GithubModel>>> source = new MutableLiveData<>();

    @Inject
    MainViewModel(SchedulerProvider provider, DataManager appDataManager) {
        super(provider, appDataManager);
        getLocalRepo();
    }

    MainViewModel() {
        super();
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
                .subscribeWith(new DisposableSingleObserver<List<GithubModel>>() {
                    @Override
                    public void onSuccess(List<GithubModel> githubModels) {
                        for (int i = 0; i < githubModels.size(); i++) {
                            githubModels.get(i).setId(new Random().nextLong());
                        }
                        insertIntoRoom(githubModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setError(e);
                    }
                })

        );
    }

    void getLocalRepo() {
        getCompositeDisposable().add(getDataManager().getRoomRepo()
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableSingleObserver<List<GithubModel>>() {
                    @Override
                    public void onSuccess(List<GithubModel> githubModels) {
                        if (githubModels.isEmpty()) {
                            getOnlineRepo();
                        } else {
                            doOnSuccess(githubModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        setError(e);
                    }
                })
        );
    }

    private void insertIntoRoom(List<GithubModel> list) {
        getCompositeDisposable().add(getDataManager().insert(list)
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getLocalRepo();
                    }

                    @Override
                    public void onError(Throwable e) {
                        setError(e);
                    }
                }));
    }

    private void deleteEntries() {
        getCompositeDisposable().add(getDataManager().deleteAll()
                .doOnSubscribe(disposable -> doOnLoading())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        //todo
                    }

                    @Override
                    public void onError(Throwable e) {
                        setError(e);
                    }
                }));
    }

    private void doOnLoading() {
        source.postValue(AuthResource.loading(null));
    }

    private void doOnSuccess(List<GithubModel> list) {
        source.setValue(AuthResource.success(list));
    }

}
