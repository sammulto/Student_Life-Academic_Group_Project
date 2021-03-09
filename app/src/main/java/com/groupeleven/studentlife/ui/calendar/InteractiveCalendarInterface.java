package com.groupeleven.studentlife.data;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface InteractiveCalendarInterface {
    
    public void viewTasks(); //Tap on date to view Task

    public void editTask(); //Tap on edit to editTask (time/date/description)

    public void deleteTask(); //delete a task in the calendar
    
    
    
}