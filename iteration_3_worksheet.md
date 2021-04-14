# What technical debt has been cleaned up
## Deliberate and Prudent Technichal Debt

In iteration 2 we have a feature which is to recommend websites for users and users can also add their favourite website. 

However, we ended up with only recommended websites and no inserting function for users. 

In iteration 3, we decide that recommended websites is enough to support our system and concept, so we removed all function and element related to the customization of websites.

It is deliberate and Prudent because we don't have enough time to build the whole system and what we can only do is to cut it and pick up the most valuable part.

[Useless code removed](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/b0ac02a21af2fa1f19816633c6a3b9a29fe30248)

# What technical debt did you leave?
## Deliberate and Prudent Technical Debt

A new feature we added in iteration 3 is the notifiication, and the notification is supposed to have different priorites.

However, to reduce the time we need to implement and test the function, we make all different priority options in the task have the same result, which is a default priority.

It is deliberate and prudent because the time is limited and we give up some unnecessary functionality to get the stability.

[Delete the parameter for priority](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/commit/42c9ba21ec31eaf96103f8b16e312b7163b49728/Toupdate.java#L195)

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
