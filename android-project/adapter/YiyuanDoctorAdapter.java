package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.entity.HospitalDoctorEntity;

import java.util.List;

public class YiyuanDoctorAdapter extends BaseAdapter {
    private List<HospitalDoctorEntity> mData;
    private Context mContext;

    public YiyuanDoctorAdapter(List<HospitalDoctorEntity> mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int positon, View convertview, ViewGroup parent) {
        YiyuanDoctorAdapter.ViewHolder holder=null;
        if(convertview == null){
            holder = new YiyuanDoctorAdapter.ViewHolder();
            convertview = LayoutInflater.from(mContext).inflate(R.layout.yisheng_item_layout,parent,false);
            holder.tv_imageView = (ImageView) convertview.findViewById(R.id.doctor_image);
            holder.doctor_name= (TextView) convertview.findViewById(R.id.doctor_id);
            holder.doctor_yiyuan= (TextView) convertview.findViewById(R.id.doctor_yiyuan);
            holder.doctor_content= (TextView) convertview.findViewById(R.id.doctor_content);
            holder.doctor_keshi= (TextView) convertview.findViewById(R.id.doctor_keshi);
            holder.doctor_zhiwu= (TextView) convertview.findViewById(R.id.yisheng_zhihu);
            convertview.setTag(holder);
        }else {
            holder = (YiyuanDoctorAdapter.ViewHolder)convertview.getTag();
        }
        holder.doctor_name.setText(mData.get(positon).getDotcor_name());
        holder.doctor_yiyuan.setText(mData.get(positon).getDoctor_yiyuan());
        holder.doctor_keshi.setText(mData.get(positon).getDoctor_keshi());
        holder.doctor_zhiwu.setText(mData.get(positon).getDoctor_zhiwu());
        holder.doctor_content.setText(mData.get(positon).getDoctor_content());
        return convertview;
    }

    static class ViewHolder{
        ImageView tv_imageView;
        TextView doctor_name;
        TextView doctor_content;
        TextView doctor_yiyuan;
        TextView doctor_keshi;
        TextView doctor_zhiwu;
    }
}
