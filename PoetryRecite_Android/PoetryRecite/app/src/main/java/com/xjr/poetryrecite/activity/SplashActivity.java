package com.xjr.poetryrecite.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.xjr.poetryrecite.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    //设定倒计时时长 n 单位 s
    private int time = 5;
    //初始化 Handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    btn_skip.setText("跳过(" + time + ")");
                    break;
                case 1:
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
            }
            super.handleMessage(msg);
        }
    };
    private Button btn_skip;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ActivityUtils.trastate(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        btn_skip = (Button) findViewById(R.id.button);

    }

    /** skip Button 点击事件 */
    public void onclick(View view) {
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void onResume() {
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                handler.sendEmptyMessage((time == 0 ? 1 : 0));
            }
        }, 1000, 1000);
        super.onResume();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }
}
