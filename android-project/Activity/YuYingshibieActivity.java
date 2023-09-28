package com.example.myapp.Activity;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.example.myapp.Activity.WJActivity.FSDActivity;
import com.example.myapp.Activity.WJActivity.SMJCActivity;
import com.example.myapp.R;
import com.example.myapp.adapter.MsgAdapter;
import com.example.myapp.listener.DownloadListener;
import com.example.myapp.model.GetTokenResponse;
import com.example.myapp.network.ApiService;
import com.example.myapp.network.NetCallBack;
import com.example.myapp.network.ServiceGenerator;
import com.example.myapp.utils.API;
import com.example.myapp.utils.ASRresponse;
import com.example.myapp.utils.Msg;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YuYingshibieActivity extends AppCompatActivity  implements EventListener{

    private static final String TAG = "OnlineAPIActivity";

    /**
     * 页面按钮
     */
    private Button btnSynthApi, btnPlay;

    /**
     * Api服务
     */
    private ApiService service;

    /**
     * 鉴权Toeken
     */
    private String accessToken;

    /**
     * 权限请求框架
     */
    private RxPermissions rxPermissions;

    /**
     * 默认文本，当输入框未输入使用，
     */
    private String defaultText = "你好！百度。";

    /**
     * 文件路径
     */
    private String mPath;

    /**
     * 缓冲区大小
     */
    private static int sBufferSize = 8192;

    /**
     * 文件
     */
    private File file;


    protected TextView txtResult;//识别结果
    protected Button startBtn;//开始识别  一直不说话会自动停止，需要再次打开
    protected Button stopBtn;//停止识别
    private ListView msgListView;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    private EventManager asr;//语音识别核心库

    private Spinner spinner;
    private ArrayAdapter adapter;
    protected  String api;
    private final API expert = new API();

    private ImageView biaoshitu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yingshibie);
        biaoshitu = findViewById(R.id.yuyingbiaoshitu);
        initView();
        initView1();
        initPermission();
        //访问API获取接口
        requestApiGetToken();

        ImageButton exit = findViewById(R.id.baisefanhui);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化EventManager对象
        asr = EventManagerFactory.create(this, "asr");
        //注册自己的输出事件类
        asr.registerListener(this); //  EventListener 中 onEvent方法

        initMsgs();

        msgAdapter = new MsgAdapter(YuYingshibieActivity.this, R.layout.msg_item, msgList);
        msgListView = findViewById(R.id.msg_list_view_1);
        msgListView.setAdapter(msgAdapter);
        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int count = position+1;
                Toast.makeText(YuYingshibieActivity.this,"正在合成"+count+"条文字", Toast.LENGTH_SHORT).show();
                requestPermission(msgList.get(position).getContent());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            play();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
                Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show();
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    /**
     * 权限申请回调，可以作进一步处理
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }



    private void initMsgs() {
        Msg msg1 = new Msg("你好，我是您的知音社区语音助手", Msg.TYPE_YUYING);
        msgList.add(msg1);


    }

    public List<String> getDataSource(){
        List<String> list = new ArrayList<>();
        list.add("普通话");
        list.add("粤语");
        list.add("四川话");
        return list;
    }


    private void initView1(){
        rxPermissions = new RxPermissions(this);
    }
    /**
     * 初始化控件
     */
    private void initView() {
        txtResult = findViewById(R.id.tv_txt);
        startBtn = findViewById(R.id.btn_start);
        stopBtn = findViewById(R.id.btn_stop);




        this.spinner = this.findViewById(R.id.test_spinner);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,getDataSource());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String feature = spinner.getSelectedItem().toString();
                api = expert.getScore(feature);
                System.out.println(api);
                Log.e("json",api);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Map<String,Object> parmas = new LinkedHashMap<>();
        parmas.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);

        parmas.put(SpeechConstant.PID,api);
        String json = null;
        json = new JSONObject(parmas).toString();

