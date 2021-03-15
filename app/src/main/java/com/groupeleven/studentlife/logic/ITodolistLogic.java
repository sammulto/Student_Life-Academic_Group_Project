package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface ITodolistLogic {

    public Task[] getData() throws RuntimeException;

    //--------------------------------------------------------------------------------------------------
    // add a task
    public boolean addTask(String name, String priorityText, String endTime);

    //--------------------------------------------------------------------------------------------------
    // edit a task
    public boolean editTask(int id, String name, String priorityText, String endTime);

    //--------------------------------------------------------------------------------------------------
    // delete a task
    public boolean deleteTask(int id);

    public String getTaskPriorityText(Task task);

    public String whichDataNotfill(String taskName, String taskPriority, String taskDate, String taskTime);
}
