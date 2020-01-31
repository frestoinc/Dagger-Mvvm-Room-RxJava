package com.frestoinc.gojekassignment.api.base.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */

public class AppSchedulerProvider implements SchedulerProvider {

  @Override
  public Scheduler computation() {
    return Schedulers.computation();
  }

  @Override
  public Scheduler io() {
    return Schedulers.io();
  }

  @Override
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}
