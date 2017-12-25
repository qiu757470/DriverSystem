<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'verifyStudent.jsp' starting page</title>
    
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
$(function(){


$("#verifiyall").click(function(){
var a=confirm("确认通过审核？");
if(a==true){
 var sel = $("input[name='check']:checked");
   if(sel.length==0){
     alert("请至少选中一个选项")
        return false;
   }else{
    return true;
   }
}else{
return false;
}

});
$("#checkall").click(function(){
        var checket = document.getElementById("checkall");
		var checkets = document.getElementsByName("check");
		if (checket.checked) {
			for (i = 0; i < checkets.length; i++) {
				checkets[i].checked = true;
			}
		} else {
			for (i = 0; i < checkets.length; i++) {
				checkets[i].checked = false;
			}
		}

});

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
$(this).children().attr("href","driveSchool/schoolCertified!studentVerifylist?page="+$(this).children().html());

});





});


</script>



  </head>
  
  
  
  
  
  <body>
   	<form action="driveSchool/schoolCertified!verifiedall" method="post" id="form1">
   	<div><input type="submit" id="verifiyall" value="批量审核" class="btn btn-primary"/> </div>
   	</br>
   <div align="center">
   		<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>学生审核
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
   				<th><input type="checkbox" name="checkall" id="checkall"/>序号</th>
   			    <th>姓名</th>
   			    <th>身份证</th>
   			    <th>性别</th>
   			    <th>电话</th>
   			    <th>审核状态</th>
   			    <th>操作</th>
   			</tr>
   		
   		<s:iterator value="unveifystudents" var="verify" status="con">
   			<tr>
 <td><input type="checkbox" name="check" value='<s:property value="#verify.student_id"/>'/><s:property value="(#con.index)+1"/></td>
   			    <td><s:property value="#verify.student_name"/> </td>
   			    <td><s:property value="#verify.student_identification"/></td>
   			    <td><s:property value="#verify.student_sex"/></td>
   			    <td><s:property value="#verify.student_phone"/></td>
   			    <td><s:property value="#verify.student_state"/></td>
   			    <td><a href="driveSchool/schoolCertified!verified?verified_id=<s:property value="#verify.student_id"/>">通过审核</a></td>
   			</tr>
   		
   		
   		</s:iterator>
   		
   		
   		
   		
   		
   		</table>
</div>
</div>
</div>
</div>
   	</form>
   
 <div class="pagination pagination-centered">
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
