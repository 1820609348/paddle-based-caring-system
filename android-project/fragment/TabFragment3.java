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


public class TabFragment3 extends TabFragment1 {

    private List<News> mData = null;
    private Context mContext;
    private NewsAdapter mAdapter = null;
    private ListView listView;

    public TabFragment3() {
        // Required empty public constructor
    }

    public TabFragment3(String s) {
    }

    // TODO: Rename and change types and number of parameters
    public static TabFragment3 newInstance() {
        TabFragment3 fragment = new TabFragment3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab3,container,false);
        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<News>();

        mData.add(new News("总书记致信祝贺云南大学建校一百周年","2023-04-20",R.mipmap.xiugaidangzhengxinwen));
        mData.add(new News("总书记同加蓬总统邦戈举行会谈","2023-04-19",R.mipmap.xiugaidangzhengxinwen));
        mData.add(new News("习近平在广州市考察调研","2023-04-13",R.mipmap.xiugaidangzhengxinwen));

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