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
    
    <title>驾校审核详情界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
		#createManagerDiv{
			margin-left: 50px;
			margin-top: 10px;
		}
	    #hrLine{
	    	width: 1px;
	    	float:left;
	    }
	    #titleH{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#hrLine").animate({width:"50%"},"slow");
			$("#titleH").animate({opacity: "1"},"slow");
			var updateSuccess = "<%=request.getAttribute("updateSuccess")%>";
			if(updateSuccess != null && (updateSuccess == true || updateSuccess == "true")){
				if(!confirm("审核成功！是否重新审核？")){
					back();
				}
			}else if(updateSuccess != null && (updateSuccess == false || updateSuccess == "false")){
				alert('审核失败！');
			}
			var url = "<%=(School)request.getAttribute("school") != null ? ((School)request.getAttribute("school")).getSchool_file_url() : ""%>";
			if(url.length > 0){
				url = url.substring(url.lastIndexOf("/") + 1, url.length);
				$("#fileUrl").html(url);
			}else{
				$("#fileDiv").html('认证资料未上传');
				$("#fileDiv").css('color', 'red');
			}
		});
		function back(){
				window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1";
		}
/* 		function homePage(tag){
			var url = $(tag).html();
			alert(url);
			$(tag).attr('href', url);
		} */
		function showText(tag){
			var state = $(tag).val();
			if(state == '审核拒绝'){
				$("#refuseTd").css('display', 'block');
			}else{
				$("#refuseTd").css('display', 'none');
			}
		}
		function verify(){
			if(confirm('确定提交审核吗？')){
				return true;			
			}
			return false;
		}
	</script>
  </head>
  
  <body>
      <div id="createManagerDiv">
      <h2 id="titleH">审核驾校</h2>
      <hr id="hrLine"/>
      <div style="clear:left"></div>
        <form enctype="application/x-www-form-urlencoded" method="post" action="transport/schoolVerifyAction!verifySchool" onsubmit="return verify()">
	      	<table cellspacing="10px">
	      		<tr>
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾校名称：&emsp;</label><label class="contentLabel"><s:property value="school.school_name"/></label></div>
					</td>
	      			
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">法人姓名：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_representative_name"/></label></div>
					</td>
					
	      		</tr>
	      			
	      		<tr>
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">法人身份证：</label><label class="contentLabel"><s:property value="school.school_representative_id"/></label></div>
					</td>
					
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">法人电话号码：</label><label class="contentLabel"><s:property value="school.school_representative_phone"/></label></div>
					</td>
	
	      		</tr>
	      		
	      		<tr>
	
					<td>
	      				<div class="textDiv"><label class="titleLabel">驾校所在省：</label><label class="contentLabel"><s:property value="school.school_province"/></label></div>
					</td>
					
					<td>
	      				<div class="textDiv"><label class="titleLabel">驾校所在市：&emsp;</label><label class="contentLabel"><s:property value="school.school_city"/></label></div>
					</td>
	      			
	      		</tr>
	      		
	      		<tr>
	      		
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾详细地址：</label><label class="contentLabel"><s:property value="school.school_address"/></label></div>
					</td>
	      			
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾校电话号码：</label><label class="contentLabel"><s:property value="school.school_phone"/></label></div>
					</td>
	      			
	      			
	      		</tr>
	      		
	      		<tr>
	      		
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">经营许可证：</label><label class="contentLabel"><s:property value="school.school_certificate"/></label></div>
					</td>
	      			
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">组织代码：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_code"/></label></div>
					</td>
	      			
	      		</tr>
	      		
	      		<tr>
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾校主页：&emsp;</label><a class="contentLabel" target="_blank" href="http://<s:property value="school.school_homepage" />"><s:property value="school.school_homepage" /></a></div>
					</td>
	      			
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾校状态：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_state"/></label></div>
					</td>
	      		</tr>
	      		
	      		<tr>
	      			<td>
	      				<div class="textDiv"><label class="titleLabel">驾校学费：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_charge"/></label></div>
					</td>
					
					<td id="fileTd">
	      				<div class="textDiv" id="fileDiv"><input class="btn" type="button" value="认证资料下载" style="width: 120px"><a class="contentLabel" id="fileUrl" href="<s:property value="school.school_file_url"/>"><s:property value="school.school_file_url"/></a></div>
					</td>
	      		</tr>
	      		
	      		<tr>
	      			<td>
	      				<input type="hidden" value="<s:property value="school.school_id"/>" name="alterSchool.school_id"/>
	      				<div class="textDiv" >驾校审核：&emsp;<select name="alterSchool.school_state" id="stateSelect" style="width:120px" onclick="showText(this)"><option>审核通过</option><option>审核拒绝</option><option>倒闭</option></select></div>
	      			</td>
	      			
	      			<td id="refuseTd" style="display: none">
	      				<div class="textDiv" style='margin-top: 0px'>拒绝理由：&emsp;<textarea name="alterSchool.school_refuse_reason" style="width: 150%;height: 30%;overflow-y: scroll;border-radius: 5px"></textarea></div>
	      			</td>
	      		</tr>
      		
	      		<tr>
	      			<td>
	      				<div class="textDiv"><input class="btn" type="submit" value="确定" /><input class="btn" type="button" value="取消" onclick="back()"/></div>
	      			</td>
	      		</tr>
	      	</table> 
      	</form>
      </div>
      
  </body>
</html>
