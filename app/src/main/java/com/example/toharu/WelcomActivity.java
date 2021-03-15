package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomActivity extends AppCompatActivity {

    // for checking github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClick(View v){
        Intent intent = new Intent(getApplicationContext(), Authentication.class);
        startActivity(intent);
    }
}