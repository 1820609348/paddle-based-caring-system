package com.example.myapp.Activity;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HealthInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_input);

        Intent intent0 = getIntent();
        String admin = intent0.getStringExtra("admin");

        ImageButton button1 = (ImageButton) findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });

         EditText editText1 = findViewById(R.id.shurukuang3);
         EditText editText2 = findViewById(R.id.shurukuang4);




        Button button2 =findViewById(R.id.jiankangbaobeitijiao_btn);
        button2.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        String isincommunity = editText1.getText().toString().trim();
                        String daily_describe = editText2.getText().toString().trim();

                        FormBody.Builder params = new FormBody.Builder();
                        params.add("username",admin);
                        params.add("daily_describe",daily_describe);
                        params.add("isincommunity",isincommunity);



                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(IP_URL+"/testApi/POSTApi/post_dailyupload")
                                .post(params.build())
                                .build();
                        Response response = client.newCall(request).execute();


                        String result = response.body().string();//接收请求的返回数据
                        Gson gson = new Gson();
                        Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
                        System.out.println(resultMap.get("code"));


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HealthInput.this,"报备成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }catch (Exception e){
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HealthInput.this,"网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

;
                }
            }).start();
        });

    }
}