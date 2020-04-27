package com.xjr.poetryrecite.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjr.poetryrecite.R;
import com.sk.view.TextViewVertical;
import com.xjr.poetryrecite.bean.Poetry;
import com.xjr.poetryrecite.fragment.PoetryFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xjr.poetryrecite.util.config.HOST;
import static com.xjr.poetryrecite.util.config.HOST_POETRY;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener{


    private ViewPager viewPager;

    private ArrayList<Fragment> fragmentlists;
    private AlertDialog.Builder builder;
    private int nowpager = 0;

    //静态变量
//    private static String rData="测试\n" +
//            "这是一段测试文字，主要是为了测试竖直排版TextView的显示效果。\" +\n" +
//            "                \"为了能更好的体验感受，我特意增加了比较接近书法的字体和颜色，如果有什么改进的建议请发邮件到我的邮箱吧。\" +\n" +
//            "                \"\\n竖直排版的TextView需要配合HorizontalScrollView使用才能有更佳的效果。当然，如果你有时间的话，也可以给这个类\" +\n" +
//            "                \"加上滚动的功能。\" + \"\\n \" + \"测试\\n这是一段测试文字，主要是为了测试竖直排版TextView的显示效果。\" +\n" +
//            "                \"为了能更好的体验感受，我特意增加了比较接近书法的字体和颜色，如果有什么改进的建议请发邮件到我的邮箱吧。\" +\n";
//    private HorizontalScrollView sv;
//    private TextViewVertical tv;

    private Handler poetryHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String responseData = msg.obj.toString();
                Log.i("获取返回信息",responseData);

                //解析json数据
                Gson gson = new Gson();
                List<Poetry> poetryList = gson.fromJson(responseData,new TypeToken<List<Poetry>>(){}.getType());
                for (Poetry poetry : poetryList) {

                    //解析成Gson了 --- 成功输出
                    System.out.println(poetry);

                    PoetryFragment poetryFragment = new PoetryFragment();
                    poetryFragment.setPoetry(poetry);
                    fragmentlists.add(poetryFragment);
                }

                //设置适配器
                viewPager.setAdapter(new ReciteActivity.MainAdapter(getSupportFragmentManager()));

            }else{
                Toast.makeText(ReciteActivity.this,"获取古诗词失败！",Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recite_layout);

//        SharedPreferences mSharedPreferences = getSharedPreferences("Detail", Context.MODE_PRIVATE);
//        //创建时给 editor赋值0
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putInt("isVisible", 0);
//        editor.commit();


        viewPager = (ViewPager)findViewById(R.id.r_vp_poetry);

        Button btn_previous = (Button)findViewById(R.id.r_btn_previous);
        Button btn_next = (Button)findViewById(R.id.r_btn_next);
        Button btn_submit = (Button)findViewById(R.id.r_btn_details);
        btn_previous.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

//        tv = (TextViewVertical) findViewById(R.id.tv);
//        sv = (HorizontalScrollView) findViewById(R.id.sv);
//
//        tv.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));

        //发送request请求
        sendRequestWithOkHttp();

        viewPager.setOnPageChangeListener(new ReciteActivity.MyOnPageChangeListener());

        //设置接口事件接收
//        Handler handler = new Handler() {
//            public void handleMessage(android.os.Message msg) {
//                switch (msg.what) {
//                    //竖版布局发生改变 响应
//                    case TextViewVertical.LAYOUT_CHANGED:
//                        sv.scrollBy(tv.getTextWidth(), 0);//滚动到最右边
//                        break;
//                }
//            }
//        };
//        tv.setHandler(handler);//将Handler绑定到TextViewVertical
//        //格式没问题 但第一次不显示获取的data
//        tv.setText(rData);
//
   }

    //向服务器发送request请求 poetry
    private void sendRequestWithOkHttp() {

        fragmentlists = new ArrayList<>();
        Log.d("request:","向服务端请求");
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(ReciteActivity.this);
        progressDialog.setTitle("获取古诗词中");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(HOST_POETRY)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        poetryHandler.obtainMessage(1,response.body().string()).sendToTarget();
                    }else {
                        throw new IOException("Unexpected code:"+response);
                    }
                    //请求成功 弹窗关闭
                    progressDialog.dismiss();

                }catch(IOException e){
                    System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
                }
            }
        }).start();
        Log.d("request:","结束");
    }

    //响应onClick事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击上一题按钮
            case R.id.r_btn_previous:
                //如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowpager == 0) {
                    Toast.makeText(ReciteActivity.this, "已经到头啦！", Toast.LENGTH_SHORT).show();
                } else {
                    viewPager.setCurrentItem(--nowpager);
                }
                break;
