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
    
    <title>My JSP 'certifiedPass.jsp' starting page</title>
    
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
    <script type="text/javascript">
    
   function download(){
    document.getElementById("link").click();
   }
    
    
    </script>
  </head>
  
  <body>
    <div id="all" align="center">
    <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>审核状态：通过
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
    <img src="<%=basePath%><s:property value='school.school_picture_url'/>" width="50"
						height="50" />
    <tr>
    <td>组织代码：<s:property value='school.school_code'/></td>
    <td>准经营许可账号：<s:property value='school.school_certificate'/></td>
    </tr>
    
    <tr>
    <td>驾校名称：<s:property value='school.school_name'/></td>
    <td>所在地区：<s:property value='school.school_province'/><s:property value='school.school_city'/>
    </td>
    </tr>
   
    <tr>
    <td>法人代表：<s:property value='school.school_representative_name'/></td>
    <td>法人身份证号：<s:property value='school.school_representative_id'/></td>
    </tr>
  
    <tr>
    <td>驾校地址：<s:property value='school.school_address'/></td>
    <td>联系电话：<s:property value='school.school_phone'/></td>
  
    </tr>
   
    
    </table>
    </div>
    </div>
    </div>
   
  <tr><td>  <a href="<%=basePath%><s:property value='school.school_file_url'/>" download="" id="link"><s:property value='school.school_name'/>认证相关文件</a></td></tr> 
   <tr><td> <input type="button" value="下载" id="download" onclick="download()" class="btn btn-primary"/></td></tr>
  <br>
    </div>
  </body>
</html>
