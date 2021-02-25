package com.groupeleven.studentlife.ui.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.data.DB;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.ui.dashboard.DashboardTempData;

import java.util.ArrayList;

public class TodolistFragment extends Fragment {

    private TextView welcome;
    private FloatingActionButton fButtonAdd;
    private FloatingActionButton fButtonEdit;
    private FloatingActionButton fButtonDelete;

    private TodolistViewModel todolistViewModel;

    private RecyclerView taskRecycle;
    private TodolistAdapter taskAdapter;
    private Task[] taskList;
    public static DB database = new DB();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todolistViewModel =
                new ViewModelProvider(this).get(TodolistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todolist, container, false);

        fButtonAdd = root.findViewById(R.id.fbutton);

        fButtonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(getActivity(), Toadd.class);
                startActivity(in);
            }
        });

        /*final TextView textView = root.findViewById(R.id.text_todolist);
        todolistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        welcome = root.findViewById(R.id.textTodo);

        // Do not work
        if(database.getTasks().length!=0) {
            welcome.setVisibility(View.INVISIBLE);
        }
        else{
            welcome.setVisibility(View.VISIBLE);
        }

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*
        fButtonEdit = view.findViewById(R.id.editTask);
        fButtonDelete = view.findViewById(R.id.deleteTask);

        fButtonDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        fButtonEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(getActivity(), Toadd.class);
                startActivity(in);
            }
        });
        */

        taskRecycle = view.findViewById(R.id.task_recyclerView);
        //taskList = TodolistTempData.createTempData(1);

        //database.insertTask(new Task("Task" + 1, 0, "2020-01-01 12:12:12", "2020-01-01 12:12:12", 1, "test Type"));

        //taskList= new Task[1];
        //taskList[0] = new Task("Task" + 1, 0, "2020-01-01 12:12:12", "2020-01-01 12:12:12", 1, "test Type");



        taskAdapter = new TodolistAdapter(database.getTasks());
        taskRecycle.setAdapter(taskAdapter);
        taskRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        //View.notifyDataSetChanged();

        //taskAdapter.setTasks(database.getTasks());
    }
}