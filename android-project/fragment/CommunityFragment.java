package com.example.myapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;
import com.example.myapp.adapter.TabFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class CommunityFragment extends Fragment {


    private ViewPager main_frag_1_main_ViewPager;
    private TabLayout TopNavTabLayout;
    private String [] TabTitle={"重要通知","社区资讯","党政新闻","防疫资讯","当地时事"};
    private List<TabFragment1> tabFragmentList;
    private List<String> TabTitleList;
    private TabFragmentAdapter tabFragmentAdapter;


    public void CommunityFragment() {
        // Required empty public constructor
    }

    public static CommunityFragment newInstance() {
        CommunityFragment fragment = new CommunityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabFragmentList = new ArrayList<>();
        TabTitleList = new ArrayList<>();
        for (int i=0;i<5;i++){
            TabTitleList.add(TabTitle[i]);
        }
        tabFragmentList.add(new TabFragment1());
        tabFragmentList.add(new TabFragment2());
        tabFragmentList.add(new TabFragment3());
        tabFragmentList.add(new TabFragment4());
        tabFragmentList.add(new TabFragment5());

        tabFragmentAdapter= new TabFragmentAdapter(getActivity().getSupportFragmentManager(), tabFragmentList,TabTitleList);
        main_frag_1_main_ViewPager.setAdapter(tabFragmentAdapter);
        TopNavTabLayout.setupWithViewPager(main_frag_1_main_ViewPager);
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shequzixun,container,false);
        main_frag_1_main_ViewPager=view.findViewById(R.id.main_frag_1_main);
        TopNavTabLayout=view.findViewById(R.id.TopNav);
        return view;
    }

}