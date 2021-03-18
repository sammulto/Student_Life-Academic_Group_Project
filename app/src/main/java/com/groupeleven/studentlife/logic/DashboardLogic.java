/*
 *   Dashboard Logic Unit
 */

package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

public class DashboardLogic implements IDashboardLogic {

    private IDatabase database;

    public DashboardLogic(IDatabase database){
        this.database = database;
    }

    public DashboardLogic(){
        this.database = new DB();
    }

    public ITaskObject[] getData(){

        //fetch data from the database
        ITaskObject[] list = null;

        try {
            list = (ITaskObject[]) database.getTasks();
        }catch(Exception exception){
            list = (ITaskObject[]) new ITaskObject[0];
        }

        return list;
    }

    public String getPriorityText (ITaskObject task){

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0,1) + rawText.substring(1).toLowerCase();
        return priorityText;
    }

}
