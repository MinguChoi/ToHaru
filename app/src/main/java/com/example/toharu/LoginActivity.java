package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toharu.API.API_Auth;
import com.example.toharu.Utils.Utils;

import org.w3c.dom.Text;

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


        InputMethodManager imm =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);


        pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE)
                {
                    //실제 키보드를 내리는 이벤트
                    imm.hideSoftInputFromWindow(pw.getWindowToken(),0);
                }
                return false;
            }
        });




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