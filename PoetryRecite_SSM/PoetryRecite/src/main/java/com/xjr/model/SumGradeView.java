package com.xjr.model;

import java.math.BigDecimal;

public class SumGradeView {
    @Override
	public String toString() {
		return "SumGradeView [euserid=" + euserid + ", eusername=" + eusername + ", egradeSum=" + egradeSum + "]";
	}

	private Long euserid;

    private String eusername;

    private BigDecimal egradeSum;

    public Long getEuserid() {
        return euserid;
    }

    public void setEuserid(Long euserid) {
        this.euserid = euserid;
    }

    public String getEusername() {
        return eusername;
    }

    public void setEusername(String eusername) {
        this.eusername = eusername == null ? null : eusername.trim();
    }

    public BigDecimal getEgradeSum() {
        return egradeSum;
    }

    public void setEgradeSum(BigDecimal egradeSum) {
        this.egradeSum = egradeSum;
    }
}