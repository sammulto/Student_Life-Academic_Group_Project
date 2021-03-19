package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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


    public String getTaskPriorityText (ITaskObject task){

        String rawText = task.getPriority().name();
        String priorityText = rawText.substring(0,1) + rawText.substring(1).toLowerCase();
        return priorityText;
    }


    public int getTimeEstimate(int id, ITaskObject [] taskObjects){

        ITaskObject whichITaskObject = taskObjects[id];
        timeEstimator = new TimeEstimator(4,40);
        return timeEstimator.getTimeEstimate(whichITaskObject);
    }


    public ArrayList getDayList() {

        ArrayList<CalendarDay> dayList = new ArrayList();
        ITaskObject taskList[] = database.getTasks();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat taskFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < taskList.length; i ++){
            String taskDateString = taskList[i].getEndTime();
            Date taskDate;
            int year, month, day;
            try {
                taskDate = taskFormat.parse(taskDateString);
                calendar.setTime(taskDate);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH)+1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                dayList.add(CalendarDay.from(year,month,day));
            }catch (Exception e){
                System.err.println(e.getStackTrace());
            }
        }
        return dayList;
    }
}
