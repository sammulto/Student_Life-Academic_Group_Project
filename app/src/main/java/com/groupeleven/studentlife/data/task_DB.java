package com.groupeleven.studentlife.data;


import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.ArrayList;

public class task_DB implements I_task_DB{
    ArrayList<Task> database;

    public task_DB(){
        database = new ArrayList<>();
    }

    public void insertTask(int tid, String taskName){
        database.add(new Task(tid, taskName));
    }

    public void insertTask(int tid, String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        database.add(new Task(tid, taskName, priority, startTime, endTime, status, type, quantity, quantityUnit));
    }

    public void insertTask(String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        database.add(new Task(taskName, priority, startTime, endTime, status, type, quantity, quantityUnit));
    }

    public void insertTask(String taskName, int priority, String startTime, String endTime, int status, String type){
        database.add(new Task(taskName, priority, startTime, endTime, status, type));
    }

    @Override
    public Task[] getTasks() {
        Task[] list = database.toArray(new Task[database.size()]);
        return list;
    }

    @Override
    public boolean updateTask(Task t) {
        boolean result = false;
        int index = find(t.getTid());
        if(index != -1) {
            database.set(index, t);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteTask(Task t) {
        return database.remove(t);
    }

    private int find(int tid){
        Task t;
        int index = -1;
        for(int i = 0; i < database.size(); i++){
            t = database.get(i);
            if(t.getTid() == tid){
                index = i;
                break;
            }
        }
        return index;
    }


}
