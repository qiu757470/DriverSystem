<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>运管平台菜单界面</title>

<style type="text/css">
body,html{
	font-family: "微软雅黑";
}
a{
	color: #5B5B5B;	
	text-decoration: none;
}
a:linked {
	color: #5B5B5B;
	text-decoration: none;
}
a:active {
	color: #5B5B5B;
	text-decoration: none;
}

a:hover {
	color: #ADADAD;
	text-decoration: underline;
}
summary{
	font-size: 18px;
}
li{
	list-style: none;
}
</style>

<script type="text/javascript">
	function transUserLogout(){
		if(confirm("确认退出登录吗？")){
			parent.logout();
		}
	}

</script>
</head>

<body>
	<div style="margin-left: 20%">
	<details open="open">
		<summary>系统管理</summary>
		<ul>
			<li class="li"><a target="mainFrame" href="transport/managerManagementAction!showPage?nowPage=1">用户管理</a></li>
			<li class="li"><a href="transport/managerJurisdictionAction!showPage?nowPage=1" target="mainFrame">权限分配</a></li>
		</ul>
	</details>
	<details open="open">
		<summary>驾校管理</summary>
		<ul>
			<li class="li"><a target="mainFrame" href="transport/schoolVerifyAction!showPage?nowPage=1">驾校审核</a></li>
			<li class="li"><a target="mainFrame" href="transport/schoolManagementAction!showPage?nowPage=1">驾校管理</a></li>
		</ul>
	</details>
	<details open="open">
		<summary>运营信箱</summary>
		<ul>
			<li class="li"><a target="mainFrame" href="transport/mailAction!checkMailPage?nowPage=1">查看邮件</a></li>
		</ul>
	</details>
	<details open="open">
		<summary>公告管理</summary>
		<ul>
			<li class="li"><a target="mainFrame" href="transport/noticeView">查看公告</a></li>
			<li class="li"><a target="mainFrame" href="transport/noticeAnnounce">发布公告</a></li>
		</ul>
	</details>
	<details open="open">
		<summary>设置</summary>
		<ul>
			<li class="li"><a target="mainFrame" href="transport/settingAction!revisePassword">修改密码</a></li>
			<li class="li"><a target="mainFrame" href="transport/settingAction!revisePersonInfo">修改个人信息</a></li>
			<li class="li"><a target="mainFrame" href="transport/settingAction!about">关于</a></li>
			<li class="li"><a target="mainFrame" onclick="transUserLogout()">退出登录</a></li>
		</ul>
	</details>
	</div>
</body>
</html>
