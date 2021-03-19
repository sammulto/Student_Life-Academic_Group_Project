package com.groupeleven.studentlife.ui.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    ICalendarLogic calendarLogic;
    ITaskObject[] taskList;

    private ITodolistLogic logic;
    private RecyclerView taskRecycle;
    private CalendarAdapter taskAdapter;

    private String selectedDate;

    public CalendarFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);


        calendarLogic = new CalendarLogic();
        taskList = null;
        selectedDate = "";

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) root.findViewById(R.id.Calendar_CalendarView);

        ArrayList<CalendarDay> dayList = calendarLogic.getDayList();

        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return dayList.contains(day);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new DotSpan(10, Color.RED));
            }
        });

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                String[] temp = materialCalendarView.getSelectedDate().toString().split("[{}}]");
                temp = temp[1].split("-");
                selectedDate = temp[0] + "-0" + temp[1];
                if (temp[2].length() == 2) {
                    selectedDate += "-" + temp[2];
                } else if (temp[2].length() == 1) {
                    selectedDate += "-0" + temp[2];
                }
                taskList = calendarLogic.viewTask(selectedDate);
                viewTasksForSelectedDate(root, selectedDate);
            }
        });

        return root;
    }


    public void viewTasksForSelectedDate(View view, String date) {

        logic = new TodolistLogic();
        taskList = calendarLogic.viewTask(date);
        taskRecycle = view.findViewById(R.id.task_recyclerView);

        taskAdapter = new CalendarAdapter(taskList,date);
        taskRecycle.setAdapter(taskAdapter);
        taskRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

    }




}