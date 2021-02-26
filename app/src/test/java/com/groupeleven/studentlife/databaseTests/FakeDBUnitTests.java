package com.groupeleven.studentlife.databaseTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FakeDBUnitTests {

    @Test
    public void DBconstructor(){
        FakeDB db = new FakeDB();
        assertNotNull("Newly created DB object should not be null", db);
        assertArrayEquals("Newly created DB object should be empty", db.getTasks(), new Task[0]);
    }

    @Test
    public void insertBaseCase(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        assertTrue("Insert should return true", db.insertTask(t));
        Task[] tasks = new Task[1];
        tasks[0] = t;
        assertArrayEquals("The DB should only contain the task t", db.getTasks(), tasks);
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
    }

    @Test
    public void deleteBaseCase(){
        FakeDB db = new FakeDB();
        Task t = new Task("idk");
        db.insertTask(t);
        assertTrue("Delete should return true", db.deleteTask(t));
        assertArrayEquals("The database should be empty", db.getTasks(), new Task[0]);

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
    }

    @Test
    public void multipleItemTest(){
        Task[] taskList = new Task[10];
        FakeDB db = new FakeDB();
        for(int i = 0; i < 10; i++){
            taskList[i] = new Task("Task" + i);
            db.insertTask(taskList[i]);
        }

        Task[] fromDB = db.getTasks();

        for (int i = 0; i<10 ; i++){
            System.out.println(fromDB[i].getTaskName());
        }

        assertTrue("Database should contain 10 Tasks", db.getTasks().length == 10);
        assertArrayEquals("The database should contain the updated task", db.getTasks(), taskList);
    }

}
