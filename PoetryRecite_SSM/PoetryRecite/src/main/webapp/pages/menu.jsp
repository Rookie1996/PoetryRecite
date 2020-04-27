<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link href="../res/css/frame.css" rel="stylesheet">
<!-- 可折叠菜单的引入 -->
<link rel=stylesheet type=text/css href="../res/foldingMenu/css/lrtk.css">
<script type=text/javascript src="../res/foldingMenu/js/jquery.min.js"></script>
</head>

<body>
	<div id="firstpane" class="menu_list" style="overflow-x: hidden;">
		<p class="menu_head current">统计</p>
		<div style="display:block" class=menu_body >
			<a href="#" onclick="reloadMainContent('../gradeController/showUserAnswerView')">试题评分</a>
			<a href="#" onclick="reloadMainContent('../everyUserController/showEveryUser')">答题情况统计</a>
		</div>
		
		<p class="menu_head">管理</p>
		<div style="display:none" class=menu_body >
			<a href="#" onclick="reloadMainContent('../userController/showManageUser')">用户管理</a>
			<a href="#" onclick="reloadMainContent('../testController/showManageTest')">试题管理</a>
			<a href="#" onclick="reloadMainContent('../poetryController/showManagePoetry')">诗歌管理</a>
		</div>
		
		<p class="menu_head">关于</p>
		<div style="display:none" class=menu_body >
			<a href="#" onclick="reloadMainContent('../aboutController/showAbout')">关于</a>
		</div>
	</div>
</body>

<script type=text/javascript>

function reloadMainContent(src){
	var ele = parent.document.getElementById("main_content");
	ele.setAttribute("src",src);
}

/* 可折叠菜单自带的js方法 */
$(document).ready(function(){
	$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>	
</html>