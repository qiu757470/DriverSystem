<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- JSTL声明 -->
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改单个驾校界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
	    #titleH{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	    #hrLine{
	    	width: 1px;
	    	float:left;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#hrLine").animate({width:"50%"},"slow");
			$("#titleH").animate({opacity: "1"},"slow");
			var alterSuccess = "<%=request.getAttribute("alterSuccess")%>";
			if(alterSuccess != null && (alterSuccess == "true" || alterSuccess == true)){
				if(!confirm('修改成功！是否继续修改？')){
					window.location.href = "transport/schoolManagementAction!showPage?nowPage=1";
				}
			}else if(alterSuccess != null && (alterSuccess == "false" || alterSuccess == false)){
				alert("修改失败！");
			}
			/* 性别、省市、状态、驾校省市名  */
			var school = "<%=request.getAttribute("school")%>";
			if(school != null && school != "" && school != "null"){
				var province = "<%=((School)request.getAttribute("school")).getSchool_province() != null ? ((School)request.getAttribute("school")).getSchool_province() : "全部"%>";
				$("#schoolProvinceSelect").val(province);
				
				addressInit1('schoolProvinceSelect', 'schoolCitySelect', 'schoolAreaSelect');  
				
				var city = "<%=((School)request.getAttribute("school")).getSchool_city() != null ? ((School)request.getAttribute("school")).getSchool_city() : "全部"%>";
				$("#schoolCitySelect").val(city);
				
				var state = "<%=((School)request.getAttribute("school")).getSchool_state() != null ? ((School)request.getAttribute("school")).getSchool_state() : "未审核"%>";
				$("#stateSelect").val(state);

			}
		});
		
		function checkForm(){
			var schoolNameCheck = $("#schoolNameCheck").val();
			var representNameCheck = $("#representNameCheck").val();
			if(schoolNameCheck == "true" && representNameCheck == "true"){	
				if(confirm('确认修改吗？')){
					return true;
				}
			}else{
				alert("请填入完整信息");
			}
			return false;
		}
		function check(tag){
			var id = $(tag).attr('id');
			if(id == 'schoolNameInput'){
				if(tag.validity.valueMissing){
					$(tag).val("");
					$(tag).attr('placeholder', '驾校名称不能为空');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '驾校名称格式错误（只能为中文、字母和数字的组合）');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				$(tag).parent().next().children("img").attr('src','img/correct.png');
				$(tag).parent().next().children("input").val("true");
			}else if(id == 'representNameInput'){
				if(tag.validity.valueMissing){
					$(tag).val("");
					$(tag).attr('placeholder', '法人姓名不能为空');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
				return;
				}
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '法人姓名格式错误（只能为中文和字母的组合）');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				$(tag).parent().next().children("img").attr('src','img/correct.png');
				$(tag).parent().next().children("input").val("true");
			}else if(id == 'chargeInput'){
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '金额格式错误（只能为纯数字）');
					return;
				}
			}
		}
		
		function resetForm(){
			var id = "<%=(School)request.getAttribute("school") != null ? ((School)request.getAttribute("school")).getSchool_id() : null%>";
			window.location.href = "transport/schoolManagementAction!alterSchoolPage?schoolId=" + id;
		}
		function cancel(){
			window.location.href = "transport/schoolManagementAction!showPage?nowPage=1";
		}
	</script>
  </head>
  
  <body>
      <div id="createManagerDiv">
      <h2 id="titleH">修改驾校</h2>
      <hr id="hrLine"/>
      <div style="clear:left"></div>
      <form action="transport/schoolManagementAction!alterSchool" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return checkForm()">
      	<table cellspacing="10px">
      		<tr>
      			<td>
      				<input type="hidden"  value='<s:property value="school.school_state"/>' name="alterSchool.school_state"/>
      				<input type="hidden" value='<s:property value="school.school_id"/>' name="alterSchool.school_id"/>
      				<div class="textDiv"><label class="titleLabel">驾校名称：&emsp;&emsp;</label><input name="alterSchool.school_name" id="schoolNameInput" type="text" placeholder="请输入驾校名" required pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+" 
						oninvalid="setCustomValidity('驾校名称格式错误（只能为数字、字母和中文的组合）');" maxlength="20" onblur="check(this)"
						oninput="setCustomValidity('');" value='<s:property value="school.school_name"/>'/></div>
					<div class="checkDiv"><img id="schoolNameImg" class="checkImg" src="img/correct.png" /><input type="hidden" value="true" id="schoolNameCheck" /></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人姓名：&emsp;&emsp;</label><input name="alterSchool.school_representative_name" id="representNameInput" type="text" placeholder="请输入法人姓名" required  pattern="^[a-zA-Z\u4e00-\u9fa5]+"
						oninvalid="setCustomValidity('用户姓名格式错误（只能为字母和中文的组合）');" onblur="check(this)" maxlength="20"
						oninput="setCustomValidity('');" value='<s:property value="school.school_representative_name"/>'/></div>
					<div class="checkDiv"><img id="representNameImg" class="checkImg" src="img/correct.png" /><input type="hidden" value="true" id="representNameCheck" /></div>
				</td>
				
      		</tr>
      			
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人身份证：&emsp;</label><input name="alterSchool.school_representative_id" id="representIDInput" type="text" placeholder="请输入法人身份证" required  pattern="^[a-zA-Z0-9]+"
						oninvalid="setCustomValidity('身份证格式错误（只能为数字和字母的组合）');"  maxlength="18"
						oninput="setCustomValidity('');" value='<s:property value="school.school_representative_id"/>'/></div>
				</td>
				
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人电话号码：</label><input name="alterSchool.school_representative_phone" id="representPhoneInput" type="text" placeholder="请输入法人固话/手机号码" pattern="^[0-9]+"
						oninvalid="setCustomValidity('电话号码格式错误（只能为数字的组合）');"  maxlength="18"
						oninput="setCustomValidity('');" value='<s:property value="school.school_representative_phone"/>'/></div>
				</td>

      		</tr>
      		
      		<tr>

				<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在省：&emsp;</label><select id="schoolProvinceSelect" name="alterSchool.school_province"></select></div>
      			</td>
				
				<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校所在市：&emsp;</label><select id="schoolCitySelect" name="alterSchool.school_city"></select><select id="schoolAreaSelect" style="display:none"></select></div>
      			</td>
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校详细地址：</label><input name="alterSchool.school_address" id="schoolAddressInput" type="text" placeholder="请输入驾校详细地址" 
						value='<s:property value="school.school_address"/>'/></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校电话：&emsp;&emsp;</label><input name="alterSchool.school_phone" id="schoolPhoneInput" type="text" placeholder="请输入驾校固话/手机号码" 
						value='<s:property value="school.school_phone"/>'/></div>
				</td>
      			
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">经营许可证：&emsp;</label>
      				<input name="alterSchool.school_certificate" id="certificateInput" type="text" placeholder="请输入经营许可证号"  value='<s:property value="school.school_certificate"/>'/></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;组织代码：&emsp;&emsp;</label>
      				<input name="alterSchool.school_code" id="codeInput" type="text" placeholder="请输入组织代码"  value='<s:property value="school.school_code"/>'/></div>
      			</td>
      			
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校主页：&emsp;&emsp;</label>
      				<input name="alterSchool.school_homepage" id="homePageInput" type="text" placeholder="请输入驾校主页"  value='<s:property value="school.school_homepage"/>'/></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校学费：&emsp;&emsp;</label>
      				<input name="alterSchool.school_charge" id="chargeInput" type="text" placeholder="请输入驾校学费"  value='<s:property value="school.school_charge"/>' pattern="^[0-9]+"
      				oninvalid="setCustomValidity('驾校名称格式错误（只能为数字、字母和中文的组合）');" maxlength="10" onblur="check(this)" oninput="setCustomValidity('');"/></div>
      			</td>
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv" style="margin-top: 20px"><input class="btn" type="submit" value="确定修改" /><input class="btn" type="button" value="重置" onclick="resetForm()"/><input class="btn" type="button" value="取消" onclick="cancel()"/></div>
      			</td>
      		</tr>
      	</table>
      	</form>
        <script type="text/javascript" src="js/jsAddress1.js"></script> 
        <script type="text/javascript">  
              addressInit1('schoolProvinceSelect', 'schoolCitySelect', 'schoolAreaSelect');  
        </script>   
      </div>
      
  </body>
</html>
