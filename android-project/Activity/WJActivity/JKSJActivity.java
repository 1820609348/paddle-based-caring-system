package com.example.myapp.Activity.WJActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JKSJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jksjactivity);
        ImageButton button1 = findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });

        BarChart barChart = findViewById(R.id.yundongbushu);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 4000f));
        entries.add(new BarEntry(1f, 8000f));
        entries.add(new BarEntry(2f, 6000f));
        entries.add(new BarEntry(3f, 12000f));
        entries.add(new BarEntry(4f, 18000f));
        entries.add(new BarEntry(5f, 9000f));
        entries.add(new BarEntry(6f, 14000f));

        BarDataSet dataSet = new BarDataSet(entries, "老人运动步数");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
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
        // 获取X轴
        XAxis xAxis = barChart.getXAxis();
        // 将值格式化器设置到X轴上
        xAxis.setValueFormatter(formatter);
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();



        PieChart pieChart;

        pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> entriesp = new ArrayList<>();

        // 添加数据
        entriesp.add(new PieEntry(35f, "浅睡"));
        entriesp.add(new PieEntry(35f, "深睡"));
        entriesp.add(new PieEntry(30f, "快速眼动"));

        PieDataSet dataSet1 = new PieDataSet(entriesp, "睡眠情况");

        // 设置颜色
        ArrayList<Integer> colorsp = new ArrayList<>();
        colorsp.add(Color.rgb(207, 248, 246));
        colorsp.add(Color.rgb(148, 212, 212));
        colorsp.add(Color.rgb(136, 180, 187));
        dataSet1.setColors(colorsp);
        dataSet1.setValueTextColor(Color.BLACK);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date1 = sdf.format(new Date());

        // 创建描述
        Description description = new Description();
        description.setText(date1);

        // 将描述设置到饼图中
        pieChart.setDescription(description);
        PieData data1 = new PieData(dataSet1);
        pieChart.setData(data1);
        pieChart.invalidate(); // refresh


        LineChart lineChart;
        lineChart = findViewById(R.id.lineChart);
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

        LineData data2 = new LineData();

        // 将两个数据集添加到数据中
        data2.addDataSet(dataSetBloodPressure);
        data2.addDataSet(dataSetHeartRate);
        // 获取X轴
        XAxis xAxis1 = lineChart.getXAxis();
        // 获取左侧Y轴
        YAxis leftAxis1 = lineChart.getAxisLeft();

        // 设置Y轴的最小值和最大值
        leftAxis1.setAxisMinimum(0f);
        leftAxis1.setAxisMaximum(140f);

        // 右侧Y轴
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(140f);
        // 创建一个值格式化器
        ValueFormatter formatter1 = new ValueFormatter() {
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
        xAxis1.setValueFormatter(formatter1);
        lineChart.setData(data2);

        // 刷新图表
        lineChart.invalidate();




// 获取水平柱状图控件
        HorizontalBarChart barChart1 = findViewById(R.id.bar_chart);

// 创建七组数据，每组数据三个类型A,B,C
        float[] group1 = {3.5f, 2.5f, 1.5f};  // 第一天
        float[] group2 = {4f, 2f, 1.5f};  // 第二天
        float[] group3 = {3.5f, 3f, 1.5f};  // 第三天
        float[] group4 = {4f, 2.5f, 1.5f};  // 第四天
        float[] group5 = {3.5f, 2.5f, 2f};  // 第五天
        float[] group6 = {4f, 2f, 2f};  // 第六天
        float[] group7 = {3.5f, 3f, 1.5f};  // 第七天

// 创建七个BarEntry对象，每个对象包含三个值
        BarEntry barEntry1 = new BarEntry(1, group1);
        BarEntry barEntry2 = new BarEntry(2, group2);
        BarEntry barEntry3 = new BarEntry(3, group3);
        BarEntry barEntry4 = new BarEntry(4, group4);
        BarEntry barEntry5 = new BarEntry(5, group5);
        BarEntry barEntry6 = new BarEntry(6, group6);
        BarEntry barEntry7 = new BarEntry(7, group7);

// 创建一个存放所有BarEntry对象的列表
        List<BarEntry> entries1 = new ArrayList<>();
        entries1.add(barEntry1);
        entries1.add(barEntry2);
        entries1.add(barEntry3);
        entries1.add(barEntry4);
        entries1.add(barEntry5);
        entries1.add(barEntry6);
        entries1.add(barEntry7);

// 创建一个BarDataSet对象，用来存放一组水平柱状图的数据集
        BarDataSet barDataSet = new BarDataSet(entries1, "水平柱状图");

        int[] colors = {Color.rgb(254,246,153), Color.rgb(248,211,149), Color.rgb(161,232,251)};
        barDataSet.setColors(colors);

        LegendEntry[] entries2 = new LegendEntry[3];

        LegendEntry entryA = new LegendEntry();
        entryA.formColor = Color.rgb(254,246,153);
        entryA.label = "浅度睡眠";

        LegendEntry entryB = new LegendEntry();
        entryB.formColor = Color.rgb(248,211,149);
        entryB.label = "深度睡眠";

        LegendEntry entryC = new LegendEntry();
        entryC.formColor = Color.rgb(161,232,251);
        entryC.label = "快速眼动";

        entries2[0] = entryA; entries2[1] = entryB; entries2[2] = entryC;

        Legend legend = barChart1.getLegend();

        legend.setCustom(entries2);
// 设置水平柱状图的值的格式，可以使用ValueFormatter接口来自定义格式，也可以使用默认的格式
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 将值转换为整数，并加上百分号
                return (int) value + "h";
            }
        });

