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
    private TextView    day_Write_TXT;
    private TextView    diaryArea_Write_ETXT;
    private ImageView   emotion_Write_IMG;
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
        day_Write_TXT = findViewById(R.id.day_Write_TXT);
        diaryArea_Write_ETXT = findViewById(R.id.diaryArea_Write_ETXT);
        emotion_Write_IMG = findViewById(R.id.emotion_Write_IMG);

    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 화면에 작성한 Diary 보여주기
    //----------------------------------------------------------------------------------
    public void displayDiary(){
        Intent intent = getIntent();
        Diary theDiary = (Diary)intent.getSerializableExtra("diary");

        day_Write_TXT.setText(theDiary.getDate());
        diaryArea_Write_ETXT.setText(theDiary.getContent());
        int imgId = Utils.getImageByName(theDiary.getMood(), getApplicationContext());
        emotion_Write_IMG.setImageResource(imgId);
    }
    //----------------------------------------------------------------------------------


}