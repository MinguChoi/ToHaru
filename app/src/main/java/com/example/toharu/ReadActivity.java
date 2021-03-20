package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReadActivity extends AppCompatActivity {

    private Button editDone_readBTN, back_readBTN;
    private EditText content_readETXT;
    private Boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        init();
    }

    public void init() {
        editDone_readBTN = findViewById();
        back_readBTN = findViewById();
        content_readETXT = findViewById();

        editDone_readBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if user clicks edit button
                if(){
                    // change the button to done

                    // change read-only content to writable

                }
                // user clicks done button
                else {
                    // check if user changed the content of diary
                    // if yes, update the diary in database
                    // else, just back to the previous activity
                }
            }
        });

        back_readBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to the previous activity
            }
        });

    }
}