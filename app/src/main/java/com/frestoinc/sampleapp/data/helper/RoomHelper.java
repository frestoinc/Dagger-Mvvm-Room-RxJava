package com.frestoinc.sampleapp.data.helper;

import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by frestoinc on 02,February,2020 for SampleApp.
 */
public interface RoomHelper {

    Single<List<GithubModel>> getRoomRepo();

    Completable insert(List<GithubModel> githubModel);

    Completable deleteAll();
}
