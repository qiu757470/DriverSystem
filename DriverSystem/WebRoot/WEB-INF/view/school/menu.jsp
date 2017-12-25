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
function check(obj){
     var ss=$('#menus'+obj).html();
     var aa=jQuery.trim(ss);
	if(aa=='驾校认证'){
	$("#menuname"+obj).attr("href","certificedPass!certifictedpass");
	}else if(aa=='考试安排'){
	$("#menuname"+obj).attr("href","driveSchool/schoolCertified!examplan");
	}else if(aa=='修改个人信息'){
	$("#menuname"+obj).attr("href","driveSchool/schoolCertified!SchoolManagerInfo");
	}else if(aa=='修改密码'){
	$("#menuname"+obj).attr("href","driveSchool/schoolCertified!updatePWD");
	}else if(aa=='关于'){
	$("#menuname"+obj).attr("href","driveSchool/schoolCertified!about");
	}else if(aa=='退出登录'){
	var r = confirm("确定退出登录吗？")
	if (r==true) {
	$("#menuname"+obj).attr("target","_parent");
	$("#menuname"+obj).attr("href","driveSchool/schoolCertified!exit");
	}
   }else if(aa=='学员管理'){
$("#menuname"+obj).attr("href","driveSchool/DriveSchoolUserAction");
}else if(aa=='教练管理'){
$("#menuname"+obj).attr("href","driveSchool/driveSchoolTeacher");
}




};


</script>
	
  </head>
  
  <body>
  <s:iterator value="managerJurOne" var="menus" status="cou">
 <details open="open">
 <summary><s:property value="#menus.manager_jur_one_name"/></summary>
 
 <s:iterator value="#menus.managerjurtwo" var="tmenus" status="ct">
 <ul>
 <a href="" target="frame2" id="menuname<s:property value='#cou.index+1'/><s:property value='#ct.index'/>" onclick="check(<s:property value='#cou.index+1'/><s:property value='#ct.index'/>)">
 <li id="menus<s:property value='#cou.index+1'/><s:property value='#ct.index'/>"><s:property value="#tmenus.manager_jur_two_name"/>
 </li></a>
</ul>
</s:iterator>
</details>
</s:iterator>
  </body>
</html>
