<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shoolMangerInfoUpdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jsAddress.js"></script>
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
<script type="text/javascript">
$(function(){
    $("#cmbProvince").val("<%=((SchoolManager)request.getAttribute("schoolManager")).getSchool_manager_province()%>");
      addressInit('cmbProvince', 'cmbCity', 'cmbArea');
    $("#cmbCity").val("<%=((SchoolManager)request.getAttribute("schoolManager")).getSchool_manager_city()%>");
     
	});
</script>
  </head>
  
  <body >
  <div align="center">
    <form action="driveSchool/schoolCertified!Update" method="post">
    
      <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>个人信息修改
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
				<table  >
    
   <tr><td> 姓名：<input type="text" name="manager_name"  value="<s:property value="schoolManager.school_manager_name"/>"/></td></tr>
   <tr><td> 身份证：<input type="text" name="manager_iden"  value="<s:property value="schoolManager.school_manager_identification"/>"/></td></tr>
    <tr><td>性别：<input type="text" name="manager_sex"   value="<s:property value="schoolManager.school_manager_sex"/>"/></td></tr>
   <tr> <td>省份：<select id="cmbProvince" name="cmbProvince"></select></tr>
   <tr><td> 市：<select id="cmbCity" name="cmbCity" ></select>  <select id="cmbArea" name="cmbArea" style="display:none;"></select></td></tr>
     <script type="text/javascript">addressInit('cmbProvince', 'cmbCity', 'cmbArea')</script>
   <tr> <td>地址：<input type="text" name="manager_address"  value="<s:property value="schoolManager.school_manager_address"/>"/></td></tr>
   <tr> <td>电话：<input type="text" name="manager_phone" value="<s:property value="schoolManager.school_manager_phone"/>"/></td></tr>
    
    
    
   </table>
   
   </div>
   </div>
   </div>
   
  <div align="center"><input type="submit" value="确定修改" class="btn btn-primary"/></div>
   
  <input type="hidden" name="manager_school_id" value="<s:property value="schoolManager.school_id"/>"/>
   <input type="hidden" name="manager_id" value="<s:property value="schoolManager.school_manager_id"/>"/>
   
    </form>
  </div>
  
  </body>
</html>
