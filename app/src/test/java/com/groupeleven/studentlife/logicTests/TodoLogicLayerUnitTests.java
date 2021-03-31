package com.groupeleven.studentlife.logicTests;


import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class TodoLogicLayerUnitTests {

    //No data loss: amount retrieved data should be equals to the amount of pushed data
    @Test
    public void retrievedDataSafety() {

        FakeDB db = new FakeDB();
        db.deleteAllTask();
        ITodolistLogic retrievedDataSafety = new TodolistLogic(db);

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

        ITodolistLogic addingNewTask = new TodolistLogic(db);
        assertTrue(addingNewTask.addTask("Task1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }


    //Task can't be added without the task name
    @Test
    public void addTaskNoNameFailed() {
        FakeDB db = new FakeDB();

        ITodolistLogic noName = new TodolistLogic(db);

        assertFalse(noName.addTask("", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }
    //Task can't be added without the task name


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer
    @Test
    public void retrievedDataListIsNotEmpty() {
        FakeDB db = new FakeDB();
        ITodolistLogic listIsNotEmpty = new TodolistLogic();
        assertTrue(listIsNotEmpty.addTask("Task1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertNotNull(listIsNotEmpty.getData());
        db.deleteAllTask();


    }
    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //after first task addition database shouldn't be empty, and data can be pulled out to logic layer

    @Test
    public void noTaskAdded() {
        FakeDB db = new FakeDB();
        ITodolistLogic listIsNotEmpty = new TodolistLogic(db);

        assertEquals(0, listIsNotEmpty.getData().length);
        db.deleteAllTask();

    }
    // after first task addition database shouldn't be empty, and data can be pulled out to logic layer


    //Task can be edited
    @Test
    public void editTask() {
        FakeDB db = new FakeDB();
        ITodolistLogic noName = new TodolistLogic(db);
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(noName.editTask(0,"it-1", "Medium", "2020-01-01 10:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }


    //Task edit should return false with no priority
    @Test
    public void editTaskFailed() {
        FakeDB db = new FakeDB();
        ITodolistLogic noName = new TodolistLogic(db);
        assertTrue(noName.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertFalse(noName.editTask(0,"it-1", "Choose priority", "2020-01-01 10:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        db.deleteAllTask();
    }
    //Task edit should return false with no priority



    //Task edit should return false with no priority
    @Test
    public void deleteTask() {
        FakeDB db = new FakeDB();
        ITodolistLogic deleteTaskSuccess = new TodolistLogic(db);
        assertTrue(deleteTaskSuccess.addTask("it-1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages"));
        assertTrue(deleteTaskSuccess.deleteTask(0));
        db.deleteAllTask();
    }
    //Task edit should return false with no priority


    //Delete Data safety, deleting only desired items
    @Test
    public void deleteTaskSafety() {
        FakeDB db = new FakeDB();
        ITodolistLogic deleteDataSafety = new TodolistLogic(db);
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
    @Test(expected = IllegalArgumentException.class)
    public void validateAllEmptyInputTest() throws IllegalArgumentException {
        FakeDB db = new FakeDB();
        ITodolistLogic missingFields = new TodolistLogic(db);
        missingFields.checkUserInput(0,"Choose priority",0, 0,"",0,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateEmptyNameInputTest() throws IllegalArgumentException {
        FakeDB db = new FakeDB();
        ITodolistLogic missingFields = new TodolistLogic(db);
        missingFields.checkUserInput(0,"Choose priority",10, 10,"Reading", 1,"pages");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateEmptyTimeInputTest() throws IllegalArgumentException {
        FakeDB db = new FakeDB();
        ITodolistLogic missingFields = new TodolistLogic(db);
        missingFields.checkUserInput(5,"Choose priority",10, 10,"Reading", 0,"pages");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateEmptyDateInputTest() throws IllegalArgumentException {
        FakeDB db = new FakeDB();
        ITodolistLogic missingFields = new TodolistLogic(db);
        missingFields.checkUserInput(5,"Choose priority",0, 0,"Reading", 1,"pages");
    }

    @Test
    public void getTaskPriorityTextTest(){
        FakeDB db = new FakeDB();
        ITodolistLogic logic = new TodolistLogic(db);
        ITaskObject taskHigh = new Task("task");
        taskHigh.setPriority(ITaskObject.Priority.HIGH);
        ITaskObject taskMed = new Task("task");
        taskMed.setPriority(ITaskObject.Priority.MEDIUM);
        ITaskObject taskLow = new Task("task");
        taskLow.setPriority(ITaskObject.Priority.LOW);

        assertEquals("getTaskPriorityText should return High.","High", logic.getTaskPriorityText(taskHigh));
        assertEquals("getTaskPriorityText should return Medium.","Medium", logic.getTaskPriorityText(taskMed));
        assertEquals("getTaskPriorityText should return Low.","Low", logic.getTaskPriorityText(taskLow));
    }


    @Test(expected = ParseException.class)
    public void dateCheckExceptionTest() throws Exception {
        FakeDB db = new FakeDB();
        ITodolistLogic logic = new TodolistLogic(db);
        logic.dateCheck("not a date","not a date");
    }

    @Test
    public void dateCheckTest() {
        FakeDB db = new FakeDB();
        ITodolistLogic logic = new TodolistLogic(db);
        Boolean exceptionRaised = false;

        try {
            assertEquals("dateCheck should return true.",true, logic.dateCheck("2020-03-12 04:20:12", "2020-05-13 04:20:12"));
            assertEquals("dateCheck should return false.",false, logic.dateCheck("2020-03-12 04:20:12", "2019-03-11 04:20:12"));
        }catch (Exception e){
            exceptionRaised = true;
        }

        assertEquals("no exception should be raised",false, exceptionRaised);
    }



}
