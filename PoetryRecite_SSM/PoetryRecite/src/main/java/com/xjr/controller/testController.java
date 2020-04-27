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
import com.xjr.model.Test;
import com.xjr.model.User;
import com.xjr.service.ITestService;
import com.xjr.util.Msg;


@Controller
@RequestMapping("/testController")
public class testController {
	
	@Autowired
	ITestService testService;
	
	/**
	 * 响应安卓端post请求 返回试题
	 * @return
	 */
	@RequestMapping(value="/getTest",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Test> getTest(){
		
		//查询所有试题
		List<Test> test = testService.getAll();
//		Iterator it = test.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		return test;
	}
	
	/**
	 * 查询试题(分页)
	 * @return
	 */
	@RequestMapping("/showManageTest")
	public String getTest(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
		//在查询之前只需要调用，传入页码以及分页大小
		PageHelper.startPage(pn,5);
		//startpage紧跟的查询就是一个分页查询
		List<Test> test = testService.getAll();
		//用pageInfo包装查询后的结果,只需要将pageinfo交给页面
		//封装了详细的信息，包括有查询的数据,连续显示的页数
		PageInfo page = new PageInfo(test,5);
		model.addAttribute("pageInfo",page);
		
		return "pages/test";
	}
	
	/**
	 * 增加模块
	 * 试题保存
	 * 
	 * @return
	 */
	@RequestMapping(value="/showManageTest",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveTest(Test test) {
		testService.saveTest(test);
		return Msg.success();
	}
	
	/**
	 * 1、删除单一试题1000
	 * 2、批量删除1000-1001-1002
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManageTest/{ids}",method=RequestMethod.DELETE)
	public Msg deleteTestById(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<Integer>();
			
			String[] str_ids = ids.split("-");
			//组装id的集合
			for(String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			testService.deleteBatch(del_ids);
			
		}else {//单个删除
			Integer id = Integer.parseInt(ids);
			testService.deleteTestById(id);
		}
		return Msg.success();
	}
	
	
	/**
	 * select公用
	 * 更新 编辑使用
	 * 按id查询试题 返回test类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showManageTest/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTest(@PathVariable("id")Integer id) {
		
		Test test = testService.getTest(id);
		return Msg.success().add("showManageTest", test);
	}
	
	/**
	 * 试题更新方法
	 * @param test
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManageTest/{testid}",method=RequestMethod.PUT)
	public Msg saveUpdateTest(Test test) {
		System.out.println("将要更新的员工数据："+test);
		testService.updateTest(test);
		return Msg.success();
	}
	
	
	/**
	 * 模糊查询 返回json
	 * @param pn
	 * @param testContent和typeid组合的字符串
	 * @return
	 */
	@RequestMapping("/showLikeTest")
	@ResponseBody
	public Msg getLikeTest(@RequestParam(value="pn",defaultValue = "1")Integer pn,String rs) {
		//报错的原因是这个方法体中没有testcontent参数
		
		List<Test> test = new ArrayList<Test>();
		
		if(rs.contains("-")) {//testContent和typeid组合的模糊查询
			String testContent = "";
			Integer typeId = 0;
			String[] str_rs = rs.split("-");
			//分开内容和试题类型
			testContent = str_rs[0];
			//System.out.println(testContent);
			typeId = Integer.parseInt(str_rs[1]);
			
			PageHelper.startPage(pn,5);
			test = testService.selectByContentAndType(testContent,typeId);
			
		}else {//没有内容 只是查询typeid
			Integer TypeId = Integer.parseInt(rs);
			
			PageHelper.startPage(pn,5);
			test = testService.selectTestByTypeId(TypeId);
		}

		PageInfo page = new PageInfo(test,5);
		System.out.println("当前页码："+page.getPageNum());
		System.out.println("总页码数："+page.getPages());
		System.out.println("总记录数："+page.getTotal());

		return Msg.success().add("pageInfo", page);
	}
	

}
