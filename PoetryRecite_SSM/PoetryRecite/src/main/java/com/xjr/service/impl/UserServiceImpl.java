package com.xjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.dao.SumGradeViewMapper;
import com.xjr.dao.TestMapper;
import com.xjr.dao.UserAnswerMapper;
import com.xjr.dao.UserMapper;
import com.xjr.model.SumGradeView;
import com.xjr.model.Test;
import com.xjr.model.User;
import com.xjr.model.UserAnswer;
import com.xjr.model.UserExample;
import com.xjr.model.UserExample.Criteria;
import com.xjr.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private SumGradeViewMapper sumGradeViewMapper;
	
	@Resource
	private UserAnswerMapper userAnswerMapper;
	
	@Resource
	private TestMapper testMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.selectByExample(null);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		Long userid = (long)1;
		user.setUserid(userid);
		userMapper.insert(user);
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Long userid = user.getUserid();
		userMapper.deleteByPrimaryKey(userid);
	}
	
	@Override
	public boolean checkUser(String userName) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		long count = userMapper.countByExample(example);
		return count==0;
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatch(List<Long> del_ids) {
		// TODO Auto-generated method stub
		UserExample u = new UserExample();
		Criteria c = u.createCriteria();
		//delete from xxx where userid in(1,2,3);
		c.andUseridIn(del_ids);
		userMapper.deleteByExample(u);
		
	}

	@Override
	public List<User> getLikeUser(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectByLikeUsername(userName);
	}

	@Override
	public User login(String username) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByUsername(username);
		return user;
	}

	@Override
	public void signup(User user) {
		// TODO Auto-generated method stub
		Long userid = (long)1;
		user.setUserid(userid);
		userMapper.insert(user);
	}
//**************************************************************************
	@Override
	public List<SumGradeView> getSumGrade() {
		// TODO Auto-generated method stub
		List<SumGradeView> list = new ArrayList<SumGradeView>();
		list = sumGradeViewMapper.getAll();
		return list;
	}

	@Override
	public Long getUserid(String username) {
		// TODO Auto-generated method stub
		Long userid = new Long(0);
		userid = userMapper.getUserid(username);
		return userid;
	}

	@Override
	public List<UserAnswer> getUserAnswer(Long userid) {
		// TODO Auto-generated method stub
		List<UserAnswer> list = new ArrayList<UserAnswer>();
		list = userAnswerMapper.getUserAnswer(userid);
		return list;
	}

	@Override
	public List<Test> getAllTest() {
		// TODO Auto-generated method stub
		List<Test> list = new ArrayList<Test>();
		list = testMapper.selectAll();
		return list;
	}
	
	
	
}
