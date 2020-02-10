package com.frestoinc.sampleapp.api.rest;

import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by frestoinc on 01,February,2020 for SampleApp.
 */
public class AppGithubRepository implements GithubRepository {

    @Inject
    public GithubApi api;

    @Inject
    public AppGithubRepository(GithubApi api) {
        this.api = api;
    }

    @Override
    public Single<List<GithubModel>> getRepo() {
        return api.getRxRepositories();
    }
}
