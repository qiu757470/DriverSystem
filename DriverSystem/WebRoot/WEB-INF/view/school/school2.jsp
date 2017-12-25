<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'transport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  <div id="top_div" align="center" style="background-color: gray;width: 100%;height: 10%">
  <h3> 厦门市驾校运管平台欢迎你--${sessionScope.schoolmanager}</h3>
   </div>
   
   <div id="left_div" style="float:left;width: 20% ;height:90%;">
   <iframe name="frame1" width="100%" height="100%" src="menuAction2!menu2"></iframe>
   </div>
   
   <div id="div_right" style="float:left;width: 80%;height: 90%;">
   <iframe name="frame2" width="100%" height="100%" ></iframe>
   </div>
   
    
  </body>
</html>