/*        asr.send(SpeechConstant.ASR_START,json,null,0,0);*/

        final String finalJson = json;

        startBtn.setOnClickListener(new View.OnClickListener() {//开始
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                biaoshitu.setImageResource(R.mipmap.yishibie);
                asr.send(SpeechConstant.ASR_START, "{"+finalJson+"}", null, 0, 0);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {//停止
            @Override
            public void onClick(View v) {
                biaoshitu.setImageResource(R.mipmap.weishibie);
                asr.send(SpeechConstant.ASR_STOP, "{"+finalJson+"}", null, 0, 0);
            }
        });
    }


    /**
     * 自定义输出事件类 EventListener 回调方法
     */
    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 识别相关的结果都在这里
            if (params == null || params.isEmpty()) {
                return;
            }
            if (params.contains("\"final_result\"")) {
                // 一句话的最终识别结果
                Log.i("ars.event", params);

                Gson gson = new Gson();
                ASRresponse asRresponse = gson.fromJson(params, ASRresponse.class);//数据解析转实体bean

                if (asRresponse == null) return;
                //从日志中，得出Best_result的值才是需要的，但是后面跟了一个中文输入法下的逗号，
                if (asRresponse.getBest_result().contains("，")) {//包含逗号  则将逗号替换为空格，这个地方还会问题，还可以进一步做出来，你知道吗？
                    txtResult.setText(asRresponse.getBest_result().replace('，', ' ').trim());//替换为空格之后，通过trim去掉字符串的首尾空格
                    Msg msg = new Msg(asRresponse.getBest_result().replace('，', ' ').trim(), Msg.TYPE_SEND);
                    Log.e("s", asRresponse.getBest_result().replace('，', ' ').trim());
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        Thread.sleep(1000);
                                        SendYuying(asRresponse.getBest_result().replace('，', ' ').trim());
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }).start();





                } else {//不包含
                    txtResult.setText(asRresponse.getBest_result().trim());
                    Msg msg = new Msg(asRresponse.getBest_result().replace('，', ' ').trim(), Msg.TYPE_SEND);
                    Log.e("s", asRresponse.getBest_result().replace('，', ' ').trim());
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        Thread.sleep(1000);
                                        SendYuying(asRresponse.getBest_result().replace('，', ' ').trim());
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }).start();


                }
            }
        }
    }

    protected void SendYuying(String msg){
        String inputString = msg;
        String content = null;

        String regex1 = ".*地图.*";
        String regex2 = ".*朗读.*";
        String regex3 = ".*党建.*";
        String regex4 = ".*导航.*";
        String regex5 = ".*运动检测.*";
        String regex6 = ".*人民健康.*";
        String regex7 = ".*防走失服务.*";
        String regex8 = ".*睡眠检测.*";

            if (Pattern.matches(regex1, inputString)) {
                 content = "正在为您打开，地图服务";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                MapService();
            } else if (Pattern.matches(regex2, inputString)) {
                 content = "正在为您打开，朗读服务";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                readloud();
            } else if (Pattern.matches(regex3, inputString)) {

                content = "正在为您打开，社区党建";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                DangJian();

            } else if(Pattern.matches(regex4, inputString)){

                 content = "正在为您打开，导航服务";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                DaoHang();
            } else if(Pattern.matches(regex5, inputString)){
                content = "正在为您打开，运动检测";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                Yundongjiance();

            } else if(Pattern.matches(regex6, inputString)){
                content = "正在为您打开，人民健康";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                Renmingjiankang();
            } else if(Pattern.matches(regex7, inputString)){
                content = "正在为您打开，防走失服务";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                fzs();
            } else if(Pattern.matches(regex8, inputString)){
                content = "正在为您打开，睡眠检测";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                smjc();
            } else{
                content = "未理解，您可以说运动检测";
                Msg msg1 = new Msg(content,Msg.TYPE_YUYING);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
            }


    }


    protected void HuifuMatch(){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 基于SDK集成4.2 发送取消事件
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        // 基于SDK集成5.2 退出事件管理器
        // 必须与registerListener成对出现，否则可能造成内存泄露
        asr.unregisterListener(this);
    }

    /**
     * 访问API获取接口
     */
    private void requestApiGetToken() {
        String grantType = "client_credentials";
        String apiKey = "rQtMRcTuqNY8S6GRyEVzaSTE";
        String apiSecret = "5EozxHcPHPjwA8inAguYkZxwFg862OUE";
        service = ServiceGenerator.createService(ApiService.class, 0);
        service.getToken(grantType, apiKey, apiSecret)
                .enqueue(new NetCallBack<GetTokenResponse>() {
                    @Override
                    public void onSuccess(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                        if (response.body() != null) {
                            //鉴权Token
                            accessToken = response.body().getAccess_token();
                            Log.d(TAG, accessToken);
                        }
                    }

                    @Override
                    public void onFailed(String errorStr) {
                        Log.e(TAG, "获取Token失败，失败原因：" + errorStr);
                        accessToken = null;
                    }
                });
    }
    /**
     * android 6.0 以上需要动态申请权限
     */
    @SuppressLint("CheckResult")
    private void requestPermission(String content) {
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(grant -> {
                    if (grant) {
                        //如果输入框的内容为空则使用默认文字进行语音合成
                        String text;
                        if (content.isEmpty()) {
                            text = defaultText;
                        } else {
                            text = content.trim();
                        }
                        //发起合成请求
                        requestSynth(text);
                        Log.e("text",text);
                    } else {
                        Toast.makeText(YuYingshibieActivity.this,"未获取到权限",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 合成请求
     * @param text 需要合成语音的文本
     */
    private void requestSynth(String text) {
        service = ServiceGenerator.createService(ApiService.class, 1);
        service.synthesis(accessToken, "1", String.valueOf(System.currentTimeMillis()), "zh", text)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG,"请求成功");
                            //写入磁盘
                            writeToDisk(response, listener);
                        } else {
                            Log.d(TAG, "请求失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "error");
                    }
                });
    }

    /**
     * 写入磁盘
     * @param response 响应体
     * @param downloadListener 下载监听
     */
    private void writeToDisk(Response<ResponseBody> response, DownloadListener downloadListener) {
        //开始下载
        downloadListener.onStart();
        //输入流  将输入流写入文件
        InputStream is = response.body().byteStream();
        //文件总长
        long totalLength = response.body().contentLength();
        //设置文件存放路径
        file = new File(getExternalCacheDir() + "/Speech/" + "test.mp3");
        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
            }
        }
        //输出流
        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件监听
     */
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onStart() {
            Log.d(TAG, "开始");
        }

        @Override
        public void onProgress(int progress) {
            Log.d(TAG, "进度：" + progress);
        }

        @Override
        public void onFinish(String path) {
            Log.d(TAG, "完成：" + path);
            mPath = path;


        }

        @Override
        public void onFail(String errorInfo) {
            Log.d(TAG, "异常：" + errorInfo);
        }
    };


    /**
     * 播放
     */
    private void play() {
        if(mPath != null){
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(mPath);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String matchFunction(String inputString) {
        String regex1 = ".*地图.*";
        String regex2 = ".*朗读.*";
        String regex3 = ".*党建.*";
        String regex4 = ".*导航.*";

        if (Pattern.matches(regex1, inputString)) {
            MapService();
            String content = "正在为您打开，地图服务";
            return content;
        } else if (Pattern.matches(regex2, inputString)) {
            readloud();
            String content = "正在为您打开，朗读服务";
            return content;
        } else if (Pattern.matches(regex3, inputString)) {
            DangJian();
            String content = "正在为您打开，社区党建";
            return content;
        } else if(Pattern.matches(regex4, inputString)){
            DaoHang();
            String content = "正在为您打开，导航服务";
            return content;
        } else {
            return null;
        }
    }

    public void MapService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this,MapServiceActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void readloud() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this,RouteActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void DangJian() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this,DangjiandongtaiActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void DaoHang() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this,RouteActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void Yundongjiance(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this, FSDActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void Renmingjiankang(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this, PeopleHealthActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void fzs(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this, MapServiceActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void smjc(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(YuYingshibieActivity.this, SMJCActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
