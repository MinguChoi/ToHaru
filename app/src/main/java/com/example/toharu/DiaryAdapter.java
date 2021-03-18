package com.example.toharu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toharu.Model.Diary;

import java.util.List;

public class DiaryAdapter extends ArrayAdapter {
    private List<Diary> diariesList;
    private Context context;

    public DiaryAdapter(List<Diary> diariesList, Context ctx) {
        super(ctx, R.layout.custom_row, diariesList);
        this.diariesList = diariesList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_row, parent, false);

            ItemDataHolder holder = new ItemDataHolder(convertView);
            convertView.setTag(holder);
        }
        ItemDataHolder holder = (ItemDataHolder) convertView.getTag();

        ImageView emotionImageView = holder.emotionImageView;
        TextView dateView = holder.dateView;
        TextView contentView = holder.contentView;

        Diary diary = diariesList.get(position);
        //emotionImageView
        dateView.setText(diary.getDate());
        contentView.setText(diary.getContent());

        return convertView;
    }
}
