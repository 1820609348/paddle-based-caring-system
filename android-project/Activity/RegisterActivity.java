package com.example.myapp.Activity;

import android.os.Bundle;
import android.os.Looper;
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

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton imageButton = (ImageButton) findViewById(R.id.baisefanhui);
        Button button = (Button)findViewById(R.id.register_2_btn);

        EditText editText3 = findViewById(R.id.EditText3);
        EditText editText4 = findViewById(R.id.EditText4);
        String Success_Login = "4002.0";
        String Success_Register = "5000.0";
        String Failed_USEREXIST = "5001.0";


        imageButton.setOnClickListener(view -> {
            finish();
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetApis getApis=new GetApis();
                        String username = editText3.getText().toString().trim();
                        String userpwd = editText4.getText().toString().trim();
//                        Map<String,Object> resultMap = getApis.Get_register_responsecode("29","456789");
                        Map<String,Object> resultMap = getApis.Get_register_responsecode(username,userpwd);
                        if(resultMap==null){
                            Log.v("请求结果:","失败");}
                        else{
                            Log.v("请求结果", String.valueOf(resultMap.get("responsecode")));
                            if((String.valueOf(resultMap.get("responsecode"))).equals(Success_Register)){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                //这里开始写SQLITE的存储插入语句
                            }
                            else if ((String.valueOf(resultMap.get("responsecode"))).equals(Failed_USEREXIST)){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "用户名已经存在", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            else{
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
















