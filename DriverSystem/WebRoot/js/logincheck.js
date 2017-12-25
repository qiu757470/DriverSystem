// JavaScript Document
$(document).ready(function(){
  $("#trans_user_account").blur(function(){
	 

	  var acc=$(this).val();
	  
	  
	  if(acc.length==0){
			$(this).attr("placeholder","账号不能为空!");
	  }else if(acc.length<3&&acc.length!=0){
		  $(this).val("");
		  $(this).attr("placeholder","账号不能少于3位数!"); 
		 
	  }
  });
  
 
	  $("#trans_user_password").blur(function(){
		  
		  var pwd=$(this).val();
		  
		  if(pwd==""){
				$(this).attr("placeholder","密码不能为空!");
		  }else if(pwd.length<3&&pwd.length!=0){
			  $(this).val("");
			  $(this).attr("placeholder","密码不能少于3位数!"); 
			 // $(this) .innerHTML ="账号须大于5位数"
			 
		  }

	 
	});
  
});


function login(){
	if($("#J_codetext").val()!=""){
		
	return true;	
	}else{
		
		$(this).attr("placeholder","验证码不能为空!");
		return false;
	}
	
}