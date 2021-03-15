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
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.DashboardLogic;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DashboardEventListAdapter extends RecyclerView.Adapter<DashboardEventListAdapter.ViewHolder>{

    private Task[] taskList;   // list to hold data from DSO
    private DashboardLogic logic;

    public DashboardEventListAdapter(Task[] taskList){

        this.taskList = taskList;
        this.logic = new DashboardLogic();
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
        ITaskObject.Priority priority = task.getPriority();
        String priorityText = logic.getPriorityText(task);

        //for date formatting
        SimpleDateFormat orgFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd", Locale.CANADA);
        String endTime = " ";

        //populate information for the views
        taskNameView.setText(task.getTaskName());
        taskStatusView.setText(priorityText);

        try {
            endTime = newFormat.format(orgFormat.parse(task.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskEndTimeView.setText(endTime);
    }

    @Override
    public int getItemCount() {
        return taskList.length;
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
