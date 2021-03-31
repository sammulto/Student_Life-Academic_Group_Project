package com.groupeleven.studentlife;

import com.groupeleven.studentlife.IntegrationTests.CalendarAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.DashboardLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndTimeEstimatorTests;
import com.groupeleven.studentlife.databaseTests.DBUnitTests;
import com.groupeleven.studentlife.databaseTests.FakeDBUnitTests;
import com.groupeleven.studentlife.logicTests.CalendarLogicTest;
import com.groupeleven.studentlife.logicTests.DashboardLogicUnitTest;
import com.groupeleven.studentlife.logicTests.TimeEstimatorUnitTests;
import com.groupeleven.studentlife.logicTests.TodoLogicLayerUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DashboardLogicAndDBTests.class,
        TodoLogicAndDBTests.class,
        TodoLogicAndTimeEstimatorTests.class,
        CalendarAndDBTests.class})

public class AllIntegrationTests { }
