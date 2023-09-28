package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.utils.GetApis;

import java.util.Map;

public class InformationChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_choose);

        Intent intent0 = getIntent();
        String admin = intent0.getStringExtra("admin");


        ImageButton button1 = (ImageButton) findViewById(R.id.baisefanhui);
        Button button2 =findViewById(R.id.chakanzhuhuxinxi_btn);
        Button button3 =(Button)findViewById(R.id.xiugaizhuhuxinxi_btn);


        button1.setOnClickListener(view -> {
            finish();
        });


        button3.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(InformationChoose.this, InformationChange.class);
            intent.putExtra("admin",admin);
            startActivity(intent);
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetApis getApis = new GetApis();
//                        Map<String,Object> resultMap =getApis.Get_login_responsecode("28","456789");

                        Map<String, Object> resultMap = getApis.Get_informationread_responsecode(admin);


                        if (resultMap == null) {
                            Log.v("请求结果:", "失败");
                        } else {
//                            Log.v("请求结果:", resultMap.toString());
//                            if((String.valueOf(resultMap.get("responsecode"))).equals(Success_Login)){
//                                Looper.prepare();
//
//                                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent();
//                                intent.setClass(MainActivity.this, RealMainActivity.class);
//                                intent.putExtra("admin",username);
//                                startActivity(intent);
//
//                                Looper.loop();
//                            } else {
//                                Looper.prepare();
//                                Toast.makeText(getApplicationContext(), "请输入正确的用户名或密码", Toast.LENGTH_SHORT).show();
//                                Looper.loop();
//                            }
                            String name = (String)resultMap.get("name");
                            String sex = (String) resultMap.get("sex");
                            double age = (double) resultMap.get("age");
                            String community = String.valueOf(resultMap.get("community"));
                            String address = String.valueOf(resultMap.get("address"));
                            String phonenumber = String.valueOf(resultMap.get("phonenumber"));
                            String wechatnumber = String.valueOf(resultMap.get("wechatnumber"));
                            String isalways = (String)resultMap.get("isalways");


                            Log.v("请求结果:", resultMap.toString());


                            Bundle bundle = new Bundle();
                            bundle.putString("name",name);
                            bundle.putString("sex",sex);
                            bundle.putDouble("age",age);
                            bundle.putString("community",community);
                            bundle.putString("address",address);
                            bundle.putString("phonenumber",phonenumber);
                            bundle.putString("wechatnumber",wechatnumber);
                            bundle.putString("isalways",isalways);

                            Intent intent1 = new Intent();
                            intent1.putExtras(bundle);
                            intent1.setClass(InformationChoose.this,InformationRead.class);
                            startActivity(intent1);

                        }
                    }
                }).start();
            }
        });
    }
}