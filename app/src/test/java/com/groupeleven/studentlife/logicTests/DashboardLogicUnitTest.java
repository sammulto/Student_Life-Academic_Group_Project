package com.groupeleven.studentlife.logicTests;

import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.DashboardLogic;

import org.junit.Test;
import static org.junit.Assert.*;

public class DashboardLogicUnitTest {

    @Test
    public void getDateWhileEmpty(){
        //test the get data method while the database is empty
        DashboardLogic logic = new DashboardLogic();
        Task[] list = logic.getData();
        assertEquals("Returned list should be empty", list.length, 0);
    }

    @Test
    public void getDate(){
        //test the get data method
        FakeDB db = new FakeDB();
        DashboardLogic logic = new DashboardLogic();
        for(int i = 0; i<10; i++){
            db.insertTask(new Task("task"+i));
        }
        Task[] list = logic.getData();
        assertEquals("Returned list should be empty", list.length,10);
        db.clearDatabase();
    }

}
