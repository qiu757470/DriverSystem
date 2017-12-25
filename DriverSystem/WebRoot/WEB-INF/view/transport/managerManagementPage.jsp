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
<title>运营平台用户管理界面</title>
<link rel="stylesheet" type="text/css" href="css/manager_css.css">
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$("#firstPageBtn").attr('title','首页');
		$("#prevPageBtn").attr('title','上一页');
		$("#nextPageBtn").attr('title','下一页');
		$("#lastPageBtn").attr('title','尾页');
		var isEmptyUser = "<%=request.getAttribute("emptyUser")%>";
		if(isEmptyUser != null && isEmptyUser == "true"){
			$("#userListDiv").css('display', 'none');
			$("#userListNoneDiv").css('display', 'block');
			alert("当前系统中没有驾校管理员用户，请先创建用户");
		}
		var isEmptySchool = "<%=request.getAttribute("emptySchool")%>";
		if(isEmptySchool != null && isEmptySchool == "true"){
			if(confirm("当前系统中没有驾校，请先添加。\n是否跳转到添加驾校界面？")){
				$("#userListDiv").css('display', 'none');
				$("#userListNoneDiv").css('display', 'block');
				window.location.href = "transport/schoolManagementAction!createSchoolPage";
			}
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
		var searchUser = "<%= request.getAttribute("searchUser")%>";
		if(searchUser != null && searchUser != "" && searchUser != "null"){
			var acc = "<%= request.getAttribute("searchUser") != null ? ((ManagerSearch)request.getAttribute("searchUser")).getSchool_manager_account(): null%>";
			if(acc != null && acc != "" && acc != "null"){
				$("#searchAcc").val(acc);
			}
			
			var state = "<%= request.getAttribute("searchUser") != null ? ((ManagerSearch)request.getAttribute("searchUser")).getSchool_manager_state() : null%>";
			if(state != null && state != "" && state != "null"){
				$("#searchState").val(state);
			}else{
				$("#searchState").val("全部");
			}
			
			var province = "<%= request.getAttribute("searchUser") != null ? ((ManagerSearch)request.getAttribute("searchUser")).getSchool_province() : null %>";
			if(province != null && province != "" && province != "null"){
				$("#cmbProvince").val(province);
			}else{
				
				$("#cmbProvince").val("全部");
			}	
			
			addressInit('cmbProvince', 'cmbCity', 'cmbArea'); 
			
			var city = "<%= request.getAttribute("searchUser") != null ? ((ManagerSearch)request.getAttribute("searchUser")).getSchool_city() : null%>";
			if(city != null && city != "" && city != "null"){
				$("#cmbCity").val(city);
			}else{
				$("#cmbCity").val("全部");
			}
			changeSchool();
		}
	});
	function firstPage(){
		var nowPage = $("#nowPageInput").val();
		if(nowPage == 1){
			return;
		}
		window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
		return;
	}
	function prevPage(){
		var nowPage = $("#nowPageInput").val();
		if(nowPage <= 1){
			return;
		}
		nowPage--;
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage;
		return;
	}
	function nextPage(){
		var nowPage = $("#nowPageInput").val();
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage >= allPage){
			return;
		}
		nowPage++;
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage;
		return;
	}
	function lastPage(){
		var nowPage = $("#nowPageInput").val();
	
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage == allPage){
			return;
		}
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + allPage;
		return;
	}
	function jumpTo(){
		var nowPage = $("#nowPageInput").val();
		if(!nowPage.match("^[0-9]*$")) {
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
			return;
		}
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage < 1){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
			return;
		}else if(nowPage >= allPage + 1){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=" + allPage;
			return;
		}
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage;
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
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1&showAll=true";
			return;
		}
		var allPage = "<%= request.getAttribute("allPage")%>";
		if(nowPage < 1){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1&showAll=true";
			return;
		}else if(nowPage >= allPage + 1){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=" + allPage + "&showAll=true";
			return;
		}
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage  + "&showAll=true";
	}
	function deleteManager(tag){
		var state = $(tag).next().val();
		if(state == "运营中" || state == "审核通过"){
			alert('驾校运营中，无法删除用户');
			return false;
		}else{
			if(confirm('确认删除该用户吗？')){
				return true;		
			}
		}
		return false;
	}
	function deleteSelect(){
		var checks = document.getElementsByClassName("userCheck");
		var count = 0;
		var managerIds = "";
		for(var i = 0; i<checks.length; i++){
			var isCheck = $(checks[i]).prop("checked");
			if(isCheck){
				count++;
				var state = $(checks[i]).next().next().next().val();
				if(state == "运营中" || state == "审核通过"){
					alert('勾选的用户中包含运营中的驾校的管理员，请重新选择');
					return;
				}
				if(i < checks.length - 1){
					managerIds += $(checks[i]).next().next().val() + ",";
				}else{
					managerIds += $(checks[i]).next().next().val();
				}
			}
		}
		if(count == 0){
			alert("请先勾选账号");
			return;
		}
		if(confirm('确认删除吗？')){
			window.location.href = "transport/managerManagementAction!deleteSelectManager?managerIds=" + managerIds;	
		}
	}
	function createManager(){
		window.location.href = "transport/managerManagementAction!createManagerPage";
	}
	function createBatch(){
		window.location.href = "transport/managerManagementAction!createBatchPage";
	
	}
	function changeSchool(){
		var province = $("#cmbProvince").val();
		var city = $("#cmbCity").val();
		$.ajax({
			type:"post",
			url:"ajax/changeSchool",
			data:{
			ajaxProvince:province,
			ajaxCity:city
			},
			dataType: "json",
			success : function(data) {
				var msg = eval(data);
				if(msg != null && msg != ""){
					var html = "<option>全部</option>"
					for(var i=0; i<msg.length; i++){
						html += "<option>" + msg[i] + "</option>";
					}
					$("#searchSchool").html(html);
				}else{
					$("#searchSchool").html("<option selected='selected'>全部</option>");
				}
				var school_name = "<%= request.getAttribute("searchUser") != null ? ((ManagerSearch)request.getAttribute("searchUser")).getSchool_name(): null%>";
				if(school_name != null && school_name != "" && school_name != "null"){
					var $options = $("#searchSchool").children();
					var count = 0;
					for(var i=0; i<$options.length; i++){
						if($options[i].innerText == school_name){
							$options[i].setAttribute("selected", "selected");
							break;
						}else{
							count++;
						}
					}
					if(count == $options.length){
						$("#searchSchool").val("全部");
					}
				}else{
					$("#searchSchool").val("全部");
				}
			},
			error : function() {
				alert('请求有误');
			}
		
		});
	}
	function orderByAccount(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage + "&orderType=school_manager_account";
	}
	function orderByName(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage + "&orderType=school_manager_name";
	}
	function orderBySchool(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage + "&orderType=school_id";
	}
	function orderByState(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage + "&orderType=school_manager_state";
	}
	function orderByDefault(){
		var nowPage = $("#nowPageInput").val();
		window.location.href = "transport/managerManagementAction!showPage?nowPage=" + nowPage + "&orderType=defaultOrder";
	}
