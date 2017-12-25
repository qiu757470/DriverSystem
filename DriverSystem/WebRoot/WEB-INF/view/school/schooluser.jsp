<%@ page language="java" import="java.util.*,org.great.bean.StudentBean" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
	
	function change(tag){
	
	var student_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	$(tag).attr("href","driveSchool/toUpdatePage?student_id="+student_id);
	return true;
	}
	function info(tag){
	
	var student_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	$(tag).attr("href","driveSchool/allUserInf?student_id="+student_id);
	return true;
	}
	function delect(tag){
	
	
	if(confirm("删除是不可恢复的，你确认要删除吗？")){
	
	var student_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	$(tag).attr("href","driveSchool/delectUser?student_id="+student_id);
	
	return true;
	}else{
	return false;
	}
	
	}
	
	function F_Open_dialog() 
{ 
document.getElementById("btn_file").click(); 
}
	
	
	function turn(tag) 
{ 
	
	var a=$(tag).attr("id");
	
	if(a==1){
	
	window.location.href="driveSchool/DriveSchoolUserAction"
	
	
	}else if(a==2){
	window.location.href="driveSchool/findByPage!beforePage"
	
	}else if(a==3){
	window.location.href="driveSchool/findByPage!afterPage"
	}else if(a==4){
	window.location.href="driveSchool/findByPage!lastPage"
	}
	
}
	
	/* function a(){
	document.getElementById("search").click();
	} */
	
		function upload() 
{ 
		 var file = document.getElementById('some'); 
		 
            if (file.value == "") { 
alert("请选择您需要上传的文件！"); 
return false;
}else{ 
document.form1.submit(); 
} 
	
}



$(function(){
var sid="<%=(StudentBean)request.getAttribute("student")
!=null?((StudentBean)request.getAttribute("student")).getStudent_identification():null%>";
 if(sid!=null&&sid!=""&&sid!="null"){
 $("#student_identification").val(sid);
 }
var name="<%=(StudentBean)request.getAttribute("student")
	!=null?((StudentBean)request.getAttribute("student")).getStudent_name():null%>";
	 if(name!=null&&name!=""&&name!="null"){
	 $("#student_name").val(name);
	 }
var sex="<%=(StudentBean)request.getAttribute("student")
	!=null?((StudentBean)request.getAttribute("student")).getStudent_sex():null%>";
	 if(sex!=null&&sex!=""&&sex!="null"){
	 $("#student_sex").val(sex);
	 }
var course="<%=(StudentBean)request.getAttribute("student")
	!=null?((StudentBean)request.getAttribute("student")).getCourse_id():null%>";
	 if(course!=null&&course!=""&&course!="null"){
	 
	 $("#course_id").val(course);
	 }

});


	</script>
	
  </head>
  <style>
	a{ text-decoration:none; }
</style>
  <body>
 
 
  <div >
  	<form id="searchUser" action="driveSchool/searchUser" method="post" >
  			
  			<div style="float: left;">
  					身份证号：<input id="student_identification" name="student_identification" class="" type="text" style="width:170px;"/>
			</div>
			
			<div style="float: left;">
					姓名：<input id="student_name" name="student_name" class="" type="text" style="width:70px;"/>
			</div>
			
			<div style="float: left;">
					性别：
					    <select id="student_sex"  name="student_sex"  style="width:80px;">
							<option id=" "  value="全部">全部</option>
							<option id=" "  value="男">男</option>
							<option id=" "  value="女">女</option>
						</select>
			</div>
			
			<div style="float: left;">		
				科目进度：
					<select  id="course_id" name="course_id" style="width:80px;">
							<option id=" "  value="全部">全部</option>
							<option id=" "  value="1">科目一</option>
							<option id=" "  value="2">科目二</option>
							<option id=" "  value="3">科目三</option>
							<option id=" "  value="4">科目四</option>
					</select>
			</div>
			
				<div style="float: left;">
					<button id="search" type="submit" class="btn btn-primary">搜索
					</button>
	  			</div>
  	</form>
  			<div style="float: left;">
  			<form action="driveSchool/DriveSchoolUserAction" method="post">
						<button id="" class="btn btn-primary" type="submit">全部
						</button>
			</form>
			</div>
  </div>
  
	 <div style="float: left;">
	 <form action="driveSchool/toCreateUser">
	 <button type="submit" class="btn btn-primary">创建用户</button>
	 </form>
		
	</div> 
	
	<div>
		<form action="upload/uploaduser" enctype="multipart/form-data" method="post" onsubmit="return upload()">
		
		<div style="float: left;">
			<input type="file" id="some" name="some" value="浏览"  style="width:150px" >
		</div>
		<div style="float: left;"><button type="submit" class="btn btn-primary">批量创建</button></div>
		
		</form>
		
		
	
		
	</div>
	

		
	

	

















 <div class="row-fluid sortable">
		<div class="box span12">
			<%-- <div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>历史公告
				</h2>
				<div class="box-icon">
						<a href="#" class="btn-setting"><i
						class="halflings-icon white wrench"></i></a> <a href="#"
						class="btn-minimize"><i
						class="halflings-icon white chevron-up"></i></a> <a href="#"
						class="btn-close"><i class="halflings-icon white remove"></i></a>
				</div>
			</div> --%>
			<div class="box-content">
				<table class=" table table-hover">
					<tr>
						<th>序号</th><th>用户名</th><th>身份证</th><th>性别</th><th>科目进度</th><th>操作</th>
					</tr>
					<%int i1=0; %>
					<s:iterator value="userlist" var="u" >
					<tr>
						<td value="#u.student_id"><%=++i1 %></td>
						
							<td name="student_id" style="display:none"><s:property value="#u.student_id"/></td>
							<td><s:property value="#u.student_name"/></td>
							<td><s:property value="#u.student_identification"/></td>
							<td><s:property value="#u.student_sex"/></td>
							<td><s:property value="#u.course_name"/></td>
						
						
						
						<td>
                        <a href="driveSchool/delectUser" target="u" onclick="return delect(this)">删除</a> 
                        <a href="driveSchool/toUpdatePage" target="u" onclick="return change(this)">修改</a> 
                         <a href="driveSchool/DriveSchoolUserAction" target="u" onclick="return info(this)">详情</a>
                        </td>
					</tr>
					</s:iterator>
					
					
					
				</table>
				<div style="float: left;"><p>  共 ${snum} 条记录</p></div>
				<div style="float: left;"><p>第${page}页</p></div>
		
		<div style="float: left;margin-left: 10px;">
			<button id="1" class="btn btn-primary" onclick=turn(this);>第一页</button>
			<button id="2" class="btn btn-primary" name="page" onclick=turn(this);>上一页</button>
			<button id="3" class="btn btn-primary" name="page" onclick=turn(this);>下一页</button>
			<button id="4" class="btn btn-primary" onclick=turn(this);>最后页</button>
		</div>
		
		<div style="float: left;margin-left: 100px;">
			<form action="driveSchool/findByPage!findByPage" method="post">
			<input id="page" name="page" style="width: 25px" type="text" pattern="^[0-9]*$" required value=${page}  />
			
			<button type="submit" class="btn btn-primary">确定</button>
			</form>
		</div>
		
			</div>
		</div>
	</div> 





















  </body>
</html>
