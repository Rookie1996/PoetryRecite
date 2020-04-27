<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>诗歌管理</title>
<script type="text/javascript" src="../static/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"> -->

</head>
<body>

<!--诗歌编辑模态框 -->
<div class="modal fade" id="poetryUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">诗歌编辑</h4>
      </div>
      <div class="modal-body">
	        <form class="form-horizontal" id="input_up_form">
	 			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">诗歌编号</label>
	   			 		<div class="col-sm-10">
	      					  <p class="form-control-static" id="poetryid_up_static" name="poetryid"></p>
	      					<!-- <span class="help-block"></span> -->
	   			 		</div>
	  			 </div>
	  			  <div class="form-group">
					 <label class="col-sm-2 control-label">题目</label>
						 <div class="col-sm-10">
						 	<input type="text" id="subject_up_input" name="subject" placeholder="subject"/>
							<span class="help-block"></span>
						 </div>
				  </div>
				  <div class="form-group">
					 <label class="col-sm-2 control-label">作者</label>
						 <div class="col-sm-10">
						 	<input type="text" id="author_up_input" name="author" placeholder="author"/>
							<span class="help-block"></span>
						 </div>
				  </div>
				  <div class="form-group">
					 <label class="col-sm-2 control-label">朝代</label>
						 <div class="col-sm-10">
						 	<input type="text" id="dynasty_up_input" name="dynasty" placeholder="dynasty"/>
							<span class="help-block"></span>
						 </div>
				  </div>
	  			 
				 <div class="form-group">
	   				 <label class="col-sm-2 control-label">诗歌内容</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="content_up_input" name="content" placeholder="Content"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">详细</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="detail_up_input" name="detail" placeholder="Detail"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
					 <label class="col-sm-2 control-label">主题</label>
					 	<div class="col-sm-10">
	      					<textarea class="form-control" rows="1" id="theme_up_input" name="theme" placeholder="theme"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
				 </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="poetry_up_btn">更新</button>
      </div>
    </div>
  </div>
</div>

