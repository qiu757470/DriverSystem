<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="utf-8"%>
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
	
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
	
	function change(tag){
	
	var teacher_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	$(tag).attr("href","driveSchool/TeacherAction!toUpdateTeacher?teacher_id="+teacher_id);
	return true;
	}
	function info(tag){
	
	var teacher_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	/* alert(teacher_id); */
	$(tag).attr("href","driveSchool/TeacherAction!teacherInfo?teacher_id="+teacher_id);
	return true;
	}
	function delect(tag){
	
	
	if(confirm("删除是不可恢复的，你确认要删除吗？")){
	var teacher_id = $(tag).parent().prev().prev().prev().prev().prev().html();
	$(tag).attr("href","driveSchool/TeacherAction!teacherDelete?teacher_id="+teacher_id);
	
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
	
	window.location.href="driveSchool/TeacherAction!firstPage"
	
	
	}else if(a==2){
	window.location.href="driveSchool/TeacherAction!beforePage"
	
	}else if(a==3){
	window.location.href="driveSchool/TeacherAction!afterPage"
	}else if(a==4){
	window.location.href="driveSchool/TeacherAction!lastPage"
	}
	
}
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
var name="<%=(TeacherBean)request.getAttribute("teacher")
	!=null?((TeacherBean)request.getAttribute("teacher")).getTeacher_name():null%>";
	 if(name!=null&&name!=""&&name!="null"){
	 $("#teacher_name").val(name);
	 }

 var sex="<%=(TeacherBean)request.getAttribute("teacher")
	!=null?((TeacherBean)request.getAttribute("teacher")).getTeacher_sex():null%>";
	 if(sex!=null&&sex!=""&&sex!="null"){
	 $("#teacher_sex").val(sex);
	 }
 
var maxgoal="<%=(TeacherBean)request.getAttribute("teacher")
	!=null?((TeacherBean)request.getAttribute("teacher")).getMaxgoal():null%>";
	 if(maxgoal!=null&&maxgoal!=""&&maxgoal!="null"){
	 $("#maxgoal").val(maxgoal);
	 }

var mingoal="<%=(TeacherBean)request.getAttribute("teacher")
	!=null?((TeacherBean)request.getAttribute("teacher")).getMingoal():null%>";
	 if(mingoal!=null&&mingoal!=""&&mingoal!="null"){
	 $("#mingoal").val(mingoal);
	 } 
var course_id="<%=(TeacherBean)request.getAttribute("teacher")
	!=null?((TeacherBean)request.getAttribute("teacher")).getCourse_id():null%>";
	 if(course_id!=null&&course_id!=""&&course_id!="null"){
	 $("#course_id").val(course_id);
	 } 

	});
	
	
	</script>
	
  </head>
  <style>
	a{ text-decoration:none; }
</style>
  <body>
  <div style="float: left;">
 	 <form id="seachTeacher" action="driveSchool/TeacherAction!seachTeacher" method="post">
			
			<div style="float: left;">
				姓名：<input id="teacher_name" name="teacher_name" style="width:70px;" type="text" />
			</div>
			
			<div style="float: left;">
			性别：
				 <select id="teacher_sex"  name="teacher_sex" style="width:80px;">
					<option id=" "  value="全部">全部</option>
					<option id=" "  value="男">男</option>
					<option id=" "  value="女">女</option>
				</select>
</div>
		
		<div style="float: left;">
			负责科目：
				 <select  id="course_id" name="course_id" style="width:80px;">
					<option id=" "  value="全部">全部</option>
					<option id=" "  value="1">科目一</option>
					<option id=" "  value="2">科目二</option>
					<option id=" "  value="3">科目三</option>
					<option id=" "  value="4">科目四</option>
				</select>
			</div>
			
			
			<div style="float: left;">
			评分：<select id="mingoal" name="mingoal" style="width:70px;">
			<%int a=-1; %>
			 <s:iterator value="new int[6]" var="u" >
			   <option ><%=++a %></option>
			</s:iterator> 
			
			
				</select>
				
				</div>
				<div style="float: left;">
				到<select id="maxgoal" name="maxgoal" style="width:70px;">
				<%int b=6; %>
					<s:iterator value="new int[6]" var="u" >
  					 <option   ><%=--b %></option>
					</s:iterator>
			</select>
		</div>
		
		<div style="float: left;">
			<button type="submit" class="btn btn-primary">搜索</button>
		</div>
</form>
		
</div>
		<div style="float: left;">
			<form action="driveSchool/driveSchoolTeacher">
			<button type="submit" class="btn btn-primary">全部</button>
			</form>
	</div>
	
			
			
			<div style="float: left; ">
			<form action="driveSchool/TeacherAction!toCreateUser">
			<button type="submit" class="btn btn-primary" >创建用户</button>
			</form>
			</div>
	
	
		<form action="upload/uploadteacher" enctype="multipart/form-data" method="post" onsubmit="return upload()">
		<div style="float: left;"><input type="file" id="some" name="some" value="浏览" style="width:150px"></div>
		<div style="float: left;"><button type="submit" class="btn btn-primary">批量创建</button></div>
		
		</form>
	

		
		

	

</div>

	<div class="row-fluid sortable">
		<div class="box span12">
			
			<div class="box-content">
				<table class=" table table-hover">
					<tr>
						<th>序号</th><th>用户名</th><th>性别</th><th>负责科目</th><th>评分</th><th>操作</th>
					</tr>
					<%int i=0; %>
					<s:iterator value="teacherlist" var="u" >
					
					<tr>
						<td value="#u.teacher_id"><%=++i %></td>
						
							<td name="teacher_id" style="display:none"><s:property value="#u.teacher_id"/></td>
							<td><s:property value="#u.teacher_name"/></td>
							<td><s:property value="#u.teacher_sex"/></td>
							
							
							<td><s:property value="#u.course_name"/></td>
						
						<td><s:property value="#u.teacher_eval_score"/></td>
						
						<td>
                        <a href="driveSchool/delectUser" target="u" onclick="return delect(this)">删除</a> 
                        <a href="driveSchool/toUpdatePage" target="u" onclick="return change(this)">修改</a> 
                         <a href="driveSchool/TeacherAction!teacherInfo" target="u" onclick="return info(this)">详情</a>
                        </td>
					</tr>
					</s:iterator>
					
					
				</table> 
				<div style="float: left;"><p>  共 ${tnum} 条记录</p></div>
				
				<div style="float: left;"><p>第${page}页</p></div>
		
		<div style="float: left;margin-left: 10px;">
			<button id="1" class="btn btn-primary"  onclick=turn(this);>第一页</button>
			<button id="2" name="page" class="btn btn-primary" onclick=turn(this);>上一页</button>
			<button id="3" name="page" class="btn btn-primary" onclick=turn(this);>下一页</button>
			<button id="4" class="btn btn-primary" onclick=turn(this);>最后页</button>
		</div>
		<div style="float: left;margin-left: 100px;">
			<form action="driveSchool/TeacherAction!seachTeacher" >
			<input id="page" name="page"  type="text"  pattern="^[0-9]*$" required  style="width: 25px" value=${page}  />
			
			<button class="btn btn-primary">确定</button>
			</form>
		</div>
				
			</div>
			</div>	
</div>
  </body>
</html>
