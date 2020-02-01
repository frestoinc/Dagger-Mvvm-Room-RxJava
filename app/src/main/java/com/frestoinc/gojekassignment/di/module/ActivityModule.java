package com.frestoinc.gojekassignment.di.module;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frestoinc.gojekassignment.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module
public class ActivityModule {

    @ActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @ActivityScope
    @Provides
    DividerItemDecoration provideDividerItemDecoration(Context context, LinearLayoutManager manager) {
        return new DividerItemDecoration(context, manager.getOrientation());
    }
}
