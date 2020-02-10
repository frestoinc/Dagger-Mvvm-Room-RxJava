package com.frestoinc.sampleapp.data;

import com.frestoinc.sampleapp.api.rest.GithubRepository;
import com.frestoinc.sampleapp.data.helper.RoomHelper;
import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by frestoinc on 02,February,2020 for SampleApp.
 */
public class AppDataManager implements DataManager {

    @Inject
    RoomHelper roomHelper;

    @Inject
    GithubRepository repository;

    @Inject
    public AppDataManager(RoomHelper helper, GithubRepository repo) {
        this.roomHelper = helper;
        this.repository = repo;
    }

    @Override
    public Single<List<GithubModel>> getRepo() {
        return repository.getRepo();
    }

    @Override
    public Single<List<GithubModel>> getRoomRepo() {
        return roomHelper.getRoomRepo();
    }

    @Override
    public Completable insert(List<GithubModel> githubModel) {
        return roomHelper.insert(githubModel);
    }

    @Override
    public Completable deleteAll() {
        return roomHelper.deleteAll();
    }
}
