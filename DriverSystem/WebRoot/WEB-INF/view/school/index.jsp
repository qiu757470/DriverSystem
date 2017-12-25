<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- start: Meta -->
<meta charset="utf-8">
<title>驾校培训系统驾校平台</title>
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



<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
	function check(obj) {
		var ss = $('#menus' + obj).html();
		var aa = jQuery.trim(ss);
		if (aa == '驾校认证') {
			$("#menuname" + obj).attr("href", "certificedPass!certifictedpass");
		} else if (aa == '考试安排') {
			$("#menuname" + obj).attr("href", "driveSchool/schoolCertified!examplan");
		} else if (aa == '修改个人信息') {
			$("#menuname" + obj).attr("href", "driveSchool/schoolCertified!SchoolManagerInfo");
		} else if (aa == '修改密码') {
			$("#menuname" + obj).attr("href", "driveSchool/schoolCertified!updatePWD");
		} else if (aa == '关于') {
			$("#menuname" + obj).attr("href", "driveSchool/schoolCertified!about");
		} else if (aa == '退出登录') {
			var r = confirm("确定退出登录吗？")
			if (r==true) {
				$("#menuname" + obj).attr("target", "_parent");
				$("#menuname" + obj).attr("href", "driveSchool/schoolCertified!exit");
				return true;
			}else{
			return false;
			}
		} else if (aa == '学员管理') {
			$("#menuname" + obj).attr("href", "driveSchool/DriveSchoolUserAction");
		} else if (aa == '教练管理') {
			$("#menuname" + obj).attr("href", "driveSchool/driveSchoolTeacher");
		}else if(aa=='成绩管理'){
		 $("#menuname" + obj).attr("href", "driveSchool/schoolCertified!scoer");
		}else if(aa=='学员审核'){
		 $("#menuname" + obj).attr("href", "driveSchool/schoolCertified!studentVerify");
		}else if (aa == '查看公告') {
			$("#menuname" + obj).attr("href", "driveSchool/Notice!findNotice");
		}else if (aa == '发布公告') {
			$("#menuname" + obj).attr("href", "driveSchool/Notice!sendNotice");
		}

	}
	;
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

