package com.groupeleven.studentlife.data;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface DatabaseInterface {
    public boolean insertTask(Task t);

    public Task[] getTasks();

    public boolean updateTask(Task t);

    public boolean deleteTask(Task t);
}
