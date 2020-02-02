package com.frestoinc.gojekassignment.di.module;

import android.app.Application;

import androidx.room.Room;

import com.frestoinc.gojekassignment.api.rest.AppGithubRepository;
import com.frestoinc.gojekassignment.api.rest.GithubRepository;
import com.frestoinc.gojekassignment.data.AppDataManager;
import com.frestoinc.gojekassignment.data.Constants;
import com.frestoinc.gojekassignment.data.DataManager;
import com.frestoinc.gojekassignment.data.dao.GithubModelDao;
import com.frestoinc.gojekassignment.data.database.AppRoomDatabase;
import com.frestoinc.gojekassignment.data.helper.AppRoomHelper;
import com.frestoinc.gojekassignment.data.helper.RoomHelper;
import com.frestoinc.gojekassignment.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 02,February,2020 for GoJekAssignment.
 */
@Module(includes = GithubModule.class)
public class RoomModule {

  @AppScope
  @Provides
  static RoomHelper provideRoomHelper(AppRoomHelper helper) {
    return helper;
  }

  @AppScope
  @Provides
  static GithubRepository provideGithubRepository(AppGithubRepository repository) {
    return repository;
  }

  @AppScope
  @Provides
  DataManager provideDataManager(RoomHelper roomHelper, GithubRepository repository) {
    return new AppDataManager(roomHelper, repository);
  }

  @AppScope
  @Provides
  AppRoomDatabase provideAppRoomDatabase(Application application) {
    return Room.databaseBuilder(application,
        AppRoomDatabase.class, Constants.ROOM_DB)
        .fallbackToDestructiveMigration()
        .build();
  }

  @AppScope
  @Provides
  GithubModelDao provideGithubModelDao(AppRoomDatabase db) {
    return db.modelDao();
  }
}
