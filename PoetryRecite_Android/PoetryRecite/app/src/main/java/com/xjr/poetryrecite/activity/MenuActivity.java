package com.xjr.poetryrecite.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xjr.poetryrecite.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        //为按钮变换字体
        Button btn_recite = (Button)findViewById(R.id.btn_recite);
        Button btn_test = (Button)findViewById(R.id.btn_test);
        Button btn_count = (Button)findViewById(R.id.btn_count);
        Button btn_gen = (Button)findViewById(R.id.btn_gen);
        Button btn_logout = (Button)findViewById(R.id.btn_logout);
        TextView welcome = (TextView)findViewById(R.id.menu_username);
        btn_recite.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        btn_test.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        btn_count.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        btn_gen.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        btn_logout.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        welcome.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));

        //完善主页功能模块
        //为menu 主页添加 用户username欢迎页面，注销 返回登录界面需要finish（）
        SharedPreferences sp = getSharedPreferences("userLogin", Context.MODE_PRIVATE);


        //测试使用username userid
//        SharedPreferences.Editor editor = sp.edit();
//        String testUseid = "220190410103445";
//        Long tUserid = Long.parseLong(testUseid);
//        editor.putLong("userid", tUserid);
//        editor.putString("username","xujiarui");
//        editor.commit();


        String username = "";
        username = sp.getString("username","null");
        welcome.setText("尊敬的【"+username+"】您好！");
        //注销
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除sharePrefences中数据
                SharedPreferences sp = getSharedPreferences("userLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MenuActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //背诵模块
        btn_recite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LitePal.getDatabase();
                Intent intent = new Intent(MenuActivity.this , ReciteActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        //测试模块
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this , TestActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        //统计模块
        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this , GradeActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        //自动生成古诗模块
        btn_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this , GenActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
