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

<title>My JSP 'newExam.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<script src="js/jquery-1.10.2.js"></script>

<script src="js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css">
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.min.css">
<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext 
'rel='stylesheet' type='text/css'>
<!-- <link href="css/OSTA.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript">
 $(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
       dateFormat: 'yy-mm-dd'
    });
     
   $( "#datepicker1" ).timepicker({
   
    });


 $("input:radio[name='exam_name']").change(function(){
 var cource_id=$(this).val();
 $.ajax({
    type:"post",
    url:"driveSchool/schoolCertified!select_exam_stu",
    data:{cource_id:cource_id},
    dataType:"json",
    success:function(res) {
    var json = jQuery.parseJSON(res);
    var T=document.getElementsByTagName('table').item(0);
    var tr1 = "<tr><th><input type='checkbox' name='checkall' id='checkall' onclick='isselect()'/>全选</th><th>姓名</th><th>性别</th><th>身份证</th><th>已学学时</th><th>阶段</th></tr>";
     $.each(json, function(idx, obj){
     tr1+="<tr><td><input type='checkbox' name='check' id='check' value="+obj.student_id+" />"+(idx+1)+"</td> <td>"+obj.student_name+"</td><td>"+obj.student_sex+"</td><td>"+obj.student_identification+"</td><td>"+obj.studentCouse.student_course_time+"</td><td>"+obj.course.course_name+"</td></tr>";
    });
   T.innerHTML=tr1;
 }
  });
 
 
});
 
$("#creatall").click(function(){
var a=confirm("确认创建？");
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
 
 
 
 
});
 
	//全选、反选功能
	function isselect() {
		var checket = document.getElementById("checkall");
		var checkets = document.getElementsByName("check");
		if (checket.checked) {
			//alert(checkets.length);
			for (i = 0; i < checkets.length; i++) {
				checkets[i].checked = true;
			}
		} else {
			for (i = 0; i < checkets.length; i++) {
				checkets[i].checked = false;
			}
		}


	}
 
  
</script>
</head>





<body>
 
	<div align="center">
	<div class="row-fluid sortable">
		<div class="box span12">
		
			<div class="box-content">
		<form action="driveSchool/schoolCertified!conNewExam" method="post">
			<div>
				<p>
					考试日期：<input type="text" id="datepicker" name="examdate">
				</p>
				<p>
					具体时间：<input type="text" id="datepicker1" name="examtime">
				</p>
				<p>
					考试地点：<input type="text" name="examplace">
				</p>
                <p>
                                         负责教练：
                        <select name="coatch">
                        <s:iterator value="exam_teacher" var="tea">
                        <option><s:property value="#tea.teacher_name"/></option>
                        </s:iterator>
                        </select>
                
                </p>
				<p>
					考试科目：
				
					
					<input type="radio" name="exam_name" checked="checked" value="1" />科目一 
					<input type="radio" name="exam_name" value="2" />科目二 
					<input type="radio" name="exam_name" value="3" />科目三
					<input type="radio" name="exam_name" value="4" />科目四 
					
					
				</p>
                
              	</div> 
				<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>参与考生
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
						<th><input type="checkbox" name="checkall" id="checkall" onclick="isselect()"/>全选</th>
						<th>姓名</th>
						<th>性别</th>
						<th>身份证</th>
						<th>已学学时</th>
						<th>阶段</th>
					</tr>
					
					<s:iterator value="exam_student" var="st" status="cou">
						<tr>
							<td><input type="checkbox" name="check" id="check" value="<s:property value="#st.student_id" />" />
							<s:property value="(#cou.index)+1" /></td>
							<td><s:property value="#st.student_name" /></td>
							<td><s:property value="#st.student_sex" /></td>
							<td ><s:property value="#st.student_identification" /></td>
							<td ><s:property value="#st.studentCouse.student_course_time" />学时(满)</td>
							<td ><s:property value="#st.course.course_name" /></td>
						</tr>
					</s:iterator>
				</table>
				</div>
				</div>
				</div>
				
		

			<div>
				<input type="submit" id="creatall" value="确认创建" class="btn btn-primary" /> 
			</div>


    

		</form>
</div>
	</div>
	</div>
	</div>

</body>
</html>
