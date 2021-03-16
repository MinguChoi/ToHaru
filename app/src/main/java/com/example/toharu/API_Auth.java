package com.example.toharu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class API_Auth extends AppCompatActivity {

//    private static final String TAG = "ToHaru";
//    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    static FirebaseDatabase DB_REF = FirebaseDatabase.getInstance();
//    static DatabaseReference DB_USERS = DB_REF.getReference("users");

    public static void createUser(String name, String email, String password, Activity ctx) {
        Utils.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update the signed-in user's information
                            Log.d(Utils.TAG, "createUserWithEmail:success");
                            // Save testUser into database
                            FirebaseUser user = Utils.mAuth.getCurrentUser();
                            writeUserToDB(user.getUid(),name, email);
                            updateUserInfo(user);
                            // back to the Login activity
                            Intent intent = new Intent(ctx, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            ctx.startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(Utils.TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public static void signIn(String email, String password, Activity ctx) {
        Utils.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update the signed-in user's information
                            Log.d(Utils.TAG, "signInWithEmail:success");
                            FirebaseUser user = Utils.mAuth.getCurrentUser();
                            updateUserInfo(user);
                            // Move to the main read activity
                            Intent intent = new Intent(ctx, CalendarActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            ctx.startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(Utils.TAG, "signInWithEmail:failure", task.getException());
                            Utils.toastError(ctx, "Wrong ID or Password");
                        }
                    }
                });
    }

    public static void writeUserToDB(String uid, String name, String email) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", name);
        updates.put("email", email);

        Utils.DB_USERS.child(uid).updateChildren(updates);
    }

    public static void updateUserInfo(FirebaseUser user) {
        String uid = user.getUid();
        User currentUser = User.getInstance();

        Utils.DB_USERS.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    HashMap map = (HashMap) task.getResult().getValue();
                    currentUser.setName(map.get("name").toString());
                    currentUser.setEmail(map.get("email").toString());
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

    }
}
