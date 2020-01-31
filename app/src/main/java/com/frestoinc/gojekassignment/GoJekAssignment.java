package com.frestoinc.gojekassignment;

import android.app.Activity;

import com.frestoinc.gojekassignment.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class GoJekAssignment extends DaggerApplication {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
