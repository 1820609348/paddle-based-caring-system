package com.example.myapp.Activity;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DuanxingregisterActivity extends AppCompatActivity {

    private  String SmsCode = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duanxingregister);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("username");
        System.out.println(username);

        EditText editText1 = findViewById(R.id.EditText3_phonenumber);
        EditText editText2 = findViewById(R.id.EditText4_duanxing);

        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });

        Button button_duanxing = findViewById(R.id.yanzhengma);
        button_duanxing.setOnClickListener(view ->{
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void run() {

                    try {
                    String phonenumber = editText1.getText().toString().trim();

                        FormBody.Builder params = new FormBody.Builder();
                        params.add("telephone", phonenumber);

                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(IP_URL+"/testApi/POSTApi/sendsms")
                                .post(params.build())
                                .build();


                        Response response =client.newCall(request).execute();
                        String result = response.body().string();
                        Gson gson =new Gson();

                        Map<String,Object> resultMap = gson.fromJson(result,Map.class);
                        SmsCode = (String) resultMap.get("smscode");




                }catch (IOException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DuanxingregisterActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    }
                }
            }).start();
        });

        Button code_denglu = findViewById(R.id.duanxingdenglu_2_btn);
        code_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DuanxingregisterActivity.this,RealMainActivity.class);
                startActivity(intent);
            }
        });
//        code_denglu.setOnClickListener(view -> {
//
//            String code_inpuy =  editText2.getText().toString().trim();
//
//            System.out.println(SmsCode);
//            if(TextUtils.isEmpty(code_inpuy)){
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(DuanxingregisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }else if(!code_inpuy.equals(SmsCode)){
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(DuanxingregisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } else {
//                Intent intent = new Intent();
//                intent.setClass(DuanxingregisterActivity.this,RealMainActivity.class);
//                intent.putExtra("admin",username);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(DuanxingregisterActivity.this, "短信登录成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                startActivity(intent);
//            }
//
//        });

    }
}