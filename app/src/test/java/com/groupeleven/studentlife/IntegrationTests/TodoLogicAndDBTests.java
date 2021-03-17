package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TodoLogicAndDBTests {

    @Test
    public void updateTaskNameCase(){

        TodolistLogic logic = new TodolistLogic();
        logic.addTask("old Name", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");
        ITaskObject[] listLogic = logic.getData();

        DB db = new DB();
        ITaskObject[] listDB = db.getTasks();

        assertArrayEquals("The two task list should be identical", listDB, listLogic);

        int id = listLogic[0].getTid();

        logic.editTask(0,"new name", "High", "2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"pages");

        String newTaskName = db.getTasks()[0].getTaskName();
        assertEquals("The return task should have a task name :new name", newTaskName, "new name");

        String newTaskName2 = logic.getData()[0].getTaskName();
        assertEquals("The return task should have a task name :new name", newTaskName2, "new name");
    }

}
