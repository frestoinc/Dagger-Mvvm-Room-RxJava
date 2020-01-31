package com.frestoinc.gojekassignment.di;

import com.frestoinc.gojekassignment.di.module.ActivityModule;
import com.frestoinc.gojekassignment.di.scope.ActivityScope;
import com.frestoinc.gojekassignment.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = {ViewModelBuilder.class, ActivityModule.class})
    abstract MainActivity contributeMainActivity();
}
