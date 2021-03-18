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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;


public class TodolistFragment extends Fragment {

    private TextView welcome;
    private FloatingActionButton fButtonAdd;
    private FloatingActionButton fButtonEdit;
    private FloatingActionButton fButtonDelete;

    private TodolistViewModel todolistViewModel;
    private ITodolistLogic logic;
    private RecyclerView taskRecycle;
    private TodolistAdapter taskAdapter;
    private ITaskObject[] taskList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todolistViewModel =
                new ViewModelProvider(this).get(TodolistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todolist, container, false);


//--------------------------------------------------------------------------------------------------
// "add" button action, jump to add activity
        fButtonAdd = root.findViewById(R.id.fbutton);

        fButtonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(getActivity(), Toadd.class);
                startActivity(in);
            }
        });
//--------------------------------------------------------------------------------------------------
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logic = new TodolistLogic();
        taskList= logic.getData();
        taskRecycle = view.findViewById(R.id.task_recyclerView);

//--------------------------------------------------------------------------------------------------
//  RecyclerView stuffs
        taskAdapter = new TodolistAdapter(taskList);
        taskRecycle.setAdapter(taskAdapter);
        taskRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
//--------------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------------
// show a welcome text when no task was added
        welcome = view.findViewById(R.id.textTodo);

        if(taskList.length!=0) {
            welcome.setVisibility(View.INVISIBLE);
        }
        else{
            welcome.setVisibility(View.VISIBLE);
        }
//--------------------------------------------------------------------------------------------------
    }

    //upon resume, update the list data
    @Override
    public void onResume() {

        //update task list
        taskList = logic.getData();

        //set welcome message visibility
        if(taskList.length!=0)
            welcome.setVisibility(View.INVISIBLE);

        //set up a new adapter
        taskAdapter = new TodolistAdapter(taskList);
        taskRecycle.setAdapter(taskAdapter);
        taskRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onResume();

    }

}