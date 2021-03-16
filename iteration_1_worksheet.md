# Adding a Feature

Feature: To-do list

This feature was developed over the course of the entire time we spent on this iteration. We started by creating a database object and a task domain specific object to pass around the layers. Then we wrote the code for the UI layers and the logic layer. Both of these steps were created in their own branches called DatabaseSystem and Todo\_list\_UI respectively. Once these were implemented we substituted the real database for a fake one. Afterwards we wrote some additional unit tests to iron out any remaining bugs. Each of these branches were merged to master as they were being completed via merge requests and another group member approving the merge request. Once all our tests passed and everything was merged to the master branch, we considered the feature complete.

Links:

- [Merge Request #1](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/merge_requests/3)
- [Merge Request #2](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/merge_requests/19)
- [Merge Request #3](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/merge_requests/20)
- [Merge Request #4](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/merge_requests/22)
- [Merge Request #5](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/merge_requests/29)
- [Feature](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/5)
- [User Story #1](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/20)
- [User Story #2](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/4)
- [User Story #3](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/2)
- [Tests](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/blob/master/app/src/test/java/com/groupeleven/studentlife/logicTests/TodoLogicLayerUnitTests.java)
- Merge Commits can be found on the associated merge request pages


# Exceptional code
[Exception Code Test File](app/src/test/java/com/groupeleven/studentlife/databaseTests/FakeDBUnitTests.java)

FakeDBUnitTests.java : public void exceptionTest();

If the database is empty, it will throw an exception when the getData() is called. This will ensure the callers are aware of the empty database and deal with the empty database explicitly. The callers for the FakeDatabse.getData() will be the logic units, we want to ensure the logic units handle the empty database situation safely.



# Branching Strategy

This repository deploys Github Flow branching strategy.

Two Branches will be kept throughout the project:
* master: the main branch, protected from pushing
* backup: an extra protection for the main branch, protected from pushing

The master branch will be merged to the backup branch from time to time.

## Commit
* Before committing, developers should pull from the master branch to make sure their local repository is up to date.
* Developers needs to commit to a new branch (or his/her current working branch if a new branch is created before).

## Push and Merge
* Developers can only push to their current working branch.
* After the work is pushed to the current working branch, developers can create a merge request.
* Upon a merge request is created, group members will review the codes and approve the merge request.
* After merging, the current working branch can be kept for further commitments or can be deleted.

## Example Of Adding Feature
![Adding Feature](Adding_feature.jpg)


# SOLID
[link to group12's issue](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/23)

#  Agile Planning
Time estimator is one of our most important features and we tried to implement it first. However, as we progressed we found out that there were other features that were higher priority, so we decided push the time estimator to iteration 2. We didn't change the description
of any features or user stories.
[link to time estimator](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/1)
[link to user story](https://code.cs.umanitoba.ca/3350-winter-2021-a01/your-internet-neighbours-group-11/-/issues/3)
