<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'sendMail.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- start: CSS -->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<!-- end: CSS -->


<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->
	
<!-- start: Favicon -->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- end: Favicon -->
<script type="text/javascript">


	var timer;
	function start() {
		var t = new Date();
		var y = t.getFullYear();
		var mon = t.getMonth() + 1;
		var d = t.getDate();
		var h = check(t.getHours());
		var mi = check(t.getMinutes());
		var s = check(t.getSeconds());
		if (mon < 10)
			mon = "0" + mon;
		document.getElementById("time").innerHTML = y + "年" + mon + "月" + d + "日" + h + ":" + mi + ":" + s;

		document.getElementById("timeHidden").value = y + "" + mon + "" + d + "" + h + "" + mi + "" + s;

		timer = setTimeout('start()', 1000);
	}

	function check(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
	window.onload = function() {
		start();
	}
	function msg() {
	alert("发布成功");
	}
</script>
</head>

<body>






	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>发送邮件
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-setting"><i
						class="halflings-icon white wrench"></i></a> <a href="#"
						class="btn-minimize"><i
						class="halflings-icon white chevron-up"></i></a> <a href="#"
						class="btn-close"><i class="halflings-icon white remove"></i></a>
				</div>
			</div>
			<div class="box-content" style="float:left">
				<form class="form-horizontal"
					action="driveSchool/Notice!insertNotice" method="post">
					<fieldset>
						<div class="control-group">
							
						</div>
						<div class="control-group">
							<label class="control-label" for="date01">发送日期</label>
							<div class="controls">
								<label id="time"></label> <input type="hidden"
									name="trans_notice_time" id="timeHidden">
							</div>

						</div>

						<div class="control-group">
							<label class="control-label">公告主题</label>
							<div class="controls">

								<input name="trans_notice_theme" type="text"  class="form-control" required>
							</div>

						</div>
						<div class="control-group hidden-phone">
							<label class="control-label" for="textarea2" >内容</label>
							<div class="controls">
								<textarea class="cleditor" id="textarea2" rows="3" required
									name="trans_notice_content" 
									oninvalid="setCustomValidity('请填入发送邮件内容');"></textarea>
							</div>

						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary" onclick=msg();>发布公告</button>
								<button type="reset" class="btn btn-primary">重置</button>
								
							</div>
							
							
						</div>
					</fieldset>
				</form>
				<div>
				<div>
				<form action="driveSchool/Notice!toSendedNotice" method="post">
								<button type="submit" class="btn btn-primary">查看已发布</button>
				</form>
				</div>
				
				</div>
	
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->








<!-- start: JavaScript-->

	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>

	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>

	<script src="js/jquery.ui.touch-punch.js"></script>

	<script src="js/modernizr.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.cookie.js"></script>

	<script src='js/fullcalendar.min.js'></script>

	<script src='js/jquery.dataTables.min.js'></script>

	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>

	<script src="js/jquery.chosen.min.js"></script>

	<script src="js/jquery.uniform.min.js"></script>

	<script src="js/jquery.cleditor.min.js"></script>

	<script src="js/jquery.noty.js"></script>

	<script src="js/jquery.elfinder.min.js"></script>

	<script src="js/jquery.raty.min.js"></script>

	<script src="js/jquery.iphone.toggle.js"></script>

	<script src="js/jquery.uploadify-3.1.min.js"></script>

	<script src="js/jquery.gritter.min.js"></script>

	<script src="js/jquery.imagesloaded.js"></script>

	<script src="js/jquery.masonry.min.js"></script>

	<script src="js/jquery.knob.modified.js"></script>

	<script src="js/jquery.sparkline.min.js"></script>

	<script src="js/counter.js"></script>

	<script src="js/retina.js"></script>

	<script src="js/custom.js"></script>
	<!-- end: JavaScript-->
</body>
</html>
