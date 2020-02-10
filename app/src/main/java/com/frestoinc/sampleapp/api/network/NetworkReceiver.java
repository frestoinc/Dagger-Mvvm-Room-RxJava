package com.frestoinc.sampleapp.api.network;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public interface NetworkReceiver {

  void onNetworkStateChanged(boolean connected);
}
