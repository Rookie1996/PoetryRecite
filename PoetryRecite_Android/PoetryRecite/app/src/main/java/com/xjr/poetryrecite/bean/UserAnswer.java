package com.xjr.poetryrecite.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by Raffello on 2019/5/6.
 */

public class UserAnswer extends DataSupport{
    int id;
    Long userid;
    int testid;
    String useranswer;
    int grade;

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", userid=" + userid +
                ", testid=" + testid +
                ", useranswer='" + useranswer + '\'' +
                ", grade=" + grade +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
