/**
 * 网站第三方登陆公共js
 *
 * js依赖
 * jquery 1.4.2+
 */
window.J = window.J || {};
(function($){
J.OtherLogin = {
    config: {
        url: 'http://www.game2.cn/oauthGateway/op/toAuthorize/', //todo
        remark: '',  // 回调备注
        isOpen: 1,   // 是否新窗口打开
        isTop: 1,    // 回调时，是否top重定向
        tplId: 'default', // 默认模板id
        tpl_default: ''
            +'<div class="otherLogin" data-init="1">' // <div class="otherLoginContent"></div>
            +    '<span class="otherLoginLabel">其他登录方式：</span>'
            +    '<div class="third">'
            +        '<a href="javascript:void(0)" title="微信登录"   data-code="weixin" class="otherLoginItem icon3 weixin"></a>'
            +        '<a href="javascript:void(0)" title="QQ登录"     data-code="qq"     class="otherLoginItem icon3 qq"></a>'
            +        '<a href="javascript:void(0)" title="微博登录"   data-code="weibo"  class="otherLoginItem icon3 weibo"></a>'
            +        '<a href="javascript:void(0)" title="支付宝登录" data-code="alipay" class="otherLoginItem icon3 alipay"></a>'
            +    '</div>'
            +'</div>',
        tpl_bind: ''
            +'<ul class="bindBox otherLogin" data-init="1">'
            +   '<li class="bindAct">'
            +       '<div class="bindItm">'
            +           '<span><i class="ico3 ico_weixin"></i><strong>绑定微信账号</strong></span><a href="javascript:void(0)">选择</a>'
            +       '</div>'
            +       '<div class="bindDrap clearfix" style="display: block;height:80px;">'
            +           '<p class="pLogo"><span class="weixin"></span><var class="v2"></var><span class="g2"></span></p>'
            +           '<div class="bindBtn">'
            +               '<span>绑定后，即可使用微信登录哥们网平台</span>'
            +               '<a href="javascript:void(0)" title="绑定微信账号" data-code="weixin" class="otherLoginItem">立即绑定</a>'
            +           '</div>'
            +       '</div>'
            +   '</li>'
            +   '<li>'
            +       '<div class="bindItm">'
            +           '<span><i class="ico3 ico_qq"></i><strong>绑定QQ账号</strong></span><a href="javascript:void(0)">选择</a>'
            +       '</div>'
            +       '<div class="bindDrap clearfix">'
            +           '<p class="pLogo"><span class="qq"></span><var></var><span class="g2"></span></p>'
            +           '<div class="bindBtn">'
            +               '<span>绑定后，即可使用QQ登录哥们网平台</span>'
            +               '<a href="javascript:void(0)" title="绑定QQ账号" data-code="qq" class="otherLoginItem">立即绑定</a>'
            +           '</div>'
            +       '</div>'
            +   '</li>'
            +   '<li>'
            +       '<div class="bindItm">'
            +           '<span><i class="ico3 ico_weibo"></i><strong>绑定新浪微博账号</strong></span><a href="javascript:void(0)">选择</a>'
            +       '</div>'
            +       '<div class="bindDrap clearfix">'
            +           '<p class="pLogo"><span class="weibo"></span><var></var><span class="g2"></span></p>'
            +           '<div class="bindBtn">'
            +               '<span>绑定后，即可使用微博登录哥们网平台</span>'
            +               '<a href="javascript:void(0)" title="绑定微博账号" data-code="weibo" class="otherLoginItem">立即绑定</a>'
            +           '</div>'
            +       '</div>'
            +   '</li>'
            +'</ul>',
        tpl_mas: ''
            +'<div class="otherLogin" data-init="1">'
            +    '<span class="otherLoginLabel">其他登录方式：</span>'
            +    '<div class="third">'
            +        '<a href="javascript:void(0)" title="QQ登录"     data-code="qq"     class="otherLoginItem icon3 qq"></a>'
            +        '<a href="javascript:void(0)" title="微信登录"   data-code="weixin" class="otherLoginItem icon3 weixin"></a>'
            +        '<a href="javascript:void(0)" title="微博登录"   data-code="weibo"  class="otherLoginItem icon3 weibo"></a>'
            +    '</div>'
            +'</div>'
    },
    init: function(config){
        $.extend(this.config, config);
        this.setDomain();
        this.initOtherLogin();
        this.enableOtherLogin();
        if (this.config.tplId == 'bind') {
            this.enableBindAct();
        }
    },
    setDomain: function(){
        var dArr = document.domain.split(".");
        var dLen = dArr.length;
        if (dArr[dLen - 2] == 'g2') return false;
        document.domain = dArr[dLen - 2] + "." + dArr[dLen - 1];
    },
    enableLoading: function(){
        var htmlArr = [];
        htmlArr.push('<img id="loading_img" width=60 height=60 src="http://img.game2.com/css/img/loadingClient.gif">');
        var $loading_img = $(htmlArr.join(''));

        var htmlArr = [];
        htmlArr.push('<style type="text/css">');
        htmlArr.push('#loading_img{z-index: 999;width:60px;height:60px;position: absolute;}');
        htmlArr.push('</style>');

        $('body').append($loading_img).append(htmlArr.join(''));
        //$loading_img.show();

        var $win = $('body');
        var winW = $win.outerWidth();
        var winH = $win.outerHeight();
        var winTop = $win.scrollTop();
        var nTop = winTop + (winH - $loading_img.height())/2 - 20;
        var nLeft = (winW - $loading_img.width())/2 - 20;
        nTop = nTop < winTop ? winTop : nTop;
        $loading_img.css('top', nTop+'px').css('left', nLeft+'px').show();
    },
    initOtherLogin: function(){
        // 初始化html结构
        var $otherLogin = $('.otherLogin');
        if ($otherLogin.length) {
            var config = this.config;
            $otherLogin.each(function(){
                var $this = $(this);
                var isInit = parseInt($this.attr('data-init') || 0);
                if (!isInit) {
                    $this.attr('data-init', 1);

                    // 每个otherLogin单独一个配置
                    var otherConfigStr = $this.attr('data-otherlogin-config') || '';
                    var otherConfigArr = otherConfigStr ? eval('('+otherConfigStr+')') : null;
                    var newConfig = {};
                    $.extend(newConfig, config, otherConfigArr||{});
                    $this.data('otherLoginConfig', newConfig);

                    // 生成新tpl
                    if (!newConfig['tpl_' + newConfig.tplId]) {
                        alert('模板不存在');
                        return false;
                    }
                    var $newContent = $(newConfig['tpl_' + newConfig.tplId]);

                    //$this.data('otherLoginConfig', newConfig).replaceWith(newConfig.tpl);
                    var $content = $this.find('.otherLoginContent');
                    if ($content.length) {
                        // 替换位置
                        $newContent.find('.otherLoginLabel').remove();
                        $content.replaceWith($newContent.html());
                    } else {
                        // 插入内容
                        $content = $this;
                        $content.html($newContent.html());
                    }
                }
            });
        }
    },
    enableOtherLogin: function(){
        // 绑定事件
        var thatConfig = this.config;
        var processFn = function(){
            var $this = $(this);
            var $otherLogin = $this.closest ? $this.closest('.otherLogin') : null;
            if ((!$otherLogin) || !$otherLogin.length) {
                var count = 0;
                var $parent = $this.parent();
                do {
                    ++count;
                    if ($parent.hasClass('otherLogin')) {
                        $otherLogin = $parent;
                        break;
                    }
                    $parent = $parent.parent();
                } while (count < 3);
            }
            //if ((!$otherLogin) || !$otherLogin.length) {
            //    alert('otherLogin未找到');
            //    return false;
            //}
            var config = $otherLogin && $otherLogin.length && $otherLogin.data('otherLoginConfig') || thatConfig;

            var code = $this.attr('data-code');
            if (!code) {
                alert('data-code属性有误');
                return false;
            }

            var url    = config.url + 'oauth/' + code + '/';
            var isTop  = config.isTop && (config.isTop == '1' || config.isTop  == 'true') ? '1' : '';

            // 当前页面url
            var loc = '';
            if (isTop) {
                try {loc = top.location + '';} catch(e){loc='';};
            }
            loc = loc || window.location + '';

            // tgInfo
            var tgInfo = window.tgInfo || '';
            if (tgInfo) {
                if (!/^\d_/.test(tgInfo)) {
                    tgInfo = '2_' + tgInfo;
                }
            } else {
                var locStr = decodeURIComponent(loc);
                if (locStr) {
                    var locArr = locStr.match(/\/tgInfo\/([^/]{20,})\//) || locStr.match(/[?&]tgInfo=([^&]{20,})/);
                    if (locArr) {
                        tgInfo = locArr[1];
                    }
                }
            }

            // 备注处理
            var remark = config.remark ? config.remark : '';
            var remarkArr = remark ? remark.split(':') : [];
            remarkArr[0] = remarkArr[0] || '';
            remarkArr[1] = remarkArr[1] || '';
            remarkArr[2] = remarkArr[2] || '';
            remarkArr[3] = remarkArr[3] || encodeURIComponent(loc);
            remarkArr[4] = config.isOpen && (config.isOpen == '1' || config.isOpen == 'true') ? '1' : '';
            remarkArr[5] = isTop;
            remarkArr[6] = encodeURIComponent(tgInfo);
            remarkArr[7] = window.VBArray ? 1 : '';
            remark = remarkArr.join(':');
            url += '?remark=' + encodeURIComponent(remark);
            if (!config.isOpen) {
                J.OtherLogin.enableLoading();
                // return false;
                setTimeout(function(){
                    var otherLoginIframeWidth = $('.wrap').width();
                    var otherLoginIframeHeight = $('.wrap').height();
                    var htmlTpl = '';
					switch(code) {
						case 'weibo':
							htmlTpl = ''
								+'<div id="otherLoginIframeOut" name="otherLoginIframeOut" style="width:'+ otherLoginIframeWidth +'px;height:'+ otherLoginIframeHeight +'px;overflow:scroll;">'
								+    '<iframe src="'+ url +'" id="otherLoginIframe" name="otherLoginIframe" width="770" height="630" marginwidth="0" marginheight="0" scrolling="No" frameborder="No"  allowtransparency="allowtransparency" border="0">'
								+    '</iframe>'
								+'</div>';
						break;
						case 'qq':
							htmlTpl = ''
								+'<div id="otherLoginIframeOut" name="otherLoginIframeOut" style="width:'+ otherLoginIframeWidth +'px;height:'+ otherLoginIframeHeight +'px;overflow:scroll;">'
								+    '<iframe src="'+ url +'" id="otherLoginIframe" name="otherLoginIframe" width="770" height="500" marginwidth="0" marginheight="0" scrolling="No" frameborder="No"  allowtransparency="allowtransparency" border="0">'
								+    '</iframe>'
								+'</div>';
						break;
						default:
							htmlTpl = ''
								+    '<iframe src="'+ url +'" id="otherLoginIframe" name="otherLoginIframe" width="'+ otherLoginIframeWidth +'" height="'+ otherLoginIframeHeight +'">'
								+    '</iframe>';
						break;
					}
                    $('body').append(htmlTpl);
                    $('.wrap').hide();
                    $('#loading_img').hide();
                    // top.location = url;
                },500);
            } else {
                var winW = window.screen.width;
                var winH = window.screen.height;
                var newW = 800;
                var newH = 600;
                var newL = (winW-newW)/2;
                var newT = (winH-newH)/2-100;

                var isMaxthon = '';
                try{window.external.max_invoke("GetHotKey");isMaxthon = true;}catch(ex){isMaxthon = false;}
                if (isMaxthon) {
                    var newWin = window.open('about:blank', 'OauthLoginWindow', 'height='+newH+',width='+newW+',top='+newT+',left='+newL+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
                    newWin.location = url;
                } else {
                    var newWin = window.open(url, 'OauthLoginWindow', 'height='+newH+',width='+newW+',top='+newT+',left='+newL+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
                }

                newWin && newWin.focus();
            }
            return false;
        };

        var $body = $('body');
        if ($body.delegate) {
            $body.delegate('.otherLoginItem', 'click', function(){
                return processFn.call($(this));
            });
        } else {
            $body.bind('click', function(evt){
                var $target = $(evt.target);
                if (!$target.hasClass('otherLoginItem')) {
                    $target = $target.parent();
                }
                if ($target.hasClass('otherLoginItem')) {
                    return processFn.call($target);
                }
            });
        }
    },
    enableBindAct: function(){
        // 授权登录页面特效
        var $bindLi = $('body .bindBox li');
        if($bindLi.length) {
            $bindLi.hover(function () {
                if (!$(this).hasClass("bindAct")) {
                    $(this).addClass("hov");
                }
            }, function () {
                if (!$(this).hasClass("bindAct")) {
                    $(this).removeClass("hov");
                }
            });
            $bindLi.click(function () {
                if(!$(this).hasClass("bindAct")) {
                    $(this).siblings('.bindAct').find('.bindDrap').animate({"height":0},function(){$(this).hide();});
                    $bindLi.removeClass("hov bindAct");
                    $(this).addClass("bindAct").find(".bindDrap").show().animate({"height":"80px"});
                    return false;
                }
            })
        }
    }
};

// 初始化
$(document).ready(function(){
    var j_otherlogin_config = window.j_otherlogin_config || {};
    J.OtherLogin.init(j_otherlogin_config);
});
})(jQuery);
