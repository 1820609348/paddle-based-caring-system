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


public class TabFragment4 extends TabFragment1 {

    private List<News> mData = null;
    private Context mContext;
    private NewsAdapter mAdapter = null;
    private ListView listView;
    public TabFragment4() {
        // Required empty public constructor
    }

    public TabFragment4(String s) {
    }

    // TODO: Rename and change types and number of parameters
    public static TabFragment4 newInstance() {
        TabFragment4 fragment = new TabFragment4();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab4,container,false);
        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<News>();

        mData.add(new News("国家铁路局关于做好铁路旅客运输疫情防控工作的通知","2023-01-09",R.mipmap.xiugaifangyizixun));
        mData.add(new News("优化发热患者就诊流程","2022-12-09",R.mipmap.xiugaifangyizixun));
        mData.add(new News("感染者居家治疗指南","2022-12-08",R.mipmap.xiugaifangyizixun));

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