package com.groupeleven.studentlife.ui.calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;

import java.util.Calendar;

public class CalendarToUpdate extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText name;
    private EditText startTime;
    private EditText endTime;
    private EditText priorityText;
    private EditText taskType;
    private EditText quantity;
    private EditText unit;
    private Spinner priority;
    private Spinner taskTypeSpinner;
    private Spinner unitSpinner;
    private Button button;

    private ICalendarLogic logic = new CalendarLogic();

    // variable for time/date picker
    private int sYear, sMonth, sDay, eYear, eMonth, eDay;
    private int sHour, sMinute, eHour, eMinute;

    // flag to see we are in start time picker or end time picker
    private int flag;
    private final int FLAG_START = 0;
    private final int FLAG_END = 1;

    // to see which type of output we want in unit
    private int resource = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toupdate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//--------------------------------------------------------------------------------------------------
// variable initialization
        startTime = findViewById(R.id.editStartTime);
        startTime.setInputType(InputType.TYPE_NULL);

        endTime = findViewById(R.id.editEndTime);
        endTime.setInputType(InputType.TYPE_NULL);

        button = findViewById(R.id.updateButton);

        priorityText = findViewById(R.id.editPriority);
        priority = findViewById((R.id.prioritySpinner));
        name = findViewById(R.id.name);

        taskType = findViewById(R.id.taskType);
        quantity = findViewById(R.id.quantity);
        unit = findViewById(R.id.unit);
        taskTypeSpinner = findViewById(R.id.taskTypeSpinner);
        unitSpinner = findViewById(R.id.unitSpinner);

//--------------------------------------------------------------------------------------------------
// handle date passed by adapter
        Intent in = getIntent();
        int positon = in.getExtras().getInt("id",-1);
        String mySelectedDate=in.getExtras().getString("selectedDate");

//        int positon = -1;
        if (positon == -1) {
            button.setText("Add");
        }

//--------------------------------------------------------------------------------------------------
// priority spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.priority));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapter);

        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // the first choice in spinner is hint(cannot be chose)
                if (parent.getItemAtPosition(position).equals("Choose priority")) {
                    ((TextView) view).setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//--------------------------------------------------------------------------------------------------
// priority show up
// after we clicked priority box we can see the spinner

        priorityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityText.setHint("");
                priority.setVisibility(View.VISIBLE);
            }
        });


//--------------------------------------------------------------------------------------------------
//Pick start time (both date and time )

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                Calendar calendar = Calendar.getInstance();
                sYear = calendar.get(Calendar.YEAR);
                sMonth = calendar.get(Calendar.MONTH);
                sDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarToUpdate.this, CalendarToUpdate.this, sYear, sMonth, sDay);
                datePickerDialog.show();
            }
        });


//--------------------------------------------------------------------------------------------------
//Pick end time (both date and time)

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                Calendar calendar = Calendar.getInstance();
                eYear = calendar.get(Calendar.YEAR);
                eMonth = calendar.get(Calendar.MONTH);
                eDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarToUpdate.this, CalendarToUpdate.this, eYear, eMonth, eDay);
                datePickerDialog.show();
            }
        });

//--------------------------------------------------------------------------------------------------
// add or update button
// final process in this activity

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String taskName = name.getText().toString();
                String taskPriority = priority.getSelectedItem().toString();
                String taskStart = startTime.getText().toString();
                String taskEnd = endTime.getText().toString();
                String type = taskTypeSpinner.getSelectedItem().toString();
                String workUnit = "";
                int num = 0;
                String fixedStart = taskStart + ":00";
                String fixedEnd = taskEnd + ":00";
                int nameLength = name.length();
                int startLength = taskStart.length();
                int endLength = taskEnd.length();


                if (unitSpinner.getSelectedItem() != null) {
                    workUnit = unitSpinner.getSelectedItem().toString();
                }
                if (!quantity.getText().toString().equals("")) {
                    num = Integer.parseInt(quantity.getText().toString());
                }

                // to see we need add or update a task
                boolean isComplete = false;
                String message = "";

                if (positon == -1) {
                    isComplete = logic.addTask(taskName, taskPriority, fixedStart, fixedEnd, type, num, workUnit);
                    message = "Task added successfully";
                } else {
                    isComplete = logic.editTask(mySelectedDate,positon, taskName, taskPriority, fixedStart, fixedEnd, type, num, workUnit);
                    message = "Task edited successfully";
                }

                // pass to logic to check is add complete

                if (isComplete) {
                    finish();
                    Toast toast = Toast.makeText(CalendarToUpdate.this, message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                    toast.show();
                }

                // if not complete show the error
                else {
                    try {
                        logic.checkUserInput(nameLength, taskPriority, startLength, endLength, type, num, workUnit);
                    } catch (Exception e) {
                        Toast.makeText(CalendarToUpdate.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }



            }

        });


//--------------------------------------------------------------------------------------------------
// time estimator part
//--------------------------------------------------------------------------------------------------
// task type spinner

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeSpinner.setAdapter(adapter1);

        taskTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose task type")) {
                    ((TextView) view).setTextColor(Color.GRAY);
                }

                if (parent.getItemAtPosition(position).equals("Reading")) {
                    resource = R.array.read;
                    setUnitSpinner();
                } else if (parent.getItemAtPosition(position).equals("Homework")) {
                    resource = R.array.homework;
                    setUnitSpinner();
                } else if (parent.getItemAtPosition(position).equals("Lecture")) {
                    resource = R.array.lecture;
                    setUnitSpinner();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//--------------------------------------------------------------------------------------------------
// task type show up
// after we clicked task type box we can see the spinner

        taskType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskType.setHint("");
                taskTypeSpinner.setVisibility(View.VISIBLE);
            }
        });
    }


    //--------------------------------------------------------------------------------------------------
// unit spinner
    public void setUnitSpinner() {
        unit.setHint("");
        unitSpinner.setVisibility(View.VISIBLE);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(resource));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter2);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose you unit")) {
                    ((TextView) view).setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

//--------------------------------------------------------------------------------------------------
// for date/time picker

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();

        // we are in start time picker
        if (flag == FLAG_START) {
            sYear = year;
            sMonth = month;
            sDay = dayOfMonth;
            sHour = calendar.get(Calendar.HOUR_OF_DAY);
            sMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarToUpdate.this, CalendarToUpdate.this, sHour, sMinute, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }

        // we are in end time picker
        else if (flag == FLAG_END) {
            eYear = year;
            eMonth = month;
            eDay = dayOfMonth;
            eHour = calendar.get(Calendar.HOUR_OF_DAY);
            eMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarToUpdate.this, CalendarToUpdate.this, eHour, eMinute, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();

        // we are in start time picker
        if (flag == FLAG_START) {
            sHour = hourOfDay;
            sMinute = minute;

            calendar.set(sYear, sMonth, sDay, sHour, sMinute);
            startTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", calendar));
        }

        // we are in end time picker
        else if (flag == FLAG_END) {
            eHour = hourOfDay;
            eMinute = minute;

            calendar.set(eYear, eMonth, eDay, eHour, eMinute);
            endTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", calendar));
        }
    }

    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}