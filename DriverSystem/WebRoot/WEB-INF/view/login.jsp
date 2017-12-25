<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >

<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>驾驶员培训管理系统登录页</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script src="js/login.js"></script>

<script>
	function clearMsg(){
		$("#keylook").css("display","none");
	}
	$(function(){
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		//验证码
		createCode();
	});
	$(function(){
		var errorSource = "<%=request.getAttribute("errorSource")%>";
		if(errorSource != null && errorSource != "" && (errorSource == true || errorSource == "true")){
			window.location.href = "login";
		}
		var msg = "<%=request.getAttribute("msg")%>";
		if(msg != null && msg != "" && msg != "null"){
			//alert(msg);
			$("#keylook").html(msg);
			$("#keylook").css("display","block");
		}
		
	});
	function customerLogin(){
		window.location.href = "";
	}
	function changePlace(tag){
		var type = $(tag).val();
		if(type == '4'){
			$("#user_account").attr('placeholder', '请输入学员身份证号');
		}else{
			$("#user_account").attr('placeholder', '请输入账号');
		}
	}
</script>

</head>

<body>
	<div>
		<form id="testform" action="loginAction!login" method="post" onsubmit="return login();">
			<dl class="admin_login">
				<dt>
					<strong>登 录</strong> <em>login</em>
				</dt>
					<dd class="kind_icon">
						<select class="login_txtbx" style="width:300px; height:50px"
							name="loginType" onclick="changePlace(this)">
							<option value="1">运营平台登录</option>
							<option value="2">驾校平台登录</option>
							<option value="4">学员平台登录</option>
						</select>
		
					</dd>
					<dd class="user_icon">
						<input type="text" class="login_txtbx" name="user_account"
							id="user_account" placeholder="请输入账号" required
							oninvalid="setCustomValidity('账号格式错误（账号只能为数字和字母的组合）');" pattern="^[a-zA-Z0-9]+"
							oninput="setCustomValidity('');" />
					</dd>
					<dd class="pwd_icon">
						<div>
							<input type="password" class="login_txtbx" name="user_password"
								id="user_password" onkeypress="return Lock(event);"
								placeholder="请输入密码" required pattern="^[a-zA-Z0-9]+"
								oninvalid="setCustomValidity('密码格式错误（密码只能为数字和字母的组合）');"
								oninput="setCustomValidity('');" />
						</div>
					</dd>
					<dd class="val_icon">
						<div class="checkcode">
							<input type="text" id="J_codetext" placeholder="验证码" maxlength="4"  required pattern="^[a-zA-Z0-9]+"
								oninvalid="setCustomValidity('验证码格式错误（验证码只能为数字和字母的组合）');"
								oninput="setCustomValidity('');"
								class="login_txtbx">
							<canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
						</div>
						<input type="button" value="验证码核验" class="ver_btn"
							onClick="validate()" id="ver_btn">
					</dd>
					<dd>
						<input type="submit" value="立即登陆" class="submit_btn" id="loginBtn" />
					</dd>
					<dd>
						<input type="button" value="回到主页" class="submit_btn" id="loginBtn" onclick="customerLogin()"/>
					</dd>
					<dd>
						<input type="reset" value="重    置" class="submit_btn" onclick="reset()"/>
					</dd>
				<dd style="text-align: center">
					<p id="pmistake"></p>
					<span style="display:none;font-size: 14px;color:red" id="keylook" >大写锁定键被按下，请注意大小写</span>
					<!--  <div id="capital" style="display:none;">大写锁定已开启</div> -->
					<p>© 2017年8月 传一科技 版权所有</p>
				</dd>
			</dl>
		</form>
	</div>
</body>
</html>