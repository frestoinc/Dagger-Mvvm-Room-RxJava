package com.frestoinc.sampleapp.api.rest;

import com.frestoinc.sampleapp.data.Constants;
import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by frestoinc on 01,February,2020 for SampleApp.
 */
public interface GithubApi {

    @GET(Constants.REST_CONSTANT)
    Single<List<GithubModel>> getRxRepositories();
}
