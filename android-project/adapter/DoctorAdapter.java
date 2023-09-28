package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.entity.DoctorEntity;

import java.util.List;

public class DoctorAdapter extends BaseAdapter {
    private List<DoctorEntity> mDoctor;
    private Context mcontext;

    public  DoctorAdapter(List<DoctorEntity> mDoctor, Context mcontext){
        this.mcontext = mcontext;
        this.mDoctor = mDoctor;
    }

    @Override
    public  int getCount(){
        return mDoctor.size();
    }
    @Override
    public Object getItem(int position){
        return mDoctor.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent){
        DoctorAdapter.ViewHolder holder = null;
        if(convertview == null){
            holder = new DoctorAdapter.ViewHolder();
            convertview = LayoutInflater.from(mcontext).inflate(R.layout.yuyue_listview_item_layout,parent,false);
            holder.doctor_imageView = (ImageView) convertview.findViewById(R.id.doctor_image);
            holder.doctor_title = (TextView) convertview.findViewById(R.id.doctor_id);
            holder.doctor_content = (TextView) convertview.findViewById(R.id.doctor_content);
            convertview.setTag(holder);
        }else {
            holder = (DoctorAdapter.ViewHolder) convertview.getTag();
        }
        holder.doctor_imageView.setBackgroundResource(mDoctor.get(position).getaIcon());
        holder.doctor_title.setText(mDoctor.get(position).getDotcor_name());
        holder.doctor_content.setText(mDoctor.get(position).getDoctor_content());
        return convertview;
    }
    static class ViewHolder{
        ImageView doctor_imageView;
        TextView doctor_title;
        TextView doctor_content;
    }
}