<!--诗歌添加模态框 -->
<div class="modal fade" id="poetryAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">诗歌添加</h4>
      </div>
      <div class="modal-body">
	        <form class="form-horizontal" id="input_add_form">
	 			 
	 			 <div class="form-group">
					 <label class="col-sm-2 control-label">题目</label>
						 <div class="col-sm-10">
						 	<input type="text" id="subject_add_input" name="subject" placeholder="subject"/>
							<span class="help-block"></span>
						 </div>
				  </div>
				  <div class="form-group">
					 <label class="col-sm-2 control-label">作者</label>
						 <div class="col-sm-10">
						 	<input type="text" id="author_add_input" name="author" placeholder="author"/>
							<span class="help-block"></span>
						 </div>
				  </div>
				  <div class="form-group">
					 <label class="col-sm-2 control-label">朝代</label>
						 <div class="col-sm-10">
						 	<input type="text" id="dynasty_add_input" name="dynasty" placeholder="dynasty"/>
							<span class="help-block"></span>
						 </div>
				  </div>
	  			 
				 <div class="form-group">
	   				 <label class="col-sm-2 control-label">诗歌内容</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="content_add_input" name="content" placeholder="Content"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">详细</label>
	   			 		<div class="col-sm-10">
	      					<textarea class="form-control" rows="3" id="detail_add_input" name="detail" placeholder="Detail"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
					 <label class="col-sm-2 control-label">主题</label>
					 	<div class="col-sm-10">
	      					<textarea class="form-control" rows="1" id="theme_add_input" name="theme" placeholder="theme"></textarea> 
	      					<span class="help-block"></span>
	   			 		</div>
				 </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="poetry_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>

	<!--搭建页面  -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>诗歌管理</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
		
			<!--诗歌编号精准查询  -->
			<div class="col-md-4  col-md-offset-2">
				<label>诗歌编号</label>
				<input type="text" id="poetryid_select_text" placeholder="诗歌编号:1"/>
				<button class="btn btn-primary btn-sm" id="poetryid_select_btn">查询</button>
			</div>
			
			<!--试题内容和试题类别选择 模糊查询  -->
			<!-- <div class="col-md-4">
				<label>试题内容</label>
				<input type="text" style="width:100px" id="testcontent_select_text" placeholder="试题内容:李白"/>
				
				<select id="test_typeid_select" style="width:100px;height:25.34px">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				</select>
			
				<button class="btn btn-primary btn-sm" id="testcontent_select_btn">检索</button>
			</div> -->
			<div class="col-md-2">
				<button class="btn btn-primary btn-sm" id="poetry_add_modal_btn">新增</button>
				<button class="btn btn-danger btn-sm" id="poetry_delete_all_modal_btn">删除</button>
			</div>
		</div>
		<!-- 显示表格信息 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="poetry_table" style="table-layout:fixed">
					<thead>
						<tr>
							<th width="5%">
								<input type="checkbox" id="check_all"/>
							</th>
							<th width="5%">编号</th>
							<th width="10%">标题</th>
							<th width="10%">作者</th>
							<th width="5%">朝代</th>
							<th width="20%">内容</th>
							<th width="20%">详细</th>
							<th width="10%">主题</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageInfo.list}" var="poetry">
							<tr>
							<td><input type="checkbox" class="check_item"/></td>
							<td>${poetry.poetryid}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${poetry.subject}</td>
							<td>${poetry.author}</td>
							<td>${poetry.dynasty}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${poetry.content}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${poetry.detail}</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${poetry.theme}</td>
							<td>
								<button class="btn btn-primary edit_btn">
									 <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger delete_btn">
									 <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									删除
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
  						<li><a href="../poetryController/showManagePoetry?pn=1">首页</a></li>
  						<c:if test="${pageInfo.hasPreviousPage}">
  							<li>
      							<a href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum-1}" aria-label="Previous">
       							 <span aria-hidden="true">&laquo;</span>
      							</a>
				    	 	</li>
  						</c:if>
				     
				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
				     	<c:if test="${page_Num ==pageInfo.pageNum }">
				     		<li class="active"><a href="#">${page_Num}</a></li>
				     	</c:if>
				     	<c:if test="${page_Num !=pageInfo.pageNum }">
				     		<li><a href="../poetryController/showManagePoetry?pn=${page_Num}">${page_Num}</a></li>
				     	</c:if>
				 
				     </c:forEach>
				     
				     <c:if test="${pageInfo.hasNextPage }">
				     	<li>
					      <a href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum+1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
				    	</li>
				     </c:if>
				     
				    <li><a href="../poetryController/showManagePoetry?pn=${pageInfo.pages}"">末页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>

