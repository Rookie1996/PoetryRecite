package com.xjr.util;

import java.math.BigDecimal;
import java.util.List;

import com.xjr.model.SumGradeView;

public class CountValues {
	
	public int countValues(int min, int max,List<SumGradeView> list) {
	
		//SumGradeView   --->    Long euserid;    String eusername;    BigDecimal egradeSum;
		int count = 0;
		int size = list.size();
		BigDecimal num = new BigDecimal(0);
		BigDecimal Min = new BigDecimal(min);
		BigDecimal Max = new BigDecimal(max);
		
		for(int i=0;i<size;i++) {
			num = list.get(i).getEgradeSum();
			if(num.compareTo(Min)>-1&&num.compareTo(Max)<1) {
				count++;
			}
		}
		
		return count;
	}
	

}
