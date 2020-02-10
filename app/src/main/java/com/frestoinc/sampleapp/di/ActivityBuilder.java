package com.frestoinc.sampleapp.di;

import com.frestoinc.sampleapp.di.module.ActivityModule;
import com.frestoinc.sampleapp.di.scope.ActivityScope;
import com.frestoinc.sampleapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = {ViewModelBuilder.class, ActivityModule.class})
    abstract MainActivity contributeMainActivity();
}
