/*
 *  This class is used to test the dashboard event list
 *
 */


package com.groupeleven.studentlife.ui.dashboard;

import android.annotation.SuppressLint;

import java.util.ArrayList;

public class DashboardTempData {
    
    private String title;
    private String event;
    
    public DashboardTempData(String title,String event){
        this.title = title;
        this.event = event;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getEvent(){
        return event;
    }

    public static ArrayList<DashboardTempData> createTempData(int quantity){
        ArrayList<DashboardTempData> list = new ArrayList<>();
        
        for(int i = 0; i < quantity; i++){
            list.add(new DashboardTempData("Title " + i, "Event " + i));
        }
        return list;
    }
}
