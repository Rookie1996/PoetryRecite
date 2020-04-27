<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题评分</title>
<script type="text/javascript" src="../static/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"> -->

</head>
<body>

<!-- 后端需要做的 1、试卷评分（实际上就是编辑试题）finished 2、模糊查询 每个用户名（转换为用户id无所谓）和 每种试题统一批改 3、初始化分页+模糊查询的分页 -->

<!--答题记录编辑模态框 -->
<div class="modal fade" id="gradeUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">答题评分</h4>
      </div>
      <div class="modal-body">
	        <form class="form-horizontal" id="input_up_form">
	 			
	 			<!-- <div class="form-group">
	   				 <label class="col-sm-2 control-label">答题索引</label>
	   			 		<div class="col-sm-10">
	      					  <p class="form-control-static" id="vid_up_static" name="vid"></p>
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div> -->
	  			 
	  			 <!--3 static value can not get  -->
	  			 <!-- <div class="form-group">
	   				 <label class="col-sm-2 control-label">用户编号</label>
	   			 		<div class="col-sm-10">
	      					  <p class="form-control-static" id="vuserid_up_static" name="vuserid"></p>
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">用户名</label>
	   			 		<div class="col-sm-10">
	      					  <p class="form-control-static" id="vusername_up_static" name="vusername"></p>
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">试题编号</label>
	   			 		<div class="col-sm-10">
	      					  <p class="form-control-static" id="vtestid_up_static" name="vtestid"></p>
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div> -->
	 			
		 			<div class="form-group">
					   <label class="col-sm-2 control-label">答题索引</label>
					   <div class="col-sm-10">
					   		<input type="text" class="form-control" id="vid_up_static" name="vid" readonly>
		      					  <!-- <p class="form-control-static" id="vid_up_static" name="vid"></p> -->
		      				<span class="help-block"></span>
		   			   </div>
	  				</div>
	 			
	  			  <div class="form-group">
				   <label class="col-sm-2 control-label">用户编号</label>
				   <div class="col-sm-10">
				   		<input type="text" class="form-control" id="vuserid_up_static" name="vuserid" readonly>
	      					  <!-- <p class="form-control-static" id="vid_up_static" name="vid"></p> -->
	      				<span class="help-block"></span>
	   			   </div>
  				</div>
  				<div class="form-group">
				   <label class="col-sm-2 control-label">用户名</label>
				   <div class="col-sm-10">
				   		<input type="text" class="form-control" id="vusername_up_static" name="vusername" readonly>
	      					  <!-- <p class="form-control-static" id="vid_up_static" name="vid"></p> -->
	      				<span class="help-block"></span>
	   			   </div>
  				</div>
  				<div class="form-group">
				   <label class="col-sm-2 control-label">试题编号</label>
				   <div class="col-sm-10">
				   		<input type="text" class="form-control" id="vtestid_up_static" name="vtestid" readonly>
	      					  <!-- <p class="form-control-static" id="vid_up_static" name="vid"></p> -->
	      				<span class="help-block"></span>
	   			   </div>
  				</div>
  				<div class="form-group">
				   <label class="col-sm-2 control-label">试题类型</label>
				   <div class="col-sm-10">
				   		<input type="text" class="form-control" id="vtestype_up_static" name="vtestype" readonly>
	      					  <!-- <p class="form-control-static" id="vid_up_static" name="vid"></p> -->
	      				<span class="help-block"></span>
	   			   </div>
  				</div>
	  			  
				 <div class="form-group">
	   				 <label class="col-sm-2 control-label">试题内容</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="vcontent_up_input" name="vcontent"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">标准答案</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="vanswer_up_input" name="vanswer"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
					 <label class="col-sm-2 control-label">用户答案</label>
					 	<div class="col-sm-10">
	      					<textarea class="form-control" rows="2" id="vuseranswer_up_input" name="vuseranswer"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
				 </div>
				 <div class="form-group">
					 <label class="col-sm-2 control-label">评分</label>
					 	<div class="col-sm-10">
	      					<textarea class="form-control" rows="1" id="vgrade_up_input" name="vgrade"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
				 </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="grade_up_btn">更新</button>
      </div>
    </div>
  </div>
