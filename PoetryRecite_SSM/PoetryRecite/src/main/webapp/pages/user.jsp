<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script type="text/javascript" src="../static/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"> -->

</head>
<body>

<!--用户修改模态框 -->
<div class="modal fade" id="userUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">用户修改</h4>
      </div>
      <div class="modal-body">
	        <form class="form-horizontal" id="input_up_form">
	 			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">UserName</label>
	   			 		<div class="col-sm-10">
	      					<!-- <input type="text" class="form-control" id="username_up_input" name="username" placeholder="UserName"> -->
	      					  <p class="form-control-static" id="username_up_static" name="username"></p>
	      					<!-- <span class="help-block"></span> -->
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">Password</label>
	    				<div class="col-sm-10">
	      					<input type="password" class="form-control" id="password_up_input" name="password" placeholder="Password">
	      					<span class="help-block"></span>
	    				</div>
	  			 </div>
				 <div class="form-group">
	   				 <label class="col-sm-2 control-label">Email</label>
	   			 		<div class="col-sm-10">
	      					<input type="text" class="form-control" id="email_up_input" name="email" placeholder="327474264@qq.com">
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="user_up_btn">更新</button>
      </div>
    </div>
  </div>
</div>

<!--用户添加模态框 -->
<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">用户添加</h4>
      </div>
      <div class="modal-body">
	        <form class="form-horizontal" id="input_add_form">
	 			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">UserName</label>
	   			 		<div class="col-sm-10">
	      					<input type="text" class="form-control" id="username_add_input" name="username" placeholder="UserName">
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
	  			 <div class="form-group">
	   				 <label class="col-sm-2 control-label">Password</label>
	    				<div class="col-sm-10">
	      					<input type="password" class="form-control" id="password_add_input" name="password" placeholder="Password">
	      					<span class="help-block"></span>
	    				</div>
	  			 </div>
				 <div class="form-group">
	   				 <label class="col-sm-2 control-label">Email</label>
	   			 		<div class="col-sm-10">
	      					<input type="text" class="form-control" id="email_add_input" name="email" placeholder="327474264@qq.com">
	      					<span class="help-block"></span>
	   			 		</div>
	  			 </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="user_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>

	<!--搭建页面  -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>用户管理</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<label>用户名</label>
				<input type="text" id="user_select_text" placeholder="wangdog"/>
				<button class="btn btn-primary btn-sm" id="user_select_btn">查询</button>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary btn-sm" id="user_add_modal_btn">新增</button>
				<button class="btn btn-danger btn-sm" id="user_delete_all_modal_btn">删除</button>
			</div>
		</div>
		<!-- 显示表格信息 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="user_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>userId</th>
							<th>userName</th>
							<th>password</th>
							<th>email</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageInfo.list}" var="user">
							<tr>
							<td><input type="checkbox" class="check_item"/></td>
							<td>${user.userid}</td>
							<td>${user.username}</td>
							<td>${user.password}</td>
							<td>${user.email}</td>
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
  						<li><a href="../userController/showManageUser?pn=1">首页</a></li>
  						<c:if test="${pageInfo.hasPreviousPage}">
  							<li>
      							<a href="../userController/showManageUser?pn=${pageInfo.pageNum-1}" aria-label="Previous">
       							 <span aria-hidden="true">&laquo;</span>
      							</a>
				    	 	</li>
  						</c:if>
				     
				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
				     	<c:if test="${page_Num ==pageInfo.pageNum }">
				     		<li class="active"><a href="#">${page_Num}</a></li>
				     	</c:if>
				     	<c:if test="${page_Num !=pageInfo.pageNum }">
				     		<li><a href="../userController/showManageUser?pn=${page_Num}">${page_Num}</a></li>
				     	</c:if>
				 
				     </c:forEach>
				     
				     <c:if test="${pageInfo.hasNextPage }">
				     	<li>
					      <a href="../userController/showManageUser?pn=${pageInfo.pageNum+1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
				    	</li>
				     </c:if>
				     
				    <li><a href="../userController/showManageUser?pn=${pageInfo.pages}"">末页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>

