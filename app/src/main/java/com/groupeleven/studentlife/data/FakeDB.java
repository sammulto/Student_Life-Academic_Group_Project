/*
 *  This is a fake database for iteration 1
 */

package com.groupeleven.studentlife.data;


import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.ArrayList;

public class FakeDB implements DatabaseInterface{
    static ArrayList<Task> database = new ArrayList<>();

    public FakeDB(){}

    public boolean insertTask(Task t){ return database.add(t); }

    @Override
    public Task[] getTasks() {
        Task[] list = database.toArray(new Task[database.size()]);
        return list;
    }

    @Override
    public boolean updateTask(Task t) {
        boolean result = false;
        int index = find(t.getTid());
        if(index != -1) {
            database.set(index, t);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteTask(Task t) {
        return database.remove(t);
    }

    private int find(int tid){
        Task t;
        int index = -1;
        for(int i = 0; i < database.size(); i++){
            t = database.get(i);
            if(t.getTid() == tid){
                index = i;
                break;
            }
        }
        return index;
    }


}
