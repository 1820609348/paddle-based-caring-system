package com.example.myapp.Activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class RexianzixunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rexianzixun);

        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });
    }
}