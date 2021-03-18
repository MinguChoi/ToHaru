package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EmotionActivity extends AppCompatActivity {

    private ImageButton joy_img,happy_img,proud_img,tired_img,sadness_img,angry_img,anxiety_img,gloom_img,peaceul_img,next_btn;


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
        anxiety_img = findViewById(R.id.angry_img);
        gloom_img = findViewById(R.id.gloom_img);
        peaceul_img = findViewById(R.id.peaceful_img);
        next_btn = findViewById(R.id.next_btn);


        //next btn 클릭시 -> diary activity

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmotionActivity.this,WriteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


    }
}