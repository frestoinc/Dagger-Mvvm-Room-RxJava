package com.frestoinc.sampleapp.di.module;

import android.app.Application;

import androidx.room.Room;

import com.frestoinc.sampleapp.api.rest.AppGithubRepository;
import com.frestoinc.sampleapp.api.rest.GithubRepository;
import com.frestoinc.sampleapp.data.AppDataManager;
import com.frestoinc.sampleapp.data.Constants;
import com.frestoinc.sampleapp.data.DataManager;
import com.frestoinc.sampleapp.data.dao.GithubModelDao;
import com.frestoinc.sampleapp.data.database.AppRoomDatabase;
import com.frestoinc.sampleapp.data.helper.AppRoomHelper;
import com.frestoinc.sampleapp.data.helper.RoomHelper;
import com.frestoinc.sampleapp.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 02,February,2020 for SampleApp.
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
