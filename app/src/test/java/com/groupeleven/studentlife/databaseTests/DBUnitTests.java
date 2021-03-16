package com.groupeleven.studentlife.databaseTests;

import org.junit.Test;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.data.DB;

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
        assertArrayEquals("Newly created DB object should be empty", db.getTasks(), new Task[0]);
    }

    @Test
    public void insertBaseCase(){
        DB db = new DB("InsertTest", "mem");
        Task t = new Task("idk");
        assertTrue("Insert should return true", db.insertTask(t));
        Task[] tasks = new Task[1];
        tasks[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), tasks);
    }

    @Test
    public void insertWithDate(){
        DB db = new DB("insertDateTest", "mem");
        Task t = new Task("idk");
        t.setStartTime("2021-01-10 12:35:20");
        t.setEndTime("2021-01-10 12:25:20");
        assertTrue("Insert should return true", db.insertTask(t));
        Task[] tasks = new Task[1];
        tasks[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), tasks);
    }

    @Test
    public void deleteBaseCase(){
        DB db = new DB("DeleteTest", "mem");
        Task t = new Task("idk");
        db.insertTask(t);
        assertTrue("Delete should return true", db.deleteTask(t));
        assertArrayEquals("The database should be empty", db.getTasks(), new Task[0]);

    }

    @Test
    public void updateBaseCase(){
        DB db = new DB("UpdateTest", "mem");
        Task t = new Task("idk");
        Task[] tasks = new Task[1];
        db.insertTask(t);
        t.setType("Homework");
        tasks[0] = t;
        db.updateTask(t);
        assertTrue("Update should return true", db.updateTask(t));
        assertArrayEquals("The database should contain the updated task", db.getTasks(), tasks);
    }

}
