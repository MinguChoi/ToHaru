package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private Button      login_login_BTN;
    private EditText    email_login_ETXT, password_login_ETXT;
    private Button      signup_login_BTN;
    //----------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }
    //----------------------------------------------------------------------------------
    // 초기화
    //----------------------------------------------------------------------------------
    public void init(){
        login_login_BTN = findViewById(R.id.login_login_BTN);
        email_login_ETXT = findViewById(R.id.email_login_ETXT);
        password_login_ETXT = findViewById(R.id.password_login_ETXT);
        signup_login_BTN = findViewById(R.id.signup_login_BTN);
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // 변수 값 초기화
        //----------------------------------------------------------------------------------
        InputMethodManager imm =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // password 리스터 등록
        //----------------------------------------------------------------------------------
        password_login_ETXT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE)
                {
                    //실제 키보드를 내리는 이벤트
                    imm.hideSoftInputFromWindow(password_login_ETXT.getWindowToken(),0);
                }
                return false;
            }
        });
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // login 버튼 리스너 등록
        //----------------------------------------------------------------------------------
        login_login_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(email_login_ETXT) || Utils.isEmpty(password_login_ETXT)) {
                    Utils.toastError(getApplicationContext(), "이메일 또는 비밀번호가 입력되지 않았어요!! :(");
                    Log.d(Utils.TAG, "login button - failure missing id or pw");
                } else {
                    // Firebase sign in
                    API_Auth.signIn(email_login_ETXT.getText().toString(), password_login_ETXT.getText().toString(), LoginActivity.this);
                    // move to the calendar activity
                    Log.d(Utils.TAG, "login button - success");
                }
            }
        });
        //----------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------
        // 회원가입 버튼 리스너 등록
        //----------------------------------------------------------------------------------
        signup_login_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------------------------
    }
}