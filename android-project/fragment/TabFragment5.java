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


public class TabFragment5 extends TabFragment1 {

    private List<News> mData = null;
    private Context mContext;
    private NewsAdapter mAdapter = null;
    private ListView listView;

    public TabFragment5() {
        // Required empty public constructor
    }

    public TabFragment5(String s) {
    }

    // TODO: Rename and change types and number of parameters
    public static TabFragment5 newInstance() {
        TabFragment5 fragment = new TabFragment5();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab5,container,false);
        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<News>();

        mData.add(new News("第31届世界大学生夏季运动会倒计时100天主题活动","2023-04-20",R.mipmap.dangdishishixiugai));
        mData.add(new News("成都十大优秀未来公园社区","2023-04-17",R.mipmap.dangdishishixiugai));
        mData.add(new News("成都医保基金监管规范发布","2023-04-13",R.mipmap.dangdishishixiugai));

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