package com.example.toharu;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class Utils {

    public static final String TAG = "ToHaru";
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase DB_REF = FirebaseDatabase.getInstance();
    public static DatabaseReference DB_USERS = DB_REF.getReference("users");
    public static DatabaseReference DB_POSTS = DB_REF.getReference("posts");

    public static boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }

    public static void toastError(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
