package com.xjr.service;

import java.util.List;

import com.xjr.model.GradeView;
import com.xjr.model.UserAnswer;

public interface IGradeService {

	/**
	 * 查询用户所有答题记录
	 * @return
	 */
	List<UserAnswer> getAll();
	
	/**
	 * 查询视图所有记录
	 * @return
	 */
	List<GradeView> getViewAll();

	/**
	  * 更新视图
	 * @param gradeView
	 */
	void updateGradeView(GradeView gradeView);

	/**
	 * 按id号查询视图
	 * @param id
	 * @return
	 */
	GradeView getGradeView(Integer id);
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 按试题类型查询视图
	 * @param vtestype
	 * @return
	 */
	List<GradeView> selectByTestype(String vtestype);

	/**
	 * 按类型和用户名查找
	 * @param vtestype
	 * @param vusername
	 * @return
	 */
	List<GradeView> selectByTestypeAndUsernamelike(String vtestype, String vusername);

	/**
	 * 按类型和内容查找
	 * @param vtestype
	 * @param vcontent
	 * @return
	 */
	List<GradeView> selectByTestypeAndContentlike(String vtestype, String vcontent);

	/**
	 * 
	 * @param vusername
	 * @return
	 */
	List<GradeView> selectByUsernamelike(String vusername);

	/**
	 * 
	 * @param vcontent
	 * @return
	 */
	List<GradeView> selectByContentlike(String vcontent);

	/**
	 * 
	 * @param vusername
	 * @param vcontent
	 * @return
	 */
	List<GradeView> selectByUsernameAndContentlike(String vusername, String vcontent);

	/**
	 * 
	 * @param vtestype
	 * @param vusername
	 * @param vcontent
	 * @return
	 */
	List<GradeView> selectByThreeKeys(String vtestype, String vusername, String vcontent);

	/**
	 * 插入记录时，先查询答题记录是否存在
	 * @param userid
	 * @param testid
	 * @return
	 */
	UserAnswer selectAnswer(Long userid, Integer testid);

	/**
	 * 插入记录
	 * @param userAnswer
	 * @return
	 */
	void save(UserAnswer userAnswer);

	/**
	 * 更新记录
	 * @param userAnswer
	 */
	void update(UserAnswer userAnswer);

	/**
	 * 查询试题是否可删除
	 * true 可以删除
	 * false 不可以删除
	 * @param testId
	 * @return
	 */
	boolean checkTest(String testId);

}
