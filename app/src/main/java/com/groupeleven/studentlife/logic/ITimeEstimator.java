package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

public interface ITimeEstimator {
    public int getTimeEstimate(ITaskObject t);
}