</div>
<!-- 后端需要做的 1、试卷评分（实际上就是编辑试题）finished 2、模糊查询 每个用户名（转换为用户id无所谓）和 每种试题统一批改 3、初始化分页+模糊查询的分页 -->

	<!--搭建页面  -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>试题评分</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		
			<!--id精准查询  -->
			<div class="col-md-4">
				<label>试题索引</label>
				<input type="text" id="vid_select_text" placeholder="索引号:1" aria-describedby="basic-addon1"/>
				<button class="btn btn-primary btn-sm" id="vid_select_btn">查询</button>
			</div>
	
			
			<!--答题内容和试题类别选择 模糊查询  -->
			<div class="col-md-6">
				<label>答题记录</label>
				<input type="text" style="width:100px" id="vcontent_select_text" placeholder="试题内容:李白"/>
				
				<!-- <label>用户名</label> -->
				<input type="text" style="width:100px" id="vusername_select_text" placeholder="用户名:xujia.."/>
				
				<select id="test_typeid_select" style="width:100px;height:25.34px">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>无</option>
				</select>
			
				<button class="btn btn-primary btn-sm" id="vcontent_select_btn">检索</button>
			</div>
			
		</div>
		<!-- 显示表格信息 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="grade_table" style="table-layout:fixed">
					<thead>
						<tr>
							<!-- <th width="5%">
								<input type="checkbox" id="check_all"/>
							</th> -->
							<!--显示条目需要建立视图 grade_view -->
							<th width="5%">答题索引</th>
							<th width="10%">用户编号</th>
							<th width="8%">用户名</th>
							<th width="5%">试题编号</th>
							<th width="5%">类型</th>
							<th width="20%">试题内容</th>
							<th width="10%">标准答案</th>
							<th width="10%">用户答案</th>
							<th width="5%">评分</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageInfo.list}" var="gradeview">
							<tr>
							<!-- <td><input type="checkbox" class="check_item"/></td> -->
							<td>${gradeview.vid}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vuserid}</td>
							<td>${gradeview.vusername}</td>
							<td>${gradeview.vtestid}</td>
							<td>${gradeview.vtestype}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vcontent}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vanswer}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vuseranswer}</td>
							<td>${gradeview.vgrade}</td>
							<td>
								<button class="btn btn-primary edit_btn">
									 <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
									编辑
								</button>
							</td>
						</tr>			
						
						</c:forEach>
					</tbody>
							
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info"><p>当前${pageInfo.pageNum}页，总${pageInfo.pages}页，总${pageInfo.total}条记录</p></div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav">
				<nav aria-label="Page navigation">
  					<ul class="pagination">
  						<li><a href="../gradeController/showUserAnswerView?pn=1">首页</a></li>
  						<c:if test="${pageInfo.hasPreviousPage}">
  							<li>
      							<a href="../gradeController/showUserAnswerView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
       							 <span aria-hidden="true">&laquo;</span>
      							</a>
				    	 	</li>
  						</c:if>
				     
				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
				     	<c:if test="${page_Num ==pageInfo.pageNum }">
				     		<li class="active"><a href="#">${page_Num}</a></li>
				     	</c:if>
				     	<c:if test="${page_Num !=pageInfo.pageNum }">
				     		<li><a href="../gradeController/showUserAnswerView?pn=${page_Num}">${page_Num}</a></li>
				     	</c:if>
				 
				     </c:forEach>
				     
				     <c:if test="${pageInfo.hasNextPage }">
				     	<li>
					      <a href="../gradeController/showUserAnswerView?pn=${pageInfo.pageNum+1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
				    	</li>
				     </c:if>
				     
				    <li><a href="../gradeController/showUserAnswerView?pn=${pageInfo.pages}"">末页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>

