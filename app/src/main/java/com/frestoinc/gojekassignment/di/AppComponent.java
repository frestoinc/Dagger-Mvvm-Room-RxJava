package com.frestoinc.gojekassignment.di;

import android.app.Application;

import com.frestoinc.gojekassignment.GoJekAssignment;
import com.frestoinc.gojekassignment.di.module.AppModule;
import com.frestoinc.gojekassignment.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@AppScope
@Component(modules = {AppContextModule.class, AppModule.class, AndroidSupportInjectionModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<GoJekAssignment> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
