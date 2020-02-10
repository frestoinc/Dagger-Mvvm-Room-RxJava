package com.frestoinc.sampleapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.frestoinc.sampleapp.di.module.ViewModelProviderFactory;
import com.frestoinc.sampleapp.di.scope.AppScope;
import com.frestoinc.sampleapp.ui.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@Module
public abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);


    @Binds
    @AppScope
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
