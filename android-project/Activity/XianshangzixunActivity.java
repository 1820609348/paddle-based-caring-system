package com.example.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.adapter.SpecialLiaotianAdapter;
import com.example.myapp.entity.DoctorEntity;

import java.util.LinkedList;
import java.util.List;

public class XianshangzixunActivity extends AppCompatActivity {
    private List<DoctorEntity> mData = null;
    private Context mContext;
    private SpecialLiaotianAdapter mAdapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianshangzixun);

        ImageButton button1 =findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });


        mContext = this.getApplicationContext();
        listView = findViewById(R.id.xianshangzixun_list_view);
        mData = new LinkedList<DoctorEntity>();

        for(int i = 0; i<4;i++){
//            mData.add(new News("我是标题","不是内容",R.mipmap.add1));
            if(i == 0){
                mData.add(new DoctorEntity("","刘医生","我是用户名",R.mipmap.liaotianyisheng,"2023-02-12"));
            }
            else if(i == 1) {
                mData.add(new DoctorEntity("","张医生","我是用户名",R.mipmap.nvyisheng,"2023-02-01"));
            }else if (i == 3){
                mData.add(new DoctorEntity("","王医生","我是用户名",R.mipmap.liaotianyisheng,"2023-01-23"));
            }
        }

        mAdapter = new SpecialLiaotianAdapter(mData,mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent_x = new Intent();
                intent_x.setClass(XianshangzixunActivity.this,ChatActivity.class);
                intent_x.putExtra("test",""+position);
                startActivity(intent_x);
            }
        });
    }


}
