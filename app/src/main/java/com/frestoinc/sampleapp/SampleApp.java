package com.frestoinc.sampleapp;

import android.app.Activity;

import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.frestoinc.sampleapp.di.DaggerAppComponent;
import com.frestoinc.sampleapp.service.AppGithubWorkerFactory;
import com.frestoinc.sampleapp.service.GithubWorker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public class SampleApp extends DaggerApplication {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    AppGithubWorkerFactory factory;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        configureWorkManager();
        setupPeriodicWork();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    private void setupPeriodicWork() {
        PeriodicWorkRequest request = getBuilder().build();
        WorkManager.getInstance(this).enqueue(request);
    }

    private void configureWorkManager() {
        Configuration config = new Configuration.Builder()
                .setWorkerFactory(factory)
                .build();

        WorkManager.initialize(this, config);
    }

    private PeriodicWorkRequest.Builder getBuilder() {
        return new PeriodicWorkRequest.Builder(
                GithubWorker.class, 2, TimeUnit.HOURS)
                .setConstraints(getConstraints())
                .setInitialDelay(10, TimeUnit.SECONDS);
    }

    private Constraints getConstraints() {
        return new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
    }
}
