package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
public class TodolistLogic implements ITodolistLogic {

    private IDatabase database;

    public TodolistLogic(){this.database = new FakeDB(); }

//--------------------------------------------------------------------------------------------------
// get task list form database

    @Override
    public Task[] getData() throws RuntimeException{
        //fetch data from the database
        Task[] list = null;

        try {
            list = database.getTasks();
        }catch(Exception exception){
            list = new Task[0];
        }

        return list;
    }

//--------------------------------------------------------------------------------------------------
// add a task
    @Override
    public boolean addTask(String name, String priorityText, String endTime){

        Boolean result = false;
        if(validTaskInput(name, priorityText, endTime)) {
            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());
            Task newTask = new Task(name, priority, "2020-01-01 12:12:12",endTime, 0, "test Type");
            result = database.insertTask(newTask);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// edit a task
    @Override
    public boolean editTask(int id, String name, String priorityText, String endTime){

        boolean result = false;
        if(validTaskInput(name, priorityText, endTime)) {
            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());
            Task newTask = new Task(name, priority, "2020-01-01 12:12:12",endTime, 0, "test Type");
            result = database.updateTask(newTask,id);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// validate the user input

    private boolean validTaskInput(String name, String priorityText, String endTime){

        boolean notEmptyName = !name.equals("");
        boolean validPriority = !priorityText.equals("Choose priority");
        boolean result = false;
        if(notEmptyName&&validPriority) {
            result = true;
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// delete a task
    @Override
    public boolean deleteTask(int id){
        Task whichTask = database.getTasks()[id];
        return database.deleteTask(whichTask);
    }

//--------------------------------------------------------------------------------------------------
// find which data user no input in adding

    @Override
    public String checkUserInput(String taskName, String taskPriority, String taskDate, String taskTime) throws Exception {

        Boolean validPriority = !taskPriority.equals("Choose priority");

        if(!validPriority&&!taskName.equals("")&&!taskDate.equals("")&&!taskTime.equals("")){
            throw new Exception("Please choose a priority");
        }
        else if (validPriority&&taskName.equals("")&&!taskDate.equals("")&&!taskTime.equals("")){
            throw new Exception("Please input a task name");
        }
        else if(validPriority&&!taskName.equals("")&&taskDate.equals("")&&!taskTime.equals("")){
            throw new Exception("Please choose a date");
        }
        else if(validPriority&&!taskName.equals("")&&!taskDate.equals("")&&taskTime.equals("")){
            throw new Exception("Please choose a time");
        }
        else{
            throw new Exception("Please fill all information");
        }
    }


//--------------------------------------------------------------------------------------------------
// find which data user no input in adding
    @Override
    public String getTaskPriorityText (Task task){

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0,1) + rawText.substring(1).toLowerCase();
        return priorityText;
    }

}
