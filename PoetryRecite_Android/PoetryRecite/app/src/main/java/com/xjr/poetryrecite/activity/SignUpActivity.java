package com.xjr.poetryrecite.activity;

import android.content.Intent;
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

import com.xjr.poetryrecite.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.xjr.poetryrecite.util.config.HOST;
import static com.xjr.poetryrecite.util.config.HOST_SIGNUP;

public class SignUpActivity extends AppCompatActivity {

    //邮箱正则表达式
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        //变换字体
        EditText inputusername = findViewById(R.id.sp_username_text);
        final EditText inputpassword = findViewById(R.id.sp_password_text);
        EditText inputemail = findViewById(R.id.sp_email_text);
        Button signup_button = findViewById(R.id.sp_btn_signup);
        TextView title = findViewById(R.id.sp_title_view);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        inputusername.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        inputpassword.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        inputemail.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        signup_button.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        //密码默认不可见
        inputpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        CheckBox checkBox = (CheckBox)findViewById(R.id.sp_checkBox);
        checkBox.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    inputpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    inputpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //注册按钮响应
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputusername = findViewById(R.id.sp_username_text);
                EditText inputpassword = findViewById(R.id.sp_password_text);
                EditText inputemail = findViewById(R.id.sp_email_text);
                String sp_username = inputusername.getText().toString().trim();
                String sp_password = inputpassword.getText().toString().trim();
                String sp_email = inputemail.getText().toString().trim();
                if(sp_username.equals("")){
                    Toast.makeText(SignUpActivity.this, "用户名不为空！", Toast.LENGTH_SHORT).show();
                }
                if(sp_password.equals("")){
                    Toast.makeText(SignUpActivity.this, "密码不为空！", Toast.LENGTH_SHORT).show();
                }
                if(sp_email.equals("")){
                    Toast.makeText(SignUpActivity.this, "邮箱不为空！", Toast.LENGTH_SHORT).show();
                }
                if(!Pattern.matches(REGEX_EMAIL,sp_email)){
                    Toast.makeText(SignUpActivity.this, "邮箱格式错误！", Toast.LENGTH_SHORT).show();
                }
                if(!sp_username.equals("")&&!sp_password.equals("")&&!sp_email.equals("")&&Pattern.matches(REGEX_EMAIL,sp_email)){
                    sendRequestWithOkHttp();
                }
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                EditText inputUsername = findViewById(R.id.sp_username_text);
                EditText inputPassword = findViewById(R.id.sp_password_text);
                EditText inputEmail = findViewById(R.id.sp_email_text);
                String sp_username = inputUsername.getText().toString().trim();
                String sp_password = inputPassword.getText().toString().trim();
                String sp_email = inputEmail.getText().toString().trim();

                System.out.println("post请求之前文本框中取得数据为:"+sp_username+"，"+sp_password+"，"+sp_email);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject json = new JSONObject();
                try {
                    json.put("username", sp_username);
                    json.put("password", sp_password);
                    json.put("email",sp_email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    Request request = new Request.Builder()
                            .url(HOST_SIGNUP)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    System.out.println(responseData);
                    if(responseData.equals("success")){
                        Looper.prepare();
                        Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();

                        //注册成功，跳转到登录界面
//                        Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
//                        startActivity(intent);
//                        finish();

                    }else if(responseData.equals("repeat")){
                        Looper.prepare();
                        Toast.makeText(SignUpActivity.this, "账号已注册！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }else {
                        Looper.prepare();
                        Toast.makeText(SignUpActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //parseJSONWithGson(responseData);
                }catch(Exception e){
//                    Looper.prepare();
//                    Toast.makeText(SignUpActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
//                    Looper.loop();
                    Looper.prepare();
                    Toast.makeText(SignUpActivity.this, "服务器未响应！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
                }
            }
        }).start();
    }


}
