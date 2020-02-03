package com.frestoinc.gojekassignment.ui;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.frestoinc.gojekassignment.R;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class, true);

    @Test
    public void test_Context() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Assert.assertEquals("com.frestoinc.gojekassignment", appContext.getPackageName());
    }

    @Test
    public void test_viewInActivity() {
        onView(withId(R.id.content)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.containerRc)).check(matches(isDisplayed()));
        onView(withId(R.id.container)).check(matches(isDisplayed()));
        onView(withId(R.id.loadingContainer)).check(matches(isDisplayed()));
    }

    @Test
    public void test_viewErrorNotInActivity() {
        onView(withId(R.id.error_btn)).check(doesNotExist());
        onView(withId(R.id.error_img)).check(doesNotExist());
        onView(withId(R.id.error_text1)).check(doesNotExist());
        onView(withId(R.id.error_text2)).check(doesNotExist());
        onView(withId(R.id.error_btn)).check(doesNotExist());
    }

    @Test
    public void test_OptionMenu() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText("Sort by stars")).perform(click());
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText("Sort by name")).perform(click());
    }
}