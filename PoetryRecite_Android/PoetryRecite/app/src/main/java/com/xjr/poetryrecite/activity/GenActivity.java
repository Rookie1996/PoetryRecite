package com.xjr.poetryrecite.activity;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sk.view.TextViewVertical;
import com.xjr.poetryrecite.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.xjr.poetryrecite.util.config.HOST_GENPOETRY;

public class GenActivity extends AppCompatActivity {

//    private HorizontalScrollView sv;
//    private TextViewVertical tv;
    private  TextView tv;
//    private static String poetry = "";

    private Handler genPoetryHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String responseData = msg.obj.toString();

                String [] Data = responseData.split("。");
                String rData = "";
                for (String data: Data){
                    System.out.println("返回数据："+data);
                    rData += data+"。\n";
                }
                responseData = rData;
                Log.i("获取返回自动生成诗歌",responseData);


                //显示数据
//                System.out.println("返回数据："+responseData);
                tv.setText(responseData);

            }else{
                Toast.makeText(GenActivity.this,"获取自动生成诗歌失败！",Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen_layout);

//        tv = (TextViewVertical) findViewById(R.id.gen_tv);
//        sv = (HorizontalScrollView) findViewById(R.id.gen_sv);
        tv = (TextView)findViewById(R.id.gen_tv);
        Button btn_gen = (Button)findViewById(R.id.btn_genPoetry);
        btn_gen.setTypeface(Typeface.createFromAsset(getAssets(), "font/genPoetry.ttf"));
        tv.setTypeface(Typeface.createFromAsset(getAssets(), "font/shoujinti .ttf"));

        btn_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //okhttp
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(HOST_GENPOETRY)
                                    .build();
                            Response response = client.newCall(request).execute();
                            if(response.isSuccessful()){
                                genPoetryHandler.obtainMessage(1,response.body().string()).sendToTarget();
                            }else {
                                throw new IOException("Unexpected code:"+response);
                            }

                        }catch(IOException e){
                            System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
                        }
                    }
                }).start();
            }
        });

//        poetry = "玉犯为能钧，一处与还泥。\n今儿是楼近，长断紫林沙。\n背石应姓浮，空郎无堪老。\n醉不欲说时，莫应知秦家。";
//        tv.setText(poetry);
    }
}
