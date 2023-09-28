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
import com.example.myapp.adapter.HuiFuAdapter;
import com.example.myapp.entity.HuiFuEntity;

import java.util.LinkedList;
import java.util.List;

public class ChaKanFanKuiActivity extends AppCompatActivity {

    private List<HuiFuEntity> mData = null;
    private Context mContext;
    private HuiFuAdapter mAdapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_kan_fan_kui);
        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("username");

        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> finish());

        mContext = this.getApplicationContext();
        listView = findViewById(R.id.listview_huifu);
        mData = new LinkedList<HuiFuEntity>();
        mData.add(new HuiFuEntity("社区垃圾处理不及时","社区垃圾处理不及时","您好，社区工作人员已经进行处理，以后绝对及时处理",R.mipmap.lvhua));

        mAdapter = new HuiFuAdapter(mData,mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(mContext,"点击了第"+position+"条数据", Toast.LENGTH_SHORT).show();
            }
        });

    }
}