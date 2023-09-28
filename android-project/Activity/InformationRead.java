package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class InformationRead extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_read);
        ImageButton button1 = findViewById(R.id.baisefanhui);

        Intent intent0 = getIntent();
//        String admin = intent0.getStringExtra("admin");
        Bundle bundle = intent0.getExtras();

        TextView textView1 = findViewById(R.id.leirong1);
        TextView textView2 = findViewById(R.id.leirong2);
        TextView textView3 = findViewById(R.id.leirong3);
        TextView textView4 = findViewById(R.id.leirong4);
        TextView textView5 = findViewById(R.id.leirong5);
        TextView textView6 = findViewById(R.id.leirong6);
        TextView textView7 = findViewById(R.id.leirong7);
        TextView textView8 = findViewById(R.id.leirong8);

        textView1.setText(bundle.getString("name"));
        textView2.setText(bundle.getString("sex"));
        textView3.setText(""+bundle.getDouble("age"));
        textView4.setText(bundle.getString("community"));
        textView5.setText(bundle.getString("address"));
        textView6.setText(bundle.getString("phonenumber"));
        textView7.setText(bundle.getString("wechatnumber"));
        textView8.setText(bundle.getString("isalways"));


        button1.setOnClickListener(view -> finish());
    }
}