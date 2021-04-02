package com.groupeleven.studentlife.logicTests;

import com.groupeleven.studentlife.faskDB.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;

import java.util.ArrayList;

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
    public void getDayListTest(){
        FakeDB db = new FakeDB();
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

        CalendarDay day1 = CalendarDay.from(2021,05,01);
        CalendarDay day2 = CalendarDay.from(2022,06,02);
        CalendarDay day3 = CalendarDay.from(2023,07,03);

        ArrayList<CalendarDay> dayList = logic.getDayList();

        assertEquals("The returned list should have 3 items.",3,dayList.size());
        assertTrue("The list should contian day1.",dayList.contains(day1));
        assertTrue("The list should contian day2.",dayList.contains(day2));
        assertTrue("The list should contian day3.",dayList.contains(day3));

        db.deleteAllTask();
    }

}
