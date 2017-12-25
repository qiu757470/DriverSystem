//大写键锁定
function  Lock(event){
	var e = event||window.event;
	var o = e.target||e.srcElement;
	var oTip = o.nextSibling;
	var keyCode  =  e.keyCode||e.which; // 按键的keyCode 
	var isShift  =  e.shiftKey ||(keyCode  ==   16 ) || false ; // shift键是否按住
	if (
			((keyCode >=   65   &&  keyCode  <=   90 )  &&   !isShift) // Caps Lock 打开，且没有按住shift键 
			|| ((keyCode >=   97   &&  keyCode  <=   122 )  &&  isShift)// Caps Lock 打开，且按住shift键
	){
		document.getElementById('keylook').style.display='';
	}
	else{
		document.getElementById('keylook').style.display='none';
	}  

}

$(document).ready(function(){
	//账号失去焦点事件
	$("#user_account").blur(function(){
		if(this.validity.valueMissing){
			$(this).attr("oninvalid","setCustomValidity('账号不能为空');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
		if(this.validity.patternMismatch){
			$(this).attr("oninvalid","setCustomValidity('账号格式错误！（账号只能为数字和字母的组合）');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
	});
	//密码失去焦点事件
	$("#user_password").blur(function(){
		if(this.validity.valueMissing){
			$(this).attr("oninvalid","setCustomValidity('密码不能为空');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
		if(this.validity.patternMismatch){
			$(this).attr("oninvalid","setCustomValidity('密码格式错误！（密码只能为数字和字母的组合）');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
	});
	//验证码失去焦点事件
	$("#J_codetext").blur(function(){
		if(this.validity.valueMissing){
			$(this).attr("oninvalid","setCustomValidity('验证码不能为空');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
		if(this.validity.patternMismatch){
			$(this).attr("oninvalid","setCustomValidity('验证码格式错误！（验证码只能为数字和字母的组合）');");
			$(this).val("");
			$("#loginBtn").click();
			return;
		}
		$("#ver_btn").click();
	});
});

function login(){
	var acc=$("#user_account").val();
	if(acc == null || acc == ""){
		$("#user_account").attr("placeholder","账号不能为空!");
		return false;
	}
	if(!acc.match("^[a-zA-Z0-9]*$")){
		$("#user_account").val("");
		$("#user_account").attr("placeholder","账号格式错误!");
		return false;
	}
	var pwd=$("#user_password").val();
	if(pwd == null || pwd==""){
		$("#user_password").attr("placeholder","密码不能为空!");
		return false;
	}
	if(!pwd.match("^[a-zA-Z0-9]*$")){
		$("#user_password").val("");
		$("#user_password").attr("placeholder","密码格式错误!");
		return false;
	}
	if(validate()){
		return true;	
	}else{
		$("#J_codetext").val("");
		$("#J_codetext").attr("placeholder","验证码错误!");
		return false;
	}

}
function reset(){
	$("#user_account").val("");
	$("#user_password").val("");
	$("#J_codetext").val("");

}