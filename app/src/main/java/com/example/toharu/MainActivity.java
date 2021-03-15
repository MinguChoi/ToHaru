package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // for checking github
    // dddddddddddddddddddddddf f
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }
}