<script type=text/javascript>

	//对添加诗歌的表单进行校验
	function validate_add_form(){
		
		var Subject = $("#subject_add_input").val();
		var Author = $("#author_add_input").val();
		var Dynasty = $("#dynasty_add_input").val();
		var Content = $("#content_add_input").val();
		var Detail = $("#detail_add_input").val();
		var Theme = $("#theme_add_input").val();
		
		//1、校验
		if(Subject==null||Subject==""){
			show_validate_msg("#subject_add_input","error","题目不可为空");
			return false;
		}else{
			show_validate_msg("#subject_add_input","success","");
		}
		
		if(Author==null||Author==""){
			show_validate_msg("#author_add_input","error","作者不可为空");
			return false;
		}else{
			show_validate_msg("#author_add_input","success","");
		}
		
		if(Dynasty==null||Dynasty==""){
			show_validate_msg("#dynasty_add_input","error","朝代不可为空");
			return false;
		}else{
			show_validate_msg("#dynasty_add_input","success","");
		}
		
		if(Content==null||Content==""){
			show_validate_msg("#content_add_input","error","内容不可为空");
			return false;
		}else{
			show_validate_msg("#content_add_input","success","");
		}
		
		if(Detail==null||Detail==""){
			show_validate_msg("#detail_add_input","error","详细解释不可为空");
			return false;
		}else{
			show_validate_msg("#detail_add_input","success","");
		}
		
		if(Theme==null||Theme==""){
			show_validate_msg("#theme_add_input","error","主题不可为空");
			return false;
		}else{
			show_validate_msg("#theme_add_input","success","");
		}
		return true;
	}
	
	//显示校验数据
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
	
	//点击新增按钮弹出模态框
	$("#poetry_add_modal_btn").click(function() {
		$("#poetryAddModal").modal({
			backdrop:"static"
		});
	})
	
	//点击添加poetry_save_btn保存按钮
	$("#poetry_save_btn").click(function() {
		//模态框中填写的表单数据请求给服务器
		
		//1、先对提交给服务器的数据进行校验
		if(!validate_add_form()){
			return false;
		};
		
		//2、发送ajax请求保存试题
		$.ajax({
			url:"../poetryController/showManagePoetry",
			type:"POST",
			data:$("#input_add_form").serialize(),
			success:function(result){
				alert(result.msg);
				//1、保存成功
				//2、关闭模态框
				$("#poetryAddModal").modal('hide');	
				//3、跳转到该界面
				window.location.href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum}";
				
			}
		})
	})
	
	//poetry_add finished 2019/5/1 21:38
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//对编辑试题的表单进行校验
	function validate_up_form(){
		
		var Subject = $("#subject_up_input").val();
		var Author = $("#author_up_input").val();
		var Dynasty = $("#dynasty_up_input").val();
		var Content = $("#content_up_input").val();
		var Detail = $("#detail_up_input").val();
		var Theme = $("#theme_up_input").val();
		
		//1、校验
		if(Subject==null||Subject==""){
			show_validate_msg("#subject_up_input","error","题目不可为空");
			return false;
		}else{
			show_validate_msg("#subject_up_input","success","");
		}
		
		if(Author==null||Author==""){
			show_validate_msg("#author_up_input","error","作者不可为空");
			return false;
		}else{
			show_validate_msg("#author_up_input","success","");
		}
		
		if(Dynasty==null||Dynasty==""){
			show_validate_msg("#dynasty_up_input","error","朝代不可为空");
			return false;
		}else{
			show_validate_msg("#dynasty_up_input","success","");
		}
		
		if(Content==null||Content==""){
			show_validate_msg("#content_up_input","error","内容不可为空");
			return false;
		}else{
			show_validate_msg("#content_up_input","success","");
		}
		
		if(Detail==null||Detail==""){
			show_validate_msg("#detail_up_input","error","详细解释不可为空");
			return false;
		}else{
			show_validate_msg("#detail_up_input","success","");
		}
		
		if(Theme==null||Theme==""){
			show_validate_msg("#theme_up_input","error","主题不可为空");
			return false;
		}else{
			show_validate_msg("#theme_up_input","success","");
		}
		return true;
		
	}
	
	
	//点击更新按钮
	$("#poetry_up_btn").click(function(){
		
		//1、校验关键数据不能为空
		//2、修改题目类型时answer的形式随之出现改变 。简答判断是 testarea/选择题是radio另一个是禁用状态。
		//3、判断只能为0-错/1-对;  简答不能为空; 按钮不能不选择
		if(!validate_up_form()){
			return false;
		}
		
		//4、发送ajax请求 保存更新数据
		$.ajax({
			url:"../poetryController/showManagePoetry/"+$(this).attr("edit-id"),
			type:"POST",
			data:$("#poetryUpdateModal form").serialize()+"&_method=PUT",
			success:function(result){
				alert(result.msg);
				$("#poetryUpdateModal").modal('hide');
				window.location.href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum}";
			}
		});
	});
	
	//为编辑按钮添加响应事件
	$(document).on("click",".edit_btn",function(){
		//alert("edit");
		//1、查出试题id
		//alert($(".edit_btn").parent().parent().find("td").first().text());
		
		var id = $(this).parents("tr").find("td:eq(1)").text();
		//var id = $(this).parents().find("td:eq(1)").text();有多个符合parents条件的id号
		//alert(id); 
		
		//重写getPoetry(poetryid) 获得诗歌所有详细信息，展示在编辑栏;
		getPoetry(id);
		
		//2、id传递给模态框更新按钮
		$("#poetry_up_btn").attr("edit-id",id);
		
		$("#poetryUpdateModal").modal({
			backdrop:"static"
		});
		
		
	});
	
	function getPoetry(id){
		$.ajax({
			url:"../poetryController/showManagePoetry/"+id,
			type:"GET",
			success:function(result){
				//console.log(result);
				var Data = result.extend.showManagePoetry;
				$("#poetryid_up_static").text(Data.poetryid);
				$("#subject_up_input").val(Data.subject);
				$("#author_up_input").val(Data.author);
				$("#dynasty_up_input").val(Data.dynasty);
				$("#content_up_input").val(Data.content);
				$("#detail_up_input").val(Data.detail);
				$("#theme_up_input").val(Data.theme);
			}
		});
	}
	
	/* add completed
	   update completed
	   delete doing  2019/5/2 10:49    */
	/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//为每行的删除按钮添加响应事件--单个删除
	$(document).on("click",".delete_btn",function(){
		//是否确认删除
		//alert($(this).parents("tr").find("td:eq(1)").text());
		var poetryId = $(this).parents("tr").find("td:eq(1)").text();
		if(confirm("确认删除【"+poetryId+"】诗歌吗？")){
			//确认 发送ajax删除请求
			$.ajax({
				url:"../poetryController/showManagePoetry/"+poetryId,
				type:"DELETE",
				success:function(result){
					alert(result.msg);
					window.location.href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum}";
				}
			})
		}
	});
	//完成全选-全不选
	$("#check_all").click(function(){
		//prop读取dom原生的属性的值,attr获取自定义属性的值
		//alert($(this).prop("checked"));	
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	//.check_item 后来创建的组件
	$(document).on("click",".check_item",function(){
		//判断当前选择框个数是否等于总个数
		var flag = $(".check_item:checked").length==$(".check_item").length;
		$("#check_all").prop("checked",flag);
	});
	
	//点击全部删除，批量删除
	$("#poetry_delete_all_modal_btn").click(function(){
		
		var poetryId = "";
		var del_idstr = "";
		$.each($(".check_item:checked"),function(){
			
			//alert($(this).parents("tr").find("td:eq(2)").text());
			poetryId += $(this).parents("tr").find("td:eq(1)").text()+","; 
			//组装试题id字符串
			del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
			
		});
		//去除多余符号-，
		poetryId = poetryId.substring(0,poetryId.length-1);
		del_idstr = del_idstr.substring(0,del_idstr.length-1);
		
		if(confirm("确认删除【"+poetryId+"】这些诗歌吗？")){
			//发送删除ajax请求
			$.ajax({
				url:"../poetryController/showManagePoetry/"+del_idstr,
				type:"DELETE",
				success:function(result){
					alert(result.msg);
					window.location.href="../poetryController/showManagePoetry?pn=${pageInfo.pageNum}";
					
				}
			});
		}
	});
	
	//  select doing
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//诗歌模糊查询doing！
	
	//加载到test.jsp页面时,对select响应(发送ajax请求题型)
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
				
			}
		});
	});
	
	//响应查询按钮
	$("#poetryid_select_btn").click(function(){
		
		//1、获取文本框内容
		var poetryId = $("#poetryid_select_text").val();
		//2、校验文本框内容
		if(poetryId==null||poetryId==""){
			alert("请输入诗歌编号！");
			return false;
			}
		
		//3、发送ajax请求 查询诗歌
		$.ajax({
			url:"../poetryController/showManagePoetry/"+poetryId,
			type:"GET",
			success:function(result){
				//console.log(result);
				var Data = result.extend.showManagePoetry;
				//1、清空td
				$("#page_nav nav").detach();
				$("#page_info p").detach();
				$("#poetry_table td").detach();
				//2、解析数据，回显表格
				var checkboxTd = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
				var poetryidTd = $("<td></td>").append(Data.poetryid);
		
				//为td添加style
				var subjectTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.subject);
				var contentTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.content);
				var authorTd = $("<td></td>").append(Data.author);
				var dynastyTd = $("<td></td>").append(Data.dynasty);
				var detailTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.detail);
				var themeTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(Data.theme);
				
				var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
				var delBtn = $("<button></button>").addClass("btn btn-danger delete_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
				
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(checkboxTd)
				.append(poetryidTd)
				.append(subjectTd)
				.append(contentTd)
				.append(authorTd)
				.append(dynastyTd)
				.append(detailTd)
				.append(themeTd)
				.append(btnTd)
				.appendTo("#poetry_table tbody");
				
				}
		});
	});
	
	
	//响应检索按钮 【试题内容】和【试题类别】的模糊查询
	$("#testcontent_select_btn").click(function(){
		//1、获取文本框内容，下拉框内容
		var testContent = $("#testcontent_select_text").val();
		var Typeid = $("#test_typeid_select").val();
		var rs = "";
		if(testContent==null||testContent==""){
			rs = Typeid;
		}else{
			rs = testContent+"-"+Typeid;
		}
		//alert(testContent+","+Typeid);
		//2、发送ajax POST请求 回显到表格中
		$.ajax({
			url:"../testController/showLikeTest",
			data:"rs="+rs,
			type:"POST",
			success:function(result){
				//console.log(result);
				//extend":{"pageInfo":{"pageNum":1,"pageSize":5,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"testid":1001,"typeid":1,"testcontent":"《静夜思》的作者是谁？","optiona":"李白","optionb":"白居易","optionc":"陆游","optiond":"杜甫","answer":"A","explaination":"《静夜思》是唐代诗人李白所作的一首五言古诗  。此诗描写了秋日夜晚，诗人于屋内抬头望月的所感。诗中运用比喻、衬托等手法，表达客居思乡之情，语言清新朴素而韵味含蓄无穷，历来广为传诵。","tips":"诗仙"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}}
				//1、解析数据、回显表格
				refresh_test_table(result);
				//2、显示分页信息
				refresh_page_info(result);
				//3、显示分页条
				refresh_page_nav(result);
				
				}
			
			});
	});
	
	
	function refresh_test_table(result){
		var test = result.extend.pageInfo.list;
		$("#test_table td").detach();
		$.each(test,function(index,item){
			//<td><input type="checkbox" class="check_item"/></td>
			var checkboxTd = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
			var testidTd = $("<td></td>").append(item.testid);
			//alert(item.testid);
			if(item.typeid==1){
				var typeidTd = $("<td></td>").append("选择");
			}else if(item.typeid==2){
				var typeidTd = $("<td></td>").append("判断");
			}else if(item.typeid==3){
				var typeidTd = $("<td></td>").append("简答");
			}
			//为td添加style
			var contentTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.testcontent);
			var optionaTd = $("<td></td>").append(item.optiona);
			var optionbTd = $("<td></td>").append(item.optionb);
			var optioncTd = $("<td></td>").append(item.optionc);
			var optiondTd = $("<td></td>").append(item.optiond);
			var answerTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.answer);
			
			//style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"
			var explainTd = $("<td></td>").attr("style","overflow:hidden;white-space:nowrap;text-overflow:ellipsis;").append(item.explaination);
			var tipsTd = $("<td></td>").append(item.tips);
			var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			var delBtn = $("<button></button>").addClass("btn btn-danger delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
			
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(checkboxTd)
			.append(testidTd)
			.append(typeidTd)
			.append(contentTd)
			.append(optionaTd)
			.append(optionbTd)
			.append(optioncTd)
			.append(optiondTd)
			.append(answerTd)
			.append(explainTd)
			.append(tipsTd)
			.append(btnTd)
			.appendTo("#test_table tbody");
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
		var testContent = $("#testcontent_select_text").val();
		var Typeid = $("#test_typeid_select").val();
		var rs = "";
		if(testContent==null||testContent==""){
			rs = Typeid;
		}else{
			rs = testContent+"-"+Typeid;
		}
		$.ajax({
			url:"../testController/showLikeTest",
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
				refresh_test_table(result);
				//2、解析并显示分页信息
				refresh_page_info(result);
				//3、加息显示分页条
				refresh_page_nav(result);
				
			}
		});
	}

</script>
</html>