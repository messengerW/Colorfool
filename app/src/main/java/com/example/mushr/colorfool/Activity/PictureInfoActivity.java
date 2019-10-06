package com.example.mushr.colorfool.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.mushr.colorfool.R;
import com.example.mushr.colorfool.Utils.StatusBarUtil;

public class PictureInfoActivity extends AppCompatActivity {

    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picinfo);
        StatusBarUtil.setStatusBar(this);

        imageButton = (ImageButton) findViewById(R.id.picinfo_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}