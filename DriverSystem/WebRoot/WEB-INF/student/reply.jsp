<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'seeTheReturn.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- start: CSS -->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link
	href='css/goo.css'
	rel='stylesheet' type='text/css'>
<!-- end: CSS -->


<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- end: Favicon -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	//翻页
	$(function() {
		//下一页
		$("#next").click(
			function() {
				$("#paging1").html(parseInt($("#paging1").html()) + 1);
				$("#paging2").html(parseInt($("#paging2").html()) + 1);
				$("#paging3").html(parseInt($("#paging3").html()) + 1);
				$("#paging4").html(parseInt($("#paging4").html()) + 1);
			}
		)
		//上一页
		$("#prev").click(
			function() {
				if (parseInt($("#paging1").html()) - 1 <= 0)
					return;
				$("#paging1").html(parseInt($("#paging1").html()) - 1);
				$("#paging2").html(parseInt($("#paging2").html()) - 1);
				$("#paging3").html(parseInt($("#paging3").html()) - 1);
				$("#paging4").html(parseInt($("#paging4").html()) - 1);

			}
		)
		//上传页数
		$(".pagingLi").click(function() {
			$(this).children().attr("href", "student/toRreply!paging?currentPage=" + $(this).children().html());
		});
	})
</script>
<script type="text/javascript">
//全选删除
 	var isCheckAll = true;
	function checkAll(objName) {
		var objNameList = document.getElementsByName(objName);
		if (null != objNameList && isCheckAll) {
			for (var i = 0; i < objNameList.length; i++) {
				objNameList[i].setAttribute('checked','checked');
			
			}
			isCheckAll = false;
		} else {

			for (var i = 0; i < objNameList.length; i++) {
				objNameList[i].removeAttribute("checked");
			}
			isCheckAll = !isCheckAll;
		}
	}
	
	 function deleSeltedRecords(){
	 
        var chckBox = document.getElementsByName("checks");  
         var num = chckBox.length;  
         var ischecked=false;
        
        var ids = "";  
         for(var index =0 ; index<num ; index++){  
             if(chckBox[index].checked){  
                 ids += chckBox[index].value + ",";                
             }  
         } 
 
         if(ids!=""){  
            if(window.confirm("确定删除所选项？")){  
      
         }else{  
            alert("请选择要删除的记录");  
			}  
        }  
}
</script>
</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>回复信息
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
				<form method="post" id="form1" action="student/toRreply!deleteAll">
					<table class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th><input  type="checkbox" onclick="checkAll('checks')"><input type="submit" onclick="deleSeltedRecords()"
									value="全选删除"></th>
								<th>回复主题</th>
								<th>回复时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="mails" var="m">
								<tr>
									<td><input type="checkbox" name="checks" value="<s:property value="#m.mail_id"/>"></td>
									<td class="center"><s:property value="#m.mail_theme" /></td>
									<td class="center"><s:property value="#m.mail_time" /></td>
									<td class="center"><span class="label label-success"><a
											href="student/toRreply!delRreplyInfo?delRreplyInfoId=<s:property value="#m.mail_id"/>"><i
												class="halflings-icon white trash"></i></a></span> <span
										class="label label-success"><a
											href="student/toRreply!toSeeReply?delRreplyInfoId=<s:property value="#m.mail_id"/>"><i
												class="halflings-icon white zoom-in"></i></a></span></td>
								</tr>
							</s:iterator>

						</tbody>
					</table>
				</form>
				<div class="pagination pagination-centered">
					<ul>
						<li><a href="javascript:void(0)" id="prev">前</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging1">1</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging2">2</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging3">3</a></li>
						<li class="pagingLi"><a href="javascript:void(0)"
							class="pagingA" id="paging4">4</a></li>
						<li><a href="javascript:void(0)" id="next">后</a></li>
					</ul>

				</div>
			</div>
		</div>
		<!--/span-->
	</div>

	<!--/row-->
	
<!-- start: JavaScript-->

	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>

	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>

	<script src="js/jquery.ui.touch-punch.js"></script>

	<script src="js/modernizr.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.cookie.js"></script>

	<script src='js/fullcalendar.min.js'></script>

	<script src='js/jquery.dataTables.min.js'></script>

	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>

	<script src="js/jquery.chosen.min.js"></script>

	<script src="js/jquery.uniform.min.js"></script>

	<script src="js/jquery.cleditor.min.js"></script>

	<script src="js/jquery.noty.js"></script>

	<script src="js/jquery.elfinder.min.js"></script>

	<script src="js/jquery.raty.min.js"></script>

	<script src="js/jquery.iphone.toggle.js"></script>

	<script src="js/jquery.uploadify-3.1.min.js"></script>

	<script src="js/jquery.gritter.min.js"></script>

	<script src="js/jquery.imagesloaded.js"></script>

	<script src="js/jquery.masonry.min.js"></script>

	<script src="js/jquery.knob.modified.js"></script>

	<script src="js/jquery.sparkline.min.js"></script>

	<script src="js/counter.js"></script>

	<script src="js/retina.js"></script>

	<script src="js/custom.js"></script>
	<!-- end: JavaScript-->
</body>
</html>
