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
    
    <title>查看权限界面</title>
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">

	<style type="text/css">
		#managerInfoDiv{
			margin-left: 10%;
			margin-top: 10px;
		}
		#jurTreeDiv{
			margin-top: 10px;
			float:left;
			margin-left: 30px;
		}
		#jurTreeDiv2{
			margin-top: 30px;
			float:left;
			margin-left: 50px;
		}
		#jurMenuDiv{
			float:left;
			margin-left: 10%;
			margin-top: 10px;
		}
		li{
			list-style: none;
		}
		.secondJur{
			margin-left: 30px;
		}
		#btnDiv{
			margin-left: 10%;
		}
		hr{
	    	width: 1px;
	    	float:left;
	    }
	    h2{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	    #jurDetailDiv{
	    	margin-left: 30px;
	    	margin-top: 10px;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("hr").animate({width:"50%"},"slow");
			$("h2").animate({opacity: "1"},"slow");
			var msg = "<%=request.getAttribute("managerJurisdictions")%>";
			if(msg != null && msg != ""){
				var msg1 = msg.substring(1, msg.length-1);
				var jurTwos = msg1.split(",");
				var tags = document.getElementsByClassName("liTag");
				for(var j=0; j<tags.length; j++){
					for(var i=0; i<jurTwos.length; i++){
						var jurValue = "";
						if(jurTwos[i].toString().indexOf(tags[j].innerText.toString()) != -1){
							$input = $(tags[j]);
							$input.children(".secondJur").prop("checked", true);
							$input.parent().prev().children(".firstJur").prop("checked", true);
							break;
						}
					}
				}
			}
		});
		function back(){
			window.location.href = "transport/managerJurisdictionAction!showPage?nowPage=1";
		}
	</script>
  </head>
  
  <body>
    <div id="jurDetailDiv">
    	<h2 id="titleH">查看驾校管理员权限</h2>
	    <hr/>
    	<div style="clear:left"></div>
    	<div id="btnDiv">
    		<input type="button" value="返回" class="btn" onclick="back()"/>
    	</div>
    	<div id="managerInfoDiv">
    		<label>用户名：<s:property value="manager.school_manager_account"/>&emsp;&emsp;驾校：<s:property value="manager.school.school_name"/></label>
    	</div>
    	<div id="jurDiv">
    		<div id="jurMenuDiv"><label>菜单权限：</label></div>
    		<div id="jurTreeDiv">
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled">系统管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="schoolVerifyMenu">驾校认证</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled" id="studentManageFirstMenu">学员管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled"  id="userManageSecondMenu">学员管理</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="userJurSecondMenu">学员审核</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled" id="examManageMenu">考试管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="examArrangeMenu">考试安排</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="examResultMenu">成绩管理</li>
					</ul>
				</details>
    		</div>
    		<div id="jurTreeDiv2">
    			<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled" id="teacherManageFirstMenu">教练管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="teacherManageSecondMenu">教练管理</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled" id="noticeManageMenu">公告管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="checkNoticeMenu">查看公告</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="sendNoticeMenu">发布公告</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" disabled="disabled" id="settingMenu">设置</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="revisePersonInfoMenu">修改个人信息</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="revisePasswordMenu">修改密码</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="aboutMenu">关于</li>
						<li class="liTag"><input type="checkbox" class="secondJur" disabled="disabled" id="logoutMenu">退出登录</li>
					</ul>
				</details>
    		</div>
    	</div>
    	<div style="clear:left"></div>
    	
    </div>
  </body>
</html>
