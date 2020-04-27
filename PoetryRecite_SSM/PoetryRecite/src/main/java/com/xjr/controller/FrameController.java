package com.xjr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/frameController")
public class FrameController {
	private Logger logger=LoggerFactory.getLogger(FrameController.class);
	
	/**
	 * frame页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/showframe")
	public String frame(Model model) {
		return "pages/frame";
	}
	
	@RequestMapping("/head")
	public String head(Model model) {
		return "pages/head";
	}
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		return "pages/menu";
	}
	
	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout.action", produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> logout(HttpSession session, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("info", "注销成功");
		return map;
	}
}
