<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'school.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style=" margin-top:20px; margin-left:5%">
        选择科目：<select style="width:200px; height:30px"> 
       <option selected="selected">科目一</option> 
            <option>科目二</option>
			<option>科目三</option>	
            <option>科目四</option>			
			
		</select><br />
      
</div>
<div style="margin-top:20px; margin-left:5%">
  科目状态：<opan>已完成</opan>
</div>
<div id="u_d">
  <table id="c1">
    <tr>
      <th>序号</th>
      <th>考核项目</th>
      <th >已进行的学时</th>
      <th>未完成的学时</th>
      <th >总学时</th>
    </tr>
    <tr >
      <td>1</td>
      <td>zs1</td>
      <td>r_1002</td>
      <td>张三1</td>
      <td>25</td>

    </tr>
    <tr >

      <td>2</td>
      <td>zs2</td>
      <td>r_1002</td>
      <td>张三2</td>
      <td>20</td>

    </tr>
    <tr>
      <td>3</td>
      <td>zs3</td>
      <td>r_1002</td>
      <td>张三3</td>
      <td>18</td>
    </tr>
  </table>
</div>
  </body>
</html>
