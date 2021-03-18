package com.example.toharu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class WriteActivity extends AppCompatActivity {
    private Button backBTN;
    private Button saveBTN;
    private EditText diaryETXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        init();

    }

    public void init(){
        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryETXT = findViewById(R.id.diary_writeETXT);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post newPost = new Post("Happy", "2021-03-16", diaryETXT.getText().toString());
                showDialog();
//                API_Post.writePostToDB(newPost, WriteActivity.this);
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
                        Post newPost = new Post("Happy", "2021-03-16", diaryETXT.getText().toString());
                        API_Post.writePostToDB(newPost, WriteActivity.this);
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}