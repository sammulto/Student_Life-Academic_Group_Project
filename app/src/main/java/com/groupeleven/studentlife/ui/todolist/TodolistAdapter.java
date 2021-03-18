package com.groupeleven.studentlife.ui.todolist;

import android.content.Context;
import android.content.Intent;
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
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import org.jetbrains.annotations.NotNull;

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.ViewHolder>{
    private ITaskObject[] taskList;
    private ITodolistLogic logic;

    public TodolistAdapter(ITaskObject[] taskList){
        this.taskList = taskList;
        this.logic = new TodolistLogic();
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
        ITaskObject task = taskList[position];
        String output = task.getTaskName()+"\n"+"Due: "+
                        task.getEndTime().substring(0, task.getEndTime().length() - 3)+"\n"+
                        "Priority: "+logic.getTaskPriorityText(task)+"\n"+
                        task.getType()+" estimated: \n"+logic.getTimeEstimate(position)+" minutes";

//--------------------------------------------------------------------------------------------------
// show task detail in check box
        holder.taskBox.setText(output);
        holder.taskBox.setChecked(task.getStatus() != 0);
//--------------------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return taskList.length;
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox taskBox;
        FloatingActionButton edit;
        FloatingActionButton delete;

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
// "check" button action, check a task and print message

            taskBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(taskBox.isChecked()){
                        if(logic.setCompleted(getAdapterPosition(),true)) {
                            Toast toast = Toast.makeText(itemView.getContext(), "Check", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                            toast.show();
                        }
                        else{
                            throw new RuntimeException("Check task fail");
                        }
                    }
                    if(!taskBox.isChecked()){
                        if(logic.setCompleted(getAdapterPosition(),false)) {
                            Toast toast = Toast.makeText(itemView.getContext(), "Uncheck", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                            toast.show();
                        }
                        else{
                            throw new RuntimeException("Uncheck task fail");
                        }
                    }
                }
            });

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
                        throw new RuntimeException("Delete task fail");
                    }
                    else{
                        Toast toast = Toast.makeText(itemView.getContext(),"Task deleted task successfully",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                        toast.show();
                        TodolistAdapter.this.taskList = logic.getData();
                        refreshAdapterData();
                    }
                }
            });
        }
    }
}
