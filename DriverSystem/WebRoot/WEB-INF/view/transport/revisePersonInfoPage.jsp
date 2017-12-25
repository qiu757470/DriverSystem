<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
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
    
    <title>修改个人信息界面</title>
    <link rel="stylesheet" href="css/manager_css.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
			margin-top: 10px;
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
			/* 删除所有全部的节点
			var provinces = $("#cmbProvince").children();
			for(var i=0; i<provinces.length; i++){
				if(provinces[i].innerText == '全部'){
					$(provinces[i]).remove();
					break;
				}
			} */
			$("hr").animate({width:"50%"},"slow");
			$("h2").animate({opacity: "1"},"slow");
			var province = "<%= session.getAttribute("loginTransUser") != null ? ((TransUser)session.getAttribute("loginTransUser")).getTrans_user_province() : null %>";
			if(province != null && province != "" && province != "null"){
				$("#cmbProvince").val(province);
			}
			
			addressInit1('cmbProvince', 'cmbCity', 'cmbArea'); 
			
			var city = "<%= session.getAttribute("loginTransUser") != null ? ((TransUser)session.getAttribute("loginTransUser")).getTrans_user_city() : null%>";
			if(city != null && city != "" && city != "null"){
				$("#cmbCity").val(city);
			}
			
			
			var msg = "<%=request.getAttribute("message")%>";
			if(msg != null && msg != "" && msg != "null"){
				alert(msg);
			}
			
			var sex = "<%= session.getAttribute("loginTransUser") != null ? ((TransUser)session.getAttribute("loginTransUser")).getTrans_user_sex() : null%>";
			if(sex != null && sex != "" && sex != "null"){
				$("#userSex").val(sex);
			}		
		});
		function check(tag){
			var userName = $(tag).val();
			if(tag.validity.valueMissing){
				$(tag).parent().next().children("img").attr("src", "img/wrong.png");
				$(tag).parent().next().children("input").val("false");
				$(tag).attr("oninvalid","setCustomValidity('用户名不能为空！');");
				$("#confirm").click();
				return;
			}
			if(tag.validity.patternMismatch){
				$(tag).parent().next().children("img").attr("src", "img/wrong.png");
				$(tag).parent().next().children("input").val("false");
				$(tag).attr("oninvalid","setCustomValidity('用户姓名格式错误（只能为数字、字母和中文的组合）');");
				$(tag).val("");
				$("#confirm").click();
				return;
			}
			$(tag).parent().next().children("img").attr("src", "img/correct.png");
			$(tag).parent().next().children("input").val("true");
		}
		function confirmRevise(){
			if(confirm("确认修改个人信息吗？")){
				var userNameCheck = $("#userNameCheck").val();
				if(userNameCheck == true || userNameCheck == "true"){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
		}
		function resetForm(){
			window.location.href = "transport/settingAction!revisePersonInfo";
		}
		
		
		function cancelRevise(){
			if(confirm('确认取消修改吗？')){
				window.location.href = "transport/transportMain";
			}
		}
	</script>
  </head>
  
  <body>
    <div id="reviseDiv">
    	 <h2>修改个人信息</h2>
     	 <hr/>
     	 <div style="clear:left"></div>
    	<form action="transport/settingAction!confirmReviseData" onsubmit="return confirmRevise()" method="post" enctype="application/x-www-form-urlencoded">
		    	<table cellspacing="5px">
		    		<tr>
		    			<td>
		    				<label class="titleLabel">用户账号：&emsp;</label><label class="contentLabel"><s:property value="#session.loginTransUser.trans_user_account"/></label>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="textDiv"><label class="titleLabel">用户姓名：&emsp;</label><input id="userNameInput" type="text" value="<s:property value="#session.loginTransUser.trans_user_name"/>" required pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+"
						oninvalid="setCustomValidity('用户姓名格式错误（只能为数字、字母和中文的组合）');" onblur="check(this)" placeholder="请输入姓名/昵称"
						oninput="setCustomValidity('');" name="alterName"></div>
							<div class="checkDiv"><img id="userNameImg" class="checkImg" src="img/correct.png" /><input type="hidden" value="true" id="userNameCheck" /></div>
						</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="textDiv"><label class="titleLabel">省份：&ensp;&emsp;&emsp;&ensp;</label><select id="cmbProvince" name="alterProvince"></select></div>
		    				<div class="checkDiv"><img id="userProvinceImg" class="checkImg" src="img/correct.png" /></div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="textDiv"><label class="titleLabel">城市：&ensp;&emsp;&emsp;&ensp;</label><select id="cmbCity" name="alterCity"></select>
		    					<select id="cmbArea" name="cmbArea" style="display: none"></select></div>
		    				<div class="checkDiv"><img id="userCityImg" class="checkImg" src="img/correct.png" /></div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">详细地址：&emsp;</label><input id="userAddressInput" type="text" name="alterAddress" value="<s:property value="#session.loginTransUser.trans_user_address"/>" placeholder="请输入地址"/></div></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">性别：&emsp;&emsp;&emsp;</label><select id="userSex" name="alterSex"><option>男</option><option>女</option></select></div>
		    			<div class="checkDiv"><img id="userSexImg" class="checkImg" src="img/correct.png" /></div></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">身份证号码：</label><input id="userIdInput" type="text" value="<s:property value="#session.loginTransUser.trans_user_identification"/>" name="alterID" placeholder="请输入身份证号码"></div></td>
		    		</tr>
		    		<tr>
		    			<td><div class="textDiv"><label class="titleLabel">电话号码：&emsp;</label><input id="userPhoneInput" type="text" value="<s:property value="#session.loginTransUser.trans_user_phone"/>" name="alterPhone" placeholder="请输入电话号码"></div></td>
		    		</tr>
		    		<tr style="height: 50px">
		    			<td><input class="btn" id="confirm" type="submit" value="确认修改" />&emsp;&emsp;<input id="resetInput" class="btn" type="reset" value="重置" onclick="resetForm()"/>&emsp;&emsp;<input class="btn" type="button" value="取消修改" onclick="cancelRevise()"/></td>
		    		</tr>
		    	</table>
	    	</form>
	    	<script type="text/javascript" src="<%=basePath%>js/jsAddress1.js"></script> 
			<script type="text/javascript">  
	                 addressInit1('cmbProvince', 'cmbCity', 'cmbArea');  
	        </script>  
    </div>
  </body>
</html>
