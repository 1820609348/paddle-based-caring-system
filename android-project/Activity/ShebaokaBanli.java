package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class ShebaokaBanli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shebaoka_banli);

        Intent intent0 = getIntent();
        String get = intent0.getStringExtra("test");

        ImageButton button1 = (ImageButton) findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });


    }
}