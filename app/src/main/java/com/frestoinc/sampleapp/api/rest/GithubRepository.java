package com.frestoinc.sampleapp.api.rest;

import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by frestoinc on 01,February,2020 for SampleApp.
 */
public interface GithubRepository {

    Single<List<GithubModel>> getRepo();

}
