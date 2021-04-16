package com.groupeleven.studentlife.UnitTests;

import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.faskDB.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CalendarLogicTest {

    @Test
    public void noTaskInADateTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);

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
    public void getEstimatedTimeTest() {
        FakeDB db = new FakeDB();
        ICalendarLogic logic = new CalendarLogic(db);

        logic.addTask("Task 1", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 10, "pages");
        logic.addTask("Task 2", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12",  "Reading", 1500, "words");
        logic.addTask("Task 3", "High", "2021-05-01 05:12:12", "2021-05-01 07:02:12", "Reading", 10, "pages");

        int estimatedTime = logic.getTimeEstimate(0, logic.viewTask("2021-05-01"));

        assertEquals(20, estimatedTime);
        estimatedTime = logic.getTimeEstimate(1, logic.viewTask("2021-05-01"));

        assertEquals(10, estimatedTime);
        db.deleteAllTask();
    }


    @Test
    public void getTaskFromADayTestAndDeleteIt() {
        IDatabase mockDB = mock(FakeDB.class);
        ICalendarLogic logic = new CalendarLogic(mockDB);

        ITaskObject task1 = new Task(0,"Task 1", ITaskObject.Priority.HIGH,"2021-05-01 05:12:12", "2021-05-01 07:02:12",  0, "Reading", 10, "pages",false );
        ITaskObject task2 = new Task(1,"Task 2", ITaskObject.Priority.HIGH,"2021-05-01 05:12:12", "2021-05-01 07:02:12",  0, "Reading", 10, "pages",false );
        ITaskObject[] returnLista = {task1, task2};
        ITaskObject[] returnListb = {task2};
        Mockito.when(mockDB.getTasks("2021-05-01")).thenReturn(returnLista);
        Mockito.when(mockDB.deleteTask(task2)).thenReturn(true);

        ITaskObject[] taskList = logic.viewTask("2021-05-01");

        assertEquals("2021-05-01 Should have 2 task for the day", 2, taskList.length);
        assertTrue("Task deleted successfully", logic.deleteTask("2021-05-01", 1));

        Mockito.when(mockDB.getTasks("2021-05-01")).thenReturn(returnListb);
        assertEquals("Still, 2021-05-01 Should have 1 task for the day", 1, logic.viewTask("2021-05-01").length);
    }


    @Test
    public void editTaskTest() {
        IDatabase mockDB = mock(FakeDB.class);
        ICalendarLogic logic = new CalendarLogic(mockDB);

        ITaskObject task = new Task(0,"Task 1", ITaskObject.Priority.HIGH,"2021-05-01 05:12:12", "2021-05-01 07:02:12",  0, "Reading", 10, "pages",false );
        ITaskObject[] returnList = {task};
        Mockito.when(mockDB.getTasks("2021-05-01")).thenReturn(returnList);
        Mockito.when(mockDB.updateTask(task, 0)).thenReturn(true);

        ITaskObject[] tasksList = logic.viewTask("2021-05-01");
        assertNotNull(tasksList);
        assertEquals("2021-05-01 Should have 1 task for the day", 1, tasksList.length);
        assertTrue(logic.editTask("2021-05-01", 0, "Task-5", "LOW", "2021-05-01 01:02:12", "2021-05-01 02:02:12", "Reading", 10, "Pages"));
    }

    @Test
    public void setCompletedTest() {
        IDatabase mockDB = mock(FakeDB.class);
        ICalendarLogic logic = new CalendarLogic(mockDB);

        ITaskObject task = new Task(0,"Task 1", ITaskObject.Priority.HIGH,"2021-05-01 05:12:12", "2021-05-01 07:02:12",  0, "Reading", 10, "pages",false );
        ITaskObject[] returnList = {task};
        Mockito.when(mockDB.getTasks("2021-05-01")).thenReturn(returnList);
        Mockito.when(mockDB.updateTask(task, 0)).thenReturn(true);

        assertTrue("Task should be completed should be True", logic.setCompleted("2021-05-01", 0, true));
    }


    @Test(expected = ParseException.class)
    public void dateTest() throws ParseException {
        IDatabase mockDB = mock(FakeDB.class);
        ICalendarLogic logic = new CalendarLogic(mockDB);

        assertTrue(logic.dateCheck("2021-05-01", "2021-05-01"));
        assertFalse(logic.dateCheck("05-2021-01", "2021-05-01"));
    }
}

