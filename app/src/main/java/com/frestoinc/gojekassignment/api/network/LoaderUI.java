package com.frestoinc.gojekassignment.api.network;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public interface LoaderUI {

  void switchToNoNetwork();

  void switchToEmpty();

  void switchToLoading(String text);

  NetworkState getState();
}
