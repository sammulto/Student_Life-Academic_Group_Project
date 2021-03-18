/*
 *   Integration Tests between TodolistLogic and TimeEstimator
 */

package com.groupeleven.studentlife.IntegrationTests;


import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITimeEstimator;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TimeEstimator;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TodoLogicAndTimeEstimatorTests {

    @AfterClass
    //clean up the items in database since the database is a singleton
    static public void beforeAllTests(){
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }

    @Test
    public void estimateTimeTest(){
        ITodolistLogic logic = new TodolistLogic();
        ITimeEstimator estimator = new TimeEstimator(3350, 10);

        //Estimate time via Logic Unit
        logic.addTask("Reading","High","2020-01-01 12:12:12","2220-05-02 01:02:12","Reading",10,"Pages");
        int readingTime = logic.getTimeEstimate(0);

        assertEquals("Retun Est. Time should be 20", 20,readingTime);
    }




}



