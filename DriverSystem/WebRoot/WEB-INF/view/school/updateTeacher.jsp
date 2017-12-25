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
</head>
<style>
a {
	text-decoration: none;
}
</style>
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
			<div syle="float:left">
				<form id="searchform" action="driveSchool/TeacherAction!updateTeacher" method="post">
				
				<table class=" table table-hover">
				<tr>
				<td>教练名：<input id="teacher_name" name="teacher_name" class=""
					type="text" value="${teacher.teacher_name}" /> 
				</td>
				<td> 身份证号：<input
					id="teacher_identification" name="teacher_identification"
					type="text" value="${teacher.teacher_identification}"  pattern="/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/" required/> </td>
				</tr>
				<tr>
				<td>	性别： <select name="teacher_sex">
				<option id=" ">${teacher.teacher_sex}</option>
				<option id=" ">男</option>
				<option id=" ">女</option>
			</select> </td>
				<td>负责科目： <select name="course_name">
				<option id=" ">${teacher.course_name}</option>
				<option id=" ">科目一</option>
				<option id=" ">科目二</option>
				<option id=" ">科目三</option>
				<option id=" ">科目四</option>
			</select> </td>
				</tr>
				<tr>
				<td>电话：
	<input id="teacher_phone" name="teacher_phone" class="" type="text"
		type="text" value="${teacher.teacher_phone}" pattern="^1[3-9]\d{9}$" required/> </td>
				<td>省份：
	<input id="teacher_province" name="teacher_province" type="text"
		value="${teacher.teacher_province}" /></td>
				</tr>
				<tr>
				<td>市区：
	<input id="teacher_city" name="teacher_city" type="text"
		value="${teacher.teacher_city}" /></td>
				<td> 地址：
	<input id="teacher_address" name="teacher_address" type="text"
		value="${teacher.teacher_address}" />
				</td>
				</tr>
			
				</table>
				
					<button type="submit">确认修改</button>
		
				
				</form>
				</div>
				<form action="driveSchool/driveSchoolTeacher">
					
					<button type="submit">取消</button>
					</form>
				
				</div>
				</div>


	<div>
	</div>






















</body>
</html>
