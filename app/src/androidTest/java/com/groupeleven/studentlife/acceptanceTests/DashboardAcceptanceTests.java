package com.groupeleven.studentlife.acceptanceTests;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.ui.MainActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertCustomAssertionAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static org.hamcrest.Matchers.containsString;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DashboardAcceptanceTests {

    private IDatabase testDB;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void testSetup(){
        //clean up the data base
        testDB = new DB();
        testDB.deleteAllTask();
    }

    @Test
    public void a_displayTest() {
        onView(ViewMatchers.withId(R.id.navigation_dashboard)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.dashboard_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void b_displayTasksTest() {

        //add task
        ITaskObject taskObject = new Task("Task A", ITaskObject.Priority.HIGH, "2021-04-01 12:12:12", "2021-04-29 01:02:12", 0, "Reading", 10, "Pages");
        testDB.insertTask(taskObject);
        onView(withId(R.id.navigation_dashboard)).perform(click());

        //check if the new task is displayed in dashboard
        assertCustomAssertionAtPosition(R.id.dashboard_recyclerView, 0, R.id.dashboard_task_name, matches(withText(containsString("Task A"))));
        assertListItemCount(R.id.dashboard_recyclerView, 1);

    }

}
