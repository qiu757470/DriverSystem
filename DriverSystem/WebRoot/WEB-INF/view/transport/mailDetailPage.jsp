<%@ page language="java" import="java.util.*,org.great.bean.Mail" pageEncoding="UTF-8"%>
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
	<style type="text/css">
		body,html{
			font-family: "微软雅黑";
		}
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
		#mailTitleDiv{
			margin-top:0px;
			margin-left: 20px;
			background-color: #02F78E;
			opacity: 0.8;
			padding-left: 20px;
			padding-right: 20px;
			padding-top: 10px;
			border-radius:10px;
			padding-bottom: 10px;
			width: 90%;
		}
		#mailContentDiv{
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
			color: white;
		}
		.titleDiv{
			margin-bottom: 5px;
		}
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		function back(){
			window.location.href = "transport/mailAction!checkMailPage?nowPage=1";
		}
		function reply(){
			var id = "<%=((Mail)request.getAttribute("detailMail")).getMail_id()%>";
			window.location.href = "transport/mailAction!replyMailPage?mailId=" + id;
		}
		
	</script>
  </head>
  
  <body>
   		<div id="mailTitleDiv">
   			<div class="titleDiv"><label class="titleLabel">主题：</label><label class="theme"><s:property value="#request.detailMail.mail_theme"/></label></div>
   			<div class="titleDiv"><label class="titleLabel">发送时间：</label><label class="time"><s:property value="#request.detailMail.mail_time"/></label></div>
   			<div class="titleDiv"><label class="titleLabel">发送人：</label><label class="info"><s:property value="#request.detailMail.student.student_name"/></label></div>
   			<div class="titleDiv">
   				<label class="titleLabel">所属驾校：</label>
   				<label class="info"><s:property value="#request.detailMail.student.school.school_name"/></label>
   				&emsp;<input class="btn" type="button" value="回复" onclick="reply()"/>
   				&emsp;<input class="btn" type="button" value="返回" onclick="back()"/>
   			</div>
   		</div>
   		<div id="mailContentDiv">
   				${requestScope.detailMail.mail_content}
   		</div>
  </body>
</html>
