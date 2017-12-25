<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
	
	<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
	
	
	
	</script>
	
  </head>
  
  <body>
  
  <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>教练信息
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

				<table class=" table table-hover">
    			<tr>
    				<td>姓名 :</td><td ><s:property value="teacher.teacher_name"/></td>
    				<td>身份证:</td><td ><s:property value="teacher.teacher_identification"/></td>
    			</tr>
    			<tr>
    				<td>性别  :</td><td ><s:property value="teacher.teacher_sex"/></td>
    			
    				<td>电话:</td><td ><s:property value="teacher.teacher_phone"/></td>
    			</tr>
    			<tr>
    				<td>省份:</td><td ><s:property value="teacher.teacher_province"/>省</td>
    				<td>市区:</td><td ><s:property value="teacher.teacher_city"/></td>
    			</tr>
    			<tr>
    				<td>详细地址:</td><td ><s:property value="teacher.teacher_address"/></td>
    				<td>负责科目:</td><td ><s:property value="teacher.course_name"/></td>
    			</tr>
    			<tr>
    				<td>评分:</td><td ><s:property value="teacher.teacher_eval_score"/></td>
    				<td>状态:</td><td ><s:property value="teacher.teacher_state"/></td>
    			</tr>
    			
    		</table>
			</div>
		</div>
	</div>
 
 
  
 <div >

    		
    		<p>负责学员:
    		<s:iterator value="studentlist" var="u" >
    		<a href="driveSchool/allUserInf?student_id=<s:property value="#u.student_id"/>"><s:property value="#u.student_name"/></a>
    		
    		</s:iterator>
    		
    			</p>
    		
    	
    		
    		
    		
    		
    		
    		
    		
    		
    		<form action="driveSchool/driveSchoolTeacher">
    		
					<button type="submit" class="btn btn-primary">返回</button>
    		</form>
						
		</div>				
		
		
		
			
		

  </body>
</html>
