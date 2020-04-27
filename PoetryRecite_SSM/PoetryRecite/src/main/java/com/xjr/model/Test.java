package com.xjr.model;

public class Test {
    @Override
	public String toString() {
		return "Test [testid=" + testid + ", typeid=" + typeid + ", testcontent=" + testcontent + ", optiona=" + optiona
				+ ", optionb=" + optionb + ", optionc=" + optionc + ", optiond=" + optiond + ", answer=" + answer
				+ ", explaination=" + explaination + ", tips=" + tips + "]";
	}

	private Integer testid;

    private Integer typeid;

    private String testcontent;

    private String optiona;

    private String optionb;

    private String optionc;

    private String optiond;

    private String answer;

    private String explaination;

    private String tips;

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTestcontent() {
        return testcontent;
    }

    public void setTestcontent(String testcontent) {
        this.testcontent = testcontent == null ? null : testcontent.trim();
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona == null ? null : optiona.trim();
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb == null ? null : optionb.trim();
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc == null ? null : optionc.trim();
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond == null ? null : optiond.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination == null ? null : explaination.trim();
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }
}