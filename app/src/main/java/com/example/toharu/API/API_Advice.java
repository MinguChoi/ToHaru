package com.example.toharu.API;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.toharu.Model.Advice;
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
        String advice;

        Utils.DB_ADVICES.child("advice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(Advice.class) != null){
                }else{
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(Utils.TAG,"advice error");
            }
        });
    }
}
