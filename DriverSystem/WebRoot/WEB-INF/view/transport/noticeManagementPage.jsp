<%@ page language="java" import="java.util.*,org.great.bean.*"
	pageEncoding="utf-8"%>
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

<title>查看公告界面</title>

<link rel="stylesheet" href="css/manager_css.css">

<script type="text/javascript" src="js/jsAddress.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css">
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.min.css">
<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
<style type="text/css">
	#noticeDiv{
		margin-left: 50px;
		margin-top: 10px;
	}
	.orderTh{
		cursor: pointer;
	}
</style>

<script type="text/javascript">


	 $(function() {
	 	var deleteMsg = "<%= request.getAttribute("deleteMsg")%>";
	 	if(deleteMsg != null && deleteMsg == "true" && deleteMsg != "null"){
	 		alert('删除成功！');
	 	}else if(deleteMsg != null && deleteMsg == "false" && deleteMsg != "null"){
	 		alert('删除失败！');
	 	}
	 	var noNotice = "<%= request.getAttribute("noNotice")%>";
	 	if(noNotice != null && noNotice == "true"){
	 		$("#userListDiv").css('display', 'none');
			$("#userListNoneDiv").css('display', 'block');
	 	}
	 	var transNoticeSearch = "<%=session.getAttribute("transNoticeSearch")%>";
	 	if(transNoticeSearch != null && transNoticeSearch != "null"){
	 		var theme = "<%=session.getAttribute("transNoticeSearch") != null ? ((TransNotice)session.getAttribute("transNoticeSearch")).getTrans_notice_theme() : null%>";
	 		if(theme != null && theme != "null"){
		 		$("#themeSearch").val(theme);
	 		}
	 		var date = "<%=session.getAttribute("transNoticeSearch") != null ? ((TransNotice)session.getAttribute("transNoticeSearch")).getTrans_notice_time() : null%>";
	 		if(date != null && date != "null"){
		 		$("#datepicker").val(date);
	 		}	
	 		var target = "<%=session.getAttribute("transNoticeSearch") != null ? ((TransNotice)session.getAttribute("transNoticeSearch")).getTrans_notice_target() : null %>";
	 		if(target != null && target != "null"){
		 		$("#searchSchool").val(target);
	 		}
	 		
	 		var province = "<%=session.getAttribute("transNoticeSearch") != null ? ((TransNotice)session.getAttribute("transNoticeSearch")).getTrans_notice_province() : null%>";
	 		if(province != null && province != "null"){
		 		$("#cmbProvince").val(province);
	 		}

	 		addressInit('cmbProvince', 'cmbCity', 'cmbArea');
	 		
	 		var city = "<%=session.getAttribute("transNoticeSearch") != null ? ((TransNotice)session.getAttribute("transNoticeSearch")).getTrans_notice_city() : null%>";
	 		if(province != null && province != "null"){
		 		$("#cmbCity").val(city);
	 		}
	 		
	 	}
	    $( "#datepicker" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	       dateFormat: 'yy-mm-dd'
	    }); 
	});
/*  做上一页下一页的判断 */
    function change(but){
      
      var changepage = $("#page").val();
      var allPage = $("#allpage").val();
      /* 跳转到某页 */
      if(but=='go'){
         if(1<=changepage && changepage<=allPage){
           window.location.href="transport/noticeView?changePage="+changepage;
         }else{
           	var nowPage = "<%=request.getAttribute("NowPage")%>";
           	$("#page").val(nowPage);
         }
      }else if(but=='check'){
         if(!(1<=changepage && changepage<=allPage)){
           var nowPage = "<%=request.getAttribute("NowPage")%>";
           	$("#page").val(nowPage);
         }
      }else if(but=='first'){
         var page = 1;
         
         window.location.href="transport/noticeView?changePage="+page;
      }else if(but=='before'){
         if(1<changepage){
           var page = changepage-1;
            window.location.href="transport/noticeView?changePage="+page;
         }
      }else if(but=='next'){
         if(changepage<allPage){
           var page = parseInt(changepage)+1;
            window.location.href="transport/noticeView?changePage="+page;
         }
      }else if(but=='last'){
         var page = allPage;
         window.location.href="transport/noticeView?changePage = "+page;
      }else if(but=='search'){
        var page = 1;
        window.location.href="transport/searchNotice?changePage = "+page;
       }else if(but=='selectAll'){
         var page = 1;
        window.location.href="transport/noticeView?changePage = "+page + "&showAll=true";
       }
    }
    
    /* 但删除某条公告时，做个弹窗 */
	function delect() {
		if (confirm("确定删除该记录？")) {

			return true;
		} else {

			return false;
		}
	}
	
	function checkAll(){
		var isAllCheck = $("#checkAllInput").prop("checked");
		var tags = document.getElementsByClassName("userCheck");
		for(var i=0; i<tags.length; i++){
			$(tags[i]).prop("checked", isAllCheck);
		}
	}
	function deleteSelect(){
		var tags = document.getElementsByClassName("userCheck");
		var count = 0;
		var noticeIds = "";
		for(var i=0; i<tags.length; i++){
			var isSelect = $(tags[i]).prop("checked");
			if(isSelect){
				count++;
				noticeIds += $(tags[i]).next().val() + ",";
			}
		}
		
		if(count == 0){
			alert('请先勾选账号');
			return;
		}
		if(noticeIds.endsWith(",")){
			noticeIds = noticeIds.substring(0, noticeIds.length- 1);
		}
		if(confirm('确认删除吗？')){
			window.location.href = "transport/deleteNotice?noticeIds=" + noticeIds;
		}
	}
	function orderList(obj){
		var changepage = $("#page").val();
		if(obj == 'default'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=defaultOrder";
		}else if(obj == 'theme'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=trans_notice_theme";
		}else if(obj == 'time'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=trans_notice_time";
		}else if(obj == 'province'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=trans_notice_province";
		}else if(obj == 'city'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=trans_notice_city";
		}else if(obj == 'target'){
			window.location.href="transport/noticeView?changePage="+changepage + "&noticeOrderType=trans_notice_target";
		}	
	
	}
