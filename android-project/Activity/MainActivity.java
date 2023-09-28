package com.example.myapp.Activity;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.utils.GetApis;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //设置视频背景
        final VideoView videoview=(VideoView)findViewById(R.id.video_background);
        final String videopath = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.source1_3).toString();
        videoview.setVideoPath(videopath);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.setVideoPath(videopath);
                videoview.start();
            }
        });


        Button test_JH = findViewById(R.id.button_JH);
        test_JH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,TESTActivity.class);
                startActivity(intent);
            }
        });

        Button test_yuyinghecheng = findViewById(R.id.button_testyuyinghecheng);
        test_yuyinghecheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(MainActivity.this,YuyinghechengActivity.class);
                startActivity(intent);
            }
        });

        Button test_yuying = findViewById(R.id.button_learn);
        test_yuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("jundge","语音");
                intent.putExtra("voiceEndAddress","369茶坊");
                intent.setClass(MainActivity.this,TestRouteActivity.class);
                startActivity(intent);
            }
        });

        Button shiwuchuli_yemianxiugai = findViewById(R.id.button_xiugaiyemian);
        shiwuchuli_yemianxiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ZhangbeimoshiActivity.class);
                startActivity(intent);
            }
        });

        Button yuyingceshi = findViewById(R.id.button_testyuying);
        yuyingceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,YuYingshibieActivity.class);
                startActivity(intent);
            }
        });

        TextView duanxingdenglu = findViewById(R.id.duanxingdenglu);
        duanxingdenglu.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,DuanxingregisterActivity.class);
            intent.putExtra("username","28");
            startActivity(intent);
        });
        Button button111= findViewById(R.id.button111);
        button111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,GuaHaoActivity.class);
                startActivity(intent);
            }
        });

        Button button_test_notice = findViewById(R.id.test_notice);
        button_test_notice.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        FormBody.Builder params =new FormBody.Builder();
                        params.add("request","给老子传东西");


                        OkHttpClient client =new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(IP_URL+"/testApi/POSTApi/post_notice")
                                .post(params.build())
                                .build();

                        Response response = client.newCall(request).execute();
//                        String responsedata= response.body().string();
//
//                        System.out.println(responsedata);

                        String result = response.body().string();//接收请求的返回数据
                        Gson gson = new Gson();

                        for(int i = 3;i>0;i--){
                        Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
                        Map<String ,Object> noticeMap = (Map<String, Object>) resultMap.get(String.valueOf(i));
                        System.out.println(noticeMap.get("time"));
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }catch (IOException e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        });
//
//        Button button_test_huifufaikui = findViewById();
//        button_test_huifufaikui.setOnClickListener(view -> {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        FormBody.Builder params =new FormBody.Builder();
//                        params.add("fankui_duixiang","28");
//
//
//                        OkHttpClient client = new OkHttpClient();
//                        Request request = new Request.Builder()
//                                .url(IP_URL+"/testApi/POSTApi/post_huifufankui")
//                                .post(params.build())
//                                .build();
//
//                        Response response = client.newCall(request).execute();
//                        String result = response.body().string();
//                        Gson gson = new Gson();
//
//                        for(int i = 3;i>0;i--){
//                            Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
//                            Map<String ,Object> huifufankuiMap = (Map<String, Object>) resultMap.get(String.valueOf(i));
//                            System.out.println(huifufankuiMap.get("time"));
//                        }
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }catch (IOException e){
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }).start();
//        });
//
//        Button button_test_runonce = findViewById();
//        button_test_runonce.setOnClickListener(view -> {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        FormBody.Builder params = new FormBody.Builder();
//                        params.add("request_code","发跑一次的东西");
//
//                        OkHttpClient client = new OkHttpClient();
//                        Request request =new Request.Builder()
//                                .url(IP_URL+"/testApi/POSTApi/post_runonce")
//                                .post(params.build())
//                                .build();
//                        Response response = client.newCall(request).execute();
//                        String result = response.body().string();
//                        Gson gson =new Gson();
//
//                        for(int i = 3;i>0;i--){
//                            Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
//                            Map<String ,Object> runonceMap = (Map<String, Object>) resultMap.get(String.valueOf(i));
//                            System.out.println(runonceMap.get("time"));
//                        }
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }catch (IOException e){
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }).start();
//        });
//
//        Button button_yishengliebiao = findViewById();
//        button_yishengliebiao.setOnClickListener(view -> {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        FormBody.Builder params = new FormBody.Builder();
//                        params.add("/testApi/POSTApi/request_code","得到医生列表");
//
//                        OkHttpClient client = new OkHttpClient();
//                        Request request = new Request.Builder()
//                                .url(IP_URL+"post_doctorlist").post(params.build()).build();
//                        Response response = client.newCall(request).execute();
//                        String result = response.body().string();
//                        Gson gson = new Gson();
//                        for(int i = 3;i>0;i--){
//                            Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
//                            Map<String ,Object> DocotrMap = (Map<String, Object>) resultMap.get(String.valueOf(i));
//                            System.out.println(DocotrMap.get("time"));
//                        }
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }catch (IOException e){
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                }
//            }).start();
//        });
//
//        Button button_yuyueyisheng = findViewById();
//        button_yuyueyisheng.setOnClickListener(view -> {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        FormBody.Builder params =new FormBody.Builder();
//                        params.add("yuyueyishengID","医生ID");\
//
//                        OkHttpClient client =new OkHttpClient();
//                        Request request = new Request.Builder().url(IP_URL+"/testApi/POSTApi/post_yuyueyisheng").post(params.build()).build();
//                        Response response = client.newCall(request).execute();
//                        String result = response.body().string();
//                        Gson gson = new Gson();
//                        Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
//                        System.out.println(resultMap);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }catch (IOException e){
//                        e.printStackTrace();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }).start();
//        });


        Button button = findViewById(R.id.test_notice);
        EditText editText1 = findViewById(R.id.editTextPhone3);
        EditText editText2 = findViewById(R.id.editTextTextPassword3);
        TextView textView1 = findViewById(R.id.yonghuzhucerukou);


        String Success_Login = "4002.0";
        String Success_Register = "5000.0";
        String Failed_USEREXIST = "5001.0";


        textView1.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        });

//        button.setOnClickListener(view -> {
//            String admin = editText1.getText().toString().trim();
//            String password = editText2.getText().toString().trim();
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, RealMainActivity.class);
//            intent.putExtra("admin", admin);
//            startActivity(intent);
//
//        });
        Button button_maptest = findViewById(R.id.login_2_btn);
        button_maptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText1.getText().toString().trim();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RealMainActivity.class);
                intent.putExtra("admin",username);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {//登录实现
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetApis getApis = new GetApis();
//                        Map<String,Object> resultMap =getApis.Get_login_responsecode("28","456789");

                        String username = editText1.getText().toString().trim();
                        String userpwd = editText2.getText().toString().trim();

                        Map<String, Object> resultMap = getApis.Get_login_responsecode(username,userpwd);


                        if (resultMap == null) {
                            Log.v("请求结果:", "失败");
                        } else {
                            Log.v("请求结果:", resultMap.toString());
                            if((String.valueOf(resultMap.get("responsecode"))).equals(Success_Login)){
                                Looper.prepare();

                                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, RealMainActivity.class);
                                intent.putExtra("admin",username);
                                startActivity(intent);

                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "请输入正确的用户名或密码", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}