<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'schoolManagerInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext 
'rel='stylesheet' type='text/css'>
<!-- <link href="css/OSTA.css" rel="stylesheet" type="text/css" /> -->
<script src="js/jquery.js"></script>
  </head>
  
  <body>
   <div id="div2" align="center">
   <form action="driveSchool/schoolCertified!SchoolManagerInfoUpdate" method="post">
   <input type="hidden" name="manager_id" value="<s:property value="schoolManager.school_manager_id"/>"/>
   <input type="hidden" name="manager_school_id"/>
     <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>个人信息
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-setting"><i
						class="halflings-icon white wrench"></i></a> <a href="#"
						class="btn-minimize"><i
						class="halflings-icon white chevron-up"></i></a> <a href="#"
						class="btn-close"><i class="halflings-icon white remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class=" table table-hover" style="text-align: center;">
 <tr><td align="center" valign="middle"> 名字：<s:property value="schoolManager.school_manager_name"/> </td></tr>
  <tr> <td align="center"> 身份证：<s:property value="schoolManager.school_manager_identification"/></td></tr>
  <tr> <td align="center"> 性别：<s:property value="schoolManager.school_manager_sex"/></td></tr>
  <tr> <td align="center"> 省份：<s:property value="schoolManager.school_manager_province"/></td></tr>
  <tr> <td align="center"> 城市：<s:property value="schoolManager.school_manager_city"/></td></tr>
  <tr> <td align="center"> 地址：<s:property value="schoolManager.school_manager_address"/></td></tr>
  <tr> <td align="center"> 电话：<s:property value="schoolManager.school_manager_phone"/></td></tr>
   
   </table>
   </div>
   </div>
   </div>
    <input type="submit" value="修改个人信息" class="btn btn-primary"/>
   </form>
   
   </div>
 
   
   
   
   
   
   
   
   
  </body>
</html>
