package com.example.toharu.Model;

import android.util.Log;

import com.example.toharu.Utils.Utils;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class User {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private static User     single_instance = null;
    private String          name;
    private String          email;
    private List<String>    diaries;

    public User() {
        this.diaries = new ArrayList<>();
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // <User> Setter
    //----------------------------------------------------------------------------------
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.diaries = new ArrayList<>();
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // <User> Getter
    //----------------------------------------------------------------------------------
    public User(HashMap<String, Object> map) {
        this.name = map.get("name").toString();
        this.email = map.get("email").toString();
        this.diaries = new ArrayList<>((ArrayList<String>) map.get("diaries"));
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // User 인스턴스 가져오기
    //----------------------------------------------------------------------------------
    public static User getInstance() {
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 기타 Getter - Setter
    //----------------------------------------------------------------------------------
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
        if (diaries != null) {
            this.diaries = diaries;
        }
    }

    //----------------------------------------------------------------------------------
    // 다이어리 추가
    //----------------------------------------------------------------------------------
    public void addDiary(String diary_id) {
        if (this.diaries == null) {
            this.diaries = new ArrayList<String>();
        }
        this.diaries.add(diary_id);
    }
    //----------------------------------------------------------------------------------

}
