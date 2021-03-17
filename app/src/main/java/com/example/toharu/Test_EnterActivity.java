package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

// 위로의 글 db에 넣어주려고 만든 activity
public class Test_EnterActivity extends AppCompatActivity {
    private Button                sendbt;
    private EditText              editdt;
    private Spinner               spinnerMoods;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_advice_enter);

        sendbt = (Button) findViewById(R.id.button);
        editdt = (EditText) findViewById(R.id.editTextTextPersonName);
        spinnerMoods = (Spinner) findViewById(R.id.spinner);

        sendbt.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                insertAdviceData();

                editdt.setText(null);

               // insertAdviceData();
            }
        });
    }

    private void insertAdviceData(){
        String Advice = editdt.getText().toString();
        String moods = spinnerMoods.getSelectedItem().toString();

        HashMap<String, String> newItem = new HashMap<String, String>();
        newItem.put("mood", moods);
        newItem.put("msg", Advice);

        databaseReference.child("advice").push().setValue(newItem);
    }
}