<script type=text/javascript>

	//显示校验结果span
	function show_validate_msg(ele,status,msg){
		//清除当前元素校验状态
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if(status == "success"){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}else if(status == "error"){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	
	//对编辑的表单进行校验
	function validate_up_form(){
		
		/* var Vid = $("#vid_up_static").val();
		var Vuserid = $("#vuserid_up_static").val();
		var Vusername = $("#vusername_up_static").val();
		var Vtestid = $("#vtestid_up_static").val(); */
		//上面的static
		var Vcontent = $("#vcontent_up_input").val();
		var Vanswer = $("#vanswer_up_input").val();
		var Vuseranswer = $("#vuseranswer_up_input").val();
		var Vgrade = $("#vgrade_up_input").val();
		var reg=/^[-+]?\d*$/; 
		
		//1、校验
		if(Vcontent==null||Vcontent==""){
			show_validate_msg("#vcontent_up_input","error","试题内容不可为空");
			return false;
		}else{
			show_validate_msg("#vcontent_up_input","success","");
		}
		
		if(Vanswer==null||Vanswer==""){
			show_validate_msg("#vanswer_up_input","error","标准答案不可为空");
			return false;
		}else{
			show_validate_msg("#vanswer_up_input","success","");
		}
		
		if(Vuseranswer==null||Vuseranswer==""){
			show_validate_msg("#vuseranswer_up_input","error","用户答案不可为空");
			return false;
		}else{
			show_validate_msg("#vuseranswer_up_input","success","");
		}
		
		if(Vgrade==null||Vgrade==""){
			show_validate_msg("#vgrade_up_input","error","成绩不可为空");
			return false;
		}else if(!reg.test(Vgrade)){
			show_validate_msg("#vgrade_up_input","error","请输入有效数字");
			return false;
		}else if(parseInt(Vgrade)<0){
			show_validate_msg("#vgrade_up_input","error","成绩不可为负数");
			return false;
		}else{
			show_validate_msg("#vgrade_up_input","success","");
		}
		
	 return true;
		
	}
	
	
	//点击更新按钮
	$("#grade_up_btn").click(function(){
		
		//1、校验关键数据不能为空
		if(!validate_up_form()){
			return false;
		}
		
		//alert($("#gradeUpdateModal form").serialize());
		//console.log($("#gradeUpdateModal form").serialize()); ！！！！静态p标签无法序列化多个p的值
		
		//2、发送ajax请求 保存更新数据
		$.ajax({
			url:"../gradeController/showUserAnswerView/"+$(this).attr("edit-id"),
			type:"POST",
			data:$("#gradeUpdateModal form").serialize()+"&_method=PUT",
			success:function(result){
				alert(result.msg);
				$("#gradeUpdateModal").modal('hide');
				window.location.href="../gradeController/showUserAnswerView?pn=${pageInfo.pageNum}";
			}
		});
	});
	
	//为编辑按钮添加响应事件
	$(document).on("click",".edit_btn",function(){
		
		//1、查出答题索引 这个视图是靠 一个自增id 号来区分不同的
		
		//alert($(".edit_btn").parent().parent().find("td").first().text());
		
		var id = $(this).parents("tr").find("td:eq(0)").text();
		//var id = $(this).parents().find("td:eq(1)").text();有多个符合parents条件的id号
		//alert(id); 
		
		//重写getPoetry(poetryid) 获得诗歌所有详细信息，展示在编辑栏;
		getGradeView(id);
		
		//2、id传递给模态框更新按钮
		$("#grade_up_btn").attr("edit-id",id);
		
		$("#gradeUpdateModal").modal({
			backdrop:"static"
		});
		
		
	});
	
	function getGradeView(id){
		$.ajax({
			url:"../gradeController/showUserAnswerView/"+id,
			type:"GET",
			success:function(result){
				//console.log(result);
				var viewData = result.extend.showUserAnswerView;
				
				/* var Vid = $("#vid_up_input").val();
				var Vuserid = $("#vuserid_up_input").val();
				var Vusername = $("#vusername_up_input").val();
				var Vtestid = $("#vtestid_up_input").val();
				//上面的static
				var Vcontent = $("#vcontent_up_input").val();
				var Vanswer = $("#vanswer_up_input").val();
				var Vuseranswer = $("#vuseranswer_up_input").val();
				var Vgrade = $("#vgrade_up_input").val(); */
				
				//给文本框赋值
				$("#vid_up_static").val(viewData.vid);
				$("#vuserid_up_static").val(viewData.vuserid);
				$("#vusername_up_static").val(viewData.vusername);
				$("#vtestid_up_static").val(viewData.vtestid);
				$("#vtestype_up_static").val(viewData.vtestype);
				
				$("#vcontent_up_input").val(viewData.vcontent);
				$("#vanswer_up_input").val(viewData.vanswer);
				$("#vuseranswer_up_input").val(viewData.vuseranswer);
				$("#vgrade_up_input").val(viewData.vgrade);
				
			}
		});
	}
	
	//  select doing
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//诗歌模糊查询doing！
	
	//加载到grade.jsp页面时,对select响应(发送ajax请求题型)
	$(document).ready(function( ){
		
		$("#test_typeid_select").empty();
		
		$.ajax({
			url:"../TypeidController/testype",
			type:"GET",
			success:function(result){
				//console.log(result);
				//"testype":[{"typeid":1,"typename":"选择"},{"typeid":2,"typename":"判断"},{"typeid":3,"typename":"简答"}
				//显示在下拉框中
				$("#test_typeid_select");
				$.each(result.extend.testype,function(){
					var optionEle = $("<option></option>").append(this.typename).attr("value",this.typeid);
					optionEle.appendTo("#test_typeid_select");
				});
				var optionAll= $("<option></option>").append("所有").attr("value","0");
				optionAll.appendTo("#test_typeid_select");
				
			}
		});
	});
	
	//响应查询按钮
	$("#vid_select_btn").click(function(){
		
		//1、获取文本框内容
		var vId = $("#vid_select_text").val();
		//2、校验文本框内容
		if(vId==null||vId==""){
			alert("请输入答题索引！");
			return false;
			}
		
		//3、发送ajax请求 查询诗歌
		$.ajax({
			url:"../gradeController/showUserAnswerView/"+vId,
			type:"GET",
			success:function(result){
				//console.log(result);
				var Data = result.extend.showUserAnswerView;
				//1、清空td
				$("#page_nav nav").detach();
				$("#page_info p").detach();
				$("#grade_table td").detach();
				//2、解析数据，回显表格
				/* var checkboxTd = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item")); */
			/* 	
				<td>${gradeview.vid}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vuserid}</td>
							<td>${gradeview.vusername}</td>
							<td>${gradeview.vtestid}</td>
							<td>${gradeview.vtestype}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vcontent}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vanswer}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${gradeview.vuseranswer}</td>
							<td>${gradeview.vgrade}</td> */
				
				var vidTd = $("<td></td>").append(Data.vid);
		
				//为td添加style
				var vuseridTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.vuserid);
				var vusernameTd = $("<td></td>").append(Data.vusername);
				var vtestidTd = $("<td></td>").append(Data.vtestid);
				var vtestypeTd = $("<td></td>").append(Data.vtestype);
				var vcontentTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.vcontent);
				var vanswerTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.vanswer);
				var vuseranswerTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.vuseranswer);
				var vgradeTd = $("<td></td>").append(Data.vgrade);
				
				var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
	
				var btnTd = $("<td></td>").append(editBtn);
				
				$("<tr></tr>").append(vidTd)
				.append(vuseridTd)
				.append(vusernameTd)
				.append(vtestidTd)
				.append(vtestypeTd)
				.append(vcontentTd)
				.append(vanswerTd)
				.append(vuseranswerTd)
				.append(vgradeTd)
				.append(btnTd)
				.appendTo("#grade_table tbody");
				
				}
		});
	});
	
	
	//响应检索按钮 【试题内容】和【试题类别】和【用户名】的模糊查询
	$("#vcontent_select_btn").click(function(){
		//1、获取文本框内容，下拉框内容
		var vContent = $("#vcontent_select_text").val();
		var vUsername = $("#vusername_select_text").val();
		var vTypeid = $("#test_typeid_select").val();
		var rs = "";
		rs = vContent+"-"+vUsername+"-"+vTypeid;
		/* if(vContent==""){
			alert("为空字符串");
		} */
		//console.log("rs:"+rs);
		//alert(testContent+","+Typeid);
		//2、发送ajax POST请求 回显到表格中
		$.ajax({
			url:"../gradeController/showLikeGradeView",
			data:"rs="+rs,
			type:"POST",
			success:function(result){
				//console.log(result);
				//extend":{"pageInfo":{"pageNum":1,"pageSize":5,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"testid":1001,"typeid":1,"testcontent":"《静夜思》的作者是谁？","optiona":"李白","optionb":"白居易","optionc":"陆游","optiond":"杜甫","answer":"A","explaination":"《静夜思》是唐代诗人李白所作的一首五言古诗  。此诗描写了秋日夜晚，诗人于屋内抬头望月的所感。诗中运用比喻、衬托等手法，表达客居思乡之情，语言清新朴素而韵味含蓄无穷，历来广为传诵。","tips":"诗仙"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}}
				//1、解析数据、回显表格
				refresh_grade_table(result);
				//2、显示分页信息
				refresh_page_info(result);
				//3、显示分页条
				refresh_page_nav(result);
				
				}
			
			});
	});
	
	
	function refresh_grade_table(result){
		var grade = result.extend.pageInfo.list;
		$("#grade_table td").detach();
		$.each(grade,function(index,item){
			
			var vidTd = $("<td></td>").append(item.vid);
			
			//为td添加style
			var vuseridTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.vuserid);
			var vusernameTd = $("<td></td>").append(item.vusername);
			var vtestidTd = $("<td></td>").append(item.vtestid);
			var vtestypeTd = $("<td></td>").append(item.vtestype);
			var vcontentTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.vcontent);
			var vanswerTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.vanswer);
			var vuseranswerTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.vuseranswer);
			var vgradeTd = $("<td></td>").append(item.vgrade);
			
			var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");

			var btnTd = $("<td></td>").append(editBtn);
			
			$("<tr></tr>").append(vidTd)
			.append(vuseridTd)
			.append(vusernameTd)
			.append(vtestidTd)
			.append(vtestypeTd)
			.append(vcontentTd)
			.append(vanswerTd)
			.append(vuseranswerTd)
			.append(vgradeTd)
			.append(btnTd)
			.appendTo("#grade_table tbody");
			
		});
		
	}
	//解析显示分页信息
	function refresh_page_info(result){
		
		$("#page_info p").detach();
		//当前页
		var pageNum = result.extend.pageInfo.pageNum;
		//总页数
		var pages = result.extend.pageInfo.pages;
		//总记录数
		var total = result.extend.pageInfo.total; 
		$("#page_info").append($("<p></p>").append("当前"+pageNum+"页，总"+pages+"页，总"+total+"条记录"));
		
	}
	//解析显示分页条
	function refresh_page_nav(result){
		
		//删除原来的导航条
		$("#page_nav nav").detach();
		
		var ul = $("<ul></ul>").addClass("pagination");
		var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		ul.append(firstPageLi).append(prePageLi);
		
		//响应首页
		firstPageLi.click(function(){
			to_page(1);
		});
		
		//响应<<
		prePageLi.click(function(){
			if(result.extend.pageInfo.pageNum-1>=1){
				to_page(result.extend.pageInfo.pageNum-1);
			}
		});

		//响应>>
		nextPageLi.click(function(){
			if(result.extend.pageInfo.pageNum+1<=result.extend.pageInfo.pages){
				to_page(result.extend.pageInfo.pageNum+1);
			}
		});

		//响应末页
		lastPageLi.click(function(){
			to_page(result.extend.pageInfo.pages);
		});
		
		//1.2.3遍历结束
		$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
			var numLi = $("<li></li>").append($("<a></a>").append(item));
			if(result.extend.pageInfo.pageNum == item){
				numLi.addClass("active");
			}
			numLi.click(function(){
				to_page(item);
			});
			ul.append(numLi);
		});
		//添加下一页 末页
		ul.append(nextPageLi).append(lastPageLi);
		var navEle = $("<nav></nav>").append(ul);
		navEle.appendTo("#page_nav");
	}
	
	function to_page(pn){
		
		var vContent = $("#vcontent_select_text").val();
		var vUsername = $("#vusername_select_text").val();
		var vTypeid = $("#test_typeid_select").val();
		var rs = "";
		rs = vContent+"-"+vUsername+"-"+vTypeid;
		
		$.ajax({
			url:"../gradeController/showLikeGradeView",
			async:false,
			data:{
				"rs":rs,
				"pn":pn
				},
			type:"POST",
			success:function(result){
				//console.log(result);
				//alert(result);
				//window.location.href = "../userController/showLikeUser";
				//传页面必须要有一个username参数 直接调用改参数为null所以返回的是空的
				//1、解析并显示用户数据
				refresh_grade_table(result);
				//2、解析并显示分页信息
				refresh_page_info(result);
				//3、加息显示分页条
				refresh_page_nav(result);
				
			}
		});
	}

</script>
</html>