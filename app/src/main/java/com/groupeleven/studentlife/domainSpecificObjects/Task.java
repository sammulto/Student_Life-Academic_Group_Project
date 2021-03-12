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
    private String priority;

    //Must be in the format 'year-month-day hour:minute:second'
    //Note must use the following number of symbols YYYY-MM-DD HH:MI:SS
    private String startTime;
    private String endTime;

    private int status;
    private String type;


    private int quantity;
    private String quantityUnit;

    private boolean completed;



    public Task(String taskName){
        this.taskName = taskName;

    }

    //Should only be used by DB class
    public Task(int tid, String taskName){
        this.tid = tid;
        this.taskName = taskName;
    }

    //Should only be used by DB class
    public Task(int tid, String taskName, String priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit, boolean completed){
        this.tid = tid;
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.completed = completed;
    }

    public Task(String taskName, String priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit, boolean completed){
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.completed = completed;
    }

    public Task(String taskName, String priority, String startTime, String endTime, int status, String type, boolean completed){
        this.taskName = taskName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.type = type;
        this.completed = completed;
    }

    public Task(String taskName, String priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        this(taskName, priority, startTime, endTime, status, type, quantity, quantityUnit, false);
    }

    public Task(String taskName, String priority, String startTime, String endTime, int status, String type){
        this(taskName, priority, startTime, endTime, status, type, false);
    }

    //For legacy use only
    public Task(String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit){
        this(taskName, convertPriority(priority), startTime, endTime, status, type, quantity, quantityUnit);
    }

    //For legacy use only
    public Task(String taskName, int priority, String startTime, String endTime, int status, String type){
        this(taskName, convertPriority(priority), startTime, endTime, status, type);
    }

    //For legacy use only
    public Task(int tid, String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit, boolean completed){
        this(tid, taskName, convertPriority(priority), startTime, endTime, status, type, quantity, quantityUnit, completed);
    }

    public Task(String taskName, int priority, String startTime, String endTime, int status, String type, int quantity, String quantityUnit, boolean completed){
        this(taskName, convertPriority(priority), startTime, endTime, status, type, quantity, quantityUnit, completed);
    }

    public Task(String taskName, int priority, String startTime, String endTime, int status, String type, boolean completed){
        this(taskName, convertPriority(priority), startTime, endTime, status, type, completed);
    }



    private static String convertPriority(int priority){
        String priorityS = null;
        if (priority==1) {
            priorityS = "H";
        }
        else if(priority==2){
            priorityS = "M";
        }
        else if(priority==3){
            priorityS = "L";
        }

        return priorityS;

    }

    private static int convertPriorityToInt(String priority){
        int result = 0;
        if (priority.equals("H")) {
            result = 1;
        }
        else if(priority.equals("M")){
            result = 2;
        }
        else if(priority.equals("L")){
            result = 3;
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return tid == task.tid &&
                status == task.status &&
                quantity == task.quantity &&
                completed == task.completed &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime) &&
                Objects.equals(type, task.type) &&
                Objects.equals(quantityUnit, task.quantityUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, taskName, priority, startTime, endTime, status, type, quantity, quantityUnit, completed);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    //For legacy use only
    public int getPriorityInt() {
        return convertPriorityToInt(priority);
    }

    //For legacy use only
    public void setPriorityInt(int priority) {
        this.priority = convertPriority(priority);
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