</script>
</head>

<body>
	<div id="noticeDiv">
		<div id="searchDiv" style="margin-left: 10%;margin-top: 0px">
			<!-- 搜索用户的模块 -->

			<form action="transport/searchNotice?changePage=1" method="post"
				enctype="application/x-www-form-urlencoded">
				<div class="smallDiv">
					<label>公告主题：</label>
					<input name="transNotice.trans_notice_theme"
						placeholder="请输入公告主题" id="themeSearch"/>
				</div>
				<div class="smallDiv">
					<label>公告日期：</label>
					<input type="text" id="datepicker"
						name="transNotice.trans_notice_time" />
				</div>
				<div class="smallDiv">
					<label>公告的对象：</label>
					<select id="searchSchool" name="transNotice.trans_notice_target">
						<option>全部</option>
						<option>驾校</option>
						<option>学员</option>
					</select>
				</div>
				<div style="clear:left"></div>
				<div class="smallDiv">
					<label>公告省份：</label>
					<select id="cmbProvince" name="transNotice.trans_notice_province"></select>
				</div>
				<div class="smallDiv">
					<label>公告城市：</label>
					<select id="cmbCity" name="transNotice.trans_notice_city"></select>
				</div>
				<select id="cmbArea" name="cmbArea" style="display: none"></select>
				<script type="text/javascript">
					addressInit('cmbProvince', 'cmbCity', 'cmbArea');
				</script>
				<div class="smallDiv">
				<input type="submit" class="btn" value="搜索" />
				</div>
				<div class="smallDiv">
					<input type="button" id="selectAll" class="btn" onclick="change('selectAll')" value="显示全部"/>
				</div>
			</form>
		</div>
		
		<div style="clear:left"></div>
		<div id="userListNoneDiv" style="margin-left: 42%;margin-top: 18%;display: none">
	    	<h1 style="font-size: 36px;color:gray;">无数据</h1>
	    </div>
		<div id="userListDiv" style="margin-top: 10px;">
			<div>
				<input type="checkbox" onchange="checkAll()" id="checkAllInput" />全选/反选
				<input type="button" onclick="deleteSelect()" id="deleteSelect"
					value="批量删除" class="btn" />&emsp;
				<input type="button" id="first" class="btn1" onclick="change('first')" value="≤" title="首页">
				<input type="button" id="before" class="btn1" onclick="change('before')" value="＜" title="上一页">
				<input type="button" id="next" class="btn1" onclick="change('next')" value="＞" title="下一页">
				<input type="button" id="last" class="btn1" onclick="change('last')"  value="≥" title="尾页">
			    <input type="text" class="page" id="page" value="<%=request.getAttribute("NowPage")%>" onblur="change('check')">
			    <input type="button" id="go" class="btn" onclick="change('go')" value="跳转至">
					<label style="margin-left: 25%"> 共“<%=request.getAttribute("AllPage") %>”页</label ><label>共“<%=request.getAttribute("RowNum") %>”条记录</label>
				<input type="hidden" id="allpage" value="${requestScope.AllPage}" />
			</div>
			<div>
				<table>
				   <thead>
						<tr id="titleTr">
							<th>勾选</th>
							<th class="orderTh" title="点击按照默认排序" onclick="orderList('default')">序号</th>
							<th class="orderTh" title="点击按照主题排序" onclick="orderList('theme')">公告主题</th>
							<th class="orderTh" title="点击按照发布时间排序" onclick="orderList('time')">发布时间</th>
							<th class="orderTh" title="点击按照省份排序" onclick="orderList('province')">发布省份</th>
							<th class="orderTh" title="点击按照城市排序" onclick="orderList('city')">发布城市</th>
							<th class="orderTh" title="点击按照对象排序" onclick="orderList('target')">发布对象</th>
							<th>公告操作</th>
						</tr>
					</thead>
					
					<tbody>
						<%
							int i = 0 + ((Integer) request.getAttribute("NowPage") - 1) * 10;
						%>
						<s:iterator value="noticeList" var="n">
							<tr class="contentTr">
								<td><input class="userCheck" type="checkbox" /><input
									type="hidden"
									value="<s:property value="#n.trans_notice_id" />" /></td>
								<td><%=++i%></td>
								<td><s:property value="#n.trans_notice_theme" /></td>
								<td><s:property value="#n.trans_notice_time" /></td>
								<td><s:property value="#n.trans_notice_province" /></td>
								<td><s:property value="#n.trans_notice_city" /></td>
								<td><s:property value="#n.trans_notice_target" /></td>
								<td>
									<a href="transport/deleteNotice?transNotice.trans_notice_id=<s:property value="#n.trans_notice_id"/>&transNotice.trans_notice_target=<s:property value="#n.trans_notice_target"/>"
									onclick="return delect()">删&nbsp;除</a>&nbsp;&nbsp;
									<a href="transport/notinceParticulars?transNotice.trans_notice_id=<s:property value="#n.trans_notice_id"/>">详&nbsp;情</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
