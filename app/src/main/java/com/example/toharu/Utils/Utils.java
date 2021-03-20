package com.example.toharu.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toharu.CalendarActivity;
import com.example.toharu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.vo.DateData;

public final class Utils {

    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    public static final String      TAG = "ToHaru";
    public static FirebaseAuth      mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase  DB_REF = FirebaseDatabase.getInstance();
    public static DatabaseReference DB_USERS =   DB_REF.getReference("users");
    public static DatabaseReference DB_DIARIES = DB_REF.getReference("diaries");
    public static DatabaseReference DB_ADVICES = DB_REF.getReference("advice");
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // ETXT Empty 체크
    //----------------------------------------------------------------------------------
    public static boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // Toast 띄우기
    //----------------------------------------------------------------------------------
    public static void toastError(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // 파일 이름으로 ResId 가져오기
    //----------------------------------------------------------------------------------
    public static int getImageByName(String imageName, Context ctx) {
        return ctx.getResources().getIdentifier(imageName.toLowerCase(), "drawable", ctx.getPackageName());

    }
    //----------------------------------------------------------------------------------
}
