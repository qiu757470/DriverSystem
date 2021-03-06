<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
    <title>驾校管理员详情界面</title>

	<style type="text/css">
		#createManagerDiv{
			margin-left: 50px;
			margin-top: 30px;
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
		});
		function back(){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
		}
	</script>
  </head>
  
  <body>
      <div id="createManagerDiv">
      <h2>驾校管理员详情</h2>
      <hr/>
      <div style="clear:left"></div>
      	<table cellspacing="10px">
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">用户账号：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_account" /></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;用户姓名：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_name" /></label></div>
				</td>
      			
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">性别：&emsp;&emsp;&emsp;&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_sex" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;用户身份证：&emsp;</label>
      				<label class="conetentLabel"><s:property value="manager.school_manager_identification" /></label></div>
      			</td>

      		</tr>
      		
      		<tr>
      		
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">用户所在省：&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_province" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;用户所在市：&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_city" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">用户详细地址：</label><label class="conetentLabel"><s:property value="manager.school_manager_address" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;用户电话号码：</label><label class="conetentLabel"><s:property value="manager.school_manager_phone" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">账号状态：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="manager.school_manager_state" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校所在省：&emsp;</label><label class="conetentLabel"><s:property value="manager.school.school_province" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在市：&emsp;</label><label class="conetentLabel"><s:property value="manager.school.school_city" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校名称：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="manager.school.school_name" /></label></div>
      			</td>

      		</tr>
      		
      		<tr>
      			      			
      			<td>
      				<div class="textDiv" style="margin-top: 20px"><input class="btn" type="button" value="返回" onclick="back()"/></div>
      			</td>
      		
      		</tr>
      	</table>
      </div>
      
  </body>
</html>
