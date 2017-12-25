<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关于界面</title>
    <link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
		#aboutDiv{
			margin-left: 50px;
			margin-top: 30px;
			opacity: 0;
		}
		#detailDiv{
			margin-left: 20px;
		}
		h3{
			float:left;
		}
		h4{
			float:left;
			color:gray;
		}
		hr {
			float: left;
			width: 580px;
		}
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#aboutDiv").animate({opacity:1},1000);
		
		});
		function back(){
			window.location.href = "transport/transportMain";
		}
	</script>
  </head>
  
  <body>
    <div id="aboutDiv">
    	<img src="img/logo1.png" style="background-color: orange"/>
    	<h1>驾驶员培训管理系统</h1>
    	<div id="detailDiv">
	    	<h3>版本号：&emsp;</h3><h4>v1.0.1</h4><div style="clear:left"></div>
	    	<h3>版权所有：</h3><h4>厦门市传一信息科技有限公司</h4><div style="clear:left"></div>
	    	<h3>官方网址：</h3><h4><a href="http://www.xmgreat.org/" target="_blank">http://www.xmgreat.org</a></h4>
    		<div style="margin-top: 20px">&emsp;&emsp;&emsp;&emsp;<input type="button" class="btn" onclick="back()"value="返回"/></div><div style="clear:left"></div>
    	</div>
    	<hr/>
    	
    </div>
  </body>
</html>
