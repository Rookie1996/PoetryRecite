package com.xjr.poetryrecite.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjr.poetryrecite.R;
import com.xjr.poetryrecite.bean.TestBean;
import com.xjr.poetryrecite.bean.UserAnswer;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xjr.poetryrecite.util.config.HOST_ANSWER_DOWNLOAD;

//统计分数界面 向服务器请求 (简答题评分后的)useranswer
public class GradeActivity extends AppCompatActivity {

    TextView tvTestGradeName;
    TextView tvTestGradeScore;
    TextView tvTestGradeUseTime;
    ListView lvGradeScoreDetail;
    TextView tvTestGradeNum;
    TextView tvTestGradeEndTime;
    private ArrayList<CharSequence> titleId;
    private String grade;
    private String time;
    private String num;
    private String end_time;
    private String username;
    private long userid;

    private Handler gradeHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String responseData = msg.obj.toString();
                Log.i("获取返回信息",responseData);

                //删除本地数据 插入新的答题记录 保持与服务器端数据库同步(以免出现服务器记录删除了，客户端多保存无效记录)
                DataSupport.deleteAll(UserAnswer.class);

                //解析json数据
                Gson gson = new Gson();
                List<UserAnswer> userAnswerList = gson.fromJson(responseData,new TypeToken<List<UserAnswer>>(){}.getType());
                for (UserAnswer userAnswer : userAnswerList) {
                    //解析成Gson了
                    //System.out.println(test.getTestid()+"\n");
                    //将试题存储在本地数据库中
                    //保存试题
                    userAnswer.save();
                }

