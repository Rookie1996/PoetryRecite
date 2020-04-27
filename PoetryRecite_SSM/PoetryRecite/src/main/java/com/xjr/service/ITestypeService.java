package com.xjr.service;

import java.util.List;

import com.xjr.model.Testype;

public interface ITestypeService {
	
	/**
	 * 查询所有试题类别
	 */
	public List<Testype> getTestype();
	
}
