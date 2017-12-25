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

<title>学员报名</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jsAddress.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- start: CSS -->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link href='css/goo.css' rel='stylesheet' type='text/css'>
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
	$(function() {
		$("#form1").on("submit", function(event) {
			$(this).validate();
		});

		$("#form1").validate({
			rules : {
				identi : {
					required : true,
					rangelength : [ 18, 18 ]
				},
				name : {
					required : true,
					maxlength : 5
				},
				sex : {
					required : true
				},
				phone : {
					required : true,
					rangelength : [ 11, 11 ]
				},
				cmbProvince : {
					required : true
				},
				cmbCity : {
					required : true
				},
				school_name : {
					required : true
				},
				teacher_name : {
					required : true
				}
			},

			messages : {
				identi : {
					required : "必填",
					rangelength : "身份证位数应为18位"
				},
				name : {
					required : "必填",
					maxlength : "名字长度不能超过5个字符"
				},
				sex : {
					required : "必填"
				},
				phone : {
					required : "必填",
					rangelength : "手机号码应为11位"
				},
				cmbProvince : {
					required : "必填"
				},
				cmbCity : {
					required : "必填"
				},
				school_name : {
					required : "必填"
				},
				teacher_name : {
					required : "必填"
				}
			},
		});


		$("#cmbCity").change(function() {
			$.ajax({
				url : "studentRegister/register!schoolselect",
				type : "post",
				data : {
					"cmbProvince" : $("#cmbProvince").val(),
					"cmbCity" : $("#cmbCity").val()
				},
				success : function(e) {
					var jsonObj = JSON.parse(e); //转换为json对象
					for (var i = 0; i < jsonObj.length; i++) {
						$("#school_name").append("<option value='" + jsonObj[i].school_id + "'>" + jsonObj[i].school_name + "</option>");
					}
				}
			})

		})


		$("#school_name").change(function() {
			$.ajax({
				url : "studentRegister/register!selectteacher",
				type : "post",
				data : {
					"school_name" : $("#school_name").val()
				},
				success : function(e) {
					var jsonObj = JSON.parse(e); //转换为json对象
					$("#teacher_name").children().remove()
					for (var i = 0; i < jsonObj.length; i++) {
						$("#teacher_name").append("<option value='" + jsonObj[i].teacher_id + "'>" + jsonObj[i].teacher_name + "</option>");
					}
				}
			})

		})

		

	});
	
	function back(){
		window.location.href = "";
	}
</script>
</head>

<body style="background-color: white;">
	<div style="float: left;width: 40%">
		<img alt="" src="img/timg.jpg">
	</div>
	<div style="padding: 0 0 0 60%">
		<div class="box yellow span12" style="width: 450px;height: 630px">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white white tasks"></i><span class="break"></span>报名
				</h2>
			</div>
			<div class="box-content">
				<form action="studentRegister/register!register" method="post"
					id="form1">
					<table class="table table-striped">
						<tr>
							<td></td>
							<td><div class="controls">
									<label id="time"></label> <input type="hidden"
										name="register_time" id="timeHidden">
								</div></td>
						</tr>
						<tr>
							<td>身份证号码：</td>
							<td><input type="text" name="identi" id="identi" /></td>
						</tr>
						<tr>
							<td>姓名：</td>
							<td><input type="text" name="name" id="name" /></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><select name="sex" id="sex">
									<option>男</option>
									<option>女</option>
							</select></td>
						</tr>
						<tr>
							<td>电话号码：</td>
							<td><input type="text" name="phone" id="phone" /></td>
						</tr>
						<tr>
							<td>所在省份：</td>
							<td><select id="cmbProvince" name="cmbProvince"></select></td>
						</tr>
						<tr>
							<td>所在市：</td>
							<td><select id="cmbCity" name="cmbCity"></select>  </td>
						</tr>
						<tr>
							<td><select id="cmbArea" name="cmbArea" style="display:none"></select> </td>
							<td></td>
						</tr>
						<tr>
							<td>驾校名称：</td>
							<td><select name="school_name" id="school_name"></select></td>
						</tr>
						<tr>
							<td>教练名称：</td>
							<td><select name="teacher_name" id="teacher_name"></select></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="注册" class="btn" />&nbsp;&nbsp;<input
								type="reset" value="重置" class="btn" />&nbsp;&nbsp;<input type="button"
								value="返回" class="btn" onclick="back()" /></td>
						</tr>






					</table>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">addressInit('cmbProvince', 'cmbCity', 'cmbArea')</script>
</body>
</html>
