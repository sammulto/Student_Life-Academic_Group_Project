package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface ICalendarLogic {
    
    public void viewTask(); //Tap on date to view Task

    public void editTask(); //Tap on edit to editTask (time/date/description)

    public void deleteTask(); //delete a task in the calendar

    public void insertTask(); //insert a new task in the calendar (?)


}