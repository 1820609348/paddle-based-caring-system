package com.example.myapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapp.fragment.TabFragment1;

import java.util.List;

public class VerticalAdapter extends MyPagerAdapter{
    private List<TabFragment1> tabFragment1List;
    private List<String> TabTitle;
    public VerticalAdapter(FragmentManager fm, List<TabFragment1> tabFragment1List, List<String> mTitles) {
        super(fm);
        this.tabFragment1List = tabFragment1List;
        this.TabTitle=mTitles;
    }


    @Override
    public Fragment getItem(int position) {
        return tabFragment1List.get(position);
    }

    @Override
    public int getCount() {
        return tabFragment1List.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitle.get(position);
    }
}
