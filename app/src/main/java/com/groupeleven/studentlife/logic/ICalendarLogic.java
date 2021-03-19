package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

import java.util.ArrayList;

public interface ICalendarLogic {

    public ITaskObject[] viewTask(String date) throws RuntimeException;//Tap on date to view Task

    public String getTaskPriorityText(ITaskObject task);

    public int getTimeEstimate(int id, ITaskObject[] taskObjects);

    public ArrayList getDayList();//return a list that containing the due date of tasks

}