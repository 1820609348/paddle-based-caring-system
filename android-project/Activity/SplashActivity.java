package com.example.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim,buttonAnim;
    ImageView imageView;
    TextView app_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        buttonAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);


        imageView = findViewById(R.id.imageview1);
        app_name = findViewById(R.id.appname);

        imageView.setAnimation(topAnim);
        app_name.setAnimation(buttonAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}