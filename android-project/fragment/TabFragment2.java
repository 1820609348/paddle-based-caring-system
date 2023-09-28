package com.example.myapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.adapter.NewsAdapter;
import com.example.myapp.entity.News;

import java.util.LinkedList;
import java.util.List;


public class TabFragment2 extends TabFragment1 {

    private List<News> mData = null;
    private Context mContext;
    private NewsAdapter mAdapter = null;
    private ListView listView;


    public  TabFragment2() {
        // Required empty public constructor
    }

    public  TabFragment2(String s) {
    }

    // TODO: Rename and change types and number of parameters
    public static TabFragment2 newInstance() {
        TabFragment2 fragment = new TabFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<News>();

        mData.add(new News("红星社区关爱长辈志愿活动","2023-04-12",R.mipmap.testtongzhi));
        mData.add(new News("红星社区志愿服务活动","2022-12-16",R.mipmap.testtongzhi));
        mData.add(new News("红星社区党委学习贯彻二十大","2022-11-24",R.mipmap.testtongzhi));



        mAdapter = new NewsAdapter(mData,mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(mContext,"点击了第"+position+"条数据", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}