<script type=text/javascript>

	//对添加用户表单进行校验
	function validate_add_form(){
		//1、校验用户名，使用正则表达式
		var userName = $("#username_add_input").val();
		var regName = /^[a-zA-Z0-9_-]{6,16}$/;
		if(!regName.test(userName)){
			//alert("用户名为字母、数字6-16位(区分大小写)");
			//需要清空之前样式
			/* $("#username_add_input").parent().addClass("has-error");
			$("#username_add_input").next("span").text("用户名为字母、数字6-16位(区分大小写)"); */
			show_validate_msg("#username_add_input","error","用户名为字母、数字6-16位(区分大小写)");
			return false;
		}else{
			/* $("#username_add_input").parent().addClass("has-success");
			$("#username_add_input").next("span").text(""); */
			show_validate_msg("#username_add_input","success","");
		}
		//2、校验密码
		var password = $("#password_add_input").val();
		if(password==null||password==""){
			//alert("请输入密码");
			/* $("#password_add_input").parent().addClass("has-error");
			$("#password_add_input").next("span").text("请输入密码"); */
			show_validate_msg("#password_add_input","error","请输入密码");
			return false;
		}else{
			show_validate_msg("#password_add_input","success","");
		}
		//3、校验邮箱
		var email = $("#email_add_input").val();
		var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(email == null || "" == email){
			//alert("请输入邮箱")
			/* $("#email_add_input").parent().addClass("has-error");
			$("#email_add_input").next("span").text("请输入邮箱"); */
			show_validate_msg("#email_add_input","error","请输入邮箱");
			return false;
		}else if (!regEmail.test(email)) {
			//alert("邮箱格式不正确");
			/* $("#email_add_input").parent().addClass("has-error");
			$("#email_add_input").next("span").text("邮箱格式不正确"); */
			show_validate_msg("#email_add_input","error","邮箱格式不正确");
			return false;
		}else{
			show_validate_msg("#email_add_input","success","");
		}
		return true;
	}
	
	//对username进行校验 是否使用
	 $("#username_add_input").change(function(){
		 //发送ajax请求 校验用户名
		 var userName = this.value;
		 $.ajax({
			 url:"../userController/checkuser",
			 data:"userName="+userName,
			 type:"POST",
			 success:function(result){
				 if(result.code==100){
					 show_validate_msg("#username_add_input","success","用户名可用");
					 $("#user_save_btn").attr("ajax-value","success");
				 }else{
					 show_validate_msg("#username_add_input","error","用户名不可用");
					 $("#user_save_btn").attr("ajax-value","error");
				 }
			 }
		 });
	 });
	
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
	$("#user_add_modal_btn").click(function() {
		$("#userAddModal").modal({
			backdrop:"static"
		});
	})
	
	//点击添加保存按钮
	$("#user_save_btn").click(function() {
		//模态框中填写的表单数据请求给服务器
		//1、先对提交给服务器的数据进行校验
		/* alert($("#input_add_form").serialize());
		var username_add_input=document.getElementById("username_add_input");
		alert(username_add_input.value); */
		/* alert($("#input_add_form").serialize()); */
		if(!validate_add_form()){
			return false;
		};
		//2、判断之前的ajax用户名是否可用
		if($(this).attr("ajax-value")=="error"){
			alert("用户名不可用");
			return false;
		}
		
		//3、发送ajax请求保存员工
		$.ajax({
			url:"../userController/showManageUser",
			type:"POST",
			data:$("#input_add_form").serialize(),
			success:function(result){
				alert(result.msg);
				//1、保存成功
				//2、关闭模态框
				$("#userAddModal").modal('hide');	
				//3、跳转到该界面
				window.location.href="../userController/showManageUser?pn=${pageInfo.pageNum}";
				
			}
		})
	})
	
	
	//点击更新按钮
	$("#user_up_btn").click(function(){
		//验证密码
		var password = $("#password_up_input").val();
		if(password==null||password==""){
			show_validate_msg("#password_up_input","error","请输入密码");
			return false;
		}else{
			show_validate_msg("#password_up_input","success","");
		}
		//验证邮箱
		var email = $("#email_up_input").val();
		var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(email == null || "" == email){
			show_validate_msg("#email_up_input","error","请输入邮箱");
			return false;
		}else if (!regEmail.test(email)) {
			show_validate_msg("#email_up_input","error","邮箱格式不正确");
			return false;
		}else{
			show_validate_msg("#email_up_input","success","");
		}
		//发送ajax请求 保存更新数据
		$.ajax({
			url:"../userController/showManageUser/"+$(this).attr("edit-id"),
			type:"POST",
			data:$("#userUpdateModal form").serialize()+"&_method=PUT",
			success:function(result){
				alert(result.msg);
				$("#userUpdateModal").modal('hide');
				window.location.href="../userController/showManageUser?pn=${pageInfo.pageNum}";
			}
		});
	});
	
	
	//为编辑按钮添加响应事件
	$(document).on("click",".edit_btn",function(){
		//alert("edit");
		//1、查出用户信息
		//alert($(".edit_btn").parent().parent().find("td").first().text());
		var id = $(this).parent().parent().find("td:eq(1)").text()
		getUser(id);
		
		//2、id传递给模态框更新按钮
		$("#user_up_btn").attr("edit-id",id);
		
		$("#userUpdateModal").modal({
			backdrop:"static"
		});
		
		
	});
	
	function getUser(id){
		$.ajax({
			url:"../userController/showManageUser/"+id,
			type:"GET",
			success:function(result){
				//console.log(result);
				var userData = result.extend.showManageUser;
				$("#username_up_static").text(userData.username);
				$("#password_up_input").val(userData.password);
				$("#email_up_input").val(userData.email);
				

			}
		});
	}
		
	//为每行的删除按钮添加响应事件--单个删除
	$(document).on("click",".delete_btn",function(){
		//是否确认删除
		//alert($(this).parents("tr").find("td:eq(1)").text());
		var userId = $(this).parents("tr").find("td:eq(1)").text();
		var userName = $(this).parents("tr").find("td:eq(2)").text();
		if(confirm("确认删除【"+userName+"】吗？")){
			//确认 发送ajax删除请求
			$.ajax({
				url:"../userController/showManageUser/"+userId,
				type:"DELETE",
				success:function(result){
					alert(result.msg);
					window.location.href="../userController/showManageUser?pn=${pageInfo.pageNum}";
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
	$("#user_delete_all_modal_btn").click(function(){
		
		var userNames = "";
		var del_idstr = "";
		$.each($(".check_item:checked"),function(){
			
			//alert($(this).parents("tr").find("td:eq(2)").text());
			userNames += $(this).parents("tr").find("td:eq(2)").text()+","; 
			//组装用户id字符串
			del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
			
		});
		//去除多余符号-，
		userNames = userNames.substring(0,userNames.length-1);
		del_idstr = del_idstr.substring(0,del_idstr.length-1);
		
		if(confirm("确认删除【"+userNames+"】吗？")){
			//发送删除ajax请求
			$.ajax({
				url:"../userController/showManageUser/"+del_idstr,
				type:"DELETE",
				success:function(result){
					alert(result.msg);
					window.location.href="../userController/showManageUser?pn=${pageInfo.pageNum}";
					
				}
			});
		}
	});
	
	//响应查询按钮
	$("#user_select_btn").click(function(){
		
		//1、获取文本框内容
		var likeUserName = $("#user_select_text").val();
		//2、校验文本框内容
		if(likeUserName==null||likeUserName==""){
			alert("请输入用户名！");
			return false;
			}
		
		//3、发送ajax请求 模糊查询
		$.ajax({
			url:"../userController/showLikeUser",
			async:false,
			data:"userName="+likeUserName,
			type:"POST",
			success:function(result){
				console.log(result);
				//alert(result);
				//window.location.href = "../userController/showLikeUser";
				//传页面必须要有一个username参数 直接调用改参数为null所以返回的是空的
				//1、解析并显示用户数据
				refresh_user_table(result);
				//2、解析并显示分页信息
				refresh_page_info(result);
				//3、加息显示分页条
				refresh_page_nav(result);
				
			}
		});
	});
	
	function refresh_user_table(result){
		var user = result.extend.pageInfo.list;
		$("#user_table td").detach();
		$.each(user,function(index,item){
			//<td><input type="checkbox" class="check_item"/></td>
			var checkboxTd = $("<td></td>").append($("<input type=\"checkbox\"/>").addClass("check_item"));
			var useridTd = $("<td></td>").append(item.userid);
			var passwordTd = $("<td></td>").append(item.password);
			var usernameTd = $("<td></td>").append(item.username);
			var emailTd = $("<td></td>").append(item.email);
			var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
			var delBtn = $("<button></button>").addClass("btn btn-danger delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
			
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(checkboxTd)
			.append(useridTd)
			.append(usernameTd)
			.append(passwordTd)
			.append(emailTd)
			.append(btnTd)
			.appendTo("#user_table tbody");
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
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
		if(result.extend.pageInfo.hasPreviousPage==false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(result.extend.pageInfo.hasNextPage==false){
			lastPageLi.addClass("disabled");
			nextPageLi.addClass("disabled");
		}
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
		var likeUserName = $("#user_select_text").val();
		$.ajax({
			url:"../userController/showLikeUser",
			async:false,
			data:{
				"userName":likeUserName,
				"pn":pn
				},
			type:"POST",
			success:function(result){
				console.log(result);
				//alert(result);
				//window.location.href = "../userController/showLikeUser";
				//传页面必须要有一个username参数 直接调用改参数为null所以返回的是空的
				//1、解析并显示用户数据
				refresh_user_table(result);
				//2、解析并显示分页信息
				refresh_page_info(result);
				//3、加息显示分页条
				refresh_page_nav(result);
				
			}
		});
	}

</script>
</html>