package com.xjr.poetryrecite.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.sk.view.TextViewVertical;
import com.xjr.poetryrecite.R;
import com.xjr.poetryrecite.bean.Poetry;

/**
 * Created by Raffello on 2019/5/6.
 */

public class PoetryFragment extends Fragment {

//    TextViewVertical textViewVertical;
//    HorizontalScrollView horizontalScrollView;
    TextView poetry_tv;
    TextView poetry_details;
    Poetry poetry = null;
    public FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    public PoetryFragment() {

    }

    public void setPoetry(Poetry poetry) {
        this.poetry = poetry;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = View.inflate(mActivity, R.layout.poetry_show, null);
        //初始化组件 绑定布局上的组件
//        textViewVertical = (TextViewVertical) view.findViewById(R.id.tv);
//        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.sv);
        poetry_details = (TextView)view.findViewById(R.id.poetry_details);
        poetry_tv = (TextView)view.findViewById(R.id.poetry_tv);
        poetry_tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font/shouxieti.ttf"));
        poetry_details.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font/poetry_default.otf"));

//        SharedPreferences sp = getContext().getSharedPreferences("Detail", Context.MODE_PRIVATE);
//        Integer flag = 0;
//        flag = sp.getInt("isVisible",0);
//        if(flag==1){
//            poetry_details.setVisibility(View.VISIBLE);
//        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public void  initData(){

        try {
            System.out.println(poetry.getSubject());
            String poetryData = "诗词编号："+poetry.getPoetryid()+"\n"+"标题："+poetry.getSubject()+"\n"
                    +"作者："+poetry.getAuthor()+"\n"+"主题："+poetry.getTheme()+"\n"+"朝代："+poetry.getDynasty()+"\n"+"内容：\n"+poetry.getContent();

            poetry_tv.setMovementMethod(new ScrollingMovementMethod());
            poetry_tv.setGravity(Gravity.CENTER);
            poetry_tv.setText(poetryData);

            poetry_details.setMovementMethod(new ScrollingMovementMethod());
            poetry_details.setText("拓展知识：\n"+poetry.getDetail());


        }catch(Exception e){
            System.out.println("TextViewVertical初始化数据失败" + e.getMessage() + "," + e.getCause());
        }
    }
}