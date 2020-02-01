package com.frestoinc.gojekassignment.di.module;

import com.frestoinc.gojekassignment.api.base.rx.AppSchedulerProvider;
import com.frestoinc.gojekassignment.api.base.rx.SchedulerProvider;
import com.frestoinc.gojekassignment.api.rest.AppGithubRepository;
import com.frestoinc.gojekassignment.api.rest.GithubApi;
import com.frestoinc.gojekassignment.api.rest.GithubRepository;
import com.frestoinc.gojekassignment.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Module(includes = GithubModule.class)
public class AppModule {

    @AppScope
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @AppScope
    GithubRepository provideGithubRepository(GithubApi api) {
        return new AppGithubRepository(api);
    }
}
