package com.example.toharu.Model;

import android.util.Log;

import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class User {

    private static User single_instance = null;
    private String name;
    private String email;
    private List<String> diaries;

    public User() {
        this.diaries = new ArrayList<>();
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.diaries = new ArrayList<>();
    }

    public User(HashMap<String, Object> map) {
        this.name = map.get("name").toString();
        this.email = map.get("email").toString();
        this.diaries = (ArrayList<String>) map.get("diaries");
    }

    public static User getInstance() {
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public List<String> getDiaries() {
        return diaries;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) { this.email = email; }
    public void setDiaries(List<String> diaries) {
        this.diaries = new ArrayList<>();
        this.diaries.addAll(diaries);
    }
    public void addDiary(String diary_id) {
        if (this.diaries == null) {
            this.diaries = new ArrayList<String>();
        }
        this.diaries.add(diary_id);
    }
}
