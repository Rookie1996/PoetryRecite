package com.xjr.dao;

import com.xjr.model.GradeView;
import com.xjr.model.GradeViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeViewMapper {
	long countByExample(GradeViewExample example);

	int deleteByExample(GradeViewExample example);

	int insert(GradeView record);

	int insertSelective(GradeView record);

	List<GradeView> selectByExample(GradeViewExample example);

	int updateByExampleSelective(@Param("record") GradeView record, @Param("example") GradeViewExample example);

	int updateByExample(@Param("record") GradeView record, @Param("example") GradeViewExample example);

	/**
	 * 查询视图所有记录
	 * 
	 * @return
	 */
	List<GradeView> selectViewAll();

	/**
	 * 无法直接更新视图 按vid更新视图
	 * 
	 * @param gradeView
	 */
	// void updateByVid(GradeView gradeView);

	/**
	 * 按vid查询
	 * 
	 * @param id
	 * @return
	 */
	GradeView selectViewById(Integer id);

	/**
	 * 
	 * @param vtestype
	 * @return
	 */
	List<GradeView> selectByTestype(String vtestype);
	
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
	 * @param vtestype
	 * @param vusername
	 * @return
	 */
	List<GradeView> selectByTestypeAndUsernamelike(String vtestype, String vusername);
	
	/**
	 * 
	 * @param vtestype
	 * @param vcontent
	 * @return
	 */
	List<GradeView> selectByTestypeAndContentlike(String vtestype, String vcontent);
	
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

}