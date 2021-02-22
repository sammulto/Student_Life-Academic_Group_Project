/*
 *  This Adapter will populate the data from DSO into RecyclerView (in Dashboard layout)
 *  The adapter will covert DSO to a row item
 */

package com.groupeleven.studentlife.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.groupeleven.studentlife.R;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class DashboardEventListAdapter extends RecyclerView.Adapter<DashboardEventListAdapter.ViewHolder>{

    private List<DashboardTempData> eventList;   // list to hold data from DSO

    // View Holder provides refs to views(from the rows in RecyclerView)
    public class ViewHolder extends RecyclerView.ViewHolder{

        //each rows in Recycler View contains:
        public TextView titleTextView;
        public Button actionButtonView;

        //itemView is the entire row in RecyclerView
        public ViewHolder(View itemView){
            super(itemView);
            //find the subviews
            titleTextView = itemView.findViewById(R.id.event_item);
            actionButtonView = itemView.findViewById(R.id.action_button);
        }
    }

    public DashboardEventListAdapter(List<DashboardTempData> eventList){
        this.eventList = eventList;
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
        //get data according to position
        DashboardTempData event = eventList.get(position);

        //set the views
        TextView textView = holder.titleTextView;
        textView.setText(event.getTitle());
        Button button = holder.actionButtonView;
        button.setText(event.getEvent());
        button.setEnabled(true);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public int getItemViewType(final int position) {
        return R.layout.dash_board_event_row;
    }
}
