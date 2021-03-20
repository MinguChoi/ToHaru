package com.example.toharu.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    private long                    backKeyPressedTime = 0;
    private Toast                   toast;
    private Activity                activity;

//    public static MCalendarView    CalenderView_calendar_VIEW;
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
    // 일기 삭제 기능
    //----------------------------------------------------------------------------------
//    public BackPressCloseHandler(Activity context){
//        this.activity = context;
//    }
//
//    public void onBackPressed(){
//        if (System.CurrentTimeMilis()>backKeyPressedTime+2000){
//            backKeyPressedTime = System.currentTimeMillis();
//            showGuide();
//            return;
//        }
//        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
//            activity.finish();
//            toast.cancel();
//        }
//    }
//
//    public void showGuide(){
//        toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 누르심녀 종료됩니다.", Toast.LENGTH_LONG);
//        toast.show();
//    }
    //----------------------------------------------------------------------------------
    // 일기 삭제 기능
    //----------------------------------------------------------------------------------

//    public static void unmarkCalendar(String[] dateArray, Context ctx){
//        Log.d(Utils.TAG, "utils unmark date - " + Integer.parseInt(dateArray[2]));
//        MCalendarView calendar = getCalendar(ctx);
//        CalenderView_calendar_VIEW.unMarkDate(Integer.parseInt(dateArray[0]),
//                Integer.parseInt(dateArray[1]),
//                Integer.parseInt(dateArray[2]));
//    }
//
//    public static MCalendarView getCalendar(Context ctx) {
//        if(CalenderView_calendar_VIEW == null) {
//            MCalendarView calendar = new MCalendarView(ctx);
//            return calendar;
//        } else
//            return CalenderView_calendar_VIEW;
//    }
}
