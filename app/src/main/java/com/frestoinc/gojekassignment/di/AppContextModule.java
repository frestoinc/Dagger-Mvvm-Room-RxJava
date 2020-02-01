package com.frestoinc.gojekassignment.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
@Module
public abstract class AppContextModule {

  @Binds
  abstract Context provideContext(Application application);
}
