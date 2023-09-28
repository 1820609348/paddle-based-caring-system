package com.example.myapp.Activity.WJActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class FSDActivity extends Activity implements SensorEventListener {

    //定义弹窗对象
    private Dialog dialog;

    //定义倒计时对象
    private CountDownTimer timer;

    //定义倒计时文本
    private TextView countdownText;

    //定义三个按钮
    private Button button1, button2, button3,button_shujucaiji;

    // 声明三个List<Entry>对象，用来存储三条折线的数据源
    List<Entry> entries1;
    List<Entry> entries2;
    List<Entry> entries3;

    // 声明三个LineDataSet对象，用来表示三条折线
    LineDataSet lineDataSet1;
    LineDataSet lineDataSet2;
    LineDataSet lineDataSet3;

    // 声明一个LineData对象，用来存放所有的LineDataSet对象
    LineData lineData;

    // 声明一个LineChart控件，用来显示折线图
    LineChart lineChart;

    // 声明一个SensorManager对象，用来访问传感器服务
    SensorManager sensorManager;

    // 声明一个Sensor对象，用来表示线性加速度传感器
    Sensor linearAccelerationSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsdactivity);
        //创建弹窗对象
        dialog = new Dialog(this);
        dialog.setTitle("请选择一个选项"); //设置标题
        dialog.setCancelable(false); //设置不可取消

        //设置弹窗的内容为自定义的布局文件
        dialog.setContentView(R.layout.dialog_layout);

        //从布局文件中获取倒计时文本和三个按钮的对象
        countdownText = dialog.findViewById(R.id.countdown_text);
        button1 = dialog.findViewById(R.id.button1);
        button2 = dialog.findViewById(R.id.button2);
        button3 = dialog.findViewById(R.id.button3);
        button_shujucaiji = findViewById(R.id.shujucaiji);
        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });
        button_shujucaiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(FSDActivity.this,JLActivity.class);
                startActivity(intent);
            }
        });

        //为每个按钮设置点击事件的逻辑
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第一个按钮的逻辑
                Toast.makeText(FSDActivity.this, "已通知家属", Toast.LENGTH_SHORT).show();
                timer.cancel();
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第二个按钮的逻辑
                Toast.makeText(FSDActivity.this, "已通知急救中心", Toast.LENGTH_SHORT).show();
                timer.cancel();
                dialog.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第三个按钮的逻辑
                Toast.makeText(FSDActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                timer.cancel();
                dialog.dismiss();
            }
        });

        //创建倒计时对象，总时长为10秒，每秒更新一次
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每秒更新一次文本，显示剩余秒数
                countdownText.setText("还有" + millisUntilFinished / 1000 + "秒将自动通知家属和急救中心");
            }

            @Override
            public void onFinish() {
                //倒计时结束后，自动点击第一个按钮，并取消倒计时和弹窗对象
                // 处理倒计时结束的逻辑
                Toast.makeText(FSDActivity.this, "已自动通知家属和急救中心", Toast.LENGTH_SHORT).show();
//                button1.performClick();
                timer.cancel();
                dialog.dismiss();
            }
        };

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                TextView textView = findViewById(R.id.yundongzhuangtai);
                switch (count) {
                    case 1:
                        textView.setText("当前运动状态：行走");
                        handler.postDelayed(this, 5500);
                        break;
                    case 2:
                        textView.setText("当前运动状态：跑步");
                        handler.postDelayed(this, 5500);
                        break;
                    case 3:
                        textView.setText("当前运动状态：摔倒");
                        handler.postDelayed(this, 5000);
                        dialog.show();
                        timer.start();
                        break;
                }
            }
        };

        handler.postDelayed(runnable, 8000);


        // 初始化SensorManager对象，获取传感器服务
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 初始化Sensor对象，获取线性加速度传感器
        linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        // 初始化LineChart控件，并设置一些基本的属性
        lineChart = findViewById(R.id.line_chart);
        // ...省略其他代码...
// 获取右侧的Y轴对象
        YAxis rightAxis = lineChart.getAxisRight();

// 将右侧的Y轴设置为不可用
        rightAxis.setEnabled(false);



// 获取X轴对象
        XAxis xAxis = lineChart.getXAxis();

        // 设置X轴的值格式化器，添加s单位
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 将数值转换为字符串，并在后面添加s单位
                return value*0.5f + "x10^-1s";
            }
        });

        // 创建描述
        Description description1 = new Description();
        description1.setText("运动参数");

        // 将描述设置到饼图中
        lineChart.setDescription(description1);

// 获取左侧的Y轴对象
        YAxis leftAxis = lineChart.getAxisLeft();

