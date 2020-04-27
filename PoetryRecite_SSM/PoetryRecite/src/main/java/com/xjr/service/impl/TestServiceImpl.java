package com.xjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.dao.TestMapper;
import com.xjr.model.Test;
import com.xjr.model.TestExample;
import com.xjr.model.TestExample.Criteria;
import com.xjr.service.ITestService;

@Service("testService")
public class TestServiceImpl implements ITestService{
	
	@Resource
	private TestMapper testMapper;
	
	public TestMapper getTestMapper() {
		return testMapper;
	}

	@Autowired
	public void setTestMapper(TestMapper testMapper) {
		this.testMapper = testMapper;
	}

	@Override
	public List<Test> getAll() {
		// TODO Auto-generated method stub
		return testMapper.selectAll();
	}

	@Override
	public Test getTest(Integer id) {
		// TODO Auto-generated method stub
		Test test = testMapper.selectByPrimaryKey(id);
		return test;
	}

	@Override
	public void saveTest(Test test) {
		// TODO Auto-generated method stub
		Integer testid = 1;
		test.setTestid(testid);
		testMapper.insert(test);
	}

	@Override
	public void updateTest(Test test) {
		// TODO Auto-generated method stub
		testMapper.updateByPrimaryKeySelective(test);
	}

	@Override
	public void deleteBatch(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		TestExample t = new TestExample();
		Criteria c = t.createCriteria();
		c.andTestidIn(del_ids);
		testMapper.deleteByExample(t);
	}

	@Override
	public void deleteTestById(Integer id) {
		// TODO Auto-generated method stub
		testMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Test> selectByContentAndType(String testcontent,Integer typeId) {
		// TODO Auto-generated method stub
		return testMapper.selectByContentAndType(testcontent,typeId);
	}

	@Override
	public List<Test> selectTestByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return testMapper.selectTestByTypeId(typeId);
	}

	@Override
	public List<String> getTestContent() {
		// TODO Auto-generated method stub
		return testMapper.getTestContent();
	}

}
