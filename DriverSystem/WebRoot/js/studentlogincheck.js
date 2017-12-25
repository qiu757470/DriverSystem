// JavaScript Document
$(document).ready(function(){
  $("#examname").blur(function(){
	  
	  var acc=$(this).val();
	  
	  if(acc==""){
			$(this).attr("placeholder","考试名称不能为空!");
	  }
  });
  
 
	  $("#zid").blur(function(){
		  
		  var zid=$(this).val();
		  
		  if(zid==""){
				$(this).attr("placeholder","准考证号不能为空!");
		  }
	 
	});
	  $("#sid").blur(function(){
		  
		  var sid=$(this).val();
		  
		  if(sid==""){
				$(this).attr("placeholder","身份证号不能为空!");
		  }
	 
	});
	  $("#name").blur(function(){
		  
		  var name=$(this).val();
		  
		  if(name==""){
				$(this).attr("placeholder","姓名不能为空!");
		  }
	 
	});
  
});


