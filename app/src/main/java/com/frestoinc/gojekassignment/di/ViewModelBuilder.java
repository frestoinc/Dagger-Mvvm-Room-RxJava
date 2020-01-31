package com.frestoinc.gojekassignment.di;

import androidx.lifecycle.ViewModel;

import com.frestoinc.gojekassignment.ui.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module
public abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);


}
