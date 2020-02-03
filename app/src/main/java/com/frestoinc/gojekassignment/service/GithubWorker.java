package com.frestoinc.gojekassignment.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.RxWorker;
import androidx.work.WorkerParameters;

import com.frestoinc.gojekassignment.api.rest.GithubApi;
import com.frestoinc.gojekassignment.data.helper.RoomHelper;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class GithubWorker extends RxWorker {

    @Inject
    GithubApi api;

    @Inject
    RoomHelper helper;

    @Inject
    public GithubWorker(@NonNull Context context, @NonNull WorkerParameters workerParams, GithubApi api, RoomHelper helper) {
        super(context, workerParams);
        this.api = api;
        this.helper = helper;
    }


    @NonNull
    @Override
    public Single<Result> createWork() {
        return api.getRxRepositories()
                .flatMapCompletable(githubModels -> helper.insert(githubModels))
                .toSingleDefault(Result.success())
                .onErrorReturnItem(Result.failure())
                .observeOn(Schedulers.io());
    }

    public static class Factory implements GithubWorkerFactory {

        private final Provider<GithubApi> githubApiProvider;

        private final Provider<RoomHelper> roomHelperProvider;

        @Inject
        public Factory(Provider<GithubApi> githubApiProvider, Provider<RoomHelper> roomHelperProvider) {
            this.githubApiProvider = githubApiProvider;
            this.roomHelperProvider = roomHelperProvider;
        }


        @Override
        public ListenableWorker create(Context appContext, WorkerParameters workerParameters) {
            return new GithubWorker(appContext, workerParameters, githubApiProvider.get(), roomHelperProvider.get());
        }
    }
}
