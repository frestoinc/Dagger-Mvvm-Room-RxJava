package com.frestoinc.gojekassignment.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frestoinc.gojekassignment.api.base.BaseViewModel;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.api.rest.GithubRepository;
import com.frestoinc.gojekassignment.data.GithubModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainViewModel extends BaseViewModel {

  private MutableLiveData<List<GithubModel>> source = new MutableLiveData<>();

  @Inject
  public MainViewModel(SchedulerProvider provider, GithubRepository repository) {
    super(provider, repository);
  }

  @Override
  public void setError(Throwable e) {
    e.printStackTrace();
  }

  public LiveData<List<GithubModel>> getSource() {
    return source;
  }

  public void getRepo() {
    getCompositeDisposable().add(getRepository().getRepo()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribeWith(new DisposableSingleObserver<List<GithubModel>>() {
          @Override
          public void onSuccess(List<GithubModel> githubModels) {
            source.setValue(githubModels);
          }

          @Override
          public void onError(Throwable e) {
            setError(e);
          }
        }));
  }
}
