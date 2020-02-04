package com.frestoinc.gojekassignment.data;

/**
 * Created by frestoinc on 02,February,2020 for GoJekAssignment.
 */
public class Constants {

  public static final String BASE_URL = "https://github-trending-api.now.sh/";

  public static final String REST_CONSTANT = "repositories";

    private static final String FIELD_ERROR = "Error";

  public static final String ROOM_DB = "github.db";

  public String getError() {
    return FIELD_ERROR;
  }
}
