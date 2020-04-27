function logout() {
	$.ajax({
		type: "POST",
		url: "logout.action",
		data: {
		},
		success: function(msg){
			if(msg.info=="注销成功"){
				layer.msg(msg.info);
				setTimeout(function(){
					//此处不能使用window.location.href,
					//因为window.location.href是页内跳转，在frameset框架下会出错
					window.top.location.href="../adminController/showAdminLogin"; 
				},1500);
			}
		},
		error: function () {
			layer.msg("注销失败");
		} 
	});
}