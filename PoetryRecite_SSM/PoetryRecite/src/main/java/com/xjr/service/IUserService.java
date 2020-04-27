package com.xjr.service;

import java.util.List;

import com.xjr.model.SumGradeView;
import com.xjr.model.Test;
import com.xjr.model.User;
import com.xjr.model.UserAnswer;

public interface IUserService {
	
	/**
	 * 获得所有用户的信息
	 * @return list
	 */
	public List<User> getAll();
	
	/**
	 * 用户保存方法
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * 用户删除方法
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * 校验用户存在
	 * @param userName
	 * @return true 当前用户名可用
	 */
	public boolean checkUser(String userName);
	
	/**
	  * 按照用户id查询用户
	 * @param id
	 * @return
	 */
	public User getUser(Long id);

	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除单一用户
	 * @param id
	 */
	public void deleteUserById(Long id);

	/**
	 * 批量删除
	 * @param del_ids
	 */
	public void deleteBatch(List<Long> del_ids);

	/**
	 * 用户名模糊查询
	 * @param userName
	 * @return
	 */
	public List<User> getLikeUser(String userName);

	/**
	 * 处理前端登录请求
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username);

	/**
	 * 注册插入新用户
	 * @param user
	 */
	public void signup(User user);

//***************************************************************************************************
	/**
	 * 获取所有用户总成绩
	 * @return
	 */
	public List<SumGradeView> getSumGrade();
	
	/**
	 * 按username精确查询出唯一的userid
	 */
	public Long getUserid(String username);

	/**
	 * 按userid查询出useranswer记录
	 * @param userid
	 * @return
	 */
	public List<UserAnswer> getUserAnswer(Long userid);

	public List<Test> getAllTest();

}
