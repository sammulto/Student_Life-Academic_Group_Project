package com.groupeleven.studentlife.domainSpecificObjects;

public interface ITaskObject {
    public enum Priority{
        HIGH, MEDIUM, LOW;
    }

    //Take an object and match with the existing task and return results
    // true if same task; false if don't
    public boolean equals(Object o);

    public int hashCode();

    //accessor method to get Task Name
    public String getTaskName();

    //mutator method for taskName
    public void setTaskName(String taskName);

    //accessor method to get level of priority
    public Priority getPriority();

    //mutator method to set priority
    public void setPriority(Priority priority);

    //accessor method to get Task start time
    public String getStartTime();

    //mutator method to change start time
    public void setStartTime(String startTime);

    //accessor method to get task end time
    public String getEndTime();

    //mutator method to change end Time
    public void setEndTime(String endTime);

    //accessor method to get Task Status
    public int getStatus();

    //mutator method to change status
    public void setStatus(int status);

    //accessor method to get Task ID
    public int getTid();

    //accessor method to get the type task
    public String getType();

    //mutator method to change type of task
    public void setType(String type);

    //accessor method to get the Quantity
    public int getQuantity();

    //mutator method to change task Quantity
    public void setQuantity(int quantity);

    //accessor method to get the Quantity Unit
    public String getQuantityUnit();

    //mutator method to change task Quantity Unit
    public void setQuantityUnit(String quantityUnit);

}

