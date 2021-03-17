package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toharu.API.API_Auth;

public class LoginActivity extends AppCompatActivity {
    private Button           loginBTN;
    private EditText         id, pw;
    private TextView         signupTXT;

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
        signupTXT = findViewById(R.id.signupTXT);

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

        signupTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}