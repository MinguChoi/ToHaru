package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toharu.API.API_Auth;
import com.example.toharu.Utils.Utils;

public class SignupActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private EditText name_singUp_ETXT, email_signUp_ETXT, pw_signUp_ETXT, checkPw_signUp_ETXT;
    private TextView signUpDone_signUp_TXT;
    //----------------------------------------------------------------------------------
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
    }


    //----------------------------------------------------------------------------------
    // 초기화
    //----------------------------------------------------------------------------------
    public void init() {
        signUpDone_signUp_TXT = findViewById(R.id.signUpDone_signUp_TXT);
        name_singUp_ETXT = findViewById(R.id.name_singUp_ETXT);
        email_signUp_ETXT = findViewById(R.id.email_signUp_ETXT);
        pw_signUp_ETXT = findViewById(R.id.pw_signUp_ETXT);
        checkPw_signUp_ETXT = findViewById(R.id.checkPw_signUp_ETXT);
        //----------------------------------------------------------------------------------

        
        //----------------------------------------------------------------------------------
        // 회원가입 이벤트
        //----------------------------------------------------------------------------------
        signUpDone_signUp_TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(name_singUp_ETXT) || Utils.isEmpty(email_signUp_ETXT) || Utils.isEmpty(pw_signUp_ETXT) || Utils.isEmpty(checkPw_signUp_ETXT)) {
                    Utils.toastError(getApplicationContext(), "이메일 또는 비밀번호가 입력되지 않았어요!! :(");
                } else {
                    if (pw_signUp_ETXT.getText().toString().equals(checkPw_signUp_ETXT.getText().toString())) {
                        // Firebase sign up
                        API_Auth.createUser(name_singUp_ETXT.getText().toString(),
                                            email_signUp_ETXT.getText().toString(),
                                            pw_signUp_ETXT.getText().toString(),
                                            SignupActivity.this);
                    } else {
                        Utils.toastError(getApplicationContext(), "비밀번호가 달라요!! :(");
                    }
                }
            }
        });
        //----------------------------------------------------------------------------------
    }
}