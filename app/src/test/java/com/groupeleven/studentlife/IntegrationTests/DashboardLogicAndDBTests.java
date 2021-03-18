/*
 *   Integration Tests between DashboardLogic and DB
 */

package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.DashboardLogic;
import com.groupeleven.studentlife.logic.IDashboardLogic;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class DashboardLogicAndDBTests {

    IDatabase db = new DB();
    IDashboardLogic logic = new DashboardLogic(db);

    @BeforeClass
    //make sure the database is empty before tests
    static public void beforeAllTests(){
        // DB is a singleton
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }

    @After
    //clean up database after each test
    public void afterEachTest(){
       db.deleteAllTask();
    }

    @Test
    public void getDataFromEmptyDBTest(){
        ITaskObject list[] = logic.getData();
        assertEquals("The size of the list should be zero.", 0, list.length);
    }

    @Test
    public void getDataFromDBTest(){
        ITaskObject task1 = new Task("task 1");
        db.insertTask(task1);
        ITaskObject task2 = new Task("task 2");
        db.insertTask(task2);
        ITaskObject task3 = new Task("task 3");
        db.insertTask(task3);

        ITaskObject list[] = logic.getData();
        assertEquals("The size of the list should be 3.", 3, list.length);
        assertEquals("The task name should be task 1 .", "task 1", list[0].getTaskName());
    }

}
