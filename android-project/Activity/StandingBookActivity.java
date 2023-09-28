package com.example.myapp.Activity;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StandingBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing_book);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("admin");

        EditText editText1 = findViewById(R.id.jingbingmingcheng_edittext);
        EditText editText2 = findViewById(R.id.huanbingshijian_edittext);
        EditText editText3 = findViewById(R.id.huanbingqingkuang_edittext);

        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });

        Button button = findViewById(R.id.tijiaojiankangtaizhang_btn);
        button.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        String diseasetype = editText1.getText().toString().trim();
                        String datatime = editText2.getText().toString().trim();
                        String diseasedescribe = editText3.getText().toString().trim();

                        if(TextUtils.isEmpty(diseasetype)|TextUtils.isEmpty(datatime)|TextUtils.isEmpty(diseasedescribe)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(StandingBookActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {

                            FormBody.Builder params = new FormBody.Builder();
                            params.add("username", username);
                            params.add("diseasetype", diseasetype);
                            params.add("datatime", datatime);
                            params.add("diseasedescribe", diseasedescribe);

                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(IP_URL + "/testApi/POSTApi/post_standingbook")
                                    .post(params.build())
                                    .build();
                            Response response = client.newCall(request).execute();

                            String result = response.body().string();//接收请求的返回数据
                            Gson gson = new Gson();
                            Map<String, Object> resultMap = gson.fromJson(result, Map.class);//使用Gson将json格式的字符串转换为HashMap
                            System.out.println(resultMap.get("code"));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(StandingBookActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }catch (IOException e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(StandingBookActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        });
    }
}