package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.utils.GetApis;

import java.util.Map;

public class InformationChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationchange);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("admin");

        EditText editText1 = findViewById(R.id.leirong1);
        EditText editText2 = findViewById(R.id.leirong2);
        EditText editText3 = findViewById(R.id.leirong3);
        editText3.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText editText4 = findViewById(R.id.leirong4);
        EditText editText5 = findViewById(R.id.leirong5);
        EditText editText6 = findViewById(R.id.leirong6);
        EditText editText7 = findViewById(R.id.leirong7);
        EditText editText8 = findViewById(R.id.leirong8);



        ImageButton button1 = findViewById(R.id.baisefanhui);

        Button button2 = findViewById(R.id.xiugaizhuhuxinxi_btn);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetApis getApis = new GetApis();
//                        Map<String,Object> resultMap =getApis.Get_login_responsecode("28","456789");

                        String name= editText1.getText().toString().trim();
                        String sex = editText2.getText().toString().trim();
                        String age_get = editText3.getText().toString().trim();
                        String community = editText4.getText().toString().trim();
                        String address = editText5.getText().toString().trim();
                        String phonenumber = editText6.getText().toString().trim();
                        String wechatnumber = editText7.getText().toString().trim();
                        String isalways = editText8.getText().toString().trim();


                        if(TextUtils.isEmpty(age_get)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InformationChange.this,"姓名不允许为空",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else if (!sex.equals("男")|!sex.equals("女")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InformationChange.this,"请输入正确的性别", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else if (!isalways.equals("是")|!sex.equals("否")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InformationChange.this,"请输入正确的居住状态", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            int age = Integer.parseInt(age_get);
                            if(age <= 0){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(InformationChange.this,"请输入正确的年龄",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                Map<String, Object> resultMap = getApis.Get_informationchange_responsecode(username, name, sex, age, community, address, phonenumber, wechatnumber, isalways);

                                if (resultMap == null) {
                                    Log.v("请求结果:", "失败");
                                } else {
                                    Log.v("请求结果:", resultMap.toString());
                                }
                            }
                        }
                    }
                }).start();
            }
        });

        button1.setOnClickListener(view -> {
            finish();
        });
    }
}