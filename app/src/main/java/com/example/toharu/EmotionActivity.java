package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EmotionActivity extends AppCompatActivity {

    private ImageButton joy_img,happy_img,proud_img,tired_img,sadness_img,angry_img,anxiety_img,gloom_img,peaceful_img,next_btn;

    private List<ImageButton> imageList;

    private final boolean   D = true;
    private final String    TAG = "EmotionActivity";

    public String           getdate;

    private String          selected_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        init();

    }


    public void init() {
        joy_img = findViewById(R.id.joy_img);
        happy_img = findViewById(R.id.happy_img);
        proud_img = findViewById(R.id.proud_img);
        tired_img = findViewById(R.id.tired_img);
        sadness_img = findViewById(R.id.sadness_img);
        angry_img = findViewById(R.id.angry_img);
        anxiety_img = findViewById(R.id.anxiety_img);
        gloom_img = findViewById(R.id.gloom_img);
        peaceful_img = findViewById(R.id.peaceful_img);
        next_btn = findViewById(R.id.next_btn);

        joy_img.setTag("joy");
        happy_img.setTag("happy");
        proud_img.setTag("proud");
        tired_img.setTag("tired");
        sadness_img.setTag("sadness");
        angry_img.setTag("angry");
        anxiety_img.setTag("anxiety");
        gloom_img.setTag("gloomy");
        peaceful_img.setTag("peaceful");

        imageList = new ArrayList<>();
        imageList.add(joy_img);
        imageList.add(happy_img);
        imageList.add(proud_img);
        imageList.add(tired_img);
        imageList.add(sadness_img);
        imageList.add(angry_img);
        imageList.add(anxiety_img);
        imageList.add(gloom_img);
        imageList.add(peaceful_img);

        getdate = getIntent().getStringExtra("mDate"); // 날짜 받아오기
        Log.i(TAG, "get Date in EmotionActivity => " + getdate);

    }

    public void setFalseExcpetFor(String selected) {
        for(int i=0; i<imageList.size(); i++) {
            String imgTag = (String) imageList.get(i).getTag();
            if( !imgTag.equals(selected) ) {
                imageList.get(i).setSelected(false);
            }
        }
    }

    public boolean isSelected() {
        for(int i=0; i<imageList.size(); i++) {
            if (imageList.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }
        //next btn 클릭시 -> diary activity
    public void selected_next_move(View v){
        switch (v.getId()){
            case R.id.next_btn:
                if(selected_img == null || !isSelected()){
                    Utils.toastError(getApplicationContext(), "감정을 선택하세요!");
                    //Toast.makeText(EmotionActivity.this, "감정을 선택하세요!",Toast.LENGTH_SHORT).show();
                } else{
                        Intent intent = new Intent(EmotionActivity.this, WriteActivity.class);
                        intent.putExtra("emotion_img", selected_img);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                }
                break;

            default:
                // 이모티콘이 선택되었을때
                selected_img = (String) v.getTag();
                // 선택 이모티콘 제외 나머지 set false
                setFalseExcpetFor(selected_img);
                // 선택 이모티콘 set false->true, set true -> false
                v.setSelected(!v.isSelected());

                break;
        }
    }
}