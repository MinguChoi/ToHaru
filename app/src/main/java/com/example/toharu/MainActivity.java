package com.example.toharu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView text_style; // ListView 안에 글꼴 option표시
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem; //글꼴 option 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textstyle);  //textstyle 화면 보이게 test

        listItem = new ArrayList<String>();
        listItem.add("글꼴 1");
        listItem.add("글꼴 2");
        listItem.add("글꼴 3");

        adapter =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_single_choice,listItem);
        text_style =findViewById(R.id.text_style); //text_style 리스트뷰 객체 가져오기
        text_style.setChoiceMode(text_style.CHOICE_MODE_SINGLE); //글꼴 하나만 선택가능하게함
        text_style.setAdapter(adapter);


        text_style.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),listItem.get(position).toString(),Toast.LENGTH_LONG).show();

            }
        });

    }
}