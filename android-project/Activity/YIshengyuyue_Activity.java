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
import com.example.myapp.adapter.DoctorAdapter;
import com.example.myapp.entity.DoctorEntity;

import java.util.LinkedList;
import java.util.List;


public class YIshengyuyue_Activity extends AppCompatActivity {

    private List<DoctorEntity> mData = null;
    private Context mContext;
    private DoctorAdapter mAdapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yishengyuyue);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("username");

        View yishengyuyuechakan = findViewById(R.id.yishengyuyuechakan);
        yishengyuyuechakan.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(YIshengyuyue_Activity.this,YishengyuyueChakanActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        });

        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mContext = this.getApplicationContext();
        listView = findViewById(R.id.list_view_docotr);
        mData = new LinkedList<DoctorEntity>();

        for(int i = 0; i<4;i++){
//            mData.add(new News("我是标题","不是内容",R.mipmap.add1));
            if(i == 0){
                mData.add(new DoctorEntity("红星社区卫生中心","刘医生","我是用户名",R.mipmap.nanyisheng));
            }
            else if(i == 1) {
                mData.add(new DoctorEntity("红星社区卫生中心","李医生","我是用户名",R.mipmap.nanyisheng));
            }
            else if(i == 2) {
                mData.add(new DoctorEntity("红星社区卫生中心","张医生","我是用户名",R.mipmap.nanyisheng));
            }
            else if(i == 3){
                mData.add(new DoctorEntity("红星社区卫生中心","田医生","我是用户名",R.mipmap.nanyisheng));
            }
        }
        mAdapter = new DoctorAdapter(mData,mContext);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(mContext,"点击了第"+position+"条数据", Toast.LENGTH_SHORT).show();
            }
        });

    }
}