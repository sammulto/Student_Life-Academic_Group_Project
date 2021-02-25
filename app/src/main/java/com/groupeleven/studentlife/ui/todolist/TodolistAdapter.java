package com.groupeleven.studentlife.ui.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.ui.dashboard.DashboardEventListAdapter;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.ViewHolder>{
    private Task[] taskList;

    public TodolistAdapter(Task[] taskList){
        this.taskList = taskList;
    }

    @NotNull
    @Override
    public TodolistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.task_layout, parent, false);

        //create and return View Holder
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(TodolistAdapter.ViewHolder holder, int position) {
        //get data according to position
        Task task = taskList[position];
        holder.taskBox.setText(task.getTaskName());

        holder.taskBox.setChecked(intToBoolean(task.getStatus()));

        //holder.taskBox.;

        /*
        int status = task.getStatus();
        SimpleDateFormat orgFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd", Locale.CANADA);
        String statusText;
        String endTime = "Parse Failed";

        //populate information for the views
        TextView taskNameView = holder.taskNameTextView;
        taskNameView.setText(task.getTaskName());

        TextView taskStatusView = holder.taskStatusTextView;
        if(status < 5){
            statusText = "HIGH";
        }else if (status < 10){
            statusText = "MEDIUM";
        }else{
            statusText = "LOW";
        }
        taskStatusView.setText(statusText);

        TextView taskEndTimeView = holder.taskEndTimeTextView;
        try {
            endTime = newFormat.format(orgFormat.parse(task.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(endTime);
        taskEndTimeView.setText(endTime);*/
    }

    private boolean intToBoolean(int status){
        return status!=0;
   }
    @Override
    public int getItemCount() {
        return taskList.length;
    }

    public void setTasks(Task[] taskList){
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox taskBox;

        FloatingActionButton del;
        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            taskBox = itemView.findViewById(R.id.todoCheckBox);

            del = itemView.findViewById(R.id.deleteTask);
        }
    }

}
