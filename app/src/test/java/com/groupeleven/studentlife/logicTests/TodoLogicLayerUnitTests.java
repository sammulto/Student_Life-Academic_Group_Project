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
        db.deleteAllTask();
        TodolistLogic retrievedDataSafety = new TodolistLogic(db);

        assertTrue(retrievedDataSafety.addTask("Task1", "High", "2220-05-01 01:02:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(retrievedDataSafety.addTask("Task 2", "Low", "2020-07-01 11:22:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(retrievedDataSafety.addTask("Task-3", "Medium", "2020-11-01 07:20:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(retrievedDataSafety.addTask("Meeting", "High", "2020-03-01 07:10:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(retrievedDataSafety.addTask("project", "Medium", "2020-03-12 04:20:12","2220-05-02 01:02:12","Reading",10,"pages"));

        assertEquals(5, retrievedDataSafety.getData().length);
        db.deleteAllTask();


    }
    //No data loss: amount retrieved data should be equals to the amount of pushed data

    @Test
    public void addTask() {
//        public boolean addTask(String name, int priority,String endTime ){

        FakeDB db = new FakeDB();

        TodolistLogic addingNewTask = new TodolistLogic(db);
        assertTrue(addingNewTask.addTask("Task1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }


    //Task can't be added without the task name
    @Test
    public void addTaskNoNameFailed() {
        FakeDB db = new FakeDB();

        TodolistLogic noName = new TodolistLogic(db);

        assertFalse(noName.addTask("", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }
    //Task can't be added without the task name


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer
    @Test
    public void retrievedDataListIsNotEmpty() {
        FakeDB db = new FakeDB();
        TodolistLogic listIsNotEmpty = new TodolistLogic();
        assertTrue(listIsNotEmpty.addTask("Task1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertNotNull(listIsNotEmpty.getData());
        db.deleteAllTask();


    }
    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer

    @Test
    public void noTaskAdded() {
        FakeDB db = new FakeDB();
        TodolistLogic listIsNotEmpty = new TodolistLogic(db);

        assertEquals(0, listIsNotEmpty.getData().length);
        db.deleteAllTask();

    }
    // after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //Task can be edited
    @Test
    public void editTask() {
        FakeDB db = new FakeDB();
        TodolistLogic noName = new TodolistLogic(db);
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(noName.editTask(0,"it-1", "Medium", "2020-01-01 10:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }


    //Task edit should return false with no priority
    @Test
    public void editTaskFailed() {
        FakeDB db = new FakeDB();
        TodolistLogic noName = new TodolistLogic(db);
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertFalse(noName.editTask(0,"it-1", "Choose priority", "2020-01-01 10:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }
    //Task edit should return false with no priority



    //Task edit should return false with no priority
    @Test
    public void deleteTask() {
        FakeDB db = new FakeDB();
        TodolistLogic deleteTaskSuccess = new TodolistLogic(db);
        assertTrue(deleteTaskSuccess.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(deleteTaskSuccess.deleteTask(0));
        db.deleteAllTask();
    }
    //Task edit should return false with no priority


    //Delete Data safety, deleting only desired items
    @Test
    public void deleteTaskSafety() {
        FakeDB db = new FakeDB();
        TodolistLogic deleteDataSafety = new TodolistLogic(db);
        assertTrue(deleteDataSafety.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(deleteDataSafety.addTask("Task-3", "Medium", "2020-11-01 07:20:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(deleteDataSafety.addTask("Meeting", "High", "2020-03-01 07:10:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(deleteDataSafety.addTask("project", "Medium", "2020-03-12 04:20:12","2220-05-02 01:02:12","Reading",10,"pages"));

        assertTrue(deleteDataSafety.deleteTask(1));
        assertTrue(deleteDataSafety.deleteTask(2));
        assertEquals(2,deleteDataSafety.getData().length);

        db.deleteAllTask();
    }
    //Task edit should return false with no priority


  //Input Validation Exceptional tests
    @Test(expected = Exception.class)
    public void validateAllEmptyInputTest() throws Exception {
        TodolistLogic missingFields = new TodolistLogic();
        missingFields.checkUserInput(0," ",0, 0,"",0,"");
    }

    @Test(expected = Exception.class)
    public void validateEmptyNameInputTest() throws Exception {
        TodolistLogic missingFields = new TodolistLogic();
        missingFields.checkUserInput(0," ",0, 0,"", 0,"");
    }

    @Test(expected = Exception.class)
    public void validateEmptyTimeInputTest() throws Exception {
        TodolistLogic missingFields = new TodolistLogic();
        missingFields.checkUserInput(0," ",0, 0,"", 0,"");
    }

    @Test(expected = Exception.class)
    public void validateEmptyDateInputTest() throws Exception {
        TodolistLogic missingFields = new TodolistLogic();
        missingFields.checkUserInput(0," ",0, 0,"", 0,"");
    }

}
