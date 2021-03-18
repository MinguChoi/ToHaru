package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class EmotionActivity extends AppCompatActivity {

    private ImageButton joy_img,happy_img,proud_img,tired_img,sadness_img,angry_img,anxiety_img,gloom_img,peaceul_img,next_btn;

    private final boolean   D = true;
    private final String    TAG = "EmotionActivity";

    public String           getdate;

    private int          selected_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        init();

    }


    public void init() {
        joy_img = findViewById(R.id.joy_img);
        happy_img = findViewById(R.id.happy_img);
        proud_img = findViewById(R.id.proud_img);
        tired_img = findViewById(R.id.tired_img);
        sadness_img = findViewById(R.id.sadness_img);
        angry_img = findViewById(R.id.angry_img);
        anxiety_img = findViewById(R.id.anxiety_img);
        gloom_img = findViewById(R.id.gloom_img);
        peaceul_img = findViewById(R.id.peaceful_img);
        next_btn = findViewById(R.id.next_btn);

        joy_img.setTag(R.drawable.joy);
        happy_img.setTag(R.drawable.happy);
        proud_img.setTag(R.drawable.proud);
        tired_img.setTag(R.drawable.tired);
        sadness_img.setTag(R.drawable.sadness);
        angry_img.setTag(R.drawable.angry);
        anxiety_img.setTag(R.drawable.anxiety);
        gloom_img.setTag(R.drawable.gloom);
        peaceul_img.setTag(R.drawable.peaceful);

        getdate = getIntent().getStringExtra("mDate"); // 날짜 받아오기
        Log.i(TAG, "get Date in EmotionActivity => " + getdate);


    }

        //next btn 클릭시 -> diary activity
    public void selected_next_move(View v){
        switch (v.getId()){
            case R.id.next_btn:
                Intent intent = new Intent(EmotionActivity.this, WriteActivity.class);
                intent.putExtra("emotion_img", selected_img);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                selected_img = (int) v.getTag();
                break;

        }
    }

}