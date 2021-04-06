package com.groupeleven.studentlife.acceptanceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DashboardAcceptanceTests.class,
        TodoListAcceptanceTests.class,
        UsefullinkAcceptanceTests.class,
        CalendarAcceptanceTests.class})

public class AllAcceptanceTests { }
