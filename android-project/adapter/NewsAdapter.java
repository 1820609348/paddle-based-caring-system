package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.entity.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<News> mData;
    private Context mContext;

    public NewsAdapter(List<News> mData, Context mContext){
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

        ViewHolder holder = null;
        if(convertview == null){
            holder = new ViewHolder();
            convertview = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout,parent,false);
            holder.tv_imageView = (ImageView) convertview.findViewById(R.id.img_icon);
            holder.tv_title = (TextView) convertview.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) convertview.findViewById(R.id.tv_content);
            convertview.setTag(holder);
        }else {
            holder = (ViewHolder) convertview.getTag();
        }
        holder.tv_imageView.setBackgroundResource(mData.get(positon).getaIcon());
        holder.tv_title.setText(mData.get(positon).getTitle());
        holder.tv_content.setText(mData.get(positon).getContent());
        return convertview;
    }

    static class ViewHolder{
        ImageView tv_imageView;
        TextView tv_title;
        TextView tv_content;
    }
}
