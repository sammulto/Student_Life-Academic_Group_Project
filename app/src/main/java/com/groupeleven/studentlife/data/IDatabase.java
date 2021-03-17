package com.groupeleven.studentlife.data;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

public interface IDatabase {
    public boolean insertTask(ITaskObject t);

    public ITaskObject[] getTasks();

    public int getSize();

    public boolean updateTask(ITaskObject t,int position);

    public boolean deleteTask(ITaskObject t);

    public ITaskObject[] getTasks(String startTime, String endTime);
}
