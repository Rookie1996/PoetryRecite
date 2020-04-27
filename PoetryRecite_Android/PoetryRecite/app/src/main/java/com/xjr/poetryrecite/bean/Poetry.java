package com.xjr.poetryrecite.bean;

/**
 * Created by Raffello on 2019/5/3.
 */

public class Poetry {
    private Integer poetryid;
    private String subject;
    private String author;
    private String dynasty;
    private String content;
    private String detail;
    private String theme;

    @Override
    public String toString() {
        return "Poetry{" +
                "poetryid=" + poetryid +
                ", subject='" + subject + '\'' +
                ", author='" + author + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", content='" + content + '\'' +
                ", detail='" + detail + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }

    public Integer getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(Integer poetryid) {
        this.poetryid = poetryid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
