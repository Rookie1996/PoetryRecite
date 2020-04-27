package com.xjr.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjr.exception.BusinessException;
import com.xjr.model.Admin;
import com.xjr.model.SessionAdmin;
import com.xjr.util.Constants;
import com.xjr.util.StringTools;

import checkcode.patchca.color.SingleColorFactory;
import checkcode.patchca.filter.predefined.CurvesRippleFilterFactory;
import checkcode.patchca.service.ConfigurableCaptchaService;
import checkcode.patchca.utils.encoder.EncoderHelper;

import com.xjr.controller.AdminController;
import com.xjr.service.IAdminService;

@Controller
@RequestMapping("/adminController")
public class AdminController {
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	

	@Autowired
	private IAdminService adminService;
	

	 /** 注册页面
		 * 
		 * @param model
		 * @return
		 */
		@RequestMapping("/showAdminRegister")
		public String showAdminRegister(Model model) {
			return "pages/register";
		}
	
	/**
	 * 登陆界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showAdminLogin")
	public String showAdminLogin(Model model) {
		return "pages/login";
	}
	
	/**
	 * 注册第一步，判断是否已经注册（根据邮箱）， 已注册  -> 请直接登录  未注册 -> 向数据库中插入数据
	 * 
	 * @param session
	 * @param userName
	 * @param email
	 * @param password
	 * @param checkCode
	 * @return
	 */
	@RequestMapping(value = "/register.action", produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> register(HttpSession session, String username, String email,
			String password) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 0:未注册;1:已注册
		int emailState = adminService.isEmailRegister(email);
		if (emailState == 0 || emailState == 1) {

			Admin adminChecked = adminService.findAdminByAdminName(username);
			if (adminChecked != null && !adminChecked.getEmail().equals(email)) { // 用户名已存在 且 用户名不与该email匹配就是被占用
				map.put("info", "用户名被占用，请修改用户名");
				return map;
			}


			Admin admin = new Admin();
			Long i = (long)1; 
			admin.setAdminid(i);
			admin.setAdminame(username);
			admin.setEmail(email);
			admin.setPassword(password);
			
			if (emailState == 0) { // 注册用户
				Long adminId = adminService.register(admin);
				map.put("info", "注册成功！");
				return map;
			} else if (emailState == 1) { // 已注册，请登录
				map.put("info", "邮箱已经注册，请登录");
				return map;
			}
		} 
		return map;
	}

	
	/**
	 * 登录
	 * 
	 * @param session
	 * @param response
	 * @param username
	 * @param password
	 * @param checkCode
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(value = "/login.action", produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> login(HttpSession session, HttpServletResponse response, String adminame,
			String password, String checkCode, String rememberMe) {
		final String REMEMBERME = "true";
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String sessionCheckCode = String.valueOf(session.getAttribute(Constants.check_code_key));
			if (StringTools.isEmpty(sessionCheckCode)) {
				map.put("info", "验证码已过期，请刷新页面重试");
				logger.info("验证码已过期，请刷新页面重试");
				return map;
			}
			if (!StringTools.isEmpty(sessionCheckCode) && !sessionCheckCode.equalsIgnoreCase(checkCode)) {
				map.put("info", "验证码错误");
				logger.info("验证码错误");
				return map;
			}

			//controller层，调用service层函数login(String adminame,String password,boolean md5)
			System.out.println("controller接收json:"+adminame+","+password);
			Admin admin = adminService.login(adminame, password, false);
			SessionAdmin sessionAdmin = new SessionAdmin();
			sessionAdmin.setAdminId(admin.getAdminid());
			sessionAdmin.setAdminName(admin.getAdminame());
			session.setAttribute(Constants.SESSION_USER_KEY, sessionAdmin);
			session.setAttribute(Constants.USER_ID, admin.getAdminid());
			session.setAttribute("adminame",adminame);
			
			// 记住登录状态
			if (REMEMBERME.equals(rememberMe)) {
				// 自动登录，保存用户名密码到Cookie
				String infor = URLEncoder.encode(adminame.toString(), "utf-8") + "|" + admin.getPassword();
				// 清除之前的Cookie信息
				Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				// 将用户信息保存到Cookie中
				Cookie cookieInfo = new Cookie(Constants.COOKIE_USER_INFO, infor);
				cookieInfo.setPath("/");
				// 设置最大生命周期为1年
				cookieInfo.setMaxAge(31536000);
				response.addCookie(cookieInfo);
			} else { // 清空cookie
				Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		} catch (BusinessException e) {
			if (map.get("info") == null) {
				map.put("info", e.getMessage());
				logger.info("登录失败: " + e.getMessage());
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (map.get("info") == null) {
				map.put("info", "error");
				logger.info("error:");
			}
			return map;
		}
		map.put("info", "登录成功");
		return map;
	}
	
	/**
	 * 生成图片验证码
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="checkCode.action")
	public void checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(20, 60, 170)));
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		String code = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		session.setAttribute(Constants.check_code_key, code);
	}


}
