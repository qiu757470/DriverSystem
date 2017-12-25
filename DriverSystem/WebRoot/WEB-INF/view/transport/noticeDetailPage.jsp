
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>运营信息邮件详情界面</title>
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.theme{
			font-size: 20px;
		}
		.time{
			font-size: 18px;
		}
		.info{
			font-size: 18px;
		}
		.content{
			font-size: 16px;
		}
		#noticeTitleDiv{
			margin-top:0px;
			margin-left: 20px;
			background-color: #2894FF;
			opacity: 0.8;
			padding-left: 20px;
			padding-right: 20px;
			padding-top: 10px;
			border-radius:10px;
			padding-bottom: 10px;
			width: 90%;
		}
		#noticeContentDiv{
			margin-top:20px;
			margin-left: 20px;
			width: 90%;
			border-radius:10px;
			padding-left: 20px;
			padding-right: 20px;
			height:40%;
			padding-top: 10px;
			padding-bottom: 10px;
			background-color: #E0E0E0;
			opacity: 0.9;
			overflow-y: auto;
		}
		.titleLabel{
			font-size:22px;
			font-weight: bold;
			color:white;
		}
		.titleDiv{
			margin-bottom: 5px;
		}
		table{
			border: 2px black solid;
		}
		table td{
			border: 1px black solid;
		}
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		function back(){
			window.location.href = "transport/noticeView";
		}
	</script>
  </head>
  
  <body>

   		<div id="noticeTitleDiv">
   			<div class="titleDiv"><label class="titleLabel">公告主题：</label><label class="theme"><s:property value="oneNotice.trans_notice_theme"/></label></div>
   			<div class="titleDiv"><label class="titleLabel">发送时间：</label><label class="time"><s:property value="oneNotice.trans_notice_time"/></label></div>
   			<div class="titleDiv"><label class="titleLabel">发送人：</label><label class="info"><s:property value="oneNotice.trans_user_name"/></label></div>
   			<div class="titleDiv">
   				<label class="titleLabel" >发送省份：</label><label class="info"><s:property value="oneNotice.trans_notice_province"/></label>
   				<label class="titleLabel" style="margin-left: 10%">发送城市：</label><label class="info"><s:property value="oneNotice.trans_notice_city"/></label>
   				<label class="titleLabel" style="margin-left: 10%">发送目标：</label><label class="info"><s:property value="oneNotice.trans_notice_target"/></label>
   				<input class="btn" type="button" value="返回" onclick="back()" style="margin-left: 5%"/>
   			</div>
   			
   		</div>
   		<div id="noticeContentDiv">
   			<p class="content">
   				${requestScope.oneNotice.trans_notice_content}
   			</p>
   		</div>
  </body>
</html>
