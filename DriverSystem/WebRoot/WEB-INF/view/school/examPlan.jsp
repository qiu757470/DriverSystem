<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'examPlan.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext 
'rel='stylesheet' type='text/css'>
<!-- <link href="css/OSTA.css" rel="stylesheet" type="text/css" /> -->
<script src="js/jquery.js"></script>

<script type="text/javascript">
function del(){
var a =confirm("确认删除吗？")
if(a==true){
return true;
}else {
return false;

}

}
$(function(){

$("#next").click(function(){
	$("#paging1").html(parseInt($("#paging1").html()) + 1);
	$("#paging2").html(parseInt($("#paging2").html()) + 1);
	$("#paging3").html(parseInt($("#paging3").html()) + 1);
	$("#paging4").html(parseInt($("#paging4").html()) + 1);
});
$("#prev").click(function(){
    if($("#paging1").html()=='1'){
       return
    }
	$("#paging1").html(parseInt($("#paging1").html()) - 1);
	$("#paging2").html(parseInt($("#paging2").html()) - 1);
	$("#paging3").html(parseInt($("#paging3").html()) - 1);
	$("#paging4").html(parseInt($("#paging4").html()) - 1);
});

$(".pagingLi").click(function(){

alert($(this).children().html());
$(this).children().attr("href","driveSchool/schoolCertified!scoerlist?page="+$(this).children().html());

});



})
</script>



</head>

<body>
	<div id='div1'>

		<a href="driveSchool/schoolCertified!newExam"> <input
			type="button" value="新建考试"  class="btn btn-primary"/></a>
	</div>

</br>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>考试安排
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
						<th>考试项目</th>
						<th>日期</th>
						<th>时间</th>
						<th>地点</th>
						<th>负责教练</th>
						<th>考试状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="eplist" var="li" status="t">
						<tr>
							<td><s:property value="(#t.index)+1" /></td>
							<td><s:property value="#li.course.course_name" /></td>
							<td><s:property value="#li.exam_plan_date" /></td>
							<td><s:property value="#li.exam_plan_time" /></td>
							<td><s:property value="#li.exam_plan_address" /></td>
							<td><s:property value="#li.teacher.teacher_name" /></td>
							<td><s:property value="#li.exam_plan_state" /></td>
							<td><a
								href="driveSchool/schoolCertified!examDelete?epid=<s:property value="#li.exam_plan_id"/>"
								onclick="return del()">删除</a> / <a
								href="driveSchool/schoolCertified!examUpdate?epid=<s:property value="#li.exam_plan_id"/>">修改</a>/
								<a
								href="driveSchool/schoolCertified!examInfo?epid=<s:property value="#li.exam_plan_id"/>">查看</a>

							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>



	</div>

	<div class="pagination pagination-centered" align="center">
		<ul>
			<li><a href="javascript:void(0)" id="prev">Prev</a></li>
			<li class="pagingLi"><a href="javascript:void(0)"
				class="pagingA" id="paging1">1</a></li>
			<li class="pagingLi"><a href="javascript:void(0)"
				class="pagingA" id="paging2">2</a></li>
			<li class="pagingLi"><a href="javascript:void(0)"
				class="pagingA" id="paging3">3</a></li>
			<li class="pagingLi"><a href="javascript:void(0)"
				class="pagingA" id="paging4">4</a></li>
			<li><a href="javascript:void(0)" id="next">Next</a></li>
		</ul>

	</div>






</body>
</html>
