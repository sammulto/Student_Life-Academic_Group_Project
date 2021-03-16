package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class CalendarLogic implements ICalendarLogic {

    TodolistLogic todolistLogic;

    ITodolistLogic iTodolistLogic;

    @Override
    public Task[] viewTask(String date) throws RuntimeException {
        Task[] taskList = null;
        //  todolistLogic=new ITodolistLogic();
        try {
            taskList = iTodolistLogic.viewTask(date);
        } catch (Exception e) {
        }


        return taskList;
    }

    @Override
    public void editTask(int id, String name, String priorityText, String endTime) {
        todolistLogic = new TodolistLogic();
        todolistLogic.editTask(id, name, priorityText, endTime);
    }

    @Override
    public void deleteTask(int id) {
        todolistLogic = new TodolistLogic();
        todolistLogic.deleteTask(id);

    }


}
