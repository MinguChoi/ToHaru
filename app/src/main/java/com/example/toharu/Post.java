package com.example.toharu;

import java.util.Dictionary;
import java.util.HashMap;

public class Post {

    private String uid;
    private String mood;
    private String date;
    private String content;

    public Post() { }

    public Post(String mood, String date, String content) {
        this.mood = mood;
        this.date = date;
        this.content = content;
    }
    public Post(String uid, HashMap<String, String> map) {
        this.uid = uid;
        this.mood = map.get("mood");
        this.date = map.get("date");;
        this.content = map.get("content");
    }

    public String getUid() {return uid;}
    public String getMood() {return mood;}
    public String getDate() {return date;}
    public String getContent() {return content;}

}
