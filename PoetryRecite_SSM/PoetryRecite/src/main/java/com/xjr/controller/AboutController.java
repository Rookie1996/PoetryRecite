package com.xjr.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjr.model.SessionAdmin;
import com.xjr.service.ITestService;
import com.xjr.util.Constants;
import com.xjr.util.Msg;
import com.xjr.util.Word;

@Controller
@RequestMapping("/aboutController")
public class AboutController {
	
	@Autowired
	ITestService testService;

	@RequestMapping("/showAbout")
	public String showBorrows(Model model,HttpSession session) {
		SessionAdmin sessionAdmin= (SessionAdmin) session.getAttribute(Constants.SESSION_USER_KEY);
		model.addAttribute("sessionAdmin", sessionAdmin);
		return "pages/about";
	}
	
	/**
	 * 显示诗歌热词--->搜索poetry没什么典型性，test题目中出题最多的词。
	 */
	@RequestMapping(value = "/showHotWords", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Msg showHotWords() {
		
		//最后返回的是一个list 封装word-->name,value
		//这里需要实现算法 将数据库中题干词 《题目》 的出现频率及名称 计算出来 （简化了）
		//1、可以先将所有的testcontent 拼接成字符串。
		//2、将带《》内容筛选出来。
		//3、将每个标题存放在数组中，遍历数组。
		//每一次遍历时，遇到相同的标题count++，并且将该位置内容置为空；本次内侧循环结束，new Word("标题内容",count值),加入到List<Word>中。
		
		List<Word> hotwords = new ArrayList<Word>();
		
		List<String> testcontent = new ArrayList<String>();
		testcontent = testService.getTestContent();
		String test = "";
//		System.out.println("数据库获取所有试题内容：");
//		Iterator it = testcontent.iterator();
//		while(it.hasNext()){
//		    System.out.println(it.next());
//		}
		for(int i=0;i<testcontent.size();i++) {
			test+=testcontent.get(i);
		}
		//截取《》中字符
		int pre = 0;
		int next = 0;
		List<String>title = new ArrayList<String>();
		for(int j=0;j<test.length();j++) {
			if(test.charAt(j)=='《') {
				pre = j+1;
				for(int m=j;m<test.length();m++) {
					if(test.charAt(m)=='》') {
						next = m;
						break;//遇到第一个》结束内侧循环
					}
				}
				//将标题添加进title集合
				title.add(test.substring(pre,next));
			}
		}
//		System.out.println("截取的所有标题：");
//		Iterator it = title.iterator();
//		while(it.hasNext()){
//		    System.out.println(it.next());
//		}
		
		//计算重复值算法有点问题  原因：每次循环前count值没有清空
		//遍历截取的集合
		int count = 60;//初始就有一次value count=1但是value值太小图就会看不清，效果使然。
		int self = 0;
		List<Integer> index = new ArrayList<Integer>();
		for(int n=0;n<title.size();n++) {
			if(title.get(n)!="") {
				
				//每次内循环得到的index索引需要清除
				//index.clear();
				
				for(int k=0;k<title.size();k++) {
					if(n!=k&&title.get(k)!="") {
						if(title.get(n).equals(title.get(k))){
							count++;
							index.add(n);
							index.add(k);
						}
					}	
				}
				//单独的title怎么办?索引为n的不为空值。
				
				Word w = new Word(title.get(n),count); 
				hotwords.add(w);
				//清空title已经统计的集合
				for(int l=0;l<index.size();l++) {
					title.set(index.get(l), "");
				}
				//每次内循环得到的index索引需要清除
				//index = new ArrayList<Integer>();
				index.clear();	
				count = 60;
			}//if 不为空
				
		}
		
		return Msg.success().add("HotWords", hotwords);
	}
	
	
}
