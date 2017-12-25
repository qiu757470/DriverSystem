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
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
    <title>运营平台回复邮件界面</title>
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link href="css/froala_editor.min.css" rel="stylesheet" type="text/css">



	<style type="text/css">
        section {
            width: 80%;
            margin: auto;
            text-align: left;
        }
		#replyDiv{
			margin-left: 50px;
			width: 80%;
		}
		.titleDiv{
			border-bottom: black 1px solid;
			height: 30px;
			margin-bottom: 5px;
			margin-top: 5px;
		}
		.contentDiv{
			height: 50%;
			margin-bottom: 5px;
			margin-top: 5px;
		}
		#title{
			margin-left: 10px;
		}
		.subTitle{
			font-weight: bold;
		}
		textarea{
			height: 250px;
			width: 100%;
			overflow-y: auto;
			border-radius: 5px;
		}
		#edit .froala-element{
			height: 200px;
			overflow-y: auto;
		}
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			var sendSuccess = "<%=request.getAttribute("sendSuccess")%>";
			if(sendSuccess != null && sendSuccess != "" && (sendSuccess == "true" || sendSuccess == true)){
				alert("发送成功！");
				cancel();
			}
		
		});
		function cancel(){
			var id = "<%=((Mail)request.getAttribute("detailMail")).getMail_id()%>";
			window.location.href = "transport/mailAction!mailDetail?mailId=" + id;
		}
		function send(){
			var id = "<%=((Mail)request.getAttribute("detailMail")).getMail_id()%>";
			var url = "transport/mailAction!replyMail?mailId=" + id;
			var content = $("#edit .froala-element").eq(0)[0].innerHTML; 
			$("#edit .froala-element").eq(0)[0].innerHTML = '111110';
			if(content == null || content == ""){
				alert("请先输入内容！");
				return false;
			}
			$("#contentLabel").val(content);
			$("#replyForm").attr('action', url);
			return true;
		}
	</script>
  </head>
  
  <body>
  	<h2 id="title">回复邮件</h2>
    <div id="replyDiv">
    	<div class="titleDiv"><label class="subTitle">收件人：</label><label><s:property value="#request.detailMail.student.student_name"/></label></div>
    	<div class="titleDiv"><label class="subTitle">所属驾校：</label><label><s:property value="#request.detailMail.student.school.school_name"/></label></div>
    	<div class="titleDiv"><label class="subTitle">主题：</label><label><s:property value="#request.detailMail.mail_theme"/></label></div>
    	<div class="contentDiv" style="height: 300px;">
    		<form id="replyForm"  action="transport/mailAction!replyMail?mailId=" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return send()">
    		<div style="float:left"><label class="subTitle">回复：</label></div>
    		<section id="editor">
		      <div id='edit' style="margin-top: 20px;width: 110%;">
		      <label id="tipInfo"> </label>
		      <input name="replyContent" id="contentLabel" type="hidden"/>
		      </div>
		  </section>
		
		 <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
	  <script src="js/froala_editor.min.js"></script>
	  <!--[if lt IE 9]>
	    <script src="../js/froala_editor_ie8.min.js"></script>
	  <![endif]-->
	  <script src="js/plugins/tables.min.js"></script>
	  <script src="js/plugins/lists.min.js"></script>
	  <script src="js/plugins/colors.min.js"></script>
	  <script src="js/plugins/media_manager.min.js"></script>
	  <script src="js/plugins/font_family.min.js"></script>
	  <script src="js/plugins/font_size.min.js"></script>
	  <script src="js/plugins/block_styles.min.js"></script>
	  <script src="js/plugins/video.min.js"></script>


		
		 <script>
		      $(function(){
		          $('#edit').editable({inlineMode: false, theme: 'dark'})
		      });
		  </script>
    		&emsp;<input class="btn" type="submit" value="发送" style="margin-top: 10px" />&emsp;<input class="btn" type="button" value="取消" onclick="cancel()" style="margin-top: 10px"/>
    		</form>
    	</div>
    </div>
  </body>
</html>
