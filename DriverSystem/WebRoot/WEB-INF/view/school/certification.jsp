<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'certification.jsp' starting page</title>
    
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
<script type="text/javascript" src="js/jsAddress.js"></script>

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

$("#form1").on("submit",function(event){
   alert("iii")
  $(this).validate();
});
$(function(){
 if($.validator){
    $.validator.prototype.elements = function () {
        var validator = this,
        rulesCache = {};
        // Select all valid inputs inside the form (no submit or reset buttons)
        return $( this.currentForm )
        .find( "input, select, textarea, [contenteditable]" )
        .not( ":submit, :reset, :image, :disabled" )
        .not( this.settings.ignore )
        .filter( function() {
            var name = this.id || this.name || $( this ).attr( "name" ); // For contenteditable
            if ( !name && validator.settings.debug && window.console ) {
                console.error( "%o has no name assigned", this );
            }
            // Set form expando on contenteditable
            if ( this.hasAttribute( "contenteditable" ) ) {
                this.form = $( this ).closest( "form" )[ 0 ];
            }
            // Select only the first element for each name, and only those with rules specified
            if (name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
                return false;
            }
            rulesCache[ name ] = true;
            return true;
        } );
    }
}
 
$("#form1").validate({
rules:{
      group_code:{required:true},
      agree_acount:{required:true},
      sc_name:{required:true},
      cmbProvince:{required:true},
      cmbCity:{required:true},
      address:{required:true},
      telephone:{required:true},
      person:{required:true},
      id:{required:true,rangelength:[18,18]},
      mobilephone:{required:true,rangelength:[11,11]},
      homepage:{required:true},
      some:{required:true},
      sc_charge:{required:true}    
},

messages:{
      group_code:{required:"必填"},
      agree_acount:{required:"必填"},
      sc_name:{required:"必填"},
      cmbProvince:{required:"必填"},
      cmbCity:{required:"必填"},
      address:{required:"必填"},
      telephone:{required:"必填"},
      person:{required:"必填"},
      id:{required:"必填",rangelength:"身份证长度为18位"},
      mobilephone:{required:"必填",rangelength:"电话号码长度为11位"},
      homepage:{required:"必填"},
      some:{required:"必填"},
      sc_charge:{required:"必填"}
},

});
$("#cmbProvince").val("<%=((School)request.getAttribute("school")).getSchool_province()%>");
 addressInit('cmbProvince', 'cmbCity', 'cmbArea');
  $("#cmbCity").val("<%=((School)request.getAttribute("school")).getSchool_city()%>");
  
});



// 	function statecheck(){
// 	var a=$("#state").val();
// 	alert(a);
// 	if(a=='未审核'){
// 	$("#form1").attr("action","driveSchool/upload!update")
// 	}else if(a=='审核拒绝'){
// 	$("#form1").attr("action","driveSchool/upload!update")
// 	}
	
// 	}

	
	</script>
</head>
  
  <body onload="display()">
  <div id="div_all" align="center">
  <form method="post" id="form1" enctype="multipart/form-data" action="driveSchool/upload!update">
  <h3>审核状态:<input type="text" id="state" name="state" value="${sessionScope.state}" style="border-style: none; font-size: 20px" readonly = "readonly"/>
 <input type="text" id="refuse_reason" style="border-style: none; font-size: 20px" readonly = "readonly" name="reason" value="<s:property value='school.school_refuse_reason'/>"/></h3>
    <div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon white align-justify"></i><span
						class="break"></span>驾校审核
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
     <td><span style="color:#F00">*</span>组织代码：<input type="text" id="group_code" name="group_code" value="<s:property value='school.school_code'/>"/></td>
     <td><span style="color:#F00">*</span>准经营许可账号：<input type="text" id="agree_acount" name="agree_acount" value="<s:property value='school.school_certificate'/>"/> </td>
     </tr>
     
     <tr>
     <td><span style="color:#F00">*</span>驾校名称：<input type="text" id="sc_name" name="sc_name" value="<s:property value='school.school_name'/>"/> </td>
     <td><span style="color:#F00">*</span>驾校收费：<input type="text" id="sc_charge" name="sc_charge" value="<s:property value='school.school_charge'/>"/></td>
     
     </tr>
     
     
     <tr>
     <td><span style="color:#F00">*</span>所在省份：<select id="cmbProvince" name="cmbProvince"></select></td>
     <td><span style="color:#F00">*</span>所在市：<select id="cmbCity" name="cmbCity" ></select>  </td>
     <td> <select id="cmbArea" name="cmbArea" style="display:none"></select> </td>
</tr>
     
     <tr>
     <td><span style="color:#F00">*</span>详细地址：<input type="text" id="address" name="address" value="<s:property value='school.school_address'/>"/></td>
     <td><span style="color:#F00">*</span>联系电话：<input type="text" id="telephone" name="telephone" value="<s:property value='school.school_phone'/>"/></td>
     </tr>
     
     <tr>
     <td><span style="color:#F00">*</span>法人代表：<input type="text" id="person" name="person" value="<s:property value='school.school_representative_name'/>"/></td>
     <td><span style="color:#F00">*</span>法人身份证号：<input type="text" id="id" name="id" value="<s:property value='school.school_representative_id'/>"/></td>
     </tr>
     
     <tr>
     <td><span style="color:#F00">*</span>法人电话：<input type="text" id="mobilephone" name="mobilephone" value="<s:property value='school.school_representative_phone'/>"/></td>
     <td><span style="color:#F00">*</span>驾校官网：<input type="text" id="homepage" name="homepage" value="<s:property value='school.school_homepage'/>"/></td>
     </tr>
     
     <tr>
     <td><span style="color:#F00">*</span>法人照片：<input type="file" id="picture" name="some" value="浏览"/></td>
     <td><span style="color:#F00">*</span>其他认证资料：<input type="file" id="otherdata" name="some" value="浏览"/></td>
     </tr>
     </table>
     <input type="hidden" name="oldaccount" value="<s:property value='school.school_id'/>"/>
   <input type="submit" value="确认发送" />
  
  
  
  </div>
  </div>
  </div>
  </form>

  </div>
   <script type="text/javascript">addressInit('cmbProvince', 'cmbCity', 'cmbArea')</script>
  </body>
</html>