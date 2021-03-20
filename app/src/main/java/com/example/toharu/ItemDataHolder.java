package com.example.toharu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDataHolder {
    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    public ImageView        emotion_customRow_IMG;
    public TextView         date_customRow_TXT, content_customRow_TXT;
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // View 지정
    //----------------------------------------------------------------------------------
    public ItemDataHolder (View root) {
        this.emotion_customRow_IMG = root.findViewById(R.id.emotion_customRow_IMG);
        this.date_customRow_TXT = root.findViewById(R.id.date_customRow_TXT);
        this.content_customRow_TXT = root.findViewById(R.id.content_customRow_TXT);
    }
    //----------------------------------------------------------------------------------

}