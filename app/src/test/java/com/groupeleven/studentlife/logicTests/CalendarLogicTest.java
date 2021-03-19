package com.groupeleven.studentlife.logicTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        taskList = logic.viewTask("2021-04-01");
        assertNull("No Task in a day return NULL array", taskList);
        db.deleteAllTask();

    }

    @Test
    public void getTaskFromADayTest() {
        FakeDB db = new FakeDB();
        ITodolistLogic todoListLogic = new TodolistLogic(db);


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
        db.deleteAllTask();


    }

    @Test
    public void getTaskFromADayTestAndDeleteIt() {
        FakeDB db = new FakeDB();


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
        assertTrue(logic.deleteTask(1));

        assertEquals("Still, 2021-05-01 Should have 3 task for the day", 2, logic.viewTask("2021-05-01").length);
        db.deleteAllTask();

    }


    @Test
    public void editTaskTest() {
        FakeDB db = new FakeDB();


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
        assertTrue(logic.editTask(1, "Task-5", "LOW", "2021-05-01 01:02:12"));

        db.deleteAllTask();

    }


    @Test
    public void taskPriorityTest() {
        FakeDB db = new FakeDB();


        ITaskObject task1 = new Task("Task 1", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");


        db.insertTask(task1);

        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");

        assertEquals("Task Priority should be high", "High", logic.getTaskPriorityText(tasksList[0]));

        db.deleteAllTask();
    }

    @Test
    public void setCompletedTest() {

        FakeDB db = new FakeDB();


        ITaskObject task1 = new Task("Task 1", ITaskObject.Priority.HIGH, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "reading", 10, "pages");


        db.insertTask(task1);

        ICalendarLogic logic = new CalendarLogic(db);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");


        assertEquals("Estimated time: 20",20,    logic.getTimeEstimate(0,tasksList));
        db.deleteAllTask();
    }





}
