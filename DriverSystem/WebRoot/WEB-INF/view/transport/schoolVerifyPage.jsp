<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">
<title>运营平台驾校管理界面</title>
<link rel="stylesheet" type="text/css" href="css/manager_css.css">
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$("#firstPageBtn").attr('title','首页');
		$("#prevPageBtn").attr('title','上一页');
		$("#nextPageBtn").attr('title','下一页');
		$("#lastPageBtn").attr('title','尾页');
		var isEmptySchool = "<%=request.getAttribute("emptySchool")%>";
		if(isEmptySchool != null && isEmptySchool == "true"){
			$("#userListDiv").css('display', 'none');
			$("#userListNoneDiv").css('display', 'block');
			if(confirm("当前系统中没有驾校，请先添加。\n是否跳转到添加驾校界面？")){
				window.location.href = "transport/schoolManagementAction!createSchoolPage";
			}
		}
		emptyDetail
		var emptyDetail = "<%=request.getAttribute("emptyDetail")%>";
		if(emptyDetail != null && emptyDetail == "true"){
			alert('该驾校无数据，无法审核');
		}
		var noUnverifySchool = "<%=session.getAttribute("noUnverifySchool")%>";
		if(noUnverifySchool != null && (noUnverifySchool == 'true' || noUnverifySchool == true)){
			$("#userListDiv").css('display', 'none');
			$("#userListNoneDiv").css('display', 'block');
		}else{
			$("#userListDiv").css('display', 'block');
			$("#userListNoneDiv").css('display', 'none');
		}
		var deleteSuccess = "<%=request.getAttribute("deleteSuccess")%>";
		if(deleteSuccess != null && (deleteSuccess == "true" || deleteSuccess == true)){
			alert("删除成功！");
		}else if(deleteSuccess != null && (deleteSuccess == "false" || deleteSuccess == false)){
			alert("删除失败！");
		}
		var nowPage = "<%= request.getAttribute("nowPage")%>";
		$("#nowPageInput").val(nowPage);
		var searchErrorMsg = "<%= request.getAttribute("searchErrorMsg")%>";
		if(searchErrorMsg != null && searchErrorMsg != "" && searchErrorMsg != "null"){
			alert(searchErrorMsg);
		}
		var searchSchool = "<%= request.getAttribute("searchSchool")%>";
		if(searchSchool != null && searchSchool != "" && searchSchool != "null"){
			var school_name = "<%= request.getAttribute("searchSchool") != null ? ((SchoolSearch)request.getAttribute("searchSchool")).getSchool_name(): null%>";
			if(school_name != null && school_name != "" && school_name != "null"){
				$("#searchName").val(school_name);
			}
			
			var state = "<%= request.getAttribute("searchSchool") != null ? ((SchoolSearch)request.getAttribute("searchSchool")).getSchool_state() : null%>";
			if(state != null && state != "" && state != "null"){
				$("#searchState").val(state);
			}else{
				$("#searchState").val("全部");
			}
			
			var province = "<%= request.getAttribute("searchSchool") != null ? ((SchoolSearch)request.getAttribute("searchSchool")).getSchool_province() : null %>";
			if(province != null && province != "" && province != "null"){
				$("#cmbProvince").val(province);
			}else{
				
				$("#cmbProvince").val("全部");
			}	
			
			addressInit('cmbProvince', 'cmbCity', 'cmbArea'); 
			
			var city = "<%= request.getAttribute("searchSchool") != null ? ((SchoolSearch)request.getAttribute("searchSchool")).getSchool_city() : null%>";
			if(city != null && city != "" && city != "null"){
				$("#cmbCity").val(city);
			}else{
				$("#cmbCity").val("全部");
			}
		}
	});
	function firstPage(){
		var nowPage = $("#nowPageInput").val();
		if(nowPage == 1){
			return;
		}
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1";
		return;
	}
	function prevPage(){
		var nowPage = $("#nowPageInput").val();
		if(nowPage <= 1){
			return;
		}
		nowPage--;
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage;
		return;
	}
	function nextPage(){
		var nowPage = $("#nowPageInput").val();
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage >= allPage){
			return;
		}
		nowPage++;
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage;
		return;
	}
	function lastPage(){
		var nowPage = $("#nowPageInput").val();
	
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage == allPage){
			return;
		}
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + allPage;
		return;
	}
	function jumpTo(){
		var nowPage = $("#nowPageInput").val();
		if(!nowPage.match("^[0-9]*$")) {
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1";
			return;
		}
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage < 1){
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1";
			return;
		}else if(nowPage >= allPage + 1){
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + allPage;
			return;
		}
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage;
	}
	function changePage(){
		var nowPage = $("#nowPageInput").val();
		if(!nowPage.match("^[0-9]*$")) {
			$("#nowPageInput").val("1");
			return;
		}
		var allPage = "<%= request.getAttribute("allPage")%>";
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
	function showAll(){
		var nowPage = $("#nowPageInput").val();
		if(!nowPage.match("^[0-9]*$")) {
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1&showAll=true";
			return;
		}
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage < 1){
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=1&showAll=true";
			return;
		}else if(nowPage >= allPage + 1){
			window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + allPage + "&showAll=true";
			return;
		}
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage  + "&showAll=true";
	}
	function deleteSchool(tag){
		var state = $(tag).next().val();
		if(state == "运营中"){
			alert('驾校运营中，无法删除');
			return false;
		}else{
			if(confirm('确认删除该驾校吗？')){
				return true;		
			}
		}
		return false;
	}
	function deleteSelect(){
		var checks = document.getElementsByClassName("userCheck");
		var count = 0;
		var schoolIds = "";
		for(var i = 0; i<checks.length; i++){
			var isCheck = $(checks[i]).prop("checked");
			if(isCheck){
				count++;
				var state = $(checks[i]).next().next().next().val();
				if(state == "运营中"){
					alert('勾选的驾校中有运营中的驾校，请重新勾选');
					return;
				}
				if(i < checks.length - 1){
					schoolIds += $(checks[i]).next().val() + ",";
				}else{
					schoolIds += $(checks[i]).next().val();
				}
			}
		}
		if(count == 0){
			alert("请先勾选账号");
			return;
		}
		if(confirm('确认删除吗？')){
			window.location.href = "transport/schoolVerifyAction!deleteSelectSchool?schoolIds=" + schoolIds;	
		}
	}
	function createSchool(){
		window.location.href = "transport/schoolVerifyAction!createSchoolPage";
	}
	function createBatch(){
		window.location.href = "transport/schoolVerifyAction!createBatchPage";
	
	}
	function orderByName(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage + "&orderType=school_name";
	}
	function orderByProvince(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage + "&orderType=school_province";
	}
	function orderByCity(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage + "&orderType=school_city";
	}
	function orderByState(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage + "&orderType=school_state";
	}
	function orderByDefault(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/schoolVerifyAction!showPage?nowPage=" + nowPage + "&orderType=defaultOrder";
	}
