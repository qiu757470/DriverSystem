<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>



<base href="<%=basePath%>">

<title>My JSP 'menu.jsp' starting page</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<!-- <link href="css/OSTA.css" rel="stylesheet" type="text/css" /> -->
<script src="js/jquery.js"></script>
<script type="text/javascript">


	function checknote(tag) {
		var trans_notice_id = $(tag).parent().prev().prev().prev().html();

		$(tag).attr("href", "driveSchool/Notice!checkNoticed?trans_notice_id=" + trans_notice_id);

		return true;


	}

	function F_Open_dialog() {
		document.getElementById("btn_file").click();
	}


	function turn(tag) {
		var a = $(tag).attr("id");

		if (a == 1) {

			window.location.href = "driveSchool/TeacherAction!firstPage"


		} else if (a == 2) {
			window.location.href = "driveSchool/TeacherAction!beforePage"

		} else if (a == 3) {
			window.location.href = "driveSchool/TeacherAction!afterPage"
		} else if (a == 4) {
			window.location.href = "driveSchool/TeacherAction!lastPage"
		}

	}
	function upload() {
		var file = document.getElementById('some');

		if (file.value == "") {
			alert("请选择您需要上传的文件！");
			return false;
		} else {
			document.form1.submit();
		}

	}
</script>

</head>
<style>
a {
	text-decoration: none;
}
</style>
<body>
	

	<p style="float: left;">共 ${tnum} 条记录</p>

	
	<div style="float: left;">第 ${page} 页</div>

	



	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>已发布公告
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-setting"><i
						class="halflings-icon white wrench"></i></a> <a href="#"
						class="btn-minimize"><i
						class="halflings-icon white chevron-up"></i></a> <a href="#"
						class="btn-close"><i class="halflings-icon white remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class=" table table-hover">
					<tr>
						<th>序号</th>
						<th>主题</th>
						<th>时间</th>
						<th>操作</th>
					</tr>

					<%
							int i = 0;
						%>
					<s:iterator value="transNoticeList" var="u">

						<tr>
							<td value="#u.trans_notice_id"><%=++i%></td>

							<td name="trans_notice_id" style="display:none"><s:property
									value="#u.trans_notice_id" /></td>
							<td><s:property value="#u.trans_notice_theme" /></td>


							<td><s:property value="#u.trans_notice_time" /></td>





							<td><a href="driveSchool/delectUser" target="u"
								onclick="return checknote(this)">查看详情</a>
						</tr>
					</s:iterator>
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>
