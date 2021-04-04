package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

import java.util.ArrayList;

public interface ICalendarLogic {

    public ITaskObject[] viewTask(String date) throws RuntimeException;//Tap on date to view Task

    public String getTaskPriorityText(ITaskObject task);

    public int getTimeEstimate(int id, ITaskObject[] taskObjects);

    public ArrayList getDayList();//return a list that containing the due date of tasks

    public boolean deleteTask(String date,int id);
    public boolean addTask(String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit);
    public boolean editTask(String selectedDate,int id, String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit);

    public void checkUserInput(int taskNameLength, String taskPriority, int startLength, int endLength, String type, int workNum, String workUnit) throws IllegalArgumentException;

    public boolean setCompleted(String date,int id, boolean status);
}