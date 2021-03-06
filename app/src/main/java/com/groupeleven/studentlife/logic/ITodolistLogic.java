package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.text.ParseException;

public interface ITodolistLogic {

    public ITaskObject[] getCompleted() throws RuntimeException;
    public ITaskObject[] getUncompleted() throws RuntimeException;
    //--------------------------------------------------------------------------------------------------
    // add a task
    public boolean addTask(String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit);

    //--------------------------------------------------------------------------------------------------
    // edit a task
    public boolean editTask(int id, String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit);

    //--------------------------------------------------------------------------------------------------
    // delete a task
    public boolean deleteTask(boolean completed, int id);

    //--------------------------------------------------------------------------------------------------
    // get the priority of a task
    public String getTaskPriorityText(ITaskObject task);

    //--------------------------------------------------------------------------------------------------
    // check the user input to see is it validate
    public void checkUserInput(int taskNameLength, String taskPriority, int startLength, int endLength, String type, int workNum, String workUnit) throws IllegalArgumentException;

    //--------------------------------------------------------------------------------------------------
    // set the task completed or uncompleted
    public boolean setCompleted(boolean completed, int id, boolean status);

    //--------------------------------------------------------------------------------------------------
    // get the time estimate result of a task
    public int getTimeEstimate(int id);

    //--------------------------------------------------------------------------------------------------
    // Compare them, if the start time is after end end time return false
    public boolean dateCheck(String start, String end) throws ParseException;
}
