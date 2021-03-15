package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    private Button backBTN;
    private Button saveBTN;

    private EditText diaryTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        backBTN = findViewById(R.id.backBTN);
        saveBTN = findViewById(R.id.saveBTN);
        diaryTXT = findViewById(R.id.diaryTXT);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isEmpty(diaryTXT)) {
                    Toast.makeText(getApplication(), "Tell me how your day was", Toast.LENGTH_LONG).show();
                } else {
                    Post newPost = new Post("Happy", "2021-03-15", diaryTXT.getText().toString());

                }
            }
        });
    }

}