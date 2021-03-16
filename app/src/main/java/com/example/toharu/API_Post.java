package com.example.toharu;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static List<Post> fetchPosts(){
        User currentUser = User.getInstance();
        List<String> postsUid = currentUser.getPosts();
        List<Post> posts = new ArrayList<Post>();

    }
}
