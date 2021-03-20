package com.example.toharu.Model;

import com.example.toharu.Utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Advice {
    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private String mood;
    private String msg;

    public Advice(){ }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // Advice Getter - Setter
    //----------------------------------------------------------------------------------
    public Advice(String mood, String advice){
        this.mood = mood;
        this.msg = advice;
    }

    public Advice(HashMap<String, String> map) {
        this.mood = map.get("mood");
        this.msg = map.get("msg");
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 기타 Getter
    //----------------------------------------------------------------------------------
    public String getMood(){return mood;}
    public String getMsg(){return msg;}
    //----------------------------------------------------------------------------------

}
