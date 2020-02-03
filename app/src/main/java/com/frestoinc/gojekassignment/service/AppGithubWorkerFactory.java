package com.frestoinc.gojekassignment.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.ListenableWorker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class AppGithubWorkerFactory extends WorkerFactory {

    private final Map<Class<? extends ListenableWorker>, Provider<GithubWorkerFactory>> workersFactories;

    @Inject
    public AppGithubWorkerFactory(Map<Class<? extends ListenableWorker>, Provider<GithubWorkerFactory>> workersFactories) {
        this.workersFactories = workersFactories;
    }

    @Nullable
    @Override
    public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        Provider<GithubWorkerFactory> factoryProvider = getWorkerFactoryProviderByKey(workersFactories, workerClassName);
        return factoryProvider.get().create(appContext, workerParameters);
    }

    private Provider<GithubWorkerFactory> getWorkerFactoryProviderByKey(
            Map<Class<? extends ListenableWorker>, Provider<GithubWorkerFactory>> map, String key) {

        for (Map.Entry<Class<? extends ListenableWorker>, Provider<GithubWorkerFactory>> entry : map.entrySet()) {
            if (Objects.equals(key, entry.getKey().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
