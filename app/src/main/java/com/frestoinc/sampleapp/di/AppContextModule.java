package com.frestoinc.sampleapp.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by frestoinc on 01,February,2020 for SampleApp.
 */
@Module
public abstract class AppContextModule {

  @Binds
  abstract Context provideContext(Application application);
}
