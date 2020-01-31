package com.frestoinc.gojekassignment.ui;

import com.frestoinc.gojekassignment.api.base.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainViewModel extends BaseViewModel {

  @Inject
  public MainViewModel() {
    super();
  }

  @Override
  public void setError(Throwable e) {

  }
}
