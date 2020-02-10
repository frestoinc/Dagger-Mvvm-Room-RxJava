package com.frestoinc.sampleapp.di.module;

import com.frestoinc.sampleapp.di.WorkerKey;
import com.frestoinc.sampleapp.service.GithubWorker;
import com.frestoinc.sampleapp.service.GithubWorkerFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by frestoinc on 03,February,2020 for SampleApp.
 */
@Module
public interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(GithubWorker.class)
    GithubWorkerFactory provideCustomWorkerFactory(GithubWorker.Factory factory);
}
