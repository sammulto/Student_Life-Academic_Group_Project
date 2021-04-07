package com.groupeleven.studentlife.ui.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

public class Viewtask extends AppCompatActivity {

    private ITodolistLogic logic;
    private RecyclerView taskRecycle;
    private FinishedAdapter taskAdapter;
    private ITaskObject[] taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtask);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        logic = new TodolistLogic();
        taskList = logic.getCompleted();

        taskRecycle = findViewById(R.id.finished);

//--------------------------------------------------------------------------------------------------
//  RecyclerView stuffs
        taskAdapter = new FinishedAdapter(taskList);
        taskRecycle.setAdapter(taskAdapter);
        taskRecycle.setLayoutManager(new LinearLayoutManager(this));
    }
}