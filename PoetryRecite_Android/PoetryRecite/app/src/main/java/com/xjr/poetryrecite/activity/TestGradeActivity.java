package com.xjr.poetryrecite.activity;

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
import com.xjr.poetryrecite.util.TimeUtils;

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

import static com.xjr.poetryrecite.util.config.HOST_ANSWER;

//记录本次答题 简答题未批 需提交到服务器端
public class TestGradeActivity extends AppCompatActivity {


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

    //上传服务器 useranswer
    private Handler answerHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String responseData = msg.obj.toString();
                Log.i("获取上传返回信息",responseData);

                //设置适配器
                //lvGradeScoreDetail.setAdapter(new MyAdapter());

            }else{
                Toast.makeText(TestGradeActivity.this,"上传失败！",Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_grade_layout);

        //接收答题用时time,grade
        grade = getIntent().getStringExtra("grade");
        time = getIntent().getStringExtra("time");

        end_time = TimeUtils.getNowTime();
        num = DataSupport.count(TestBean.class)+"";
        SharedPreferences sp = getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        username = sp.getString("username","null");
        Long tUserid = Long.parseLong("0");
        userid = sp.getLong("userid",tUserid);

        //创建新的sharePreference保存 time grade end_time num
        SharedPreferences gradesp = getSharedPreferences("gradeInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gradesp.edit();
        editor.putString("grade", grade);
        editor.putString("time",time);
        editor.putString("end_time",end_time);
        editor.putString("num",num);
        editor.commit();

        tvTestGradeName = (TextView) findViewById(R.id.tv_test_grade_name);
        tvTestGradeScore = (TextView)findViewById(R.id.tv_test_grade_score);
        tvTestGradeUseTime = (TextView)findViewById(R.id.tv_test_grade_use_time);
        tvTestGradeNum = (TextView)findViewById(R.id.tv_test_grade_num);
        tvTestGradeEndTime = (TextView)findViewById(R.id.tv_test_grade_end_time);
        lvGradeScoreDetail = (ListView)findViewById(R.id.lv_grade_score_detail);

        //设置显示成绩分数
        tvTestGradeName.setText(username + " 您的本次得分是(不包含简答题)：");
        tvTestGradeScore.setText(grade + "分");
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
        //向服务端发送请求
        List<UserAnswer> answerList = DataSupport.findAll(UserAnswer.class);
        if (answerList.size()>0&&answerList!=null){
            for (UserAnswer useranswer : answerList){
                sendRequestWithOkHttp(useranswer);
            }
        }
        lvGradeScoreDetail.setAdapter(new MyAdapter());
    }

    //将用户答题记录提交到服务器
    private void sendRequestWithOkHttp(UserAnswer useranswer){
        //解析用handle做
        final Integer id = useranswer.getId();
        final Long userid = useranswer.getUserid();
        //Log.i("userid:",userid+"");
        final Integer testid = useranswer.getTestid();
        final String uanswer = useranswer.getUseranswer();
        final Integer grade = useranswer.getGrade();

        //okhttp
        new Thread(new Runnable() {
            @Override
            public void run() {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject json = new JSONObject();
                try {
                    json.put("id",id);
                    json.put("userid",userid);
                    json.put("testid",testid);
                    json.put("useranswer",uanswer);
                    json.put("grade",grade);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    Request request = new Request.Builder()
                            .post(requestBody)
                            .url(HOST_ANSWER)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        answerHandler.obtainMessage(1,response.body().string()).sendToTarget();
                    }else {
                        throw new IOException("Unexpected code:"+response);
                    }

                }catch(IOException e){
                    System.out.println("请求失败" + e.getMessage() + "," + e.getCause());
                }
            }
        }).start();
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(TestGradeActivity.this).inflate(R.layout.list_item_activity_score, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_activty_score_title);
                holder.tvOptionA = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionA);
                holder.tvOptionB = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionB);
                holder.tvOptionC = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionC);
                holder.tvOptionD = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionD);
                holder.tvRightAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_right);
                holder.tvWrongAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_wrong);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            查询数据库
            //QuestBean questBeenC = LoveDao.queryLove(Integer.parseInt(titleName.get(position).toString()));
            //String title = questBeenC.getTitle();

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

                //查询用户答案  这里要带上userid进行查询！！！
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
                Toast.makeText(TestGradeActivity.this, "本地试题数据库为空！", Toast.LENGTH_SHORT).show();
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvTitle, tvRightAnswer, tvWrongAnswer, tvOptionA,
                    tvOptionB, tvOptionC, tvOptionD;
        }

    }
}