                //设置适配器
                lvGradeScoreDetail.setAdapter(new GradeActivity.MyAdapter());

            }else{
                Toast.makeText(GradeActivity.this,"获取答题记录失败！",Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_layout);

        SharedPreferences userLoginsp = getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        username = userLoginsp.getString("username","null");
        Long tUserid = Long.parseLong("0");
        userid = userLoginsp.getLong("userid",tUserid);

        SharedPreferences testgradesp = getSharedPreferences("gradeInfo", Context.MODE_PRIVATE);
        grade = testgradesp.getString("grade","0");
        time = testgradesp.getString("time","0");
        end_time = testgradesp.getString("end_time","0");
        num = testgradesp.getString("num","0");

        tvTestGradeName = (TextView) findViewById(R.id.tv_test_grade_name);
        tvTestGradeScore = (TextView)findViewById(R.id.tv_test_grade_score);
        tvTestGradeUseTime = (TextView)findViewById(R.id.tv_test_grade_use_time);
        tvTestGradeNum = (TextView)findViewById(R.id.tv_test_grade_num);
        tvTestGradeEndTime = (TextView)findViewById(R.id.tv_test_grade_end_time);
        lvGradeScoreDetail = (ListView)findViewById(R.id.lv_grade_score_detail);

        //向服务端发送请求
        sendRequestWithOkHttp();

        //设置显示成绩分数
        //查询本地数据库UserAnswer
        Long userid = userLoginsp.getLong("userid",0);
        List<UserAnswer> gradeUserAnswerList = DataSupport.where("userid = ?",""+userid).find(UserAnswer.class);
//        Integer Grade = Integer.parseInt(grade); 这里是将之前传的选择判断grade传递过来 会重复计算
        Integer Grade = Integer.parseInt("0");
        for (UserAnswer userAnswer : gradeUserAnswerList){
            String temp = userAnswer.getGrade()+"";
            if(temp==""){
               Grade += 0;
            }else {
                Grade += userAnswer.getGrade();
            }
        }

        tvTestGradeName.setText(username + " 您的本次得分是(包含简答题)：");
        tvTestGradeScore.setText(Grade + "分");
        tvTestGradeUseTime.setText("使用时间：" + time);
        tvTestGradeNum.setText("题目数量：" + num);
        tvTestGradeEndTime.setText("交卷时间：" + end_time);

        //为题目列表赋值
        titleId = new ArrayList<CharSequence>();
        List<TestBean> testList = DataSupport.findAll(TestBean.class);
        if(testList.size()>0&&testList!=null){
            for (TestBean test : testList) {
                titleId.add(test.getTestid()+"");
            }
        }

    }


    private void sendRequestWithOkHttp(){
        Log.d("request:","向服务端请求");
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(GradeActivity.this);
        progressDialog.setTitle("获取答题记录中");
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
                            .url(HOST_ANSWER_DOWNLOAD)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        gradeHandler.obtainMessage(1,response.body().string()).sendToTarget();
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

    /**
     * 题目列表适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titleId != null ? titleId.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return titleId.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            listview优化，复用布局以及id
            GradeActivity.MyAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new GradeActivity.MyAdapter.ViewHolder();
                convertView = LayoutInflater.from(GradeActivity.this).inflate(R.layout.list_item_activity_score, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_activty_score_title);
                holder.tvOptionA = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionA);
                holder.tvOptionB = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionB);
                holder.tvOptionC = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionC);
                holder.tvOptionD = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionD);
                holder.tvRightAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_right);
                holder.tvWrongAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_wrong);


                convertView.setTag(holder);
            } else {
                holder = (GradeActivity.MyAdapter.ViewHolder) convertView.getTag();
            }

            List<TestBean> testList = DataSupport.findAll(TestBean.class);

//            List<TestBean> list = DataSupport.where("testid = ?", "" + 1001).find(TestBean.class);
//            if (list.size() > 0 && list != null) {
//                System.out.println("testid=1001 :" + list.get(0));
//            }

            if (testList.size() > 0 && testList != null) {   //本地数据库不为空

                //注意 查询下本地试题数据库 验证试题是否重复插入了？？？
                //每次要从服务端下载试题 使用的是save() 不知道有没有重复
                //验证结果 ：果然重复了

                UserAnswer userAnswer = new UserAnswer();
                TestBean test = testList.get(position);
                String type = "";
                if (test.getTypeid()==1){
                    type = "选择题";
                }else if(test.getTypeid()==2){
                    type = "判断题";
                }else if(test.getTypeid()==3){
                    type = "简答题";
                }
                String title = "("+type+")"+test.getTestcontent();
                holder.tvTitle.setText(position + 1 + "." + title);
//            设置题目数据
                //  显示正确答案以及填写答案
                String rightAnswer = test.getAnswer();
                holder.tvRightAnswer.setText("正确答案：" + rightAnswer);

                //查询用户答案
                List<UserAnswer> userAnswerList = DataSupport.where("testid = ? and userid = ?", "" + test.getTestid(),""+userid).find(UserAnswer.class);
                if (userAnswerList.size() > 0 && userAnswerList != null) {
                    userAnswer = userAnswerList.get(0);
                } else {
                    //userAnswer初始化过了
                    //Toast.makeText(TestGradeActivity.this, "本地用户答题数据库无对应记录！", Toast.LENGTH_SHORT).show();
                }

                holder.tvTitle.setTextColor(Color.parseColor("#8a000000"));
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.tvOptionA.setVisibility(View.VISIBLE);
                holder.tvOptionB.setVisibility(View.VISIBLE);
                holder.tvOptionC.setVisibility(View.VISIBLE);
                holder.tvOptionD.setVisibility(View.VISIBLE);
                holder.tvRightAnswer.setVisibility(View.VISIBLE);
                holder.tvWrongAnswer.setVisibility(View.VISIBLE);

                if (("1".equals(test.getTypeid() + ""))) {
                    String optionA = test.getOptiona();
                    holder.tvOptionA.setText("A." + optionA);
                    String optionB = test.getOptionb();
                    holder.tvOptionB.setText("B." + optionB);
                    String optionC = test.getOptionc();
                    holder.tvOptionC.setText("C." + optionC);
                    String optionD = test.getOptiond();
                    holder.tvOptionD.setText("D." + optionD);
                    String YourAnswer = userAnswer.getUseranswer();
                    holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);

                    if (!rightAnswer.equals(YourAnswer)) {
                        holder.tvTitle.setTextColor(Color.RED);
                    }
                }else if ("2".equals(test.getTypeid() + "")) {
                    holder.tvOptionA.setText("1、对");
                    holder.tvOptionB.setText("2、错");
                    holder.tvOptionC.setVisibility(View.GONE);
                    holder.tvOptionD.setVisibility(View.GONE);
                    String YourAnswer = userAnswer.getUseranswer();
                    if ("A".equals(YourAnswer)) {
                        holder.tvWrongAnswer.setText("你选择了：对");
                    } else if ("B".equals(YourAnswer)) {
                        holder.tvWrongAnswer.setText("你选择了：错");
                    } else {
                        holder.tvWrongAnswer.setText("你没有进行选择");
                    }
                    //rightAnswer 0-B 1-A
                    String rAnswer = "";
                    if(rightAnswer.equals("0")){
                        rAnswer = "B";
                    }else if (rightAnswer.equals("1")){
                        rAnswer = "A";
                    }
                    //错误涂红
                    if (!rAnswer.equals(YourAnswer)) {
                        holder.tvTitle.setTextColor(Color.RED);
                    }
                }else if("3".equals(test.getTypeid() + "")){//简答题

                    holder.tvOptionA.setVisibility(View.GONE);
                    holder.tvOptionB.setVisibility(View.GONE);
                    holder.tvOptionC.setVisibility(View.GONE);
                    holder.tvOptionD.setVisibility(View.GONE);
                    String YourAnswer = userAnswer.getUseranswer();
                    holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);
                    if (!rightAnswer.equals(YourAnswer)) {
                        holder.tvTitle.setTextColor(Color.RED);
                    }
                }

            } else {
                Toast.makeText(GradeActivity.this, "本地试题数据库为空！", Toast.LENGTH_SHORT).show();
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvTitle, tvRightAnswer, tvWrongAnswer, tvOptionA,
                    tvOptionB, tvOptionC, tvOptionD;
        }

    }
}
