package com.xjr.poetryrecite.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xjr.poetryrecite.R;
import com.xjr.poetryrecite.bean.TestBean;
import com.xjr.poetryrecite.bean.UserAnswer;


import org.litepal.crud.DataSupport;

import java.io.Console;
import java.util.List;

/**
 * Created by Raffello on 2019/5/5.
 */

public class AnswerFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
    private TextView tv_tips;
    private String option = "";
    private RadioGroup rg_base;
    private TextView tv_title;
    private EditText et_answer;
    TestBean test = null;
    public FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    public AnswerFragment() {

    }

    public void setTestBean(TestBean test){
        this.test = test;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = View.inflate(mActivity,R.layout.fragment_test,null);

        tv_title = (TextView) view.findViewById(R.id._tv_title);
        tv_tips = (TextView)view.findViewById(R.id._tv_tips);
        rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
        et_answer = (EditText)view.findViewById(R.id.et_answer);
        et_answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果改变了，并且文本长度>0
                if (s.toString().length() > 0) {
                    //设置用户答案
                    SharedPreferences sp = getContext().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
                    Long userid = sp.getLong("userid",Long.parseLong("0"));
                    UserAnswer userAnswer = new UserAnswer();
                    userAnswer.setId(0);
                    userAnswer.setUserid(userid);
                    userAnswer.setTestid(test.getTestid());
                    userAnswer.setUseranswer(s.toString());
                    //保存用户答题记录到本地
                    List<UserAnswer> isExist = DataSupport.where("userid = ? and testid = ?",""+userid,""+test.getTestid()).find(UserAnswer.class);
                    if(isExist.size()==0){
                        userAnswer.save();//可以对已存储的进行更新
                    }else {
                        userAnswer.updateAll("userid = ? and testid = ?",""+userid,""+test.getTestid());
                    }
                    System.out.println("提交useranswer："+userAnswer);

                }
            }
        });

        //如果是选择题，找id,设置监听事件
        if ("1".equals(test.getTypeid() + "")) {

            rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
            rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
            rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
            rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
            rg_base.setOnCheckedChangeListener(this);
        }
        //如果是判断题，找id,使C,D选项不可见，设置监听事件
        else if ("2".equals(test.getTypeid() + "")) {
            //LogUtils.e("initView: " + questBean.getQ_type());
            rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
            rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
            rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
            rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
            //CD不可见
            rb_option_c.setVisibility(View.GONE);
            rb_option_d.setVisibility(View.GONE);
            //监听事件
            rg_base.setOnCheckedChangeListener(this);
        }
        //如果是简答题，找id,使选项组不可见，使EditText出现。
        else if ("3".equals(test.getTypeid() + "")) {

            et_answer = (EditText) view.findViewById(R.id.et_answer);
            et_answer.setVisibility(View.VISIBLE);
            rg_base.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public void initData() {

        //System.out.println(test.getTestcontent());
        String type = "";
        if (test.getTypeid()==1){
            type = "选择题";
        }else if(test.getTypeid()==2){
            type = "判断题";
        }else if(test.getTypeid()==3){
            type = "简答题";
        }
        tv_title.setText("" + "("+type+")"+test.getTestcontent());
        //如果没有传递数据，则退出
        if (test == null) {
            Log.d( "initData:test==null","test==null");
            return;
        }
//        如果是选择题，对应选项赋值
        if ("1".equals(test.getTypeid() + "")) {
            rb_option_a.setText("" + test.getOptiona());
            rb_option_b.setText("" + test.getOptionb());
            rb_option_c.setText("" + test.getOptionc());
            rb_option_d.setText("" + test.getOptiond());
            if(test.getTips()==null){
                tv_tips.setText("Tips:无");
            }else{
                tv_tips.setText("Tips:"+test.getTips());
            }

        }
//        如果是判断题，AB设置为对，错。
        else if ("2".equals(test.getTypeid() + "")) {
            rb_option_a.setText("对");
            rb_option_b.setText("错");
            if(test.getTips()==null){
                tv_tips.setText("Tips:无");
            }else{
                tv_tips.setText("Tips:"+test.getTips());
            }
        }
//        如果是简答题或者其他,不做数据填充
        else {
            if(test.getTips()==null){
                tv_tips.setText("Tips:无");
            }else{
                tv_tips.setText("Tips:"+test.getTips());
            }
        }
    }

    public void onCheckedChanged(RadioGroup group,int checkedId){
        if (checkedId == rb_option_a.getId()) {
            option = "A";
        } else if (checkedId == rb_option_b.getId()) {
            option = "B";
        } else if (checkedId == rb_option_c.getId()) {
            option = "C";
        } else if (checkedId == rb_option_d.getId()) {
            option = "D";
        }

        //设置用户答案
        SharedPreferences sp = getContext().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        Long userid = sp.getLong("userid",Long.parseLong("0"));
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setId(0);
        userAnswer.setUserid(userid);
        userAnswer.setTestid(test.getTestid());
        userAnswer.setUseranswer(option);
        //更新本地数据库

        //wait to do...
        //DataSupport.deleteAll(UserAnswer.class);
        //userAnswer.deleteAll(UserAnswer.class);错误写法！
//        DataSupport.where("age = ? and sex = ?", "" + age, sex).find(Comment.class);
        List<UserAnswer> isExist = DataSupport.where("userid = ? and testid = ?",""+userid,""+test.getTestid()).find(UserAnswer.class);
        if(isExist.size()==0){
            userAnswer.save();//可以对已存储的进行更新
        }else {
            userAnswer.updateAll("userid = ? and testid = ?",""+userid,""+test.getTestid());
        }
        //保存用户答题记录到本地
//        userAnswer.save();
        System.out.println("提交useranswer："+userAnswer);
    }
}

