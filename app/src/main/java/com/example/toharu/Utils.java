package com.example.toharu;

import android.widget.EditText;

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
}
