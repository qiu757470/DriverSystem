<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%><!--要用到OGNL表达式  就得要写的 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'school.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
<!-- select 提交form -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function submitForm() {
		//获取form表单对象
		var form = document.getElementById("myform");
		form.submit(); //form表单提交
	}
</script>
<script type="text/javascript">
	//翻页
	$(function() {
		//下一页
		$("#next").click(
			function() {

				$("#paging1").html(parseInt($("#paging1").html()) + 1);
				$("#paging2").html(parseInt($("#paging2").html()) + 1);
				$("#paging3").html(parseInt($("#paging3").html()) + 1);
				$("#paging4").html(parseInt($("#paging4").html()) + 1);
			}
		)
		//上一页
		$("#prev").click(
			function() {
				if (parseInt($("#paging1").html()) - 1 <= 0)
					return;
				$("#paging1").html(parseInt($("#paging1").html()) - 1);
				$("#paging2").html(parseInt($("#paging2").html()) - 1);
				$("#paging3").html(parseInt($("#paging3").html()) - 1);
				$("#paging4").html(parseInt($("#paging4").html()) - 1);

			}
		)
   //上传页数
   $(".pagingLi").click(function(){
   $(this).children().attr("href","student/toFindStudentTime!findAllByCourseProject?currentPage="+$(this).children().html());
   });

	})
	
</script>
</head>

<body>


	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>查学时
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

				<form id="myform"
					action="student/toFindStudentTime!findAllByCourseProject"
					method='post'>
					<select name="courseSelect" onchange="submitForm()">

						<option>请选择科目</option>
						<s:iterator value="courses" var="c">
							<option value="<s:property value="#c.course_id"/>"><s:property
									value="#c.course_name" /></option>
						</s:iterator>
					</select> <br> 科目状态:<s:property value="courseState"/>
				</form>

				<table class="table table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>考核科目</th>
							<th>已进行学时</th>
							<th>未完成学时</th>
							<th>总学时</th>
						</tr>
					</thead>
					<tbody>
					
						<s:iterator value="stuCourseProjects" var="cp" status="status">
						
								<tr>
									<td><s:property value="%{#status.count}" /></td>

									<td class="center" ><s:property value="#cp.courseProject.project_name" /></td>


									<td class="center"><s:property
											value="#cp.stu_course_project_time" /></td>

									<td class="center"><span class="label label-success"><s:property value="#cp.stu_course_project_todo" /></span>
									</td>
									<td class="center"><s:property
											value="#cp.courseProject.project_time"/></td>
								</tr>
							</s:iterator>
					</tbody>
				</table>
				<div class="pagination pagination-centered">
					<ul>
						<li><a href="javascript:void(0)" id="prev">前</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging1">1</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging2">2</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging3">3</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging4">4</a></li>
						<li><a href="javascript:void(0)" id="next">后</a></li>
					</ul>
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
