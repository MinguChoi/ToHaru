package com.example.toharu.Model;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.HashMap;

public class Diary implements Serializable {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private String uid;
    private String mood;
    private String date;
    private String content;

    public Diary() { }

    //----------------------------------------------------------------------------------
    // Diary Getter - Setter
    //----------------------------------------------------------------------------------
    public Diary(String mood, String date, String content) {
        this.mood = mood;
        this.date = date;
        this.content = content;
    }

    public Diary(String uid, HashMap<String, String> map) {
        this.uid = uid;
        this.mood = map.get("mood");
        this.date = map.get("date");;
        this.content = map.get("content");
    }
    //----------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    // 기타 Getter
    //----------------------------------------------------------------------------------
    public String getUid() {return uid;}
    public String getMood() {return mood;}
    public String getDate() {return date;}
    public String getContent() {return content;}
    //----------------------------------------------------------------------------------
}
