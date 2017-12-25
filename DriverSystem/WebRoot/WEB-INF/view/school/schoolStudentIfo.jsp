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
						class="break"></span>学员信息
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
    				<td>用户名 :</td><td ><s:property value="student.student_name"/></td>
    				<td>身份证:</td><td ><s:property value="student.student_identification"/></td>
    			</tr>
    			<tr>
    				<td>性别  :</td><td ><s:property value="student.student_sex"/></td>
    				<td>学校:</td><td ><s:property value="school"/></td>
    			</tr>
    			<tr>
    				<td>省份:</td><td ><s:property value="student.student_province"/></td>
    				<td>市区:</td><td ><s:property value="student.student_city"/></td>
    			</tr>
    			<tr>
    				<td>地址:</td><td ><s:property value="student.student_address"/></td>
    				<td>电话:</td><td ><s:property value="student.student_phone"/></td>
    			</tr>
    			<tr>
    				<td>科目进度:</td><td ><s:property value="student.course_name"/></td>
    				<td>负责教练:</td><td ><s:property value="teacher.teacher_name"/></td>
    			</tr>
    			
    		</table>
    		</div>
    		</div>
    		
    		<form action="driveSchool/DriveSchoolUserAction">
    		
					<button type="submit" class="btn btn-primary">返回</button>
    		</form>
						
		</div>				
		

  </body>
</html>
