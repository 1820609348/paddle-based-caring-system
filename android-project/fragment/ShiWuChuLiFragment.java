package com.example.myapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapp.Activity.MapServiceActivity;
import com.example.myapp.Activity.WJActivity.FSDActivity;
import com.example.myapp.Activity.WJActivity.JKSJActivity;
import com.example.myapp.Activity.WJActivity.SMJCActivity;
import com.example.myapp.Activity.YuYingshibieActivity;
import com.example.myapp.Activity.ZhangbeimoshiActivity;
import com.example.myapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ShiWuChuLiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShiWuChuLiFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ShiWuChuLiFragment newInstance() {
        ShiWuChuLiFragment fragment = new ShiWuChuLiFragment();
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
        return inflater.inflate(R.layout.activity_changeshiwuchulu, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        String admin = intent.getStringExtra("admin");
        System.out.println("这是fragment获得的信息"+admin);



        Button button2 = getActivity().findViewById(R.id.jiankangbaobei_btn2);
        Button button3 = getActivity().findViewById(R.id.xinxifankui_btn6);
        Button button4 = getActivity().findViewById(R.id.renmingjiankang_btn5);
        Button button5 = getActivity().findViewById(R.id.paoyitang_btn);
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



        LineChart lineChart;
        lineChart = getActivity().findViewById(R.id.lineChart);
        ArrayList<Entry> entriesBloodPressure = new ArrayList<>();
        ArrayList<Entry> entriesHeartRate = new ArrayList<>();

        // 添加血压数据
        entriesBloodPressure.add(new Entry(0, 120));
        entriesBloodPressure.add(new Entry(1, 125));
        entriesBloodPressure.add(new Entry(2, 130));
        entriesBloodPressure.add(new Entry(3, 128));
        entriesBloodPressure.add(new Entry(4, 122));
        entriesBloodPressure.add(new Entry(5, 124));
        entriesBloodPressure.add(new Entry(6, 126));

        // 添加心率数据
        entriesHeartRate.add(new Entry(0, 70));
        entriesHeartRate.add(new Entry(1, 72));
        entriesHeartRate.add(new Entry(2, 68));
        entriesHeartRate.add(new Entry(3, 70));
        entriesHeartRate.add(new Entry(4, 72));
        entriesHeartRate.add(new Entry(5, 68));
        entriesHeartRate.add(new Entry(6, 70));

        LineDataSet dataSetBloodPressure = new LineDataSet(entriesBloodPressure, "血压");

        // 设置血压数据的颜色
        dataSetBloodPressure.setColor(Color.rgb(207, 248, 246));

        LineDataSet dataSetHeartRate = new LineDataSet(entriesHeartRate, "心率");

        // 设置心率数据的颜色
        dataSetHeartRate.setColor(Color.rgb(148, 212, 212));

        LineData data1 = new LineData();

        // 将两个数据集添加到数据中
        data1.addDataSet(dataSetBloodPressure);
        data1.addDataSet(dataSetHeartRate);
        // 获取X轴
        XAxis xAxis = lineChart.getXAxis();
        // 获取左侧Y轴
        YAxis leftAxis = lineChart.getAxisLeft();

        // 设置Y轴的最小值和最大值
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(140f);

        // 右侧Y轴
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(140f);
        // 创建一个值格式化器
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 将值转换为日期
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, (int)value - 6);
                return sdf.format(calendar.getTime());
            }
        };

        // 创建描述
        Description description1 = new Description();
        description1.setText("健康监测");

        // 将描述设置到饼图中
        lineChart.setDescription(description1);
        // 将值格式化器设置到X轴上
        xAxis.setValueFormatter(formatter);
        lineChart.setData(data1);

        // 刷新图表
        lineChart.invalidate();

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
                Intent intent = new Intent(getActivity(), SMJCActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JKSJActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapServiceActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });


    }

}