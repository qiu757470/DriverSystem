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
    
    <title>创建单个驾校界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
		#createManagerDiv{
			margin-left: 50px;
			margin-top: 30px;
		}
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
			var createSuccess = "<%=request.getAttribute("createSuccess")%>";
			if(createSuccess != null && (createSuccess == "true" || createSuccess == true)){
				if(!confirm('创建成功！是否继续创建？')){
					window.location.href = "transport/schoolManagementAction!showPage?nowPage=1";
				}
			}else if(createSuccess != null && (createSuccess == "false" || createSuccess == false)){
				alert("创建失败！");
			}
		});
		
		function checkForm(){
			var schoolNameCheck = $("#schoolNameCheck").val();
			var representNameCheck = $("#representNameCheck").val();
			if(schoolNameCheck == "true" && representNameCheck == "true"){	
				if(confirm('确认创建吗？')){
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
			}
		}
		
		function resetForm(){
			window.location.href = "transport/schoolManagementAction!createSchoolPage";
		}
		function cancel(){
			window.location.href = "transport/schoolManagementAction!showPage?nowPage=1";
		}
	</script>
  </head>
  
  <body>
      <div id="createManagerDiv">
      <h2 id="titleH">创建驾校</h2>
      <hr id="hrLine"/>
      <div style="clear:left"></div>
      <form action="transport/schoolManagementAction!createSchool" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return checkForm()">
      	<table cellspacing="10px">
      		<tr>
      			<td>
      				<input type="hidden" value='<s:property value="school.school_id"/>' name="createSchool.school_id"/>
      				<div class="textDiv"><label class="titleLabel">驾校名称：&emsp;&emsp;</label><input name="createSchool.school_name" id="schoolNameInput" type="text" placeholder="请输入驾校名" required pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+" 
						oninvalid="setCustomValidity('驾校名称格式错误（只能为数字、字母和中文的组合）');" maxlength="20" onblur="check(this)"
						oninput="setCustomValidity('');" /></div>
					<div class="checkDiv"><img id="schoolNameImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="schoolNameCheck" /></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人姓名：&emsp;&emsp;</label><input name="createSchool.school_representative_name" id="representNameInput" type="text" placeholder="请输入法人姓名" required  pattern="^[a-zA-Z\u4e00-\u9fa5]+"
						oninvalid="setCustomValidity('用户姓名格式错误（只能为字母和中文的组合）');" onblur="check(this)" maxlength="20"
						oninput="setCustomValidity('');" /></div>
					<div class="checkDiv"><img id="representNameImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="representNameCheck" /></div>
				</td>
				
      		</tr>
      			
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">法人身份证：&emsp;</label><input name="createSchool.school_representative_id" id="representIDInput" type="text" placeholder="请输入法人身份证" required  pattern="^[a-zA-Z0-9]+"
						oninvalid="setCustomValidity('身份证格式错误（只能为数字和字母的组合）');"  maxlength="18"
						oninput="setCustomValidity('');" /></div>
				</td>
				
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;法人电话号码：</label><input name="createSchool.school_representative_phone" id="representPhoneInput" type="text" placeholder="请输入法人固话/手机号码" pattern="^[0-9]+"
						oninvalid="setCustomValidity('电话号码格式错误（只能为数字的组合）');"  maxlength="18"
						oninput="setCustomValidity('');" /></div>
				</td>

      		</tr>
      		
      		<tr>

				<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在省：&emsp;</label><select id="schoolProvinceSelect" name="createSchool.school_province"></select></div>
      			</td>
				
				<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校所在市：&emsp;</label><select id="schoolCitySelect" name="createSchool.school_city"></select><select id="schoolAreaSelect" style="display:none"></select></div>
      			</td>
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校详细地址：</label><input name="createSchool.school_address" id="schoolAddressInput" type="text" placeholder="请输入驾校详细地址" /></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校电话：&emsp;&emsp;</label><input name="createSchool.school_phone" id="schoolPhoneInput" type="text" placeholder="请输入驾校固话/手机号码" /></div>
				</td>
      			
      			
      		</tr>
      		
      		<tr>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">经营许可证：&emsp;</label>
      				<input name="createSchool.school_certificate" id="certificateInput" type="text" placeholder="请输入经营许可证号" /></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;组织代码：&emsp;&emsp;</label>
      				<input name="createSchool.school_code" id="codeInput" type="text" placeholder="请输入组织代码" /></div>
      			</td>
      			
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校主页：&emsp;&emsp;</label>
      				<input name="createSchool.school_homepage" id="codeInput" type="text" placeholder="请输入驾校主页"  /></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校状态：&emsp;&emsp;</label>
      				<select name="createSchool.school_state" id="stateSelect"><option>未审核</option><option>审核通过</option><option>审核拒绝</option><option>倒闭</option></select>
      				</div>
      			</td>
      			
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv" style="margin-top: 20px"><input class="btn" type="submit" value="确定创建" /><input class="btn" type="button" value="重置" onclick="resetForm()"/><input class="btn" type="button" value="取消" onclick="cancel()"/></div>
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
