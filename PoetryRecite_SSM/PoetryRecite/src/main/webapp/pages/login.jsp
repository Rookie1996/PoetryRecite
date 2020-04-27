<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="../res/css/style.css">
    <script src="../res/js/admin.login.js"></script>
    <script src="../res/layer/layer.js"></script>
</head>
<body>
<div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">管理员登录</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="adminame" name="adminame" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                            <i class="fa fa-lock fa-lg"></i>
                            <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="20"/>
                    </div>
                    <div class="form-group">
						<label class="label">验证码：</label>
							<input class="form-control required" type="text" placeholder="请输入验证码" id="checkCode" maxlength="4"/>
							<a onclick="refreshCheckCodeButton()" href="#" id="refreshCheckCode"><img src="checkCode.action"></a>
					</div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <!-- <button type="submit" class="btn btn-success pull-right" name="submit">登录</button> -->
                        <button class="btn" name="submit" onclick="login()">登录</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="showAdminRegister.action" class="link">注册</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>