</script>
</head>

<body>
	<div id="searchDiv"><!-- 搜索用户的模块 -->
		<form action="transport/managerManagementAction!searchUser" method="post" enctype="application/x-www-form-urlencoded" >
			<div id="userNameDiv" class="smallDiv">
				<label>账号：</label> <input type="text" placeholder="请输入账号"
					name="searchUser.school_manager_account" id="searchAcc"/>
			</div>
			<div id="userProvinceDiv" class="smallDiv">
				<label>省份：</label> <select id="cmbProvince" name="searchUser.school_province" onclick="changeSchool()"></select>
			</div>
			<div id="userCityDiv" class="smallDiv">
				<label>城市：</label> <select id="cmbCity" name="searchUser.school_city" onclick="changeSchool()"></select>
				<select id="cmbArea" name="cmbArea" style="display: none"></select>  
			</div>
			<div style="clear:left"></div>
			<div id="userStateDiv" class="smallDiv">
				<label>驾校：</label> <select id="searchSchool" name="searchUser.school_name" >
					<option>全部</option>
				</select>
			</div>
			<div id="userStateDiv" class="smallDiv">
				<label>账号状态：</label> 
				<select name="searchUser.school_manager_state" id="searchState">
					<option>全部</option>
					<option>可用</option>
					<option>冻结</option>
				</select>
			</div>
			<div id="searchSubmitDiv" class="smallDiv">
				<input type="submit" value="搜  索" class="btn"/>
			</div>
			<div class="smallDiv1">
				<input type="button" value="显示全部" class="btn" onclick="showAll()" />
			</div> 
		</form>
		<script type="text/javascript" src="<%=basePath%>js/jsAddress.js"></script> 
		<script type="text/javascript">  
                 addressInit('cmbProvince', 'cmbCity', 'cmbArea');  
        </script>  
	</div>
	
	<div style="clear:left"></div>
	<div id="userListNoneDiv" style="margin-left: 42%;margin-top: 18%;display: none">
    	<h1 style="font-size: 36px;color:gray;">无数据</h1>
    </div>
	<div id="userListDiv"><!-- 用户列表的模块 -->
		<div id="listFunctionDiv"><!-- 全选分页的模块 -->
				<input type="checkbox" onchange="checkAll()" id="checkAllInput"/>
				<label>全选/反选</label>
				<input type="button" value="创建用户" class="btn" onclick="createManager()"/>
				<input type="button" value="批量创建" class="btn" onclick="createBatch()"/>
				<input type="button" value="批量删除" class="btn" onclick="deleteSelect()"/>&emsp;
				<input type="button" value="≤" class="btn1" id="firstPageBtn" onclick="firstPage()"/>
				<input type="button" value="＜" class="btn1" id="prevPageBtn" onclick="prevPage()" />
				<input type="button" value="＞" class="btn1" id="nextPageBtn" onclick="nextPage()" />
				<input type="button" value="≥" class="btn1" id="lastPageBtn" onclick="lastPage()" />
				<input type="text" value="1" class="page" id="nowPageInput" onblur="changePage()"/>
				<input type="button" value="跳转至" class="btn" onclick="jumpTo()"/>
				<label >&emsp;共“<s:property value="allPage" />”页</label>
				<label style="margin-left: 3%">总共有“<s:property value="recordNum" />”条记录</label>
		</div>
		
		<div id="userTableDiv"><!-- 列表表格模块 -->
			<table>
				<thead>
					<tr id="titleTr">
						<th>勾选</th>
						<th title="点击按照默认排序" class="orderTh" onclick="orderByDefault()">序号</th>
						<th title="点击按照账号排序" class="orderTh" onclick="orderByAccount()">账号</th>
						<th title="点击按照姓名排序" class="orderTh" onclick="orderByName()">姓名</th>
						<th title="点击按照驾校排序" class="orderTh" onclick="orderBySchool()">所属驾校</th>
						<th title="点击按照状态排序" class="orderTh" onclick="orderByState()">状态</th>
						<th>用户操作</th>
					</tr>
				</thead>
				<tbody>
					<%int i=0+((Integer)request.getAttribute("nowPage")-1)*10; %>
					<s:iterator value="managerUsers" var= "u">
						<tr class="contentTr">
							<td><input class="userCheck" type="checkbox" /><input type="hidden" value="<s:property value="#u.school_manager_account" />" /><input type="hidden" value="<s:property value="#u.school_manager_id" />" /><input type="hidden" value="<s:property value="#u.school.school_state" />" /></td>
							<td><%=++i%></td>
							<td><s:property value="#u.school_manager_account" /> </td>
							<td><s:property value="#u.school_manager_name" /> </td>
							<td title="驾校所属地：<s:property value="#u.school.school_province" />&emsp;<s:property value="#u.school.school_city" />&emsp;<s:property value="#u.school.school_address" />&#10;驾校状态：<s:property value="#u.school.school_state" />"><s:property value="#u.school.school_name" /></td>
							<td><s:property value="#u.school_manager_state" /> </td>
							<td>
								<a class="operate" href="transport/managerManagementAction!deleteManager?managerId=<s:property value="#u.school_manager_id" />" onclick="return deleteManager(this)">删除</a>
								<input type="hidden" value="<s:property value="#u.school.school_state" />" />
								<a class="operate" href="transport/managerManagementAction!alterManagerPage?managerId=<s:property value="#u.school_manager_id" />"  >修改</a>
								<a class="operate" href="transport/managerManagementAction!managerDetailPage?managerId=<s:property value="#u.school_manager_id" />"  >详情</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>

</body>

</html>
