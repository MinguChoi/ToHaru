package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

public class ReadActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private TextView    day_Read_TXT;
    private TextView    diaryArea_Read_ETXT;
    private ImageView   emotion_Read_IMG;
    //----------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        init();
        displayDiary();
    }

    //----------------------------------------------------------------------------------
    // 초기화
    //----------------------------------------------------------------------------------
    public void init(){
//        day_Read_TXT = findViewById(R.id.day_Read_TXT);
        diaryArea_Read_ETXT = findViewById(R.id.diaryArea_Read_ETXT);
        emotion_Read_IMG = findViewById(R.id.emotion_Read_IMG);

    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 화면에 작성한 Diary 보여주기
    //----------------------------------------------------------------------------------
    public void displayDiary(){
        Intent intent = getIntent();
        Diary theDiary = (Diary)intent.getSerializableExtra("diary");

//        day_Read_TXT.setText(theDiary.getDate());
        diaryArea_Read_ETXT.setText(theDiary.getContent());
        int imgId = Utils.getImageByName(theDiary.getMood(), getApplicationContext());
        emotion_Read_IMG.setImageResource(imgId);
    }
    //----------------------------------------------------------------------------------


}