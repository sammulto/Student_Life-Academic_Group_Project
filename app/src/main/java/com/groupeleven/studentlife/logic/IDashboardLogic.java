package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface IDashboardLogic {

    public Task[] getData();

    public String getPriorityText (Task task);

}