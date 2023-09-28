package com.example.myapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;
import com.example.myapp.adapter.MyPagerAdapter;
import com.example.myapp.entity.TabEntity;
import com.example.myapp.fragment.CommunityFragment;
import com.example.myapp.fragment.MyFragment;
import com.example.myapp.fragment.ShiWuChuLiFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class RealMainActivity extends AppCompatActivity {

    private String[] mTitles = {"社区资讯", "事务处理", "个人主页"};
    private int[] mIconUnselectIds = {
            R.mipmap.zixun_unselected, R.mipmap.shiwu_unselected, R.mipmap.my_unselected};
    private int[] mIconSelectIds = {
            R.mipmap.zixun_selected, R.mipmap.shiwu_selected, R.mipmap.my_selected};
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntitye = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realmain);



        viewPager = findViewById(R.id.ViewPage);
        commonTabLayout = findViewById((R.id.CommonTabLayout));
        mFragments.add(CommunityFragment.newInstance());
        mFragments.add(ShiWuChuLiFragment.newInstance());
        mFragments.add(MyFragment.newInstance());




        for (int i = 0; i < mTitles.length; i++) {
            mTabEntitye.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));

        }
            commonTabLayout.setTabData(mTabEntitye);
            commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
//        ViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()),mTitles,mFragments);
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitles,mFragments));
    }
}