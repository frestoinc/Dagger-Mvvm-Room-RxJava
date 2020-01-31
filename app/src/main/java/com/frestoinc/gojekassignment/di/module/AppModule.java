package com.frestoinc.gojekassignment.di.module;

import android.app.Application;
import android.content.Context;

import com.frestoinc.gojekassignment.api.base.rx.AppSchedulerProvider;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.di.scope.AppScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module
public class AppModule {

    @AppScope
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @AppScope
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }
}
