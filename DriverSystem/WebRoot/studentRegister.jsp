<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学员报名页面</title>

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
<script src="js/jquery.js"></script>
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
$(function(){
$("#form1").on("submit",function(event){
  $(this).validate();
});

$("#form1").validate({
rules:{
      identi:{required:true,rangelength:[18,18]},
      name:{required:true,maxlength:5},
      sex:{required:true},
      phone:{required:true,rangelength:[11,11]},
      cmbProvince:{required:true},
      cmbCity:{required:true},
      school_name:{required:true},
      teacher_name:{required:true}
},

messages:{
      identi:{required:"必填",rangelength:"身份证位数应为18位"},
      name:{required:"必填",maxlength:"名字长度不能超过5个字符"},
      sex:{required:"必填"},
      phone:{required:"必填",rangelength:"手机号码应为11位"},
      cmbProvince:{required:"必填"},
      cmbCity:{required:"必填"},
      school_name:{required:"必填"},
      teacher_name:{required:"必填"}
},

});


$("#cmbCity").change(function(){
$.ajax({
url:"studentRegister/register!schoolselect",
type: "post",
data:{"cmbProvince":$("#cmbProvince").val(),"cmbCity":$("#cmbCity").val()},
success:function(e){
var jsonObj =  JSON.parse(e);//转换为json对象
for(var i=0;i<jsonObj.length;i++){  
        $("#school_name").append("<option value='"+jsonObj[i].school_id+"'>"+jsonObj[i].school_name+"</option>");
}
}
})

})


$("#school_name").change(function(){
$.ajax({
url:"studentRegister/register!selectteacher",
type: "post",
data:{"school_name":$("#school_name").val()},
success:function(e){
var jsonObj =  JSON.parse(e);//转换为json对象
        $("#teacher_name").children().remove()  
for(var i=0;i<jsonObj.length;i++){ 
        $("#teacher_name").append("<option value='"+jsonObj[i].teacher_id+"'>"+jsonObj[i].teacher_name+"</option>");
}
}
})

})



});


</script>
</head>

<body>
	<div align="center">
		<form action="studentRegister/register!register" method="post" id="form1">
		
		<div class="controls">
								<label id="time"></label> <input type="hidden"
									name="register_time" id="timeHidden">
							</div>
          <div>
          
          
			<p>
				身份证号码：<input type="text" name="identi" id="identi" />
			</p>
			<p>
				姓名：<input type="text" name="name" id="name" />
			</p>
			<p>
				性别：<select name="sex" id="sex">
					<option>男</option>
					<option>女</option>
				</select>
			</p>

			<p>
				电话号码：<input type="text" name="phone" id="phone" />
			</p>
			<p>
				所在省份：<select id="cmbProvince" name="cmbProvince"></select>
			</p>
			<p>
				所在市：<select id="cmbCity" name="cmbCity"></select>  
			</p>
			<p>
				<select id="cmbArea" name="cmbArea" style="display:none"></select> 
			</p>
			 
			<script type="text/javascript">addressInit('cmbProvince', 'cmbCity', 'cmbArea')</script>
			
			<p>驾校名称：<select name="school_name" id="school_name"></select> </p>
			
			<p>教练名称：<select name="teacher_name" id="teacher_name"></select></p>
			
            </div>
                
              <div > 
              <p><input type="submit" value="注册"/></p>
              
              <p><input type="reset" value="重置"/></p>
              </div>  
   
   

		</form>
	</div>
</body>
</html>
