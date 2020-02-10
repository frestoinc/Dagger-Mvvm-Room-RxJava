package com.frestoinc.sampleapp.service;

import android.content.Context;

import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

/**
 * Created by frestoinc on 03,February,2020 for SampleApp.
 */
public interface GithubWorkerFactory {

    ListenableWorker create(Context appContext, WorkerParameters workerParameters);
}
