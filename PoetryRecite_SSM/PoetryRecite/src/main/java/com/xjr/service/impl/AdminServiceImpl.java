package com.xjr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.exception.BusinessException;
import com.xjr.dao.AdminMapper;
import com.xjr.model.Admin;
import com.xjr.service.IAdminService;
import com.xjr.util.StringTools;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {
	
	@Resource
	private AdminMapper adminMapper;
	
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	@Autowired
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	

	/**
	 * 注册用户
	 */
	
	public Long register(Admin admin) {
		//向admin表中插入数据
		int adminRow = adminMapper.insert(admin);
		long adminId = (long) 0;
		if(adminRow!=0) {
			adminId = admin.getAdminid();
		}
		return adminId;
	}

	
	public Admin login(String adminame, String password, boolean hasMD5) throws Exception{
		
		System.out.println("service接收controller:"+adminame+","+password);
		if (StringTools.isEmpty(adminame) || StringTools.isEmpty(password)) {
			throw new BusinessException("输入参数不合法,username或password不能为空");
		}
		Admin admin = null;
		 // 用户名登录
		admin = this.findAdminByAdminName(adminame);
		
		if (null == admin) {
			throw new BusinessException("用户不存在，请前往注册");
		}
		if(hasMD5){
			if (!password.equals(admin.getPassword())) {
				throw new BusinessException("密码错误");  //MD5
			}
		}
		adminMapper.updateByPrimaryKeySelective(admin);
		return admin;
	}

	
	public void update(Admin admin) {
		adminMapper.updateByPrimaryKeySelective(admin);
	}

	//根据用户名查找用户是否存在
	@Override
	public Admin findAdminByAdminName(String adminame) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		//map 键值对 键名为 adminame
		map.put("adminame",adminame);
		if(adminMapper == null) {
			adminMapper = getAdminMapper();
		}
		List<Admin>list = adminMapper.findAdminByAdminName(map);
		if(list.size()>=1) {
			return list.get(0);
		}
		return null;
	}
	
	//根据邮箱查找用户
	public Admin findAdminByEmail(String email) {
		Map<String,String> map = new HashMap<String,String>(); 
		map.put("email", email);
		List<Admin>list = adminMapper.findAdminByEmail(map);
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int isEmailRegister(String email) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>(); 
		map.put("email", email);
		List<Admin> adminlist = adminMapper.findAdminByEmail(map);
		if(adminlist.size()==0){
			return 0;
		}else if(adminlist.size()!=0){
			return 1; 
		}
		return 0;
	}
	
	

	
}
