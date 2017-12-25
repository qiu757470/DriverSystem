<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcomeToStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
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
  </head>
  
  <body>
    <h1 style="text-align: center;">欢迎您登陆驾校培训管理系统</h1>
 <!-- start: JavaScript-->



<div class="row-fluid sortable" style="width: 95%;margin-left: 2%">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white picture"></i><span class="break"></span></h2>
						<div class="box-icon">
							<a href="#" id="toggle-fullscreen" class="hidden-phone hidden-tablet"><i class="halflings-icon white fullscreen"></i></a>
							<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
						</div>
					</div>
				<div class="box-content">
						<div class="masonry-gallery">
														<div id="image-1" class="masonry-thumb">
								<a style="background:url(img/gallery/school1.jpg)" title="Sample Image 1" href="img/gallery/school1.jpg"><img class="grayscale" src="img/gallery/school1.jpg" alt="Sample Image 1"></a>
							</div>
														<div id="image-2" class="masonry-thumb">
								<a style="background:url(img/gallery/school2.jpg)" title="Sample Image 2" href="img/gallery/school2.jpg"><img class="grayscale" src="img/gallery/school2.jpg" alt="Sample Image 2"></a>
							</div>
														<div id="image-3" class="masonry-thumb">
								<a style="background:url(img/gallery/school3.jpg)" title="Sample Image 3" href="img/gallery/school3.jpg"><img class="grayscale" src="img/gallery/school3.jpg" alt="Sample Image 3"></a>
							</div>
														<div id="image-4" class="masonry-thumb">
								<a style="background:url(img/gallery/school4.jpg)" title="Sample Image 4" href="img/gallery/school4.jpg"><img class="grayscale" src="img/gallery/school4.jpg" alt="Sample Image 4"></a>
							</div>
														<div id="image-5" class="masonry-thumb">
								<a style="background:url(img/gallery/school5.jpg)" title="Sample Image 5" href="img/gallery/school5.jpg"><img class="grayscale" src="img/gallery/school5.jpg" alt="Sample Image 5"></a>
							</div>
														<div id="image-6" class="masonry-thumb">
								<a style="background:url(img/gallery/school6.jpg)" title="Sample Image 6" href="img/gallery/school6.jpg"><img class="grayscale" src="img/gallery/school6.jpg" alt="Sample Image 6"></a>
							</div>
														<div id="image-7" class="masonry-thumb">
								<a style="background:url(img/gallery/school7.jpg)" title="Sample Image 7" href="img/gallery/school7.jpg"><img class="grayscale" src="img/gallery/school7.jpg" alt="Sample Image 7"></a>
							</div>
														<div id="image-8" class="masonry-thumb">
								<a style="background:url(img/gallery/school8.jpg)" title="Sample Image 8" href="img/gallery/school8.jpg"><img class="grayscale" src="img/gallery/school8.jpg" alt="Sample Image 8"></a>
							</div>
														<div id="image-9" class="masonry-thumb">
								<a style="background:url(img/gallery/school9.jpg)" title="Sample Image 9" href="img/gallery/school9.jpg"><img class="grayscale" src="img/gallery/school9.jpg" alt="Sample Image 9"></a>
							</div>
														<div id="image-10" class="masonry-thumb">
								<a style="background:url(img/gallery/school10.jpg)" title="Sample Image 10" href="img/gallery/school10.jpg"><img class="grayscale" src="img/gallery/school10.jpg" alt="Sample Image 10"></a>
							</div>
														<div id="image-11" class="masonry-thumb">
								<a style="background:url(img/gallery/school11.jpg)" title="Sample Image 11" href="img/gallery/school11.jpg"><img class="grayscale" src="img/gallery/school11.jpg" alt="Sample Image 11"></a>
							</div>
														<div id="image-12" class="masonry-thumb">
								<a style="background:url(img/gallery/school12.jpg)" title="Sample Image 12" href="img/gallery/school12.jpg"><img class="grayscale" src="img/gallery/school12.jpg" alt="Sample Image 12"></a>
							</div>
														<div id="image-13" class="masonry-thumb">
								<a style="background:url(img/gallery/school13.jpg)" title="Sample Image 13" href="img/gallery/school13.jpg"><img class="grayscale" src="img/gallery/school13.jpg" alt="Sample Image 13"></a>
							</div>
													</div>
					</div>
					<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-content">
							<ul class="list-inline item-details">
								<li><a href="#">Admin templates</a></li>
								<li><a href="http://themescloud.org">Bootstrap themes</a></li>
							</ul>
						</div>
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
    

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
