package com.example.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.adapter.TongyongAdapter;
import com.example.myapp.entity.TongyongEntity;

import java.util.List;

public class Dangjianyuandi extends AppCompatActivity {
    private List<TongyongEntity> mData = null;
    private Context mContext;
    private TongyongAdapter mAdapter = null;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangjianyuandi);
        ImageButton button1 = findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });

        ImageButton button2 = findViewById(R.id.dangjiantongtai);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Dangjianyuandi.this,DangjiandongtaiActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button3 = findViewById(R.id.shequdangwei);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Dangjianyuandi.this,ShequDangweiActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button4 = findViewById(R.id.xuexiyuandi);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Dangjianyuandi.this,XuexiyuandiActivity.class);
                startActivity(intent);
            }
        });

    }
}