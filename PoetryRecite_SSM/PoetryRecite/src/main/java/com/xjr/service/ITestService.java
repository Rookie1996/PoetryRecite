package com.xjr.service;

import java.util.List;

import com.xjr.model.Test;

public interface ITestService {

	/**
	 * 查询所有试题
	 * @return
	 */
	public List<Test> getAll();

	/**
	 * 按id查询试题
	 * @param id
	 * @return
	 */
	public Test getTest(Integer id);

	/**
	 * 插入试题
	 * @param test
	 */
	public void saveTest(Test test);

	/**
	 * 更新试题
	 * @param test
	 */
	public void updateTest(Test test);
	
	/**
	 * 批量删除试题
	 * @param del_ids
	 */
	public void deleteBatch(List<Integer> del_ids);
	
	/**
	 * 根据试题号删除试题
	 * @param id
	 */
	public void deleteTestById(Integer id);

	/**
	 * 模糊查询
	 * @param testContent
	 * @param typeId
	 * @return 
	 */
	public List<Test> selectByContentAndType(String testContent,Integer typeId);

	/**
	 * 按试题类型查询
	 * @param typeId
	 * @return
	 */
	public List<Test> selectTestByTypeId(Integer typeId);

	/**
	 * 查询所有试题的内容
	 * @return
	 */
	public List<String> getTestContent();


}
