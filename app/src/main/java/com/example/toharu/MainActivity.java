package com.example.toharu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();

    private int year = myCalendar.get(Calendar.YEAR);
    private int month = myCalendar.get(Calendar.MONTH);
    private int day = myCalendar.get(Calendar.DAY_OF_MONTH);

//   DatePickerDialog myDatePicker = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
//       @Override
//       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//           myCalendar.set(Calendar.YEAR, year);
//           myCalendar.set(Calendar.MONTH, month);
//           myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//           updateLabel();
//       }
//   }, year, month, day);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__calendar);

        EditText et_Date = (EditText)findViewById(R.id.et_date);

        Button createBTN = (Button)findViewById(R.id.createBTN);
        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDataPicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, myDataPicker, year, month, day);
                datePickerDialog.show();
            }


        });

    }

    private void updateLabel(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText)findViewById(R.id.et_date);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }
}