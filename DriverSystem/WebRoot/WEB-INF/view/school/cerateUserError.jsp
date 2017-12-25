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
	
	<link  href="css/OSTA.css" rel="stylesheet" type="text/css"/>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
	
	
	
	</script>
	
  </head>
  
  <body>
  该身份证号所对应的用户已存在,请勿再次创建
  
 <div id="u_d">
<table>
    			<tr>
    				<td>用户名 :</td><td ><s:property value="student.student_name"/></td>
    				<td>身份证:</td><td ><s:property value="student.student_identification"/></td>
    			</tr>
    			<tr>
    				<td>性别  :</td><td ><s:property value="student.student_sex"/></td>
    				<td>学校:</td><td ><s:property value="student.student_sex"/></td>
    			</tr>
    			<tr>
    				<td>电话:</td><td ><s:property value="student.student_phone"/></td>
    				<td>地址:</td><td ><s:property value="student.student_address"/></td>
    			</tr>
    			<tr>
    				<td>科目进度:</td><td ><s:property value="student.student_schedule"/></td>
    				<td>负责教练:</td><td ><s:property value="teacher.teacher_name"/></td>
    			</tr>
    			
    		</table>
    		<form action="driveSchool/DriveSchoolUserAction">
    		
					<button type="submit">返回</button>
    		</form>
						
		</div>				
		

  </body>
</html>
