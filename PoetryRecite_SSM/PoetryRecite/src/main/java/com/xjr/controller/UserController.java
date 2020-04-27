package com.xjr.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjr.model.User;
import com.xjr.service.IUserService;
import com.xjr.util.Msg;

/**
 * 处理用户crud请求
 * @author Raffello
 *
 */
@Controller
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	/**
	 * 处理android端发送的post请求
	 */
	
	/**
	 * 登录
	 * 
	 * @param user
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login.action", produces = "application/json;charset=UTF-8")
	public User login(@RequestBody User user) {
	
			String username = user.getUsername();
			String password = user.getPassword();
			//controller层，调用service层函数login(String username,String password)
			System.out.println("controller接收json:"+username+","+password);
			User getUser = userService.login(username);
			System.out.println("调用dao层方法返回的user对象为："+getUser);
		
			return getUser;
	}
	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signup.action", produces = "application/json;charset=UTF-8")
	public String signup(@RequestBody User user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		System.out.println("controller接收json："+username+","+password+","+email);
		String result = "fail";
		//检查用户名是否可用
		boolean flag = userService.checkUser(username);
		if(username!=""&&password!=""&&email!="") {
			if(flag) {
				userService.signup(user);
				result = "success";
			}else {
				result = "repeat";
			}
		}
		
		return result;
	}
	
	
	/**
	 * 牛批兄弟！！！
	 * 
	 * 1、删除单一用户1
	 * 2、批量删除1-2-3
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManageUser/{ids}",method=RequestMethod.DELETE)
	public Msg deleteUserById(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Long> del_ids = new ArrayList<Long>();
			
			String[] str_ids = ids.split("-");
			//组装id的集合
			for(String string : str_ids) {
				del_ids.add(Long.parseLong(string));
			}
			userService.deleteBatch(del_ids);
			
		}else {//单个删除
			Long id = Long.parseLong(ids);
			userService.deleteUserById(id);
		}
		return Msg.success();
	}
	
	/**
	 * 员工更新方法
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManageUser/{userid}",method=RequestMethod.PUT)
	public Msg saveUpdateUser(User user) {
		System.out.println("将要更新的员工数据："+user);
		userService.updateUser(user);
		return Msg.success();
	}
	
	/**
	  * 根据id查询用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showManageUser/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getUser(@PathVariable("id")Long id) {
		
		User user = userService.getUser(id);
		return Msg.success().add("showManageUser", user);
	}
	
	
	/**
	 * 校验用户名是否可用
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("userName")String userName) {
		boolean b = userService.checkUser(userName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	
	/**
	 * 用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value="/showManageUser",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveUser(User user) {
		userService.saveUser(user);
		return Msg.success();
	}
	
	/**
	 * 用户删除
	 * 
	 * @return
	 */
	@RequestMapping(value="/showManageUser",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteUser(User user) {
		userService.deleteUser(user);
		return Msg.success();
	}
	
	
	
	/**
	 * 查询用户(分页)
	 * @return
	 */
	@RequestMapping("/showManageUser")
	public String getUser(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
		 //@RequestMapping("/emps")	
		//引入PageHelper分页插件
		//在查询之前只需要调用，传入页码以及分页大小
		PageHelper.startPage(pn,5);
		//startpage紧跟的查询就是一个分页查询
		List<User> user = userService.getAll();
		//用pageInfo包装查询后的结果,只需要将pageinfo交给页面
		//封装了详细的信息，包括有查询的数据,连续显示的页数
		PageInfo page = new PageInfo(user,5);
		model.addAttribute("pageInfo",page);
		
		return "pages/user";
	}
	
	/**
	 * 模糊查询 返回json
	 * @param pn
	 * @param userName
	 * @return
	 */
	@RequestMapping("/showLikeUser")
	@ResponseBody
	public Msg getLikeUser(@RequestParam(value="pn",defaultValue = "1")Integer pn,String userName) {
		PageHelper.startPage(pn,5);
		List<User> user = userService.getLikeUser(userName);
		Iterator it = user.iterator();
		while(it.hasNext()){
		    System.out.println(it.next());
		}
		PageInfo page = new PageInfo(user,5);
		System.out.println("当前页码："+page.getPageNum());
		System.out.println("总页码数："+page.getPages());
		System.out.println("总记录数："+page.getTotal());

		return Msg.success().add("pageInfo", page);
	} 

}
