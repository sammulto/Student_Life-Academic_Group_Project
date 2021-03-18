package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodolistLogic implements ITodolistLogic {

    private IDatabase database;
    private ITimeEstimator timeEstimator;
    private boolean startFirst;

    public TodolistLogic(){
        this.database = DB.getDB();
        startFirst = false;
    }

    public TodolistLogic(IDatabase database){
        this.database = database;
        startFirst = false;
    }

//--------------------------------------------------------------------------------------------------
// get task list form database

    @Override
    public ITaskObject[] getData() throws RuntimeException{
        //fetch data from the database
        ITaskObject[] list = null;

        try {
            list = (ITaskObject[]) database.getTasks();
        }catch(Exception exception){
            list = new ITaskObject[0];
        }

        return list;
    }

//--------------------------------------------------------------------------------------------------
// add a task
    @Override
    public boolean addTask(String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit){

        boolean result = false;

        // check the input
        if(validTaskInput(name, priorityText, startTime, endTime, type, quantity, unit)) {

            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());

            ITaskObject newTask = new Task(name, priority, startTime, endTime, 0, type,quantity,unit);

            result = database.insertTask(newTask);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// edit a task
    @Override
    public boolean editTask(int id, String name, String priorityText, String startTime, String endTime,String type, int quantity, String unit){

        boolean result = false;

        // check the input
        if(validTaskInput(name, priorityText, startTime, endTime, type, quantity, unit)) {

            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());
            ITaskObject taskToEdit = this.getData()[id];
            taskToEdit.setTaskName(name);
            taskToEdit.setPriority(priority);
            taskToEdit.setStartTime(startTime);
            taskToEdit.setEndTime(endTime);
            taskToEdit.setType(type);
            taskToEdit.setQuantityUnit(unit);
            taskToEdit.setQuantity(quantity);
            result = database.updateTask(taskToEdit,id);
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// validate the user input

    private boolean validTaskInput(String name, String priorityText, String startTime, String endTime,String type, int quantity, String unit){

        boolean notEmptyName = !name.equals("");
        boolean validPriority = !priorityText.equals("Choose priority");
        boolean notEmptyStart = !startTime.equals(":00");
        boolean notEmptyEnd = !endTime.equals(":00");
        boolean validType = !type.equals("Choose task type");
        boolean notEmptyQ = quantity >0;
        boolean notEmptyUnit = !unit.equals("");

        boolean result = false;

        try {
            startFirst = dateCheck(startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(notEmptyName && validPriority && notEmptyStart && notEmptyEnd && validType && notEmptyQ && notEmptyUnit && startFirst) {
            result = true;
        }
        return result;
    }

//--------------------------------------------------------------------------------------------------
// delete a task
    @Override
    public boolean deleteTask(int id){
        ITaskObject whichITaskObject = database.getTasks()[id];
        return database.deleteTask(whichITaskObject);
    }


//--------------------------------------------------------------------------------------------------
// set the task completed or uncompleted
    public boolean setCompleted(int id, boolean status){
        ITaskObject whichTask = database.getTasks()[id];
        whichTask.setCompleted(status);
        return database.updateTask(whichTask,id);
    }

//--------------------------------------------------------------------------------------------------
// get time estimate result
    public int getTimeEstimate(int id){
        ITaskObject whichITaskObject = database.getTasks()[id];
        timeEstimator = new TimeEstimator(4,40);
        return timeEstimator.getTimeEstimate(whichITaskObject);
    }

//--------------------------------------------------------------------------------------------------
// find which data user no input in adding

    @Override
    public void checkUserInput(int taskNameLength, String taskPriority, int startLength, int endLength, String type, int workNum, String workUnit) throws Exception {

        Boolean validPriority = !taskPriority.equals("Choose priority");
        boolean validType = !type.equals("Choose task type");
        boolean notEmptyUnit = !workUnit.equals("");


        // check error one by one text box
        if ( taskNameLength != 0 && endLength != 0 && startLength != 0 && !validPriority && validType && workNum != 0 && notEmptyUnit){
            throw new Exception("Please choose a priority");
        }
        else if (taskNameLength == 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit){
            throw new Exception("Please input a task name");
        }
        else if(taskNameLength != 0 && endLength != 0 && startLength == 0 && validPriority && validType && workNum != 0 && notEmptyUnit){
            throw new Exception("Please choose a start time");
        }
        else if(taskNameLength != 0 && endLength == 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit){
            throw new Exception("Please choose a end time");
        }
        else if(taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && !validType && workNum != 0 && notEmptyUnit){
            throw new Exception("Please choose a task type");
        }
        else if(taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum == 0 && notEmptyUnit){
            throw new Exception("Please choose a quantity");
        }
        else if(taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && !notEmptyUnit){
            throw new Exception("Please choose a unit");
        }
        else if(taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit && !startFirst){
            throw new Exception("End time must after start time");
        }
        else {
            throw new Exception("Please fill all information");
        }
    }


//--------------------------------------------------------------------------------------------------
// find which data user no input in adding
    @Override
    public String getTaskPriorityText (ITaskObject task){

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0,1) + rawText.substring(1).toLowerCase();
        return priorityText;
    }


//--------------------------------------------------------------------------------------------------
// Covert int date to String in the a DB accepted format
    @Override
    public String covertDateToString(int year, int month, int day){

        String tempMon=""+month;
        String tempDay=""+day;
        month = month + 1;
        if(day<10){
            tempDay = "0"+day;
        }
        if(month<10){
            tempMon = "0"+month;
        }
        String date = year+"-"+tempMon+"-"+tempDay;
        return date;
    }

//--------------------------------------------------------------------------------------------------
// Given two date in String
// Compare them, if the start time is after end end time return false
    public boolean dateCheck(String start, String end) throws ParseException {
        boolean result = false;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date sDate = format.parse(start);
        Date eDate = format.parse(end);
        if(sDate.after(eDate)){
            result = false;
        }
        else{
            result = true;
        }
        return result;
    }

}