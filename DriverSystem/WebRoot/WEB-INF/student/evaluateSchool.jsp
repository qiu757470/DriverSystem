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

<title>My JSP 'evaluateSchool.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/style-eva.css">
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
<script type="text/javascript" src="js/jquery.min.js"></script>

</head>
<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>评价驾校
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
				<form action="student/toSchoolEvaluationAction!submitDrivingEvaluation" method="post">
					<table class=" table table-hover">
						<tr>
							<td style="width: 20%">文明服务：</td>


							<td><input name="schoolEvaluation.s_service_grade"  required
								type="radio" value="1">1分</td>
							<td><input name="schoolEvaluation.s_service_grade" required
								type="radio" value="2">2分</td>
							<td><input name="schoolEvaluation.s_service_grade" required
								type="radio" value="3">3分</td>
							<td><input name="schoolEvaluation.s_service_grade" required
								type="radio" value="4">4分</td>
							<td><input name="schoolEvaluation.s_service_grade" required
								type="radio" value="5">5分</td>
						</tr>
						<tr>
						<td style="width: 20%">规范教学：</td>

						<td><input name="schoolEvaluation.s_standard_grade" required
							type="radio" value="1">1分</td>
						<td><input name="schoolEvaluation.s_standard_grade" required
							type="radio" value="2">2分</td>
						<td><input name="schoolEvaluation.s_standard_grade" required
							type="radio" value="3">3分</td>
						<td><input name="schoolEvaluation.s_standard_grade" required
							type="radio" value="4">4分</td>
						<td><input name="schoolEvaluation.s_standard_grade" required
							type="radio" value="5">5分</td>
						</tr>
						<tr>
							<td style="width: 20%">场地设施：</td>
							<td><input name="schoolEvaluation.s_place_grade" required
								type="radio" value="1">1分</td>
							<td><input name="schoolEvaluation.s_place_grade" required
								type="radio" value="2">2分</td>
							<td><input name="schoolEvaluation.s_place_grade" required
								type="radio" value="3">3分</td>
							<td><input name="schoolEvaluation.s_place_grade" required
								type="radio" value="4">4分</td>
							<td><input name="schoolEvaluation.s_place_grade" required
								type="radio" value="5">5分</td>
						</tr>

						<tr>
							<td style="width: 20%">安全管理：</td>
							<td><input name="schoolEvaluation.s_security_grade" required
								type="radio" value="1">1分</td>
							<td><input name="schoolEvaluation.s_security_grade" required
								type="radio" value="2">2分</td>
							<td><input name="schoolEvaluation.s_security_grade" required
								type="radio" value="3">3分</td>
							<td><input name="schoolEvaluation.s_security_grade" required
								type="radio" value="4">4分</td>
							<td><input name="schoolEvaluation.s_security_grade" required
								type="radio" value="5">5分</td>
						</tr>
						<tr>
							<td style="width: 20%">评语：</td>
							<td colspan="5">

								<div class="form-group">
									<textarea name="schoolEvaluation.s_eval_comment"  required
										style="overflow:scroll; overflow-x:hidden; height:80px;width:100%;resize:none; "
										class="form-control" rows="50"></textarea>
								</div>
							</td>

						</tr>
						<tr>
							<td><input class="btn btn-default" type="submit"
								value="提交评价"> <input class="btn btn-default"
								type="reset" value="重置信息"></td>
							<td></td>
						</tr>

					</table>

				</form>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->








</body>
</html>
