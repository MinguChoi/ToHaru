package com.example.toharu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toharu.API.API_Advice;
import com.example.toharu.API.API_Auth;
import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WriteActivity extends AppCompatActivity {

    private final boolean D = true;
    private final String TAG = "WriteActivity";

    private ImageButton backBTN;
    private ImageButton saveBTN;
    private Button btnAccept;
    private EditText diaryETXT;
    private Dialog customDialog;

    private TextView adviceTXT;

    private boolean getCheckWR;
    public String getdate;
    private int getdateMONTH;
    private String getdateDAY;

    private String    selected_emotion;
    private ImageView sellIMG;

    private List<Advice> adviceList = new ArrayList<>();
    private Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getdate = getIntent().getStringExtra("mDate");
        selected_emotion = getIntent().getStringExtra("emotion_img");
        Log.i(TAG, "dd"+getdate);
        init();

    }

    public void init() {
        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryETXT = findViewById(R.id.diary_writeETXT);
        customDialog = new Dialog(this);
        sellIMG = findViewById(R.id.selIMG);

        int img_id = Utils.getImageByName(selected_emotion, getApplicationContext());
        sellIMG.setImageResource(img_id);


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
                Show();
//                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });
    }

    // 팝업창 띄우는 함수
    public void Show(){
        //selected_emotion = getIntent().getStringExtra("emotion_img");
        customDialog.setContentView(R.layout.custom_dialog);
        btnAccept = (Button) customDialog.findViewById(R.id.diaBTN);
        adviceTXT = customDialog.findViewById(R.id.adviceTXT);

        API_Advice.fetchAdvice(selected_emotion, new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                adviceList = (List<Advice>) object;
                Log.d(Utils.TAG, "adviceList size: " + adviceList.size());
                //adviceTXT.setText(adviceList.get(0).getMsg());
                adviceTXT.setText(adviceList.get(ran.nextInt((adviceList.size()))).getMsg());
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diary newDiary = new Diary(selected_emotion, "2021-03-16", diaryETXT.getText().toString());
                API_Diary.writeDiaryToDB(newDiary, WriteActivity.this);
                customDialog.dismiss();
            }
        });

        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();
    }
}