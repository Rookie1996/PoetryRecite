package com.xjr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.dao.TestypeMapper;
import com.xjr.model.Testype;
import com.xjr.service.ITestypeService;

@Service
public class TestypeServiceImpl implements ITestypeService {

	@Autowired
	private TestypeMapper testypeMapper;
	
	@Override
	public List<Testype> getTestype() {
		// TODO Auto-generated method stub
		List<Testype> list = testypeMapper.selectByExample(null);
		return list;
	}

}
