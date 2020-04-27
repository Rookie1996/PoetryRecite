
// 点击验证码图片换验证码
function refreshCheckCodeButton(){
//	$(refreshCheckCode).find("img").attr("src", "checkCode.action?"+Math.floor(Math.random()*999+1));
	$(refreshCheckCode).find("img").attr("src", "checkCode.action?" + new Date());
}
// 登录

//数据从前台以json格式发给controller层login.action处理
function login() 
{               
	var adminame = $("#adminame").val();
	var password = $("#password").val();
	var checkCode = $("#checkCode").val();
	var rememberMe = $("#rememberMe").is(':checked');
	$.ajax({
		type: "POST",
		url: "login.action",
		dataType:"json",  
		data: {
			"adminame":adminame,
			"password":password,
			"checkCode":checkCode,
			"rememberMe":rememberMe
		},
		success: function(msg){
			if(msg.info=="登录成功"){
				window.location.href="../frameController/showframe";
			}else{
				layer.msg(msg.info);
			}
		},
		error: function () {
			alert("登录失败!");
		} 
	});
}

// 按 enter 键提交
$(document).keyup(function(event) {
	var code = event.keyCode;
	if (code == 13) {
		login();
	}
})