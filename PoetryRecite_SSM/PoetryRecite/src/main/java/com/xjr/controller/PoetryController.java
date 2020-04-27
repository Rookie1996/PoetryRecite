package com.xjr.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjr.model.Poetry;
import com.xjr.model.Test;
import com.xjr.service.IPoetryService;
import com.xjr.util.Msg;

@Controller
@RequestMapping("/poetryController")
public class PoetryController {
	
	@Autowired
	IPoetryService poetryService;
	
	/**
	  *  响应android端 发送的request请求
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showPoetry", produces = "application/json;charset=UTF-8")
	public List<Poetry> getAllPoetry() {
		//查询所有古诗词
		List<Poetry> poetry = poetryService.getAll();
		//输出古诗词
//		Iterator it = poetry.iterator();
//		while(it.hasNext()){
//		    System.out.println(it.next());
//		}
		return poetry;
	}
	
	/**
	 * 查询诗歌(分页)
	 * @return
	 */
	@RequestMapping("/showManagePoetry")
	public String getPoetry(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
		//在查询之前只需要调用，传入页码以及分页大小
		PageHelper.startPage(pn,5);
		//startpage紧跟的查询就是一个分页查询
		List<Poetry> poetry = poetryService.getAll();
		//用pageInfo包装查询后的结果,只需要将pageinfo交给页面
		//封装了详细的信息，包括有查询的数据,连续显示的页数
		PageInfo page = new PageInfo(poetry,5);
		model.addAttribute("pageInfo",page);
		
		return "pages/poetry";
	}
	
	/**
	 * 增加模块
	 * 诗歌保存
	 * 
	 * @return
	 */
	@RequestMapping(value="/showManagePoetry",method=RequestMethod.POST)
	@ResponseBody
	public Msg savePoetry(Poetry poetry) {
		poetryService.savePoetry(poetry);
		return Msg.success();
	}
	
	/**
	 * 1、删除单一诗歌1
	 * 2、批量删除1-2-3
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManagePoetry/{ids}",method=RequestMethod.DELETE)
	public Msg deletePoetryById(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<Integer>();
			
			String[] str_ids = ids.split("-");
			//组装id的集合
			for(String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			poetryService.deleteBatch(del_ids);
			
		}else {//单个删除
			Integer id = Integer.parseInt(ids);
			poetryService.deletePoetryById(id);
		}
		return Msg.success();
	}
	
	/**
	 * select 公用
	 * 更新 编辑使用
	 * 按id查询诗歌 返回poetry类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showManagePoetry/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTest(@PathVariable("id")Integer id) {
		
		Poetry poetry = poetryService.getPoetry(id);
		return Msg.success().add("showManagePoetry", poetry);
	}
	
	/**
	  * 诗歌更新方法
	 * @param poetry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showManagePoetry/{poetryid}",method=RequestMethod.PUT)
	public Msg saveUpdatePoetry(Poetry poetry) {
		
		//System.out.println("将要更新的诗歌数据："+poetry);
		poetryService.updatePoetry(poetry);
		return Msg.success();
	}

}
