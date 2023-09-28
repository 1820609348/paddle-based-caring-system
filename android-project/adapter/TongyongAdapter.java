package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.entity.TongyongEntity;

import java.util.List;

public class TongyongAdapter extends BaseAdapter {
    private List<TongyongEntity> mData;
    private Context mContext;

    public TongyongAdapter(List<TongyongEntity> mData, Context mContext){
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
        TongyongAdapter.ViewHolder holder = null;
        if(convertview == null){
            holder = new TongyongAdapter.ViewHolder();
            convertview = LayoutInflater.from(mContext).inflate(R.layout.xiaoxitongyong_item_layout,parent,false);
            holder.tv_title = (TextView) convertview.findViewById(R.id.tongyong_title);
            holder.tv_content = (TextView) convertview.findViewById(R.id.tongyong_content);
            convertview.setTag(holder);
        }else {
            holder = (TongyongAdapter.ViewHolder) convertview.getTag();
        }
        holder.tv_title.setText(mData.get(positon).getTitle());
        holder.tv_content.setText(mData.get(positon).getContent());
        return convertview;
    }

    static class ViewHolder{
        TextView tv_title;
        TextView tv_content;
    }
}
