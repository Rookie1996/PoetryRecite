package com.xjr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjr.model.GradeView;
import com.xjr.model.UserAnswer;
import com.xjr.service.IGradeService;
import com.xjr.util.Msg;

@Controller
@RequestMapping("/gradeController")
public class GradeController {

	@Autowired
	IGradeService gradeService;
	
	/**
	 * 处理android端发送的post请求
	 * 返回给客户端答题记录
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserAnswer", produces = "application/json;charset=UTF-8")
	public List<UserAnswer> getUserAnswer() {
	
		//查询所有答题记录
		List<UserAnswer> userAnswerList = gradeService.getAll();
	
		return userAnswerList;
	}
	
	
	/**
	 * 处理android端发送的post请求
	 * 保存答题记录
	 */
	
	@ResponseBody
	@RequestMapping(value = "/saveAnswer", produces = "application/json;charset=UTF-8")
	public Msg saveAnswer(@RequestBody UserAnswer userAnswer) {
	
			Integer testid = userAnswer.getTestid();
			Long userid = userAnswer.getUserid();
			System.out.println("testid = "+testid+",userid = "+userid);
		
			UserAnswer answer = new UserAnswer();
			answer = gradeService.selectAnswer(userid,testid);
			System.out.println("mysql查询结果="+answer);
			
			if(answer==null) {
				//插入记录
				gradeService.save(userAnswer);
			}else if(answer!=null) {
				//更新记录
				gradeService.update(userAnswer);
			}
			
			return Msg.success();
	}
	
	
	/**
	 * 校验试题是否删除
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkTest")
	public Msg checkTest(@RequestParam("testId")String testId) {
		
		boolean b = gradeService.checkTest(testId);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 校验试题是否可以删除
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkTests")
	public Msg checkTests(@RequestParam("testId")String testId) {
		
		String wtestid = "";
		boolean b = true;
		String[] str_ids = testId.split("-");
		//组装id的集合
		for(String string : str_ids) {
		
			b = gradeService.checkTest(string);
			if(!b) {
				wtestid += string+",";
			}
		}
		
		if(wtestid=="") {
			return Msg.success();
		}else
			return Msg.fail().add("wtestid", wtestid);
	}
	
	
	/**
	 * 查询用户答题记录(分页)
	 * @return
	 */
	@RequestMapping("/showUserAnswer")
	public String getAnswer(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
		//在查询之前只需要调用，传入页码以及分页大小
		PageHelper.startPage(pn,5);
		//startpage紧跟的查询就是一个分页查询
		List<UserAnswer> userAnswer = gradeService.getAll();
		//用pageInfo包装查询后的结果,只需要将pageinfo交给页面
		//封装了详细的信息，包括有查询的数据,连续显示的页数
		PageInfo page = new PageInfo(userAnswer,5);
		model.addAttribute("pageInfo",page);
		
		return "pages/grade";
	}
	
	/**
	 * 查询用户答题记录--视图  (分页)
	 * @return
	 */
	@RequestMapping("/showUserAnswerView")
	public String getAnswerView(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
		//在查询之前只需要调用，传入页码以及分页大小
		PageHelper.startPage(pn,5);
		//startpage紧跟的查询就是一个分页查询
		List<GradeView> gradeView = gradeService.getViewAll();
		//用pageInfo包装查询后的结果,只需要将pageinfo交给页面
		//封装了详细的信息，包括有查询的数据,连续显示的页数
		PageInfo page = new PageInfo(gradeView,5);
		model.addAttribute("pageInfo",page);
		
		return "pages/grade";
	}
	
	/**
	 * select 公用
	 * 更新 编辑使用
	 * 按id查询答题记录 返回GradeView类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showUserAnswerView/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getGradeView(@PathVariable("id")Integer id) {
		
		GradeView gradeView = gradeService.getGradeView(id);
		return Msg.success().add("showUserAnswerView", gradeView);
	}

	/**
	  * 用户答题记录更新方法
	 * @param GradeView
	 * @return
	 */
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="/showUserAnswerView/{vid}",method=RequestMethod.PUT)
	public Msg saveUpdateGradeView(GradeView gradeView) {
//		
//		System.out.println(gradeView);
//		System.out.println(gradeView);
//		System.out.println(gradeView);
//		
		gradeService.updateGradeView(gradeView);
		return Msg.success();
	}
	
	/**
	 * 模糊查询 返回json
	 * @param pn
	 * @param vContent和vUsername和vTypeid组合的字符串
	 * @return
	 */
	@RequestMapping("/showLikeGradeView")
	@ResponseBody
	public Msg getLikeGradeView(@RequestParam(value="pn",defaultValue = "1")Integer pn,String rs) {
		//报错的原因是这个方法体中没有testcontent参数
		
		List<GradeView> gradeViewList = new ArrayList<GradeView>();
		
		String vcontent = "";
		String vusername = "";
		String vtestype = "";
		Integer vtypeid = 0;
		String [] str_rs = rs.split("-");
		vcontent = str_rs[0];
		vusername = str_rs[1];
		vtypeid = Integer.parseInt(str_rs[2]);
		
		if(vtypeid==1) {
			vtestype = "选择";
		}else if(vtypeid==2) {
			vtestype = "判断";
		}else if(vtypeid==3){
			vtestype = "简答";
		}
		
		//取值没问题
		System.out.println("vcontent:"+vcontent+",vusername:"+vusername+",vtestype:"+vtestype);
		
		if(vcontent.equals("")&&vusername.equals("")&&vtypeid!=0) {//按类型
			
			//调用查找类型的select
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByTestype(vtestype);
			
		}else if(vcontent==""&&vusername==""&&vtypeid==0) {//所有记录
			//select All
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.getViewAll();
			
		}else if(vcontent==""&&vusername!=""&&vtypeid!=0) {//按vtestype和vusername
			//select vtestype和vusername
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByTestypeAndUsernamelike(vtestype,vusername);
			
		}else if(vusername==""&&vcontent!=""&&vtypeid!=0) {//按vtestype和vcontent
			//select vtestype和vcontent
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByTestypeAndContentlike(vtestype,vcontent);
			
		}else if(vusername!=""&&vcontent!=""&&vtypeid==0) {//按 vusername和 vcontent
			
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByUsernameAndContentlike(vusername,vcontent);
			
		}else if(vusername!=""&&vcontent!=""&&vtypeid!=0) {//按3个关键字
			
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByThreeKeys(vtestype,vusername,vcontent);
			
		}else if(vcontent==""&&vusername!=""&&vtypeid==0) {//按vusername
			
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByUsernamelike(vusername);
			
		}else if(vcontent!=""&&vusername==""&&vtypeid==0) {//按vcontent
			
			PageHelper.startPage(pn,5);
			gradeViewList = gradeService.selectByContentlike(vcontent);
		}
		
		 PageInfo page = new PageInfo(gradeViewList,5); 
		 System.out.println("当前页码："+page.getPageNum()); 
		 System.out.println("总页码数："+page.getPages()); 
		 System.out.println("总记录数："+page.getTotal());
		 for(int i=0;i<gradeViewList.size();i++) {
			 System.out.println("返回结果:"+gradeViewList.get(i));
		 }
		 
		return Msg.success().add("pageInfo", page);
	}
	
	
}
