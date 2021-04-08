package com.groupeleven.studentlife.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.DashboardLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

import static com.prolificinteractive.materialcalendarview.CalendarDay.today;

public class DashboardFragment extends Fragment {

    private ITaskObject[] listData; //events to show in the Dashboard
    private DashboardLogic logicUnit;
    private DashboardEventListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialCalendarView mcv;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mcv = (MaterialCalendarView) root.findViewById(R.id.weeklyCalendarView);
        mcv.setDateSelected(today(),true);


        //TODO
        //add day list
        //set decorator
//        mcv.addDecorator(new DayViewDecorator() {
//            @Override
//            public boolean shouldDecorate(CalendarDay day) {
//                return day.equals(CalendarDay.today());
//            }
//
//            @Override
//            public void decorate(DayViewFacade view) {
//                view.addSpan(new DotSpan(10, Color.BLUE));
//            }
//        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //init the event list
        logicUnit = new DashboardLogic();

        try {
            listData = logicUnit.getData();
        }catch (Exception error){
            System.out.println("Error when getting data from dashboard logic unit");
        }

        //populate the RecyclerView fire up the adapter
        recyclerView = view.findViewById(R.id.dashboard_recyclerView);
        adapter = new DashboardEventListAdapter(listData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(listData.length == 0) {
            //display empty list message
            TextView textView = requireView().findViewById(R.id.text_dashboard);
            textView.setText(R.string.noEvent);
        }

    }

}