//            点击提交按钮
            case R.id.r_btn_details:
                //否则初始化并展示提交对话框
//                initAlertDialog();
//                builder.show();

                //跳转到activity? or 使隐形TextView显现并 setText()
                //writing...

                /*认为请求结束之后 fragment就已经画好 所以之后触发按钮 不会更新UI界面*/
                //触发按钮 --> 给editor 赋值1
//                SharedPreferences mSharedPreferences = getSharedPreferences("Detail", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = mSharedPreferences.edit();
//                editor.putInt("isVisible", 1);
//                editor.commit();

                break;
            //点击下一题按钮
            case R.id.r_btn_next:
                //如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowpager == fragmentlists.size()) {
                    Toast.makeText(ReciteActivity.this, "已经是最后一首了！", Toast.LENGTH_SHORT).show();
                } else {
                    viewPager.setCurrentItem(++nowpager);
                }
                break;

        }
    }

    /**
     * viewpager适配器
     */
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }


        //获取条目
        @Override
        public Fragment getItem(int position) {
            return fragmentlists.get(position);
        }

        //数目
        @Override
        public int getCount() {
            return fragmentlists.size();
        }
    }

    /**
     * viewpager监听事件
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            nowpager = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //解析json数据
//    private void parseJSONWithGson(String jsonData){
//
//        try {
//            Gson gson = new Gson();
//            //Poetry poetry = gson.fromJson(jsonData, Poetry.class);
//            List<Poetry> poetryList = gson.fromJson(jsonData,new TypeToken<List<Poetry>>(){}.getType());
//            //清空之前的rData
//            rData = "";
//            for (Poetry poetry : poetryList) {
//                rData+="诗歌编号："+poetry.getPoetryid()+"\n"+"标题："+poetry.getSubject()+"\n"+"作者："+poetry.getAuthor()+"\n"+"朝代："+poetry.getDynasty()+"\n"+"内容："+poetry.getContent()+"\n"+"详细："+poetry.getDetail()+"\n"+"主题："+poetry.getTheme();
//                System.out.println("获得服务端返回解析Gson数据 poetryid为:" + poetry.getPoetryid());
//                System.out.println("获得服务端返回解析Gson数据 标题为:" + poetry.getSubject());
//                System.out.println("获得服务端返回解析Gson数据 作者为:" + poetry.getAuthor());
//                System.out.println("获得服务端返回解析Gson数据 朝代为:" + poetry.getDynasty());
//                System.out.println("获得服务端返回解析Gson数据 内容为:" + poetry.getContent());
//                System.out.println("获得服务端返回解析Gson数据 详细为:" + poetry.getDetail());
//                System.out.println("获得服务端返回解析Gson数据 主题为:" + poetry.getTheme());
//                //Log.d("ReciteActivity","id is"+poetry.getPoetryid());
//            }

            //子线程等更新ui 输出排版会出点问题（但是实时更新）
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            System.out.println("子线程输出："+rData);
//                            tv.setText(rData);
//                            sv.scrollBy(tv.getTextWidth(), 0);
//                        }
//                    });
//                }
//            }).start();

//        }catch(Exception e){
//            System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
//        }
//    }

}
