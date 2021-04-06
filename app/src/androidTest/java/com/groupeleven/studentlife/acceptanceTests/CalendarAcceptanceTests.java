package com.groupeleven.studentlife.acceptanceTests;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.ui.MainActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertCustomAssertionAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalendarAcceptanceTests {

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
        onView(ViewMatchers.withId(R.id.navigation_calendar)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.calendar_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void b_displayTasksTest() {

        //add task
        ITaskObject taskObject1 = new Task("Task A", ITaskObject.Priority.HIGH, "2021-04-01 12:12:12", "2021-04-29 01:02:12", 0, "Reading", 10, "Pages");
        ITaskObject taskObject2 = new Task("Task B", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-17 01:02:12", 0, "Reading", 10, "Pages");
        ITaskObject taskObject3 = new Task("Task C", ITaskObject.Priority.MEDIUM, "2021-04-01 12:12:12", "2021-04-22 01:02:12", 0, "Reading", 10, "Pages");
        ITaskObject taskObject4 = new Task("Task D", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-29 06:02:12", 0, "Reading", 10, "Pages");
        testDB.insertTask(taskObject1);
        testDB.insertTask(taskObject2);
        testDB.insertTask(taskObject3);
        testDB.insertTask(taskObject4);

        onView(withId(R.id.navigation_calendar)).perform(click());

        //day 22 should have 1 tasks with name "Task C"
        ViewInteraction dayView22 = onView(allOf(withText("22"), withContentDescription("22"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 32), isDisplayed()));
        dayView22.perform(click());
        assertCustomAssertionAtPosition(R.id.calendar_recyclerView, 0, R.id.calendar_todoCheckBox, matches(withText(containsString("Task C"))));
        assertListItemCount(R.id.calendar_recyclerView, 1);
        //test the check box
        onView(withId(R.id.calendar_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.calendar_todoCheckBox)).check(matches(isChecked()));
        onView(withId(R.id.calendar_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.calendar_todoCheckBox)).check(matches(isNotChecked()));

        //day 17 should have 1 tasks
        ViewInteraction dayView17 = onView(allOf(withText("17"), withContentDescription("17"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 27), isDisplayed()));
        dayView17.perform(click());
        assertListItemCount(R.id.calendar_recyclerView, 1);

        //day 29 should have 2 tasks
        ViewInteraction dayView29 = onView(allOf(withText("29"), withContentDescription("29"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 39), isDisplayed()));
        dayView29.perform(click());
        assertListItemCount(R.id.calendar_recyclerView, 2);

        //day 5 should have no tasks, the recycler view should not display
        ViewInteraction dayView5 = onView(allOf(withText("5"), withContentDescription("5"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 15), isDisplayed()));
        dayView5.perform(click());
        onView(withId(R.id.calendar_recyclerView)).check(matches(not(isDisplayed())));

    }


    @Test
    public void c_editTaskTest() {

        ITaskObject taskObject = new Task("To Edit", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-22 06:02:12", 0, "Reading", 10, "Pages");
        testDB.insertTask(taskObject);
        onView(withId(R.id.navigation_calendar)).perform(click());
        ViewInteraction dayView22 = onView(allOf(withText("22"), withContentDescription("22"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 32), isDisplayed()));
        dayView22.perform(click());

        onView(withId(R.id.calendarEditTask)).perform(click());

        //check if edit activity is display
        onView(withId(R.id.toupdate_layout)).check(matches(isDisplayed()));

        //perform Edit action
        fillTask(2021,04,22,12,30,"After Edit","High","Reading","Pages","5");

        //check if the edited task is displayed
        dayView22.perform(click());
        assertCustomAssertionAtPosition(R.id.calendar_recyclerView, 0, R.id.calendar_todoCheckBox, matches(withText(containsString("After Edit"))));
    }

    @Test
    public void d_deleteTaskTest() {

        ITaskObject taskObject = new Task("To Edit", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-22 06:02:12", 0, "Reading", 10, "Pages");
        testDB.insertTask(taskObject);
        onView(withId(R.id.navigation_calendar)).perform(click());
        ViewInteraction dayView22 = onView(allOf(withText("22"), withContentDescription("22"), childAtPosition(allOf(withContentDescription("Calendar"), childAtPosition(withId(R.id.mcv_pager), 0)), 32), isDisplayed()));
        dayView22.perform(click());

        //perform Delete action
        onView(withId(R.id.calendarDeleteTask)).perform(click());

        //check if the edited task is delete
        onView(withId(R.id.calendar_recyclerView)).check(matches(not(isDisplayed())));
    }


    private static void fillTask(int year, int month, int day, int hrs, int min, String name, String priority, String taskType, String unit, String quantity){
        onView(withId(R.id.name)).perform(typeText(name));
        closeSoftKeyboard();
        pickTime(R.id.editStartTime,year,month,day, hrs, min-1);
        pickTime(R.id.editEndTime,year,month,day, hrs, min);
        onView(withId(R.id.prioritySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(priority))).perform(click());
        onView(withId(R.id.taskTypeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(taskType))).perform(click());
        onView(withId(R.id.quantity)).perform(typeText(quantity));
        closeSoftKeyboard();
        onView(withId(R.id.unitSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(unit))).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());
    }


    private static void pickTime(int launchViewId, int year, int month, int day, int hrs, int min ){
        onView(withId(launchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hrs,min));
        onView(withId(android.R.id.button1)).perform(click());
    }


    //method created by espresso recorder
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}