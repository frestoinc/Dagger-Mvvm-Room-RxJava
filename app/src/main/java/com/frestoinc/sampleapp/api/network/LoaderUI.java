package com.frestoinc.sampleapp.api.network;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public interface LoaderUI {

  void switchToError();

  void switchToEmpty();

  NetworkState getState();
}
