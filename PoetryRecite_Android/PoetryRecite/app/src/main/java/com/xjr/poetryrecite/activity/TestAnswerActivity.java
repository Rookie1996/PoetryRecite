package com.xjr.poetryrecite.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjr.poetryrecite.R;
import com.xjr.poetryrecite.bean.TestBean;
import com.xjr.poetryrecite.bean.UserAnswer;
import com.xjr.poetryrecite.fragment.AnswerFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.xjr.poetryrecite.util.config.HOST;
import static com.xjr.poetryrecite.util.config.HOST_TEST;

public class TestAnswerActivity extends AppCompatActivity implements Chronometer.OnChronometerTickListener,View.OnClickListener{

    //声明位置错误 导致app闪退
    //Chronometer chronometer = (Chronometer)findViewById(R.id.chro_exam);
    //ViewPager viewPager = (ViewPager)findViewById(R.id.vp_answer);

    private Chronometer chronometer;
    private ViewPager viewPager;

    private ArrayList<Fragment> fragmentlists;
    private int second = 0;
    private int minute =0;
    private AlertDialog.Builder builder;
    private int nowpager = 0;
    private List<TestBean> testBeanList;

    private Handler testHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String responseData = msg.obj.toString();
                Log.i("获取返回信息",responseData);

                //删除本地试卷库 插入新的试卷 保持与服务器端数据库同步(以免出现服务器试题删除了，客户端多保存无效试题)
                DataSupport.deleteAll(TestBean.class);

                //解析json数据
                Gson gson = new Gson();
                testBeanList = gson.fromJson(responseData,new TypeToken<List<TestBean>>(){}.getType());
                for (TestBean test : testBeanList) {
                    //解析成Gson了
                    System.out.println(test.getTestid()+"\n");
                    //将试题存储在本地数据库中
                    //保存试题
                    test.save();

                    AnswerFragment answerFragment = new AnswerFragment();
                    answerFragment.setTestBean(test);
                    fragmentlists.add(answerFragment);
                }

                //设置适配器
                viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));

                }else{
                    Toast.makeText(TestAnswerActivity.this,"获取试卷失败！",Toast.LENGTH_SHORT).show();
                }

            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_answer_layout);

        chronometer = (Chronometer)findViewById(R.id.chro_exam);
        viewPager = (ViewPager)findViewById(R.id.vp_answer);

        Button btn_previous = (Button)findViewById(R.id._btn_previous);
        Button btn_next = (Button)findViewById(R.id._btn_next);
        Button btn_submit = (Button)findViewById(R.id._btn_submit);
        btn_previous.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        //向服务端发送请求
        sendRequestWithOkHttp();

        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        setChronometer();
    }

    /**
     * 设置计时器
     */
    private void setChronometer(){
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
    }

    /**
     * 计时器规则
     *
     * @param chronometer
     */
    public void onChronometerTick(Chronometer chronometer){
        second++;
        if(second == 59){
            minute++;
            second = 00;
        }
    }
    /**
     * 现在时间
     */
    private String nowtime(){
        if (second < 10){
            return (minute+":0"+second);
        }else {
            return (minute+":"+second);
        }
    }

    private void sendRequestWithOkHttp(){

        fragmentlists = new ArrayList<>();
        Log.d("request:","向服务端请求");
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(TestAnswerActivity.this);
        progressDialog.setTitle("获取试题中");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        //okhttp
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(HOST_TEST)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        testHandler.obtainMessage(1,response.body().string()).sendToTarget();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击上一题按钮
            case R.id._btn_previous:
                //如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowpager == 0) {
                    Toast.makeText(TestAnswerActivity.this, "已经到头啦！", Toast.LENGTH_SHORT).show();
                } else {
                    viewPager.setCurrentItem(--nowpager);
                }
                break;
//            点击提交按钮
            case R.id._btn_submit:
                //否则初始化并展示提交对话框
                initAlertDialog();
                builder.show();
                break;
            //点击下一题按钮
            case R.id._btn_next:
                //如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowpager == fragmentlists.size()) {
                    Toast.makeText(TestAnswerActivity.this, "已经是最后一题了！", Toast.LENGTH_SHORT).show();
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

    // 弹出是否确认交卷的对话框
    private void initAlertDialog() {
        //新建对话框
        builder = new AlertDialog.Builder(TestAnswerActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否确定交卷?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //取得用户id
                SharedPreferences userLogin = getSharedPreferences("userLogin", Context.MODE_PRIVATE);
                Long userid = Long.parseLong("0");
                String username = "";
                String password = "";
                userid = userLogin.getLong("userid",0);

//                计算分数
                int grade = 0;
//                判断题型 从本地数据库中抽取 使用LitePal
                for(TestBean test : testBeanList){
                    List<UserAnswer> uanswerList = DataSupport.where("userid = ? and testid = ?",""+userid,""+test.getTestid()).find(UserAnswer.class);

                    //Log.d("useranswer:",""+ uanswerList.get(0).getUseranswer());
                    if(uanswerList.size()>0&&uanswerList!=null){
                        if(test.getTypeid()!=3) {//简答题手动评分
                            if (test.getAnswer().equals(uanswerList.get(0).getUseranswer())) {
                                grade+=5;
                                uanswerList.get(0).setGrade(5);
                                uanswerList.get(0).updateAll("userid = ? and testid = ?",""+userid,""+test.getTestid());
                            } else if (test.getAnswer().equals("1")&&uanswerList.get(0).getUseranswer().equals("A")||test.getAnswer().equals("0")&&uanswerList.get(0).getUseranswer().equals("B")) {
                                grade+=5;
                                uanswerList.get(0).setGrade(5);
                                uanswerList.get(0).updateAll("userid = ? and testid = ?",""+userid,""+test.getTestid());
                            }else{
                                uanswerList.get(0).setGrade(0);
                                uanswerList.get(0).updateAll("userid = ? and testid = ?",""+userid,""+test.getTestid());
                            }
                        }
                    }

                }
                Log.d("经过计算后，该试卷得分为",""+ grade);

                //将本地useranswer上传到服务器端

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        EditText inputUsername = (EditText)findViewById(R.id.username_text);
//                        EditText inputPassword = (EditText)findViewById(R.id.password_text);
//                        String username = inputUsername.getText().toString().trim();
//                        String password = inputPassword.getText().toString().trim();
//
//                        System.out.println("post请求之前文本框中取得数据为:"+username+"，"+password);
//                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                        JSONObject json = new JSONObject();
//                        try {
//                            json.put("username", username);
//                            json.put("password", password);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        try{
//                            OkHttpClient client = new OkHttpClient();
//                            RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
//                            Request request = new Request.Builder()
//                                    .url(HOST+"userController/login.action")
//                                    .post(requestBody)
//                                    .build();
//                            Response response = client.newCall(request).execute();
//
//                        }catch(Exception e){
//                            System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
//                        }
//                    }
//                }).start();


//              传递所用时间time,总grade
                Intent intent = new Intent(TestAnswerActivity.this, TestGradeActivity.class);
                intent.putExtra("time", nowtime());
                intent.putExtra("grade", "" + grade);
                startActivity(intent);
                finish();

            }

        });
        builder.setNegativeButton("取消", null);
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
}
