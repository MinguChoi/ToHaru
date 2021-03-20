package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class WelcomActivity extends AppCompatActivity {

    // for checking github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);


        //------------------------------------------------------------------------------------------
        // 초기 화면 지속시간 설정
        //------------------------------------------------------------------------------------------
        Handler hand = new Handler();

        hand.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(WelcomActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        }, 1000);
        //------------------------------------------------------------------------------------------


    }


}