package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface ITodolistLogic {
    public static int toInt(String priority){
        int result = 0;
        if (priority.equals("High")) {
            result = 1;
        }
        else if(priority.equals("Medium")){
            result = 2;
        }
        else if(priority.equals("Low")){
            result = 3;
        }
        return result;
    }

    public static String toPriority(int priority){
        String result ="";
        if (priority==1) {
            result = "High";
        }
        else if(priority==2){
            result = "Medium";
        }
        else if(priority==3){
            result = "Low";
        }
        return result;
    }

    public Task[] getData() throws RuntimeException;

    //--------------------------------------------------------------------------------------------------
    // add a task
    public boolean addTask(String name, int priority, String endTime);

    //--------------------------------------------------------------------------------------------------
    // edit a task
    public boolean editTask(int id, String name, int priority, String endTime);

    //--------------------------------------------------------------------------------------------------
    // delete a task
    public boolean deleteTask(int id);

    public String whichDataNotfill(String taskName, String taskPriority, String taskDate, String taskTime);
}
