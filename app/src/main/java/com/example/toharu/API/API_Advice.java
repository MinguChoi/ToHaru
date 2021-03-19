package com.example.toharu.API;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;
import com.example.toharu.OnCompletion;
import com.example.toharu.Model.User;
import com.example.toharu.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class API_Advice {

    public static void fetchAdvice(String emotion, final OnCompletion completion){
        List<Advice> advices = new ArrayList<>();

        Utils.DB_ADVICES.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for(DataSnapshot childSnapshot: task.getResult().getChildren()) {
                        Advice advice = new Advice((HashMap)(childSnapshot.getValue()));
                        if(advice.getMood() == emotion) {
                            advices.add(advice);
                        }
                    }
                    completion.onCompletion(advices);
                }
            }
        });
    }
}
