package com.example.myapp.Activity.WJActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activity.MapServiceActivity;
import com.example.myapp.R;

public class JKJKActivity extends AppCompatActivity {
    private Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jkjkactivity);
        ImageButton button = findViewById(R.id.baisefanhui);
        button.setOnClickListener(view -> {
            finish();
        });
        //设置视频背景
        final VideoView videoview=(VideoView)findViewById(R.id.video_background);
        final String videopath = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.source1_4).toString();
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
        button1 = findViewById(R.id.weizhi);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JKJKActivity.this, MapServiceActivity.class);
                startActivity(intent);
            }
        });


    }
}