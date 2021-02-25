package com.groupeleven.studentlife.data;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface DatabaseInterface {
    public void insertTask(int tid, String taskName);

    public void insertTask(int tid, String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit);

    public void insertTask(String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit);

    public void insertTask(String taskName, int priority, String startTime, String endTime, int status, String type);

    public Task[] getTasks();

    public boolean updateTask(Task t);

    public boolean deleteTask(Task t);
}
