package com.groupeleven.studentlife.logicTests;

import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.DashboardLogic;
import com.groupeleven.studentlife.logic.IDashboardLogic;

import org.junit.Test;
import static org.junit.Assert.*;

public class DashboardLogicUnitTest {

    @Test
    public void getDateWhileEmpty(){
        //test the get data method while the database is empty
        FakeDB testDB = new FakeDB();
        DashboardLogic logic = new DashboardLogic(testDB);
        ITaskObject[] list = logic.getData();
        assertEquals("Returned list should be empty", list.length, 0);
    }

    @Test
    public void getDate(){
        //test the get data method
        FakeDB testDB = new FakeDB();
        DashboardLogic logic = new DashboardLogic(testDB);
        for(int i = 0; i<10; i++){
            testDB.insertTask(new Task("task"+i));
        }
        ITaskObject[] list = logic.getData();
        assertEquals("Returned list should be empty", list.length,10);
        testDB.clearDatabase();
    }

    @Test
    public void getPriorityTextTest(){
        FakeDB testDB = new FakeDB();
        IDashboardLogic logic = new DashboardLogic(testDB);
        ITaskObject task = new Task("Task Name",ITaskObject.Priority.HIGH,"2020-03-12 04:20:12","2020-03-12 14:20:12",12,"Reading");
        String priorityText = logic.getPriorityText(task);
        assertEquals("priorityText should be High",priorityText,"High");
    }
}
