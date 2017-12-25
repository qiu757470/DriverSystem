<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- start: Meta -->
<meta charset="utf-8">
<title>驾校培训管理系统运营平台主页</title>
<meta name="description" content="Bootstrap Metro Dashboard">
<meta name="author" content="Dennis Ji">
<meta name="keyword"
	content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<!-- end: Meta -->

<!-- start: Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- end: Mobile Specific -->

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
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function logout(){
		window.location.href = "loginAction!logout"
	}
	var loginUser = "<%=session.getAttribute("loginTransUser")%>";
	if(loginUser == null || loginUser == "" || loginUser == "null"){
		logout();
	}
	
	function transUserLogout(){
		if(confirm("确认退出登录吗？")){
			logout();
		}
	}

</script>

<style type="text/css">

	html,body{
		font-family: '微软雅黑';
	}
	.hidden-tablet:hover{
		color:red;
	}
	.submenu:hover{
		color:red;
	}
	dropmenu:hover{
		color:red;
	}
	a:hover{
		color:red;
	}
	a:active{
		color:yellow;
		opacity: 0.8;
	}
    a:focus{
         outline: none;
    }
    .icon-file-alt{
    	margin-left: 20px;
    }
</style>

</head>

<body >
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a> <a class="brand"><span style="font-family: '黑体'">驾校培训管理系统<span style="font-size: 18px;font-family: '微软雅黑'">——运管平台</span></span></a>
  
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
					
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> ${loginTransUser.trans_user_name} <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>系统设置</span></li>
								<li><a href="transport/settingAction!revisePassword" target="u"><i class="halflings-icon user"></i>
										修改密码</a></li>
								<li><a href="transport/settingAction!revisePersonInfo" target="u"><i class="halflings-icon off"></i>
										修改个人信息</a></li>
								<li><a href="transport/settingAction!about" target="u"><i class="halflings-icon off"></i>
										关于系统</a></li>
								<li><a onclick="transUserLogout()"><i class="halflings-icon off"></i>
										退出登录</a></li>
							</ul></li>
						<!-- end: User Dropdown -->
					</ul>
	
				</div>
				<!-- end: Header Menu -->

			</div>
		</div>
		 
	</div>
	<!-- start: Header -->
	<div class="copyrights">
		Collect from <a href="http://www.cssmoban.com/">企业网站模板</a>
	</div>

	<div class="container-fluid-full">
		<div class="row-fluid">
<!-- left -->
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									系统管理</span></a>
							<ul>
								<li><a class="submenu" href="transport/managerManagementAction!showPage?nowPage=1" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											用户管理</span></a></li>
											<li><a class="submenu" href="transport/managerJurisdictionAction!showPage?nowPage=1" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											权限管理</span></a></li>
							
							</ul></li>
						
					</ul>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									驾校管理</span></a>
							<ul>
								<li><a class="submenu" href="transport/schoolVerifyAction!showPage?nowPage=1" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											驾校审核</span></a></li>
											<li><a class="submenu" href="transport/schoolManagementAction!showPage?nowPage=1" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											驾校管理</span></a></li>
							
							</ul></li>
						
					</ul>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									运营信箱</span></a>
							<ul>
								<li><a class="submenu" href="transport/mailAction!checkMailPage?nowPage=1" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											查看邮件</span></a></li>
											
							
							</ul></li>
						
					</ul>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									公告管理</span></a>
							<ul>
								<li><a class="submenu" href="transport/noticeView" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											查看公告</span></a></li>
											<li><a class="submenu" href="transport/noticeAnnounce" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											发布公告</span></a></li>
							
							</ul></li>
						
					</ul>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									数据统计</span></a>
							<ul>
								<li><a class="submenu" href="transport/statisticsAction!statisticsView" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											数据统计</span></a></li>
							</ul></li>
						
					</ul>
					<!-- <ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="dropmenu" href="#"><i
								class="icon-folder-close-alt"></i><span class="hidden-tablet">
									设置</span></a>
							<ul>
								<li><a class="submenu" href="transport/settingAction!revisePassword" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											修改密码</span></a></li>
											<li><a class="submenu" href="transport/settingAction!revisePersonInfo" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											修改个人信息</span></a></li>
											<li><a class="submenu" href="transport/settingAction!about" target="u"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											关于</span></a></li>
												<li><a onclick="transUserLogout()" class="submenu" ><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											退出登录</span></a></li>
							
							</ul></li>
						
					</ul> -->
				</div>
			</div>
			<!-- end: Main Menu -->

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>
<!-- right -->
			<!-- start: Content -->
			<div id="content" class="span10">
			<iframe  src="transport/transportMain" name="u" 
		style="height: 800px;width: 1100px;border: none" scrolling="auto"></iframe>
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
					class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<div class="common-modal modal fade" id="common-Modal1" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-content">
				<ul class="list-inline item-details">
					<li><a href="#">Admin templates</a></li>
					<li><a href="http://themescloud.org">Bootstrap themes</a></li>
				</ul>
			</div>
		</div>

		<div class="clearfix"></div>

		<footer>

			<p>
				<span style="text-align:left;float:left">&copy; 2013 <a
					href="downloads/janux-free-responsive-admin-dashboard-template/"
					alt="Bootstrap_Metro_Dashboard">JANUX Responsive Dashboard</a></span>

			</p>

		</footer>z

		<!-- start: JavaScript-->

		<!-- <script src="js/jquery-1.9.1.min.js"></script> -->
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
	</div>
</body>
</html>

