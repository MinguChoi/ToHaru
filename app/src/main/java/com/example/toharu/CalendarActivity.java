package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

public class CalendarActivity extends AppCompatActivity {

    private final boolean   D = true;
    private final String    TAG = "CalendarActivity";

    private LinearLayout          linLAY;
    private ImageButton           settingBTN;
    private ImageButton           ch_calendarBTN;
    private Intent                intent;
    private MCalendarView         CalendarView;

    private ListView              listView;
    private DiaryAdapter          adapter;
    private List<Diary>           diaries;

    public String                 mDate;
    private String                dateYEAR;
    private String                dateMONTH;
    private String                dateDAY;

    private String[]              dateArray;

    private boolean               show_calendar = true;


    private boolean CheckWR; // true = 작성된 사항 / false = 작성이 안된 사항




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        init();
        do_Mark();
    }

    public void displayListView() {
        // Fetch diaries from database and update UI
        API_Diary.fetchPosts(new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                diaries = (ArrayList<Diary>) object;
                Log.d(Utils.TAG, "diaries from db: " + diaries.size());
                adapter = new DiaryAdapter(diaries, CalendarActivity.this);
                listView.setAdapter(adapter);
            }
        });
    }

    public void init(){

        linLAY = findViewById(R.id.linLAY);
        settingBTN = findViewById(R.id.settingBTN);
        CalendarView = findViewById(R.id.CalenderView);
        CheckWR = false; // 초기엔 안쓴 상태로 초기화
        ch_calendarBTN = findViewById(R.id.ch_calendarBTN);
        listView = findViewById(R.id.main_listView);


        
        // 실험 ----------------
        displayListView();
        //adapter.notifyDataSetChanged();
        // --------------



        linLAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CalendarActivity.this, WriteActivity.class);
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

        ch_calendarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_calendar == true){
                    show_calendar = false;
                    CalendarView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                }
                else{
                    show_calendar = true;
                    listView.setVisibility(View.INVISIBLE);
                    CalendarView.setVisibility(View.VISIBLE);
                }
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
                CheckWR = isMarked(date);
                if(CheckWR == true){ // 쓴 상태 라면
                    dateYEAR = Integer.toString(date.getYear());
                    dateMONTH = Integer.toString(date.getMonth());
                    dateDAY = Integer.toString(date.getDay());
                    mDate = dateYEAR + "/" + dateMONTH + "/" + dateDAY ;
                    API_Diary.fetchADiary(mDate, new OnCompletion() {
                        @Override
                        public void onCompletion(Object object) {
                            intent = new Intent(CalendarActivity.this, ReadActivity.class);
                            intent.putExtra("diary", (Diary)object);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    // 선택한 날짜의 연도와 달, 일 모두 DB에 저장 해야함
//                    CheckWR = true; // 값을 DB에 저장 해야함
                    dateYEAR = Integer.toString(date.getYear());
                    dateMONTH = Integer.toString(date.getMonth());
                    dateDAY = Integer.toString(date.getDay());

                    mDate = dateYEAR + "/" + dateMONTH + "/" + dateDAY ;

                    Log.i(TAG, mDate);

                    intent = new Intent(CalendarActivity.this, EmotionActivity.class);
                    intent.putExtra("mDate", mDate);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }

            }
        });

    }

    public boolean isMarked(DateData date){
        MarkedDates markedDates = MarkedDates.getInstance();
        if (markedDates.check(date) != null)
            return true;

        return false;
    }
    public void do_Mark() {
        // Fetch diaries from database and update UI
        API_Diary.fetchPosts(new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                diaries = (ArrayList<Diary>) object;
                for(int i = 0; i<diaries.size(); i++){
                    if (diaries.get(i).getDate() != null) {
                        diaries.get(i).getDate();
                        Log.i(TAG, "\n" + diaries.get(i).getDate() + "\n");
                        dateArray = diaries.get(i).getDate().split("/");
                        CalendarView.markDate(new DateData(Integer.parseInt(dateArray[0]),
                                Integer.parseInt(dateArray[1]),
                                Integer.parseInt(dateArray[2])).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED)));
                    }
                }
            }
        });
    }

}