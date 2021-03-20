package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toharu.API.API_Advice;
import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Diary;
import com.example.toharu.Model.User;
import com.example.toharu.Utils.Utils;

import java.util.HashMap;

public class ReadActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private TextView    delete_Read_TXT;
    private EditText    diaryArea_Read_ETXT;
    private ImageView   emotion_Read_IMG;
    private Button      back_Read_BTN, editDone_Read_BTN;
    //----------------------------------------------------------------------------------

    private Diary theDiary;
//    private Diary diaryFromCalendar = (Diary)intent.getSerializableExtra("diary");
//    private Diary diaryFromList = (Diary)intent.getSerializableExtra("diary2");

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
        back_Read_BTN = findViewById(R.id.back_Read_BTN);
        editDone_Read_BTN = findViewById(R.id.editDone_Read_BTN);
        delete_Read_TXT = findViewById(R.id.delete_Read_TXT);

        Intent intent = getIntent();
        if(intent.getStringExtra("previous").equals("calendar")) {
            theDiary = (Diary) intent.getSerializableExtra("diary");
        } else {
            theDiary = (Diary) intent.getSerializableExtra("diary2");
        }
        back_Read_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editDone_Read_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editDone_Read_BTN.getText().toString().equals("수정")) {
                    editDone_Read_BTN.setText("확인");
                    delete_Read_TXT.setText("삭제");
                    diaryArea_Read_ETXT.setFocusable(true);
                    diaryArea_Read_ETXT.setClickable(true);

                } else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("mood", theDiary.getMood());
                    map.put("date", theDiary.getDate());
                    map.put("content", diaryArea_Read_ETXT.getText().toString());

                    Diary update = new Diary(theDiary.getUid(), map);
                    Log.d(Utils.TAG,"diary -"+update.getContent());
                    API_Diary.updateDiary(update);
                    finish();
                }
            }
        });

        delete_Read_TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API_Diary.deleteDiary(theDiary);
                finish();
            }
        });
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 화면에 작성한 Diary 보여주기
    //----------------------------------------------------------------------------------
    public void displayDiary(){

        diaryArea_Read_ETXT.setText(theDiary.getContent());
        int imgId = Utils.getImageByName(theDiary.getMood(), getApplicationContext());
        emotion_Read_IMG.setImageResource(imgId);
    }
    //----------------------------------------------------------------------------------


}