package com.frestoinc.gojekassignment.api.rest;

import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public interface GithubRepository {

  Single<List<GithubModel>> getRepo();

}
