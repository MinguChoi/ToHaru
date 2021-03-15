package com.example.toharu;

import java.util.Dictionary;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String[] posts;

    public User() {}

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.posts = new String[]{};
    }

    public User(Dictionary<String, Object> dictionary) {
        this.firstName = dictionary.get("firstName").toString();
        this.lastName = dictionary.get("lastName").toString();
        this.email = dictionary.get("email").toString();
        this.posts = (String[])dictionary.get("posts");
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addPost(String post_id) {
        this.posts[posts.length] = post_id;
    }
}
