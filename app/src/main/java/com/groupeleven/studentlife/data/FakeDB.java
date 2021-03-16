/*
 *  This is a fake database for iteration 1
 */

package com.groupeleven.studentlife.data;


import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.ArrayList;

public class FakeDB implements IDatabase {

    static ArrayList<Task> database = new ArrayList<>();

    public FakeDB(){}

    public boolean insertTask(Task t){ return database.add(t); }

    @Override
    public Task[] getTasks() throws RuntimeException{

        if(database.size() == 0)
            throw new RuntimeException("Database is empty");

        Task[] list = database.toArray(new Task[database.size()]);
        return list;
    }

    @Override
    public boolean updateTask(Task t, int position) {
        boolean result = false;
        if(position != -1) {
            database.set(position, t);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteTask(Task t) {
        return database.remove(t);
    }

    @Override
    public int getSize(){
        return database.size();
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

    // this method is for testing purpose only
    public void clearDatabase(){
        database.clear();
    }
    
    // this method is here to make the interface happy
    // TO-DO
    public Task [] getTasks( String startTime, String endTime){
        retur null;
    }


}
