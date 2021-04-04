package com.groupeleven.studentlife;

import com.groupeleven.studentlife.logicTests.CalendarLogicTest;
import com.groupeleven.studentlife.logicTests.DashboardLogicUnitTest;
import com.groupeleven.studentlife.logicTests.TimeEstimatorUnitTests;
import com.groupeleven.studentlife.logicTests.TodoLogicLayerUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DashboardLogicUnitTest.class,
        TodoLogicLayerUnitTests.class,
        TimeEstimatorUnitTests.class,
        CalendarLogicTest.class,})

public class AllTests { }
