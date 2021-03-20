package com.example.toharu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toharu.Model.Diary;
import com.example.toharu.Utils.Utils;

import java.util.List;

public class DiaryAdapter extends ArrayAdapter {
    //----------------------------------------------------------------------------------
    // 변수 선언
    //----------------------------------------------------------------------------------
    private List<Diary>     diariesList;
    private Context         context;
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // Diary Adapter 등록
    //----------------------------------------------------------------------------------
    public DiaryAdapter(List<Diary> diariesList, Context ctx) {
        super(ctx, R.layout.custom_row, diariesList);
        this.diariesList = diariesList;
        this.context = ctx;
    }
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    // Diary 세팅
    //----------------------------------------------------------------------------------
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_row, parent, false);

            ItemDataHolder holder = new ItemDataHolder(convertView);
            convertView.setTag(holder);
        }
        ItemDataHolder holder = (ItemDataHolder) convertView.getTag();

        ImageView emotionImageView = holder.emotion_customRow_IMG;
        TextView dateView = holder.date_customRow_TXT;
        TextView contentView = holder.content_customRow_TXT;

        Diary diary = diariesList.get(position);
        int imageId = Utils.getImageByName(diary.getMood(), context);
        emotionImageView.setImageResource(imageId);
        dateView.setText(diary.getDate());
        contentView.setText(diary.getContent());

        return convertView;
    }
    //----------------------------------------------------------------------------------

}
