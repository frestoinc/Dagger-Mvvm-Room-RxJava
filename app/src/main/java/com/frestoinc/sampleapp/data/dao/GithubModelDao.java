package com.frestoinc.sampleapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.frestoinc.sampleapp.data.model.GithubModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by frestoinc on 02,February,2020 for SampleApp.
 */
@Dao
public interface GithubModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<GithubModel> githubModel);

    @Query("DELETE FROM github")
    Completable deleteAll();

    @Query("SELECT * from github ORDER BY author ASC")
    Single<List<GithubModel>> getAllEntries();
}
