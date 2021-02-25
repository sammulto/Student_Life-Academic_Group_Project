package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class TodolistLogic {

    private FakeDB database;

    public TodolistLogic(){this.database = new FakeDB(); }

//--------------------------------------------------------------------------------------------------
// get task list form database

    public Task[] getData() throws RuntimeException{
        //fetch data from the database
        Task[] list = database.getTasks();

        if (list == null){
            throw new RuntimeException("Database Returns null");
        }
        return list;
    }

//--------------------------------------------------------------------------------------------------
// add a task
    public boolean addTask(String name, int priority,String endTime ){
        Task newTask = new Task(name, priority, "2020-01-01 12:12:12",endTime, 0, "test Type");
        boolean notEmptyName = !name.equals("");
        boolean notEmptyPriority = (priority!=0);
        boolean not0priority = priority!=0;
        boolean result = false;

        if(notEmptyName&&notEmptyPriority&&not0priority) {
            result = database.insertTask(newTask);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// edit a task
    public boolean editTask(String name, int priority,String endTime ){
        Task newTask = new Task(name, priority, "2020-01-01 12:12:12",endTime, 0, "test Type");
        boolean notEmptyName = !name.equals("");
        boolean notEmptyPriority = (priority!=0);
        boolean not0priority = priority!=0;
        boolean result = false;

        if(notEmptyName&&notEmptyPriority&&not0priority) {
            result = database.updateTask(newTask);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// delete a task
    public boolean deleteTask(int id){
        Task whichTask = database.getTasks()[id];

        return database.deleteTask(whichTask);
    }

//--------------------------------------------------------------------------------------------------
// find which data user no input in adding

    public String whichDataNotfill(String taskName, String taskPriority, String taskDate,String taskTime) {
        String result = "";
        int intPriority = toInt(taskPriority);

        if(intPriority==0&&!taskName.equals("")&&!taskDate.equals("")&&!taskTime.equals("")){
            result = "Please choose a priority";
        }
        else if (intPriority!=0&&taskName.equals("")&&!taskDate.equals("")&&!taskTime.equals("")){
            result = "Please input a task name";
        }
        else if(intPriority!=0&&!taskName.equals("")&&taskDate.equals("")&&!taskTime.equals("")){
            result = "Please choose a date";
        }
        else if(intPriority!=0&&!taskName.equals("")&&!taskDate.equals("")&&taskTime.equals("")){
            result = "Please choose a time";
        }
        else{
           result = "Please fill all information";
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// string type priority to int type priority

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

//--------------------------------------------------------------------------------------------------
// int type priority to string type priority

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
//--------------------------------------------------------------------------------------------------
}
