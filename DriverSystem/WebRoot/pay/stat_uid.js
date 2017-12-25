//type 1:推广 2:平台 3:官网 4:支付中心
function stat_uid(type, param)
{
    if (typeof(type) == 'undefined') return false;
    var statUrl = 'http://log.mygame2.cn/stat_uid.htm';
    var flashVer=(function(){var ab=null,ag=[0,0,0];if(typeof navigator.plugins!="undefined"&&typeof navigator.plugins['Shockwave Flash']=="object"){ab=navigator.plugins["Shockwave Flash"].description;if(ab&&typeof navigator.mimeTypes!="undefined"&&navigator.mimeTypes["application/x-shockwave-flash"]){ab=ab.replace(/^.*\s+(\S+\s+\S+$)/,"$1");ag[0]=parseInt(ab.replace(/^(.*)\..*$/,"$1"),10);ag[1]=parseInt(ab.replace(/^.*\.(.*)\s.*$/,"$1"),10);ag[2]=/[a-zA-Z]/.test(ab)?parseInt(ab.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0}}else{if(typeof window.ActiveXObject!="undefined"){try{var ad=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");if(ad){ab=ad.GetVariable("$version");if(ab){ab=ab.split(" ")[1].split(",");ag=[parseInt(ab[0],10),parseInt(ab[1],10),parseInt(ab[2],10)]}}}catch(Z){}}}return ag.join('.')})();
    var uid=(function getCookie(sName){var aCookie=document.cookie.split('; ');for(var i=0;i<aCookie.length;i++){var aCrumb=aCookie[i].split('=');if(sName==aCrumb[0]){return unescape(aCrumb[1])}}return''})('login_id');
    var tcid=(function getCookie(sName){var aCookie=document.cookie.split('; ');for(var i=0;i<aCookie.length;i++){var aCrumb=aCookie[i].split('=');if(sName==aCrumb[0]){return unescape(aCrumb[1])}}return''})('tcid');
    statUrl += '?r=' + Math.random() + '&type=' + type + '&refer=' + encodeURIComponent(window.location+'') + '&fv=' + flashVer + (uid ? '&uid='+uid : '') + '&refer2=' + encodeURIComponent(document.referrer+'') + '&tcid=' + tcid + '&' + (param ? param : '');
    var div = document.createElement('div');
    div.className='__stat_uid_div';div.style.width='1px';div.style.height='1px';div.style.overflow='hidden';div.style.position='absolute';div.style.left='-100%';div.style.top='0';
    div.innerHTML = '<iframe scrolling="no" frameborder="0" style="width: 0; height: 0; opacity: 0;" src="'+statUrl+'" marginwidth="0" marginheight="0"></iframe>';
    document.body.appendChild(div);
};
