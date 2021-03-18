package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

public interface IDashboardLogic {

    public ITaskObject[] getData();

    public String getPriorityText (ITaskObject task);

}