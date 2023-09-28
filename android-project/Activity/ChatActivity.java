package com.example.myapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.adapter.MsgAdapter;
import com.example.myapp.utils.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private EditText inputText;
    private Button send;

    private ListView msgListView;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.liaotian);

        ImageButton button1 =findViewById(R.id.baisefanhui);

        button1.setOnClickListener(view -> {
            finish();
        });

        initMsgs();
        adapter = new MsgAdapter(ChatActivity.this, R.layout.msg_item, msgList);
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgListView = findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("你好，我是刘医生", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("你好，刘医生，我是XX，我想咨询一下流感用药", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("好的，我想了解一下你的症状情况", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}