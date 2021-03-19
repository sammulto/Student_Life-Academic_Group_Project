/*
    Integration Tests between CalendarLogic and DB
 */

package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CalendarAndDBTests {

    IDatabase db = new DB();
    
    ICalendarLogic logic = new CalendarLogic(db);


    @BeforeClass
    //make sure the database is empty before tests
    static public void beforeAllTests() {
        // DB is a singleton
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }

    @After
    //clean up database after each test
    public void afterEachTest() {
        db.deleteAllTask();
    }

    @Test
    public void testDeleteTaskFromCalendar() {

        //add data to DB
        ITaskObject taskObject1 = new Task("Task 1", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject taskObject2 = new Task("Task 2", null, "2021-05-02 12:12:12", "2021-05-02 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 3", null, "2021-05-04 12:12:12", "2021-05-04 01:02:12", 10, "Reading", 10, "Reading");


        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);

        ITodolistLogic todolistLogic = new TodolistLogic(db);


        System.out.println(todolistLogic.getData().length + " " + db.getSize() + " " + logic.viewTask("2021-05-01").length);
        ITaskObject list[] = logic.viewTask("2021-05-01");

        assertEquals("The size of the list should be 3.", 1, list.length);

        assertArrayEquals("The task list should be be the same.", list, db.getTasks("2021-05-01"));
        System.out.println(todolistLogic.getData().length + " " + db.getSize() + " " + logic.viewTask("2021-05-01").length);

        assertEquals("The Task name should be Task 1", "Task 1", list[0].getTaskName());


        //delete data from DB via Logic Unit
        assertEquals("Deleting Task", true, logic.deleteTask(1));


        list = logic.viewTask("2021-05-02");
        assertEquals("The size of the list should be 0.", 0, list.length);
        logic.deleteTask(0);
        logic.deleteTask(0);
        list = logic.viewTask("2021-05-01");

        assertEquals("The size of the list should be 0.", 0, list.length);
        list = logic.viewTask("2021-05-04");
        assertEquals("The size of the list should be 0.", 0, list.length);
        assertEquals("The size of the list should be 0.", 0, list.length);


    }

    @Test
    public void testGetTaskForADay() {


        ITaskObject taskObject1 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject taskObject2 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);


        assertEquals("Task for Date: 2021-04-01  Should be three", 3, logic.viewTask("2021-04-01").length);


    }


    @Test
    public void testADayWithNoTask() {


//    public Task(String taskName, ITaskObject.Priority priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit, boolean completed)
        ITaskObject taskObject1 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject taskObject2 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 1", null, "2021-04-01 12:12:12", "2021-04-01 01:02:12", 10, "Reading", 10, "Reading");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);


        assertEquals("Task for Date: 2021-04-02  Should be 0", 0, logic.viewTask("2021-04-02").length);


    }

    @Test
    public void testNoTaskInAConsecutiveDay() {


        ITaskObject taskObject1 = new Task("Task 1", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject taskObject2 = new Task("Task 1", null, "2021-05-02 12:12:12", "2021-05-02 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 1", null, "2021-05-04 12:12:12", "2021-05-04 01:02:12", 10, "Reading", 10, "Reading");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);


        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-01").length);

        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-02").length);
        assertEquals("Task for Date: 2021-04-02  Should be 0", 0, logic.viewTask("2021-05-03").length);
        assertEquals("Task for Date: 2021-04-02  Should be 1, so taskList is not NULL", 1, logic.viewTask("2021-05-04").length);

    }


    @Test
    public void editTaskTest() {

        ITaskObject taskObject1 = new Task("Task 1", null, "2021-05-01 12:12:12", "2021-05-01 01:02:12", 10, "Reading", 10, "Reading");

        ITaskObject taskObject2 = new Task("Task 2", null, "2021-05-02 12:12:12", "2021-05-02 01:02:12", 10, "Reading", 10, "Reading");
        ITaskObject taskObject3 = new Task("Task 3", null, "2021-05-04 12:12:12", "2021-05-04 01:02:12", 10, "Reading", 10, "Reading");

        db.insertTask(taskObject1);
        db.insertTask(taskObject2);
        db.insertTask(taskObject3);

        assertEquals("Task Updated",true, logic.editTask(1, "new name", "Low", "2021-05-02 01:02:12"));
       



    }
}
