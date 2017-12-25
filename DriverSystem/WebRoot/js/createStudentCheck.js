// JavaScript Document
$(document).ready(function(){
  $("#student_identification").blur(function(){
	 
	  var acc=$(this).val();
	  var a =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;

	 var  reg=acc.match(a);
	  if(acc==""){
		  $(this).attr("placeholder","身份证号不能为空!");
	  }else{
		  if(reg==null){
			  $(this).val("");
			  $(this).attr("placeholder","身份证号输入不合法!"); 
		  }else{
			  
			  $("#student_password").val(acc.substring(acc.length-6));
		  }
	  }
	  
	  /*if(acc.length==0){
			$(this).attr("placeholder","账号不能为空!");
	  }else if(acc.length!=16){
		  $(this).val("");
		  $(this).attr("placeholder","身份证号不能少于16位数!"); 
		 
	  }*/
  });
  
 
	  $("#student_password").blur(function(){
		  
		  var pwd=$(this).val();
		  
		  if(pwd==""){
				$(this).attr("placeholder","密码不能为空!");
		  }else if(pwd.length<6&&pwd.length!=0){
			  $(this).val("");
			  $(this).attr("placeholder","密码不能少于6位数!"); 
			 // $(this) .innerHTML ="账号须大于5位数"
			 
		  }

	 
	});
	  
	  
	  
	  
	  $("#student_name").blur(function(){
			
			var name=$(this).val();
			
			if(name==""){
				$(this).attr("placeholder","名字不能为空!");
			}
			
			
		});


		$("#student_phone").blur(function(){
			
			var phone=$(this).val();
			
			
			if(phone==""){
				$(this).attr("placeholder","电话号码不能为空!");
			}else{
				  if(phone.length!=11){
					  $(this).val("");
					  $(this).attr("placeholder","电话号码输入不合法!"); 
				  }
			  }
			
			
		});
		$("#student_province").blur(function(){
			
			var phone=$(this).val();
			
			if(phone==""){
				$(this).attr("placeholder","省份不能为空!");
			}
			
			
		});
		$("#student_city").blur(function(){
			
			var phone=$(this).val();
			
			if(phone==""){
				$(this).attr("placeholder","市区不能为空!");
			}
			
			
		});

		$("#student_address").blur(function(){
			var address=$(this).val();
			
			
			
			if(address==""){
				$(this).attr("placeholder","地址不能为空!");
			}
			
			
		});
  
});






function check(){
	var check1=$("#student_identification").val();
	var check2=$("#student_password").val();
	var check3=$("#student_name").val();
	var check4=$("#student_phone").val();
	var check5=$("#student_address").val();
	var check6=$("#student_province").val();
	var check7=$("#student_city").val();
	
	if(check1==""||check2==""||check3==""||check4==""||check5==""||check6==""||check7==""){
		return false;
		
	}
}
function reset(){
	alert();
	$("#trans_user_account").val("");
	$("#trans_user_password").val("");
	$("#J_codetext").val("");
	
}