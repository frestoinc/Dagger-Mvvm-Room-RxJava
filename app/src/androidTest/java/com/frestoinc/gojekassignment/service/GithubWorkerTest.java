package com.frestoinc.gojekassignment.service;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.testing.TestListenableWorkerBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
@RunWith(AndroidJUnit4.class)
public class GithubWorkerTest {
    private Context mContext;

    @Before
    public void setUp() {
        mContext = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testGithubWorker() {
        Data inputData = new Data.Builder()
                .putLong("SLEEP_DURATION", 10_000L)
                .build();

        ListenableWorker worker =
                TestListenableWorkerBuilder.from(mContext,
                        GithubWorker.class)
                        .setInputData(inputData)
                        .build();

        assertThat(worker.startWork(), is(ListenableWorker.Result.success()));
    }
}