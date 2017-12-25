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
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
    <title>修改权限界面</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#managerInfoDiv{
			margin-left: 10%;
			margin-top: 20px;
		}
		#jurTreeDiv{
			margin-top: 10px;
			float:left;
			margin-left: 30px;
		}
		#jurTreeDiv2{
			margin-top: 10px;
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
		#jurDetailDiv{
			margin-left: 30px;
			margin-top: -20px;
		}
		#btnDiv{
			margin-top: 0px;
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
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("hr").animate({width:"50%"},"slow");
			$("h2").animate({opacity: "1"},"slow");
			var accounts = "<%=request.getAttribute("userAccounts")%>";
			if(accounts.endsWith(",")){
				accounts = accounts.substring(0, accounts.length-1);
			}
			var alterSelectSuccess = "<%=request.getAttribute("alterSelectSuccess")%>";
			if(accounts != null && accounts != "" && accounts != "null"){
				if(!(alterSelectSuccess != null && alterSelectSuccess != "" && alterSelectSuccess == "true")){
					var firsts = document.getElementsByClassName("firstJur");
					var seconds = document.getElementsByClassName("secondJur");
					for(var i=0; i<firsts.length; i++){
						$(firsts[i]).prop("checked", false);
					}
					for(var j=0; j<firsts.length; j++){
						$(seconds[j]).prop("checked", false);
					}
				}
				var acc = accounts.split(",");
				if(acc.length <= 3 && acc.length >= 1){
					$("#userNameLabel").html("用户名：" + accounts);
				}else if(acc.length > 3){
					acc = acc[0] + "," + acc[1] + "," + acc[2];
					$("#userNameLabel").html("用户名：" + acc + "等人");
				}
				$("#titleH").html('修改驾校管理员权限（多用户）');
			}
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
			var saveMsg = "<%=request.getAttribute("saveMsg")%>";
			if(saveMsg != null && saveMsg != "" && saveMsg != "null"){
				alert(saveMsg);
			}
		});
		function changeJurTwo(tag){
			var tags = $(tag).parent().parent().children();
			var num = 0;
			for(var i=0; i<tags.length; i++){
				var check = $(tags[i]).children(".secondJur").prop("checked");
				if(check == true){
					num++;
				}
			}
			if(num == 0){
				$(tag).parent().parent().prev().children(".firstJur").prop("checked", false);
			}else{
				$(tag).parent().parent().prev().children(".firstJur").prop("checked", true);
			}
		}
		function changeJurOne(tag){
			var check = $(tag).prop("checked");
			var open = $(tag).parent().parent().attr("open");
			if(open == "open"){
				$(tag).parent().parent().removeAttr("open");
			}else{
				$(tag).parent().parent().attr("open", "open");
			}
			if(check == true){
				var tags = $(tag).parent().next().children();
				for(var i=0; i<tags.length; i++){
					$(tags[i]).children(".secondJur").prop("checked", true);
				}
			}else{
				var tags = $(tag).parent().next().children();
				for(var i=0; i<tags.length; i++){
					$(tags[i]).children(".secondJur").prop("checked", false);
				}
			}
		
		}
		function cancelRevise(){
			if(confirm("确认取消修改吗？")){
				window.location.href = "transport/managerJurisdictionAction!showPage?nowPage=1";
			}
		}
		function confirmRevise(){
			if(confirm("确认保存修改吗？")){
				var tags = document.getElementsByClassName("secondJur");
				var jurTwoNames = "";
				for(var i=0; i<tags.length; i++){
					if($(tags[i]).prop("checked")){
						if(i < tags.length - 1){
							jurTwoNames += $(tags[i]).val() + ",";
						}else{
							jurTwoNames += $(tags[i]).val();
						}
					}
				}
				if(jurTwoNames == ""){
					jurTwoNames = "empty";
				}
				if(jurTwoNames.endsWith(",")){
					jurTwoNames = jurTwoNames.substring(0, jurTwoNames.length - 1);
				}
				var accounts = "<%=request.getAttribute("userAccounts")%>";
				if(accounts.endsWith(",")){
					accounts = accounts.substring(0, accounts.length-1);
				}
				if(accounts != null && accounts != "" && accounts != "null"){
					window.location.href = "transport/managerJurisdictionAction!saveJurisdiction?userAccounts=" + accounts + "&jurTwoNames=" + encodeURI(encodeURI(jurTwoNames));
					return;
				}else{
					var manager_id = document.getElementById("manager_id").value;
					window.location.href = "transport/managerJurisdictionAction!saveJurisdiction?jurManagerId=" + manager_id + "&jurTwoNames=" + encodeURI(encodeURI(jurTwoNames));
					return;
				}
			}
		}
	</script>
  </head>
  
  <body>
    <div id="jurDetailDiv">
	    <h2 id="titleH">修改驾校管理员权限（单用户）</h2>
	    <hr/>
    	<div style="clear:left"></div>
    	<div id="btnDiv">
    		<input type="button" value="保存修改" class="btn" onclick="confirmRevise()"/>&emsp;
    		<input type="button" value="取消修改" class="btn" onclick="cancelRevise()"/>
    	</div>
    	<div id="managerInfoDiv">
    		<input type="hidden" value="<s:property value="manager.school_manager_id"/>"  id="manager_id"/>
    		<label id="userNameLabel">用户名：<s:property value="manager.school_manager_account"/>&emsp;&emsp;驾校：<s:property value="manager.school.school_name"/></label>
    	</div>
    	<div id="jurDiv">
    		<div id="jurMenuDiv"><label>菜单权限：</label></div>
    		<div id="jurTreeDiv">
				<details open="open">
					<summary><input type="checkbox"  class="firstJur" onchange="changeJurOne(this)" value="系统管理">系统管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="schoolVerifyMenu" onchange="changeJurTwo(this)" value="驾校认证">驾校认证</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur"  id="studentManageFirstMenu" onchange="changeJurOne(this)" value="学员管理">学员管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"   id="userManageSecondMenu" onchange="changeJurTwo(this)" value="学员管理">学员管理</li>
						<li class="liTag"><input type="checkbox" class="secondJur"   id="userJurSecondMenu" onchange="changeJurTwo(this)" value="学员审核">学员审核</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur"  id="examManageMenu" onchange="changeJurOne(this)" value="考试管理">考试管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="examArrangeMenu" onchange="changeJurTwo(this)" value="考试安排">考试安排</li>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="examResultMenu" onchange="changeJurTwo(this)" value="成绩管理">成绩管理</li>
					</ul>
				</details>
    		</div>
    		<div id="jurTreeDiv2">
    			<details open="open">
					<summary><input type="checkbox"  class="firstJur"  id="teacherManageFirstMenu" onchange="changeJurOne(this)" value="教练管理">教练管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="teacherManageSecondMenu" onchange="changeJurTwo(this)" value="教练管理">教练管理</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur"  id="noticeManageMenu" onchange="changeJurOne(this)" value="公告管理">公告管理</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="checkNoticeMenu" onchange="changeJurTwo(this)" value="查看公告">查看公告</li>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="sendNoticeMenu" onchange="changeJurTwo(this)" value="发布公告">发布公告</li>
					</ul>
				</details>
				<details open="open">
					<summary><input type="checkbox"  class="firstJur"  id="settingMenu" onchange="changeJurOne(this)" value="设置">设置</summary>
					<ul>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="revisePersonInfoMenu" onchange="changeJurTwo(this)" value="修改个人信息">修改个人信息</li>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="revisePasswordMenu" onchange="changeJurTwo(this)" value="修改密码">修改密码</li>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="aboutMenu" onchange="changeJurTwo(this)" value="关于">关于</li>
						<li class="liTag"><input type="checkbox" class="secondJur"  id="logoutMenu" onchange="changeJurTwo(this)" value="退出登录">退出登录</li>
					</ul>
				</details>
    		</div>
    	</div>
    	<div style="clear:left"></div>
    	
    </div>
  </body>
</html>
