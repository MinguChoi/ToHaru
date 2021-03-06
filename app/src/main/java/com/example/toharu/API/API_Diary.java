package com.example.toharu.API;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.toharu.CalendarActivity;
import com.example.toharu.Model.Advice;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API_Diary {
    //----------------------------------------------------------------------------------
    // diary 데이터 DB에 작성
    //----------------------------------------------------------------------------------
    public static void writeDiaryToDB(Diary diary, Activity ctx) {
        // Save diary into DB
        DatabaseReference newPostRef = Utils.DB_DIARIES.push();
        newPostRef.setValue(diary);
        // Sync with user info
        User currentUser = User.getInstance();
        currentUser.addDiary(newPostRef.getKey());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        HashMap updates = new HashMap();
        updates.put("diaries", currentUser.getDiaries());
        Utils.DB_USERS.child(user.getUid()).updateChildren(updates);
        // back to the calendar activity
        Intent intent = new Intent(ctx, CalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
        //ctx.finish();
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // DB로 부터 모든 diary 불러오기
    //----------------------------------------------------------------------------------
    public static void fetchPosts(final OnCompletion completion){
        User currentUser = User.getInstance();
        List<String> postsUid = currentUser.getDiaries();
        Log.d(Utils.TAG, "check user diary Uid - " + currentUser.getDiaries().size() + " // " + postsUid.size());
        List<Diary> diaries = new ArrayList<>();

        Utils.DB_DIARIES.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Diary diary = new Diary(childSnapshot.getKey(), (HashMap)(childSnapshot.getValue()));
                    Log.d(Utils.TAG, "check diary value from db - " + childSnapshot.getValue());
                    Log.d(Utils.TAG, "check diary uid from db - " + diary.getUid());
                    //Log.d(Utils.TAG, "check user diary Uid - " + postsUid.get(0));
                    if(postsUid.contains(diary.getUid())) {
                        diaries.add(diary);
                    }
                }
                completion.onCompletion(diaries);
                Log.d(Utils.TAG, "onComplete obj : " + diaries.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // DB로 부터 특정 날짜 diary 불러오기
    //----------------------------------------------------------------------------------
    public static void fetchADiary(String date, final OnCompletion completion){
        Utils.DB_DIARIES.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User currentUser = User.getInstance();
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else{
                    DataSnapshot dataSnapshot = task.getResult();
                    for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        Diary theDiary = new Diary(childSnapshot.getKey(), (HashMap)(childSnapshot.getValue()));
                        if(date.equals(theDiary.getDate()) && currentUser.getDiaries().contains(theDiary.getUid())) {
                            completion.onCompletion(theDiary);
                        }
                    }
                }
            }
        });
    }
    //----------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    // DB에 수정 diary 저장
    //----------------------------------------------------------------------------------
    public static void updateDiary(Diary diary) {
        Log.d(Utils.TAG,"update start- ");
        String uid = diary.getUid();
        Map<String, Object> diaryValue = diary.toMap();

        Utils.DB_DIARIES.child(uid).updateChildren(diaryValue);
    }
    //----------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    // DB에 diary 삭제
    //----------------------------------------------------------------------------------
    public static void deleteDiary(Diary diary) {
        String uid = diary.getUid();
        User currentUser = User.getInstance();
        currentUser.deleteDiary(diary.getUid());

        String userUid = Utils.mAuth.getCurrentUser().getUid();
        Utils.DB_DIARIES.child(uid).removeValue();
        Utils.DB_USERS.child(userUid).updateChildren(currentUser.toMap());

//        String dateArray[] = diary.getDate().split("/");
//        Log.d(Utils.TAG, "delete date : " + dateArray[2]);
//        Utils.unmarkCalendar(dateArray);
    }
    //----------------------------------------------------------------------------------

}
