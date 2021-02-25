package com.groupeleven.studentlife.ui.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import java.util.Calendar;

public class Toadd extends AppCompatActivity{


    EditText name;
    EditText timer;
    EditText dater;
    EditText priority;
    Spinner spinner;
    Button buttonAdd;
    int Year,Month,Day;
    int Hour,Minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toadd);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        timer = findViewById(R.id.editTextTime);
        timer.setInputType(InputType.TYPE_NULL);

        dater = findViewById(R.id.editTextDate);
        dater.setInputType(InputType.TYPE_NULL);

        buttonAdd = findViewById(R.id.button3);

        priority = findViewById(R.id.editPriority);
        spinner = findViewById((R.id.spinner1));
        name = findViewById(R.id.name);

   /*    ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,level);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                    case 0:
                        String text = parent.getItemAtPosition(position).toString();
                        priority.setText(text);
                        break;
                    case 1:
                        text = parent.getItemAtPosition(position).toString();
                        priority.setText(text);
                        break;
                    case 2:
                        text = parent.getItemAtPosition(position).toString();
                        priority.setText(text);
                        break;
                }

            }

        });*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.priority));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        priority.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                priority.setHint("");
                spinner.setVisibility(View.VISIBLE);
            }
        });

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

                                timer.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },Hour,Minute,false
                );
                //timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
            }
        });

        dater.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Calendar Cal = Calendar.getInstance();
                //Date = Cal.get(Calendar.DATE);
                //Month = Cal.get(Calendar.MONTH);
                //Year = Cal.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Toadd.this,new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = month+"/"+day+"/"+year+"  M/D/Y";
                                dater.setText(date);
                                //Year = year;
                                //Month = month;
                                //Date = day;
                                //Calendar calendar = Calendar.getInstance();

                                //calendar.set(0,0,0,0,0);

                                //tvTimer2.setText(DateFormat.format("yy:mm:dd aa",calendar));
                            }
                        }, Year,Month,Day
                );
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                datePickerDialog.show();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String a = name.getText().toString();
                //timer.get
                String p = priority.getText().toString();


                TodolistFragment.database.insertTask(new Task("Task" + 1, 0, "2020-01-01 12:12:12", "2020-01-01 12:12:12", 0, "test Type"));
                finish();
            }
        });
/*
        private int toInt(String s){
            int result = 0;
            if (s.quals("Low")) {

            }
        }*/
    }
}