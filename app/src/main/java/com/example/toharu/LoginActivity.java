package com.example.toharu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private Button           loginBTN;
    private EditText         id, pw;
    private Button         signup_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init(){
        loginBTN = findViewById(R.id.loginBTN);
        id = findViewById(R.id.id_login);
        pw = findViewById(R.id.pw_login);
        signup_BTN = findViewById(R.id.signup_BTN);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(id) || Utils.isEmpty(pw)) {
                    Utils.toastError(getApplicationContext(), "fill out everything :)");
                    Log.d(Utils.TAG, "login button - failure missing id or pw");
                } else {
                    // Firebase sign in
                    API_Auth.signIn(id.getText().toString(), pw.getText().toString(), LoginActivity.this);
                    // move to the calendar activity
                    Log.d(Utils.TAG, "login button - success");
                }
            }
        });

        signup_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}