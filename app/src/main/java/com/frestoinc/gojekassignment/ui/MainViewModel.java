package com.frestoinc.gojekassignment.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frestoinc.gojekassignment.BuildConfig;
import com.frestoinc.gojekassignment.api.base.BaseViewModel;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.data.AppDataManager;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.ArrayList;
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
  MainViewModel(SchedulerProvider provider, AppDataManager appDataManager) {
    super(provider, appDataManager);
  }

  @Override
  public void setError(Throwable e) {
      if (BuildConfig.DEBUG) {
          Log.e("TAG", "Error: " + e);
          e.printStackTrace();
      }
      source.setValue(AuthResource.error());
  }

    LiveData<AuthResource<List<GithubModel>>> getSource() {
    return source;
  }

    void getOnlineRepo() {
        source.setValue(AuthResource.loading(new ArrayList<>()));
    getCompositeDisposable().add(getDataManager().getRepo()
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
        }));
  }

  void getLocalRepo() {
      source.setValue(AuthResource.loading(new ArrayList<>()));
    getCompositeDisposable().add(getDataManager().getRepo()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribeWith(new DisposableSingleObserver<List<GithubModel>>() {
          @Override
          public void onSuccess(List<GithubModel> githubModels) {
            if (githubModels.isEmpty()) {
              getOnlineRepo();
            } else {
                source.setValue(AuthResource.success(githubModels));
            }
          }

          @Override
          public void onError(Throwable e) {
            setError(e);
          }
        }));
  }

  private void insertIntoRoom(List<GithubModel> list) {
      source.setValue(AuthResource.loading(new ArrayList<>()));
    getCompositeDisposable().add(getDataManager().insert(list)
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

}
