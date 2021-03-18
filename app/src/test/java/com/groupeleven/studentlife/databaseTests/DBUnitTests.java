package com.groupeleven.studentlife.databaseTests;

import org.junit.Test;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.logic.TodolistLogic;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DBUnitTests {

    @Test
    public void DBconstructor(){
        DB db = new DB("ConstructorTest", "mem");
        assertNotNull("Newly created DB object should not be null", db);
        assertArrayEquals("Newly created DB object should be empty", db.getTasks(), new ITaskObject[0]);
    }

    @Test
    public void insertBaseCase(){
        DB db = new DB("InsertTest", "mem");
        Task t = new Task("idk");
        assertTrue("Insert should return true", db.insertTask(t));
        ITaskObject[] ITaskObjects = new ITaskObject[1];
        ITaskObjects[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), ITaskObjects);
    }

    @Test
    public void insertWithDate(){
        DB db = new DB("insertDateTest", "mem");
        Task t = new Task("idk");
        t.setStartTime("2021-01-10 12:35:20");
        t.setEndTime("2021-01-10 12:25:20");
        assertTrue("Insert should return true", db.insertTask(t));
        ITaskObject[] ITaskObjects = new ITaskObject[1];
        ITaskObjects[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), ITaskObjects);
    }

    @Test
    public void deleteBaseCase(){
        DB db = new DB("DeleteTest", "mem");
        Task t = new Task("idk");
        db.insertTask(t);
        assertTrue("Delete should return true", db.deleteTask(t));
        assertArrayEquals("The database should be empty", db.getTasks(), new ITaskObject[0]);

    }

    @Test
    public void updateBaseCase(){
        DB db = new DB("UpdateTest", "mem");
        Task t = new Task("idk");
        ITaskObject[] ITaskObjects = new ITaskObject[1];
        db.insertTask(t);
        t.setType("Homework");
        ITaskObjects[0] = t;
        db.updateTask(t);
        assertTrue("Update should return true", db.updateTask(t));
        assertArrayEquals("The database should contain the updated task", db.getTasks(), ITaskObjects);
    }

    @Test
    public void updateTaskNameCase() {
        DB db = new DB("UpdateTaskNameTest", "mem");
        ITaskObject t = new Task(0,"idk");
        db.insertTask(t);
        t.setType("Homework");
        t.setTaskName("new name");
        ITaskObject[] ITaskObjects = new ITaskObject[1];
        ITaskObjects[0] = t;
        assertTrue("Update should return true", db.updateTask(t,0));
        assertArrayEquals("The database should contain the updated task", db.getTasks(), ITaskObjects);
        String newTaskName = db.getTasks()[0].getTaskName();
        assertEquals("The return task should have a task name :new name", newTaskName, "new name");
    }

}
