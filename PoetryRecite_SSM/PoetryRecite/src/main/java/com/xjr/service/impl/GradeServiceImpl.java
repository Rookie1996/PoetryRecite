package com.xjr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.dao.GradeViewMapper;
import com.xjr.dao.TestMapper;
import com.xjr.dao.UserAnswerMapper;
import com.xjr.model.GradeView;
import com.xjr.model.Test;
import com.xjr.model.UserAnswer;
import com.xjr.service.IGradeService;

@Service("gradeService")
public class GradeServiceImpl implements IGradeService{

	@Autowired
	private UserAnswerMapper userAnswerMapper;
	@Autowired
	private GradeViewMapper gradeViewMapper;
	@Autowired
	private TestMapper testMapper;
	

	@Override
	public List<UserAnswer> getAll() {
		// TODO Auto-generated method stub
		return userAnswerMapper.selectAll();
	}

	@Override
	public List<GradeView> getViewAll() {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectViewAll();
	}

	@Override
	public void updateGradeView(GradeView gradeView) {
		// TODO Auto-generated method stub
		
		//同时更新两张表
		//更新test表
		int testid = gradeView.getVtestid();
		//System.out.println("需要更新的试题编号:"+testid);
		String content = gradeView.getVcontent();
		String answer = gradeView.getVanswer();
		Test test = new Test();
		test.setTestid(testid);
		test.setTestcontent(content);
		test.setAnswer(answer);
		testMapper.updateByPrimaryKeySelective(test);
		
		//更新useranswer表
		int id = gradeView.getVid();
		String useranswer = gradeView.getVuseranswer();
		int grade = gradeView.getVgrade();
		UserAnswer u = new UserAnswer();
		u.setId(id);
		u.setUseranswer(useranswer);
		u.setGrade(grade);
		userAnswerMapper.updateByPrimaryKeySelective(u);
		
	}

	@Override
	public GradeView getGradeView(Integer id) {
		// TODO Auto-generated method stub
		GradeView gradeView = gradeViewMapper.selectViewById(id);
		return gradeView;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<GradeView> selectByTestype(String vtestype) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByTestype(vtestype);
	}

	@Override
	public List<GradeView> selectByTestypeAndUsernamelike(String vtestype, String vusername) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByTestypeAndUsernamelike(vtestype,vusername);
	}

	@Override
	public List<GradeView> selectByTestypeAndContentlike(String vtestype, String vcontent) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByTestypeAndContentlike(vtestype,vcontent);
	}

	@Override
	public List<GradeView> selectByUsernamelike(String vusername) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByUsernamelike(vusername);
	}

	@Override
	public List<GradeView> selectByContentlike(String vcontent) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByContentlike(vcontent);
	}

	@Override
	public List<GradeView> selectByUsernameAndContentlike(String vusername, String vcontent) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByUsernameAndContentlike(vusername,vcontent);
	}

	@Override
	public List<GradeView> selectByThreeKeys(String vtestype, String vusername, String vcontent) {
		// TODO Auto-generated method stub
		return gradeViewMapper.selectByThreeKeys(vtestype,vusername,vcontent);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//查询答题记录是否存在
	@Override
	public UserAnswer selectAnswer(Long userid, Integer testid) {
		// TODO Auto-generated method stub
		return userAnswerMapper.selectByUseridAndTestid(userid,testid);
	}

	@Override
	public void save(UserAnswer userAnswer) {
		// TODO Auto-generated method stub
		userAnswerMapper.insert(userAnswer);
	}

	@Override
	public void update(UserAnswer userAnswer) {
		// TODO Auto-generated method stub
		userAnswerMapper.update(userAnswer);
	}

	@Override
	public boolean checkTest(String testId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Integer testid = Integer.parseInt(testId);
		Integer i = userAnswerMapper.selectByTestid(testid);
		if(i==0) {
			flag = true;//可以删除
		}else {
			flag = false;
		}
		return flag;
	}

	
}
