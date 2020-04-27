package com.xjr.util;

public class Testid_Grade {
	
	private int testid;
	private int grade;
	
	@Override
	public String toString() {
		return "Testid_Grade [testid=" + testid + ", grade=" + grade + "]";
	}

	public int getTestid() {
		return testid;
	}

	public void setTestid(int testid) {
		this.testid = testid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Testid_Grade(int testid,int grade) {
		
		this.testid = testid;
		this.grade = grade;
	}

}
