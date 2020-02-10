package com.frestoinc.sampleapp.api.base.rx;

import io.reactivex.Scheduler;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public interface SchedulerProvider {

  Scheduler computation();

  Scheduler io();

  Scheduler ui();
}
