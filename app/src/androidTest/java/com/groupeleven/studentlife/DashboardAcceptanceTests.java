package com.groupeleven.studentlife;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.groupeleven.studentlife.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DashboardAcceptanceTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void displayTest() {
        onView(withId(R.id.navigation_dashboard)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.dashboard_layout)).check(matches(isDisplayed()));
    }

    //task display will be tested in To-do List Acceptance Test

}
