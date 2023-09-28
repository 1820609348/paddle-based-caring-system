package com.example.myapp.Activity;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapp.R;

public class ZhangbeimoshiActivity extends AppCompatActivity {

    // 定义一个常量作为通知渠道的 ID
    public static final String CHANNEL_ID = "test_channel";

    // 定义一个常量作为通知的 ID
    public static final int NOTIFICATION_ID = 1;

    // 创建一个 Handler 对象，用于执行定时任务
    Handler handler = new Handler();

    // 创建一个 Runnable 对象，用于发送通知
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 获取 NotificationManager 的实例
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // 判断是否是 Android 8.0 及更高版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 创建一个 NotificationChannel 对象
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Test Channel", NotificationManager.IMPORTANCE_DEFAULT);
                // 设置通知渠道的描述
                notificationChannel.setDescription("This is a test channel for notification");
                // 注册通知渠道
                notificationManager.createNotificationChannel(notificationChannel);
            }

            // 创建一个 NotificationCompat.Builder 对象
            NotificationCompat.Builder builder = new NotificationCompat.Builder(ZhangbeimoshiActivity.this, CHANNEL_ID);
            // 设置通知的小图标
            builder.setSmallIcon(R.mipmap.zhiyingshequ);
            // 设置通知的标题
            builder.setContentTitle("Test Notification");
            // 设置通知的内容
            builder.setContentText("This is a test notification");
            // 设置通知的优先级
            builder.setPriority(NotificationCompat.PRIORITY_MAX);

            // 发送通知
            notificationManager.notify(NOTIFICATION_ID, builder.build());

            // 每隔 30 秒重复执行该任务
            handler.postDelayed(this, 5000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String admin = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhangbeimoshi);
        // 启动定时任务
        handler.post(runnable);
        ImageButton exit = findViewById(R.id.baisefanhui);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        View button1 = findViewById(R.id.kapian_btn1);
        View button2 = findViewById(R.id.kapian_btn2);
        View button3 = findViewById(R.id.kapian_btn3);
        View button4 = findViewById(R.id.kapian_btn4);
        View button5 = findViewById(R.id.kapian_btn5);
        View button6 = findViewById(R.id.kapian_btn6);

        View fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this,YuYingshibieActivity.class);
                startActivity(intent);
            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, InformationChoose.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, HealthInput.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, XinXiFankuiActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, PeopleHealthActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, SocialThingsHandle.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhangbeimoshiActivity.this, Dangjianyuandi.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
}