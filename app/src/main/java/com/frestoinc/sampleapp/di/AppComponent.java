package com.frestoinc.sampleapp.di;

import android.app.Application;

import com.frestoinc.sampleapp.SampleApp;
import com.frestoinc.sampleapp.di.module.AppModule;
import com.frestoinc.sampleapp.di.module.WorkerModule;
import com.frestoinc.sampleapp.di.scope.AppScope;
import com.frestoinc.sampleapp.service.AppGithubWorkerFactory;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@AppScope
@Component(modules = {AppContextModule.class, AppModule.class,
        AndroidSupportInjectionModule.class, ActivityBuilder.class, WorkerModule.class})
public interface AppComponent extends AndroidInjector<SampleApp> {

    AppGithubWorkerFactory factory();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
