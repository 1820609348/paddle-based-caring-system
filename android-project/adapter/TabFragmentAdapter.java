package com.example.myapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapp.fragment.TabFragment1;

import java.util.List;

public class TabFragmentAdapter extends FragmentPagerAdapter {
    private List<TabFragment1> tabFragment1List;
    private List<String> TabTitle;
    public TabFragmentAdapter(FragmentManager fm, List<TabFragment1> tabFragment1List, List<String> TabTitle) {
        super(fm);
        this.tabFragment1List = tabFragment1List;
        this.TabTitle=TabTitle;
    }

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
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
