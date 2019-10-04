package com.example.mushr.colorfool;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mushr.colorfool.Utils.StatusBarUtil;

public class UserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        StatusBarUtil.setStatusBar(this, Color.WHITE);
    }
}
