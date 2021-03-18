# Paying Off Technical Debt
## Debt 1: Taking the Shortcut
In iteration 1, we uses number to represent a task' priority level and was doing priority check inside the UI layers. These speed up the development process but also violate some SOLID principles. We have to rewrite the Task Object to use the enum type as its' priority variable type. moreover, we have to rewrite the methods in UI layer and logic layer to deal with the new priority variable type.

This is a reckless and deliberate technical debt. We did this on purpose to speed up our development process due to the limited time we had.

[Added enum Type](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/a0b2ea12c5757de9ad20e28afb1f5a1d0dd21e22/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/ITaskObject.java#L4)    
[Changed Method to Convert Priority to String](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/172a43a50f4e95c6f9d5c42f1cb593dbb804f737/app/src/main/java/com/groupeleven/studentlife/logic/TodolistLogic.java#L112)    
[Changed Task's Constructors - L42](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/b1f602f980b5abf9819687dd3288ffc4035a4911/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/Task.java#L42)    
[Changed Task's Constructors - L56](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/b1f602f980b5abf9819687dd3288ffc4035a4911/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/Task.java#L56)   
[Changed Task's Constructors - L69](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/b1f602f980b5abf9819687dd3288ffc4035a4911/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/Task.java#L69)   
[Changed Task's Constructors - L80](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/b1f602f980b5abf9819687dd3288ffc4035a4911/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/Task.java#L80)   
[Changed Task's Constructors - L85](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/b1f602f980b5abf9819687dd3288ffc4035a4911/app/src/main/java/com/groupeleven/studentlife/domainSpecificObjects/Task.java#L85)   



## Debt 2: Not Implementing Interface
We were only using interface for the Database in iteration 1. When iteration 2 comes, we realized that we need interface for almost every logic units and D.O.Ses so that the functions for these components are guaranteed. Without implementing interfaces, we have to rewrite our codes while we swapping one module to the other module (eg. swapping LogicUnitA to LogicUnitB). During the process of implementing interfaces, we have to go thought almost every file to change the variable type, which is painful.  

This is a reckless and inadvertent technical debt. We did not think of module swapping and we was rushing while doing iteration 1. Therefore, this is a reckless and deliberate technical debt.  

Specific line numbers are not given since the change is almost everywhere. Any where that is `ITaskObject` was `Task`.    

[Change Variable Type](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/119c6cc7054c8f307fcdb8397a0d4ecf8cfd19ba#5f0b738fa288df1e816a4210dec216b750bf9a62_71_68)    
[Added Dashboard Logic Interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/db63b5c7663bd99a8837822012390162cc17d9a6)    
[Added TodolistLogic Interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/266be02b3fdf1a46a7f121f10d06679aa8e675cd)    



# SOLID



#  Retrospective

We changed by starting our iteration by having interfaces that need to be implemented earlier, and had set up deadlines to start making merge requests after implementing them. This allowed us to work in the same time frame.

links for evidence:
[link object interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/b378ea4130969b66ca840bd0eb0c9b0cd28e1414)    
[task object interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/7f32f80659f1e34a07713d78759990a81c664660)     
[calendar interfaec object](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/0a4a8df196207161ffe2d45c9686aa38d3b5e3e4)     
[dashboard logic interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/db63b5c7663bd99a8837822012390162cc17d9a6)    
[time estimator interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/0e41f1d1a8e39e3e8938d6fff3cf1b1e128e8018)      
[to do interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/266be02b3fdf1a46a7f121f10d06679aa8e675cd)     
[useful link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/b378ea4130969b66ca840bd0eb0c9b0cd28e1414)    


# Design Patterns

We used the singleton design pattern for our database. This means that only one connection to the database can be active at a time which will help solve concurrency issues if they occur in the future. 

[Link to design pattern](https://refactoring.guru/design-patterns/singleton)

[Link to design pattern in our code](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/master/app/src/main/java/com/groupeleven/studentlife/data/DB.java#L25)





# Iteration 1 Feedback fixes

### [Issue #37 - Logic In the UI](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/37)

###Issue
The UI source file (Toedit.java) had a section of code that checks the date and convers the date format. This violates the 3-tier architecture, the UI layer was doing logical tasks.

### The Solution
The responsibility of checking and converting date format is transferred to the logic layer (TodolistLogic.java). A new method called `covertDateToString` is created to perform the task. The UI layer calls this method to do the date conversion so the UI layer is not doing the logical tasks anymore.

### Link to the Commits
[Solved Issue #37](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/d89fd50c88f54d1410446a951f787a4f34aa76e0)
