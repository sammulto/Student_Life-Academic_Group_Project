package com.groupeleven.studentlife.ui.calendar;

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
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.ui.todolist.Toupdate;

import org.jetbrains.annotations.NotNull;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ITaskObject[] taskList;
    private ICalendarLogic logic;
    private String date;

    public CalendarAdapter(ITaskObject[] taskList, String selectedDate) {
        this.taskList = taskList;
        this.logic = new CalendarLogic();
        this.date = selectedDate;
    }

    @NotNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView =
                inflater.inflate(R.layout.calendar_task_layout, parent, false);
//        LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_task_layout,parent,false);
        //create and return View Holder
        return new CalendarAdapter.ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(CalendarAdapter.ViewHolder holder, int position) {
        //get data according to position
        ITaskObject task = taskList[position];
        String output = task.getTaskName() + "\n" + "Due: " +
                task.getEndTime().substring(0, task.getEndTime().length() - 3) + "\n" +
                "Priority: " + logic.getTaskPriorityText(task) + "\n" +
                task.getType() + " estimated: " + logic.getTimeEstimate(position, taskList) + " minutes";

//--------------------------------------------------------------------------------------------------
// show task detail in check box
        holder.taskBox.setText(output);
        holder.taskBox.setChecked(task.isCompleted());
//--------------------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        int temp = 0;
        if (taskList != null) {
            temp = taskList.length;
        }
        return temp;
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox taskBox;
        FloatingActionButton edit;
        FloatingActionButton delete;


        //update the adapter with new data
        public void refreshAdapterData() {
            CalendarAdapter.this.taskList = logic.viewTask(date);

            CalendarAdapter.this.notifyDataSetChanged();
        }



        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView) {
            super(itemView);
            //find the subviews
            taskBox = itemView.findViewById(R.id.todoCheckBox);

            edit = itemView.findViewById(R.id.calendarEditTask);
            delete = itemView.findViewById(R.id.calendarDeleteTask);
            logic = new CalendarLogic();


//"edit" button action, jump to edit activity
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(itemView.getContext(), CalendarToUpdate.class);
                    in.putExtra("id", getAdapterPosition());
                    System.out.println("Selected Date="+ date);
                    in.putExtra("selectedDate",date);
                    itemView.getContext().startActivity(in);


                    refreshAdapterData();

                }

            });


//--------------------------------------------------------------------------------------------------
            logic = new CalendarLogic();

//--------------------------------------------------------------------------------------------------
//  "delete" button action, delete a task

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!logic.deleteTask(getAdapterPosition())) {
                        throw new RuntimeException("Delete task fail");
                    } else {
                        Toast toast = Toast.makeText(itemView.getContext(), "Task deleted task successfully", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                        toast.show();
                        com.groupeleven.studentlife.ui.calendar.CalendarAdapter.this.taskList = logic.viewTask(date);
                        refreshAdapterData();
                    }
                }
            });
        }
    }


//
}
