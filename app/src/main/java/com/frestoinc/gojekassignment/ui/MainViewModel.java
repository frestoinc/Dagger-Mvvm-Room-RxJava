package com.frestoinc.gojekassignment.ui;

import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frestoinc.gojekassignment.api.base.BaseViewModel;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.data.AppDataManager;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainViewModel extends BaseViewModel {

  private MutableLiveData<List<GithubModel>> source = new MutableLiveData<>();

  @Inject
  MainViewModel(SchedulerProvider provider, AppDataManager appDataManager) {
    super(provider, appDataManager);
  }

  @Override
  public void setError(Throwable e) {
    e.printStackTrace();
  }

  LiveData<List<GithubModel>> getSource() {
    return source;
  }

  public void getOnlineRepo() {
    Log.e("TAG", "getOnlineRepo");
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
    Log.e("TAG", "getLocalRepo");
    getCompositeDisposable().add(getDataManager().getRepo()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribeWith(new DisposableSingleObserver<List<GithubModel>>() {
          @Override
          public void onSuccess(List<GithubModel> githubModels) {
            if (githubModels.isEmpty()) {
              Log.e("TAG", "room is empty");
              getOnlineRepo();
            } else {
              source.setValue(githubModels);
            }
          }

          @Override
          public void onError(Throwable e) {
            setError(e);
          }
        }));
  }

  private void insertIntoRoom(List<GithubModel> list) {
    Log.e("TAG", "insertIntoRoom");
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

  void setSortedSource(boolean isName) {
    if (getSource().getValue() == null || getSource().getValue().isEmpty()) {
      return;
    }
    List<GithubModel> list = getSource().getValue();
    Comparator<GithubModel> comparator = (o1, o2) -> {
      if (isName) {
        return o1.getName().compareToIgnoreCase(o2.getName());
      }
      return o2.getStars() - o1.getStars();
    };

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      list.sort(comparator);
    } else {
      Collections.sort(list, comparator);
    }
    source.setValue(list);
  }
}
