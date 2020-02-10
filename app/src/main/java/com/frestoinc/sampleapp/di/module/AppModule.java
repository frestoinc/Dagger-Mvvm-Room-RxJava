package com.frestoinc.sampleapp.di.module;

import com.frestoinc.sampleapp.api.base.rx.AppSchedulerProvider;
import com.frestoinc.sampleapp.api.base.rx.SchedulerProvider;
import com.frestoinc.sampleapp.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@Module(includes = {GithubModule.class, RoomModule.class})
public class AppModule {

    @AppScope
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
