package com.example.toharu;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class API_Advice {

    public static void fetchAdvice(final OnCompletion completion){
        User currentUser = User.getInstance();
        List<String> postsUid = currentUser.getPosts();
        List<Post> posts = new ArrayList<Post>();

        Utils.DB_ADVICES.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Post post = new Post(childSnapshot.getKey(), (HashMap)(childSnapshot.getValue()));
                    Log.d(Utils.TAG, "check post value from db - " + childSnapshot.getValue());
                    Log.d(Utils.TAG, "check post uid from db - " + post.getUid());
                    if(postsUid.contains(post.getUid())) {
                        posts.add(post);
                    }
                }
                completion.onCompletion(posts);
                Log.d(Utils.TAG, "check post uid from userInfo - " + postsUid.get(0).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
