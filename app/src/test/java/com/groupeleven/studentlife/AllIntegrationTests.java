package com.groupeleven.studentlife;

import com.groupeleven.studentlife.IntegrationTests.CalendarAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.DashboardLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndDBTests;
import com.groupeleven.studentlife.IntegrationTests.TodoLogicAndTimeEstimatorTests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DashboardLogicAndDBTests.class,
        TodoLogicAndDBTests.class,
        TodoLogicAndTimeEstimatorTests.class,
        CalendarAndDBTests.class})

public class AllIntegrationTests { }
