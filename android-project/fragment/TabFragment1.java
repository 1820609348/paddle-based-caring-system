package com.example.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapp.Activity.ShequtongzhimubanActivity;
import com.example.myapp.R;
import com.example.myapp.adapter.NewsAdapter;
import com.example.myapp.entity.News;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;


public class TabFragment1 extends Fragment {



    private List<News> mData = null;
    private Context mContext;
    private NewsAdapter mAdapter = null;
    private ListView listView;

    private Banner mbanner;
    private MyImageLoader myImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    public class MyImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView){
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }


    public TabFragment1() {
        // Required empty public constructor
    }

    public TabFragment1(String s) {
    }

    // TODO: Rename and change types and number of parameters
    public static TabFragment1 newInstance() {
        TabFragment1 fragment = new TabFragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void iniData(){
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.mipmap.a1);
        imagePath.add(R.mipmap.a2);
        imagePath.add(R.mipmap.a3);
        imageTitle.add("众望所归，习近平总书记引领中国新征程");
        imageTitle.add("全国政协十四届一次会议");
        imageTitle.add("习近平总书记出席中国共产党与世界高层对话会");
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tabfragment,container,false);

        iniData();

        myImageLoader = new MyImageLoader();
        mbanner = view.findViewById(R.id.baner);
        //设置样式
        mbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //图片加载器
        mbanner.setImageLoader(myImageLoader);
        //动画轮播效果
        mbanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置文字
        mbanner.setBannerTitles(imageTitle);
        //设置轮播时间间隔
        mbanner.setDelayTime(5000);
        //是否自动轮播
        mbanner.isAutoPlay(true);
        //设置指示器位置和小点点
        mbanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置加载位置图片
        mbanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        switch (position){
                            case 0:
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        Toast.makeText(mContext,"点击了第1条数据",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }).start();
                                break;
                            case 1:
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        Toast.makeText(mContext,"点击了第2条数据",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }).start();
                                break;
                            case 2:
                            new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        Toast.makeText(mContext,"点击了第3条数据",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }).start();
                                break;
                        }
                    }
                }).start();


        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();//获得系统时间.
// 获取当前年
        int year = calendar.get(Calendar.YEAR);
// 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
// 获取当前日
        int day = calendar.get(Calendar.DATE);

        TextView nianyue = view.findViewById(R.id.nianyue);
        TextView ri = view.findViewById(R.id.ri);
        TextView xingqiji = view.findViewById(R.id.xingqiji);
        nianyue.setText(year+"年"+month+"日");
        ri.setText(""+day);
        xingqiji.setText(getWeekOfDate(date));


        mContext = this.getActivity();
        listView = view.findViewById(R.id.listview);
        mData = new LinkedList<News>();
        mData.add(new News("社区停车场车位临时调整通知","2023-04-10",R.mipmap.xiugaizhongyaotongzhi));
        mData.add(new News("社区党支部活动举办通知","2023-04-10",R.mipmap.xiugaizhongyaotongzhi));
        mData.add(new News("社区5单元3幢电梯修理通知","2023-04-07",R.mipmap.xiugaizhongyaotongzhi));
        mData.add(new News("清明节社区清洁事务通知","2023-04-05",R.mipmap.xiugaizhongyaotongzhi));
//        for(int i = 0; i<10;i++){
//            mData.add(new News("我是标题","不是内容",R.mipmap.add1));
//            mData.add(new News("我是标题","我是内容",R.mipmap.xiugaizhongyaotongzhi));
//        }
        mAdapter = new NewsAdapter(mData,mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShequtongzhimubanActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

//    private void getMdataList(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request  = new Request.Builder()
//                            .url(IP_URL+"/testApi/POSTApi/get_notice")
//                            .build();
//
//                    Response response = client.newCall(request).execute();
//
//
////                    String result = response.body().string();//接收请求的返回数据
////                    Gson gson = new Gson();
////                    Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
////                    System.out.println(resultMap.get("code"));
//
//                    String result = response.body().string();
//                    Gson gson = new Gson();
//                    Map<String ,Object> resultMap = gson.fromJson(result,Map.class);
//                    System.out.println(resultMap.get("code"));
//                    S
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                    Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).start();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//
//    }
}

