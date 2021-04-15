package com.groupeleven.studentlife.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.groupeleven.studentlife.logic.CalendarLogic;
import com.groupeleven.studentlife.logic.ICalendarLogic;

public class CalendarDelete extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent in = getIntent();
        int position = in.getExtras().getInt("id");
        String mySelectedDate = in.getExtras().getString("selectedDate");

        ICalendarLogic logic = new CalendarLogic();

        if (!logic.deleteTask(mySelectedDate,position)) {
            throw new RuntimeException("Delete task fail");

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Task deleted task successfully", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
            toast.show();
        }

        finish();
    }

    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }

}
