package com.example.toharu.API;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.toharu.CalendarActivity;
import com.example.toharu.Model.Diary;
import com.example.toharu.OnCompletion;
import com.example.toharu.Model.User;
import com.example.toharu.Utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class API_Post {
    public static void writePostToDB(Diary diary, Activity ctx) {
        // Save diary into DB
        DatabaseReference newPostRef = Utils.DB_POSTS.push();
        newPostRef.setValue(diary);
        // Sync with user info
        User currentUser = User.getInstance();
        currentUser.addPost(newPostRef.getKey());
//        Log.d(Utils.TAG, "new diary key : " + newPostRef.getKey());
//        Log.d(Utils.TAG, "check if user adds diary uid : " + currentUser.getPosts().get(0));
        // update user info in DB
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        HashMap updates = new HashMap();
        updates.put("diaries", currentUser.getPosts());
        Utils.DB_USERS.child(user.getUid()).updateChildren(updates);
        // back to the calendar activity
        Intent intent = new Intent(ctx, CalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
    }

    public static void fetchPosts(final OnCompletion completion){
        User currentUser = User.getInstance();
        List<String> postsUid = currentUser.getPosts();
        List<Diary> diaries = new ArrayList<Diary>();

        Utils.DB_POSTS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Diary diary = new Diary(childSnapshot.getKey(), (HashMap)(childSnapshot.getValue()));
                    Log.d(Utils.TAG, "check diary value from db - " + childSnapshot.getValue());
                    Log.d(Utils.TAG, "check diary uid from db - " + diary.getUid());
                    if(postsUid.contains(diary.getUid())) {
                        diaries.add(diary);
                    }
                }
                completion.onCompletion(diaries);
                Log.d(Utils.TAG, "check post uid from userInfo - " + postsUid.get(0).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
