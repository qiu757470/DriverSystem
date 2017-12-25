	<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<title>百度地图API自定义地图</title>
<!--引用百度地图API-->
<style type="text/css">
    html,body{margin:0;padding:0;}
    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>

    
    <title>修改单个驾校界面</title>
    <link rel="stylesheet" href="css/manager_css.css"/>
	<style type="text/css">
		#createManagerDiv{
			margin-left: 50px;
			margin-top: 0px;
		}
	    #hrLine{
	    	width: 1px;
	    	float:left;
	    }
	    #titleH{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#hrLine").animate({width:"50%"},"slow");
			$("#titleH").animate({opacity: "1"},"slow");
		});
		function back(){
				window.location.href = "transport/schoolManagementAction!showPage?nowPage=1";
		}
/* 		function homePage(tag){
			var url = $(tag).html();
			alert(url);
			$(tag).attr('href', url);
		} */
	</script>
	<script type="text/javascript">
		$(function(){
		    //创建和初始化地图函数：
		    function initMap(){
		        createMap();//创建地图
		        setMapEvent();//设置地图事件
		        addMapControl();//向地图添加控件
		     
		    }
		     
		    //创建地图函数：
		    function createMap(){
		        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
		        var point = new BMap.Point(116.395645,39.929986);//定义一个中心点坐标
		        map.centerAndZoom(point,12);//设定地图的中心点和坐标并将地图显示在地图容器中
		        window.map = map;//将map变量存储在全局
		         map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);      
				 var local = new BMap.LocalSearch(map, {      
				       renderOptions:{map: map}      
				 });
				 var province = "<%=((School)request.getAttribute("school")).getSchool_province()%>";      
				 var city = "<%=((School)request.getAttribute("school")).getSchool_city()%>";      
				 var address = "<%=((School)request.getAttribute("school")).getSchool_address()%>";  
				 var location = city + " " + address;    
				 local.search(location);
		    }    

		    //地图事件设置函数：
		    function setMapEvent(){
		        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
		        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		        map.enableKeyboard();//启用键盘上下左右键移动地图
		    }
		    
		    //地图控件添加函数：
		    function addMapControl(){
		        //向地图中添加缩放控件
			var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
			map.addControl(ctrl_nav);
		        //向地图中添加缩略图控件
			var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
			map.addControl(ctrl_ove);
		        //向地图中添加比例尺控件
			var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
			map.addControl(ctrl_sca);
		    }
		   
		    
		    initMap();//创建和初始化地图
	    });
	</script>
</head>

<body>
	<div id="createManagerDiv" style="float:left">
      <h2 id="titleH">驾校详情</h2>
      <hr id="hrLine"/>
      <div style="clear:left"></div>
      	<table cellspacing="10px">
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校名称：&emsp;</label><label class="contentLabel"><s:property value="school.school_name"/></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人姓名：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_representative_name"/></label></div>
				</td>
				
      		</tr>
      			
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人身份证：</label><label class="contentLabel"><s:property value="school.school_representative_id"/></label></div>
				</td>
				
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人电话号码：</label><label class="contentLabel"><s:property value="school.school_representative_phone"/></label></div>
				</td>

      		</tr>
      		
      		<tr>

				<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在省：</label><label class="contentLabel"><s:property value="school.school_province"/></label></div>
				</td>
				
				<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在市：&emsp;</label><label class="contentLabel"><s:property value="school.school_city"/></label></div>
				</td>
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾详细地址：</label><label class="contentLabel"><s:property value="school.school_address"/></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校电话号码：</label><label class="contentLabel"><s:property value="school.school_phone"/></label></div>
				</td>
      			
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">经营许可证：</label><label class="contentLabel"><s:property value="school.school_certificate"/></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">组织代码：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_code"/></label></div>
				</td>
      			
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校主页：&emsp;</label><a class="contentLabel" target="_blank" href="http://<s:property value="school.school_homepage" />"><s:property value="school.school_homepage" /></a></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校状态：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.school_state"/></label></div>
				</td>
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校学费：&emsp;</label><label class="contentLabel"><s:property value="school.school_charge"/></label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">管理员人数：&emsp;</label><label class="contentLabel"><s:property value="school.managerNum"/>人</label></div>
				</td>
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">教练人数：&emsp;</label><label class="contentLabel"><s:property value="school.teacherNum"/>人</label></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">学员人数：&emsp;&emsp;</label><label class="contentLabel"><s:property value="school.studentNum"/>人</label></div>
				</td>
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv" style="margin-top: 22px"><input class="btn" type="button" value="返回" onclick="back()"/></div>
      			</td>
      		</tr>
      	</table> 
      </div>
  <!--百度地图容器-->
  <div class="textDiv" style="margin-top: 8%;"><label class="titleLabel">&emsp;&emsp;&emsp;地图：&emsp;&emsp;</label></div>
  <div style="width:300px;height:300px;float:right;border:#ccc solid 1px;margin-top: 10px" id="dituContent"></div>
  
</body>

</html>