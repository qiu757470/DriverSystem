<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script type="text/javascript">
    
    function exit(){
    var r = confirm("确定退出登录吗？")
	if (r==true) {
	$("#exit").attr("target","_parent");
	$("#exit").attr("href","driveSchool/schoolCertified!exit");
	return true;
	}else{
	return false;
	}
    
    }
    
    
    
    </script>
  </head>
  
  <body>
 <details open="open">
 <summary>系统管理</summary>
 <ul>
 <a href="certificed!certificted" target="frame2"><li class="li">驾校认证</li></a>
</ul>
</details>

 <details open="open">
 <summary>设置</summary>
 <ul>
 <a href="driveSchool/schoolCertified!SchoolManagerInfo" target="frame2"><li class="li">修改个人信息</li></a>
 <a href="driveSchool/schoolCertified!updatePWD" target="frame2"><li class="li">修改密码</li></a>
 <a href="#" target="frame2" id="exit" onclick="return exit()"><li class="li">退出登录</li></a>
</ul>
</details>

  </body>
</html>
