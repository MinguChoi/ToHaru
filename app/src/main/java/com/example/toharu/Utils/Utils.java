package com.example.toharu.Utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {

    public static final String TAG = "ToHaru";
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase DB_REF = FirebaseDatabase.getInstance();
    public static DatabaseReference DB_USERS = DB_REF.getReference("users");
    public static DatabaseReference DB_DIARIES = DB_REF.getReference("diaries");
    public static DatabaseReference DB_ADVICES = DB_REF.getReference("advice");

    public static boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }

    public static void toastError(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    public static String DateToString(Date date) {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        return df.format(date);
    }
}
