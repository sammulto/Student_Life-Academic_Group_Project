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
* Unit Tests
  * Logic Unit Tests `package com.groupeleven.studentlife.logicTests`
  * Database Tests `package com.groupeleven.studentlife.databaseTests`



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
   +-----------------------+
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
      |    ../ui/todolist/                       ../logic/                 |   |    DB.java     |
      |   +----------------------------------+   +--------------------+    |   +----------------+
      |   |           To-do List UI          |   |  To-do List Logic  |    |      |
      |   |                                  +---+                    +----+      |
      +---+       TodolistFragment.java      |   | TodolistLogic.Java |    |      |
      |   |       TodolistViewModel.ja^a     |   +--------------------+    |      |
      |   +----------------------------------+                             |      |  ../domainSpecificobjects/
      |                                                                    |      |   +---------------------+
      |    ../ui/calendar/                       ../logic/                 |      |   |        Task         |
      |   +----------------------------------+   +--------------------+    |      +---+                     |
      |   |           Calendar UI            |   |    Calendar Logic  |    |      |   |      Task.java      |
      |   |                                  +---+                    +----+      |   +---------------------+
      +---+      CalenderFragment.java       |   | CalendarLogic.Java |    |      |
      |   |      CalendarViewModel.java      |   +--------------------+    |      |  ../domainSpecificobjects/
      |   +----------------------------------+                             |      |   +---------------------+
      |                                                                    |      |   |        Link         |
      |    ../ui/calendar/                       ../logic/                 |      +---+                     |
      |   +----------------------------------+   +---------------------+   |          |      Link.java      |
      |   |       Useful Links UI            |   | Useful Links Logic  |   |          +---------------------+
      |   |                                  +---+                     +---+
      +---+     UsefullinksFragment.java     |   |UsefulLinksLogic.Java|
          |     UsefullinksViewModel.java    |   +---------------------+
          +----------------------------------+





```
