package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class CalendarLogic implements ICalendarLogic {

    private IDatabase database;
    private ITimeEstimator timeEstimator;


    public CalendarLogic(){
        this.database = DB.getDB();
    }

    public CalendarLogic(IDatabase database){
        this.database = database;
    }

    @Override
    public ITaskObject[] viewTask(String date) throws RuntimeException {
        ITaskObject[] taskList = null;

        try {
            taskList = database.getTasks(date);
        } catch (Exception e) {
            taskList = new ITaskObject[0];
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
        ITaskObject whichITaskObject = database.getTasks()[id];

        result = database.deleteTask(whichITaskObject);

        return result;

    }

    public int getTimeEstimate(int id){
        ITaskObject whichITaskObject = database.getTasks()[id];
        timeEstimator = new TimeEstimator(4,40);
        return timeEstimator.getTimeEstimate(whichITaskObject);
    }
    public String getTaskPriorityText (ITaskObject task){

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0,1) + rawText.substring(1).toLowerCase();
        return priorityText;
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

    public boolean setCompleted(int id, boolean status){
        ITaskObject whichTask = database.getTasks()[id];
        whichTask.setCompleted(status);
        return database.updateTask(whichTask,id);
    }
    public int getTimeEstimate(int id, ITaskObject [] taskObjects){
        ITaskObject whichITaskObject = taskObjects[id];
        timeEstimator = new TimeEstimator(4,40);
        return timeEstimator.getTimeEstimate(whichITaskObject);
    }
}
