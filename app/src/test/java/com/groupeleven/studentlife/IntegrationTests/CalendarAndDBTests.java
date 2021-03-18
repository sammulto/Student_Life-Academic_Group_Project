/*
    Integration Tests between CalendarLogic and DB
 */

package com.groupeleven.studentlife.IntegrationTests;

import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CalendarAndDBTests {

    IDatabase db = new DB();
    ICalendarLogic logic = new CalendarLogic(db);

    @BeforeClass
    //make sure the database is empty before tests
    static public void beforeAllTests(){
        // DB is a singleton
        IDatabase classDB = new DB();
        classDB.deleteAllTask();
    }

    @After
    //clean up database after each test
    public void afterEachTest(){
        db.deleteAllTask();
    }

   // @Test

    //public void




}
