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
    
    <title>批量创建管理员详情界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
		#createSchoolDiv{
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
			window.location.href = "transport/managerManagementAction!createBatchPage";
		}
	</script>
  </head>
  
  <body>
      <div id="createSchoolDiv">
      <h2>新建驾校详情</h2>
      <hr/>
      <div style="clear:left"></div>
      	<table cellspacing="10px">
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校名称：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_name" /></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人姓名：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_representative_name" /></label></div>
				</td>
      			
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人身份证：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_representative_id" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人电话：&emsp;</label>
      				<label class="conetentLabel"><s:property value="#request.createSchool.school_representative_phone" /></label></div>
      			</td>

      		</tr>
      		
      		<tr>
      		
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在省：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_province" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校所在市：</label><label class="conetentLabel"><s:property value="#request.createSchool.school_city" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校详细地址：</label><label class="conetentLabel"><s:property value="#request.createSchool.school_address" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校电话：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_phone" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">经营许可证：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_certificate" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;组织代码：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_code" /></label></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校状态：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_state" /></label></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校主页：&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_homepage" /></label></div>
      			</td>

      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校学费：&emsp;&emsp;</label><label class="conetentLabel"><s:property value="#request.createSchool.school_charge" /></label></div>
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
