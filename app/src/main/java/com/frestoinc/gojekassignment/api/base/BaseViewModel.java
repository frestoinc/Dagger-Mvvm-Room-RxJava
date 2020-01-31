package com.frestoinc.gojekassignment.api.base;

import androidx.lifecycle.ViewModel;

import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public abstract class BaseViewModel extends ViewModel {

  private CompositeDisposable compositeDisposable;

  @Inject
  public SchedulerProvider schedulerProvider;

  public abstract void setError(Throwable e);

  public BaseViewModel() {
    this.compositeDisposable = new CompositeDisposable();
  }

  public CompositeDisposable getCompositeDisposable() {
    return compositeDisposable;
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
