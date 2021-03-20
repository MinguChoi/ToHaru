package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toharu.API.API_Auth;

public class SettingActivity extends AppCompatActivity {
    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private TextView                      logout_Setting_TXT;
    //----------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
    }

    //----------------------------------------------------------------------------------
    // 초기화 및 로그 아웃 기능!!!!!!!!!!!!
    //----------------------------------------------------------------------------------
    public void init(){
        logout_Setting_TXT = findViewById(R.id.logout_Setting_TXT);
        logout_Setting_TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API_Auth.signOut();
                Intent intent = new Intent(SettingActivity.this, WelcomActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    //----------------------------------------------------------------------------------
}