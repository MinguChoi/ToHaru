package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

public class CalendarActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private final boolean         D = true;
    private final String          TAG = "CalendarActivity";

    private LinearLayout          linLAY;
    private ImageButton           setting_calender_BTN;
    private ImageButton           changeView_calendar_BTN;
    private MCalendarView         CalenderView_calendar_VIEW;
    private Intent                intent;

    private ListView              ListView_calendar_LST;
    private DiaryAdapter          adapter;
    private List<Diary>           diaries;

    // 날짜 관련 데이터 ------------------------------------------------------------
    public String                 mDate;
    private String                dateYEAR;
    private String                dateMONTH;
    private String                dateDAY;
    private String[]              dateArray;
    // -----------------------------------------------------------------------------

    private boolean               show_calendar = true;


    private boolean CheckWR; // true = 작성된 사항 / false = 작성이 안된 사항
    //----------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        init();
    }

    //----------------------------------------------------------------------------------
    // DataBase에서 저장된 일기들 ListView로 불러오기
    //----------------------------------------------------------------------------------
    public void displayListView() {
        // Fetch diaries from database and update UI
        API_Diary.fetchPosts(new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                diaries = (ArrayList<Diary>) object;
                adapter = new DiaryAdapter(diaries, CalendarActivity.this);
                ListView_calendar_LST.setAdapter(adapter);
            }
        });
    }
    //----------------------------------------------------------------------------------

    
    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    public void init(){

        CheckWR = false; // 초기엔 안쓴 상태로 초기화
        linLAY = findViewById(R.id.linLAY);
        setting_calender_BTN = findViewById(R.id.setting_calender_BTN);
        CalenderView_calendar_VIEW = findViewById(R.id.CalenderView_calendar_VIEW);
        changeView_calendar_BTN = findViewById(R.id.changeView_calendar_BTN);
        ListView_calendar_LST = findViewById(R.id.ListView_calendar_LST);
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // 일기 불러와서 출력하기
        //----------------------------------------------------------------------------------
        displayListView();
        do_Mark();
        //----------------------------------------------------------------------------------



        //----------------------------------------------------------------------------------
        // Setting 버튼 이벤트
        //----------------------------------------------------------------------------------
        setting_calender_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CalendarActivity.this, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        //----------------------------------------------------------------------------------



        //----------------------------------------------------------------------------------
        // Calendar -> ListView 전환 버튼
        //----------------------------------------------------------------------------------
        changeView_calendar_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_calendar == true){
                    show_calendar = false;
                    CalenderView_calendar_VIEW.setVisibility(View.INVISIBLE);
                    ListView_calendar_LST.setVisibility(View.VISIBLE);
                }
                else{
                    show_calendar = true;
                    ListView_calendar_LST.setVisibility(View.INVISIBLE);
                    CalenderView_calendar_VIEW.setVisibility(View.VISIBLE);
                }
            }
        });
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // Calendar에서 원하는 날짜를 클릭할 때 이벤트
        //----------------------------------------------------------------------------------
        CalenderView_calendar_VIEW.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
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
        //----------------------------------------------------------------------------------

    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 날짜에 표식이 있는지(일기 작성여부) 파악
    //----------------------------------------------------------------------------------
    public boolean isMarked(DateData date){
        MarkedDates markedDates = MarkedDates.getInstance();
        if (markedDates.check(date) != null)
            return true;

        return false;
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 일기를 작성한 날짜에 표식 남기기
    //----------------------------------------------------------------------------------
    public void do_Mark() {
        // Fetch diaries from database and update UI
        API_Diary.fetchPosts(new OnCompletion() {
            @Override
            public void onCompletion(Object object) {

                diaries = (ArrayList<Diary>) object;
                Log.d(Utils.TAG, "fetch diaries on completion " + diaries.size());
                for(int i = 0; i<diaries.size(); i++){
                    diaries.get(i).getDate();
                    dateArray = diaries.get(i).getDate().split("/");
                    CalenderView_calendar_VIEW.markDate(new DateData(Integer.parseInt(dateArray[0]),
                            Integer.parseInt(dateArray[1]),
                            Integer.parseInt(dateArray[2])).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED)));
                }
            }
        });
    }
    //----------------------------------------------------------------------------------
}