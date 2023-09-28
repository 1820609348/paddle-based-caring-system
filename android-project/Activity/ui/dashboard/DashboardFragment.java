package com.example.myapp.Activity.ui.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.Activity.MapServiceActivity;
import com.example.myapp.Activity.WJActivity.JKJKActivity;
import com.example.myapp.Activity.WJActivity.JKSJActivity;
import com.example.myapp.R;
import com.example.myapp.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private Button button1,button2,button3,button4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button1 = root.findViewById(R.id.jiankangshuju_btn2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(root.getContext(), JKSJActivity.class);
                startActivity(intent);
            }
        });

        button2 = root.findViewById(R.id.paoyitang_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(root.getContext(), MapServiceActivity.class);
                startActivity(intent);
            }
        });

        button2 = root.findViewById(R.id.anquanjiankong_btn5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(root.getContext(), JKJKActivity.class);
                startActivity(intent);
            }
        });

        //创建弹窗对象
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("请选择一个选项"); //设置标题
        dialog.setCancelable(false); //设置不可取消
        //设置弹窗的内容为自定义的布局文件
        dialog.setContentView(R.layout.family_layout);
        button1 = dialog.findViewById(R.id.button114);
        button2 = dialog.findViewById(R.id.button514);
        button3 = dialog.findViewById(R.id.button1919);
        button4 = dialog.findViewById(R.id.button810);
        Button button5 = dialog.findViewById(R.id.button5141);
        //为每个按钮设置点击事件的逻辑
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第一个按钮的逻辑
                Toast.makeText(getActivity(), "正在定位长辈位置信息", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), MapServiceActivity.class);
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第二个按钮的逻辑
                Toast.makeText(getActivity(), "已联系长辈", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第三个按钮的逻辑
                Toast.makeText(getActivity(), "已联系急救中心", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击第四个按钮的逻辑
                Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在查看监控信息", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), JKJKActivity.class);
                startActivity(intent1);
            }
        });
// 声明一个Handler对象，用来执行延迟任务
        Handler handler = new Handler();

// 声明一个Runnable对象，用来设置TextView的文本
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //显示弹窗和倒计时
                dialog.show();
            }
        };
// 延迟10秒执行Runnable对象
        handler.postDelayed(runnable, 6000);


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}