package com.example.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.adapter.TongyongAdapter;
import com.example.myapp.entity.TongyongEntity;

import java.util.LinkedList;
import java.util.List;

public class DangjiandongtaiActivity extends AppCompatActivity {

    private List<TongyongEntity> mData = null;
    private Context mContext;
    private TongyongAdapter mAdapter = null;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangjiandongtai);


        ImageButton button1 = findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });
        
        mContext = this.getApplicationContext();
        listView = findViewById(R.id.dangjiantongtai_listview);
        mData = new LinkedList<TongyongEntity>();
        mData.add(new TongyongEntity("红星社区党委学习贯彻二十大精神活动","红星社区"));
        mData.add(new TongyongEntity("红星社区学党史做先锋活动","红星社区"));
        mData.add(new TongyongEntity("红星社区红色记忆博物馆参观活动","红星社区"));


        mAdapter = new TongyongAdapter(mData,mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(mContext,"点击了第"+position+"条数据", Toast.LENGTH_SHORT).show();

                Intent intent_x = new Intent();
                intent_x.setClass(DangjiandongtaiActivity.this,ShebaokaBanli.class);
                intent_x.putExtra("test",""+position);
                startActivity(intent_x);
            }
        });
    }
}