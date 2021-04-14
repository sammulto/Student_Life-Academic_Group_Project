package com.groupeleven.studentlife;

import com.groupeleven.studentlife.UnitTests.CalendarLogicTest;
import com.groupeleven.studentlife.UnitTests.DashboardLogicUnitTest;
import com.groupeleven.studentlife.UnitTests.TimeEstimatorUnitTests;
import com.groupeleven.studentlife.UnitTests.TodoLogicLayerUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DashboardLogicUnitTest.class,
        TodoLogicLayerUnitTests.class,
        TimeEstimatorUnitTests.class,
        CalendarLogicTest.class,})

public class AllTests { }
