package com.example.toharu.API;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.toharu.Model.Advice;
import com.example.toharu.Model.Diary;
import com.example.toharu.OnCompletion;
import com.example.toharu.Model.User;
import com.example.toharu.Utils.Utils;
import com.example.toharu.WriteActivity;
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
        Log.d(Utils.TAG, "Fetch data start");
        Utils.DB_ADVICES.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d(Utils.TAG, "Fetch data start and go to db - " + task.getResult().getValue());
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else{
                    Log.d(Utils.TAG, "Fetch data in success else condition");
                    DataSnapshot dataSnapshot = task.getResult();
                    for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        Advice advice = new Advice((HashMap)(childSnapshot.getValue()));
                        Log.d(Utils.TAG, "check advice from db - " + advice.getMood() + " " + advice.getMsg());
                        if(advice.getMood().toLowerCase().equals(emotion.toLowerCase())) {
                            advices.add(advice);
                            Log.d(Utils.TAG, "Fetch advices size : " + advices.size());
                        }
                    }
                    completion.onCompletion(advices);
                    //WriteActivity.setAdvice(advices.get(0).getMsg());
                    Log.d(Utils.TAG, "Fetch done advices - " + advices.isEmpty());
                }
            }
        });
    }
}
