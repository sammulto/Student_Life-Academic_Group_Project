package com.groupeleven.studentlife.ui.todolist;

import android.content.Context;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import org.jetbrains.annotations.NotNull;

public class FinishedAdapter extends RecyclerView.Adapter<FinishedAdapter.ViewHolder>{

    private ITaskObject[] taskList;
    private ITodolistLogic logic;

    public FinishedAdapter(ITaskObject[] taskList){
        this.taskList = taskList;
        this.logic = new TodolistLogic();
    }

    @NotNull
    @Override
    public FinishedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.finished_task_layout, parent, false);

        //create and return View Holder
        return new FinishedAdapter.ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(FinishedAdapter.ViewHolder holder, int position) {
        //get data according to position
        ITaskObject task = taskList[position];

        String output = "Name: "+task.getTaskName() + "\n" +
                    "Start: " + task.getStartTime().substring(0, task.getStartTime().length() - 3) + "\n" +
                    "Due: " + task.getEndTime().substring(0, task.getEndTime().length() - 3) + "\n" +
                    "Priority: " + logic.getTaskPriorityText(task) + "\n" +
                    "Task: "+task.getType()+" "+task.getQuantity()+" ("+task.getQuantityUnit()+")";

//--------------------------------------------------------------------------------------------------
// show task detail in check box
        holder.taskBox.setText(output);
        holder.taskBox.setChecked(task.isCompleted());
//--------------------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return taskList.length;
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox taskBox;
        FloatingActionButton delete;

        //update the adapter with new data
        public void refreshAdapterData(){
            FinishedAdapter.this.taskList = logic.getCompleted();
            FinishedAdapter.this.notifyDataSetChanged();
        }

        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            taskBox = itemView.findViewById(R.id.todoCheckBox);
            delete = itemView.findViewById(R.id.deleteTask);
            logic = new TodolistLogic();

//--------------------------------------------------------------------------------------------------
// "check" button action, check a task and print message

            taskBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!taskBox.isChecked()){
                        if(logic.setCompleted(true,getAdapterPosition(),false)) {
                            Toast toast = Toast.makeText(itemView.getContext(), "Uncheck", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                            toast.show();
                            taskBox.setEnabled(false);
                            delete.setEnabled(false);
                        }
                        else{
                            throw new RuntimeException("Uncheck task fail");
                        }
                    }
                }
            });

//--------------------------------------------------------------------------------------------------
//  "delete" button action, delete a task

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!logic.deleteTask(true,getAdapterPosition())){
                        throw new RuntimeException("Delete task fail");
                    }
                    else{
                        Toast toast = Toast.makeText(itemView.getContext(),"Task deleted task successfully",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                        toast.show();
                        FinishedAdapter.this.taskList = logic.getCompleted();
                        refreshAdapterData();
                    }
                }
            });
        }
    }
}