<body>

	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a> <a class="brand"><span>驾校平台</span></a>

				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<%-- <li class="dropdown hidden-phone"><a
							class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-bell"></i> <span class="badge red"> </span>
						</a>
							<ul class="dropdown-menu notifications">
								<li class="dropdown-menu-title"><span>You have 11
										notifications</span> <a href="#refresh"><i class="icon-repeat"></i></a>
								</li>
								<li><a href="#"> <span class="icon blue"><i
											class="icon-user"></i></span> <span class="message">New user
											registration</span> <span class="time">1 min</span>
								</a></li>
								<li><a href="#"> <span class="icon green"><i
											class="icon-comment-alt"></i></span> <span class="message">New
											comment</span> <span class="time">7 min</span>
								</a></li>
								<li><a href="#"> <span class="icon green"><i
											class="icon-comment-alt"></i></span> <span class="message">New
											comment</span> <span class="time">8 min</span>
								</a></li>
								<li><a href="#"> <span class="icon green"><i
											class="icon-comment-alt"></i></span> <span class="message">New
											comment</span> <span class="time">16 min</span>
								</a></li>
								<li><a href="#"> <span class="icon blue"><i
											class="icon-user"></i></span> <span class="message">New user
											registration</span> <span class="time">36 min</span>
								</a></li>
								<li><a href="#"> <span class="icon yellow"><i
											class="icon-shopping-cart"></i></span> <span class="message">2
											items sold</span> <span class="time">1 hour</span>
								</a></li>
								<li class="warning"><a href="#"> <span class="icon red"><i
											class="icon-user"></i></span> <span class="message">User
											deleted account</span> <span class="time">2 hour</span>
								</a></li>
								<li class="warning"><a href="#"> <span class="icon red"><i
											class="icon-shopping-cart"></i></span> <span class="message">New
											comment</span> <span class="time">6 hour</span>
								</a></li>
								<li><a href="#"> <span class="icon green"><i
											class="icon-comment-alt"></i></span> <span class="message">New
											comment</span> <span class="time">yesterday</span>
								</a></li>
								<li><a href="#"> <span class="icon blue"><i
											class="icon-user"></i></span> <span class="message">New user
											registration</span> <span class="time">yesterday</span>
								</a></li>
								<li class="dropdown-menu-sub-footer"><a>View all
										notifications</a></li>
							</ul></li>
						<!-- start: Notifications Dropdown -->
						<li class="dropdown hidden-phone"><a
							class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-calendar"></i> <span class="badge red"> 
							</span>
						</a>
							<ul class="dropdown-menu tasks">
								<li class="dropdown-menu-title"><span>You have 17
										tasks in progress</span> <a href="#refresh"><i
										class="icon-repeat"></i></a></li>
								<li><a href="#"> <span class="header"> <span
											class="title">iOS Development</span> <span class="percent"></span>
									</span>
										<div class="taskProgress progressSlim red">80</div>
								</a></li>
								<li><a href="#"> <span class="header"> <span
											class="title">Android Development</span> <span
											class="percent"></span>
									</span>
										<div class="taskProgress progressSlim green">47</div>
								</a></li>
								<li><a href="#"> <span class="header"> <span
											class="title">ARM Development</span> <span class="percent"></span>
									</span>
										<div class="taskProgress progressSlim yellow">32</div>
								</a></li>
								<li><a href="#"> <span class="header"> <span
											class="title">ARM Development</span> <span class="percent"></span>
									</span>
										<div class="taskProgress progressSlim greenLight">63</div>
								</a></li>
								<li><a href="#"> <span class="header"> <span
											class="title">ARM Development</span> <span class="percent"></span>
									</span>
										<div class="taskProgress progressSlim orange">80</div>
								</a></li>
								<li><a class="dropdown-menu-sub-footer">View all tasks</a>
								</li>
							</ul></li>
						<!-- end: Notifications Dropdown -->
						<!-- start: Message Dropdown -->
						<li class="dropdown hidden-phone"><a
							class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-envelope"></i> <span class="badge red"> 
							</span>
						</a>
							<ul class="dropdown-menu messages">
								<li class="dropdown-menu-title"><span>You have 9
										messages</span> <a href="#refresh"><i class="icon-repeat"></i></a></li>
								<li><a href="#"> <span class="avatar"><img
											src="img/avatar.jpg" alt="Avatar"></span> <span class="header">
											<span class="from"> Dennis Ji </span> <span class="time">
												6 min </span>
									</span> <span class="message"> Lorem ipsum dolor sit amet
											consectetur adipiscing elit, et al commore </span>
								</a></li>
								<li><a href="#"> <span class="avatar"><img
											src="img/avatar.jpg" alt="Avatar"></span> <span class="header">
											<span class="from"> Dennis Ji </span> <span class="time">
												56 min </span>
									</span> <span class="message"> Lorem ipsum dolor sit amet
											consectetur adipiscing elit, et al commore </span>
								</a></li>
								<li><a href="#"> <span class="avatar"><img
											src="img/avatar.jpg" alt="Avatar"></span> <span class="header">
											<span class="from"> Dennis Ji </span> <span class="time">
												3 hours </span>
									</span> <span class="message"> Lorem ipsum dolor sit amet
											consectetur adipiscing elit, et al commore </span>
								</a></li>
								<li><a href="#"> <span class="avatar"><img
											src="img/avatar.jpg" alt="Avatar"></span> <span class="header">
											<span class="from"> Dennis Ji </span> <span class="time">
												yesterday </span>
									</span> <span class="message"> Lorem ipsum dolor sit amet
											consectetur adipiscing elit, et al commore </span>
								</a></li>
								<li><a href="#"> <span class="avatar"><img
											src="img/avatar.jpg" alt="Avatar"></span> <span class="header">
											<span class="from"> Dennis Ji </span> <span class="time">
												Jul 25, 2012 </span>
									</span> <span class="message"> Lorem ipsum dolor sit amet
											consectetur adipiscing elit, et al commore </span>
								</a></li>
								<li><a class="dropdown-menu-sub-footer">View all
										messages</a></li>
							</ul></li> --%>

						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> ${sessionScope.schoolmanager} <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>账号设置</span></li>
								<!-- <li><a href="#"><i class="halflings-icon user"></i>
										Profile</a></li> -->
								<li><a href="login"><i class="halflings-icon off"></i>
										注销</a></li>
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
						<s:iterator value="managerJurOne" var="menus" status="cou">
							<li><a class="dropmenu" href="#"><i
									class="icon-folder-close-alt"></i><span class="hidden-tablet">
										<s:property value="#menus.manager_jur_one_name" />
								</span><span class="label label-important"></span></a>
								<ul>
									<s:iterator value="#menus.managerjurtwo" var="tmenus"
										status="ct">
										<li
											id="menus1<s:property value='#cou.index+1'/><s:property value='#ct.index'/>"><a
											class="submenu" href="" target="u"
											id="menuname<s:property value='#cou.index+1'/><s:property value='#ct.index'/>"
											onclick="return check(<s:property value='#cou.index+1'/><s:property value='#ct.index'/>)"><i
												class="icon-file-alt"></i><span  id="menus<s:property value='#cou.index+1'/><s:property value='#ct.index'/>" class="hidden-tablet"><s:property value="#tmenus.manager_jur_two_name" /></span></a></li>
									</s:iterator>
								</ul></li>
						</s:iterator>

					</ul>

				</div>
			</div>

		</div>

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
			<iframe frameborder="0" src="driveSchool/toMainPage"
				name="u" style="height: 600px;width: 1100px" scroll="auto"></iframe>
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

	</footer>

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

