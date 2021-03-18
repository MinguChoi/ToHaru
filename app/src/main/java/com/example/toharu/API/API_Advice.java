package com.example.toharu.API;

import android.util.Log;

import com.example.toharu.Model.Diary;
import com.example.toharu.OnCompletion;
import com.example.toharu.Model.User;
import com.example.toharu.Utils.Utils;
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
        List<Diary> diaries = new ArrayList<Diary>();

        Utils.DB_ADVICES.addListenerForSingleValueEvent(new ValueEventListener() {
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
