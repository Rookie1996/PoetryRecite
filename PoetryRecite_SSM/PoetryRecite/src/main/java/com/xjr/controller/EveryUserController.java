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
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjr.model.SumGradeView;
import com.xjr.model.Test;
import com.xjr.model.UserAnswer;
import com.xjr.service.IUserService;
import com.xjr.util.CountValues;
import com.xjr.util.GradesRange;
import com.xjr.util.Msg;
import com.xjr.util.Testid_Grade;

/**
 * 处理个人统计信息
 * @author Raffello
 *
 */
@Controller
@RequestMapping("/everyUserController")
public class EveryUserController {
	
	@Autowired
	IUserService userService;
	
	
	/**
	 * 跳转到echart页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/showEveryUser")
	public String showEveryUser(Model model) {
		return "pages/echart";
	}
	
	/**
	 * 返回所有用户总成绩
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showEverySumGrade", produces = "application/json;charset=UTF-8")
	public Msg getSumGrade(){
		
		List<SumGradeView> list = new ArrayList<SumGradeView>();
		list = userService.getSumGrade();
		
//		System.out.println("数据库获取所有总成绩：");
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//		    System.out.println(it.next());
//		}
		
		//统计分数段 返回list 中是对象GradesRange 包括value:10 name:"60-69"
		List<GradesRange> range = new ArrayList<GradesRange>();
		CountValues c = new CountValues();
		int value1 = c.countValues(0, 59, list);
		int value2 = c.countValues(60, 69, list);
		int value3 = c.countValues(70, 79, list);
		int value4 = c.countValues(80, 89, list);
		int value5 = c.countValues(90, 100, list);
		GradesRange g1 = new GradesRange(value1,"<60");
		GradesRange g2 = new GradesRange(value2,"60-69");
		GradesRange g3 = new GradesRange(value3,"70-79");
		GradesRange g4 = new GradesRange(value4,"80-89");
		GradesRange g5 = new GradesRange(value5,"90-100");
		range.add(g1);
		range.add(g2);
		range.add(g3);
		range.add(g4);
		range.add(g5);
		
		return Msg.success().add("GradesRange", range);
		
	}
	
	/**
	 * 按username精确查询 ，用户所有答题记录(不能做模糊查询，防止返回多个useranswer记录)
	 * 处理答题记录，将结果以对象形式返回。testid和grade的组合 Testid_Grade对象 的集合
	 */
	@ResponseBody
	@RequestMapping(value = "/showUserAnswer/{username}",method=RequestMethod.GET)
	public Msg getUserAnswerByUsername(@PathVariable("username")String username){
		
		Long userid = new Long(0);
		userid = userService.getUserid(username);
		//System.out.println("查询出userid为："+userid);
		
		//查询当前用户所有答题记录
		List<UserAnswer> userAnswer = new ArrayList<UserAnswer>();
		userAnswer = userService.getUserAnswer(userid);
		
		//查询出所有试题test
		List<Test> test = new ArrayList<Test>();
		test = userService.getAllTest();
		
		//返回所有testid对应的grade  用户没有回答默认赋值为0
		List<Testid_Grade> list = new ArrayList<Testid_Grade>();
		//ArrayList<Integer> intlist = new ArrayList<Integer>(Collections.nCopies(10, 5));初始化长度为10，值为5
		
		//按test集合初始化list 长度和testid都是匹配的，每个grade都先赋值为0
		for(int m=0;m<test.size();m++) {
			Testid_Grade tg = new Testid_Grade(test.get(m).getTestid(),0);
			list.add(tg);
		}
		
		int count = 0;
		for(int i=0;i<test.size();i++) {
//			System.out.println("test-testid:"+test.get(i).getTestid());
			for(int j=0;j<userAnswer.size();j++) {
//				System.out.println("useranswer-testid:"+userAnswer.get(j).getTestid());
				if((test.get(i).getTestid()).equals(userAnswer.get(j).getTestid())) {
					//匹配到相同testid时，修改list对应位置的grade
					list.get(i).setGrade(userAnswer.get(j).getGrade());
					count++;
				}
			}
		}
		//封装进GradesRange对象中
		List<GradesRange> List = new ArrayList<GradesRange>();
		for(int n=0;n<list.size();n++) {
			GradesRange gr = new GradesRange(list.get(n).getGrade(),list.get(n).getTestid()+"");
			List.add(gr);
		}
//		System.out.println("list修改次数"+count);
		
//		System.out.println("所有答题记录：");
//		Iterator it1 = userAnswer.iterator();
//		while(it1.hasNext()){
//		    System.out.println(it1.next());
//		}
//		
//		System.out.println("所有试题：");
//		Iterator it2 = test.iterator();
//		while(it2.hasNext()){
//		    System.out.println(it2.next());
//		}
		
//		System.out.println("所有试题对应成绩：");
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//		    System.out.println(it.next());
//		}
		
		return Msg.success().add("List", List);
	}

}
