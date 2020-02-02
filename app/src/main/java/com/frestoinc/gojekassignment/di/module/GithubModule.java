package com.frestoinc.gojekassignment.di.module;

import com.frestoinc.gojekassignment.api.rest.GithubApi;
import com.frestoinc.gojekassignment.data.Constants;
import com.frestoinc.gojekassignment.di.scope.AppScope;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
@Module
public class GithubModule {

  @AppScope
  @Provides
  static GithubApi provideGithubApi(OkHttpClient client) {
    return new Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(GithubApi.class);
  }

  @AppScope
  @Provides
  static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
    return new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build();
  }

  @AppScope
  @Provides
  static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @AppScope
  @Provides
  static Gson provideGson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

}
