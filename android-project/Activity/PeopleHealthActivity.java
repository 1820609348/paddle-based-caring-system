package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

import java.sql.Timestamp;
import java.util.Date;

public class PeopleHealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_health);
        ImageButton button1 =findViewById(R.id.baisefanhui);


        Intent intent0 = getIntent();
        String admin = intent0.getStringExtra("admin");

        Button button2_standingbook =findViewById(R.id.jiankangzhuangkuangdengji_btn1);
        Button button3_exit =findViewById(R.id.xianshangzixun_btn2);
        Button button4_hospital =findViewById(R.id.xinxifankui_btn6);
        Button button5_shequyueyue =findViewById(R.id.shequyishengyuyue_btn5);
        Button button6_guahao =findViewById(R.id.xinlizixun_btn4);

        button6_guahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PeopleHealthActivity.this, GuaHaoActivity.class);

                startActivity(intent);
            }
        });

        button3_exit.setOnClickListener(view -> {
            java.util.Date date =new Date();
            Timestamp t = new Timestamp(date.getTime());
            System.out.println(t);

            Intent intent = new Intent();
            intent.setClass(PeopleHealthActivity.this, XianshangzixunActivity.class);
            startActivity(intent);
        });

        button1.setOnClickListener(view -> {
            finish();
        });

        button2_standingbook.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(PeopleHealthActivity.this,StandingBookActivity.class);
            intent.putExtra("admin",admin);
            startActivity(intent);
        });

        button4_hospital.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(PeopleHealthActivity.this,RexianzixunActivity.class);
            intent.putExtra("username",admin);
            startActivity(intent);
        });

        button5_shequyueyue.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(PeopleHealthActivity.this,YIshengyuyue_Activity.class);
            intent.putExtra("username",admin);
            startActivity(intent);
        });
    }
}