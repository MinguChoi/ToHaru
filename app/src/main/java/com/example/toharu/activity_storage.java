package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class activity_storage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        // [START storage_field_initialization]
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // [END storage_field_initialization]

        includesForCreateReference();

    }

    private void includesForCreateReference() {






    }
}