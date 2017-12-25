<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'examPlanUpdate.jsp' starting page</title>
    
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
    
    <form action="driveSchool/schoolCertified!confirm" method="post">
    <div id="div2" align="center">
    <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>考试安排信息修改
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
				<table >
    <span>考试项目：</span>  
    <select name="select1">
    <option><s:property value="examPlan.course.course_name"/></option>
    <s:iterator value="courses" var="cou">
    <option><s:property value="#cou.course_name"/></option>
    </s:iterator>
    </select>
    <br>
    
    <tr><td>考试日期：<input type="text" value="<s:property value="examPlan.exam_plan_date"/>" name="date"/></td></tr>
    <tr><td>考试时间：<input type="text" value="<s:property value="examPlan.exam_plan_time"/>" name="time"/></td></tr>
    <tr><td>地点：<input type="text" name="address" value="<s:property value="examPlan.exam_plan_address"/>"/></td></tr>
    
    <span>负责教练：</span>  
    <select name="select2">
    <option><s:property value="examPlan.teacher.teacher_name"/></option>
    <s:iterator value="teachers" var="tea">
    <option><s:property value="#tea.teacher_name"/></option>
   </s:iterator>
    </select>
   </table>
    </div>
  
  </div>
  </div>
  </div>
   <input type="hidden" name="oldepi" value="<s:property value="examPlan.exam_plan_id"/>"/>
   
  <div align="center"> <input type="submit" value="确定修改" class="btn btn-primary"/> </div>
    
    </form>
    
  </body>
</html>
