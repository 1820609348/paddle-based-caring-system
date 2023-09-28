package com.example.myapp.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapp.Activity.InformationChange;
import com.example.myapp.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    //定义弹窗对象
    private Dialog dialog;
    //定义三个按钮
    private Button button1, button2, button3,button4;

    public MyFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_xiugaizhuye, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button_exit = (Button)getActivity().findViewById(R.id.My_exit);
//        TextView admin = (TextView)getActivity().findViewById(R.id.yonghuID_zhuye);
        TextView greetings =(TextView)getActivity().findViewById(R.id.greetings);
        Intent intent = getActivity().getIntent();
        String admin_get = intent.getStringExtra("admin");
//        admin.setText(admin_get);


        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour >= 8 && hour < 11) {

            greetings.setText("早上好,记得要吃早餐");
        } else if (hour >= 11 && hour < 13) {

            greetings.setText("中午好，祝您心情愉快");
        } else if (hour >= 13 && hour < 16) {

            greetings.setText("下午好，记得要锻炼身体");
        } else if (hour >= 16 && hour < 18) {

            greetings.setText("傍晚好，祝您心情愉快");
        } else {

            greetings.setText("晚上好,请不要熬夜,注意身体健康");
        }


        RelativeLayout bianji_ziliao = (RelativeLayout)getActivity().findViewById(R.id.bianji);
        bianji_ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), InformationChange.class);
                intent1.putExtra("admin",admin_get);
                startActivity(intent1);
            }
        });

        button_exit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
}