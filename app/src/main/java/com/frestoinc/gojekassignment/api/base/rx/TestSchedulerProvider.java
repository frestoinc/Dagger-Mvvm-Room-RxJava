package com.frestoinc.gojekassignment.api.base.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class TestSchedulerProvider implements SchedulerProvider {

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
