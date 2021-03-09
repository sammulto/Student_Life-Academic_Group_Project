package com.groupeleven.studentlife.logic;

import com.groupeleven.studentlife.domainSpecificObjects.Task;

public interface ITimeEstimator {
    public int getTimeEstimate(Task t);
}
