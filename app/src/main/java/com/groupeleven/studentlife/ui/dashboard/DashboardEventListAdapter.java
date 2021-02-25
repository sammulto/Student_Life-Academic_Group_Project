/*
 *  This Adapter will populate the data from DSO into RecyclerView (in Dashboard layout)
 *  The adapter will covert DSO to a row item
 */

package com.groupeleven.studentlife.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardEventListAdapter extends RecyclerView.Adapter<DashboardEventListAdapter.ViewHolder>{

    private Task[] taskList;   // list to hold data from DSO

    public DashboardEventListAdapter(Task[] taskList){
        this.taskList = taskList;
    }

    @NotNull
    @Override
    public DashboardEventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.dash_board_event_row, parent, false);

        //create and return View Holder
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView taskNameView = holder.taskNameTextView;
        TextView taskStatusView = holder.taskStatusTextView;
        TextView taskEndTimeView = holder.taskEndTimeTextView;
        Task task = taskList[position];
        int priority = task.getPriority(); // task's status code
        String priorityText = " ";

        //for date formatting
        SimpleDateFormat orgFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd", Locale.CANADA);
        String endTime = " ";

        //populate information for the views
        taskNameView.setText(task.getTaskName());

        if(priority == 1){
            priorityText = "HIGH";
        }else if (priority == 2){
            priorityText = "MEDIUM";
        }else if (priority == 3){
            priorityText = "LOW";
        }
        taskStatusView.setText(priorityText);

        try {
            endTime = newFormat.format(orgFormat.parse(task.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(endTime);
        taskEndTimeView.setText(endTime);
    }

    @Override
    public int getItemCount() {
        return taskList.length;
    }

    public void refreshView(){
        this.notifyDataSetChanged();
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        //each rows in Recycler View contains:
        public TextView taskNameTextView;
        public TextView taskStatusTextView;
        public TextView taskEndTimeTextView;

        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            taskNameTextView = itemView.findViewById(R.id.dashboard_task_name);
            taskStatusTextView = itemView.findViewById(R.id.dashboard_task_status);
            taskEndTimeTextView = itemView.findViewById(R.id.dashboard_task_end_time);
        }
    }
}
