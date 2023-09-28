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
import com.example.myapp.adapter.YiyuanDoctorAdapter;
import com.example.myapp.entity.HospitalDoctorEntity;

import java.util.LinkedList;
import java.util.List;

public class BlankFragment1 extends MultiplexingFragment {


    private List<HospitalDoctorEntity> mData = null;
    private Context mContext;
    private YiyuanDoctorAdapter mAdapter = null;
    private ListView listView;


    public BlankFragment1() {
        // Required empty public constructor
    }

    public static BlankFragment1 newInstance() {
        BlankFragment1 fragment = new BlankFragment1();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_blank1,container,false);
        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<HospitalDoctorEntity>();

        for(int i = 0; i<10;i++){
//            mData.add(new News("我是标题","不是内容",R.mipmap.add1));
            if(i == 0){
                mData.add(new HospitalDoctorEntity("李医生", "", "成华区人民医院", "血管外科", "主治医生", R.mipmap.nanyisheng));
            }
            else if(i == 1) {
                mData.add(new HospitalDoctorEntity("王医生", "", "成华区人民医院", "普通外科", "主治医生", R.mipmap.nvyisheng));
            }
            else if(i == 2) {
                mData.add(new HospitalDoctorEntity("刘医生", "", "成华区人民医院", "运动医学科", "主治医生", R.mipmap.nanyisheng));
            }
            else if(i == 3){
                mData.add(new HospitalDoctorEntity("张医生", "", "成华区人民医院", "脊椎外科", "主治医生", R.mipmap.nanyisheng));
            }else {
                mData.add(new HospitalDoctorEntity("戴医生", "", "成华区人民医院", "脊椎外科", "主治医生", R.mipmap.nanyisheng));
            }
        }
        mAdapter = new YiyuanDoctorAdapter(mData,mContext);
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