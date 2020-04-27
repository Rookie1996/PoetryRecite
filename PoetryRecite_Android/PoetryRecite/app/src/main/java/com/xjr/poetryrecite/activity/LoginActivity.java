package com.xjr.poetryrecite.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xjr.poetryrecite.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.xjr.poetryrecite.bean.User;

import static com.xjr.poetryrecite.util.config.HOST;
import static com.xjr.poetryrecite.util.config.HOST_LOGIN;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_layout);
            //为按钮和注册,checkbox添加点击事件
            Button btn_login = (Button)findViewById(R.id.btn_login);
            btn_login.setOnClickListener(this);
            TextView link_signup = (TextView) findViewById(R.id.link_signup);
            link_signup.setOnClickListener(this);

            //为文字标题变换字体
            TextView title = (TextView)findViewById(R.id.title_view);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            //为按钮,注册变换字体
            btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            link_signup.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            //为username,password提示变换字体
            EditText username = (EditText)findViewById(R.id.username_text);
            final EditText password = (EditText)findViewById(R.id.password_text);
            username.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            password.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            //默认隐藏密码
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
            checkBox.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        //如果选中，显示密码
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        //否则隐藏密码
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });
        }

        private void sendRequestWithOkHttp() {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    EditText inputUsername = (EditText)findViewById(R.id.username_text);
                    EditText inputPassword = (EditText)findViewById(R.id.password_text);
                    String username = inputUsername.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();

                    System.out.println("post请求之前文本框中取得数据为:"+username+"，"+password);
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    JSONObject json = new JSONObject();
                    try {
                        json.put("username", username);
                        json.put("password", password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try{
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                        Request request = new Request.Builder()
                                .url(HOST_LOGIN)
                                .post(requestBody)
                                .build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        //System.out.println(responseData);
                        parseJSONWithGson(responseData);
                    }catch(Exception e){
                        System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
                    }
                }
            }).start();
        }

        private void parseJSONWithGson(String jsonData){
            try {
                Gson gson = new Gson();
                User user = gson.fromJson(jsonData, User.class);
                System.out.println("获得服务端返回解析Gson数据的为:" + user);
                EditText inputPassword = (EditText) findViewById(R.id.password_text);
                String password = inputPassword.getText().toString().trim();
                String server_password = user.getPassword();

                if (!server_password.equals(password)) {
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else if (server_password.equals(password)) {

                    //登陆成功！--> 存储用户信息
                    SharedPreferences mSharedPreferences = getSharedPreferences("userLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putLong("userid", user.getUserid());
                    editor.putString("username",user.getUsername());
                    editor.putString("password",user.getPassword());
                    editor.commit();

                    //登录成功进入菜单界面menu
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    //可以返回登录界面
                    //finish();
                }
            }catch(Exception e){
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "用户不存在！请前往注册", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_login:
                    //点击登录按钮
                    EditText inputUsername = (EditText)findViewById(R.id.username_text);
                    EditText inputPassword = (EditText)findViewById(R.id.password_text);
                    String username = inputUsername.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();
                    if(username.equals("")){
                        Toast.makeText(LoginActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
                    }
                    if(password.equals("")){
                        Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    }
                    if(!username.equals("")&&!password.equals("")){
                        sendRequestWithOkHttp();
                    }
                    break;
                //如果点击了注册链接，则跳转到注册页面
                case R.id.link_signup:
                    Intent intent = new Intent(LoginActivity.this , SignUpActivity.class);
                    startActivity(intent);
                    //finish();
                    //可以返回登录界面
                    break;
            }
        }

}

