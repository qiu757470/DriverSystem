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
    
    <title>My JSP 'updatePwd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.validate.min.js"></script>
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

$(function(){

$("#form1").on("submit",function(event){
  $(this).validate();
});
$("#form1").validate({
　onsubmit:true,// 是否在提交是验证
　onfocusout:false,// 是否在获取焦点时验证
　onkeyup :false,// 是否在敲击键盘时验证

rules:{
      oldpwd:{required:true,rangelength:[6,12]},
      newpwd:{required:true,rangelength:[6,12]},
      confirmpwd:{required:true,rangelength:[6,12],equalTo: "#newpwd"}
},

messages:{
      oldpwd:{required:"请输入初始密码",rangelength:"密码长度6到12位"},
      newpwd:{required:"请输入新密码",rangelength:"密码长度6到12位"},
      confirmpwd:{required:"请输入确认密码",equalTo: "两次密码输入不一致",rangelength:"密码长度6到12位"}
},
submitHandler: function(form) { //通过之后回调
//进行ajax传值
$.ajax({
　　url :"driveSchool/updpwd",
　　type : "post",
     async:true,//请求是否异步，默认为异步，这也是ajax重要特性  
　　dataType : "text",
　　 data:{"oldpwd":$("#oldpwd").val(),"newpwd":$("#newpwd").val(),
             "confirmpwd":$("#confirmpwd").val(),"pwd_id":$("#pwd_id").val()}, 
　　success : function(req) {
　　　　  if(req=='correct'){
       alert("密码修改成功！！！");
       }else if(req=='notcorr'){
        alert("旧密码输入错误！！！");
       }else{
       alert("nothing")
       }
　　}
});
},
invalidHandler: function(form, validator) {return false;}
}); 


});



</script>

</head>
  
  <body>
  
  <div align="center">
  
  
  <form id="form1" method="post">
      <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>用户名：<span><s:property value="schoolManager.school_manager_name"/> </span>
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
				<table  >
  
  
 <tr> <td>旧密码：<input type="password" name="oldpwd" id="oldpwd" placeholder="输入旧密码"  /> </td></tr>
  <tr><td>新密码：<input type="password" name="newpwd" id="newpwd" placeholder="输入新密码" />  </td></tr>
  <tr><td>确认密码： <input type="password" name="confirmpwd" id="confirmpwd" placeholder="再次输入密码" /></td> </tr>
  <tr> <td><input type="hidden" name="pwd_id" id="pwd_id" value="<s:property value="schoolManager.school_manager_id"/>"/></td> </tr>
 <tr> <td><label id="label"></label></tr>
  
  
  
 </table>
 </div>
 </div>
 </div>
  <input type="submit" value="确认修改" class="btn btn-primary"/>
  </form>
  </div>
  </div>
  </div>
  </div>
  </body>
</html>
