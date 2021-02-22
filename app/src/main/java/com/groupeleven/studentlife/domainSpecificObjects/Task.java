/*
 * This is a domain specific object that represents a task
 * This object contains all the information found in a single row of the database with attribute
 * names that match the column names of the database
 */

package com.groupeleven.studentlife.domainSpecificObjects;

import java.util.Objects;

public class Task {
    private int tid;
    private String taskName;
    private int priority;

    //Must be in the format 'year-month-day hour:minute:second'
    //Note must use the following number of symbols YYYY-MM-DD HH:MI:SS
    private String startTime;
    private String endTime;

    private int status;
    private String type;


    private int quantity;
    private String quantityUnit;



    public Task(String taskName){
        this.taskName = taskName;

    }

    //Should only be used by DB class
    public Task(int tid, String taskName){
        this.tid = tid;
        this.taskName = taskName;
    }

    //Should only be used by DB class
    public Task(int tid, String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        this.tid = tid;
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public Task(String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public Task(String taskName, int priority, String startTime, String endTime, int status, String type){
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return tid == task.tid &&
                priority == task.priority &&
                status == task.status &&
                quantity == task.quantity &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime) &&
                Objects.equals(type, task.type) &&
                Objects.equals(quantityUnit, task.quantityUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, taskName, priority, startTime, endTime, status, type, quantity, quantityUnit);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTid() {
        return tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

}
