package com.frestoinc.gojekassignment.di;

import androidx.lifecycle.ViewModelProvider;

import com.frestoinc.gojekassignment.di.module.ViewModelProviderFactory;
import com.frestoinc.gojekassignment.di.scope.AppScope;

import dagger.Binds;
import dagger.Module;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module
public abstract class ViewModelFactoryBuilder {

    @Binds
    @AppScope
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);


}
