package com.frestoinc.gojekassignment.di.module;

import com.frestoinc.gojekassignment.di.WorkerKey;
import com.frestoinc.gojekassignment.service.GithubWorker;
import com.frestoinc.gojekassignment.service.GithubWorkerFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
@Module
public interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(GithubWorker.class)
    GithubWorkerFactory provideCustomWorkerFactory(GithubWorker.Factory factory);
}
