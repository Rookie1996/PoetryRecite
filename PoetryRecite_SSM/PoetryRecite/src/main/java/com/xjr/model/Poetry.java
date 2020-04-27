package com.xjr.model;

public class Poetry {
    @Override
	public String toString() {
		return "Poetry [poetryid=" + poetryid + ", subject=" + subject + ", author=" + author + ", dynasty=" + dynasty
				+ ", content=" + content + ", detail=" + detail + ", theme=" + theme + "]";
	}

	private Integer poetryid;

    private String subject;

    private String author;

    private String dynasty;

    private String content;

    private String detail;

    private String theme;

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
        this.subject = subject == null ? null : subject.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty == null ? null : dynasty.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
}