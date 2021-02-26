package com.groupeleven.studentlife.data;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface DatabaseInterface {
    public boolean insertTask(Task t);

    public Task[] getTasks();

    public int getSize();

    //public boolean updateTask(Task t);
    public boolean updateTask(Task t,int position);

    public boolean deleteTask(Task t);
}
