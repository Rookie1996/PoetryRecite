// 注册
function register() {
	if(!validator()){
		return;
	}
	
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	
	// loading
	var index = layer.load(1, {
	  shade: [0.8,'#fff'] //0.1透明度的白色背景
	});
	
	$.ajax({
		type: "POST",
		url: "register.action",
		data: {
			"username":username,
			"email":email,
			"password":password,
		},
		success: function(msg){
			if(msg.info=="注册成功！"){
				layer.msg(msg.info);
				setTimeout(function(){
					window.location.href="../adminController/showAdminLogin.action";
				},2000);
			}
			if(msg.info=="邮箱已经注册，请登录"){
				alert(msg.info);
				window.location.href="../adminController/showAdminLogin.action";
			}
			if(msg.info=="用户名被占用，请修改用户名"){
				alert(msg.info);
				window.location.reload();
			}
		},
		error: function () {
			alert("注册失败");
		}
	});
}

// 按 enter 键提交
$(document).keyup(function(event) {
	var code = event.keyCode;
	if (code == 13) {
		register();
	}
})


function validator() {
	var userName = document.getElementById("username").value;
	if(!validateAccount(userName)){
		return false;
	}
	
	var email = document.getElementById("email").value;
	if(!validateEmail(email)){
		return false;
	}
	
	var password = document.getElementById("password").value;
	if(!validatePassword(password)){
		return false;
	}
	var repassword = document.getElementById("repassword").value;
	if(!validatePassword(repassword)){
		return false;
	}
	if (password != repassword) {
		alert("两次输入的密码不一致");
		return false;
	}
	
	return true;
}