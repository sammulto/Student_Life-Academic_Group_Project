package com.groupeleven.studentlife;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertCustomAssertionAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;



@RunWith(AndroidJUnit4.class)
public class TodoListAcceptanceTests {

    private View decorView;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Before
    public void testSetup(){
        //clean up the data base
        DB testDB = new DB();
        testDB.deleteAllTask();
    }

    @Test
    public void displayTest() {
        onView(withId(R.id.navigation_todolist)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.todolist_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void addTaskTest() {

        onView(withId(R.id.navigation_todolist)).perform(click());
        onView(withId(R.id.fbutton)).perform(click());
        fillTask(2021,04,15,15,30,"addTaskTest","High","Reading","Words","1200");

        //should have one task display
        assertCustomAssertionAtPosition(R.id.task_recyclerView, 0, R.id.todoCheckBox, matches(withText(containsString("addTaskTest"))));
        assertListItemCount(R.id.task_recyclerView, 1);

        //test the check box
        onView(withId(R.id.task_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.todoCheckBox)).check(matches(isChecked()));
        onView(withId(R.id.task_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.todoCheckBox)).check(matches(isNotChecked()));

        //check if the new task is displayed in dashboard
        onView(withId(R.id.navigation_dashboard)).perform(click());

        //add more task
        onView(withId(R.id.navigation_todolist)).perform(click());
        onView(withId(R.id.fbutton)).perform(click());
        fillTask(2021,04,15,15,30,"Task B","High","Homework","Days","1");
        onView(withId(R.id.fbutton)).perform(click());
        fillTask(2021,04,15,15,30,"Task C","Low","Reading","Pages","5");
        assertListItemCount(R.id.task_recyclerView, 3);
    }

    @Test
    public void EditTaskTest() {

        onView(withId(R.id.navigation_todolist)).perform(click());
        onView(withId(R.id.fbutton)).perform(click());
        fillTask(2021,04,13,12,30,"Before Edit","Low","Reading","Pages","10");

        //check if the new task is displayed
        assertCustomAssertionAtPosition(R.id.task_recyclerView, 0, R.id.todoCheckBox, matches(withText(containsString("Before Edit"))));

        //perform Edit action
        clickListItemChild(R.id.task_recyclerView, 0, R.id.editTask);
        fillTask(2021,04,13,12,30,"After Edit","Medium","Reading","Pages","10");

        //check if the edited task is displayed
        assertCustomAssertionAtPosition(R.id.task_recyclerView, 0, R.id.todoCheckBox, matches(withText(containsString("After Edit"))));
    }

    @Test
    public void DeleteTaskTest() {

        onView(withId(R.id.navigation_todolist)).perform(click());
        onView(withId(R.id.fbutton)).perform(click());
        fillTask(2021,04,13,12,30,"DeleteTask","Low","Reading","Pages","10");

        //perform Delete action
        clickListItemChild(R.id.task_recyclerView, 0, R.id.deleteTask);

        //check if the edited task is delete
        assertListItemCount(R.id.task_recyclerView, 0);
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

}
