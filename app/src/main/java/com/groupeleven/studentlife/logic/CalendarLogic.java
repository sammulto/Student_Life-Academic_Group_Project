package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.FakeDB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.Calendar;

public class CalendarLogic implements ICalendarLogic {

    private IDatabase database;


    public CalendarLogic() {
        database = new FakeDB();

    }

    @Override
    public Task[] viewTask(String date) throws RuntimeException {
        Task[] taskList = null;
        String startTime = date + " 00:00:00";
        String endTime = "11:59:59";

        //  todolistLogic=new ITodolistLogic();
        try {
            taskList = database.getTasks(startTime, endTime);
        } catch (Exception e) {
            taskList = new Task[0];

        }


        return taskList;
    }

    @Override
    public boolean editTask(int id, String name, String priorityText, String endTime) {
        boolean result = false;
        if (validTaskInput(name, priorityText, endTime)) {
            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());
            Task newTask = new Task(name, priority, "2020-01-01 12:12:12", endTime, 0, "test Type");
            result = database.updateTask(newTask, id);
        }

        return result;


    }

    @Override
    public boolean deleteTask(int id) {
        boolean result = false;
        Task whichTask = database.getTasks()[id];

        result = database.deleteTask(whichTask);


        return result;

    }

    private boolean validTaskInput(String name, String priorityText, String endTime) {

        boolean notEmptyName = !name.equals("");
        boolean validPriority = !priorityText.equals("Choose priority");
        boolean result = false;
        if (notEmptyName && validPriority) {
            result = true;
        }
        return result;
    }

}