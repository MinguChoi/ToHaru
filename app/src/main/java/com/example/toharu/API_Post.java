package com.example.toharu;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class API_Post {
    public static void writePostToDB(Post post, Activity ctx) {
        // Save post into DB
        DatabaseReference newPostRef = Utils.DB_POSTS.push();
        newPostRef.setValue(post);
        // Sync with user info
        User currentUser = User.getInstance();
        currentUser.addPost(newPostRef.getKey());
        // update user info in DB
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        HashMap updates = new HashMap();
        updates.put("posts", currentUser.getPosts());
        Utils.DB_USERS.child(user.getUid()).updateChildren(updates);
        // back to the calendar activity
        Intent intent = new Intent(ctx, CalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
    }

}
