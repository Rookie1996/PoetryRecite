package com.xjr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjr.model.Testype;
import com.xjr.service.ITestypeService;
import com.xjr.util.Msg;

@Controller
@RequestMapping("/TypeidController")
public class TypeidController {

	@Autowired
	private ITestypeService testypeService;
	
	/**
	 * 返回所有试题类型
	 */
	@RequestMapping("/testype")
	@ResponseBody
	public Msg getTypeName() {
		//查出所有试题类型表中的信息
		List<Testype> list = testypeService.getTestype();
		return Msg.success().add("testype", list);
	}
}
