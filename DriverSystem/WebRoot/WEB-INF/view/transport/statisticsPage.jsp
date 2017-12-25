<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据统计</title>
    <link rel="stylesheet" href="css/manager_css.css">

	<script type="text/javascript" src="js/jsAddress.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
	<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css">
	<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.min.css">
	<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
	<script type="text/javascript">
		$(function(){
			 var date=new Date;
 			var y=date.getFullYear(); 
			$year = $("#searchYear");
			for(var i=1949; i<=y; i++){
				$year.append('<option>' + i + '</option>');
			}
			$("#searchYear").val('2017');
			changeChart();
			var chartType = $("#chartType").val();
			
		});
		function changeType(tag){
			var staType = $(tag).val();
			if(staType == '驾校分布'){
				$("#searchYear").val('2017');
				$("#searchYear").attr('disabled', 'disabled');
			}else{
				$("#searchYear").removeAttr('disabled');
			}
		}
		function changeChart(){
			var chartType = $("#chartType").val();
			if(chartType == '地图'){
				$("#cmbProvince").attr('disabled', 'disabled');
				$("#cmbCity").attr('disabled', 'disabled');
				$("#searchSchool").attr('disabled', 'disabled');
				$("#cmbProvince").val('全部');
				 addressInit('cmbProvince', 'cmbCity', 'cmbArea');  
				 changeSchool();
				$("#cmbCity").val('全部');
				$("#searchSchool").val('全部');
			}else{
				$("#cmbProvince").removeAttr('disabled');
				$("#cmbCity").removeAttr('disabled');
				$("#searchSchool").removeAttr('disabled');
			}
		
		}
		function changeSchool(){
			var province = $("#cmbProvince").val();
			var city = $("#cmbCity").val();
			$.ajax({
				type:"post",
				url:"ajax/changeSchool",
				data:{
				ajaxProvince:province,
				ajaxCity:city
				},
				dataType: "json",
				success : function(data) {
					var msg = eval(data);
					if(msg != null && msg != ""){
						var html = "<option>全部</option>"
						for(var i=0; i<msg.length; i++){
							html += "<option>" + msg[i] + "</option>";
						}
						$("#searchSchool").html(html);
					}else{
						$("#searchSchool").html("<option selected='selected'>全部</option>");
					}
					var school_name = "<%= request.getAttribute("searchChart") != null ? ((SearchChart)request.getAttribute("searchChart")).getSchool_name(): null%>";
					if(school_name != null && school_name != "" && school_name != "null"){
						var $options = $("#searchSchool").children();
						var count = 0;
						for(var i=0; i<$options.length; i++){
							if($options[i].innerText == school_name){
								$options[i].setAttribute("selected", "selected");
								break;
							}else{
								count++;
							}
						}
						if(count == $options.length){
							$("#searchSchool").val("全部");
						}
					}else{
						$("#searchSchool").val("全部");
					}
				},
				error : function() {
					alert('请求有误');
				}
			
			});
		}
	</script>

  </head>
  
  <body>
	   <div id="searchDiv"><!-- 搜索用户的模块 -->
			<form action="transport/statisticsAction!search" method="post" enctype="application/x-www-form-urlencoded" >
				<div class="smallDiv">
					<label>省份：</label> <select id="cmbProvince" name="searchChart.school_province" onclick="changeSchool()"></select>
				</div>
				<div class="smallDiv">
					<label>城市：</label> <select id="cmbCity" name="searchChart.school_city" onclick="changeSchool()"></select>
					<select id="cmbArea" name="cmbArea" style="display: none"></select>  
				</div>
				<div class="smallDiv">
					<label>驾校：</label> <select id="searchSchool" name="searchChart.school_name" >
						<option>全部</option>
					</select>
				</div>
				<div style="clear:left"></div>
				<div class="smallDiv" style="margin-left: 100px">
					<label>图表类型：</label><select id="chartType" onclick="changeChart()">
						<option>柱状图/折线图</option>
						<option>地图</option>
					</select>
				</div>
				<div class="smallDiv">
					<label>年份：</label> 
					<select id="searchYear" name="searchChart.searchYear" style="width:80px"></select>
				</div>
				<div id="searchSubmitDiv" class="smallDiv">
					<input type="submit" value="显示数据" class="btn"/>
				</div>
			</form>
			<script type="text/javascript" src="<%=basePath%>js/jsAddress.js"></script> 
			<script type="text/javascript">  
	                 addressInit('cmbProvince', 'cmbCity', 'cmbArea');  
	        </script>  
		</div>
		<div style="clear:left"></div>
	    <div id="registerDiv" style="height:400px;border:1px solid #ccc;padding:10px;margin-top: 20px;"></div>
   		<div id="locationDiv" style="height:400px;border:1px solid #ccc;padding:10px;margin-top: 20px;display: none"></div>
    
	    <!--Step:2 Import echarts.js-->
	    <!--Step:2 引入echarts.js-->
	     <script src="js/dist_echarts.js"></script>
    
	    <script type="text/javascript">
		    // Step:3 conifg ECharts's path, link to echarts.js from current page.
		    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
		    require.config({
		        paths: {
		            echarts: 'http://echarts.baidu.com/build/dist'
		        }
		    });
		    
		    // Step:4 require echarts and use it in the callback.
		    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
		    require(
		        [
		            'echarts',
		            'echarts/chart/bar',
		            'echarts/chart/line',
		            'echarts/chart/map'
		        ],
		        function (ec) {
		            //--- 折柱 ---
		            var myChart = ec.init(document.getElementById('registerDiv'));
		            
		            myChart.setOption({
		            	title : {
					        text: '驾校与学员数据统计图表',
					        x:'center'
					    },
		                tooltip : {
		                    trigger: 'axis',
		                    //在这里设置
	       					formatter: '{a0}:{c0}人 <br/>{a1}:{c1}人<br/>{a2}:{c2}万元'
		                },
		                toolbox: {
		                    show : true,
		                    feature : {
		                        mark : {show: true},
		                        dataView : {show: true, readOnly: false},
		                        magicType : {show: true, type: ['line', 'bar']},
		                        restore : {show: true},
		                        saveAsImage : {show: true}
		                    }
		                },
		                calculable : true,
		                xAxis : [
		                    {
		                        type : 'category',
		                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		                    }
		                ],
		                yAxis : [
		                    {
		                        type : 'value',
		                        splitArea : {show : true}
		                    }
		                ],
		                legend: {
					        orient: 'horizontal',
					        x:'left',
					        left:'right',
					        data:['报名人数','毕业人数','报名金额']
					    },
		                series : [
		                    {
		                        name:'报名人数',
		                        type:'bar',
		                        data:[2.0, 4.0, 7.0, 23.0, 25.0, 76.0, 135.0, 162.0, 32.0, 20.0, 6.0, 3.0],
		                        label: {
			                     	 normal: {
			                     	 	    show: true,
			                     	 	    position: 'top',
				                   			formatter: '{a} 人'
				                   	},
				                },
		                    },
		                    {
		                        name:'毕业人数',
		                        type:'bar',
		                        data:[2.0, 5.0, 9.0, 26.0, 28.0, 70.0, 175.0, 182.0, 48.0, 18.0, 6.0, 2.0]
		                    }, 
		                    {
		                        name:'报名金额',
		                        type:'bar',
		                        data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
		                    }
		                    
		                ]
		            });
		            // --- 地图 ---
		            var myChart2 = ec.init(document.getElementById('locationDiv'));
		            myChart2.setOption({
		             	title : {
					        text: '全国驾校与学员数据统计图表',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: '{b}:{c}'
					    },
					    legend: {
					        orient: 'horizontal',
					        x:'left',
					        data:['报名人数','报名金额','毕业人数','驾校个数']
					    },
					    dataRange: {
					        min: 0,
					        max: 2500,
					        x: 'left',
					        y: 'bottom',
					        text:['高','低'],           // 文本，默认为数值文本
					        calculable : true
					    },
					    toolbox: {
					        show: true,
					        orient : 'vertical',
					        x: 'right',
					        y: 'center',
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    roamController: {
					        show: true,
					        x: 'right',
					        mapTypeControl: {
					            'china': true
					        }
					    },
					    series : [
					        {
					            name: '报名人数',
					            type: 'map',
					            mapType: 'china',
					            roam: false,
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:[
					                {name: '北京',value: Math.round(Math.random()*1000)},
					                {name: '天津',value: Math.round(Math.random()*1000)},
					                {name: '上海',value: Math.round(Math.random()*1000)},
					                {name: '重庆',value: Math.round(Math.random()*1000)},
					                {name: '河北',value: Math.round(Math.random()*1000)},
					                {name: '河南',value: Math.round(Math.random()*1000)},
					                {name: '云南',value: Math.round(Math.random()*1000)},
					                {name: '辽宁',value: Math.round(Math.random()*1000)},
					                {name: '黑龙江',value: Math.round(Math.random()*1000)},
					                {name: '湖南',value: Math.round(Math.random()*1000)},
					                {name: '安徽',value: Math.round(Math.random()*1000)},
					                {name: '山东',value: Math.round(Math.random()*1000)},
					                {name: '新疆',value: Math.round(Math.random()*1000)},
					                {name: '江苏',value: Math.round(Math.random()*1000)},
					                {name: '浙江',value: Math.round(Math.random()*1000)},
					                {name: '江西',value: Math.round(Math.random()*1000)},
					                {name: '湖北',value: Math.round(Math.random()*1000)},
					                {name: '广西',value: Math.round(Math.random()*1000)},
					                {name: '甘肃',value: Math.round(Math.random()*1000)},
					                {name: '山西',value: Math.round(Math.random()*1000)},
					                {name: '内蒙古',value: Math.round(Math.random()*1000)},
					                {name: '陕西',value: Math.round(Math.random()*1000)},
					                {name: '吉林',value: Math.round(Math.random()*1000)},
					                {name: '福建',value: Math.round(Math.random()*1000)},
					                {name: '贵州',value: Math.round(Math.random()*1000)},
					                {name: '广东',value: Math.round(Math.random()*1000)},
					                {name: '青海',value: Math.round(Math.random()*1000)},
					                {name: '西藏',value: Math.round(Math.random()*1000)},
					                {name: '四川',value: Math.round(Math.random()*1000)},
					                {name: '宁夏',value: Math.round(Math.random()*1000)},
					                {name: '海南',value: Math.round(Math.random()*1000)},
					                {name: '台湾',value: Math.round(Math.random()*1000)},
					                {name: '香港',value: Math.round(Math.random()*1000)},
					                {name: '澳门',value: Math.round(Math.random()*1000)}
					            ]
					        },
					        {
					            name: '报名金额',
					            type: 'map',
					            mapType: 'china',
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:[
					                {name: '北京',value: Math.round(Math.random()*1000)},
					                {name: '天津',value: Math.round(Math.random()*1000)},
					                {name: '上海',value: Math.round(Math.random()*1000)},
					                {name: '重庆',value: Math.round(Math.random()*1000)},
					                {name: '河北',value: Math.round(Math.random()*1000)},
					                {name: '安徽',value: Math.round(Math.random()*1000)},
					                {name: '新疆',value: Math.round(Math.random()*1000)},
					                {name: '浙江',value: Math.round(Math.random()*1000)},
					                {name: '江西',value: Math.round(Math.random()*1000)},
					                {name: '山西',value: Math.round(Math.random()*1000)},
					                {name: '内蒙古',value: Math.round(Math.random()*1000)},
					                {name: '吉林',value: Math.round(Math.random()*1000)},
					                {name: '福建',value: Math.round(Math.random()*1000)},
					                {name: '广东',value: Math.round(Math.random()*1000)},
					                {name: '西藏',value: Math.round(Math.random()*1000)},
					                {name: '四川',value: Math.round(Math.random()*1000)},
					                {name: '宁夏',value: Math.round(Math.random()*1000)},
					                {name: '香港',value: Math.round(Math.random()*1000)},
					                {name: '澳门',value: Math.round(Math.random()*1000)}
					            ]
					        },
					        {
					            name: '毕业人数',
					            type: 'map',
					            mapType: 'china',
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:[
					               {name: '北京',value: Math.round(Math.random()*1000)},
					                {name: '天津',value: Math.round(Math.random()*1000)},
					                {name: '上海',value: Math.round(Math.random()*1000)},
					                {name: '重庆',value: Math.round(Math.random()*1000)},
					                {name: '河北',value: Math.round(Math.random()*1000)},
					                {name: '河南',value: Math.round(Math.random()*1000)},
					                {name: '云南',value: Math.round(Math.random()*1000)},
					                {name: '辽宁',value: Math.round(Math.random()*1000)},
					                {name: '黑龙江',value: Math.round(Math.random()*1000)},
					                {name: '湖南',value: Math.round(Math.random()*1000)},
					                {name: '安徽',value: Math.round(Math.random()*1000)},
					                {name: '山东',value: Math.round(Math.random()*1000)},
					                {name: '新疆',value: Math.round(Math.random()*1000)},
					                {name: '江苏',value: Math.round(Math.random()*1000)},
					                {name: '浙江',value: Math.round(Math.random()*1000)},
					                {name: '江西',value: Math.round(Math.random()*1000)},
					                {name: '湖北',value: Math.round(Math.random()*1000)},
					                {name: '广西',value: Math.round(Math.random()*1000)},
					                {name: '甘肃',value: Math.round(Math.random()*1000)},
					                {name: '山西',value: Math.round(Math.random()*1000)},
					                {name: '内蒙古',value: Math.round(Math.random()*1000)},
					                {name: '陕西',value: Math.round(Math.random()*1000)},
					                {name: '吉林',value: Math.round(Math.random()*1000)},
					                {name: '福建',value: Math.round(Math.random()*1000)},
					                {name: '贵州',value: Math.round(Math.random()*1000)},
					                {name: '广东',value: Math.round(Math.random()*1000)},
					                {name: '青海',value: Math.round(Math.random()*1000)},
					                {name: '西藏',value: Math.round(Math.random()*1000)},
					                {name: '四川',value: Math.round(Math.random()*1000)},
					                {name: '宁夏',value: Math.round(Math.random()*1000)},
					                {name: '海南',value: Math.round(Math.random()*1000)},
					                {name: '台湾',value: Math.round(Math.random()*1000)},
					                {name: '香港',value: Math.round(Math.random()*1000)},
					                {name: '澳门',value: Math.round(Math.random()*1000)}
					            ]
					        },
					         {
					            name: '驾校个数',
					            type: 'map',
					            mapType: 'china',
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:[
					               {name: '北京',value: Math.round(Math.random()*1000)},
					                {name: '天津',value: Math.round(Math.random()*1000)},
					                {name: '上海',value: Math.round(Math.random()*1000)},
					                {name: '重庆',value: Math.round(Math.random()*1000)},
					                {name: '河北',value: Math.round(Math.random()*1000)},
					                {name: '河南',value: Math.round(Math.random()*1000)},
					                {name: '云南',value: Math.round(Math.random()*1000)},
					                {name: '辽宁',value: Math.round(Math.random()*1000)},
					                {name: '黑龙江',value: Math.round(Math.random()*1000)},
					                {name: '湖南',value: Math.round(Math.random()*1000)},
					                {name: '安徽',value: Math.round(Math.random()*1000)},
					                {name: '山东',value: Math.round(Math.random()*1000)},
					                {name: '新疆',value: Math.round(Math.random()*1000)},
					                {name: '江苏',value: Math.round(Math.random()*1000)},
					                {name: '浙江',value: Math.round(Math.random()*1000)},
					                {name: '江西',value: Math.round(Math.random()*1000)},
					                {name: '湖北',value: Math.round(Math.random()*1000)},
					                {name: '广西',value: Math.round(Math.random()*1000)},
					                {name: '甘肃',value: Math.round(Math.random()*1000)},
					                {name: '山西',value: Math.round(Math.random()*1000)},
					                {name: '内蒙古',value: Math.round(Math.random()*1000)},
					                {name: '陕西',value: Math.round(Math.random()*1000)},
					                {name: '吉林',value: Math.round(Math.random()*1000)},
					                {name: '福建',value: Math.round(Math.random()*1000)},
					                {name: '贵州',value: Math.round(Math.random()*1000)},
					                {name: '广东',value: Math.round(Math.random()*1000)},
					                {name: '青海',value: Math.round(Math.random()*1000)},
					                {name: '西藏',value: Math.round(Math.random()*1000)},
					                {name: '四川',value: Math.round(Math.random()*1000)},
					                {name: '宁夏',value: Math.round(Math.random()*1000)},
					                {name: '海南',value: Math.round(Math.random()*1000)},
					                {name: '台湾',value: Math.round(Math.random()*1000)},
					                {name: '香港',value: Math.round(Math.random()*1000)},
					                {name: '澳门',value: Math.round(Math.random()*1000)}
					            ]
					        }
					    ]
		            });
		        }
		    );
	    </script>

    
  </body>
</html>
