package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import sun.bob.mcalendarview.vo.DateData;

public class ReadActivity extends AppCompatActivity {

    private TextView    dateTXT;
    private TextView    contentTXT;
    private ImageView   img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        init();

    }

    public void init(){
        dateTXT = findViewById(R.id.dayTXT);
        contentTXT = findViewById(R.id.diary_writeETXT);
        img = findViewById(R.id.selIMG);

        Intent intent = getIntent();
        Diary theDiary = (Diary)intent.getSerializableExtra("diary");

        dateTXT.setText(theDiary.getDate());
        contentTXT.setText(theDiary.getContent());
        int imgId = Utils.getImageByName(theDiary.getMood(), getApplicationContext());
        img.setImageResource(imgId);
    }
}