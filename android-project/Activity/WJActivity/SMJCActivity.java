package com.example.myapp.Activity.WJActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.view.ClockView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SMJCActivity extends AppCompatActivity {
    // 声明时钟控件对象
    private ClockView clockView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smjcactivity);
        // 通过findViewById方法获取时钟控件对象
        clockView = findViewById(R.id.clockView);


        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });


        PieChart pieChart;

        pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> entriesp = new ArrayList<>();

        // 添加数据
        entriesp.add(new PieEntry(35f, "浅睡"));
        entriesp.add(new PieEntry(35f, "深睡"));
        entriesp.add(new PieEntry(30f, "快速眼动"));

        PieDataSet dataSet = new PieDataSet(entriesp, "睡眠情况");

        // 设置颜色
        ArrayList<Integer> colorsp = new ArrayList<>();
        colorsp.add(Color.rgb(207, 248, 246));
        colorsp.add(Color.rgb(148, 212, 212));
        colorsp.add(Color.rgb(136, 180, 187));
        dataSet.setColors(colorsp);
        dataSet.setValueTextColor(Color.BLACK);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(new Date());

        // 创建描述
        Description description = new Description();
        description.setText(date);

        // 将描述设置到饼图中
        pieChart.setDescription(description);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh






        // 为时钟控件设置点击监听器
        clockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击时钟控件时，弹出一个Toast提示当前时间
                Toast.makeText(SMJCActivity.this, "当前时间是：" + new SimpleDateFormat("HH:mm:ss").format(new Date()), Toast.LENGTH_SHORT).show();
            }
        });

// 假设您的按钮ID为button，文本控件ID为textview
        Button button1111 = findViewById(R.id.button1111);
        TextView textViewzt = findViewById(R.id.zhuangtaiwenzi);

        button1111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewzt.setText("状态：采集中");
            }
        });
// 假设您的按钮ID为button，文本控件ID为textview
        Button button2222 = findViewById(R.id.button2222);

        TextView textView1 = findViewById(R.id.txtlog1);
        TextView textView2 = findViewById(R.id.txtlog2);
        TextView textView3 = findViewById(R.id.txtlog3);
        TextView textView4 = findViewById(R.id.txtlog4);
        TextView textView5 = findViewById(R.id.txtlog5);
        TextView textView6 = findViewById(R.id.txtlog6);
        TextView textView7 = findViewById(R.id.txtlog7);
        TextView textView8 = findViewById(R.id.txtlog8);
        TextView textView9 = findViewById(R.id.txtlog9);
        TextView textView10 = findViewById(R.id.txtlog10);
        TextView textView11 = findViewById(R.id.txtlog11);

        button2222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewzt.setText("状态：生成采集结果");

                // 创建一个Handler
                Handler handler = new Handler();

                // 使用handler.postDelayed方法实现延迟2秒后执行的操作
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 2到8号文字控件变成可见
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        textView5.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        textView7.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        textView9.setVisibility(View.VISIBLE);
                        textView10.setVisibility(View.VISIBLE);
                        textView11.setVisibility(View.VISIBLE);
                        // ... 同样的方式设置textView4到textView8为可见
                    }
                }, 2000);  // 延迟时间为2000毫秒，即2秒
            }
        });

        Button button3333 = findViewById(R.id.button3333);
        button3333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewzt.setText("状态：结果预测中");

                // 创建一个Handler
                Handler handler = new Handler();

                // 使用handler.postDelayed方法实现延迟2秒后执行的操作
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 2到8号文字控件变成可见
                        TextView textView = findViewById(R.id.yuce);
                        textView.setText("预测:失眠");
                        // ... 同样的方式设置textView4到textView8为可见
                    }
                }, 2000);  // 延迟时间为2000毫秒，即2秒
            }
        });

    }

}