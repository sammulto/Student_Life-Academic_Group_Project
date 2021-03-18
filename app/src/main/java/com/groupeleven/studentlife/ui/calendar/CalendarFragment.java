package com.groupeleven.studentlife.ui.calendar;

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
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;
import com.groupeleven.studentlife.ui.todolist.TodolistAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

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

                if (taskList != null) {
                    System.out.println(taskList.length);
                } else {
                    System.out.println("There are no tasks for the day");

                }

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