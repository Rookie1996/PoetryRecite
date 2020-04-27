<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link href="../res/css/about.css" rel="stylesheet">
<!-- <script type="text/javascript" src="../res/echarts/echarts-wordcloud.min.js"></script> 新版本-->
<script type="text/javascript" src="../res/echarts/echarts-all.js"></script>
<!-- <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script> 需联网-->
<script type="text/javascript" src="../static/js/jquery-3.2.0.min.js"></script>

</head>
<body>
	<div class="container-fluid padding-top15">
		<div class="row">
			<div class="col-xs-12">
				<ol class="breadcrumb navigation">
					<li class="navi-li"><span class="navi-text">关于 > 关于</span></li>
				</ol>
			</div>

			
			<!--系统介绍  -->
			<div class="col-xs-12">
				<div class="col-xs-12 label1">
					<table border="0" width="330px" align="center">
						<thead><h3>使用PoetryRecite后台管理，您的加入就是对我们的支持！</h3><br></thead>
						<tr height="34px">
							<td valign="top" width="120px">技术团队</td>
							<td valign="top" align="left" width="100px">负&nbsp;&nbsp;责&nbsp;&nbsp;人</td>
							<td valign="top" align="left" width="100px">许家瑞</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div><p></p></div>
			
			<!--wordCloud  -->
			<div class="col-xs-12" id="wordCloud" style="width: 500px; height: 400px; margin: 0 auto">
			</div>
			
			
		</div>
	</div>
	
	<script type="text/javascript">
	
	function createRandomItemStyle() {
	    return {
	        normal: {
	            color: 'rgb(' + [
	               	Math.round(Math.random() * 160),
	                Math.round(Math.random() * 160),
	                Math.round(Math.random() * 160)
	            ].join(',') + ')'
	        }
	    };
	}
	
	var dom = document.getElementById("wordCloud");
	var wordCloud = echarts.init(dom);
	option = null;
	option = {
	    title: {
	        text: '题库热词',
	        link: 'http://www.baidu.com',
	        subtext : '来源poetryapp数据库',
	        x : 'center'
	    },
	    tooltip: {
	        show: true
	    }
	 };
	  	
	  //向aboutController发送ajax请求，返回题库中诗歌标题热词。
	   $.ajax({
	    	type : "GET",
			async : true,
			url : "../aboutController/showHotWords",
			success : function(result) {

				//输出ajax返回值
				console.log(result);
				
				var data = [];
				//alert(result.extend.HotWords['静夜思']);//弹出undefined
				
				/* for(var name in result.extend.HotWords){
					data.push({
						name : name,
					    value : result.extend.HotWords[name],
						itemStyle: createRandomItemStyle()
					});
				} */
				
				$.each(result.extend.HotWords,function(index,item){
					data.push({
						name : item.name,
						value : item.value,
						itemStyle: createRandomItemStyle()
					});
				});
				
				wordCloud.hideLoading(); //隐藏加载动画
				wordCloud.setOption({ //加载数据图表
				 series: [{
				        name: 'Test Hot Words',
				        type: 'wordCloud',
				        size: ['80%', '80%'],
				        textRotation : [0, 45, 90, -45],
				        textPadding: 0,
				        autoSize: {
				            enable: true,
				            minSize: 14
				        },
				        data: data
				        //itemStyle : createRandomItemStyle()
				      /*   data: [
				            {
				                name: "李白",
				                value: 10000,
				                itemStyle: {
				                    normal: {
				                        color: 'black'
				                    }
				                }
				            },
				            {
				                name: "杜甫",
				                value: 6181,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "静夜思",
				                value: 4386,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "陶渊明",
				                value: 4055,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "锦瑟",
				                value: 2467,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "华年",
				                value: 2244,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "庄生",
				                value: 1898,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "追忆",
				                value: 1484,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "次北固山下",
				                value: 1112,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Home",
				                value: 965,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Johnny Depp",
				                value: 847,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Lena Dunham",
				                value: 582,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Lewis Hamilton",
				                value: 555,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "KXAN",
				                value: 550,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Mary Ellen Mark",
				                value: 462,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Farrah Abraham",
				                value: 366,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Rita Ora",
				                value: 360,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Serena Williams",
				                value: 282,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "NCAA baseball tournament",
				                value: 273,
				                itemStyle: createRandomItemStyle()
				            },
				            {
				                name: "Point Break",
				                value: 265,
				                itemStyle: createRandomItemStyle()
				            }
				        ]    */
				    }] //series
				});  //setOption
			}  //function
	    });  //ajax

	//将title和数据内容组合重新显示
	if (option && typeof option === "object") {
		wordCloud.setOption(option, true);
	}
	</script>
</body>
</html>