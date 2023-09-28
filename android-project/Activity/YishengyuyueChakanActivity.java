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

public class YishengyuyueChakanActivity extends AppCompatActivity {

    private List<TongyongEntity> mData = null;
    private Context mContext;
    private TongyongAdapter mAdapter = null;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yishengyuyue_chakan);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("username");
        
        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });

        mContext = this.getApplicationContext();
        listView = findViewById(R.id.list_view_yuyue);
        mData = new LinkedList<TongyongEntity>();

        for(int i = 0; i<5;i++){
//            mData.add(new News("我是标题","不是内容",R.mipmap.add1));
            if(i == 0){
                mData.add(new TongyongEntity("2023-01-12","李医生"));
            }
            else if(i == 1) {
                mData.add(new TongyongEntity("2023-02-01","李医生"));
            }
            else if(i == 2) {
                mData.add(new TongyongEntity("2023-02-02","张医生"));
            }
            else if(i == 3){
                mData.add(new TongyongEntity("2023-02-12","刘医生"));
            }else {
                mData.add(new TongyongEntity("2023-03-23","田医生"));
            }
        }
        mAdapter = new TongyongAdapter(mData,mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(mContext,"点击了第"+position+"条数据", Toast.LENGTH_SHORT).show();
            }
        });




    }
}