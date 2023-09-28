package com.example.myapp.Activity.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.R;
import com.example.myapp.databinding.FragmentHomeBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    //定义弹窗对象
    private Dialog dialog;
    //定义三个按钮
    private Button button1, button2, button3,button4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();













        TextView greetings =root.findViewById(R.id.greetings);
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour >= 8 && hour < 11) {

            greetings.setText("早上好,记得要吃早餐");
        } else if (hour >= 11 && hour < 13) {

            greetings.setText("中午好，祝您心情愉快");
        } else if (hour >= 13 && hour < 16) {

            greetings.setText("下午好，记得要锻炼身体");
        } else if (hour >= 16 && hour < 18) {

            greetings.setText("傍晚好，祝您心情愉快");
        } else {

            greetings.setText("晚上好,请不要熬夜,注意身体健康");
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}