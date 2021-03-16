package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                API_Post.writePostToDB(newPost, WriteActivity.this);
//                Intent intent = new Intent(WriteActivity.this, CalendarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });
    }

}