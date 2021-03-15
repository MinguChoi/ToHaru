package com.example.toharu;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class API_Post {

//    private static final String TAG = "ToHaru";
//    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    static FirebaseDatabase DB_REF = FirebaseDatabase.getInstance();
//    static DatabaseReference DB_POSTS = DB_REF.getReference("posts");

    public static void wirtePost(Post post) {
        // Save post into DB
        Utils.DB_POSTS.setValue(post);
        // Sync with user info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        Utils.DB_USERS.child(uid).child("posts").setValue()
    }
}
