/*
 *   Dashboard Logic Unit
 */

package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class DashboardLogic {

    private DB database;

    public DashboardLogic(){
        this.database = new DB();
    }

    public Task[] getData() throws RuntimeException{

        //fetch data from the database
        Task[] list = database.getTasks();

        if (list == null){
            throw new RuntimeException("Database Returns null");
        }

        return list;
    }


}
