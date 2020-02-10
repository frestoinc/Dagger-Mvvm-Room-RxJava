package com.frestoinc.sampleapp.data.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by frestoinc on 02,February,2020 for SampleApp.
 */
public class NestedConverter {

    @TypeConverter
    public static List<GithubModel.GithubInnerModel> convertToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<GithubModel.GithubInnerModel>>() {
        }.getType();
        return new Gson().fromJson(data, type);
    }

    @TypeConverter
    public static String convertToString(List<GithubModel.GithubInnerModel> list) {
        return new Gson().toJson(list);
    }

}
