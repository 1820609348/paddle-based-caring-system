package com.example.myapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp.R;
import com.example.myapp.adapter.MylistViewAdapter;
import com.example.myapp.entity.User;
import com.example.myapp.fragment.BlankFragment1;
import com.example.myapp.fragment.MultiplexingFragment;

import java.util.ArrayList;
import java.util.List;

public class GuaHaoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private FrameLayout mFrame;
    private List<User> mList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentManager supportFragmentManager = getSupportFragmentManager();
    private MylistViewAdapter apader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guahao_activity);
        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mFrame = (FrameLayout) findViewById(R.id.mFrame);
    }

    private void initData() {
        //左边listView集合添加数据，适配器适配
        listViewData();
        //添加fragment,复用fragment
        addFragment();
        //默认选中ListView第一条item
        replese(0);
        //ListView第一条item的Select为true
        mList.get(0).setSelect(true);
        //listView点击事件
        mListView.setOnItemClickListener(this);
    }

    /*
     * 方法名： listViewData()
     * 功    能：左边listView集合添加数据
     * 参    数：无
     * 返回值：无
     */
    private void listViewData() {
//        for (int i = 0; i < 10; i++) {
//            mList.add(new User("页面" + i));
//        }
        mList.add(new User("内科"));
        mList.add(new User("外科"));
        mList.add(new User("骨科"));
        mList.add(new User("儿科"));
        mList.add(new User("眼科"));
        mList.add(new User("妇产科"));
        mList.add(new User("肿瘤科"));
        mList.add(new User("口腔科"));
        mList.add(new User("皮肤科"));
        mList.add(new User("精神心理科"));


        //适配器适配
        apader = new MylistViewAdapter(mList, this);
        mListView.setAdapter(apader);
    }

    /*
     * 方法名：addFragment()
     * 功    能：添加fragment,复用fragment
     * 参    数：无
     * 返回值：无
     */
    private void addFragment() {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        MultiplexingFragment multiplexingFragment = new MultiplexingFragment();
//        for (int i = 0; i < mList.size(); i++) {
//            Fragment multiplexing = multiplexingFragment.getMultiplexing(mList.get(i).getName(), "");
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());
        mFragmentList.add(new BlankFragment1());

//        }
        //添加fragment
        for (int i = 0; i < mFragmentList.size(); i++) {
            transaction.add(R.id.mFrame, mFragmentList.get(i));
        }
        transaction.commit();
    }

    /*
     * 方法名：replese(int position)
     * 功    能：根据点击事件的下标切换fragment页面
     * 参    数：int position
     * 返回值：无
     */
    private void replese(int position) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        //所有的fragment隐藏，position对应的fragment显示，提交。
        for (int i = 0; i < mFragmentList.size(); i++) {
            Fragment fragment = mFragmentList.get(i);
            transaction.hide(fragment);
        }
        transaction.show(mFragmentList.get(position)).commit();
    }

    /*
     * 方法名：onItemClick(AdapterView<?> parent, View view, int position, long id)
     * 功    能：ListView的item点击事件
     * 参    数：AdapterView<?> parent, View view, int position, long id
     * 返回值：无
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //切换fragment
        replese(position);
        //在bean类里写的一个标记 boolean类型的isSelect是关键，默认无状态， 并设置get set方法
        //集合里所有数据的Select设置为flase,position下标所对应的item的Select为true，刷新适配器。
        for (int i = 0; i <mList.size() ; i++) {
            mList.get(i).setSelect(false);
        }
        mList.get(position).setSelect(true);
        //在刷新一下适配器就ok
        apader.notifyDataSetChanged();

        Toast.makeText(GuaHaoActivity.this, "" + position, Toast.LENGTH_SHORT).show();
    }
}