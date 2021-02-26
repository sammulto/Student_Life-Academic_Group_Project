package com.groupeleven.studentlife.databaseTests;

import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FakeDBUnitTests {

    @Test(expected = RuntimeException.class)
    public void exceptionTest(){
        FakeDB db = new FakeDB();
        //getting data from empty database should raise an exception
        db.getTasks();
    }

    @Test
    public void emptyDatabaseTest(){
        FakeDB db = new FakeDB();
        assertEquals("The database should be empty", db.getSize(), 0);
    }

    @Test
    public void DBconstructor(){
        FakeDB db = new FakeDB();
        assertNotNull("Newly created DB object should not be null", db);
        assertEquals("The database should be empty", db.getSize(), 0);
        db.clearDatabase();
    }

    @Test
    public void insertBaseCase(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        assertTrue("Insert should return true", db.insertTask(t));
        Task[] tasks = new Task[1];
        tasks[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), tasks);
        db.clearDatabase();
    }

    @Test
    public void insertWithDate(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        t.setStartTime("2021-01-10 12:35:20");
        t.setEndTime("2021-01-10 12:25:20");
        assertTrue("Insert should return true", db.insertTask(t));
        Task[] tasks = new Task[1];
        tasks[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), tasks);
        db.clearDatabase();
    }

    @Test
    public void deleteBaseCase(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        db.insertTask(t);
        assertTrue("Delete should return true", db.deleteTask(t));
        assertEquals("The database should be empty", db.getSize(), 0);
        db.clearDatabase();
    }

    @Test
    public void updateBaseCase(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        Task[] tasks = new Task[1];
        db.insertTask(t);
        t.setType("Homework");
        tasks[0] = t;
        db.updateTask(t);
        assertTrue("Update should return true", db.updateTask(t));
        assertArrayEquals("The database should contain the updated task", db.getTasks(), tasks);
        db.clearDatabase();
    }

    @Test
    public void multipleItemTest(){
        Task[] taskList = new Task[10];
        FakeDB db = new FakeDB();
        for(int i = 0; i < 10; i++){
            taskList[i] = new Task("Task" + i);
            db.insertTask(taskList[i]);
        }

        assertTrue("Database should contain 10 Tasks", db.getTasks().length == 10);
        assertArrayEquals("The database should contain the updated task", db.getTasks(), taskList);
        db.clearDatabase();
    }

}
