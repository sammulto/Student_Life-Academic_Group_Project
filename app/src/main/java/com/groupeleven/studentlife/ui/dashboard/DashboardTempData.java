/*
 *  This class is used to test the dashboard event list
 *
 */


package com.groupeleven.studentlife.ui.dashboard;

import android.annotation.SuppressLint;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;


public class DashboardTempData {
    private static DB database = new DB();
    
    public DashboardTempData(){}

    public static Task[] createTempData(int quantity){

        for(int i = 0; i < quantity; i++){
            Task newTask = new Task("Task Name" + i, 0, "2020-01-01 12:12:12", "2020-01-01 12:12:12", i, "test Type");
            database.insertTask(newTask);
        }

        return database.getTasks();
    }
}
