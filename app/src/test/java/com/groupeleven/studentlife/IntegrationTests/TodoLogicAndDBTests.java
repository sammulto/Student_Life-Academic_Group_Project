/*
 *   Integration Tests between TodolistLogic and DB
 */

package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class TodoLogicAndDBTests {

    IDatabase db = new DB();
    ITodolistLogic logic = new TodolistLogic(db);

    @BeforeClass
    //make sure the database is empty before tests
    static public void beforeAllTests(){
        // DB is a singleton
        DB.setDBPath("src/main/assets/db/");
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }


    @After
    //clean up database after each test
    public void afterEachTest(){
        db.deleteAllTask();
    }

    @Test
    public void getDataFromEmptyDBTest(){
        ITaskObject completedList[] = logic.getUncompleted();
        ITaskObject unCompletedList[] = logic.getCompleted();
        assertEquals("The size of the completed list should be zero.", 0, completedList.length);
        assertEquals("The size of the uncompleted list should be zero.", 0, unCompletedList.length);
    }

    @Test
    public void addAndDeleteTest(){

        //add data to DB via logic Unit
        logic.addTask("Task 1","High","2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"Pages");
        logic.addTask("Task 2","Medium","2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"Pages");
        logic.addTask("Task 3","Low","2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"Pages");

        ITaskObject unCompletedList[] = logic.getUncompleted();
        ITaskObject completedList[] = logic.getCompleted();

        assertEquals("The size of the unCompletedList should be 3.", 3, unCompletedList.length);
        assertEquals("The size of the completedList should be 0.", 0, completedList.length);
        assertArrayEquals("The task list should be the same.",unCompletedList,db.getTasks());
        assertEquals("The Task name should be Task 1","Task 1",unCompletedList[0].getTaskName());


        //delete data from DB via Logic Unit
        logic.deleteTask(false,1);
        unCompletedList = logic.getUncompleted();
        assertEquals("The size of the list should be 2.", 2, unCompletedList.length);
        logic.deleteTask(false,0);

        //delete a completed task from DB via Logic Unit
        logic.setCompleted(false,0,true);
        completedList = logic.getCompleted();
        assertEquals("The size of the completedList should be 1.", 1, completedList.length);
        logic.deleteTask(true,0);

        completedList = logic.getCompleted();
        unCompletedList = logic.getUncompleted();
        assertEquals("The size of the completedList should be 0.", 0, completedList.length);
        assertEquals("The size of the unCompletedList should be 0.", 0, unCompletedList.length);
    }

    @Test
    public void editTaskTest(){

        logic.addTask("Task 1", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        logic.addTask("Task 2", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        logic.addTask("Task 3", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        ITaskObject[] listLogic = logic.getUncompleted();

        //Edit Task Name
        logic.editTask(1,"new name", "Low", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        String newTaskName = logic.getUncompleted()[1].getTaskName();
        String newPriority = logic.getUncompleted()[1].getPriority().name();
        assertEquals("The updated Task name should be :","new name", newTaskName);
        assertEquals("The updated priority should be:LOW", "LOW", newPriority);

        logic.editTask(2,"another name", "Medium", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        String newTaskName2 = logic.getUncompleted()[2].getTaskName();
        String newPriority2 = logic.getUncompleted()[2].getPriority().name();
        assertEquals("The updated Task name should be: another name","another name", newTaskName2);
        assertEquals("The updated priority should be:LOW", "MEDIUM", newPriority2);
    }


    @Test
    public void setCompletedTest(){

        logic.addTask("Task 1","High","2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"Pages");

        //set task as completed via Logic Unit
        logic.setCompleted(false,0,true);
        ITaskObject task = logic.getCompleted()[0];
        assertEquals("Task.isComplete should be true:",true, task.isCompleted());

        //set task as uncompleted via Logic Unit
        logic.setCompleted(true,0,false);
        task = logic.getUncompleted()[0];
        assertEquals("Task.isComplete should be false:",false, task.isCompleted());
    }

}
