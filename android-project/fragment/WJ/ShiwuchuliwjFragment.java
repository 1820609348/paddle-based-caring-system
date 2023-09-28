package com.example.myapp.fragment.WJ;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapp.Activity.Dangjianyuandi;
import com.example.myapp.Activity.InformationChoose;
import com.example.myapp.Activity.PeopleHealthActivity;
import com.example.myapp.Activity.SocialThingsHandle;
import com.example.myapp.Activity.WJActivity.FSDActivity;
import com.example.myapp.Activity.XinXiFankuiActivity;
import com.example.myapp.Activity.YuYingshibieActivity;
import com.example.myapp.Activity.ZhangbeimoshiActivity;
import com.example.myapp.R;

public class ShiwuchuliwjFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public void ShiWuChuLiFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ShiwuchuliwjFragment newInstance() {
        ShiwuchuliwjFragment fragment = new ShiwuchuliwjFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shiwuchuliwj, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        String admin = intent.getStringExtra("admin");
        System.out.println("这是fragment获得的信息"+admin);


        Button button1 = getActivity().findViewById(R.id.zhuhuxinxi_btn1);
        Button button2 = getActivity().findViewById(R.id.jiankangbaobei_btn2);
        Button button3 = getActivity().findViewById(R.id.xinxifankui_btn6);
        Button button4 = getActivity().findViewById(R.id.renmingjiankang_btn5);
        Button button5 = getActivity().findViewById(R.id.paoyitang_btn);
        Button button6 = getActivity().findViewById(R.id.shequdangjian_btn6);
        View fab = getActivity().findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZhangbeimoshiActivity.class);
                startActivity(intent);
            }
        });

        Button imageButton_yuying = getActivity().findViewById(R.id.yuyingkongzhi);
        imageButton_yuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YuYingshibieActivity.class);
                startActivity(intent);
            }
        });


//        Button button_mapservice = (Button)getActivity().findViewById(R.id.mapservice_btn7);
//        button_mapservice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),MapServiceActivity.class);
//                startActivity(intent);
//            }
//        });




        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InformationChoose.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FSDActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), XinXiFankuiActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PeopleHealthActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SocialThingsHandle.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Dangjianyuandi.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }

}