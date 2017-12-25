<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布公告界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link href="css/froala_editor.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsAddress.js"></script>
 <script type="text/javascript">
	$(function(){
		$("hr").animate({width:"50%"},"slow");
		$("h2").animate({opacity: "1"},"slow");
		var wrongLogin = "<%=request.getAttribute("wrongLogin")%>";
		if(wrongLogin != null && wrongLogin == "true"){
			parent.logout();
		}
		var sendSuccess = "<%=request.getAttribute("sendSuccess")%>";
		if(sendSuccess != null && sendSuccess == "true"){
			if(!confirm('发布成功！是否继续发布？')){
				cancel();
			}
		}else if(sendSuccess != null && sendSuccess == "false"){
			alert('发布失败！');
		}
		
	});
	function cancel(){
		window.location = "transport/noticeView";
	}
	function send(){
		var content = $("#edit .froala-element").eq(0)[0].innerHTML; 
			if(content == null || content == ""){
				alert("请先输入内容！");
				return false;
			}
		$("#contentLabel").val(content);
		if(confirm('确认发布吗？')){
			return true;
		}
		return false;
	}
</script>
	 <style>
	 	section {
            width: 80%;
            margin: auto;
            text-align: left;
        }
	    hr{
	    	width: 1px;
	    	float:left;
	    }
	    h2{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
		#sendNoticeDiv{
			margin-left: 50px;
			margin-top: 10px;
		}
		textarea{
			border-radius: 5px;
			width: 400%;
			overflow: auto;
			
		}
		.tip{
			font-family:"黑体";
			font-size:16px;
			color: red;
		}
		#edit .froala-element{
			height: 200px;
			overflow-y: auto;
		}
	 </style>
  </head>
  
  <body>
  	<div id="sendNoticeDiv">
	    <form action="transport/announcenNotice" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return send()">
		   <h2>发布新公告</h2>
	      <hr/>
	      <div style="clear:left"></div>
		    <div class="textDiv">
		    	<label  class="titleLabel">公告主题：</label>
		  		 <input name="transNotice.trans_notice_theme" value="" type="text" style="width: 300px" maxlength="20" required
		    id="theme" onblur="checkTheme(this)" placeholder="请输入公告主题"/>
		  		  <label class="tip">(20个字以内)</label>
		    </div>
		    <div class="textDiv">
		    	<label class="titleLabel">公告省份：</label><select id="cmbProvince" name="province"></select>
			    <label class="titleLabel">&emsp;公告城市：</label><select id="cmbCity" name="city" ></select>
			    <select id="cmbArea" name="cmbArea" style="display: none"></select>
			    <script type="text/javascript">
					addressInit('cmbProvince', 'cmbCity', 'cmbArea');
			    </script>
		    </div>
		    <div class="textDiv">
				<label  class="titleLabel">&emsp;公告目标：<select id="searchSchool" name="transNotice.trans_notice_target">
						<option>全部</option>
						<option>驾校</option>
						<option>学员</option>
					</select>
				</label>
			</div>
			<div style="clear:left"></div>
			<div class="textDiv">
				<label  class="titleLabel">公告内容：</label>
			</div>

			<div class="textDiv">
				<section id="editor">
				      <div id='edit' style="margin-top: 10px;width: 110%;margin-left: -50px">
				      <label id="tipInfo"></label>
				      <input name="transNotice.trans_notice_content" id="contentLabel" type="hidden"/>
				      </div>
				  </section>
				 
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
				<!-- <textarea  name="transNotice.trans_notice_content" rows="10" placeholder="请输入公告内容..."></textarea>  -->
		    </div>
		    
		    <div style="clear:left"></div>
		    <div class="textDiv">
				 <input type="submit" class="btn" value="发布公告"/>
				 <input type="reset" class="btn" value="取消" onclick="cancel()"/>
		    </div>
		   
	   </form>
   </div>
  </body>
</html>
