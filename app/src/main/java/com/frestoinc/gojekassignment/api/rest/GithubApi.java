package com.frestoinc.gojekassignment.api.rest;

import com.frestoinc.gojekassignment.data.GithubModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public interface GithubApi {

  @GET("repositories")
  Single<List<GithubModel>> getRepositories();
}
