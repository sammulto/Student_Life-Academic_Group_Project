package com.groupeleven.studentlife.logicTests;


import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TodoLogicLayerUnitTests {

    //No data loss: amount retrieved data should be equals to the amount of pushed data
    @Test
    public void retrievedDataSafety() {

        FakeDB db = new FakeDB();
        db.clearDatabase();
        TodolistLogic retrievedDataSafety = new TodolistLogic();

        assertTrue(retrievedDataSafety.addTask("Task1", "High", "2220-05-01 01:02:12"));
        assertTrue(retrievedDataSafety.addTask("Task 2", "Low", "2020-07-01 11:22:12"));
        assertTrue(retrievedDataSafety.addTask("Task-3", "Medium", "2020-11-01 07:20:12"));
        assertTrue(retrievedDataSafety.addTask("Meeting", "High", "2020-03-01 07:10:12"));
        assertTrue(retrievedDataSafety.addTask("project", "Medium", "2020-03-12 04:20:12"));


        assertEquals(5, retrievedDataSafety.getData().length);
        db.clearDatabase();


    }
    //No data loss: amount retrieved data should be equals to the amount of pushed data

    @Test
    public void addTask() {
//        public boolean addTask(String name, int priority,String endTime ){

        FakeDB db = new FakeDB();

        TodolistLogic addingNewTask = new TodolistLogic();
        assertTrue(addingNewTask.addTask("Task1", "High", "2020-01-01 12:12:12"));
        db.clearDatabase();
    }


    //Task can't be added without the task name
    @Test
    public void addTaskNoNameFailed() {
        FakeDB db = new FakeDB();

        TodolistLogic noName = new TodolistLogic();

        assertFalse(noName.addTask("", "High", "2020-01-01 12:12:12"));
        db.clearDatabase();
    }
    //Task can't be added without the task name


    //Task can't be added without the priority
    //Priority can be set between (1-3)
    @Test
    public void addTaskNoPriorityFailed() {
        FakeDB db = new FakeDB();
        TodolistLogic noPriority = new TodolistLogic();
        assertFalse(noPriority.addTask("reading a book", "High", "2020-01-01 12:12:12"));
        db.clearDatabase();

    }
    //Task can't be added without the priority


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer
    @Test
    public void retrievedDataListIsNotEmpty() {
        FakeDB db = new FakeDB();
        TodolistLogic listIsNotEmpty = new TodolistLogic();
        assertTrue(listIsNotEmpty.addTask("Task1", "High", "2020-01-01 12:12:12"));
        assertNotNull(listIsNotEmpty.getData());
        db.clearDatabase();


    }
    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer

    @Test
    public void noTaskAdded() {
        FakeDB db = new FakeDB();
        TodolistLogic listIsNotEmpty = new TodolistLogic();

        assertEquals(0, listIsNotEmpty.getData().length);
        db.clearDatabase();

    }
    // after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //Task can be edited
    @Test
    public void editTask() {
        FakeDB db = new FakeDB();
        TodolistLogic noName = new TodolistLogic();
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12"));
        assertTrue(noName.editTask(0,"it-1", "Medium", "2020-01-01 10:12:12"));
        db.clearDatabase();
    }


    //Task edit should return false with no priority
    @Test
    public void editTaskFailed() {
        FakeDB db = new FakeDB();
        TodolistLogic noName = new TodolistLogic();
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12"));
        assertFalse(noName.editTask(0,"it-1", "Choose priority", "2020-01-01 10:12:12"));
        db.clearDatabase();
    }
    //Task edit should return false with no priority



    //Task edit should return false with no priority
    @Test
    public void deleteTask() {
        FakeDB db = new FakeDB();
        TodolistLogic deleteTaskSuccess = new TodolistLogic();
        assertTrue(deleteTaskSuccess.addTask("it-1", "High", "2020-01-01 12:12:12"));
        assertTrue(deleteTaskSuccess.deleteTask(0));
        db.clearDatabase();
    }
    //Task edit should return false with no priority


    //Delete Data safety, deleting only desired items
    @Test
    public void deleteTaskSafety() {
        FakeDB db = new FakeDB();
        TodolistLogic deleteDataSafety = new TodolistLogic();
        assertTrue(deleteDataSafety.addTask("it-1", "High", "2020-01-01 12:12:12"));
        assertTrue(deleteDataSafety.addTask("Task-3", "Medium", "2020-11-01 07:20:12"));
        assertTrue(deleteDataSafety.addTask("Meeting", "High", "2020-03-01 07:10:12"));
        assertTrue(deleteDataSafety.addTask("project", "Medium", "2020-03-12 04:20:12"));

        assertTrue(deleteDataSafety.deleteTask(1));
        assertTrue(deleteDataSafety.deleteTask(2));
        assertEquals(2,deleteDataSafety.getData().length);

        db.clearDatabase();
    }
    //Task edit should return false with no priority

  //Sending missing field information to UI
    @Test
    public void informMissingFields()
    {
        FakeDB db = new FakeDB();
        TodolistLogic missingFields = new TodolistLogic();
        //assertEquals("Please choose a priority",missingFields.whichDataNotfill("toDO","","2020-01-01", "12:12:12"));
        //assertEquals("Please fill all information",missingFields.whichDataNotfill("","","", ""));
        db.clearDatabase();
    }


//    //taking numerical Priority Level From DB and sending text priority level UI
//    @Test
//    public void priorityIntToString(){
//
//        FakeDB db = new FakeDB();
//        TodolistLogic priorityConversion = new TodolistLogic();
//        assertEquals("High",ITodolistLogic.toPriority(1));
//
//        db.clearDatabase();
//    }
//
//    //taking text priority level from UI and sending  numerical Priority Level to DB
//
//    @Test
//    public void priorityStringTInt(){
//
//        FakeDB db = new FakeDB();
//        TodolistLogic priorityConversion = new TodolistLogic();
//        assertEquals(2, ITodolistLogic.toInt("Medium"));
//
//        db.clearDatabase();
//    }
}
