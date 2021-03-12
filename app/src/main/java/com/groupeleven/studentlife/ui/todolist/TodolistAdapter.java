package com.groupeleven.studentlife.ui.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import org.jetbrains.annotations.NotNull;

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.ViewHolder>{
    private Task[] taskList;
    private ITodolistLogic logic;

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
        String output = task.getTaskName()+"\n"+
                        task.getEndTime()+"\n"+
                        ITodolistLogic.toPriority(task.getPriorityInt());

//--------------------------------------------------------------------------------------------------
// show task detail to checkboxs
        holder.taskBox.setText(output);

        holder.taskBox.setChecked(intToBoolean(task.getStatus()));
//--------------------------------------------------------------------------------------------------
    }

//--------------------------------------------------------------------------------------------------
// 0 is false, 1 is true
    private boolean intToBoolean(int status){
        return status!=0;
   }
//--------------------------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return taskList.length;
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox taskBox;
        FloatingActionButton edit;
        FloatingActionButton delete;
        TodolistLogic logic;

        //update the adapter with new data
        public void refreshAdapterData(){
            TodolistAdapter.this.taskList = logic.getData();
            TodolistAdapter.this.notifyDataSetChanged();
        }

        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            taskBox = itemView.findViewById(R.id.todoCheckBox);
            edit = itemView.findViewById(R.id.editTask);
            delete = itemView.findViewById(R.id.deleteTask);
            logic = new TodolistLogic();

//--------------------------------------------------------------------------------------------------
// "edit" button action, jump to edit activity
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(itemView.getContext(), Toedit.class);
                    in.putExtra("id",getAdapterPosition());
                    itemView.getContext().startActivity(in);
                    refreshAdapterData();
                }
            });
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
//  "delete" button action, delete a task

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!logic.deleteTask(getAdapterPosition())){
                        Toast.makeText(itemView.getContext(),"Delete task fail",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(itemView.getContext(),"Task deleted task successfully",Toast.LENGTH_SHORT).show();
                        TodolistAdapter.this.taskList = logic.getData();
                        refreshAdapterData();
                    }
                }
            });
//--------------------------------------------------------------------------------------------------
        }
    }

}
