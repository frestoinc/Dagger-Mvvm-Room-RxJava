package com.frestoinc.gojekassignment.api.rest;

import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
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
