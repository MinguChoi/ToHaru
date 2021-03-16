package com.example.toharu;

import java.util.Dictionary;

public class User {

    private static User single_instance = null;
    private String name;
    private String email;
    private String[] posts;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.posts = new String[]{};
    }

    public User(Dictionary<String, Object> dictionary) {
        this.name = dictionary.get("name").toString();
        this.email = dictionary.get("email").toString();
        this.posts = (String[])dictionary.get("posts");
    }

    public static User getInstance() {
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }

    public String getName() {return name;}
    public String getEmail() {return email;}

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addPost(String post_id) {
        this.posts[posts.length] = post_id;
    }
}
