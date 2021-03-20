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

public class EmotionActivity extends AppCompatActivity {

    private ImageButton joy_img,happy_img,proud_img,tired_img,sadness_img,angry_img,anxiety_img,gloom_img,peaceful_img,next_btn;

    private final boolean   D = true;
    private final String    TAG = "EmotionActivity";

    public String           getdate;

    private String          selected_img;
    private Boolean         isSelected = false;


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

        getdate = getIntent().getStringExtra("mDate"); // 날짜 받아오기
        Log.i(TAG, "get Date in EmotionActivity => " + getdate);

    }

        //next btn 클릭시 -> diary activity
    public void selected_next_move(View v){
        switch (v.getId()){
            case R.id.next_btn:
                if(selected_img == null){
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
//                if(selected_img.equals((String)v.getTag())) {
//                    v.setSelected(!v.isSelected());
//                    selected_img = null;
//                } else {
//                    v.setSelected(!v.isSelected());
//                    switch (selected_img) {
//                        case "joy":
//                            joy_img.setSelected(!joy_img.isSelected());
//                            break;
//                        case "happy":
//                            happy_img.setSelected(!happy_img.isSelected());
//                            break;
//                        case "proud":
//                            proud_img.setSelected(!proud_img.isSelected());
//                            break;
//                    }
//                    selected_img = (String) v.getTag();
//                }

                    // 한 번 클릭했을 때 (감정 버튼이 선택 되었을 때)
                    if (isSelected == false) {
                            v.setSelected(!v.isSelected()); // 버튼 이미지 계속 유지
                            selected_img = (String) v.getTag();
                            isSelected = true;
                        }
                    // 두 번 클릭했을 때 (감정 버튼 선택 해제했을 때)
                    else {
                        v.setSelected(!v.isSelected()); // 버튼 이미지 계속 유지
                        selected_img = null;
                        isSelected = false;
                    }
                    break;
        }
    }

}