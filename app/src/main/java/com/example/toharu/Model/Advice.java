package com.example.toharu.Model;

import com.example.toharu.Utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Advice {
    private String mood;
    private String advice;

    public Advice(){ }

    public Advice(String mood, String advice){
        this.mood = mood;
        this.advice = advice;
    }

    public String getMood(){return mood;}
    public String getAdvice(){return advice;}
}
