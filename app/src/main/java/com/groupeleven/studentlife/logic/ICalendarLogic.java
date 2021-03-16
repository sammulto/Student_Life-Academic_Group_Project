package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface ICalendarLogic {

    public Task[] viewTask(String date) throws RuntimeException;

    //Tap on date to view Task

    public boolean editTask(int id, String name, String priorityText, String endTime); //Tap on edit to editTask (time/date/description)

    public boolean deleteTask(int id); //delete a task in the calendar

//    public void insertTask(); //insert a new task in the calendar (?)


}