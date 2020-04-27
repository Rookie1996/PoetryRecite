package com.xjr.service;

import com.xjr.model.Admin;

public interface IAdminService {

	/**
	 * 管理员注册
	 * @param admin
	 */
	public Long register(Admin admin);
	
	/** 用户登录
	 * @param adminame 帐号可以是用户名
	 * @param password
	 * @return User
	 * @throws Exception 
	 * @throws BusinessException
	 */
	public Admin login(String adminame, String password, boolean hasMD5) throws Exception;

	/**
	 * 根据username查找admin
	 * @param email
	 * @return
	 */
	Admin findAdminByAdminName(String adminName);

	/**
	 * 根据邮箱判断用户是否已经注册
	 * @param email
	 * @return 	0:未注册
	 * 			1:已注册		
	 */
	public int isEmailRegister(String email);
}
