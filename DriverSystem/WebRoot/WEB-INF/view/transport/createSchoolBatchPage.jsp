<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>批量创建驾校界面</title>
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
    <style type="text/css">
    	#uploadDiv{
    		margin-left: 80px;
    		margin-top: 20px;
    	}
    	#previewFile{
    		opacity: 0;
    		position: absolute;
    		left:360px;
    	}
    	#previewBtn{
    		position: absolute;
    		left:360px;
    	}
    	
    	#filePositionLabel{
    		position: left;
    		left:0px;
    	}
    	#filePath{
    		position: left;
    		left:60px;
    		width: 200px;
    	}
    	#importBtn{
    		position: absolute;
    		left:450px;
    	}
    	#userListDiv{
    		margin-left: 10%;
    		margin-top: 10px;
    		width: 90%;
    		display: none;
    	}
		#friendNote{
			color:red;
			margin-left: 25%;
			font-size:14px;
		}
		#friendNote1{
			font-size:14px;
		}
		#mask_shadow{
			width: 100%;
			height: 100%;
			position: absolute;
			z-index: 100;
			opacity: 0;
			display: none;
		}
		#inputPwdDiv{
			position: absolute;
			width: 300px;
			height:150px;
			top:25%;
			left:30%;
			z-index: 101;
			border-radius: 10px;
			text-align: center;
			background-color: white;
			box-shadow: 5px 5px 5px gray;
			display: none;
			opacity:0;
			margin-top: -300px;
		}
    </style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#firstPageBtn").attr('title','首页');
			$("#prevPageBtn").attr('title','上一页');
			$("#nextPageBtn").attr('title','下一页');
			$("#lastPageBtn").attr('title','尾页');
			var uploadSuccess = "<%=request.getAttribute("uploadSuccess")%>";
			if(uploadSuccess != null && uploadSuccess != "" && (uploadSuccess == 'true' || uploadSuccess == true)){
				alert('上传成功！');
			}else if(uploadSuccess != null && uploadSuccess != "" && (uploadSuccess == 'false' || uploadSuccess == false)){
				alert('上传失败！');
			}
			var createSuccess = "<%=request.getAttribute("createSuccess")%>";
			if(createSuccess != null && createSuccess != "" && (createSuccess == 'true' || createSuccess == true)){
				alert('创建成功！');
			}else if(createSuccess != null && createSuccess != "" && (createSuccess == 'false' || createSuccess == false)){
				alert('创建失败！');
			}
			var newSchools = "<%=session.getAttribute("newSchools")%>";
			if(newSchools != null && newSchools != "null" && newSchools != ""){
				$("#userListDiv").css('display', 'block');
				$("#userListNoneDiv").css('display', 'none');
			}else{
				$("#userListDiv").css('display', 'none');
				$("#userListNoneDiv").css('display', 'block');
			}
			var noDetail = "<%=session.getAttribute("noDetail")%>";
			if(noDetail != null && noDetail != "" && (noDetail == 'true' || noDetail == true)){
				alert('无法进入详情页');
			}
			var inputFilePwd = "<%=request.getAttribute("inputFilePwd")%>";
			if(inputFilePwd != null && inputFilePwd != "" && (inputFilePwd == 'true' || inputFilePwd == true)){
				$("#mask_shadow").css('display','block');
				$("#inputPwdDiv").css('display','block'); 
				$("#inputPwdDiv").animate({marginTop:"0px",opacity:1},"slow");
			}
			var wrongLogin = "<%=request.getAttribute("wrongLogin")%>";
			if(wrongLogin != null && wrongLogin != "" && (wrongLogin == "true" || wrongLogin == true)){
				parent.logout();
			}
			var nowPage = "<%=session.getAttribute("createSchoolNowPage")%>";
			if(nowPage != null && nowPage != ""){
				$("#nowPageInput").val(nowPage);
			}
		});
		function importFile(){
			var filePath = $("#filePath").val();
			if(filePath == null || filePath == ""){
				alert('请先点击“预览”按钮，选择您要上传的文件');
				return false;
			}
			return true;
		
		}
		function preview(){
			$("#previewFile").click();
		}
		function writeFilePath(){
			var path = $("#previewFile").val();
			var filePath = path.substr(path.lastIndexOf("\\") + 1);
			$("#filePath").val(filePath);
		}
		function firstPage(){
			var nowPage = $("#nowPageInput").val();
			if(nowPage == 1){
				return;
			}
			window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=1";
			return;
		}
		function prevPage(){
			var nowPage = $("#nowPageInput").val();
			if(nowPage <= 1){
				return;
			}
			nowPage--;
			window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=" + nowPage;
			return;
		}
		function nextPage(){
			var nowPage = $("#nowPageInput").val();
			var allPage = "<%= session.getAttribute("createPageNum")%>";
			if(nowPage >= allPage){
				return;
			}
			nowPage++;
			window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=" + nowPage;
			return;
		}
		function lastPage(){
			var nowPage = $("#nowPageInput").val();
		
			var allPage = "<%= session.getAttribute("createPageNum")%>";
			if(nowPage == allPage){
				return;
			}
			window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=" + allPage;
			return;
		}
		function jumpTo(){
			var nowPage = $("#nowPageInput").val();
			if(!nowPage.match("^[0-9]*$")) {
				window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=1";
				return;
			}
			var allPage = "<%= session.getAttribute("createPageNum")%>";
			if(nowPage < 1){
				window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=1";
				return;
			}else if(nowPage >= allPage + 1){
				window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=" + allPage;
				return;
			}
			window.location.href = "transport/schoolManagementAction!showCreatePage?batchNowPage=" + nowPage;
		}
		function changePage(){
			var nowPage = $("#nowPageInput").val();
			if(!nowPage.match("^[0-9]*$")) {
				$("#nowPageInput").val("1");
				return;
			}
			var allPage = "<%= session.getAttribute("createSchoolPageNum")%>";
			if(nowPage < 1){
				$("#nowPageInput").val("1");
				return;
			}else if(nowPage > allPage){
				$("#nowPageInput").val(allPage);
				return;
			}
		}
		function checkAll(){
			var allCheck = $("#checkAllInput").prop("checked");
			var isCheck = true;
			if(allCheck == true || allCheck == "checked"){
				isCheck = true;
			}else{
				isCheck = false;
			}
			var checks = document.getElementsByClassName("userCheck");
			for(var i = 0; i<checks.length; i++){
				$check = $(checks[i]);
				$check.prop("checked",isCheck);
			}
		}
		function createAll(){
			if(confirm('确认全部创建吗？')){
				window.location.href = "transport/schoolManagementAction!createAll";
			}
		}
		function createSelect(){
			var checks = document.getElementsByClassName("userCheck");
			var count = 0;
			var createIds = "";
			
			for(var i = 0; i<checks.length; i++){
				var isCheck = $(checks[i]).prop("checked");
				if(isCheck){
					count++;
					if(i < checks.length -1){
						createIds += $(checks[i]).next().val() + ",";
					}else{
						createIds += $(checks[i]).next().val();
					}
				}
			}
			if(createIds.endsWith(",")){
				createIds = createIds.substr(0, createIds.length-1);
			}
			if(count == 0){
				alert("请先勾选账号");
				return;
			}
			if(confirm('确认创建勾选的账号吗？')){
				window.location.href = "transport/schoolManagementAction!createSelect?createIds=" + createIds;
			}
		}
		function cancelInputPwd(){
			$("#mask_shadow").css('display','none');
			$("#inputPwdDiv").animate({marginTop:"-300px",opacity:0},"slow");
			
		}
	</script>
  </head>
  
  <body>
  	<div id="mask_shadow"></div>
  	<div id="inputPwdDiv">
		<form id="inputPwdForm" action="transport/schoolManagementAction!uploadFile" method="post">
			<div style="margin-top: 10px;margin-bottom: 10px">
				<label style="font-size: 20px;margin-left: 20px;color:black;width:120px;font-weight:bold">请输入压缩文件密码:</label>
			</div>
			<div>
				<input id="inputPwdInput" type="password" style="width:200px;height:30px;margin-left:20px" 
			placeholder="请输入压缩文件密码" required  oninvalid="setCustomValidity('压缩密码不能为空');" oninput="setCustomValidity('');" name="filePwd"/>
			</div>
			<div style="float:left;margin-left:50px;width:105px;height:35px;margin-top: 20px">
				<input  id="confirmInputPwdBtn" type="submit" class="btn" value="确定" />
			</div>
			<div style="float:left;margin-left:10px;width:105px;height:35px;margin-top: 20px">
				<input id="cancelInputPwdBtn" type="button" class="btn" value="取消" onclick="cancelInputPwd()"/>
			 </div>
		</form>
	</div>
    <div id="uploadDiv">
    	<form action="transport/schoolManagementAction!uploadFile"
			enctype="multipart/form-data" id="fileForm" method="post" onsubmit="return importFile()">
				<label id="filePositionLabel">文件：</label>
				<input id="filePath" type="text" name="filePath" placeholder="请选择Excel文件或压缩包"></input>
				<input id="previewFile" type="file" name="uploadFile" value="浏览" class="btn" onchange="writeFilePath()" accept="application/x-zip-compressed,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
				<input id="previewBtn" type="button" class="btn" value="浏览" class="btn" onclick = "preview()"/>
				<input id="importBtn" type="submit" class="btn" value="导入" class="btn"/>
		</form>
    </div>
    <div id="userListNoneDiv">
    	<h1>无数据</h1>
    </div>
    <div id="userListDiv">
    	<s:if test="#session.newSchools != null">
    	<input type="checkbox" onchange="checkAll()" id="checkAllInput"/>
		<label>全选/反选</label>
    	<input type="button" value="≤" class="btn1" id="firstPageBtn" onclick="firstPage()"/>
		<input type="button" value="＜" class="btn1" id="prevPageBtn" onclick="prevPage()" />
		<input type="button" value="＞" class="btn1" id="nextPageBtn" onclick="nextPage()" />
		<input type="button" value="≥" class="btn1" id="lastPageBtn" onclick="lastPage()" />
		<input type="text" value="1" class="page" id="nowPageInput" onblur="changePage()"/>
		<input type="button" value="跳转至" class="btn" onclick="jumpTo()"/>
		<input type="button" value="选择创建" class="btn" onclick="createSelect()"/>
		<input type="button" value="全部创建" class="btn" onclick="createAll()"/>
    	<label>&emsp;&emsp;共“${sessionScope.createSchoolTotalNum}”条</label>&emsp;&emsp;<label>共“${sessionScope.createSchoolPageNum}”页</label>
    	<table>
				<thead>
					<tr id="titleTr">
						<th>勾选</th>
						<th>序号</th>
						<th>驾校</th>
						<th>省份</th>
						<th>城市</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%int i= session.getAttribute("createSchoolNowPage") != null && ((Integer)session.getAttribute("createSchoolNowPage")) != 0 ? 0+((Integer)session.getAttribute("createSchoolNowPage")-1)*10 : 0; %>
					<s:iterator value="#session.newSchools" var= "u">
						<tr class="contentTr">
							<td><input class="userCheck" type="checkbox" /><input type="hidden" value="<s:property value="#u.createId" />" /></td>
							<td><%=++i%></td>
							<td><s:property value="#u.school_name" /></td>
							<td><s:property value="#u.school_province" /></td>
							<td><s:property value="#u.school_city" /></td>
							<td><s:property value="#u.school_state" /></td>
							<td>
								<a class="operate" href="transport/schoolManagementAction!createSchoolDetail?createId=<s:property value="#u.createId" />">详情</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</s:if>
    </div>
  </body>
</html>
