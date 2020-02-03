package com.frestoinc.gojekassignment.api.base;

import androidx.lifecycle.ViewModel;

import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public abstract class BaseViewModel extends ViewModel {

  public abstract void setError(Throwable e);

  @Inject
  public DataManager dataManager;

  @Inject
  public SchedulerProvider schedulerProvider;

  private CompositeDisposable compositeDisposable;

  public BaseViewModel(SchedulerProvider provider, DataManager manager) {
    this.compositeDisposable = new CompositeDisposable();
    this.schedulerProvider = provider;
    this.dataManager = manager;
  }

  public BaseViewModel() {
    this.compositeDisposable = new CompositeDisposable();
  }

  protected CompositeDisposable getCompositeDisposable() {
    return compositeDisposable;
  }

  protected DataManager getDataManager() {
    return dataManager;
  }

  protected SchedulerProvider getSchedulerProvider() {
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
