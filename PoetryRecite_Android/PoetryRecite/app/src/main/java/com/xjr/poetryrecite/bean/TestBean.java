package com.xjr.poetryrecite.bean;

/**
 * Created by Raffello on 2019/5/5.
 */

import org.litepal.crud.DataSupport;

/**
 * 试卷pojo类
 */
public class TestBean extends DataSupport{
    private int testid;
    private int typeid;
    private String testcontent;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private String explaination;
    private String tips;
    private String userAnswer;

    @Override
    public String toString() {
        return "TestBean{" +
                "testid=" + testid +
                ", typeid=" + typeid +
                ", testcontent='" + testcontent + '\'' +
                ", optionA='" + optiona + '\'' +
                ", optionB='" + optionb + '\'' +
                ", optionC='" + optionc + '\'' +
                ", optionD='" + optiond + '\'' +
                ", answer='" + answer + '\'' +
                ", explaination='" + explaination + '\'' +
                ", tips='" + tips + '\'' +
                '}';
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }


    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTestcontent() {
        return testcontent;
    }

    public void setTestcontent(String testcontent) {
        this.testcontent = testcontent;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
