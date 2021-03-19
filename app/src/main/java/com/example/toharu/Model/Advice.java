package com.example.toharu.Model;

import com.example.toharu.Utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Advice {
    private String mood;
    private String msg;

    public Advice(){ }

    public Advice(String mood, String advice){
        this.mood = mood;
        this.msg = advice;
    }

    public Advice(HashMap<String, String> map) {
        this.mood = map.get("mood");
        this.msg = map.get("msg");
    }

    public String getMood(){return mood;}
    public String getMsg(){return msg;}
}
