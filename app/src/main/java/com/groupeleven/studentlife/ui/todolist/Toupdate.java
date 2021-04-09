package com.groupeleven.studentlife.ui.todolist;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.PriorityChannel;
import com.groupeleven.studentlife.logic.AlarmReceiver;
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Toupdate extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private EditText name;
    private EditText startTime;
    private EditText endTime;
    private EditText quantity;
    private EditText unit;
    private Spinner priority;
    private Spinner taskTypeSpinner;
    private Spinner unitSpinner;
    private Button button;

    private ITodolistLogic logic = new TodolistLogic();

    // variable for time/date picker
    private int sYear,sMonth,sDay,eYear,eMonth,eDay;
    private int sHour,sMinute,eHour,eMinute;

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
        name = findViewById(R.id.name);
        startTime = findViewById(R.id.editStartTime);
        startTime.setInputType(InputType.TYPE_NULL);

        endTime = findViewById(R.id.editEndTime);
        endTime.setInputType(InputType.TYPE_NULL);

        priority = findViewById((R.id.prioritySpinner));

        taskTypeSpinner = findViewById(R.id.taskTypeSpinner);
        quantity = findViewById(R.id.quantity);
        unit = findViewById(R.id.unit);
        unitSpinner = findViewById(R.id.unitSpinner);

        button = findViewById(R.id.updateButton);
//--------------------------------------------------------------------------------------------------
// handle date passed by adapter
        Intent in = getIntent();
        int positon = in.getExtras().getInt("id",-1);

        if(positon==-1){
            button.setText("Add");
        }

//--------------------------------------------------------------------------------------------------
// priority spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.priority));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapter);

        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // the first choice in spinner is hint(cannot be chose)
                if(parent.getItemAtPosition(position).equals("Choose priority")){
                    ((TextView) view).setTextColor(Color.GRAY);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//--------------------------------------------------------------------------------------------------
//Pick start time (both date and time )

        startTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flag = 0;
                Calendar calendar = Calendar.getInstance();
                sYear = calendar.get(Calendar.YEAR);
                sMonth = calendar.get(Calendar.MONTH);
                sDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Toupdate.this, Toupdate.this,sYear,sMonth,sDay);
                datePickerDialog.show();
            }
        });


//--------------------------------------------------------------------------------------------------
//Pick end time (both date and time)

        endTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flag = 1;
                Calendar calendar = Calendar.getInstance();
                eYear = calendar.get(Calendar.YEAR);
                eMonth = calendar.get(Calendar.MONTH);
                eDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Toupdate.this, Toupdate.this,eYear,eMonth,eDay);
                datePickerDialog.show();
            }
        });

//--------------------------------------------------------------------------------------------------
// add or update button
// final process in this activity


        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
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


                if(unitSpinner.getSelectedItem()!=null){
                    workUnit = unitSpinner.getSelectedItem().toString();
                }
                if(!quantity.getText().toString().equals("")){
                    num = Integer.parseInt(quantity.getText().toString());
                }

//--------------------------------------------------------------------------------------------------
//notification added
//--------------------------------------------------------------------------------------------------
                String hint = taskName + " begins from " + taskStart;
                Calendar c = Calendar.getInstance();
                c.set(sYear, sMonth, sDay, sHour, sMinute);
                startAlarm(c, taskName,hint);

                // to see we need add or update a task
                boolean isComplete = false;
                String message = "";

                if(positon==-1){
                    isComplete = logic.addTask(taskName, taskPriority, fixedStart, fixedEnd,type,num,workUnit);
                    message = "Task added successfully";
                }
                else{
                    isComplete = logic.editTask(positon,taskName, taskPriority, fixedStart, fixedEnd,type,num,workUnit);
                    message = "Task edited successfully";
                }

                // pass to logic to check is add complete
                if (isComplete) {
                    finish();
                    Toast toast = Toast.makeText(Toupdate.this,message,Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 400);
                    toast.show();
                }

                // if not complete show the error
                else {
                    try {
                        logic.checkUserInput(nameLength, taskPriority, startLength, endLength, type, num, workUnit);
                    }catch (Exception e) {
                        Toast.makeText(Toupdate.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


//--------------------------------------------------------------------------------------------------
// time estimator part
//--------------------------------------------------------------------------------------------------
// task type spinner

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeSpinner.setAdapter(adapter1);

        taskTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose task type")){
                    ((TextView) view).setTextColor(Color.GRAY);
                }

                if(parent.getItemAtPosition(position).equals("Reading")){
                    resource = R.array.read;
                    setUnitSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Homework")){
                    resource = R.array.homework;
                    setUnitSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Lecture")){
                    resource = R.array.lecture;
                    setUnitSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Lab")){
                    resource = R.array.lab;
                    setUnitSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Term paper")){
                    resource = R.array.paper;
                    setUnitSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Studying")){
                    resource = R.array.study;
                    setUnitSpinner();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


//--------------------------------------------------------------------------------------------------
// unit spinner
    public void setUnitSpinner(){
        unit.setHint("");
        unitSpinner.setVisibility(View.VISIBLE);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(resource));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter2);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose you unit")){
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
        if (flag == FLAG_START){
            sYear = year;
            sMonth = month;
            sDay = dayOfMonth;
            sHour = calendar.get(Calendar.HOUR_OF_DAY);
            sMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(Toupdate.this, Toupdate.this, sHour, sMinute, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }

        // we are in end time picker
        else if(flag == FLAG_END){
            eYear = year;
            eMonth = month;
            eDay = dayOfMonth;
            eHour = calendar.get(Calendar.HOUR_OF_DAY);
            eMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(Toupdate.this, Toupdate.this, eHour, eMinute, DateFormat.is24HourFormat(this));
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

    //set the notification with a specific time and necessary text
    private void startAlarm(Calendar c, String title, String message){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("TaskName", title);
        intent.putExtra("Hint", message);

//have a random request code makes the jump out notification no longer have the same data
        int r = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        r += new Random().nextInt(100) + 1;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, r, intent, 0);

//if the start time is behind the current date, then no notification (kind of tricky here)
//        if(c.before(Calendar.getInstance())){
//            c.add(Calendar.DATE, 1);
//        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}