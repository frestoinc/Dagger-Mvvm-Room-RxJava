package com.frestoinc.gojekassignment.api.view.expandable;

import androidx.room.TypeConverter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frestoinc on 23,December,2019 for MailDemo.
 */
public enum State {
  @SerializedName("Collapsed")
  COLLAPSED(0),
  @SerializedName("Collapsing")
  COLLAPSING(1),
  @SerializedName("Expanding")
  EXPANDING(2),
  @SerializedName("Expanded")
  EXPANDED(3);

  private Integer code;

  State(Integer code) {
    this.code = code;
  }

  @TypeConverter
  public static Integer getStatusInt(State state) {
    if (state != null) {
      return state.code;
    }
    return null;
  }

  public static State valueOf(Integer code) {
    State[] vals = values();
    if (code >= 0 && code < vals.length)
      return vals[code];
    return null;
  }
}
