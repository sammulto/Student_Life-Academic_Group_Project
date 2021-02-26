/*
 *   Dashboard Logic Unit
 */

package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class DashboardLogic {

    private FakeDB database;

    public DashboardLogic(){
        this.database = new FakeDB();
    }

    public Task[] getData(){

        //fetch data from the database
        Task[] list = null;

        try {
            list = database.getTasks();
        }catch(Exception exception){
            list = new Task[0];
        }

        return list;
    }

}
