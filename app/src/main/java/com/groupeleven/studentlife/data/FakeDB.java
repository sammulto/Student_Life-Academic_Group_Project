/*
 *  This is a fake database for iteration 1
 */

package com.groupeleven.studentlife.data;


import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.ArrayList;

public class FakeDB implements IDatabase {

    static ArrayList<ITaskObject> database = new ArrayList<>();

    public FakeDB() {
    }

    public boolean insertTask(ITaskObject t) {
        return database.add(t);
    }

    @Override
    public ITaskObject[] getTasks() throws RuntimeException {

        if (database.size() == 0)
            throw new RuntimeException("Database is empty");

        ITaskObject[] list = database.toArray(new ITaskObject[database.size()]);
        return list;
    }

    @Override
    public boolean updateTask(ITaskObject t, int position) {
        boolean result = false;
        if (position != -1) {
            database.set(position, t);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteTask(ITaskObject t) {
        return database.remove(t);
    }

    @Override
    public int getSize() {
        return database.size();
    }

    private int find(int tid) {
        ITaskObject t;
        int index = -1;
        for (int i = 0; i < database.size(); i++) {
            t = database.get(i);
            if (t.getTid() == tid) {
                index = i;
                break;
            }
        }
        return index;
    }

    // this method is for testing purpose only
    public void clearDatabase() {
        database.clear();
    }

    // this method is here to make the interface happy
    // TO-DO
    public ITaskObject[] getTasks(String startTime, String endTime) {

        ITaskObject[] result = null;
        int count = 0;
        ArrayList<ITaskObject> temp = new ArrayList<>();

        for (int i = 0; i < database.size(); i++) {

            if (database.get(i).getStartTime().startsWith(startTime)) {

                temp.add(database.get(i));
            }

        }
        if (temp.size() > 0) {

            result = temp.toArray(new ITaskObject[temp.size()]);
        }
        return result;
    }


}
