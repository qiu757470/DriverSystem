// JavaScript Document
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
     
    /*  $("#keylook").css('display','');
     alert( $("#keylook").val()); */
    // alert( $("#keylook").); 
  //  alert( document.getElementById('keylook').style.display=''); 
     
   // oTip.style.display = '';
     document.getElementById('keylook').style.display='';
 }
     else{
/*      $("#keylook").css('display','none'); */
     
   document.getElementById('keylook').style.display='none';
     } 
       
     
}
