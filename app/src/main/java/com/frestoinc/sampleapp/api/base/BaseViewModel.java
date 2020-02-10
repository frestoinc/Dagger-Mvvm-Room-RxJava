package com.frestoinc.sampleapp.api.base;

import androidx.lifecycle.ViewModel;

import com.frestoinc.sampleapp.api.base.rx.AppSchedulerProvider;
import com.frestoinc.sampleapp.api.base.rx.SchedulerProvider;
import com.frestoinc.sampleapp.data.AppDataManager;
import com.frestoinc.sampleapp.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public abstract class BaseViewModel extends ViewModel {

    @Inject
    DataManager dataManager;
    @Inject
    SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;

    /**
     * Instantiates a new Base view model.
     *
     * @param provider {@link AppSchedulerProvider} the provider
     * @param manager  {@link AppDataManager}the manager
     */
    public BaseViewModel(SchedulerProvider provider, DataManager manager) {
        this.compositeDisposable = new CompositeDisposable();
        this.schedulerProvider = provider;
        this.dataManager = manager;
    }

    public abstract void setError(Throwable e);

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }

    protected SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    @Override
    protected void onCleared() {
        if (getCompositeDisposable() != null) {
            getCompositeDisposable().dispose();
        }
        super.onCleared();
    }
}