// 创建一个BarData对象，用来存放所有的水平柱状图数据
        BarData barData = new BarData(barDataSet);

// 设置水平柱状图的宽度，数值越小，水平柱状图越宽
        barData.setBarWidth(0.5f);

// 将BarData对象设置到水平柱状图控件中
        barChart1.setData(barData);

// 刷新图表
        barChart1.invalidate();

// 设置图表的一些样式和交互属性

// 设置图例是否可见，以及位置和方向
        barChart1.getLegend().setEnabled(true);
        barChart1.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        barChart1.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        barChart1.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);

// 设置X轴是否可见，以及位置和方向
        barChart1.getXAxis().setEnabled(true);
        barChart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // X轴的位置默认为右边，但是设置位置还是设置成在下面
        barChart1.getXAxis().setGranularity(1f); // 设置最小间隔，防止重复显示
// 设置X轴的值格式化方法
        barChart1.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 创建一个Calendar对象，用来获取和计算日期
                Calendar calendar = Calendar.getInstance();
                // 设置Calendar对象的时间为当前时间
                calendar.setTime(new Date());
                // 根据X轴的值，计算出对应的日期距离今天的天数，使用7-value来得到反向的结果，例如1对应六天前，2对应五天前，以此类推
                int days = 7 - (int) value;
                // 将Calendar对象的时间减去对应的天数，得到对应的日期
                calendar.add(Calendar.DATE, -days);
                // 获取对应日期的年、月、日
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // 月份从0开始，需要加1
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                // 将年、月、日转换为字符串，并拼接成格式为yyyy/MM/dd的字符串
                String date = year + "/" + month + "/" + day;
                // 返回该字符串作为X轴的值
                return date;
            }
        });
// 设置Y轴是否可见，以及位置和方向
        barChart1.getAxisLeft().setEnabled(true); // 设置左边的Y轴可见
        barChart1.getAxisRight().setEnabled(false); // 设置右边的Y轴不可见

// 设置是否可以缩放和拖动
        barChart1.setScaleEnabled(true);
        barChart1.setDragEnabled(true);

// 设置是否可以触摸高亮显示
        barChart1.setHighlightPerTapEnabled(true);


    }

}