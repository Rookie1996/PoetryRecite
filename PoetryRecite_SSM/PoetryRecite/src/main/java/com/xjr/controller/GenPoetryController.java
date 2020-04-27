package com.xjr.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjr.util.Jpython;


@Controller
@RequestMapping("/genPoetryController")
public class GenPoetryController {

	/**
	  *  响应android端 发送的request生成古诗请求
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/generatePoetry", produces = "application/json;charset=UTF-8")
	public String getPoetry() {

		String poetry = "";
		Jpython jpython = new Jpython();
		try {
			poetry = jpython.getPoetry();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return poetry;
	}
}
