package com.example.toharu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toharu.API.API_Advice;
import com.example.toharu.API.API_Auth;
import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.vo.DateData;

public class WriteActivity extends AppCompatActivity {

    private final boolean D = true;
    private final String TAG = "WriteActivity";

    private Button backBTN;
    private Button saveBTN;
    private EditText diaryETXT;

    private boolean getCheckWR;
    public String getdate;
    public String[] dateArray;
    private String    selected_emotion;
    private ImageView sellIMG;
    private MCalendarView CalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getdate = getIntent().getStringExtra("mDate2");

        selected_emotion = getIntent().getStringExtra("emotion_img");
        Log.i(TAG, "dd" + getdate);

        init();

    }

    public void init() {

        dateArray = getdate.split("/");
        Log.i(TAG, dateArray[0] + "\n" + dateArray[1] + "\n" + dateArray[2]);

        int IntdateMonth = Integer.parseInt(dateArray[0]); // 월
        int IntdateDay = Integer.parseInt(dateArray[1]); // 일
        int IntdateYear = Integer.parseInt(dateArray[2]); // 년


        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryETXT = findViewById(R.id.diary_writeETXT);
        CalendarView = findViewById(R.id.CalenderView);

        sellIMG = findViewById(R.id.selIMG);
        int img_id = Utils.getImageByName(selected_emotion, getApplicationContext());
        sellIMG.setImageResource(img_id);

//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", false);
//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", true);


//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", false);


//        Log.i(TAG, getdate);

        //DB에 저장 된다면 이렇게..?

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // 중간에 뒤로 갈건지 여부 묻기
                startActivity(intent);
            }
        });


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Diary newDiary = new Diary(selected_emotion, "2021-03-18", diaryETXT.getText().toString());
                API_Diary.writeDiaryToDB(newDiary, WriteActivity.this);

                CalendarView.markDate(new DateData(IntdateYear, IntdateMonth, IntdateDay).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED)));

//                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

            }
        });
    }

}