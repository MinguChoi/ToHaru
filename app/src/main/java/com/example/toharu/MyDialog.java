package com.example.toharu;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MyDialog extends Dialog {
    private Button Confirm;
    private Button Cancel;

    private View.OnClickListener Confirm_Btn;
    private View.OnClickListener Cancel_Btn;

    public TextView Body;
    public TextView Title;

    public String title;
    public String body;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //다이얼로그의 꼭짓점이 짤리는부분 방지.
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.mydialog);

        Confirm=(Button)findViewById(R.id.Confirm);

        Cancel=(Button)findViewById(R.id.Cancel);

        Title =findViewById(R.id.title);

        Body =findViewById(R.id.body);

        Confirm.setOnClickListener(Confirm_Btn);
        Cancel.setOnClickListener(Cancel_Btn);

        //타이틀과 바디의 글씨 재셋팅
        Title.setText(this.title);
        Body.setText(this.body);
    }

    public MyDialog(@NonNull Context context, View.OnClickListener Confirm_Btn, View.OnClickListener Cancel_Btn, String title, String body) {
        super(context);
        //생성자에서 리스너 및 텍스트 초기화
        this.Confirm_Btn = Confirm_Btn;
        this.Cancel_Btn = Cancel_Btn;
        this.title = title;
        this.body = body;
    }
}
