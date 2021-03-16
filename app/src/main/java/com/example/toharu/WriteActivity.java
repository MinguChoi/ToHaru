package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WriteActivity extends AppCompatActivity {

    private final boolean   D = true;
    private final String    TAG = "WriteActivity";

    private Button backBTN;
    private Button saveBTN;

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
                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}