//// 设置左侧Y轴的最小值为-5
//        leftAxis.setAxisMinimum(-10);
//
//// 设置左侧Y轴的最大值为5
//        leftAxis.setAxisMaximum(10);
// 设置左侧Y轴的值格式化器，添加m/s^2单位
        leftAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 将数值转换为字符串，并在后面添加m/s^2单位
                return value + "m/s^2";
            }
        });


        // 初始化第一条折线的数据源，初始大小为10
        entries1 = new ArrayList<>(1);
        for (int i = 0; i < 10; i++) {
            // 生成一个-20到20之间的随机数
            float y = (float) (Math.random() * 40 - 20);
            // 创建一个Entry对象，x值为i，y值为随机数
            entries1.add(new Entry(i, y));
        }

        // 初始化第二条折线的数据源，初始大小为10
        entries2 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            // 生成一个-20到20之间的随机数
            float y = (float) (Math.random() * 40 - 20);
            // 创建一个Entry对象，x值为i，y值为随机数
            entries2.add(new Entry(i, y));
        }

        // 初始化第三条折线的数据源，初始大小为10
        entries3 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            // 生成一个-20到20之间的随机数
            float y = (float) (Math.random() * 40 - 20);
            // 创建一个Entry对象，x值为i，y值为随机数
            entries3.add(new Entry(i, y));
        }

        // 初始化第一条折线的LineDataSet对象，并设置一些属性
        lineDataSet1 = new LineDataSet(entries1, "AccX");
        lineDataSet1.setColor(Color.RED); // 设置线条颜色
        lineDataSet1.setLineWidth(2f); // 设置线条宽度
        // ...省略其他代码...

        // 初始化第二条折线的LineDataSet对象，并设置一些属性
        lineDataSet2 = new LineDataSet(entries2, "AccY");
        lineDataSet2.setColor(Color.GREEN); // 设置线条颜色
        lineDataSet2.setLineWidth(3f); // 设置线条宽度
        // ...省略其他代码...

        // 初始化第三条折线的LineDataSet对象，并设置一些属性
        lineDataSet3 = new LineDataSet(entries3, "AccZ");
        lineDataSet3.setColor(Color.BLUE); // 设置线条颜色
        lineDataSet3.setLineWidth(4f); // 设置线条宽度
        // ...省略其他代码...

        // 初始化一个LineData对象，并添加所有的LineDataSet对象
        lineData = new LineData();
        lineData.addDataSet(lineDataSet1);
        lineData.addDataSet(lineDataSet2);
        lineData.addDataSet(lineDataSet3);

        // 将LineData对象设置给LineChart控件
        lineChart.setData(lineData);

        // 刷新图表
        lineChart.invalidate();
    }

    // 声明一个时间戳，用来记录上一次处理的传感器事件的时间
    long lastTime = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 判断事件的传感器类型是否为线性加速度传感器
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            // 获取当前的时间
            long currentTime = System.currentTimeMillis();

            // 判断当前的时间与上一次的时间相差是否大于等于1秒
            if (currentTime - lastTime >= 100) {
            // 获取事件中的三个方向的线性加速度值
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // 遍历第一条折线的数据源，删除最左边的数据点，添加最右边的数据点
            for (int i = 0; i < entries1.size() - 1; i++) {
                // 将第i个数据点的x值和y值设置为第i+1个数据点的x值和y值
                entries1.get(i).setX(entries1.get(i + 1).getX());
                entries1.get(i).setY(entries1.get(i + 1).getY());
            }
            // 获取最后一个数据点的x值和y值
            float lastX = entries1.get(entries1.size() - 1).getX();
            float lastY = entries1.get(entries1.size() - 1).getY();
            // 将最后一个数据点的x值加1，y值设置为x方向的线性加速度值
            lastX += 1;
            lastY = x;
            // 将最后一个数据点的x值和y值更新
            entries1.get(entries1.size() - 1).setX(lastX);
            entries1.get(entries1.size() - 1).setY(lastY);

            // 遍历第二条折线的数据源，删除最左边的数据点，添加最右边的数据点
            for (int i = 0; i < entries2.size() - 1; i++) {
                // 将第i个数据点的x值和y值设置为第i+1个数据点的x值和y值
                entries2.get(i).setX(entries2.get(i + 1).getX());
                entries2.get(i).setY(entries2.get(i + 1).getY());
            }
            // 获取最后一个数据点的x值和y值
            lastX = entries2.get(entries2.size() - 1).getX();
            lastY = entries2.get(entries2.size() - 1).getY();
            // 将最后一个数据点的x值加1，y值设置为y方向的线性加速度值
            lastX += 1;
            lastY = y;
            // 将最后一个数据点的x值和y值更新
            entries2.get(entries2.size() - 1).setX(lastX);
            entries2.get(entries2.size() - 1).setY(lastY);

            // 遍历第三条折线的数据源，删除最左边的数据点，添加最右边的数据点
            for (int i = 0; i < entries3.size() - 1; i++) {
                // 将第i个数据点的x值和y值设置为第i+1个数据点的x值和y值
                entries3.get(i).setX(entries3.get(i + 1).getX());
                entries3.get(i).setY(entries3.get(i + 1).getY());
            }
            // 获取最后一个数据点的x值和y值
            lastX = entries3.get(entries3.size() - 1).getX();
            lastY = entries3.get(entries3.size() - 1).getY();
            // 将最后一个数据点的x值加1，y值设置为z方向的线性加速度值
            lastX += 1;
            lastY = z;
            // 将最后一个数据点的x值和y值更新
            entries3.get(entries3.size() - 1).setX(lastX);
            entries3.get(entries3.size() - 1).setY(lastY);

            // 通知数据已经改变
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();

            // 设置横轴的最小值和最大值为当前数据源的第一个和最后一个数据点的x值
            lineChart.getXAxis().setAxisMinimum(entries1.get(0).getX());
            lineChart.getXAxis().setAxisMaximum(entries1.get(entries1.size() - 1).getX());

            // 刷新图表
            lineChart.invalidate();

            // 将上一次的时间更新为当前的时间
            lastTime = currentTime;
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();


        // 注册传感器监听器，设置采样频率为每秒10次
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timer timer = new Timer();

        // 取消定时器或者异步任务
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        // 取消传感器监听器
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度发生变化时调用，一般无需处理
    }
}
