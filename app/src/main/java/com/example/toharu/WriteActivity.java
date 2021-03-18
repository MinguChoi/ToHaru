package com.example.toharu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.toharu.API.API_Advice;
import com.example.toharu.API.API_Post;
import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;

public class WriteActivity extends AppCompatActivity {

    private final boolean   D = true;
    private final String    TAG = "WriteActivity";

    private Button backBTN;
    private Button saveBTN;
    private EditText diaryETXT;

    private boolean getCheckWR;
    public String getdate;
    private int getdateMONTH;
    private String getdateDAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        getdate = getIntent().getStringExtra("mDate");
        Log.d(TAG, getdate);
        init();

    }

    public void init(){
        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryETXT = findViewById(R.id.diary_writeETXT);

//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", false);
//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", true);



//        getCheckWR = getIntent().getBooleanExtra("CheckWRdata", false);


//        Log.i(TAG, getdate);

        //DB에 저장 된다면 이렇게..?

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // 중간에 뒤로 갈건지 여부 묻기
                startActivity(intent);
            }
        });


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diary newDiary = new Diary("Happy", "2021-03-16", diaryETXT.getText().toString());
                showDialog();
//                API_Post.writePostToDB(newDiary, WriteActivity.this);
//                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });
    }

    void showDialog(){
        String msg = "";
        API_Advice.fetchAdvice(new OnCompletion) {
            @Override
                    public void onCompletiong(Object object) {
                msg = ((Advice) object).getMsg;
                AlertDialog
            }
        }
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(WriteActivity.this)
                .setTitle("하루의 위로 한마디...")
                .setMessage("힘내요")
                .setPositiveButton("힘낼게!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Diary newDiary = new Diary("Happy", "2021-03-16", diaryETXT.getText().toString());
                        API_Post.writePostToDB(newDiary, WriteActivity.this);
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}