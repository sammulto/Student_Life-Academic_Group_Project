package com.groupeleven.studentlife.acceptanceTests;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.ui.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasLinks;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UsefullinkAcceptanceTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void displayTest() {
        onView(ViewMatchers.withId(R.id.navigation_usefullinks)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.usefullink_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void linkTest() {
        onView(withId(R.id.navigation_usefullinks)).perform(click());
        onView(withId(R.id.link_desmos)).check(matches(withText("DESMOS")));
        onView(withId(R.id.link_desmos)).check(matches(hasLinks()));

        onView(withId(R.id.link_khan_academy)).check(matches(withText("KHAN ACADEMY")));
        onView(withId(R.id.link_khan_academy)).check(matches(hasLinks()));

        onView(withId(R.id.link_wiki)).check(matches(withText("WIKIPEDIA")));
        onView(withId(R.id.link_wiki)).check(matches(hasLinks()));

        onView(withId(R.id.link_quizlet)).check(matches(withText("QUIZLET")));
        onView(withId(R.id.link_quizlet)).check(matches(hasLinks()));

        onView(withId(R.id.link_ocw)).check(matches(withText("MIT OPENCOURSEWARE")));
        onView(withId(R.id.link_ocw)).check(matches(hasLinks()));

        onView(withId(R.id.link_youtube)).check(matches(withText("YOUTUBE")));
        onView(withId(R.id.link_youtube)).check(matches(hasLinks()));

        onView(withId(R.id.link_iclicker)).check(matches(withText("ICLICKER")));
        onView(withId(R.id.link_iclicker)).check(matches(hasLinks()));

        onView(withId(R.id.link_crowdmark)).check(matches(withText("CROWDMARK")));
        onView(withId(R.id.link_crowdmark)).check(matches(hasLinks()));

        onView(withId(R.id.link_umlearn)).check(matches(withText("UMLEARN")));
        onView(withId(R.id.link_umlearn)).check(matches(hasLinks()));

        onView(withId(R.id.link_gather)).check(matches(withText("GATHER")));
        onView(withId(R.id.link_gather)).check(matches(hasLinks()));

        onView(withId(R.id.link_google_doc)).check(matches(withText("GOOGLE DOC")));
        onView(withId(R.id.link_google_doc)).check(matches(hasLinks()));
    }

}
