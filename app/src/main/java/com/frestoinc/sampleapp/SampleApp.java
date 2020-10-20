package com.frestoinc.sampleapp;

import android.app.Application;

import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.frestoinc.sampleapp.service.AppGithubWorkerFactory;
import com.frestoinc.sampleapp.service.GithubWorker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@HiltAndroidApp
public class SampleApp extends Application {

    @Inject
    AppGithubWorkerFactory factory;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        configureWorkManager();
        //setupPeriodicWork();
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
                GithubWorker.class, 4, TimeUnit.HOURS)
                .setConstraints(getConstraints())
                .setInitialDelay(0, TimeUnit.SECONDS);
    }

    private Constraints getConstraints() {
        return new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
    }
}
