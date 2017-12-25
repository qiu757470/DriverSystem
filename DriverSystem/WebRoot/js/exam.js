// JavaScript Document
var time;
function startTime(){
	var t=new Date();
	var h=t.getHours();
	var m=t.getMinutes();
	var s=t.getSeconds();
	if(h<10){
		h="0"+h;
	}
	if(m<10){
		m="0"+m;
	}
	if(s<10){
		s="0"+s;
	}
	document.getElementById("p1").innerHTML=h+":"+m+":"+s;
	time=setTimeout("startTime()",1000)
}
function stopTime(){
	clearInterval(time);
}

function user(){
	p2.innerHTML="用户管理";
	

	
}
function power(){
	p2.innerHTML="权限管理"
}
function exampaper(){
	p2.innerHTML="试卷管理"
}
function exam(){
	p2.innerHTML="考试管理"
}
function add(){
	p2.innerHTML="考试管理"
}
function delect(){
	var a=document.getElementById("tr1");
	var b=document.getElementById("td1");
	a.remove(b);
}