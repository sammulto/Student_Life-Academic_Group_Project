/*
    Integration Tests between CalendarLogic and DB
 */

package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.faskDB.FakeDB;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CalendarAndDBTests {

    IDatabase db = new DB();

    ICalendarLogic logic = new CalendarLogic(db);


    @BeforeClass
    //make sure the database is empty before tests
    static public void beforeAllTests() {
        // DB is a singleton
        DB.setDBPath("src/main/assets/db/");
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }

    @After
    //clean up database after each test
    public void afterEachTest() {
        db.deleteAllTask();
    }


    @Test
    public void testGetTaskForADay() {

        ITaskObject taskObject1 = new Task("Task 1", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-11 01:02:12", 10, "Reading", 10, "Pages");
        ITaskObject taskObject2 = new Task("Task 2", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-11 01:02:12", 10, "Reading", 10, "Pages");
        ITaskObject taskObject3 = new Task("Task 3", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-11 01:02:12", 10, "Reading", 10, "Pages");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);

        assertEquals("Task for Date: 2021-04-11  Should be three", 3, logic.viewTask("2021-04-11").length);
        assertEquals("Task for Date: 2021-04-11  Should be three in DB", 3, db.getTasks("2021-04-11").length);

    }


    @Test
    public void testADayWithNoTask() {

        ITaskObject taskObject1 = new Task("Task 1", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-21 01:02:12", 10, "Reading", 10, "Pages");
        ITaskObject taskObject2 = new Task("Task 2", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-11 01:02:12", 10, "Reading", 10, "Pages");
        ITaskObject taskObject3 = new Task("Task 3", ITaskObject.Priority.LOW, "2021-04-01 12:12:12", "2021-04-12 01:02:12", 10, "Reading", 10, "Pages");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);


        assertEquals("Task for Date: 2021-04-02  Should be 0", 0, logic.viewTask("2021-04-02").length);
        assertEquals("Task for Date: 2021-04-02  Should be 0", 0, db.getTasks("2021-04-02").length);
    }

    @Test
    public void testNoTaskInAConsecutiveDay() {

        ITaskObject taskObject1 = new Task("Task 1", ITaskObject.Priority.LOW, "2021-05-01 12:12:12", "2021-05-01 16:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject2 = new Task("Task 2", ITaskObject.Priority.LOW, "2021-05-02 12:12:12", "2021-05-02 18:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 3", ITaskObject.Priority.LOW, "2021-05-04 12:12:12", "2021-05-04 19:02:12", 10, "Reading", 10, "Reading");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);


        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-01").length);
        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-02").length);
        assertEquals("Task for Date: 2021-04-02  Should be 0", 0, logic.viewTask("2021-05-03").length);
        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-04").length);
    }


    @Test
    public void estimatedTimeTest() {


        ITaskObject task1 = new Task("Task 1", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "pages");
        ITaskObject task2 = new Task("Task 2", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 1500, "words");
        ITaskObject task3 = new Task("Task 3", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "pages");

        ITaskObject[] taskList = null;

        db.insertTask(task1);
        db.insertTask(task2);
        db.insertTask(task3);


        ICalendarLogic logic = new CalendarLogic(db);

        int estimatedTime = logic.getTimeEstimate(0, logic.viewTask("2021-05-01"));


        assertEquals(20, estimatedTime);
        estimatedTime = logic.getTimeEstimate(1, logic.viewTask("2021-05-01"));

        assertEquals(10, estimatedTime);


    }


    @Test
    public void getDayListTest() {
        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject task1 = new Task("Task1");
        ITaskObject task2 = new Task("Task2");
        ITaskObject task3 = new Task("Task3");
        task1.setEndTime("2021-05-01 01:02:12");
        task2.setEndTime("2022-06-02 01:02:12");
        task3.setEndTime("2023-07-03 01:02:12");
        db.insertTask(task1);
        db.insertTask(task2);
        db.insertTask(task3);


        ArrayList<CalendarDay> dayList = logic.getDayList();
        assertNotNull(dayList);
        assertEquals("The returned list should have 3 items.", 3, dayList.size());
        assertEquals("The returned list should have 3 items in DB.", 3, db.getTasks().length);

    }


    @Test
    public void getTaskFromADayTestAndDeleteIt() {


        ITaskObject task1 = new Task("Task 1", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject task2 = new Task("Task 2", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject task3 = new Task("Task 3", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject[] taskList = null;

        db.insertTask(task1);
        db.insertTask(task2);
        db.insertTask(task3);

        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertNotNull(tasksList);
        assertEquals("2021-05-01 Should have 3 task for the day", 3, tasksList.length);
        assertTrue("Task deleted successfully", logic.deleteTask("2021-05-01", 1));

        assertEquals("Still, 2021-05-01 Should have 3 task for the day", 2, logic.viewTask("2021-05-01").length);

    }


    @Test
    public void editTaskTest() {


        ITaskObject task1 = new Task("Task 1", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject task2 = new Task("Task 2", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject task3 = new Task("Task 3", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject[] taskList = null;

        db.insertTask(task1);
        db.insertTask(task2);
        db.insertTask(task3);


        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertNotNull(tasksList);
        assertEquals("2021-05-01 Should have 3 task for the day", 3, tasksList.length);
        assertTrue(logic.editTask("2021-05-01", 1, "Task-5", "LOW", "2021-05-01 01:02:12", "2021-05-01 02:02:12", "Reading", 10, "Pages"));

        assertEquals("Name in DB also Changed","Task-5",db.getTasks("2021-05-01")[1].getTaskName());

    }


    @Test
    public void setCompletedTest() {


        ITaskObject task1 = new Task("Task 1", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "reading", 10, "pages");


        db.insertTask(task1);

        ICalendarLogic logic = new CalendarLogic(db);

        assertTrue("Task should be completed should be True", logic.setCompleted("2021-05-01", 0, true));



    }


    @Test
    public void addTaskTest() {

        ICalendarLogic noName = new CalendarLogic(db);

        assertTrue(noName.addTask("T-0", "High", "2020-01-01 00:12:12", "2020-01-01 01:02:12", "Reading", 10, "pages"));
        assertEquals(1, db.getTasks("2020-01-01").length);

    }


    @Test
    public void allTaskTest() {


        ITaskObject task1 = new Task("Task 1", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "reading", 10, "pages");
        db.insertTask(task1);

        ICalendarLogic logic = new CalendarLogic(db);
        ArrayList<ITaskObject> allTask = logic.getDayList();

        assertEquals("Task List should not be empty", 1, allTask.size());

        assertEquals("Task List in DB should not be empty", 1, db.getTasks().length);

    }


}
