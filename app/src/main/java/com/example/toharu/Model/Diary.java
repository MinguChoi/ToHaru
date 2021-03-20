package com.example.toharu.Model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Diary {

    private String uid;
    private String mood;
    private String date;
    private String content;

    public Diary() { }

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

    public String getUid() {return uid;}
    public String getMood() {return mood;}
    public String getDate() {return date;}
    public String getContent() {return content;}

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("mood", mood);
        result.put("date", date);
        result.put("content", content);

        return result;
    }
}
