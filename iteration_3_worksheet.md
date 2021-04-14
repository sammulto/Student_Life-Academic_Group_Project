
# What technical debt has been cleaned up

# What technical debt did you leave?

# Discuss a Feature or User Story that was cut/re-prioritized

# Acceptance test/end-to-end

One of the acceptance tests is the to-do list acceptance test.  This test tests the interactions between user and the to-do list interface. These interactions include adding task to the list, editing the already existent task, and deleting task from the list. These tests are written base on the user stores. To ensure the test is not flaky, the database will be clean up during each sub-test. Also, the sub-tests are enforced to run in an order that matches the user scenarios.

[Link to the test]( https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/master/app/src/androidTest/java/com/groupeleven/studentlife/acceptanceTests/TodoListAcceptanceTests.java)  
[User stores relate to the test – issue#2]( https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/2)  
[User stores relate to the test– issue#20]( https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/20)  
[User stores relate to the test– issue#19]( https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/19)  

# Acceptance test, untestable  

Writing acceptance test by using Espresso is straight forward but also tedious. Testing a specific RecyclerView entry is challenging. The outdates information provided by the API added another difficulty to target the specific RecyclerView entry. It is worth to mention that finding the resources ID for the UI elements is painful if the ID are not named systematically.  What’s more, the push notification is untestable by Espresso.  

# Velocity/teamwork
