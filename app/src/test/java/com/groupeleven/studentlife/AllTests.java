package com.groupeleven.studentlife;

import com.groupeleven.studentlife.IntegrationTests.DashboardLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndTimeEstimatorTests;
import com.groupeleven.studentlife.databaseTests.DBUnitTests;
import com.groupeleven.studentlife.databaseTests.FakeDBUnitTests;
import com.groupeleven.studentlife.logicTests.DashboardLogicUnitTest;
import com.groupeleven.studentlife.logicTests.TimeEstimatorUnitTests;
import com.groupeleven.studentlife.logicTests.TodoLogicLayerUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DashboardLogicUnitTest.class, TodoLogicLayerUnitTests.class, FakeDBUnitTests.class, DBUnitTests.class, TimeEstimatorUnitTests.class, DashboardLogicAndDBTests.class, TodoLogicAndDBTests.class, TodoLogicAndTimeEstimatorTests.class})

public class AllTests { }
