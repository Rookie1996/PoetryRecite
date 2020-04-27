<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计</title>
<script type="text/javascript" src="../res/echarts/echarts.min.js"></script>
<script type="text/javascript" src="../static/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript"
	src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<!--搭建页面  -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>答题情况统计</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">

			<!--用户名精确查询  -->
			<div class="col-md-4 col-md-offset-4">
				<label>用户名</label> <input type="text" id="user_select_text"
					placeholder="用户名:wangdog" />
				<button class="btn btn-primary btn-sm" id="user_select_btn">查询</button>
			</div>

		</div>

		<!--分割线  -->
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="80%" color=#987cb9 SIZE=3>

		<!-- 显示图表信息 -->
		<div class="row">
			<div class="col-md-12">
				<div id="chartmain1"
					style="width: 500px; height: 400px; float: left"></div>
				<div id="chartmain2"
					style="width: 500px; height: 400px; float: right"></div>
			</div>
		</div>


	</div>

	<!--显示pieChart  -->
	<script type="text/javascript">
		var dom = document.getElementById("chartmain1");
		var pieChart = echarts.init(dom);
		//var app = {};
		option = null;
		option = {
			title : {
				text : '用户答题分数段分布',
				subtext : '来源poetryapp数据库',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			}
		};

		$.ajax({
			type : "GET",
			async : true,
			url : "../everyUserController/showEverySumGrade",
			success : function(result) {

				//输出ajax返回值
				console.log(result);

				pieChart.hideLoading(); //隐藏加载动画
				pieChart.setOption({ //加载数据图表
					legend : {
						orient : 'vertical',
						left : 'left',
						data : [ '90-100', '80-89', '70-79', '60-69', '<60' ]
					},
					series : [ {
						name : '分数段分布',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : result.extend.GradesRange,
						itemStyle : {
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(0, 0, 0, 0.5)'
							}
						}
					} ]
				});
			},
			error : function(errorMsg) {

			}

		});

		//将title和数据内容组合重新显示
		if (option && typeof option === "object") {
			pieChart.setOption(option, true);
		}
	</script>

	<!--显示barChart  -->
	<script type="text/javascript">
		//响应用户名查询按钮
		$("#user_select_btn").click(function() {

					//1、首先清除上一次显示图表

					//2、显示图表
					var dom = document.getElementById("chartmain2");
					var barChart = echarts.init(dom);
					var username = $("#user_select_text").val();
					//console.log("前端取得文本框用户名：" + username);
					var testid = new Array();
					
					optionBar = null;
					optionBar = {
						title : {
							text : '每题得分情况',
							subtext : '来源poetryapp数据库',
							x : 'center'
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '题目得分' ]
						},
						toolbox : {
							show : true,
							feature : {
								mark : {
									show : true
								},
								dataView : {
									show : true,
									readOnly : false
								},
								magicType : {
									show : true,
									type : [ 'line', 'bar' ]
								},
								restore : {
									show : true
								},
								saveAsImage : {
									show : true
								}
							}
						},
						calculable : true
					};

					//向后台发送ajax请求，获得username的所有答题记录，
					//controller处理，返回值为testid试题号和grade得分的json串   的一个list
					$.ajax({
						type : "GET",
						async : true,
						url : "../everyUserController/showUserAnswer/"
								+ username,
						success : function(result) {

							//输出ajax返回值
							console.log(result);
							$.each(result.extend.List,function(index,item){
								testid.push(item.name);
							});

							barChart.hideLoading(); //隐藏加载动画
							barChart.setOption({ //加载数据图表
								xAxis : [ {
									name :'testid',
									type : 'category',
									data : testid
								} ],
								yAxis : [ {
									name : 'grade',
									type : 'value'
								} ],
								series : [
										{
											name : 'grade',
											type : 'bar',
											data : result.extend.List,
											markPoint : {
												data : [ {
													type : 'max',
													name : '最大值'
												}, {
													type : 'min',
													name : '最小值'
												} ]
											},
											markLine : {
												data : [ {
													type : 'average',
													name : '平均值'
												} ]
											}
										}, ]
							});

						}
					});

					barChart.setOption(optionBar);
				
				});
	</script>
	
	<!--2019/5/28加一个热词图表  -->
	
	
</body>
</html>