package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarLogic implements ICalendarLogic {

    private IDatabase database;
    private ITimeEstimator timeEstimator;
    private boolean startFirst;

    private String clSelectedDate;

    public CalendarLogic() {
        this.database = DB.getDB();
        startFirst = false;
    }

    public CalendarLogic(IDatabase database) {
        this.database = database;
        startFirst = false;
    }


    @Override
    public ITaskObject[] viewTask(String date) throws RuntimeException {

        clSelectedDate = date;
        ITaskObject[] taskList = null;

        try {
            taskList = database.getTasks(date);
        } catch (Exception e) {
            taskList = new ITaskObject[0];
        }
        return taskList;
    }


    public String getTaskPriorityText(ITaskObject task) {

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0, 1) + rawText.substring(1).toLowerCase();
        return priorityText;
    }


    public int getTimeEstimate(int id, ITaskObject[] taskObjects) {

        ITaskObject whichITaskObject = taskObjects[id];
        timeEstimator = new TimeEstimator(4, 40);
        return timeEstimator.getTimeEstimate(whichITaskObject);
    }


    public ArrayList getDayList() {

        ArrayList<CalendarDay> dayList = new ArrayList();
        ITaskObject taskList[] = database.getTasks();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat taskFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < taskList.length; i++) {
            String taskDateString = taskList[i].getEndTime();
            Date taskDate;
            int year, month, day;
            try {
                taskDate = taskFormat.parse(taskDateString);
                calendar.setTime(taskDate);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                dayList.add(CalendarDay.from(year, month, day));
            } catch (Exception e) {
                System.err.println(e.getStackTrace());
            }
        }
        return dayList;
    }


    //--------------------------------------------------------------------------------------------------
// add a task
    @Override
    public boolean addTask(String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit) {

        boolean result = false;

        // check the input
        if (validTaskInput(name, priorityText, startTime, endTime, type, quantity, unit)) {

            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());

            ITaskObject newTask = new Task(name, priority, startTime, endTime, 0, type, quantity, unit);
            result = database.insertTask(newTask);

        }
        return result;
    }


// validate the user input

    private boolean validTaskInput(String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit) {

        boolean notEmptyName = !name.equals("");
        boolean validPriority = !priorityText.equals("Choose priority");
        boolean notEmptyStart = !startTime.equals(":00");
        boolean notEmptyEnd = !endTime.equals(":00");
        boolean validType = !type.equals("Choose task type");
        boolean notEmptyQ = quantity > 0;
        boolean notEmptyUnit = !unit.equals("");

        boolean result = false;

        try {
            startFirst = dateCheck(startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (notEmptyName && validPriority && notEmptyStart && notEmptyEnd && validType && notEmptyQ && notEmptyUnit && startFirst) {
            result = true;
        }
        return result;
    }

    public boolean dateCheck(String start, String end) throws ParseException {
        boolean result = false;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sDate = format.parse(start);
        Date eDate = format.parse(end);
        if (sDate.after(eDate)) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public void checkUserInput(int taskNameLength, String taskPriority, int startLength, int endLength, String type, int workNum, String workUnit) throws IllegalArgumentException {

        Boolean validPriority = !taskPriority.equals("Choose priority");
        boolean validType = !type.equals("Choose task type");
        boolean notEmptyUnit = !workUnit.equals("");


        // check error one by one text box
        if (taskNameLength != 0 && endLength != 0 && startLength != 0 && !validPriority && validType && workNum != 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a priority");
        } else if (taskNameLength == 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please input a task name");
        } else if (taskNameLength != 0 && endLength != 0 && startLength == 0 && validPriority && validType && workNum != 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a start time");
        } else if (taskNameLength != 0 && endLength == 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a end time");
        } else if (taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && !validType && workNum != 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a task type");
        } else if (taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum == 0 && notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a quantity");
        } else if (taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && !notEmptyUnit) {
            throw new IllegalArgumentException("Please choose a unit");
        } else if (taskNameLength != 0 && endLength != 0 && startLength != 0 && validPriority && validType && workNum != 0 && notEmptyUnit && !startFirst) {
            throw new IllegalArgumentException("End time must after start time");
        } else {
            throw new IllegalArgumentException("Please fill all information");
        }
    }


    @Override
    public boolean editTask(String selectedDate,int id, String name, String priorityText, String startTime, String endTime, String type, int quantity, String unit) {

        boolean result = false;

        // check the input
        if (validTaskInput(name, priorityText, startTime, endTime, type, quantity, unit)) {

            ITaskObject.Priority priority = ITaskObject.Priority.valueOf(priorityText.toUpperCase());


            ITaskObject taskToEdit=this.viewTask(selectedDate)[id];

            taskToEdit.setTaskName(name);
            taskToEdit.setPriority(priority);
            taskToEdit.setStartTime(startTime);
            taskToEdit.setEndTime(endTime);
            taskToEdit.setType(type);
            taskToEdit.setQuantityUnit(unit);
            taskToEdit.setQuantity(quantity);
            result = database.updateTask(taskToEdit, id);
        }
        return result;
    }

    public boolean deleteTask(String date, int id) {

        ITaskObject temp = this.viewTask(date)[id];

        return database.deleteTask(temp);
    }
    public boolean setCompleted(String date, int id, boolean status) {

        ITaskObject temp = this.viewTask(date)[id];
        temp.setCompleted(status);
        return database.updateTask(temp,temp.getTid());
    }
}
