package com.example.mushr.colorfool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mushr.colorfool.Utils.StatusBarUtil;

public class SettingsActivity extends AppCompatActivity {
    private Button btn;
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        imageButton = (ImageButton) findViewById(R.id.setting_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        StatusBarUtil.setStatusBar(this, Color.WHITE);
    }
}
