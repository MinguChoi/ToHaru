package com.example.toharu;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class User {

    private static User single_instance = null;
    private String name;
    private String email;
    private List<String> posts;

    public User() {
        this.posts = new ArrayList<String>();
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.posts = new ArrayList<String>();
    }

    public User(Dictionary<String, Object> dictionary) {
        this.name = dictionary.get("name").toString();
        this.email = dictionary.get("email").toString();
        this.posts = (ArrayList<String>) dictionary.get("posts");
    }

    public static User getInstance() {
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public List<String> getPosts() {return posts;}

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addPost(String post_id) {
        this.posts.add(post_id);
    }
}
