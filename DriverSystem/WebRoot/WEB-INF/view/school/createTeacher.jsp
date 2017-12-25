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
	<script src="js/createStudentCheck.js"></script>
	<script type="text/javascript" src="js/jsAddress.js"></script>
	<script type="text/javascript">
	
	
	
	</script>
	
  </head>
  <style>
	a{ text-decoration:none; }
</style>
  <body>
  
  
  
  <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>创建用户
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
				<form id="searchform" action="driveSchool/TeacherAction!createTeacher" method="post">
				
				<table class=" table table-hover">
				
				
				<tr>
				<td>
  			身份证号：<input id="student_identification" name="teacher_identification" class="" type="text" />
				</td>
				<td>
				姓名：<input id="student_name" name="teacher_name"  type="text" />
				</td>
				
				</tr>
				<tr>
				<td>
				密码：<input id="student_password"  name="teacher_password"  type="text" />
				</td>
				<td>
				性别：
			 <select   name="teacher_sex">
<option id=" "  value="男">男</option>
<option id=" "  value="女">女</option>
</select>
				</td>
				
				</tr>
				<tr>
				<td>
				电话：<input id="student_phone" name="teacherstudent_phone"  type="text" />
				</td>
				<td>省份：<select id="cmbProvince" name="teacher_province" ></select></td>
				
				</tr>
				<tr>
				 <td>市区：<select id="cmbCity" name="teacher_city" ></select>  </td>
				   <select id="cmbArea" name="cmbArea" style="display:none"></select>  
     <script type="text/javascript">addressInit('cmbProvince', 'cmbCity', 'cmbArea')</script>
				<td>住址：<input id="teacher_address" name="teacher_address"  type="text" /></td>
				</tr>
				
				</table>
				<button type="submit" >确认创建</button>
				
				</form>
				</div>
				</div>
				</div>
				
				
  
  
  
  
  
  
  
  
  
  
  
  

<div>


				
</div>
  </body>
</html>
