package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Authentication extends AppCompatActivity {

    private final String TAG = "ToHaru";

    //private FirebaseAuth mAuth;
    private EditText id_etxt, pw_etxt;
    private Button signUp_btn, signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_authentication);

        init();
    }

    public void init() {
        // Initialize Firebase Auth
        //mAuth = FirebaseAuth.getInstance();

        id_etxt = findViewById(R.id.id_etxt);
        pw_etxt = findViewById(R.id.pw_etxt);
        signUp_btn = findViewById(R.id.signUp_btn);
        signIn_btn = findViewById(R.id.signIn_btn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isEmpty(id_etxt) || Utils.isEmpty(pw_etxt)) {
                    Log.i(TAG, "sign up - failure");
                }
                else {
                    API_Auth.createUser(id_etxt.getText().toString(), pw_etxt.getText().toString(), Authentication.this);
                    Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                    startActivity(intent);
                }
            }
        });
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isEmpty(id_etxt) || Utils.isEmpty(pw_etxt)) {
                    Log.i(TAG, "sign up - failure");
                }
                else {
                    API_Auth.signIn(id_etxt.getText().toString(), pw_etxt.getText().toString(), Authentication.this);
                    Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}