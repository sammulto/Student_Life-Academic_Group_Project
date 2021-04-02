/*
 *  This is a fake database for iteration 1
 */

package com.groupeleven.studentlife.faskDB;


import com.groupeleven.studentlife.data.IDatabase;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;

import java.util.ArrayList;

public class FakeDB implements IDatabase {

    static ArrayList<ITaskObject> database = new ArrayList<>();

    public FakeDB() { }

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


    // this method is here to make the interface happy
    public ITaskObject[] getTasks(String startTime, String endTime) {

        ITaskObject[] result = null;
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

    public ITaskObject[] getTasksUncompleted(){
        ITaskObject[] result = null;
        ArrayList<ITaskObject> temp = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            if (!database.get(i).isCompleted()) {
                temp.add(database.get(i));
            }
        }
        if (temp.size() > 0) {
            result = temp.toArray(new ITaskObject[temp.size()]);
        }
        return result;
    }

    public ITaskObject[] getTasksCompleted(){
        ITaskObject[] result = null;
        ArrayList<ITaskObject> temp = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).isCompleted()) {
                temp.add(database.get(i));
            }
        }
        if (temp.size() > 0) {
            result = temp.toArray(new ITaskObject[temp.size()]);
        }
        return result;
    }

    public ITaskObject[] getTask(int tid){
        ITaskObject[] result = null;
        ArrayList<ITaskObject> temp = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getTid() == tid) {
                temp.add(database.get(i));
            }
        }
        if (temp.size() > 0) {
            result = temp.toArray(new ITaskObject[temp.size()]);
        }
        return result;
    }


    public boolean deleteAllTask() {

        boolean success = false;
        database.clear();

        if (database.size() == 0)
            success = true;
        return success;
    }

    public ITaskObject[] getTasks(String date){
        ITaskObject[] result = null;
        ArrayList<ITaskObject> temp = new ArrayList<>();

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getStartTime().startsWith(date)) {
                temp.add(database.get(i));
            }
        }
        if (temp.size() > 0) {
            result = temp.toArray(new ITaskObject[temp.size()]);
        }
        return result;
    }
}
