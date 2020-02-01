package com.frestoinc.gojekassignment.api.base;

import androidx.lifecycle.ViewModel;

import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.api.rest.GithubRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public abstract class BaseViewModel extends ViewModel {

  public abstract void setError(Throwable e);

  @Inject
  public GithubRepository repository;

  @Inject
  public SchedulerProvider schedulerProvider;

  private CompositeDisposable compositeDisposable;

  public BaseViewModel(SchedulerProvider provider, GithubRepository repository) {
    this.compositeDisposable = new CompositeDisposable();
    this.schedulerProvider = provider;
    this.repository = repository;
  }

  public CompositeDisposable getCompositeDisposable() {
    return compositeDisposable;
  }

  public GithubRepository getRepository() {
    return repository;
  }

  public SchedulerProvider getSchedulerProvider() {
    return schedulerProvider;
  }

  @Override
  protected void onCleared() {
    if (getCompositeDisposable() != null) {
      getCompositeDisposable().dispose();
    }
    super.onCleared();
  }
}
