package com.frestoinc.gojekassignment.api.rest;

import com.frestoinc.gojekassignment.data.Constants;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public interface GithubApi {

  @GET(Constants.REST_CONSTANT)
  Single<List<GithubModel>> getRxRepositories();
}
