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
    
    <title>修改密码界面</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/manager_css.css">
	<style type="text/css">
		.titleLabel{
			font-family: "微软雅黑";
			font-weight: bold;
			font-size:22px;
			float:left;
			height:30px;
		}
		.contentLabel{
			font-family: "微软雅黑";
			font-weight: bold;
			color:gray;
			font-size:20px;
			float:left;
			height:30px;
		}
		#reviseDiv{
			margin-left: 50px;
			margin-top: 30px;
		}
		.text{
			height:30px;
		}
	    .checkImg{
	    	height: 20px;;
	    }
	    .checkDiv{
	    	float:left;
	    	margin-top:8px;
	    	margin-left: 10px;
	    }
	    .textDiv{
	    	float:left;
	    }
		hr{
	    	width: 1px;
	    	float:left;
	    }
	    h2{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	    .btn{
	    	float:left;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("hr").animate({width:"50%"},"slow");
			$("h2").animate({opacity: "1"},"slow");
			var msg = "<%=request.getAttribute("message")%>";
			if(msg != null && msg != "" && msg != "null"){
				alert(msg);
				if(msg.indexOf("修改成功") != -1){
					parent.logout();
				}
			}	
		});
		function check(tag){
			var pwd = $(tag).val();
			if(pwd.length < 6){
				/* $(tag).val("");
				$(tag).attr("placeholder", "密码不能小于6位！"); */
				$(tag).val("");
				$(tag).attr("oninvalid","setCustomValidity('密码不能小于6位！');");
				$(tag).parent().next().children("img").attr("src", "img/wrong.png");
				$(tag).parent().next().next().val("false");
				$("#confirmBtn").click();
				return;
			}
			if(!pwd.match("^[A-Za-z0-9]*$")){
				/* $(tag).val("");
				$(tag).attr("placeholder", "密码格式错误！"); */
				$(tag).val("");
				$(tag).attr("oninvalid","setCustomValidity('密码格式错误！（密码只能为字母和数字的组合）');");
				$(tag).parent().next().children("img").attr("src", "img/wrong.png");
				$(tag).parent().next().next().val("false");
				$("#confirmBtn").click();
				return;
			}
			if($(tag).attr("id") == "confirmPwd"){
				if($(tag).val() != $("#newPwd").val()){
					$(tag).val("");
					$(tag).attr("placeholder", "两次密码不一致！");
					$(tag).parent().next().children("img").attr("src", "img/wrong.png");
					$(tag).parent().next().next().val("false");
					return;
				}
			}
			$(tag).parent().next().children("img").attr("src", "img/correct.png");
			$(tag).parent().next().next().val("true");
			return;
			
		}
		function confirmRevise(){
			if(confirm("确认修改密码吗？")){
				var oldPwdCheck = document.getElementById("oldPwdCheck").value;
				var newPwdCheck = document.getElementById("newPwdCheck").value;
				var confirmPwdCheck = document.getElementById("confirmPwdCheck").value;
				if((oldPwdCheck == "true" || oldPwdCheck == true)
				 && (newPwdCheck == "true" || newPwdCheck == true)
				  && (confirmPwdCheck == "true" || confirmPwdCheck == true)){
				  	return true;
				  }else{
					return false;
				  }
			}else{
				return false;
			}
			
		}
		function resetForm(){
			$("#oldPwdImg").attr("src", "img/wrong.png");
			$("#newPwdImg").attr("src", "img/wrong.png");
			$("#confirmPwdImg").attr("src", "img/wrong.png");
			$("#oldPwdCheck").attr("value", "false");
			$("#newPwdCheck").attr("value", "false");
			$("#confirmPwdCheck").attr("value", "false");
		}
		function cancelRevise(){
			window.location.href = "transport/transportMain";
		}
	</script>
  </head>
  
  <body>
	  	<div id="reviseDiv">
	  	<h2>修改密码</h2>
     	 <hr/>
     	 <div style="clear:left"></div>
	  		<form action="transport/settingAction!confirmRevisePwd" onsubmit="return confirmRevise()" method="post" enctype="application/x-www-form-urlencoded">
		    	<table cellspacing="10px">
		    		<tr>
		    			<td><label class="titleLabel">用户账号：</label><label class="contentLabel"><s:property value="#session.loginTransUser.trans_user_account"/> </label></td>
		    		</tr>
		    		<tr>
		    			<td><label class="titleLabel">用户姓名：</label><label class="contentLabel"><s:property value="#session.loginTransUser.trans_user_name"/> </label></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">旧密码：&emsp;</label><input class="text" name="oldPwd" id="oldPwd" type="password" placeholder="请输入旧密码" required  pattern="^[a-zA-Z0-9]+"
						oninvalid="setCustomValidity('密码格式错误（密码只能为数字和字母的组合）');"
						oninput="setCustomValidity('');" onblur="check(this)"/></div><div class="checkDiv"><img id="oldPwdImg" class="checkImg" src="img/wrong.png" /></div><input id="oldPwdCheck"type="hidden" value="false" /></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">新密码：&emsp;</label><input class="text" name="newPwd" id="newPwd" type="password" placeholder="请输入新密码" required  pattern="^[a-zA-Z0-9]+"
						oninvalid="setCustomValidity('密码格式错误（密码只能为数字和字母的组合）');"
						oninput="setCustomValidity('');"  onblur="check(this)"/></div><div class="checkDiv"><img id="newPwdImg" class="checkImg" src="img/wrong.png"/></div><input id="newPwdCheck"type="hidden" value="false" /></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">确认密码：</label><input class="text" name="confirmPwd" id="confirmPwd" type="password" placeholder="请输入确认密码" required  pattern="^[a-zA-Z0-9]+"
						oninvalid="setCustomValidity('密码格式错误（密码只能为数字和字母的组合）');"
						oninput="setCustomValidity('');"  onblur="check(this)"/></div><div class="checkDiv"><img id="confirmPwdImg" class="checkImg" src="img/wrong.png"/></div><input id="confirmPwdCheck"type="hidden" value="false" /></td>
		    		</tr>
		    		<tr style="height: 50px">
		    			<td><input class="btn" type="submit" value="确认修改" id="confirmBtn"/>&emsp;&emsp;<input class="btn" type="reset" value="重置" onclick="resetForm()"/>&emsp;&emsp;<input class="btn" type="button" value="取消修改" onclick="cancelRevise()"/></td>
		    		</tr>
		    	</table>
	    	</form>
	    </div>
  </body>
</html>
