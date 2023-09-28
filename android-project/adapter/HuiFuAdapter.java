package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.entity.HuiFuEntity;

import java.util.List;

public class HuiFuAdapter extends BaseAdapter {
    private List<HuiFuEntity> mHuiFu;
    private Context mcontext;

    public  HuiFuAdapter(List<HuiFuEntity> mHuiFu, Context mcontext){
        this.mcontext = mcontext;
        this.mHuiFu = mHuiFu;
    }

    @Override
    public  int getCount(){
        return mHuiFu.size();
    }
    @Override
    public Object getItem(int position){
        return mHuiFu.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent){
        HuiFuAdapter.ViewHolder holder = null;
        if(convertview == null){
            holder = new HuiFuAdapter.ViewHolder();
            convertview = LayoutInflater.from(mcontext).inflate(R.layout.huifu_listview_item_layout,parent,false);
            holder.huifu_tv_imageView = (ImageView) convertview.findViewById(R.id.huifu_img_icon);
            holder.huifu_tv_title = (TextView) convertview.findViewById(R.id.huifu_tv_title);
            holder.huifu_tv_content = (TextView) convertview.findViewById(R.id.huifu_tv_content);
            convertview.setTag(holder);
        }else {
            holder = (ViewHolder) convertview.getTag();
        }
        holder.huifu_tv_imageView.setBackgroundResource(mHuiFu.get(position).getaIcon());
        holder.huifu_tv_title.setText(mHuiFu.get(position).getHuifufankui_title());
        holder.huifu_tv_content.setText(mHuiFu.get(position).getHuifufankui_content());
        return convertview;
    }
    static class ViewHolder{
        ImageView huifu_tv_imageView;
        TextView huifu_tv_title;
        TextView huifu_tv_content;
    }
}
