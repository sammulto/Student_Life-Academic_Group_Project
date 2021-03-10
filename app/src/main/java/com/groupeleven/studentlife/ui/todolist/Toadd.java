package com.groupeleven.studentlife.ui.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
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
import com.groupeleven.studentlife.logic.ITodolistLogic;
import com.groupeleven.studentlife.logic.TodolistLogic;

import java.util.Calendar;

public class Toadd extends AppCompatActivity{

    private EditText name;
    private EditText timer;
    private EditText dater;
    private EditText priorityText;
    private Spinner priority;
    private Button buttonAdd;
    private int Year,Month,Day;
    private int Hour,Minute;

    private ITodolistLogic logic = new TodolistLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toadd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//--------------------------------------------------------------------------------------------------
// variable initialization
        timer = findViewById(R.id.editTextTime);
        timer.setInputType(InputType.TYPE_NULL);

        dater = findViewById(R.id.editTextDate);
        dater.setInputType(InputType.TYPE_NULL);

        buttonAdd = findViewById(R.id.button3);

        priorityText = findViewById(R.id.editPriority);
        priority = findViewById((R.id.spinner1));
        name = findViewById(R.id.name);

//--------------------------------------------------------------------------------------------------
// priority spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.priority));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapter);

        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose priority")){
                    ((TextView) view).setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
    });

//--------------------------------------------------------------------------------------------------
// priority show up

        priorityText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                priorityText.setHint("");
                priority.setVisibility(View.VISIBLE);
            }
        });


//--------------------------------------------------------------------------------------------------
//Pick date

        Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);

        timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Toadd.this,new TimePickerDialog.OnTimeSetListener(){
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Hour = hourOfDay;
                                Minute = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0,Hour,Minute);

                                timer.setText(DateFormat.format("HH:mm",calendar));
                            }
                        },Hour,Minute,true
                );
                timePickerDialog.show();
            }
        });

//--------------------------------------------------------------------------------------------------
//Pick time

        dater.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Toadd.this,new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                String tempMon=""+month;
                                String tempDay=""+day;
                                month = month + 1;
                                if(day<10){
                                    tempDay = "0"+day;
                                }
                                if(month<10){
                                    tempMon = "0"+month;
                                }
                                String date = year+"-"+tempMon+"-"+tempDay;
                                dater.setText(date);
                            }
                        }, Year,Month,Day
                );
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                datePickerDialog.show();
            }
        });

//--------------------------------------------------------------------------------------------------
// add button

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Calendar calendar = Calendar.getInstance();
                String taskName = name.getText().toString();
                String taskPriority = priority.getSelectedItem().toString();
                String taskDate = dater.getText().toString();
                String taskTime = timer.getText().toString();
                String fixedTaskTime = timer.getText().toString()+":00";

                int intPriority = ITodolistLogic.toInt(taskPriority);
                    if (logic.addTask(taskName, intPriority, taskDate+" "+fixedTaskTime)) {

                        finish();
                        Toast.makeText(Toadd.this,"Task added successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String whereFault = logic.whichDataNotfill(taskName,taskPriority,taskDate,taskTime);
                        Toast.makeText(Toadd.this,whereFault,Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }


}