</script>
</head>

<body>
	<div id="searchDiv" style="margin-left: 10%"><!-- 搜索用户的模块 -->
		<form action="transport/schoolVerifyAction!searchSchool" method="post" enctype="application/x-www-form-urlencoded" >
			<div id="schoolNameDiv" class="smallDiv">
				<label>驾校名：</label> <input type="text" placeholder="请输入驾校名称"
					name="searchSchool.school_name" id="searchName"/>
			</div>
			<div id="schoolProvinceDiv" class="smallDiv">
				<label>省份：</label> <select id="cmbProvince" name="searchSchool.school_province"></select>
			</div>
			<div id="schoolCityDiv" class="smallDiv">
				<label>城市：</label> <select id="cmbCity" name="searchSchool.school_city"></select>
				<select id="cmbArea" name="cmbArea" style="display: none"></select>  
			</div>
			<div id="searchSubmitDiv" class="smallDiv">
				<input type="submit" value="搜  索" class="btn"/>
			</div>
			<div class="smallDiv">
				<input type="button" value="显示全部" class="btn" onclick="showAll()"/>
			</div> 
		</form>
		<script type="text/javascript" src="<%=basePath%>js/jsAddress.js"></script> 
		<script type="text/javascript">  
                 addressInit('cmbProvince', 'cmbCity', 'cmbArea');  
        </script>  
	</div>
	
	<div style="clear:left"></div>
	<div id="userListNoneDiv" style="margin-left: 42%;margin-top: 18%;">
    	<h1 style="font-size: 36px;color:gray;">无数据</h1>
    </div>
	<div id="userListDiv"><!-- 用户列表的模块 -->
		<div id="listFunctionDiv"><!-- 全选分页的模块 -->
				<input type="checkbox" onchange="checkAll()" id="checkAllInput"/>
				<label>全选/反选</label>&emsp;&emsp;
				<input type="button" value="≤" class="btn1" id="firstPageBtn" onclick="firstPage()"/>
				<input type="button" value="＜" class="btn1" id="prevPageBtn" onclick="prevPage()" />
				<input type="button" value="＞" class="btn1" id="nextPageBtn" onclick="nextPage()" />
				<input type="button" value="≥" class="btn1" id="lastPageBtn" onclick="lastPage()" />
				<input type="text" value="1" class="page" id="nowPageInput" onblur="changePage()"/>
				<input type="button" value="跳转至" class="btn" onclick="jumpTo()"/>
				<label style="margin-left: 25%;">共“<s:property value="allPage" />”页</label>
				<label style="margin-left: 3%">总共有“<s:property value="recordNum" />”条记录</label>
		</div>
		
		<div id="userTableDiv"><!-- 列表表格模块 -->
			<table>
				<thead>
					<tr id="titleTr">
						<th>勾选</th>
						<th title="点击按照默认排序" class="orderTh" onclick="orderByDefault()">序号</th>
						<th title="点击按照账号排序" class="orderTh" onclick="orderByName()">驾校</th>
						<th title="点击按照姓名排序" class="orderTh" onclick="orderByProvince()">省份</th>
						<th title="点击按照驾校排序" class="orderTh" onclick="orderByCity()">城市</th>
						<th title="点击按照状态排序" class="orderTh" onclick="orderByState()">状态</th>
						<th>驾校操作</th>
					</tr>
				</thead>
				<tbody>
					<%int i=0+((Integer)request.getAttribute("nowPage")-1)*10; %>
					<s:iterator value="schoolList" var= "u">
						<tr class="contentTr">
							<td><input class="userCheck" type="checkbox" /><input type="hidden" value="<s:property value="#u.school_id" />" /><input type="hidden" value="<s:property value="#u.school_state" />" /></td>
							<td><%=++i%></td>
							<td title="驾校id：<s:property value="#u.school_id" />"><s:property value="#u.school_name" /></td>
							<td><s:property value="#u.school_province" /></td>
							<td><s:property value="#u.school_city" /></td>
							<td><s:property value="#u.school_state" /></td>
							<td>
								<a class="operate" href="transport/schoolVerifyAction!verifySchoolDetailPage?schoolId=<s:property value="#u.school_id" />" >审核</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>

</body>

</html>
