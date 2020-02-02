package com.frestoinc.gojekassignment.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.frestoinc.gojekassignment.data.dao.GithubModelDao;
import com.frestoinc.gojekassignment.data.model.GithubModel;

/**
 * Created by frestoinc on 02,February,2020 for GoJekAssignment.
 */
@Database(entities = {GithubModel.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

  public abstract GithubModelDao modelDao();
}
