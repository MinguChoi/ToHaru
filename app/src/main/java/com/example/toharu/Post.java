package com.example.toharu;

import java.util.Dictionary;

public class Post {

    private String uid;
    private String mood;
    private String date;
    private String content;

    public Post(String uid, Dictionary<String, String> dictionary) {
        this.uid = uid;
        this.mood = dictionary.get("mood");
        this.date = dictionary.get("date");;
        this.content = dictionary.get("content");
    }

    public String getUid() {return uid;}
    public String getMood() {return mood;}
    public String getDate() {return date;}
    public String getContent() {return content;}

}
