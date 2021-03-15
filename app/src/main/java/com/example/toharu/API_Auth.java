package com.example.toharu;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class API_Auth {

    private static final String TAG = "ToHaru";
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    static FirebaseDatabase DB_REF = FirebaseDatabase.getInstance();
    static DatabaseReference DB_USERS = DB_REF.getReference("users");

    public static void createUser(String email, String password, Context ctx) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            // Save testUser into database
                            writeUserToDB("Mingu", "Choi", email);
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public static void signIn(String email, String password, Context ctx) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
//                            Toast.makeText((Activity) ctx, "Authentication succeed.",
//                                    Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText((Activity) ctx, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public static void writeUserToDB(String firstName, String lastName, String email) {
        User newUser = new User(firstName,lastName, email);
        DB_USERS.setValue(newUser);
    }
}
