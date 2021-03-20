package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class EmotionActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private ImageButton joy_Emotion_IMG,
                        happy_Emotion_IMG,
                        proud_Emotion_IMG,
                        tired_Emotion_IMG,
                        sadness_Emotion_IMG,
                        angry_Emotion_IMG,
                        anxiety_Emotion_IMG, 
                        gloom_Emotion_IMG,
                        peaceful_Emotion_IMG,
                        next_Emotion_BTN;

    private final boolean   D = true;
    private final String    TAG = "EmotionActivity";

    public String           getdate;

    private String          selected_img;
    //----------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        init();
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 초기화
    //----------------------------------------------------------------------------------
    public void init() {
        joy_Emotion_IMG = findViewById(R.id.joy_Emotion_IMG);
        happy_Emotion_IMG = findViewById(R.id.happy_Emotion_IMG);
        proud_Emotion_IMG = findViewById(R.id.proud_Emotion_IMG);
        tired_Emotion_IMG = findViewById(R.id.tired_Emotion_IMG);
        sadness_Emotion_IMG = findViewById(R.id.sadness_Emotion_IMG);
        angry_Emotion_IMG = findViewById(R.id.angry_Emotion_IMG);
        anxiety_Emotion_IMG = findViewById(R.id.anxiety_Emotion_IMG);
        gloom_Emotion_IMG = findViewById(R.id.gloom_Emotion_IMG);
        peaceful_Emotion_IMG = findViewById(R.id.peaceful_Emotion_IMG);
        next_Emotion_BTN = findViewById(R.id.next_Emotion_BTN);

        joy_Emotion_IMG.setTag("joy");
        happy_Emotion_IMG.setTag("happy");
        proud_Emotion_IMG.setTag("proud");
        tired_Emotion_IMG.setTag("tired");
        sadness_Emotion_IMG.setTag("sadness");
        angry_Emotion_IMG.setTag("angry");
        anxiety_Emotion_IMG.setTag("anxiety");
        gloom_Emotion_IMG.setTag("gloomy");
        peaceful_Emotion_IMG.setTag("peaceful");

        getdate = getIntent().getStringExtra("mDate"); // 날짜 받아오기
        Log.i(TAG, "get Date in EmotionActivity => " + getdate);
    }
    //----------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------
    // next btn 클릭시 -> diary activity
    //----------------------------------------------------------------------------------
    public void selected_next_move(View v){
        switch (v.getId()){
            case R.id.next_Emotion_BTN:
                Intent intent = new Intent(EmotionActivity.this, WriteActivity.class);
                intent.putExtra("emotion_img", selected_img);
                intent.putExtra("mDate2", getdate);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                // 버튼 누른 이미지 계속 유지되도록
                v.setSelected(!v.isSelected());
                selected_img = (String) v.getTag();
                if(v.isSelected())
                    //setSelected(false);
                break;
        }
    }
    //----------------------------------------------------------------------------------


}