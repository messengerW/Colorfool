package com.example.mushr.colorfool.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mushr.colorfool.R;
import com.example.mushr.colorfool.Utils.StatusBarUtil;

public class LoginActivity extends AppCompatActivity {

    private Button btn1;
    private Context momentContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtil.setStatusBar(this, Color.WHITE);

        momentContext = this;
        //  根据按钮的 id 找到制定按钮
        btn1 = (Button) findViewById(R.id.btn_login);
        //  给按钮设置点击事件，这里实现的是跳转时间，跳转到 MainActivity 页面
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(momentContext, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
