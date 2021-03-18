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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toharu.API.API_Advice;
import com.example.toharu.API.API_Auth;
import com.example.toharu.API.API_Diary;
import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;

public class WriteActivity extends AppCompatActivity {

    private final boolean D = true;
    private final String TAG = "WriteActivity";

    private Button backBTN;
    private Button saveBTN;
    private Button btnAccept;
    private EditText diaryETXT;
    private Dialog customDialog;

    private boolean getCheckWR;
    public String getdate;
    private int getdateMONTH;
    private String getdateDAY;

    private int     image_rsrc;
    private ImageView sellIMG, closePopupIMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getdate = getIntent().getStringExtra("mDate");
        image_rsrc = getIntent().getIntExtra("emotion_img", 0);
        Log.d(TAG, "dd"+getdate);
        init();

    }

    public void init() {
        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryETXT = findViewById(R.id.diary_writeETXT);

        sellIMG = findViewById(R.id.selIMG);

        sellIMG.setImageResource(image_rsrc);

        customDialog = new Dialog(this);


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
//                Diary newDiary = new Diary("Happy", "2021-03-16", diaryETXT.getText().toString());
//                API_Diary.writeDiaryToDB(newDiary, WriteActivity.this);
                Show();

//                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });
    }

    public void Show(){
        customDialog.setContentView(R.layout.custom_dialog);
        closePopupIMG = (ImageView) customDialog.findViewById(R.id.close_diaIMG);
        btnAccept = (Button) customDialog.findViewById(R.id.diaBTN);

        closePopupIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diary newDiary = new Diary("Happy", "2021-03-16", diaryETXT.getText().toString());
                API_Diary.writeDiaryToDB(newDiary, WriteActivity.this);
                customDialog.dismiss();
            }
        });

        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();
    }
}