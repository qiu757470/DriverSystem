<%@ page language="java"
	import="java.util.*,org.great.bean.StudentSearch" pageEncoding="UTF-8"%>
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

<title>查看邮件页面</title>
<link rel="stylesheet" type="text/css" href="css/manager_css.css">
<style type="text/css">

</style>

<script src="js/jquery.js"></script>
<script type="text/javascript">
		$(function(){
			//邮件为空返回消息
			var emptyMail = "<%= request.getAttribute("emptyMail")%>";
			if(emptyMail != null && (emptyMail == true || emptyMail == "true")){
				if(confirm("当前没有邮件，是否返回？")){
					$("#userListDiv").css('display', 'none');
					$("#userListNoneDiv").css('display', 'block');
					window.location.href = "transport/transportMain";
				}
			}
			var deleteSuccess = "<%= request.getAttribute("deleteSuccess")%>";
			if(deleteSuccess != null && (deleteSuccess == true || deleteSuccess == "true")){
				alert("删除成功！");
			}
			var searchErrorMsg = "<%= request.getAttribute("searchErrorMsg")%>";
			if(searchErrorMsg != null && searchErrorMsg != "" && searchErrorMsg != "null"){
				alert(searchErrorMsg);
			}
			//当前页的输入栏显示当前页数
			var nowPage = "<%= request.getAttribute("nowPage")%>";
			$("#nowPageInput").val(nowPage);
			//搜索的学员
			var searchUser = "<%= request.getAttribute("searchUser")%>";
			if(searchUser != null && searchUser != "" && searchUser != "null"){
				//学员准考证
				var id = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getStudent_identification(): null%>";
				if(id != null && id != "" && id != "null"){
					$("#searchIdentification").val(id);
				}
				var name = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getStudent_name() : null%>";
				if(name != null && name != "" && name != "null"){
					$("#searchName").val(name);
				}
				
				//驾校名称
				var school_name = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getSchool_name(): null%>";
				if(school_name != null && school_name != "" && school_name != "null"){
					$("#searchSchool").val(school_name);
				}else{
					$("#searchSchool").val("全部");
				}
				//学员省份
				var province = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getSchool_province() : null %>";
				if(province != null && province != "" && province != "null"){
					$("#cmbProvince").val(province);
				}else{
					$("#cmbProvince").val("全部");
				}
				addressInit('cmbProvince', 'cmbCity', 'cmbArea'); 
				//驾校城市
				var city = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getSchool_city() : null%>";
				if(city != null && city != "" && city != "null"){
					$("#cmbCity").val(city);
				}else{
					$("#cmbCity").val("全部");
				}
				changeSchool();
				//邮件类型
				var type = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getMail_type() : null%>";
				if(type != null && type != "" && type != "null"){
					$("#mailType").val(type);
				}else{
					$("#mailType").val("全部");
				}
				var theme = "<%= request.getAttribute("searchUser") != null ? ((StudentSearch)request.getAttribute("searchUser")).getMail_theme() : null%>";
				if(theme != null && theme != "" && theme != "null"){
					$("#mailTheme").val(theme);
				}else{
					$("#mailTheme").val("");
				}
			}
		});
		function firstPage(){
		var nowPage = $("#nowPageInput").val();
		if(nowPage == 1){
			return;
		}
		window.location.href = "transport/mailAction!checkMailPage?nowPage=1";
		return;
	}
		function prevPage(){
			var nowPage = $("#nowPageInput").val();
			if(nowPage <= 1){
				return;
			}
			nowPage--;
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage;
			return;
		}
		function nextPage(){
			var nowPage = $("#nowPageInput").val();
			var allPage = "<%= request.getAttribute("allPage")%>";
			if(nowPage >= allPage){
				return;
			}
			nowPage++;
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage;
			return;
		}
		function lastPage(){
			var nowPage = $("#nowPageInput").val();
		
			var allPage = "<%= request.getAttribute("allPage")%>";
			if(nowPage == allPage){
				return;
			}
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + allPage;
			return;
		}
		function jumpTo(){
			var nowPage = $("#nowPageInput").val();
			if(!nowPage.match("^[0-9]*$")) {
				window.location.href = "transport/mailAction!checkMailPage?nowPage=1";
				return;
			}
			var allPage = "<%= request.getAttribute("allPage")%>";
			if(nowPage < 1){
				window.location.href = "transport/mailAction!checkMailPage?nowPage=1";
				return;
			}else if(nowPage >= allPage + 1){
				window.location.href = "transport/mailAction!checkMailPage?nowPage=" + allPage;
				return;
			}
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage;
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
			var checks = document.getElementsByClassName("mailCheck");
			for(var i = 0; i<checks.length; i++){
				$check = $(checks[i]);
				$check.prop("checked",isCheck);
			}
		}
		function showAll(){
			var nowPage = $("#nowPageInput").val();
			if(!nowPage.match("^[0-9]*$")) {
				window.location.href = "transport/mailAction!checkMailPage?nowPage=1&showAll=true";
				return;
			}
			var allPage = "<%= request.getAttribute("allPage")%>";
			if(nowPage < 1){
				window.location.href = "transport/mailAction!checkMailPage?nowPage=1&showAll=true";
				return;
			}else if(nowPage >= allPage + 1){
				window.location.href = "transport/mailAction!checkMailPage?nowPage=" + allPage + "&showAll=true";
				return;
			}
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage  + "&showAll=true";
		}
		function deleteSelect(){
			var checks = document.getElementsByClassName("mailCheck");
			var deleteIds = "";
			for(var i = 0; i<checks.length; i++){
				var isCheck = $(checks[i]).prop("checked");
				if(isCheck == true || isCheck == "true"){
					if(i < checks.length - 1){
						deleteIds += $(checks[i]).next().val() + ",";
					}else{
						deleteIds += $(checks[i]).next().val();
					}
				}
			}
			if(confirm('确认删除吗？')){
				window.location.href = "transport/mailAction!deleteMails?mailIds=" + deleteIds;
			}
		}
		function changeSchool(){
			var province = $("#cmbProvince").val();
			var city = $("#cmbCity").val();
			/* alert(province);
			alert(city); */
			$.ajax({
				type:"post",
				url:"<%=basePath%>ajax/changeSchool",
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
					var school_name = "<%=request.getAttribute("searchUser") != null
					? ((StudentSearch) request.getAttribute("searchUser")).getSchool_name()
					: null%>";
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
		function orderByDefault(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=defaultOrder";
		}
		function orderByTheme(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=mail_theme";
		}
		function orderByTime(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=mail_time";
		}
		function orderByStudent(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=student_id";
		}
		function orderBySchool(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=school_id";
		}
		function orderByType(){
			var nowPage = $("#nowPageInput").val();
			window.location.href = "transport/mailAction!checkMailPage?nowPage=" + nowPage + "&orderType=mail_type";
		}
	</script>
</head>

<body>
	<div id="searchDiv" style="margin-left: 10%;">
		<!-- 搜索用户的模块 -->
		<form action="transport/mailAction!searchMail" method="post"
			enctype="application/x-www-form-urlencoded">
			<div class="smallDiv">
				<label>省份：</label> <select id="cmbProvince"
					name="searchUser.school_province" onclick="changeSchool()"></select>
			</div>
			<div class="smallDiv">
				<label>城市：</label> <select id="cmbCity"
					name="searchUser.school_city" onclick="changeSchool()"></select> <select
					id="cmbArea" name="cmbArea" style="display: none"></select>
			</div>
			<div class="smallDiv">
				<label>驾校：</label> <select id="searchSchool"
					name="searchUser.school_name">
					<option>全部</option>
				</select>
			</div>
			<div class="smallDiv">
				<label>邮件类型：</label> <select id="mailType"
					name="searchUser.mail_type">
					<option>全部</option>
					<option>发送</option>
					<option>回复</option>
				</select>
			</div>
			<div style="clear:left"></div>
			<div id="mailThemeDiv" class="smallDiv">
				<label>邮件主题：</label> <input type="text" placeholder="输入邮件主题"
					name="searchUser.mail_theme" id="mailTheme" class="text"/>
			</div>
			<div id="userIdentificationDIv" class="smallDiv">
				<label>准考证号：</label> <input type="text" placeholder="输入学员准考证号"
					name="searchUser.student_identification" id="searchIdentification" class="text" />
			</div>
			<div id="userNameDiv" class="smallDiv">
				<label>学员姓名：</label> <input type="text" placeholder="输入学员姓名"
					name="searchUser.student_name" id="searchName" class="text"/>
			</div>
			<div id="searchSubmitDiv" class="smallDiv">
				<input type="submit" value="搜  索" class="btn" />
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
	<div id="userListDiv">
		<div id="listFunctionDiv">
			<!-- 全选分页的模块 -->
			<input type="checkbox" onchange="checkAll()" id="checkAllInput" /> <label>全选/反选</label>
			<input type="button" value="批量删除" class="btn" onclick="deleteSelect()" />&emsp;
			<input type="button" value="≤" class="btn1" id="firstPageBtn" title="首页"
				onclick="firstPage()" /> <input type="button" value="＜" class="btn1" title="上一页"
				id="prevPageBtn" onclick="prevPage()" /> <input type="button"
				value="＞" class="btn1" id="nextPageBtn" onclick="nextPage()" title="下一页"/> <input
				type="button" value="≥" class="btn1" id="lastPageBtn" title="尾页"
				onclick="lastPage()" /> <input type="text" value="1" class="page" 
				id="nowPageInput" onblur="changePage()" /> <input type="button"
				value="跳转至" class="btn" onclick="jumpTo()" /> <label>&emsp;共“<s:property
					value="allPage" />”页
			</label> <label style="margin-left: 20%">总共有“<s:property
					value="recordNum" />”条记录
			</label>
		</div>
	
		<div id="emailDiv">
			<table id="emailTable">
				<thead>
					<tr id="titleTr">
						<th>勾选</th>
						<th title="点击按照默认排序" class="orderTh" onclick="orderByDefault()">序号</th>
						<th title="点击按照主题排序" class="orderTh" onclick="orderByTheme()">邮件主题</th>
						<th title="点击按照发送时间排序" class="orderTh" onclick="orderByTime()">发送时间</th>
						<th title="点击按照发送人排序" class="orderTh" onclick="orderByStudent()">发送人</th>
						<th title="点击按照驾校排序" class="orderTh" onclick="orderBySchool()">所属驾校</th>
						<th title="点击按照类型排序" class="orderTh" onclick="orderByType()">类型</th>
						<th>邮件操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 0 + ((Integer) request.getAttribute("nowPage") - 1) * 10;
					%>
					<s:iterator value="mailList" var="m">
						<tr class="contentTr">
							<td><input class="mailCheck" type="checkbox" /><input
								type="hidden" value="<s:property value="#m.mail_id" />" /></td>
							<td><%=++i%></td>
							<td><s:property value="#m.mail_theme" /></td>
							<td><s:property value="#m.mail_time" /></td>
							<td
								title="身份证号：<s:property value="#m.student.student_identification" />"><s:property
									value="#m.student.student_name" /></td>
							<td
								title="驾校所属地：<s:property value="#m.student.school.school_province" />&emsp;<s:property value="#m.student.school.school_city" />&emsp;<s:property value="#m.student.school.school_address" />&#10;驾校状态：<s:property value="#m.student.school.school_state" />"><s:property
									value="#m.student.school.school_name" /></td>
							<td><s:property value="#m.mail_type" /></td>
							<td><a class="operate"
								href="transport/mailAction!deleteMail?mailId=<s:property value="#m.mail_id" />"
								style="cursor: pointer;" onclick="return confirm('确认删除吗？')">删除</a>
								<a class="operate"
								href="transport/mailAction!mailDetail?mailId=<s:property value="#m.mail_id" />"
								style="cursor: pointer;">详情</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
