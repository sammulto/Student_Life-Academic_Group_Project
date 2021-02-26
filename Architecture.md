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
* Unit Tests
  * Database Unit Test `package com.groupeleven.studentlife.databaseTests`



# Directory

The major source code files are under [your-internet-neighbours-group-11/app/src/main/java](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/tree/master/app/src)

Below are the directories for the major source code files :

Java
-------
* Database   `app/src/main/java/com/groupeleven/studentlife/data`
* Domain Specific Object   `app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects`
* Logic Units   `app/src/main/java/com/groupeleven/studentlife/logic`
* UI Units `app/src/main/java/com/groupeleven/studentlife/ui`
* Unit Tests `app/src/test/java/com/groupeleven/studentlife`

XML
-----
* layout, values etc. `app/src/main/res`


## Overall Architecture

Below is a sketch for the overall architecture:


                                       com.groupeleven.studentlife

                                 ../ =  app/src/main/java/com/groupeleven/studentlife/
                                        +-----------------------+
                                        |     Main Activity     |
                                        |                       |
                                        |   MainActivity.java   |
                                        +------------+----------+
                                                     |
                 +-----------------------------------+--------------------------------+
                 |                                   |                                |
../ui/dashboard/ |                  ../ui/todolist/  |                ../ui/calendar/ |
+----------------+-----------------+   +-------------+--------------+    +------------+-------------+
|          Dashboard UI            |   |       To-do List UI        |    |       Calendar UI        |
|                                  |   |                            |    |                          |
|      DashboardFragment.java      |   |   TodolistFragment.java    |    |  CalenderFragment.java   |
|      DashboardViewModel.java     |   |   TodolistViewModel.java   |    |  CalendarViewModel.java  |
|  DashboardElentListAdapter.java  |   +---------------+------------+    +--------------------------+
+----------------+-----------------+                   |
                 |                                     |
      ../logic/  |                          ../logic/  |                  
      +----------+------------+             +----------+---------+
      |    Dashboard Logic    |             |  To-do List Logic  |
      |                       |             |                    +
      |  DashboardLogic.Java  |             |    xxxxxx.Java     |   
      +---------+-------------+             +-----------+--------+
                |                                       |
                +---------------------------------------+
                |
       ../data/ |                ../domainSpecificobjects/
       +--------+-------+         +---------------------+
       |    Database    |         |        Task         |
       |                +---------+                     |
       |    DB.java     |         |      Task.java      |
       +----------------+         +---------------------+
