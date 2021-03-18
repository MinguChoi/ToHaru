package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.toharu.Model.Diary;

import java.util.ArrayList;
import java.util.List;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnExpDateClickListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

public class CalendarActivity extends AppCompatActivity {

    private final boolean   D = true;
    private final String    TAG = "CalendarActivity";

    public List<Diary> diaries = new ArrayList<Diary>();
    private LinearLayout          linLAY;
    private Button                settingBTN;
    private Intent                intent;
    private MCalendarView         CalendarView;

    public String                mDate;
    private String                dateYEAR;
    private String                dateMONTH;
    private String                dateDAY;

    private boolean CheckWR; // true = 작성된 사항 / false = 작성이 안된 사항


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        init();
    }

    public void init(){

        linLAY = findViewById(R.id.linLAY);
        settingBTN = findViewById(R.id.settingBTN);
        CalendarView = findViewById(R.id.CalenderView);
        CheckWR = false; // 초기엔 안쓴 상태로 초기화


        linLAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CalendarActivity.this, EmotionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        settingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CalendarActivity.this, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

//        expCalendarView.setOnDateClickListener(new OnExpDateClickListener(){
//            public void onExpDateClickListener(View view, DateData date){
//                Toast.makeText(CalendarActivity.this, String.format("%d-%d", date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
//            }
//        });

        CalendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                if(D) Log.i(TAG, "onClick()" + date.getYear() + " // "+ date.getMonth() + " // " + date.getDay());

                if(CheckWR == true){ // 쓴 상태 라면
                    intent = new Intent(CalendarActivity.this, ReadActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    // 선택한 날짜의 연도와 달, 일 모두 DB에 저장 해야함
//                    CheckWR = true; // 값을 DB에 저장 해야함
                    dateYEAR = Integer.toString(date.getYear());
                    dateMONTH = Integer.toString(date.getMonth());
                    dateDAY = Integer.toString(date.getDay());

                    mDate = dateMONTH + "/" + dateDAY + "/" + dateYEAR ;
                    Log.i(TAG, mDate);

                    intent = new Intent(CalendarActivity.this, WriteActivity.class);
                    intent.putExtra("mDate", mDate);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }

            }
        });

    }

}