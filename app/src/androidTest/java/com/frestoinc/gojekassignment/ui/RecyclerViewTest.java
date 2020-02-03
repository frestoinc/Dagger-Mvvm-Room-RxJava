package com.frestoinc.gojekassignment.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.frestoinc.gojekassignment.R;
import com.frestoinc.gojekassignment.data.model.GithubModel;
import com.frestoinc.gojekassignment.data.model.MockGitHubData;
import com.google.gson.Gson;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.util.Checks.checkNotNull;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class RecyclerViewTest {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class, true);

    private int listSize = 3;

    private List<GithubModel> getList() {
        JSONArray array = MockGitHubData.generateFakeData();
        GithubModel[] arrName = new Gson().fromJson(array.toString(), GithubModel[].class);
        return Arrays.stream(arrName).collect(Collectors.toList());
    }

    @Test
    public void test_RecyclerViewItem() {
        onView(withId(R.id.containerRc)).check(matches(isDisplayed()));
        waitFor(3000);
        onView(withId(R.id.containerRc))
                .check(matches(atPosition(
                        0, hasDescendant(withId(R.id.vhd_avatar)))));

        onView(withId(R.id.containerRc))
                .check(matches(atPosition(
                        0, hasDescendant(withId(R.id.vhd_author)))));

        onView(withId(R.id.containerRc))
                .check(matches(atPosition(
                        0, hasDescendant(withId(R.id.vhd_name)))));
    }

    @Test
    public void test__RecyclerViewSelectItem() {
        onView(withId(R.id.containerRc)).check(matches(isDisplayed()));
        waitFor(3000);
        for (int i = 0; i < listSize; i++) {
            onView(withId(R.id.containerRc))
                    .perform(actionOnItemAtPosition(i, click()));
            onView(withId(R.id.containerRc))
                    .check(matches(atPosition(
                            i, hasDescendant(withId(R.id.vhd_description)))));
        }
    }

    @Test
    public void test_ModelSize() {
        assert getList().size() == listSize;
    }

    private static void waitFor(long duration) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + duration;
        while (System.currentTimeMillis() < endTime) {
        }
    }

    private static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}
