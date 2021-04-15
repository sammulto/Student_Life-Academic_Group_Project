package com.groupeleven.studentlife.UnitTests;

import com.groupeleven.studentlife.faskDB.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CalendarLogicTest {


    @Test

    public void noTaskInADateTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] taskList = null;
        assertNull("No Task in a day return NULL array", logic.viewTask("2021-04-01"));
        db.deleteAllTask();
    }

    @Test
    public void getTaskFromADayTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");


        ITaskObject[] taskList = null;


        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertEquals("2021-05-01 Should have 3 task for the day", 3, tasksList.length);
        db.deleteAllTask();
    }


    @Test
    public void taskPriorityTest() {
        FakeDB db = new FakeDB();

        ICalendarLogic logic = new CalendarLogic(db);

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");



        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertEquals("Task Priority should be high", "High", logic.getTaskPriorityText(tasksList[0]));

        db.deleteAllTask();
    }


    @Test
    public void getDayListTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");




        ArrayList<CalendarDay> dayList = logic.getDayList();

        assertNotNull(dayList);
        assertEquals("The returned list should have 3 items.", 3, dayList.size());

        db.deleteAllTask();
    }


    @Test
    public void getTaskFromADayTestAndDeleteIt() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);


        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");

        ITaskObject[] taskList = null;


        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertEquals("2021-05-01 Should have 3 task for the day", 3, tasksList.length);
        assertTrue("Task deleted successfully", logic.deleteTask("2021-05-01", 1));

        assertEquals("Still, 2021-05-01 Should have 3 task for the day", 2, logic.viewTask("2021-05-01").length);
        db.deleteAllTask();

    }


    @Test
    public void editTaskTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);


        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");

        ITaskObject[] taskList = null;




        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertNotNull(tasksList);
        assertEquals("2021-05-01 Should have 3 task for the day", 3, tasksList.length);
        assertTrue(logic.editTask("2021-05-01", 1, "Task-5", "LOW", "2021-05-01 01:02:12", "2021-05-01 02:02:12", "Reading", 10, "Pages"));

        db.deleteAllTask();

    }


    @Test
    public void setCompletedTest() {

        FakeDB db = new FakeDB();

        ICalendarLogic logic = new CalendarLogic(db);

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");




        ITaskObject[] tasksList = logic.viewTask("2021-05-01");


        assertTrue("Task should be completed should be True", logic.setCompleted("2021-05-01", 0, true));
        db.deleteAllTask();
    }


    @Test
    public void addTaskTest() {
        FakeDB db = new FakeDB();

        ICalendarLogic noName = new CalendarLogic(db);

        assertTrue(noName.addTask("T-0", "High", "2020-01-01 12:12:12", "2220-05-02 01:02:12", "Reading", 10, "pages"));
        db.deleteAllTask();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateEmptyDateInputTest() throws IllegalArgumentException {
        FakeDB db = new FakeDB();
        ICalendarLogic missingFields = new CalendarLogic(db);
        missingFields.checkUserInput(5, "Choose priority", 0, 0, "Reading", 1, "pages");

    }


    @Test
    public void allTaskTest() {

        FakeDB db = new FakeDB();

        ICalendarLogic logic = new CalendarLogic(db);
        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");


        ArrayList<ITaskObject> allTask = logic.getDayList();

        assertEquals("Task List should not be empty", allTask.size(), 1);

        db.deleteAllTask();

    }

    @Test
    public void getEstimatedTimeTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);



        ITaskObject[] taskList = null;

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 1500, "words");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");


        int estimatedTime = logic.getTimeEstimate(0, logic.viewTask("2021-05-01"));


        assertEquals(20, estimatedTime);
        estimatedTime = logic.getTimeEstimate(1, logic.viewTask("2021-05-01"));

        assertEquals(10, estimatedTime);
        db.deleteAllTask();


    }

    //    (Excepted= ParseException.class)
    @Test(expected = ParseException.class)
    public void dateTest() throws ParseException {
        FakeDB db = new FakeDB();


        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertTrue(logic.dateCheck("2021-05-01", "2021-05-01"));
        assertFalse(logic.dateCheck("05-2021-01", "2021-05-01"));

        db.deleteAllTask();


    }
}

