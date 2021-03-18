package com.example.toharu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDataHolder {
    public ImageView emotionImageView;
    public TextView dateView, contentView;


    public ItemDataHolder (View root) {
        this.emotionImageView = root.findViewById(R.id.emotion_row);
        this.dateView = root.findViewById(R.id.date_row);
        this.contentView = root.findViewById(R.id.content_row);
    }
}