package com.frestoinc.gojekassignment.data.helper;

import com.frestoinc.gojekassignment.data.database.AppRoomDatabase;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by frestoinc on 02,February,2020 for GoJekAssignment.
 */
public class AppRoomHelper implements RoomHelper {

  @Inject
  public AppRoomDatabase appDatabase;

  @Inject
  public AppRoomHelper(AppRoomDatabase appDatabase) {
    this.appDatabase = appDatabase;
  }

  @Override
  public Single<List<GithubModel>> getRoomRepo() {
    return appDatabase.modelDao().getAllEntries();
  }

  @Override
  public Completable insert(List<GithubModel> githubModel) {
    return appDatabase.modelDao().insert(githubModel);
  }

  @Override
  public Completable deleteAll() {
    return appDatabase.modelDao().deleteAll();
  }
}
