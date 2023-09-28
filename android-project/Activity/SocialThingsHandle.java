package com.example.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.adapter.TongyongAdapter;
import com.example.myapp.entity.TongyongEntity;

import java.util.List;

public class SocialThingsHandle extends AppCompatActivity {

    private List<TongyongEntity> mData = null;
    private Context mContext;
    private TongyongAdapter mAdapter = null;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_things_handle);

        RelativeLayout ditufuwu = findViewById(R.id.ditufuwu);
        ditufuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(SocialThingsHandle.this,MapServiceActivity.class);
                startActivity(intent1);
            }
        });

        ImageButton button1 = findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });



    }
}