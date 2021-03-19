package com.groupeleven.studentlife.ui.calendar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;

import org.jetbrains.annotations.NotNull;


public class CalendarAdapter extends RecyclerView.Adapter<com.groupeleven.studentlife.ui.calendar.CalendarAdapter.ViewHolder>{
    private ITaskObject[] taskList;
    private ICalendarLogic logic;
    private String date;

    public CalendarAdapter(ITaskObject[] taskList, String selectedDate){
        this.taskList = taskList;
        this.logic = new CalendarLogic();
        this.date=selectedDate;
    }

    @NotNull
    @Override
    public com.groupeleven.studentlife.ui.calendar.CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.calendar_task_layout, parent, false);

        //create and return View Holder
        return new com.groupeleven.studentlife.ui.calendar.CalendarAdapter.ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(com.groupeleven.studentlife.ui.calendar.CalendarAdapter.ViewHolder holder, int position) {
        //get data according to position
        ITaskObject task = taskList[position];
        String output = task.getTaskName()+"\n"+"Due: "+
                task.getEndTime().substring(0, task.getEndTime().length() - 3)+"\n"+
                "Priority: "+logic.getTaskPriorityText(task)+"\n"+
                task.getType()+" estimated: "+logic.getTimeEstimate(position,taskList)+" minutes";

//--------------------------------------------------------------------------------------------------
// show task detail in check box
        holder.taskBox.setText(output);
        holder.taskBox.setChecked(task.isCompleted());
//--------------------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        int temp=0;
        if(taskList!=null){
            temp=taskList.length;
        }
        return temp;
    }

    // ViewHolder class provides refs to views (rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox taskBox;

        //update the adapter with new data
        public void refreshAdapterData(){
            com.groupeleven.studentlife.ui.calendar.CalendarAdapter.this.taskList = logic.viewTask(date);

            com.groupeleven.studentlife.ui.calendar.CalendarAdapter.this.notifyDataSetChanged();
        }

        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            taskBox = itemView.findViewById(R.id.todoCheckBox);

            logic = new CalendarLogic();

        }
    }
}