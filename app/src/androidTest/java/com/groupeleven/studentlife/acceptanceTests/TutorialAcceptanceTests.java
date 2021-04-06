package com.groupeleven.studentlife.acceptanceTests;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorialAcceptanceTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void a_displayTest() {
        //check if tutorial button is shown
        onView(ViewMatchers.withId(R.id.navigation_usefullinks)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.tutorial_button)).check(matches(isDisplayed()));
    }

    @Test
    public void b_showTutorialTest() {
        //show tutorial
        onView(ViewMatchers.withId(R.id.navigation_usefullinks)).perform(click()).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.tutorial_button)).perform(click());
        onView(withId(R.id.pdfView)).check(matches(isDisplayed()));
    }


}


