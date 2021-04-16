Student Life Project Architecture


# Package
The packages for the major source code files are:

* Database   `package com.groupeleven.studentlife.data`
* Domain Specific Object   `package com.groupeleven.studentlife.domainSpecificObjects`
* Logic Units   `package com.groupeleven.studentlife.logic`
* UI Units
  * Dashboard    `package com.groupeleven.studentlife.ui.dashboard`
  * Calendar `package com.groupeleven.studentlife.ui.calendar`
  * To-do List  `package com.groupeleven.studentlife.ui.todolist`
  * Useful Links  `package com.groupeleven.studentlife.ui.usefullinks`
  * Notification  `package com.groupeleven.studentlife.ui.notification`
* Unit Tests
  * Unit Tests `package com.groupeleven.studentlife.UnitTests`
  * Database Tests `package com.groupeleven.studentlife.databaseTests`
* IntegrationTests `package com.groupeleven.studentlife.IntegrationTests`
* Acceptance Tests `com.groupeleven.studentlife.acceptanceTests`



# Directory

The major source code files are under [your-internet-neighbours-group-11/app/src/main/java](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/tree/master/app/src)

Below are the directories for the major source code files :

Java
-------
* Database   `app/src/main/java/com/groupeleven/studentlife/data`
* Domain Specific Object   `app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects`
* Logic Units   `app/src/main/java/com/groupeleven/studentlife/logic`
* UI Units `app/src/main/java/com/groupeleven/studentlife/ui`
* Tests `app/src/test/java/com/groupeleven/studentlife`

XML
-----
* layout, values etc. `app/src/main/res`


## Overall Architecture

Below is a sketch for the overall architecture:

```


com.groupeleven.studentlife

../ =  app/src/main/java/com/groupeleven/studentlife/

+-----------------------+
|     Main Activity     |
|                       |
|   MainActivity.java   |
+--+--------------------+
   |
   |   ../ui/dashboard/
   |   +----------------------------------+   ../logic/
   |   |          Dashboard UI            |   +-----------------------+
   |   |                                  |   |    Dashboard Logic    |
   +---+      DashboardFragment.java      +---+                       +-+
   |   |      DashboardViewModel.java     |   |  DashboardLogic.Java  | |   ../data/
   |   |  DashboardElentListAdapter.java  |   +-----------------------+ |   +----------------+
   |   +----------------------------------+                             |   |    Database    |
   |                                                                    +---+                |
   |    ../ui/todolist/                                                 |   |    DB.java     |
   |   +----------------------------------+                             |   +--+-------------+
   |   |           To+do List UI          |   ../logic/                 |      |
   |   |                                  |   +--------------------+    |      |
   +---+       TodolistFragment.java      |   |  To+do List Logic  |    |      |
   |   |       TodolistViewModel.java     +---+                    +----+      |
   |   |       TodolistAdapter.java       |   | TodolistLogic.Java |    |      |  ../domainSpecificobjects/
   |   |       Toupdate.java              |   +--------------------+    |      |   +---------------------+
   |   |       Viewtask.java              |                             |      |   |        Task         |
   |   |       FinishedAdapter.java       |                             |      +---+                     |
   |   +----------------------------------+                             |      |   |      Task.java      |
   |                                                                    |      |   +---------------------+
   |    ../ui/calendar/                                                 |      |
   |   +----------------------------------+                             |      |  ../domainSpecificobjects/
   |   |           Calendar UI            |   ../logic/                 |      |   +---------------------+
   |   |                                  |   +--------------------+    |      |   |        Link         |
   +---+      CalenderFragment.java       |   |    Calendar Logic  |    |      +---+                     |
   |   |      CalendarViewModel.java      +---+                    +----+          |      Link.java      |
   |   |      CalendarAdapter.java        |   | CalendarLogic.Java |               +---------------------+
   |   |      CalendarToUpdate.java       |   +--------------------+
   |   |      CalendarDelete.java         |
   |   +----------------------------------+
   |
   |    ../ui/usefullinks/
   |   +----------------------------------+
   |   |       Useful Links UI            |
   |   |                                  |
   +---+     UsefullinksFragment.java     |
   |   |     UsefullinksViewModel.java    |
   |   |          Tutorial.java           |
   |   +----------------------------------+
   |
   |    ../ui/notification/
   |   +----------------------------------+
   |   |        Notification UI           |
   |   |                                  |
   +---+      AlarmReceiver.Java          |
       |      PriorityChannel.java        |
       +----------------------------------+




```
