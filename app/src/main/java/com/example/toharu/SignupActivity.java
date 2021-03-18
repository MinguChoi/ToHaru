package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toharu.API.API_Auth;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, password, password2;
    private TextView signUp_TXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
    }

    public void init() {
        signUp_TXT = findViewById(R.id.signupTXT);
        name = findViewById(R.id.name_singUp);
        email = findViewById(R.id.email_signUp);
        password = findViewById(R.id.pw_signUp);
        password2 = findViewById(R.id.pw_signUp2);

        signUp_TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(name) || Utils.isEmpty(email) || Utils.isEmpty(password) || Utils.isEmpty(password2)) {
                    Utils.toastError(getApplicationContext(), "fill out everything :)");
                } else {
                    if (password.getText().toString().equals(password2.getText().toString())) {
                        // Firebase sign up
                        API_Auth.createUser(name.getText().toString(),
                                            email.getText().toString(),
                                            password.getText().toString(),
                                            SignupActivity.this);
                    } else {
                        Utils.toastError(getApplicationContext(), "check your password");
                    }
                }
            }
        });
    }
}