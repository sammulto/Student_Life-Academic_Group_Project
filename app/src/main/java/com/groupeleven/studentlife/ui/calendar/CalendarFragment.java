package com.groupeleven.studentlife.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    ICalendarLogic calendarLogic = new CalendarLogic();
    ITaskObject[] taskList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarLogic = new CalendarLogic();
        taskList = null;

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) root.findViewById(R.id.Calendar_CalendarView);


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                String[] temp = materialCalendarView.getSelectedDate().toString().split("[{}}]");


                taskList = calendarLogic.viewTask(temp[1]);

                if (taskList != null) {
                    System.out.println(taskList.length);
                } else {
                    System.out.println("There are no tasks for the day");

                }

            }
        });

        return root;
    }


}