<%@ page language="java" import="java.util.*,org.great.bean.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新建单个驾校管理员界面</title>
	<link rel="stylesheet" type="text/css" href="css/manager_css.css">
	<style type="text/css">
		#createManagerDiv{
			margin-left: 50px;
			margin-top: 30px;
		}
	    hr{
	    	width: 1px;
	    	float:left;
	    }
	    h2{
	    	font-family: "幼圆";
	    	opacity: 0;
	    }
	</style>
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("hr").animate({width:"50%"},"slow");
			$("h2").animate({opacity: "1"},"slow");
			var createSuccess = "<%= request.getAttribute("createSuccess")%>";
			if(createSuccess != null && (createSuccess == "true" || createSuccess == true)){
				if(!confirm('创建成功！是否继续创建？')){
					window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
				}
			}else if(createSuccess != null && (createSuccess == "false" || createSuccess == false)){
				alert("创建失败！");
			}
		});
		function checkAccount(tag){
			var acc = $(tag).val();
			if(tag.validity.valueMissing){
				$(tag).val("");
				$(tag).attr('placeholder', '账号不能为空');
				$(tag).parent().next().children("img").attr('src','img/wrong.png');
				$("#userAccountCheck").val("false");
				return;
			}
			if(tag.validity.patternMismatch){
				$(tag).val("");
				$(tag).attr('placeholder', '账号格式错误（只能为数字和字母的组合）');
				$(tag).parent().next().children("img").attr('src','img/wrong.png');
				$("#userAccountCheck").val("false");
				return;
			}
			$.ajax({
			type:"post",
			url:"ajax/checkManagerAccount",
			data:{
			managerAccount:acc
			},
			dataType: "json",
			success : function(data) {
				var msg = eval(data);
				if(msg != null && msg == "notExist"){
					$(tag).parent().next().children("img").attr('src','img/correct.png');
					$("#userAccountCheck").val("true");
				}else if(msg != null && msg == "exist"){
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$("#userAccountCheck").val("false");
					$(tag).val("");
					$(tag).attr('placeholder', '账号已存在');
				}	
			},
			error : function() {
				alert('请求有误');
			}
			});
		}
		function checkForm(){
			var accCheck = $("#userAccountCheck").val();
			var pwdCheck = $("#userPwdCheck").val();
			var confirmCheck = $("#confirmPwdCheck").val();
			var nameCheck = $("#userNameCheck").val();
			var provinceCheck = $("#schoolProvinceCheck").val();
			var cityCheck = $("#schoolCityCheck").val();
			var schoolNameCheck = $("#schoolNameCheck").val();
			if(accCheck == "true" && pwdCheck == "true" && nameCheck == "true" && provinceCheck == "true" && cityCheck == "true" && schoolNameCheck == "true" && confirmCheck == "true"){
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
			if(id == 'userPwdInput'){
				if(tag.validity.valueMissing){
				$(tag).val("");
				$(tag).attr('placeholder', '密码不能为空');
				$(tag).parent().next().children("img").attr('src','img/wrong.png');
				$(tag).parent().next().children("input").val("false");
				return;
				}
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '密码格式错误（只能为字母和数字的组合）');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				$(tag).parent().next().children("img").attr('src','img/correct.png');
				$(tag).parent().next().children("input").val("true");
			}else if(id == 'confirmPwdInput'){
				if(tag.validity.valueMissing){
				$(tag).val("");
				$(tag).attr('placeholder', '密码不能为空');
				$(tag).parent().next().children("img").attr('src','img/wrong.png');
				$(tag).parent().next().children("input").val("false");
				return;
				}
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '密码格式错误（只能为字母和数字的组合）');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				if($(tag).val() != $("#userPwdInput").val()){
					$(tag).val("");
					$(tag).attr('placeholder', '两次密码输入不一致');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				$(tag).parent().next().children("img").attr('src','img/correct.png');
				$(tag).parent().next().children("input").val("true");
			}else if(id == 'userNameInput'){
				if(tag.validity.valueMissing){
				$(tag).val("");
				$(tag).attr('placeholder', '姓名不能为空');
				$(tag).parent().next().children("img").attr('src','img/wrong.png');
				$(tag).parent().next().children("input").val("false");
				return;
				}
				if(tag.validity.patternMismatch){
					$(tag).val("");
					$(tag).attr('placeholder', '姓名格式错误（只能为字母、数字和中文的组合）');
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
					return;
				}
				$(tag).parent().next().children("img").attr('src','img/correct.png');
				$(tag).parent().next().children("input").val("true");
			}else if(id == 'schoolNameSelect'){
				if($(tag).val() == '全部'){
					$(tag).parent().next().children("img").attr('src','img/wrong.png');
					$(tag).parent().next().children("input").val("false");
				}else if($(tag).val() != ""){
					$(tag).parent().next().children("img").attr('src','img/correct.png');
					$(tag).parent().next().children("input").val("true");
				}
				
			}
		}
		function changeSchool(){
			var province = $("#schoolProvinceSelect").val();
			var city = $("#schoolCitySelect").val();
			
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
						$("#schoolNameSelect").html(html);
					}else{
						$("#schoolNameSelect").html("<option selected='selected'>全部</option>");
					}
					var school_name = "<%= request.getAttribute("createUser") != null ? ((SchoolManager)request.getAttribute("createUser")).getSchool().getSchool_name(): null%>";
					if(school_name != null && school_name != "" && school_name != "null"){
						var $options = $("#schoolNameSelect").children();
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
							$("#schoolNameSelect").val("全部");
						}
					}else{
						$("#schoolNameSelect").val("全部");
					}
				},
				error : function() {
					alert('请求有误');
				}
				});
				if(province == '全部'){
					$("#schoolProvinceImg").attr('src', 'img/wrong.png');
					$("#schoolProvinceCheck").val("false");
				}else{
					$("#schoolProvinceImg").attr('src', 'img/correct.png');
					$("#schoolProvinceCheck").val("true");
				}
				if(city == '全部'){
					$("#schoolCityImg").attr('src', 'img/wrong.png');
					$("#schoolCityCheck").val("false");
				}else{
					$("#schoolCityImg").attr('src', 'img/correct.png');
					$("#schoolCityCheck").val("true");
				}
		}
		
		function resetForm(){
			window.location.href = "transport/managerManagementAction!createManagerPage";
		}
		function cancel(){
			window.location.href = "transport/managerManagementAction!showPage?nowPage=1";
		}
	</script>
  </head>
  
  <body>
      <div id="createManagerDiv">
      <h2>创建驾校管理员</h2>
      <hr/>
      <div style="clear:left"></div>
      <form action="transport/managerManagementAction!createManager" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return checkForm()">
      	<table cellspacing="5px" >
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">账号：&emsp;&emsp;&emsp;&emsp;</label><input name="createUser.school_manager_account" id="userAccountInput" type="text" placeholder="请输入账号" required pattern="^[a-zA-Z0-9]+" 
						oninvalid="setCustomValidity('用户账号格式错误（只能为数字、字母和的组合）');" onblur="checkAccount(this)" maxlength="20"
						oninput="setCustomValidity('');"/></div>
					<div class="checkDiv"><img id="userAccountImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="userAccountCheck" /></div>
				</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;姓名：&emsp;&emsp;&emsp;</label><input name="createUser.school_manager_name" id="userNameInput" type="text" placeholder="请输入姓名/昵称" required  pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+"
						oninvalid="setCustomValidity('用户姓名格式错误（只能为数字、字母和的组合）');" onblur="check(this)" maxlength="30" onblur="check(this)"
						oninput="setCustomValidity('');"/></div>
					<div class="checkDiv"><img id="userNameImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="userNameCheck" /></div>
				</td>
      			
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">初始密码：&emsp;&emsp;</label><input name="createUser.school_manager_password" id="userPwdInput" type="password" placeholder="请输入密码" required pattern="^[a-zA-Z0-9]+" 
      					oninvalid="setCustomValidity('初始密码格式错误（只能为数字、字母和中文的组合）');" onblur="check(this)" maxlength="20" onblur="check(this)"
						oninput="setCustomValidity('');"/></div>
      				<div class="checkDiv"><img id="userPwdImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="userPwdCheck" /></div>
      			</td>
      		
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;确认密码：&emsp;</label><input id="confirmPwdInput" type="password" placeholder="请再次输入密码" required pattern="^[a-zA-Z0-9]+" 
      					oninvalid="setCustomValidity('初始密码格式错误（只能为数字、字母和中文的组合）');" onblur="check(this)" maxlength="20" onblur="check(this)"
						oninput="setCustomValidity('');"/></div>
      				<div class="checkDiv"><img id="confirmPwdImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="confirmPwdCheck" /></div>
      			</td>
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">性别：&emsp;&emsp;&emsp;&emsp;</label><select name="createUser.school_manager_sex" id="userSexSelect"><option>男</option><option>女</option></select></div>
      				<div class="checkDiv"><img id="userNameImg" class="checkImg" src="img/correct.png" /></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;身份证：&emsp;&emsp;</label><input name="createUser.school_manager_identification" id="userIdentificationInput" type="text" placeholder="请输入身份证号码" maxlength="18"/></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      		
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">用户所在省：&emsp;</label><select id="userProvinceSelect" name="createUser.school_manager_province"></select></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;用户所在市：</label><select id="userCitySelect" name="createUser.school_manager_city"></select><select id="userAreaSelect" style="display: none"></select></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			<td>
      				<div class="textDiv"><label class="titleLabel">用户详细地址：</label><input type="text" id="userAddressInput" name="createUser.school_manager_address" placeholder="请输入详细地址" /></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;电话：&emsp;&emsp;&emsp;</label><input type="text" id="userPhoneInput" name="createUser.school_manager_phone" placeholder="请输入固话/手机/其他联系方式" /></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">账号状态：&emsp;&emsp;</label><select id="userStateSelect" name="createUser.school_manager_state"><option>可用</option><option>冻结</option></select></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校所在省：</label><select id="schoolProvinceSelect" name="createUser.school.school_province" onclick="changeSchool()"></select></div>
      				<div class="checkDiv"><img id="schoolProvinceImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="schoolProvinceCheck" /></div>
      			</td>
      		
      		</tr>
      		
      		<tr>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">驾校所在市：&emsp;</label><select id="schoolCitySelect" name="createUser.school.school_city" onclick="changeSchool()"></select><select id="schoolAreaSelect" style="display: none"></select></div>
      				<div class="checkDiv"><img id="schoolCityImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="schoolCityCheck" /></div>
      			</td>
      			
      			<td>
      				<div class="textDiv"><label class="titleLabel">&emsp;&emsp;驾校名：&emsp;&emsp;</label><select id="schoolNameSelect" name="createUser.school.school_name" onclick="check(this)"><option>全部</option></select></div>
      				<div class="checkDiv"><img id="schoolNameImg" class="checkImg" src="img/wrong.png" /><input type="hidden" value="false" id="schoolNameCheck" /></div>
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
              addressInit1('userProvinceSelect', 'userCitySelect', 'userAreaSelect');  
        </script>  
        
        <script type="text/javascript" src="js/jsAddress.js"></script> 
        <script type="text/javascript">  
              addressInit('schoolProvinceSelect', 'schoolCitySelect', 'schoolAreaSelect');  
        </script>  
      </div>
      
  </body>
</html>
