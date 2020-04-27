package com.xjr.model;

public class UserAnswer {
    @Override
	public String toString() {
		return "UserAnswer [id=" + id + ", userid=" + userid + ", testid=" + testid + ", useranswer=" + useranswer
				+ ", grade=" + grade + "]";
	}

	private Integer id;

    private Long userid;

    private Integer testid;

    private String useranswer;

    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}