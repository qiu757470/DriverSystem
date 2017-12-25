/**
 * 支付中心页面公共js
 *
 * js依赖
 * jquery 1.4.2+
 */
Pay.Common = Pay.getClass();

// Pay.Common.Lib{{{
Pay.Common.Lib = Pay.Common.Lib || {};
Pay.$.extend(Pay.Common.Lib, {
    isLogin: function(){
        return window.UserInfo && window.UserInfo.id;
    },
    getUserCodeLabel: function(){
        if (window.UserInfo && window.UserInfo.id) {
            return 'payulist_' + window.UserInfo.id;
        } else {
            return '';
        }
    },
    doSubmitLogin: function(postData, callback) {
        var returnArr = {status_code: 'post_success', status_msg:'提交成功'};
        callback = callback || function(){};
        if (postData) {
            if (!postData.code) {
                returnArr.status_code = 'username_is_empty';
                returnArr.status_msg = '请输入您的账号';
                return callback(returnArr);
            } else if (/^[a-z_][a-z_0-9]*$/i.test(postData.code)) {
                if (postData.code.length > 20 || postData.code.length <3) {
                    returnArr.status_code = 'username_is_error';
                    returnArr.status_msg = '您输入的账号有误';
                    return callback(returnArr);
                }
            } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(postData.code)) && !(postData.code.indexOf('@')>0)) {
                returnArr.status_code = 'username_is_error';
                returnArr.status_msg = '您输入的账号有误';
                return callback(returnArr);
            }

            if (!postData.password) {
                returnArr.status_code = 'password_is_empty';
                returnArr.status_msg = '请输入您的密码';
                return callback(returnArr);
            } else if (postData.password.length < 6) {
                returnArr.status_code = 'password_is_error';
                returnArr.status_msg = '您输入的密码有误';
                return callback(returnArr);
            }

            if (typeof(postData.vcode) !== 'undefined') {
                if (!postData.vcode) {
                    returnArr.status_code = 'vcode_is_empty';
                    returnArr.status_msg = '请输入验证码';
                    return callback(returnArr);
                } else if (postData.vcode.length < 4 || !(/^\d{4}$/.test(postData.vcode))) {
                    returnArr.status_code = 'vcode_is_error';
                    returnArr.status_msg = '您输入的验证码有误';
                    return callback(returnArr);
                }
            }

            if (typeof(hex_md5) == 'function') {
                postData.password = hex_md5(postData.password);
            }

            postData.sco = Math.random();

            $.ajax({
                type: "POST",
                url: Pay.Ulib.getUrl('ajax', 'op/login'),
                data: postData,
                dataType: 'json',
                success: function(result){
                    var returnArr = {};
                    if (result.status > 0) {
                        returnArr.status_code = 'login_success';
                        returnArr.status_msg = '登陆成功';
                    } else if (result.status == -6) {
                        returnArr.status_code = 'more_fail_login';
                        returnArr.status_msg = result.msg;
                    } else if (result.status == -5) {
                        returnArr.status_code = 'ip_banned';
                        returnArr.status_msg = result.msg;
                    } else if (result.status == -3) {
                        returnArr.status_code = 'username_or_password_is_error';
                        returnArr.status_msg = result.msg;
                    } else if (result.status == -2) {
                        returnArr.status_code = 'password_is_error';
                        returnArr.status_msg = '您输入的密码有误';
                    } else {
                        if (result.msg.indexOf('验证码') > -1) {
                            returnArr.status_code = 'vcode_is_error';
                            returnArr.status_msg = '您输入的验证码有误';
                        } else {
                            returnArr.status_code = 'username_is_error';
                            returnArr.status_msg = '您输入的账号有误';
                        }
                    }
                    callback(returnArr);
                    return false;
                }
            });
        } else {
            returnArr.status_code = 'data_empty';
            returnArr.status_msg = '提交的数据不可为空';
            return callback(returnArr);
        }
    },
    signout: function(callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/sigout'),
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    gameNotice: function(data, callback){
        callback = callback || function(){};
        var interface = window.PlatInfo.interface;
        var url = interface.substr(interface.length-1) == '/' ? window.PlatInfo.interface : window.PlatInfo.interface + '/';
        $.ajax({
            type: 'GET',
            url: Pay.Ulib.getUrl('updatePayState', 'op/gameNotice', url),
            data: data,
            dataType: 'jsonp',
            success: function(result) {
                callback(result);
                return false;
            }
        });

        return false;
    },
    getGameList: function(callback){
        callback = callback || function(){};
        /*
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getGameList'),
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        */
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('upload', window.PlatInfo.code + '/data') + 'gameList.htm',
            dataType: 'script',
            success: function(rs) {
                var result = {status: 0, msg: '数据为空'};
                if (window.g_gameList) {
                    result.status = 1;
                    result.msg = '操作成功！';
                    result.data = g_gameList;

                    // 特殊处理
                    var forceGame = Pay.Common.Lib.getForceGame();
                    if (forceGame) {
                        var hasF = false;
                        for (var i=0,len=result.data.length; i<len; i++) {
                            if (result.data[i]['code'] == forceGame.info.code) {
                                hasF = true;
                                break;
                            }
                        }
                        if (!hasF) {
                            result.data.push(forceGame.info);
                        }
                    }
                }
                callback(result);
                return false;
            },
            error: function(res) {
                if (res && res.status == 404) {
                    // 404页面重新生成
                    Pay.Common.Lib.gameNotice({gameId:1, serverId:0}, function(result){
                        if (result.status) {
                            // 重新请求数据
                            $.ajax({
                                type: "GET",
                                url: Pay.Ulib.getUrl('upload', window.PlatInfo.code + '/data') + 'gameList.htm',
                                dataType: 'script',
                                success: function(rs) {
                                    var result = {status: 0, msg: '数据为空'};
                                    if (window.g_gameList) {
                                        result.status = 1;
                                        result.msg = '操作成功！';
                                        result.data = g_gameList;
                                    }
                                    callback(result);
                                    return false;
                                },
                                error: function(){
                                    var result = {status: 0, msg: '数据为空'};
                                    callback(result);
                                    return false;
                                }
                            });
                        } else {
                            var result = {status: 0, msg: '数据为空'};
                            callback(result);
                            return false;
                        }
                    });
                }
            }
        });
        return false;
    },
    getUserPlayGameList: function(callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getUserPlayGameList'),
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    getServerListByGameId: function(data, callback){
        callback = callback || function(){};
        /*
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getServerListByGameId'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        */
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('upload', window.PlatInfo.code + '/data') + 'serverList_' + data.gameId + '.htm',
            dataType: 'script',
            success: function(rs) {
                var result = {status: 0, msg: '数据为空'};
                if (window['g_serverList_' + data.gameId]) {
                    result.status = 1;
                    result.msg = '操作成功！';
                    result.data = window['g_serverList_' + data.gameId];
                }

                // 特殊区服处理
                var forceGame = Pay.Common.Lib.getForceGame();
                if (forceGame) {
                    result.status = 1;
                    result.data   = result.data || [];
                    var hasF = false;
                    for (var i=0,len=result.data.length; i<len; i++) {
                        if (result.data[i]['code'] == forceGame.server.code) {
                            hasF = true;
                            break;
                        }
                    }
                    if (!hasF) {
                        result.data.push(forceGame.server);
                    }
                }

                // 检测修复，生成
                if (result.status == 0 && window['g_serverList_' + data.gameId] == null) {
                    Pay.Common.Lib.gameNotice({gameId:data.gameId, serverId:1}, function(result){
                        if (result.status) {
                            // 重新请求数据
                            $.ajax({
                                type: "GET",
                                url: Pay.Ulib.getUrl('upload', window.PlatInfo.code + '/data') + 'serverList_' + data.gameId + '.htm',
                                dataType: 'script',
                                success: function(rs) {
                                    var result = {status: 0, msg: '数据为空'};
                                    if (window['g_serverList_' + data.gameId]) {
                                        result.status = 1;
                                        result.msg = '操作成功！';
                                        result.data = window['g_serverList_' + data.gameId];
                                    }
                                    callback(result);
                                    return false;
                                },
                                error: function(){
                                    var result = {status: 0, msg: '数据为空'};
                                    callback(result);
                                    return false;
                                }
                            });
                        } else {
                            var result = {status: 0, msg: '数据为空'};
                            callback(result);
                            return false;
                        }
                    });
                }

                callback(result);
                return false;
            },
            error: function(res) {
                if (res && res.status == 404) {
                    // 404页面重新生成
                    Pay.Common.Lib.gameNotice({gameId:data.gameId, serverId:1}, function(result){
                        if (result.status) {
                            // 重新请求数据
                            $.ajax({
                                type: "GET",
                                url: Pay.Ulib.getUrl('upload', window.PlatInfo.code + '/data') + 'serverList_' + data.gameId + '.htm',
                                dataType: 'script',
                                success: function(rs) {
                                    var result = {status: 0, msg: '数据为空'};
                                    if (window['g_serverList_' + data.gameId]) {
                                        result.status = 1;
                                        result.msg = '操作成功！';
                                        result.data = window['g_serverList_' + data.gameId];
                                    }
                                    callback(result);
                                    return false;
                                },
                                error: function(){
                                    var result = {status: 0, msg: '数据为空'};
                                    callback(result);
                                    return false;
                                }
                            });
                        } else {
                            var result = {status: 0, msg: '数据为空'};
                            callback(result);
                            return false;
                        }
                    });
                }
            }
        });
        return false;
    },
    getUserRechargeList: function(callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getUserRechargeList'),
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    updatePayStateByOrderLabel: function(data, callback){
        callback = callback || function(){};
        var interface = window.PlatInfo.interface;
        var url = interface.substr(interface.length-1) == '/' ? window.PlatInfo.interface : window.PlatInfo.interface + '/';
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('updatePayState', 'op/updatePayStateByOrderLabel', url),
            data: data,
            dataType: 'jsonp',
            disableXdomain: true,
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    payform: function(data, callback){
        callback = callback || function(){};
        $.ajax({
            type: "POST",
            url: Pay.Ulib.getUrl('payform', 'isAjax/1'),
            data: data,
            async: false,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    getSeccode: function(width, height){
        width = width ? width : '80';
        height = height ? height : '30';
        var url = '/seccode.php?r=' + Math.random();
        var swfHtml = [];
        swfHtml.push('<object width="' + width + '" height="' + height + '" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0">');
        swfHtml.push('    <param name="movie" value="' + url + '">');
        swfHtml.push('    <param name="quality" value="high">');
        swfHtml.push('    <param name="wmode" value="transparent">');
        swfHtml.push('    <param name="menu" value="false">');
        swfHtml.push('    <embed width="' + width + '" height="' + height + '" src="' + url + '" wmode="opaque"  menu="false" quality="high" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>');
        swfHtml.push('</object>');
        return swfHtml.join('');
    },
    submitPay: {
        // 表单数据
        postData: {},
        // 表单弹窗确认
        comfirmOrder: function(newPostData, label, _state){
            var that = this;
            var postData = that.postData = newPostData;

            var noticeArr = [];
            if ((!postData.usercode) || !(/.{3,20}$/i.test(postData.usercode))) {
                noticeArr.push('请正确填写要充值的平台账号。');
            }

            if (!postData.user_check) {
                if (postData.paytarget == 'GAME') {
                    if (!postData.gcodeid) {
                        noticeArr.push('您输入的账号不存在或者未激活当前游戏。');
                    }
                } else {
                    noticeArr.push('您输入的账号不存在。');
                }
            }

            if (!postData.paychannel) {
                noticeArr.push('请选择充值方式。');
            }

            if (!postData.isLogin && postData.paychannel == 'ROOF') {
                noticeArr.push('使用该充值方式，请先登陆。');
            }

            if (postData.paytarget == 'GAME') {
                if (!postData.serverid) {
                    noticeArr.push('请选择要充值的游戏区服。');
                } else if (!postData.gamename) {
                    noticeArr.push('请选择要充值的游戏名称。');
                } else if (!postData.servername) {
                    noticeArr.push('请选择要充值的区服名称。');
                }
            }

            if (postData.paychannel.indexOf('E-BANK') > -1) {
                if (postData.banktype == '') {
                    noticeArr.push('请选择支付银行类型。');
                }
                if (postData.bankname == '') {
                    noticeArr.push('请选择支付银行名称。');
                }
            }

            if (postData.paychannel == 'QUYOU-NET') {
                if (!postData.paycard) {
                    noticeArr.push('请填写趣游一卡通卡号。');
                } else {
                    var payCardArr = postData.paycard.split(','), payCardItem=undefined;
                    var existPayCard = {};
                    for (var i=0,len=payCardArr.length; i<len; i++) {
                        payCardItem = payCardArr[i].split('|');
                        var cardNum = (len == 1) ? '' : (i+1);
                        if (!payCardItem[0]) {
                            noticeArr.push('请填写趣游一卡通卡号' + cardNum + '。');
                        } else if (!(/^\d{16}$/.test(payCardItem[0]))) {
                            noticeArr.push('您填写趣游一卡通卡号' + cardNum + '不正确。');
                        } else if (existPayCard[payCardItem[0]]) {
                            noticeArr.push('您填写趣游一卡通卡号' + cardNum + '重复。');
                        } else {
                            existPayCard[payCardItem[0]] = 1;
                        }

                        if (!payCardItem[1]) {
                            noticeArr.push('请填写趣游一卡通密码' + cardNum + '。');
                        } else if (!(/^\d{16}$/.test(payCardItem[1]))) {
                            noticeArr.push('您填写趣游一卡通密码' + cardNum + '不正确。');
                        }
                    }
                }
            }

            if ((/^SHENGPAYCARD_.+/.test(postData.paychannel) && postData.paychannel != 'SHENGPAYCARD_WXZF') || (window.pay_center_config && window.pay_center_config.switchConfig == '1' && postData.bind_payment && postData.bind_payment == '6')) {
                if (!postData.paycard) {
                    noticeArr.push('请填写充值卡号。');
                } else {
                    var payCardArr = postData.paycard.split(','), payCardItem=undefined;
                    var existPayCard = {};
                    for (var i=0,len=payCardArr.length; i<len; i++) {
                        payCardItem = payCardArr[i].split('|');
                        var cardNum = (len == 1) ? '' : (i+1);
                        if (!payCardItem[0]) {
                            noticeArr.push('请填写充值卡号' + cardNum + '。');
                        } else if (!(/^[a-zA-Z0-9]{14,}$/.test(payCardItem[0]))) {
                            noticeArr.push('您填写充值卡号' + cardNum + '不正确。');
                        } else if (existPayCard[payCardItem[0]]) {
                            noticeArr.push('您填写充值卡号' + cardNum + '重复。');
                        } else {
                            existPayCard[payCardItem[0]] = 1;
                        }

                        if (!payCardItem[1]) {
                            noticeArr.push('请填写充值卡密码' + cardNum + '。');
                        } else if (!(/^[a-zA-Z0-9]{8,}$/.test(payCardItem[1]))) {
                            noticeArr.push('您填写充值卡密码' + cardNum + '不正确。');
                        }
                    }
                }
            }

            if (!postData.payamount) {
                noticeArr.push('请选择充值套餐。');
            }

            if (postData.paychannel == 'ROOF') {
                var balance = postData.user_balance && (parseFloat(postData.user_balance) || 0);
                var djAmount = postData.daijin_code && postData.daijin_amount ? (parseFloat(postData.daijin_amount) || 0) : 0;
                if (balance <= 0 || (balance+djAmount) < parseFloat(postData.payamount)) {
                    // 余额不足
                    noticeArr.push('您的余额不足，请使用其他支付方式！');
                }
            }

            var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
            if (postData.userphone && !phoneReg.test(postData.userphone)) {
                noticeArr.push('您输入的联系手机不正确。');
            }

            // 遇到问题联系客服
            if (window.PlatInfo && window.PlatInfo.kfcall) {
                var callArr = window.PlatInfo.kfcall.replace(/\r/g, '').split("\n");
                postData.kfcall = callArr[0].replace(/^\s*|\s*$/g, '');
            } else {
                noticeArr.push('请设置客服联系信息。');
            }

            // 遇到问题在线客服url
            if (window.PlatInfo && window.PlatInfo.kfurl) {
                postData.kfurl = window.PlatInfo.kfurl;
            } else {
                noticeArr.push('请设置在线客服url。');
            }

            if (noticeArr.length) {
                Pay.Common.showMsg('以下原因导致提交失败：', noticeArr.join('</p><p>'));
                return false;
            }

            // 实名认证处理
            var authflag = false;
            var _pos = {userCode: postData.usercode};
            if (!_state) _pos.step = 1;
            Pay.Common.Lib.doCheckRealNameAuth(_pos, function(rs){
                if (!rs.status) {
                    authflag = true;
                } else {
                    $('#paypop_msg').data('comfirmOrderData', [newPostData, label]);
                    $.ajax({
                        type: "GET",
                        url: Pay.Ulib.getUrl('ajax', 'op/goRealName'),
                        data: {userCode: postData.usercode, step:1, sid:postData.serverid},
                        success: function(result){
                        }
                    });

                    if (!rs.data.isLogin) {
                        // 提示认证说明
                        Pay.Common.showRealNameNoticePop(postData.usercode);
                    } else {
                        // 认证弹窗
                        Pay.Common.showRealNamePop();
                    }
                }
            }, true);
            if (!authflag) {
                return false;
            }


            postData.orderlabel = (new Date()).getTime() + '' + parseInt(Math.random()*10);
            postData.userphone  = postData.userphone || '';
            var postUrl = '/payform/';

            // extendip处理 开始
            var path = window.location && window.location.pathname ? window.location.pathname + '' : '';
            var matchs = path.match(/\/(_extendip\/\d+\/)/);
            if (matchs && matchs[1]) {
                postUrl += matchs[1];
            }
            // extendip处理 结束

            var htmlArr = [];
            htmlArr.push('<form name="Form2" action="' + postUrl + '" method="POST" target="_blank" onsubmit="return false;">');
            htmlArr.push('<input type="hidden" name="serverid" value="' + postData.serverid + '" />');
            htmlArr.push('<input type="hidden" name="paytarget" value="' + postData.paytarget + '" />');
            htmlArr.push('<input type="hidden" name="paychannel" value="' + postData.paychannel + '" />');
            htmlArr.push('<input type="hidden" name="usercode" value="' + postData.usercode +'" />');
            htmlArr.push('<input type="hidden" name="payamount" value="' + postData.payamount + '" />')
            htmlArr.push('<input type="hidden" name="orderlabel" value="' + postData.orderlabel + '" />');
            htmlArr.push('<input type="hidden" name="gcodeid" value="' + postData.gcodeid + '" />');
            htmlArr.push('<input type="hidden" name="userphone" value="' + postData.userphone + '" />');
            if (path.match(/switchConfig/)) {
                htmlArr.push('<input type="hidden" name="switchConfig" value="1" />');
            }
            if (path.match(/\/force\//)) {
                htmlArr.push('<input type="hidden" name="force" value="1" />');
            }

            // 快速按钮
            if (label && label == 'quickPay') {
                htmlArr.push('<input type="hidden" name="isquickpay" value="1" />');
            }

            // 重新支付处理
            if (postData.orderid) {
                htmlArr.push('<input type="hidden" name="orderid" value="' + postData.orderid + '" />');
            }

            // 哪家的网银
            if (postData.paychannel.indexOf('E-BANK') > -1) {
                htmlArr.push('<input type="hidden" name="banktype" value="' + postData.banktype + '" />');
            }

            //todo UM处理
            if (postData.paychannel == 'UM' && postData.paytarget == 'GAME') {
                htmlArr.push('<input type="hidden" name="user_payid" value="" id="flowid"/>');
                $('#pop_notice').data('applyMobileFlag', 0).trigger('applyForMobile');
            }

            if (postData.paycard) {
                htmlArr.push('<input type="hidden" name="paycard" value="' + postData.paycard + '" />');
            }

            // 代金券
            if (postData.daijin_code && postData.daijin_amount) {
                htmlArr.push('<input type="hidden" name="daijin_code" value="' + postData.daijin_code + '" />');
            }

            htmlArr.push('<ul>');

            // 充值账号
            if (window.UserInfo && window.UserInfo.id && window.UserInfo.oauthShow && (window.UserInfo.code == postData.usercode || window.UserInfo.showcode == postData.usercode)) {
                htmlArr.push('<li><span>充值账号：</span><p title="' + postData.usercode + '" alt="' + postData.usercode + '">' + Pay.Ulib.cutLen(postData.usercode, 16) +  '</p>');
                htmlArr.push('<a class="span3" href="javascript:void(0)" title="' + Pay.Ulib.encodeHtml(window.UserInfo.oauthShow.lo_title) + '">');
                htmlArr.push('    <var>(</var><i class="ico3 ico_' + window.UserInfo.oauthShow.lo + '"></i><strong>' + Pay.Ulib.encodeHtml(Pay.Ulib.cutLen(window.UserInfo.oauthShow.lo_name, 14)) + '</strong><var>)</var>');
                htmlArr.push('</a>');
                htmlArr.push('</li>');
            } else {
                htmlArr.push('<li><span>充值账号：</span><p title="' + postData.usercode + '" alt="' + postData.usercode + '">' + Pay.Ulib.cutLen(postData.usercode, 22) +  '</p></li>');
            }

            // 充值目标
            if (postData.paytarget == 'GAME') {
                htmlArr.push('<li><span>充值游戏：</span><p>' + postData.gamename +  '</p></li>');
                htmlArr.push('<li><span>充值区服：</span><p>' + postData.servername + '</p></li>');
            } else {
                htmlArr.push('<li><span>充值目标：</span><p>' + postData.platname + ' - ' + postData.platcoin + '</p></p>');
            }

            // 充值方式
            if (postData.paychannel.indexOf('E-BANK') > -1 && postData.banktype && postData.bankname) {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  ' - ' + postData.bankname + '</p></li>');
            } else {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  '</p></li>');
            }

            // 充值套餐
            htmlArr.push('<li><span>充值套餐：</span><p>' + postData.showpayamount + '</p></li>');

            // 代金券显示
            if (postData.daijin_code && postData.daijin_amount) {
                htmlArr.push('<input type="hidden" name="daijin_code" value="' + postData.daijin_code + '" />');
                htmlArr.push('<li><span>代&nbsp;金&nbsp;劵：</span><p>' + postData.daijin_amount + '元代金券' + (postData.only_first ? '<font style="color:red;">(首)</font>' : '') + '</p></li>');
            } else if (postData.couponid) {
                // 使用代金券不成功
                htmlArr.push('<li><span>代&nbsp;金&nbsp;劵：</span><p>无</p></li>');
            }

            // 充值货币
            htmlArr.push('<li><span>游戏货币：</span><p>' + postData.showpaypoint + '</p></li>');

            // 支付密码
            if (postData.paychannel == 'ROOF') {
                htmlArr.push('<li><span>支付密码：</span><p><input type="password" name="user_paypwd" value="" id="user_paypwd" />&nbsp;<a href="/payPwd/getpaypwd/1/" target="_blank">忘记密码？</a></p></li>');
            }

            // 网银充值平台币 需验证码
            if (window.PlatInfo.code == 'game2' && postData.paychannel.indexOf('E-BANK') > -1 && postData.paytarget == 'ROOF' && parseInt(postData.payamount) >= 300) {
                htmlArr.push('<li><span style="text-align:right;">验&nbsp;证&nbsp;码：</span><p class="seccodeSwf"><input id="user_seccode" name="seccode" type="text" value="" style="float:left;" /><font class="seccodeSwf_cont" style="position:relative;float:left;width:70px;height:26px;cursor:pointer;padding:0 0 0 2px;">' + Pay.Common.Lib.getSeccode(70, 26) + '<b class="seccodeBtn" data-seccodeswf="#paypop_msg .seccodeSwf_cont" title="点击刷新验证码" alt="点击刷新验证码" style="position:absolute;left:0;top:0;width:72px;height:26px;text-indent:-9999px;overflow:hidden;;user-select:none;background:transparent url(/images/commom/transparent.gif) scroll 0 0 repeat;">&nbsp;</b></font></p></li>');
            }

            htmlArr.push('</ul>');
            htmlArr.push('</form>');

            var btnArr = [];
            btnArr.push('<div class="tBtn">');
            btnArr.push('    <a href="javascript:void(0)" class="al paySubmit">确认充值</a>');
            btnArr.push('    <a href="javascript:void(0)" class="b1 closePop">返回修改</a>');
            btnArr.push('</div>');

            // 提交表单处理
            $('#paypop_msg').undelegate('.paySubmit', 'click').delegate('.paySubmit', 'click', function(){
                that.submitOrder();
                return false;
            }).undelegate('.seccodeBtn', 'click').delegate('.seccodeBtn', 'click', function(){
                var seccodeswf = $(this).attr('data-seccodeswf');
                $(seccodeswf).html(Pay.Common.Lib.getSeccode(70, 26)+'<b class="seccodeBtn" data-seccodeswf="#paypop_msg .seccodeSwf_cont" title="点击刷新验证码" alt="点击刷新验证码" style="position:absolute;left:0;top:0;width:72px;height:26px;text-indent:-9999px;overflow:hidden;;user-select:none;background:transparent url(/images/commom/transparent.gif) scroll 0 0 repeat;">&nbsp;</b>');
                return false;
            });
            Pay.Common.showPop('<div class="paySure">' + htmlArr.join('') + '</div>' + btnArr.join(''), '充值信息确认');

            return false;
        },
        // 弹窗表单提交
        submitOrder: function(){
            var that = this;
            var postData = that.postData;
            var noticeArr = [];
            // 支付密码检测
            if (postData.paychannel == 'ROOF') {
                var user_paypwd = $('#user_paypwd').val();
                var paypwdReg = [/[A-Za-z_]+/g, /[0-9]+/g];
                if (user_paypwd.length >= 6 && paypwdReg[0].test(user_paypwd) && paypwdReg[1].test(user_paypwd)) {
                    // 支付密码md5传输
                    if (user_paypwd.length != 32) {
                        if (typeof(hex_md5) == 'function') {
                            $('#user_paypwd').val(hex_md5($('#user_paypwd').val()));
                        }
                    }
                } else {
                    noticeArr.push('支付密码不正确。');
                }
            }
            // 验证码检测
            if (window.PlatInfo && window.PlatInfo.code == 'game2' && postData.paychannel.indexOf('E-BANK') > -1 && postData.paytarget == 'ROOF' && postData.payamount >= 300) {
                var seccode = $('#user_seccode').val();
                if ((!seccode) || seccode.length < 4) {
                    noticeArr.push('验证码不正确。');
                }
            }

            // 错误提示
            if (noticeArr.length) {
                alert(noticeArr.join("\n"));
                return false;
            }

            // 记录usercode
            if (window.UserInfo && window.UserInfo.id && window.UserInfo.code != postData.usercode && window.UserInfo.showcode != postData.usercode) {
                var userCodeLabel = Pay.Common.Lib.getUserCodeLabel();
                if (userCodeLabel) {
                    var newUserCodeArr = [postData.usercode];
                    var existUserCode = Pay.Ulib.getCookie(userCodeLabel) || '';
                    var existUserCodeTemp = {};
                    existUserCodeTemp[postData.usercode] = 1
                    if (existUserCode) {
                        var existUserCodeArr = existUserCode.split(',');
                        for (var i=0,len=existUserCodeArr.length; i<len; i++) {
                            if (!existUserCodeTemp[existUserCodeArr[i]]) {
                                existUserCodeTemp[existUserCodeArr[i]] = 1;
                                newUserCodeArr.push(existUserCodeArr[i]);
                            }
                            if (newUserCodeArr.length >= 3) break;
                        }
                    }
                    Pay.Ulib.setCookie(userCodeLabel, newUserCodeArr.join(','), 30*24*60*60*1000);
                }
            }

            if (true) {
                var formData = $('#paypop_msg form[name="Form2"]').serialize();
                // 提示在新窗口完成支付
                var showFinishPay = function(){
                    // 在新打开的支付页面完成付款
                    var htmlArr = [];
                    htmlArr.push('<div class="tTip">');
                    htmlArr.push('    <h5>请您在打开的支付页面完成付款</h5>');
                    htmlArr.push('    <p>');
                    htmlArr.push('        支付完成前请不要关闭或刷新此窗口。<br>');
                    htmlArr.push('        支付失败时，可拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
                    htmlArr.push('    </p>');
                    htmlArr.push('</div>');
                    htmlArr.push('<div class="tBtn">');
                    htmlArr.push('    <a href="javascript:void(0)" class="al finishPay">查看支付结果</a>');
                    htmlArr.push('    <a href="javascript:void(0)" class="b1 closePop">返回充值中心</a>');
                    htmlArr.push('</div>');
                    Pay.Common.showPop(htmlArr.join(''));

                    // 点击完成支付
                    $('#paypop_msg').undelegate('.finishPay', 'click').delegate('.finishPay', 'click', function(){
                        that.finishOrder();
                        return false;
                    });
                };
                // ajax提交请求函数
                var doPayform = function(){
                    Pay.Common.Lib.payform(formData, function(result){
                        if (result.status) {
                            // 跳转到第三方支付
                            // postUrl, postData, sign
                            var data = result.data;
                            var link = '/gotochannel/?';
                            for (var i in data) {
                                link += i + '=' + encodeURIComponent(data[i]) + '&';
                            }

                            // 支付窗口handle
                            var winHandle = null;
                            try {
                                winHandle = window.open(link);
                            } catch (err) {
                                winHandle = null;
                            }
                            if (!winHandle) {
                                // 打开弹窗失败，使用点击新窗口打开
                                var htmlArr = [];
                                htmlArr.push('<div class="tBtn" style="padding:50px 0 3px 98px;">');
                                htmlArr.push('    <span style="float:left;line-height:32px;height:32px;font-size:14px;padding-right:5px;color:#434343;white-space:nowrap;">请点击</span>');
                                htmlArr.push('    <a href="' + link + '" class="gotoChannel" target="_blank">' + (postData.paychannelname ? postData.paychannelname + '支付' : '立即支付') + '</a>');
                                htmlArr.push('    <span style="float:left;line-height:32px;height:32px;font-size:14px;padding-left:5px;color:#434343;white-space:nowrap;">前往支付</span>');
                                htmlArr.push('</div>');
                                // 点击立即支付
                                $('#paypop_msg').undelegate('.gotoChannel', 'click').delegate('.gotoChannel', 'click', function(){
                                    // 在新打开的支付页面完成付款
                                    setTimeout(function(){showFinishPay();}, 0);
                                });
                                Pay.Common.showPop(htmlArr.join(''), '立即支付');
                            } else {
                                // 在新打开的支付页面完成付款
                                showFinishPay();
                            }
                        } else {
                            if (postData.paychannel == 'ROOF' && result && result.cid) {
                                var htmlArr = [];
                                htmlArr.push('<div class="tTip suc">');
                                htmlArr.push('    <h5 class="h03">' + result.msg + '</h5>');
                                htmlArr.push('</div>');
                                htmlArr.push('<div class="tBtn">');
                                htmlArr.push('    <a href="javascript:/*点击关闭*/" class="am" onclick="window.location.assign(window.location.href);return false;">确定</a>');
                                htmlArr.push('</div>');
                                Pay.Common.showPop(htmlArr.join(''));
                            } else {
                                Pay.Common.showMsg(result.msg);
                            }
                        }
                        return false;
                    });
                };
                // 状态加载中显示..
                var loadArr = [];
                loadArr.push('<div class="tTip" style="padding-left:26px;">');
                loadArr.push('    <h5 class="h03">');
                loadArr.push('        <p style="background-position: 0px 0px; padding-left: 31px; font-size: 15px;_font-size:14px; padding-top: 6px; margin-top: 26px;" class="loading">订单生成中...若长时间没响应，请<a class="rePaySubmit" href="javascript:void(0);">点击这里</a>刷新。</p>');
                loadArr.push('    </h5>');
                loadArr.push('</div>');
                // 重新提交表单处理
                $('#paypop_msg').undelegate('.rePaySubmit', 'click').delegate('.rePaySubmit', 'click', function(){
                    doPayform();
                    return false;
                });
                Pay.Common.showPop(loadArr.join(''));

                // ajax提交请求处理
                doPayform();
            } else {
                // 页面post提交
                document.Form2.submit();

                // 在新打开的支付页面完成付款
                var htmlArr = [];
                htmlArr.push('<div class="tTip">');
                htmlArr.push('    <h5>请您在打开的支付页面完成付款</h5>');
                htmlArr.push('    <p>');
                htmlArr.push('        支付完成前请不要关闭或刷新此窗口。<br>');
                htmlArr.push('        支付失败时，可拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
                htmlArr.push('    </p>');
                htmlArr.push('</div>');
                htmlArr.push('<div class="tBtn">');
                htmlArr.push('    <a href="javascript:void(0)" class="al finishPay">查看支付结果</a>');
                htmlArr.push('    <a href="javascript:void(0)" class="b1 closePop">返回充值中心</a>');
                htmlArr.push('</div>');

                // 点击完成支付
                $('#paypop_msg').undelegate('.finishPay', 'click').delegate('.finishPay', 'click', function(){
                    that.finishOrder();
                    return false;
                });
                Pay.Common.showPop(htmlArr.join(''));
            }
            return false;
        },
        // 点击完成支付，检测处理
        finishOrder: function(){
            var postData = this.postData;
            var site = window.PlatInfo.site || '/';
            if (postData.orderlabel) {
                var orderlabel = postData.orderlabel;
                // 检测充值完成情况
                Pay.Common.Lib.updatePayStateByOrderLabel({orderlabel:orderlabel}, function(result){
                    var htmlArr = [];
                    if (result.status == 1) {
                        htmlArr.push('<div class="tTip suc">');
                        htmlArr.push('    <h5 class="h03">恭喜，您充值成功！</h5>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        if (postData.isLogin) {
                            htmlArr.push('    <a href="/myRecharge/" class="al">查看充值记录</a>');
                        } else {
                            htmlArr.push('    <a href="' + site + '" class="al">返回平台</a>');
                        }
                        htmlArr.push('    <a href="javascript:/*点击继续充值*/" onclick="window.location.assign(window.location.href);return false;">继续充值</a>');
                        htmlArr.push('</div>');
                    } else {
                        // 未完成支付
                        htmlArr.push('<div class="tTip">');
                        htmlArr.push('    <h5 class="h02">抱歉，充值失败！</h5>');
                        htmlArr.push('    <p>');
                        htmlArr.push('        充值遇到问题，查看充值常见问题。<br>');
                        htmlArr.push('        拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
                        htmlArr.push('    </p>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        htmlArr.push('    <a href="' + postData.kfurl + '" class="al" target="_blank">联系在线客服</a>');
                        htmlArr.push('    <a href="/" class="b1">返回充值中心</a>');
                        htmlArr.push('</div>');
                    }
                    Pay.Common.showPop(htmlArr.join(''));
                });
            } else {
                window.location = site;
            }
            return false;
        }
    },
    submitPayVip: {
        // 表单数据
        postData: {},
        // 表单弹窗确认
        comfirmOrder: function(newPostData, label, _state){
            var that = this;
            var postData = this.postData = newPostData;

            var noticeArr = [];

            if ((!postData.usercode) || !(/.{3,20}$/i.test(postData.usercode))) {
                noticeArr.push('请先登陆平台账号。');
            }

            if (!postData.paychannel) {
                noticeArr.push('请选择充值方式。');
            }

            if (postData.paychannel.indexOf('E-BANK') > -1) {
                if (postData.banktype == '') {
                    noticeArr.push('请选择支付银行类型。');
                }
                if (postData.bankname == '') {
                    noticeArr.push('请选择支付银行名称。');
                }
            }

            if ((!postData.payamount) || (!postData.vip)) {
                noticeArr.push('请选择购买等级。');
            }

            var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
            if (postData.userphone && !phoneReg.test(postData.userphone)) {
                noticeArr.push('您输入的联系手机不正确。');
            }

            // 遇到问题联系客服
            if (window.PlatInfo && window.PlatInfo.kfcall) {
                var callArr = window.PlatInfo.kfcall.replace(/\r/g, '').split("\n");
                postData.kfcall = callArr[0].replace(/^\s*|\s*$/g, '');
            } else {
                noticeArr.push('请设置客服联系信息。');
            }

            // 遇到问题在线客服url
            if (window.PlatInfo && window.PlatInfo.kfurl) {
                postData.kfurl = window.PlatInfo.kfurl;
            } else {
                noticeArr.push('请设置在线客服url。');
            }

            if (noticeArr.length) {
                Pay.Common.showMsg('以下原因导致提交失败：', noticeArr.join('</p><p>'));
                return false;
            }

            // 实名认证处理
            var authflag = false;
            var _pos = {userCode: postData.usercode};
            if (!_state) _pos.step = 1;
            Pay.Common.Lib.doCheckRealNameAuth(_pos, function(rs){
                if (!rs.status) {
                    authflag = true;
                } else {
                    $('#paypop_msg').data('comfirmOrderData', [newPostData, label, 'submitPayVip']);
                    $.ajax({
                        type: "GET",
                        url: Pay.Ulib.getUrl('ajax', 'op/goRealName'),
                        data: {userCode: postData.usercode, step:1, sid:postData.serverid},
                        success: function(result){
                        }
                    });

                    if (!rs.data.isLogin) {
                        // 提示认证说明
                        Pay.Common.showRealNameNoticePop(postData.usercode);
                    } else {
                        // 认证弹窗
                        Pay.Common.showRealNamePop();
                    }
                }
            }, true);
            if (!authflag) {
                return false;
            }


            postData.orderlabel = (new Date()).getTime() + '' + parseInt(Math.random()*10);
            postData.userphone  = postData.userphone || '';
            var postUrl = '/payform/';

            var htmlArr = [];
            htmlArr.push('<form name="Form2" action="' + postUrl + '" method="POST" target="_blank" onsubmit="return false;">');
            htmlArr.push('<input type="hidden" name="serverid" value="' + postData.serverid + '" />');
            htmlArr.push('<input type="hidden" name="paytarget" value="BUY" />');
            htmlArr.push('<input type="hidden" name="paychannel" value="' + postData.paychannel + '" />');
            htmlArr.push('<input type="hidden" name="usercode" value="' + postData.usercode +'" />');
            htmlArr.push('<input type="hidden" name="payamount" value="' + postData.payamount + '" />')
            htmlArr.push('<input type="hidden" name="orderlabel" value="' + postData.orderlabel + '" />');
            htmlArr.push('<input type="hidden" name="userphone" value="' + postData.userphone + '" />');
            htmlArr.push('<input type="hidden" name="extend" value="' + postData.vip + '" />');

            // 哪家的网银
            if (postData.paychannel.indexOf('E-BANK') > -1) {
                htmlArr.push('<input type="hidden" name="banktype" value="' + postData.banktype + '" />');
            }

            htmlArr.push('<ul>');

            // 充值方式
            if (postData.paychannel.indexOf('E-BANK') > -1 && postData.banktype && postData.bankname) {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  ' - ' + postData.bankname + '</p></li>');
            } else {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  '</p></li>');
            }

            // 充值账号
            htmlArr.push('<li><span>充值账号：</span><p title="' + postData.usercode + '" alt="' + postData.usercode + '">' + Pay.Ulib.cutLen(postData.usercode, 22) +  '</p></li>');

            // 充值目标
            htmlArr.push('<li><span>充值目标：</span><p>' + postData.platname + 'VIP' + '</p></p>');

            // 充值金额
            htmlArr.push('<li><span>充值金额：</span><p>' + postData.showpayamount + '</p></li>');

            // 充值货币
            htmlArr.push('<li><span>游戏货币：</span><p>' + postData.showpaypoint + '</p></li>');

            htmlArr.push('</ul>');
            htmlArr.push('</form>');

            var btnArr = [];
            btnArr.push('<div class="tBtn">');
            btnArr.push('    <a href="javascript:void(0)" class="al paySubmit">确认充值</a>');
            btnArr.push('    <a href="javascript:void(0)" class="b1 closePop">返回修改</a>');
            btnArr.push('</div>');

            // 提交表单处理
            $('#paypop_msg').undelegate('.paySubmit', 'click').delegate('.paySubmit', 'click', function(){
                Pay.Common.Lib.submitPayVip.submitOrder();
                return false;
            });
            Pay.Common.showPop('<div class="paySure">' + htmlArr.join('') + '</div>' + btnArr.join(''));

            return false;
        },
        // 弹窗表单提交
        submitOrder: function(){
            var that = this;
            var postData = that.postData;
            var noticeArr = [];
            if (postData.paychannel == 'ROOF') {
                var user_paypwd = $('#user_paypwd').val();
                var paypwdReg = [/[A-Za-z_]+/g, /[0-9]+/g];
                if (user_paypwd.length >= 6 && paypwdReg[0].test(user_paypwd) && paypwdReg[1].test(user_paypwd)) {
                    // 支付密码md5传输
                    if (user_paypwd.length != 32) {
                        if (typeof(hex_md5) == 'function') {
                            $('#user_paypwd').val(hex_md5($('#user_paypwd').val()));
                        }
                    }
                } else {
                    noticeArr.push('支付密码不正确。');
                }
            }

            // 错误提示
            if (noticeArr.length) {
                alert(noticeArr.join("\n"));
                return false;
            }

            document.Form2.submit();

            // 在新打开的支付页面完成付款
            var htmlArr = [];
            htmlArr.push('<div class="tTip">');
            htmlArr.push('    <h5>请您在打开的支付页面完成付款</h5>');
            htmlArr.push('    <p>');
            htmlArr.push('        支付完成前请不要关闭或刷新此窗口。<br>');
            htmlArr.push('        支付失败时，可拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
            htmlArr.push('    </p>');
            htmlArr.push('</div>');
            htmlArr.push('<div class="tBtn">');
            htmlArr.push('    <a href="javascript:void(0)" class="al finishPay">查看购买结果</a>');
            htmlArr.push('    <a href="javascript:void(0)" class="b1 closePop">遇到问题</a>');
            htmlArr.push('</div>');

            // 点击完成支付
            $('#paypop_msg').undelegate('.finishPay', 'click').delegate('.finishPay', 'click', function(){
                that.finishOrder();
                return false;
            });
            Pay.Common.showPop(htmlArr.join(''));
            return false;
        },
        // 点击完成支付，检测处理
        finishOrder: function(){
            var postData = this.postData;
            var site = window.PlatInfo.site || '/';
            if (postData.orderlabel) {
                var orderlabel = postData.orderlabel;
                // 检测充值完成情况
                Pay.Common.Lib.updatePayStateByOrderLabel({orderlabel:orderlabel}, function(result){
                    var htmlArr = [];
                    if (result.status == 1) {
                        htmlArr.push('<div class="tTip suc">');
                        htmlArr.push('    <h5 class="h03">恭喜，您购买成功！</h5>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        htmlArr.push('    <a href="javascript:/*点击关闭*/" class="am closePop">确定</a>');
                        htmlArr.push('</div>');
                    } else {
                        // 未完成支付
                        htmlArr.push('<div class="tTip">');
                        htmlArr.push('    <h5 class="h02">抱歉，充值失败！</h5>');
                        htmlArr.push('    <p>');
                        htmlArr.push('        充值遇到问题，查看充值常见问题。<br>');
                        htmlArr.push('        拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
                        htmlArr.push('    </p>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        htmlArr.push('    <a href="' + postData.kfurl + '" class="al" target="_blank">联系在线客服</a>');
                        htmlArr.push('    <a href="/" class="b1">返回充值中心</a>');
                        htmlArr.push('</div>');
                    }
                    Pay.Common.showPop(htmlArr.join(''));
                });
            } else {
                window.location = site;
            }
            return false;
        }
    },
    submitPayUid: {
        // 表单数据
        postData: {},
        // 表单弹窗确认
        comfirmOrder: function(newPostData, label, _state){
            var that = this;
            var postData = this.postData = newPostData;

            var noticeArr = [];

            if ((!postData.usercode) || !(/.{3,20}$/i.test(postData.usercode))) {
                noticeArr.push('请先登陆平台账号。');
            }

            if (!postData.paychannel) {
                noticeArr.push('请选择充值方式。');
            }

            if (postData.paychannel.indexOf('E-BANK') > -1) {
                if (postData.banktype == '') {
                    noticeArr.push('请选择支付银行类型。');
                }
                if (postData.bankname == '') {
                    noticeArr.push('请选择支付银行名称。');
                }
            }

            if (!postData.isLogin && postData.paychannel == 'ROOF') {
                noticeArr.push('使用该充值方式，请先登陆。');
            }

            if (postData.paychannel == 'ROOF') {
                var balance = postData.user_balance && (parseFloat(postData.user_balance) || 0);
                if (balance <= 0 || balance < parseFloat(postData.payamount)) {
                    // 余额不足
                    noticeArr.push('您的余额不足，请使用其他支付方式！');
                }
            }

            if ((!postData.payamount) || (!postData.newuid)) {
                noticeArr.push('请选择靓号！');
            }

            var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
            if (postData.userphone && !phoneReg.test(postData.userphone)) {
                noticeArr.push('您输入的联系手机不正确。');
            }

            // 遇到问题联系客服
            if (window.PlatInfo && window.PlatInfo.kfcall) {
                var callArr = window.PlatInfo.kfcall.replace(/\r/g, '').split("\n");
                postData.kfcall = callArr[0].replace(/^\s*|\s*$/g, '');
            } else {
                noticeArr.push('请设置客服联系信息。');
            }

            // 遇到问题在线客服url
            if (window.PlatInfo && window.PlatInfo.kfurl) {
                postData.kfurl = window.PlatInfo.kfurl;
            } else {
                noticeArr.push('请设置在线客服url。');
            }

            if (noticeArr.length) {
                Pay.Common.showMsg('以下原因导致提交失败：', noticeArr.join('</p><p>'));
                return false;
            }

            // 实名认证处理
            var authflag = false;
            var _pos = {userCode: postData.usercode};
            if (!_state) _pos.step = 1;
            Pay.Common.Lib.doCheckRealNameAuth(_pos, function(rs){
                if (!rs.status) {
                    authflag = true;
                } else {
                    $('#paypop_msg').data('comfirmOrderData', [newPostData, label, 'submitPayUid']);
                    $.ajax({
                        type: "GET",
                        url: Pay.Ulib.getUrl('ajax', 'op/goRealName'),
                        data: {userCode: postData.usercode, step:1, sid:postData.serverid},
                        success: function(result){
                        }
                    });

                    if (!rs.data.isLogin) {
                        // 提示认证说明
                        Pay.Common.showRealNameNoticePop(postData.usercode);
                    } else {
                        // 认证弹窗
                        Pay.Common.showRealNamePop();
                    }
                }
            }, true);
            if (!authflag) {
                return false;
            }


            postData.orderlabel = (new Date()).getTime() + '' + parseInt(Math.random()*10);
            postData.userphone  = postData.userphone || '';
            var postUrl = '/payform/';

            var htmlArr = [];
            htmlArr.push('<form name="Form2" action="' + postUrl + '" method="POST" target="_blank" onsubmit="return false;">');
            htmlArr.push('<input type="hidden" name="serverid" value="' + postData.serverid + '" />');
            htmlArr.push('<input type="hidden" name="paytarget" value="BUY" />');
            htmlArr.push('<input type="hidden" name="paychannel" value="' + postData.paychannel + '" />');
            htmlArr.push('<input type="hidden" name="usercode" value="' + postData.usercode +'" />');
            htmlArr.push('<input type="hidden" name="payamount" value="' + postData.payamount + '" />')
            htmlArr.push('<input type="hidden" name="orderlabel" value="' + postData.orderlabel + '" />');
            htmlArr.push('<input type="hidden" name="userphone" value="' + postData.userphone + '" />');
            htmlArr.push('<input type="hidden" name="extend" value="' + postData.newuid + '" />');

            // 哪家的网银
            if (postData.paychannel.indexOf('E-BANK') > -1) {
                htmlArr.push('<input type="hidden" name="banktype" value="' + postData.banktype + '" />');
            }

            htmlArr.push('<ul>');

            // 充值方式
            if (postData.paychannel.indexOf('E-BANK') > -1 && postData.banktype && postData.bankname) {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  ' - ' + postData.bankname + '</p></li>');
            } else {
                htmlArr.push('<li><span>支付方式：</span><p>' + postData.paychannelname +  '</p></li>');
            }

            // 当前靓号
            htmlArr.push('<li><span>当前靓号：</span><p>' + postData.uid + '</p></p>');

            // 购买靓号
            htmlArr.push('<li><span>购买靓号：</span><p>' + postData.newuid + '</p></p>');

            // 靓号价格
            htmlArr.push('<li><span>靓号价格：</span><p>' + postData.showpayamount + '</p></li>');

            // 支付密码
            if (postData.paychannel == 'ROOF') {
                htmlArr.push('<li><span>支付密码：</span><p><input type="password" name="user_paypwd" value="" id="user_paypwd" />&nbsp;<a href="/payPwd/getpaypwd/1/" target="_blank">忘记密码？</a></p></li>');
            }

            htmlArr.push('</ul>');
            htmlArr.push('</form>');

            var btnArr = [];
            btnArr.push('<div class="tBtn">');
            btnArr.push('    <a href="javascript:void(0)" class="al paySubmit">确认充值</a>');
            btnArr.push('    <a href="javascript:void(0)" class="b1 closePop">返回修改</a>');
            btnArr.push('</div>');

            // 提交表单处理
            $('#paypop_msg').undelegate('.paySubmit', 'click').delegate('.paySubmit', 'click', function(){
                that.submitOrder();
                return false;
            });
            Pay.Common.showPop('<div class="paySure">' + htmlArr.join('') + '</div>' + btnArr.join(''));

            return false;
        },
        // 弹窗表单提交
        submitOrder: function(){
            var that = this;
            var postData = that.postData;
            var noticeArr = [];
            if (postData.paychannel == 'ROOF') {
                var user_paypwd = $('#user_paypwd').val();
                var paypwdReg = [/[A-Za-z_]+/g, /[0-9]+/g];
                if (user_paypwd.length >= 6 && paypwdReg[0].test(user_paypwd) && paypwdReg[1].test(user_paypwd)) {
                    // 支付密码md5传输
                    if (user_paypwd.length != 32) {
                        if (typeof(hex_md5) == 'function') {
                            $('#user_paypwd').val(hex_md5($('#user_paypwd').val()));
                        }
                    }
                } else {
                    noticeArr.push('支付密码不正确。');
                }
            }

            // 错误提示
            if (noticeArr.length) {
                alert(noticeArr.join("\n"));
                return false;
            }

            document.Form2.submit();

            // 在新打开的支付页面完成付款
            var htmlArr = [];
            htmlArr.push('<div class="tTip">');
            htmlArr.push('    <h5>请您在打开的支付页面完成付款</h5>');
            htmlArr.push('    <p>');
            htmlArr.push('        支付完成前请不要关闭或刷新此窗口。<br>');
            htmlArr.push('        支付失败时，可拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
            htmlArr.push('    </p>');
            htmlArr.push('</div>');
            htmlArr.push('<div class="tBtn">');
            htmlArr.push('    <a href="javascript:void(0)" class="al finishPay">查看购买结果</a>');
            htmlArr.push('    <a href="javascript:void(0)" class="b1 closePop">遇到问题</a>');
            htmlArr.push('</div>');

            // 点击完成支付
            $('#paypop_msg').undelegate('.finishPay', 'click').delegate('.finishPay', 'click', function(){
                that.finishOrder();
                return false;
            });
            Pay.Common.showPop(htmlArr.join(''));
            return false;
        },
        // 点击完成支付，检测处理
        finishOrder: function(){
            var postData = this.postData;
            var site = window.PlatInfo.site || '/';
            if (postData.orderlabel) {
                var orderlabel = postData.orderlabel;
                // 检测充值完成情况
                Pay.Common.Lib.updatePayStateByOrderLabel({orderlabel:orderlabel}, function(result){
                    var htmlArr = [];
                    if (result.status == 1) {
                        htmlArr.push('<div class="tTip">');
                        htmlArr.push('    <h5 class="h03">恭喜，您购买成功！</h5>');
                        htmlArr.push('    <p>您的靓号为：' + postData.newuid + '<br />您可用该靓号登录' + postData.platname + '平台。</p>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        htmlArr.push('    <a href="javascript:/*点击关闭*/" class="am closePop">确定</a>');
                        htmlArr.push('</div>');
                    } else {
                        // 未完成支付
                        htmlArr.push('<div class="tTip">');
                        htmlArr.push('    <h5 class="h02">抱歉，充值失败！</h5>');
                        htmlArr.push('    <p>');
                        htmlArr.push('        充值遇到问题，查看充值常见问题。<br>');
                        htmlArr.push('        拨打<strong>' + postData.kfcall + '</strong>，或<a href="' + postData.kfurl + '" target="_blank">点击这里</a>联系客服。');
                        htmlArr.push('    </p>');
                        htmlArr.push('</div>');
                        htmlArr.push('<div class="tBtn">');
                        htmlArr.push('    <a href="' + postData.kfurl + '" class="al" target="_blank">联系在线客服</a>');
                        htmlArr.push('    <a href="/payforuid/" class="b1">返回充值中心</a>');
                        htmlArr.push('</div>');
                    }
                    Pay.Common.showPop(htmlArr.join(''));
                });
            } else {
                window.location = site;
            }
            return false;
        }

    },

    getForceGame: function(loc) {
        loc = typeof(loc) == 'undefined' ? window.location + '' : loc;
        // 额外区服配置
        var forceList = {
            game2: {
                tscj: {
                    info: {"id":102, "name":"天书残卷", "code":"tscj", "pinyin":"tianshucanjuan"},
                    server: {
                        9999: {"id":8806, "name":"[9999服]天书9999", "code":"tscj9999", "open_date":'', "area":9999}
                    }
                },
                ts: {
                    info: {"id":125, "name":"天书世界", "code":"ts", "pinyin":"tianshushijie"},
                    server: {
                        0: {"id":9026, "name":"[0服]第0服", "code":"ts0", "open_date":'', "area":0}
                    }
                }
            },
            2667: {
                //cjh: {
                //    info: {"id":13, "name":"闯江湖", "code":"cjh", "pinyin":"chuangjianghu"},
                //    server: {
                //        1: {"id":572, "name":"[1服]第1服", "code":"cjh1", "open_date":'', "area":1}
                //    }
                //}
                dtx: {
                    info: {"id":16, "name":"打天下", "code":"dtx", "pinyin":"datianxia"},
                    server: {
                        1: {"id":576, "name":"[1服]第1服", "code":"dtx1", "open_date":'', "area":1}
                    }
                }
            }
        };


        // 检测平台
        if ((!window.PlatInfo.code) || (!forceList) || !forceList[window.PlatInfo.code]) {
            return false;
        }

        // 计算额外游戏区服
        var paramStr = loc.match(/:\/\/[^/]+\/([^?&]+)/);
        if ((!paramStr) || !paramStr[1]) {
            return false;
        }

        var paramArr = paramStr[1].split('/');
        var info = {};
        for (var i=1,len=paramArr.length; i<len; i+=2) {
            info[paramArr[i]] = paramArr[i+1];
        }
        if (typeof(info['force']) == 'undefined' || typeof(info['g']) == 'undefined' || typeof(info['s']) == 'undefined') {
            return false;
        }

        // 检测游戏是否存在
        if (!forceList[window.PlatInfo.code][info['g']]) {
            return false;
        }

        // 检测区服是否存在
        if ((!forceList[window.PlatInfo.code][info['g']].server) || !forceList[window.PlatInfo.code][info['g']].server[info['s']]) {
            return false;
        }

        return {
            info: forceList[window.PlatInfo.code][info['g']].info,
            server: forceList[window.PlatInfo.code][info['g']].server[info['s']]
        }
    },

    // 检测是否天书
    getTsCodeByGid: function(gid) {
        var type = {125:'ts', 141:'tsqy', 183:'tshg'};
        return type[gid] || '';
    },
    getTsGidByCode: function(code) {
        var typed = {'ts': 125, 'tsqy': 141, 'tshg': 183};
        return typed[code] || '';
    },
    doCheckRealNameAuth: function(postData, callback, sync) {
        var async = !sync;
        var returnArr = {status: 0, msg:''};
        callback = callback || function(){};
        if (postData) {
            if (!postData.userCode) {
                returnArr.msg = '请输入您的账号';
                return callback(returnArr);
            } else if (/^[a-z_][a-z_0-9]*$/i.test(postData.userCode)) {
                if (postData.userCode.length > 20 || postData.userCode.length <3) {
                    returnArr.msg = '您输入的账号有误';
                    return callback(returnArr);
                }
            } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(postData.userCode)) && !(postData.userCode.indexOf('@')>0)) {
                returnArr.msg = '您输入的账号有误';
                return callback(returnArr);
            }

            if ((window.location+'').indexOf('enablerealname') > -1) postData.enablerealname = 1;

            $.ajax({
                type: "GET",
                url: Pay.Ulib.getUrl('ajax', 'op/checkRealNameAuth'),
                data: postData,
                async: async,
                dataType: 'json',
                success: function(result){
                    callback(result);
                    return false;
                }
            });
        } else {
            returnArr.msg = '提交的数据不可为空';
            return callback(returnArr);
        }
    },
    doSetRealNameAuth: function(postData, callback) {
        var returnArr = {status: 0, msg:''};
        callback = callback || function(){};
        if (postData) {
            if (!postData.name) {
                returnArr.msg = '请输入您的真实姓名';
                return callback(returnArr);
            }
            if (!/^[\u4e00-\u9fa5]+$/.test(postData.name)) {
                returnArr.msg = '请输入您的真实姓名';
                return callback(returnArr);
            }
            if (!window.surname) {
                returnArr.msg = '请输入您的真实姓名.';
                return callback(returnArr);
            }
            if (window.surname.indexOf(postData.name.substr(0,2) + "|") == -1 && window.surname.indexOf(postData.name.substr(0,1) + "|") == -1 || !/^[\u4e00-\u9fa5]{2,4}$/.test(postData.name)) {
                returnArr.msg = '请输入您的真实姓名';
                return callback(returnArr);
            }

            if (!postData.idcard) {
                returnArr.msg = '请输入您的身份证号码';
                return callback(returnArr);
            }
            if (!window.IsIdCard) {
                returnArr.msg = '请输入您的身份证号码.';
                return callback(returnArr);
            }
            if (!window.IsIdCard(postData.idcard)) {
                returnArr.msg = '请输入您的身份证号码';
                return callback(returnArr);
            }

            if ((window.location+'').indexOf('enablerealname') > -1) postData.enablerealname = 1;

            var flag = false;
            $.ajax({
                type: 'GET',
                url: Pay.Ulib.getUrl('ajax', 'op/checkIdCard'),
                data: {idcard: postData.idcard},
                dataType: 'json',
                async: false,
                success: function(result){
                    if (result.status == '1') {
                        flag = true;
                    } else {
                        returnArr.msg = result.msg;
                    }
                }
            });
            if (!flag) {
                return callback(returnArr);
            }

            postData.step = 4;
            $.ajax({
                type: "GET",
                url: Pay.Ulib.getUrl('ajax', 'op/setRealNameAuth'),
                data: postData,
                dataType: 'json',
                success: function(result) {
                    callback(result);
                    return false;
                }
            });
        } else {
            returnArr.msg = '提交的数据不可为空';
            return callback(returnArr);
        }
    }

});
//}}}


// Pay.Common.showMsg{{{
Pay.Common.showMsg = function(msg, content){
    var htmlArr = [];
    htmlArr.push('<div class="tTip suc">');
    htmlArr.push('    <h5 class="h03">' + msg + '</h5>');
    if (content) {
        content = content.replace(/\r/g, '').replace(/\n/g, '<br />');
        htmlArr.push('<p>' + content + '</p>');
    }
    htmlArr.push('</div>');
    htmlArr.push('<div class="tBtn">');
    htmlArr.push('    <a href="javascript:void(0)" class="am closePop">确 定</a>');
    htmlArr.push('</div>');

    $('#paypop_msg').attr('class', 'paypop tan');
    $('#paypop_msg .paypop_content').html(htmlArr.join(''));
    Pay.window('#paypop_msg').open();
};
//}}}

// Pay.Common.showPop{{{
Pay.Common.showPop = function(content, title){
    title = title || '温馨提示';
    $('#paypop_msg').attr('class', 'paypop tan');
    $('#paypop_msg h3:first span').html(title);
    $('#paypop_msg .paypop_content').html(content);
    Pay.window('#paypop_msg').open();
};
//}}}

// Pay.Common.showRealNameNoticePop{{{
Pay.Common.showRealNameNoticePop = function(usercode){
    var title = '实名认证';
    var contentArr = [];
    contentArr.push('<div class="paypop_content tanM">');
    contentArr.push('    <p class="avatar">');
    contentArr.push('        <img src="/images/avatar.jpg" />');
    contentArr.push('    </p>');
    contentArr.push('    <p class="pTip">');
    contentArr.push('        亲爱的玩家：按照国家政策要求，网游用户需要进行身份信息绑定。');
    contentArr.push('    </p>');
    contentArr.push('    <p class="btn">');
    contentArr.push('        <a class="goRealNameBtn" href="javascript:void(0)">前往认证</a>');
    contentArr.push('    </p>');
    contentArr.push('</div>');

    // 显示登陆框
    $('#paypop_msg').undelegate('.goRealNameBtn', 'click').delegate('.goRealNameBtn', 'click', function(){
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/goLogin'),
            data: {userCode:usercode , step: 2},
            success: function(result) {
            }
        });
        Pay.Common.showLoginPop(usercode||'', 'goRealName');
        return false;
    });

    $('#paypop_msg').attr('class', 'paypop tan tan2');
    $('#paypop_msg h3:first span').html(title);
    $('#paypop_msg .paypop_content').html(contentArr.join(''));
    Pay.window('#paypop_msg').open();
};
//}}}

// Pay.Common.showLoginPop{{{
Pay.Common.showLoginPop = function(keepcode, label){
    var keepcode = keepcode || Pay.Ulib.getCookie('KEEPCODE') || Pay.Ulib.getCookie('keepcode') || '';
    var label = label || '';
    var title = '账号登录';
    var contentArr = [];
    contentArr.push('<div class="logWrap">');
    contentArr.push('    <form class="ajax_login_form" method="POST" data-label="' + label + '">');
    contentArr.push('    <p class="pWarn" style="display:block;"></p>');
    contentArr.push('    <p id="p1">');
    contentArr.push('        <label>通行证：</label>');
    contentArr.push('        <input class="i ii" name="code" type="text" value="' + keepcode + '" tabindex="1" data-placeholder="账号" />');
    contentArr.push('        <a class="del" href="javascript:void(0)" style="' + (keepcode ? 'display:block' : 'display:none') + ';">清除</a>');
    contentArr.push('    </p>');
    contentArr.push('    <p id="p2">');
    contentArr.push('        <label>密　码：</label>');
    contentArr.push('        <span class="placeholder">密码</span>');
    contentArr.push('        <input class="i" name="password" tabindex="2" value="" data-placeholder="密码" type="password">');
    contentArr.push('    </p>');
    if (window.PlatInfo.seccode) {
        contentArr.push('    <p id="validate">');
        contentArr.push('        <label>验证码：</label>');
        contentArr.push('        <input class="i ii" name="vcode" type="text" value="" tabindex="3" data-placeholder="验证码" />');
        contentArr.push('        <img class="vcodeItem" width="64" height="32" src="/verifyCode.php?r=' + Math.random() + '" title="点击刷新验证码" alt="点击刷新验证码" />');
        contentArr.push('    </p>');
    }
    contentArr.push('    <p id="pCheck">');
    contentArr.push('        <input checked="checked" value="1" name="keeplive" style="display: none;" type="checkbox">');
    contentArr.push('        <label class="checkbox checked checkboxItem" data-name="keeplive" data-checkedcls="checked">记住账号</label>');
    contentArr.push('        <a href="' + (window.PlatInfo.getpwd||'') + '" target="_blank">找回密码</a>');
    contentArr.push('    </p>');
    contentArr.push('    <p class="btn">');
    contentArr.push('        <button type="submit" tabindex="4" >立即登录</button>');
    contentArr.push('    </p>');
    if (window.PlatInfo.code == 'game2') {
        var calurl = window.location.protocol == 'https:' ? 'https://passport.game2.cn' : 'http://www.game2.cn';
        var url = '';
        var $server_id   = $('#server_id');
        var $lastChannel = $('#lastChannel');
        var $amount      = $('#amount');
        if ($server_id.length && $lastChannel.length && $amount.length) {
            var server_id   = $server_id.val();
            var lastChannel = $lastChannel.val();
            var amount      = $amount.val();
            if (server_id > 0) url = 's/' + server_id + '/';
            if (lastChannel > 0) url = 'type/' + lastChannel + '/';
            if (amount > 0) url = 'm/' + amount + '/';


            var view = (window.PlatInfo && window.PlatInfo.passport ? window.PlatInfo.passport : '/') + 'pay/';
            var viewArr = {'submitPayVip': 'payforvip', 'submitPayUid': 'payforuid'};
            var orderData = $('#paypop_msg').data('comfirmOrderData');
            if (orderData && orderData[2] && viewArr[orderData[2]]) {
                view = '/' + viewArr[orderData[2]] + '/';
            }
            if (url) url = view + url;
        }

        contentArr.push('    <div class="otherLogin" data-otherlogin-config=\'{"remark":"::' + encodeURIComponent(calurl) + ':' + (url ? encodeURIComponent(url) : '') + '"}\'></div>');
    }
    contentArr.push('    </form>');
    contentArr.push('</div>');

    var $paypop_msg = $('#paypop_msg');
    $paypop_msg.attr('class', 'paypop tan logDiv');
    $paypop_msg.find('h3:first span').html(title);
    $paypop_msg.find('.paypop_content').html(contentArr.join(''));
    $paypop_msg.find('input[type=text],input[type=password]').trigger('init');
    setTimeout(function(){$paypop_msg.find('input[name="code"],input[name="password"]').trigger('blur', 'nostate');}, 20);
    if (window.PlatInfo.code == 'game2') {
        setTimeout(function(){window.J && window.J.OtherLogin && window.J.OtherLogin.initOtherLogin();}, 0);
    }

    Pay.window('#paypop_msg').open();
};
//}}}

// Pay.Common.showRealNamePop{{{
Pay.Common.showRealNamePop = function(){
    var title = '完善资料';
    var contentArr = [];
    contentArr.push('<div class="pTip">');
    contentArr.push('    <h4>完善资料</h4>');
    contentArr.push('    <p>按照国家政策要求，网游用户需要进行身份信息绑定</p>');
    contentArr.push('</div>');
    contentArr.push('<div class="realWrap clearfix">');
    contentArr.push('    <form class="ajax_auth_form" method="POST">');
    contentArr.push('    <p class="pWarn" style="display:block"></p>');
    contentArr.push('    <p id="p01">');
    contentArr.push('        <label>姓　名：</label>');
    contentArr.push('        <input class="i ii" name="name" type="text" value="" data-placeholder="请输入真实姓名" />');
    contentArr.push('        <var class="sp_name"></var>');
    contentArr.push('    </p>');
    contentArr.push('    <p class="pSpan">如：张三</p>');
    contentArr.push('    <p id="p02">');
    contentArr.push('        <label>身份证：</label>');
    contentArr.push('        <input class="i ii" name="idcard" type="text" value="" data-placeholder="请输入有效身份证号" />');
    contentArr.push('        <var class="sp_idcard"></var>');
    contentArr.push('    </p>');
    contentArr.push('    <p class="pSpan">如：220523198508177210</p>');
    if (window.PlatInfo && window.PlatInfo.id == '1') {
        contentArr.push('    <p class="checkbox">');
        contentArr.push('        <input name="readed" value="1" checked="checked" style="display:none;" type="checkbox">');
        contentArr.push('        <label class="checkbox checked protocolCheckboxItem" data-name="readed" data-checkedcls="checked"></label>');
        contentArr.push('        <label class="wykg">我已经看过并同意</label>');
        contentArr.push('        <span>《</span> <a target="_blank" href="http://www.game2.cn/protocol/">哥们网服务协议</a><span>》</span>');
        contentArr.push('    </p>');
    }
    contentArr.push('    <p class="btn">');
    contentArr.push('        <button class="protocalBtn" type="submit" tabindex="">提交信息</button>');
    contentArr.push('    </p>');
    contentArr.push('    <p class="pTip2">');
    contentArr.push('        • 身份信息只需提交一次');
    contentArr.push('    </p>');
    contentArr.push('    </form>');
    contentArr.push('</div>');

    var $paypop_msg = $('#paypop_msg');
    $paypop_msg.attr('class', 'paypop tan realDiv');
    $paypop_msg.find('h3:first span').html(title);
    $paypop_msg.find('.paypop_content').html(contentArr.join(''));
    var $protocolCheckboxItem = $paypop_msg.find('.protocolCheckboxItem');
    if ($protocolCheckboxItem.length) {
        $protocolCheckboxItem.unbind('click').bind('click', function(){
            var $that = $(this);
            var name = $that.attr('data-name');
            var checkedCls = $that.attr('data-checkedCls');
            var $form = $that.closest('form');
            if ($form.length) {
                $checkItem = $form.find('input[name="'+name+'"]');
            } else {
                $checkItem = $('input[name="'+name+'"]');
            }
            var $submit = $form.find('.protocalBtn');
            if ($that.hasClass(checkedCls)) {
                $that.removeClass(checkedCls);
                $checkItem.attr('checked', false);
                $submit.addClass('dis');
            } else {
                $that.addClass(checkedCls);
                $checkItem.attr('checked', true);
                $submit.removeClass('dis');
            }
            return false;
        });
    }

    $paypop_msg.find('input[type=text],input[type=password]').trigger('init');
    setTimeout(function(){$paypop_msg.find('input[name="name"],input[name="idcard"]').trigger('blur', 'nostate');}, 20);
    if (!window.surname) $.getScript('/js/sname.js');

    Pay.window('#paypop_msg').open();
};
//}}}

// Pay.Common {{{
Pay.Common.prototype = $.extend(Pay.Common.prototype, {
    moduleName: 'Pay.Common',  // 对应实例  Pay.Common.instance
    quickPayList: {},  // 缓存快速充值列表
    handleArr: {}, //setTimeout handle
    events: [
        //['.ajax_login', 'click', 'login_click'],
        //['.ajax_login2', 'click', 'login2_click'],
        ['.ajax_signout', 'click', 'signout_click'],
        ['body', 'delegate|click|.vcodeItem', 'vcodeItem_click']
    ],
    // 初始化函数
    init: function() {
        this.enablePlugin();
        this.enablePlaceholder();
        this.checkboxItem();
        // ajax登陆
        this.enableAjaxLogin();
        // ajax登陆2
        this.enableAjaxLogin2();
        // 实名认证
        this.enableRealNameAuth();
        // 头部用户名hover事件
        this.enableHeaderUnameHover();
        this.enableFooterHover();
        // 下拉滚动列表插件
        this.enableScrollBarPlugin();
        // 快速充值
        this.enableQuickPay();
        // document click
        this.enableDocumentClick();
        //hover处理
        this.enableBtnHover();
        this.enableBtnFocus();
    },
    enablePlugin: function(){
        $('.selectPlugin').selectPlugin();
        $('.scrollBarPlugin').scrollBarPlugin();
        $('.lazyLoadPlugin').lazyLoadPlugin();
    },
    enablePlaceholder: function(){
        var hasPlaceholderItem = function($that){
            if ($that.length) {
                var placeholder = $that.attr('data-placeholder');
                if (placeholder) {
                    return placeholder;
                }
            }
            return false;
        };
        $('body').delegate('input[type=text],input[type=password]', 'init', function(){
            var $that = $(this);
            var placeholder = hasPlaceholderItem($that);
            if (placeholder) {
                if (!$that.siblings('.placeholder').length) {
                    if ($that.val()) {
                        $that.before('<span class="placeholder" style="display:none;">' + placeholder + '</span>');
                    } else {
                        $that.before('<span class="placeholder">' + placeholder + '</span>');
                    }
                }
            }
        }).delegate('input[type=text],input[type=password]', 'focus keyup blur', function(){
            var $that = $(this);
            var placeholder = hasPlaceholderItem($that);
            if (placeholder) {
                if ($that.val()) {
                    $that.parent().find('.placeholder').hide();
                } else {
                    $that.parent().find('.placeholder').show();
                }
            }
        });
        $('input[type=text],input[type=password]').trigger('init');
    },
    checkboxItem: function(){
        $('body').delegate('.checkboxItem', 'click', function(){
            var $that = $(this);
            var name = $that.attr('data-name');
            var checkedCls = $that.attr('data-checkedCls');
            var $form = $that.closest('form');
            if ($form.length) {
                $checkItem = $form.find('input[name="'+name+'"]');
            } else {
                $checkItem = $('input[name="'+name+'"]');
            }
            if ($that.hasClass(checkedCls)) {
                $that.removeClass(checkedCls);
                $checkItem.attr('checked', false);
            } else {
                $that.addClass(checkedCls);
                $checkItem.attr('checked', true);
            }
            return false;
        });
    },
    enableAjaxLogin: function(){
        var $pop = $('#paypop_msg');
        if ($pop.length) {
            $('#paypop_msg').delegate('input[name="code"]', 'focus', function(){
                var $p = $(this).parent();
                var $placeholder = $p.find('span:first');
                var val = $(this).val();
                if ('' == val) {
                    $placeholder.html('账号');
                    $p.find('.del').hide();
                } else {
                    $p.find('.del').show();
                }
                $p.addClass('pOn');
            }).delegate('input[name="code"]', 'blur', function(evt, label){
                var $that = $(this);
                var $p = $that.parent();
                var $placeholder = $p.find('span:first');
                var val = $that.val();
                if ('' == val) {
                    $placeholder.html('账号');
                }

                if (label != 'nostate') {
                    var $pWarn = $('.ajax_login_form .pWarn').html('');
                    if ('' == val) {
                        $pWarn.html('请输入您的账号')
                    } else if (/[\u4E00-\u9FA5]/g.test(val) || val.length > 20 || val.length < 4) {
                        $pWarn.html('您输入的账号有误');
                    }
                }
                $p.removeClass('pOn');
            }).delegate('input[name="code"]', 'keyup', function(){
                var $p = $(this).parent();
                var $placeholder = $p.find('span:first');
                var val = $(this).val();
                if ('' == val) {
                    $placeholder.html('账号');
                    $p.find('.del').hide();
                } else {
                    $placeholder.html('');
                    $p.find('.del').show();
                }
            }).delegate('.del', 'click', function(){
                var $p = $(this).parent();
                var $code = $p.find('input[name="code"]');
                $code.val('');
                setTimeout(function(){$code.focus();}, 0);
                return false;
            }).delegate('input[name="password"]', 'focus', function(){
                var $p = $(this).parent();
                var $placeholder = $p.find('span:first');
                $p.addClass('pOn');
            }).delegate('input[name="password"]', 'blur', function(evt, label){
                var $p = $(this).parent();
                var $placeholder = $p.find('span:first');
                var val = $(this).val();
                if ('' == val) {
                    $placeholder.html('密码');
                }

                if (label != 'nostate') {
                    var $pWarn = $('.ajax_login_form .pWarn').html('');
                    if ('' == val) {
                        $pWarn.html('请输入您的密码');
                    } else if (val.length < 6) {
                        $pWarn.html('您输入的密码有误');
                    }
                }
                $p.removeClass('pOn');
            }).delegate('input[name="password"]', 'keyup', function(){
                var $p = $(this).parent();
                var $placeholder = $p.find('span:first');
                var val = $(this).val();
                if ('' == val) {
                    $placeholder.html('密码');
                } else {
                    $placeholder.html('');
                }
            }).delegate('.ajax_login_form', 'submit', function(){
                var $this = $(this);
                var label = $this.attr('data-label') || '';
                var postData = Pay.Ulib.parseSerializeArr($this.serializeArray());
                $this.find('#p1,#p2').removeClass('pOn');
                postData.step = 3;
                Pay.Common.Lib.doSubmitLogin(postData, function(result){
                    if (result.status_code == 'login_success') {
                        $this.find('.pWarn').html('');
                        if (label && label == 'goRealName') {
                            // 实名认证处理
                            Pay.Common.Lib.doCheckRealNameAuth({userCode: postData.code}, function(rs){
                                if (!rs.status) {
                                    window.location = window.location + '';
                                } else {
                                    Pay.Common.showRealNamePop();
                                }
                            }, true);
                        } else {
                            window.location = window.location + '';
                        }
                    } else {
                        $this.find('.pWarn').html(result.status_msg);
                        if (result.status_code == 'username_is_empty' || result.status_code == 'username_is_error') {
                            $this.find('#p1').addClass('pErr');
                            $this.find('#p2').removeClass('pErr');
                            setTimeout(function(){$this.find('input[name="code"]').focus();}, 50);
                        } else if (result.status_code == 'password_is_empty' || result.status_code == 'password_is_error') {
                            $this.find('#p1').removeClass('pErr');
                            $this.find('#p2').addClass('pErr');
                            setTimeout(function(){$this.find('input[name="password"]').focus();}, 50);
                        } else if (result.status_code == 'more_fail_login') {
                            $this.find('#p1,#p2').removeClass('pErr');
                        } else if (result.status_code == 'vcode_is_error') {
                            $this.find('#p1,#p2').removeClass('pErr');
                            $this.find('.vcodeItem').trigger('click');
                        } else {
                            $this.find('#p1,#p2').addClass('pErr');
                        }
                    }
                });
                return false;
            });
        }
    },
    enableAjaxLogin2: function(){
        var $hLoginWrap = $('.hLoginWrap');
        if ($hLoginWrap.length) {
            // 显示表单处理
            $('.ajax_login2,.hLoginWrap').bind('mouseenter', function(evt){
                if (Pay.Common.instance.handleArr['ajaxLogin']) {
                    clearTimeout(Pay.Common.instance.handleArr['ajaxLogin']);
                    Pay.Common.instance.handleArr['ajaxLogin'] = null;
                }
                if (Pay.Common.instance.handleArr['ajaxLoginFirst']) {
                    clearTimeout(Pay.Common.instance.handleArr['ajaxLoginFirst']);
                    Pay.Common.instance.handleArr['ajaxLoginFirst'] = null;
                }
                Pay.Common.instance.login2_click(evt, 'show');
            }).bind('mouseleave', function(evt){
                var $target = $(evt.target);
                if ($target.closest('#p1').length || $target.closest('#p2').length || $target.closest('#validate').length) {
                    return true;
                }
                if (Pay.Common.instance.handleArr['ajaxLogin']) {
                     clearTimeout(Pay.Common.instance.handleArr['ajaxLogin']);
                     Pay.Common.instance.handleArr['ajaxLogin'] = null;
                }
                Pay.Common.instance.handleArr['ajaxLogin'] = setTimeout(function(){Pay.Common.instance.login2_click(evt, 'hide');}, 50);
            });
            // 自动展开, 3秒关闭
            setTimeout(function(){Pay.Common.instance.login2_click(null, 'show');}, 0);
            Pay.Common.instance.handleArr['ajaxLoginFirst'] = setTimeout(function(){Pay.Common.instance.login2_click(null, 'hide');}, 3000);


            // 表单检测处理
            var checkFormHandle = null;
            var checkForm = function(evt, label){
                if (checkFormHandle) {clearTimeout(checkFormHandle);checkFormHandle=null;};
                $(this).closest('p').removeClass('pOn');
                if (label != 'nostate') {
                    var $form = $('.ajax_login2_form');
                    var $pWarn = $form.find('.pWarn').html('');

                    // 账号检测
                    var $code  = $form.find('input[name="code"]');
                    var $codeP = $code.closest('p').removeClass('pErr');
                    var code   = $code.val();
                    if ('' == code) {
                        $pWarn.html('请输入您的账号');
                        $codeP.addClass('pErr');
                        setTimeout(function(){$code.focus();}, 0);
                        return;
                    } else if (/[\u4E00-\u9FA5]/g.test(code) || code.length > 20 || code.length < 4) {
                        $pWarn.html('您输入的账号有误');
                        $codeP.addClass('pErr');
                        setTimeout(function(){$code.focus();}, 0);
                        return;
                    }

                    // 密码检测
                    var $password  = $form.find('input[name="password"]');
                    var $passwordP = $password.closest('p').removeClass('pErr');
                    var password   = $password.val();
                    if (document.activeElement) {
                        if ($(document.activeElement)[0] == $password[0]) {
                            return;
                        } else if ($(document.activeElement)[0] == $code[0] && password == '') {
                            return;
                        }
                    }
                    if ('' == password) {
                        $pWarn.html('请输入您的密码');
                        $passwordP.addClass('pErr');
                        //setTimeout(function(){$password.focus();}, 0);
                        return;
                    } else if (password.length < 6) {
                        $pWarn.html('您输入的密码有误');
                        $passwordP.addClass('pErr');
                        //setTimeout(function(){$password.focus();}, 0);
                        return;
                    }
                }
            };
            $('.hLoginWrap').delegate('input[name="code"]', 'focus', function(){
                var $p = $(this).parent();
                var val = $(this).val();
                if ('' == val) {
                    $p.find('.del').hide();
                } else {
                    $p.find('.del').show();
                }
                $p.addClass('pOn');
            }).delegate('input[name="code"]', 'blur', function(evt, label){
                var that = this;
                if (checkFormHandle) {clearTimeout(checkFormHandle);checkFormHandle=null;};
                checkFormHandle = setTimeout(function(){checkForm.call(that, evt, label);}, 50);
            }).delegate('input[name="code"]', 'keyup', function(){
                var $p = $(this).parent();
                var val = $(this).val();
                if ('' == val) {
                    $p.find('.del').hide();
                } else {
                    $p.find('.del').show();
                }
            }).delegate('.del', 'click', function(){
                var $p = $(this).parent();
                var $code = $p.find('input[name="code"]');
                $code.val('');
                setTimeout(function(){$code.focus();}, 0);
                return false;
            }).delegate('input[name="password"]', 'focus', function(){
                var $p = $(this).parent();
                $p.addClass('pOn');
            }).delegate('input[name="password"]', 'blur', function(evt, label){
                var $that = $(this);
                if (checkFormHandle) {clearTimeout(checkFormHandle);checkFormHandle=null;};
                checkFormHandle = setTimeout(function(){checkForm.call($that, evt, label);}, 200);
            }).delegate('input[name="password"]', 'keyup', function(){
                var $p = $(this).parent();
                var val = $(this).val();
            }).delegate('.ajax_login2_form', 'submit', function(){
                var $this = $(this);
                if (checkFormHandle) {clearTimeout(checkFormHandle);checkFormHandle=null;};
                $this.find('#p1,#p2').removeClass('pOn');
                Pay.Common.Lib.doSubmitLogin(Pay.Ulib.parseSerializeArr($this.serializeArray()), function(result){
                    if (result.status_code == 'login_success') {
                        $this.find('.pWarn').html('');
                        window.location = window.location + '';
                    } else {
                        $this.find('.pWarn').html(result.status_msg);
                        if (result.status_code == 'username_is_empty' || result.status_code == 'username_is_error') {
                            $this.find('#p1').addClass('pErr');
                            $this.find('#p2').removeClass('pErr');
                            setTimeout(function(){$this.find('input[name="code"]').focus();}, 50);
                        } else if (result.status_code == 'password_is_empty' || result.status_code == 'password_is_error') {
                            $this.find('#p1').removeClass('pErr');
                            $this.find('#p2').addClass('pErr');
                            setTimeout(function(){$this.find('input[name="password"]').focus();}, 50);
                        } else if (result.status_code == 'more_fail_login') {
                            $this.find('#p1,#p2').removeClass('pErr');
                        } else if (result.status_code == 'vcode_is_error') {
                            $this.find('#p1,#p2').removeClass('pErr');
                            $this.find('.vcodeItem').trigger('click');
                        } else {
                            $this.find('#p1,#p2').addClass('pErr');
                        }
                    }
                });
                return false;
            });
        }
    },
    enableRealNameAuth: function(){
        var $pop = $('#paypop_msg');
        if ($pop.length) {
            $('#paypop_msg').delegate('input[name="name"]', 'focus', function(){
                var $p = $(this).parent();
                $p.addClass('pOn');
            }).delegate('input[name="name"]', 'blur', function(evt, label){
                var $p = $(this).parent();
                $p.removeClass('pOn');
                if (label == 'nostate') return true;

                $('.ajax_auth_form').find('.pWarn').html('');
                var name = $(this).val();
                if (!name) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }
                if (!/^[\u4e00-\u9fa5]+$/.test(name)) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }
                if (!window.surname) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }
                if (window.surname.indexOf(name.substr(0,2) + "|") == -1 && window.surname.indexOf(name.substr(0,1) + "|") == -1 || !/^[\u4e00-\u9fa5]{2,4}$/.test(name)) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }

                $p.find('var').attr('class', 'suc');
            }).delegate('input[name="idcard"]', 'focus', function(){
                var $p = $(this).parent();
                $p.addClass('pOn');
            }).delegate('input[name="idcard"]', 'blur', function(evt, label){
                var $p = $(this).parent();
                $p.removeClass('pOn');
                if (label == 'nostate') return true;

                $('.ajax_auth_form').find('.pWarn').html('');
                var idcard = $(this).val();
                if (!idcard) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }
                if (!window.IsIdCard) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }
                if (!window.IsIdCard(idcard)) {
                    $p.find('var').attr('class', 'warn');
                    return false;
                }

                $p.find('var').attr('class', 'suc');
            }).delegate('.ajax_auth_form', 'submit', function(){
                var $this = $(this);
                if ($this.find('.protocalBtn').hasClass('dis')) {
                    return false;
                }

                var label = $this.attr('data-label') || '';
                $this.find('#p1,#p2').removeClass('pOn');
                Pay.Common.Lib.doSetRealNameAuth(Pay.Ulib.parseSerializeArr($this.serializeArray()), function(result){
                    if (result.status) {
                        $this.find('.pWarn').html('');
                        var orderData = $('#paypop_msg').data('comfirmOrderData');
                        if (orderData) {
                            $('#paypop_msg').data('comfirmOrderData', '');
                            if (orderData[2]) {
                                Pay.Common.Lib[orderData[2]].comfirmOrder(orderData[0], orderData[1]||0, 1);
                            } else {
                                Pay.Common.Lib.submitPay.comfirmOrder(orderData[0], orderData[1]||0, 1);
                            }
                        } else {
                            window.location = window.location + '';
                        }
                    } else {
                        $this.find('.pWarn').html(result.msg);
                    }
                });
                return false;
            });
        }
    },
    // 头部用户名hover事件
    enableHeaderUnameHover: function() {
        var $this = $('.header .logon .uName');
        if ($this.length) {
            var op = function(cmd){
                var $uname = $('.header .logon .uName');
                var $userInfo = $('.header .logon .uDown');
                var userInfoH = $userInfo.outerHeight(true);
                if (cmd == 'show') {
                    $uname.addClass('aOn');
                    $userInfo.stop().animate({top:'30px'}, 500);
                } else if (cmd == 'hide') {
                    $uname.removeClass('aOn');
                    $userInfo.stop().animate({top:'-'+userInfoH+'px'}, 200);
                } else {
                    if ($uname.hasClass('aOn')) {
                        $uname.removeClass('aOn');
                        $userInfo.stop().animate({top:'-'+userInfoH+'px'}, 200);
                    } else {
                        $uname.addClass('aOn');
                        $userInfo.stop().animate({top:'30px'}, 500);
                    }
                }
            };
            var unameHandle = null;
            $('.header .logon .uName, .header .logon .uDown').bind('mouseenter', function(){
                if (unameHandle) {clearTimeout(unameHandle);unameHandle = null;}
                op('show');
            }).bind('mouseleave', function(){
                if (unameHandle) {clearTimeout(unameHandle);unameHandle = null;}
                unameHandle = setTimeout(function(){op('hide');}, 50);
            });
        }
    },
    // 尾部客服
    enableFooterHover: function(){
        var $this = $('.csFloat');
        if ($this.length) {
            if (',2,3,4,5,6,8,'.indexOf(','+window.PlatInfo.id+',') > -1) {
                $this.find('li .c3').hover(
                    function(){
                        $(this).stop(true,true).animate({"width":"105px"})
                    },
                    function(){
                        $(this).stop(true,true).animate({"width":"0"});
                    }
                );
                $this.find('li .c1,li .c2').hover(
                    function(){
                        $(this).stop(true,true).animate({"width":"160px"})
                    },
                    function(){
                        $(this).stop(true,true).animate({"width":"0"});
                    }
                );
            } else {
                $this.find('li a').hover(
                    function(){
                        $(this).stop(true,true).animate({"width":"105px"})
                    },
                    function(){
                        $(this).stop(true,true).animate({"width":"0"});
                    }
                );
            }
        }
    },
    // 下拉滚动列表插件
    enableScrollBarPlugin: function() {
        var $this = $('.plugin_scrollBar');
        if ($this.length) {
            $this.each(function(){
                var config = $(this).attr('data-config') || '{}';
                config = eval('(' + config + ')');
                $(this).scrollBar(config);
            });
        }
    },
    // 快速充值
    enableQuickPay: function() {
        var $this = $('#quickPay');
        if ($this.length) {
            $this.bind('update', function(){
                Pay.Common.Lib.getUserRechargeList(function(result){
                    Pay.Common.instance.quickPayList = result;
                    var htmlArr = [];
                    if (result.status) {
                        var data = result.data;
                        var userList = result.userList;
                        for (var i=0,len=data.length; i<len; i++) {
                            htmlArr.push('<tr>');
                            htmlArr.push('    <td>' + userList[data[i].touserid] + '</td>');
                            if (data[i].serverid > 0) {
                                htmlArr.push('    <td>' + data[i].gamename + '-' + data[i].servername + '</td>');
                            } else {
                                htmlArr.push('    <td>' + data[i].platname + '-' + data[i].platcoin + '</td>');
                            }
                            htmlArr.push('    <td>' + data[i].showpayamount + '</td>');
                            htmlArr.push('    <td>' + data[i].paychannelname + '</td>');
                            htmlArr.push('    <td><a class="quickPayItem" data-key="' + i + '" href="javascript:void(0)">充值</a></td>');
                            htmlArr.push('</tr>');
                        }
                        $('#quickPay').show();
                        if (window.pay_center_config && ((!window.pay_center_config.selectedGameId) && !window.pay_center_config.selectedServerArea)) {
                            $('.showQuickPay').trigger('click');
                            Pay.Common.instance.handleArr['showQuickPay'] = setTimeout(function(){
                                $('.showQuickPay').trigger('click', 'hide');
                            }, 2000);
                        }
                    } else {
                        htmlArr.push('<tr><td colspan="5">暂无历史充值信息。</td></tr>');
                    }
                    $this.find('tbody').html(htmlArr.join(''));
                });
            }).delegate('.quickPayItem', 'click', function(){
                var quickPayList = Pay.Common.instance.quickPayList;
                var idx = parseInt($(this).attr('data-key')) || 0;
                if (quickPayList && quickPayList.data && quickPayList.data[idx]) {
                    // 确认订单
                    quickPayList.data[idx]['usercode'] = window.UserInfo && window.UserInfo.id && window.UserInfo.id == quickPayList.data[idx]['touserid'] ? window.UserInfo.showcode : quickPayList.userList[quickPayList.data[idx]['touserid']];
                    quickPayList.data[idx]['user_check'] = 1;
                    quickPayList.data[idx]['payamount'] = quickPayList.data[idx]['amount'];
                    quickPayList.data[idx]['paytarget'] = quickPayList.data[idx]['serverid'] > 0 ? 'GAME' : 'ROOF';

                    // 检测代金券是否可用
                    var path = window.location && window.location.pathname ? window.location.pathname + '' : '';
                    var matchs = path.match(/\/couponid\/([a-zA-Z0-9]+)\//);
                    if (matchs && matchs[1] && Pay.Center) {
                        quickPayList.data[idx]['couponid'] = matchs[1];
                        var couponid = matchs[1];
                        var daijin_code   = '';
                        var daijin_amount = '';
                        var only_first    = '';
                        // 检测渠道是否可用
                        var $payItem = $('.payItem[data-code="' + quickPayList.data[idx]['paychannel'] + '"]');
                        if (!$payItem.length) $payItem = $('.groupPayItem[data-code="' + quickPayList.data[idx]['paychannel'] + '"]');
                        var limitValue = parseInt($payItem.attr('data-voucher_limit')) || 0;
                        if ($payItem.length && $payItem.attr('data-enable_voucher') == '0' && quickPayList.data[idx]['paytarget'] == 'GAME' && quickPayList.data[idx]['payamount'] > 0 && limitValue > 0 && quickPayList.data[idx]['payamount'] >= limitValue) {
                            // 达到使用代金券条件(渠道条件)
                            // 检测代金券在该游戏区服是否可用
                            Pay.Center.Lib.checkDjCode({
                                userCode:quickPayList.userList[quickPayList.data[idx]['touserid']],
                                serverId:quickPayList.data[idx]['serverid'],
                                daijinCode:couponid,
                                amount:quickPayList.data[idx]['payamount']
                            }, function(result){
                                if (result.status) {
                                    var voucher_limit = voucher_limit ? parseInt(result.voucher_limit)||0 : 0;
                                    if (quickPayList.data[idx]['payamount'] >= voucher_limit) {
                                        // 代金券可用
                                        only_first    = result.only_first ? parseInt(result.only_first)||0 : 0;
                                        daijin_code   = couponid;
                                        daijin_amount = result.amount;
                                    }
                                }
                                quickPayList.data[idx]['only_first']    = only_first;
                                quickPayList.data[idx]['daijin_code']   = daijin_code;
                                quickPayList.data[idx]['daijin_amount'] = daijin_amount;

                                Pay.Common.Lib.submitPay.comfirmOrder(quickPayList.data[idx], 'quickPay');
                            });
                        } else {
                            Pay.Common.Lib.submitPay.comfirmOrder(quickPayList.data[idx], 'quickPay');
                        }
                    } else {
                        Pay.Common.Lib.submitPay.comfirmOrder(quickPayList.data[idx], 'quickPay');
                    }
                } else {
                    alert('系统繁忙！');
                }
                return false;
            }).delegate('.showQuickPay', 'click', function(evt, label){
                var $quickBox = $this.find('.quickBox');
                if (label == 'hide') {
                    $quickBox.hide();
                    $('.showQuickPay').removeClass('aOn');
                } else if (label == 'show') {
                    $quickBox.show();
                    $('.showQuickPay').addClass('aOn');
                } else {
                    if (Pay.Common.instance.handleArr['showQuickPay']) {
                        clearTimeout(Pay.Common.instance.handleArr['showQuickPay']);
                        Pay.Common.instance.handleArr['showQuickPay'] = null;
                    }
                    if ($quickBox.css('display') == 'none') {
                        if (!parseInt($this.attr('data-hasdata'))) {
                            $this.attr('data-hasdata', 1);
                            //$this.trigger('update');
                        }
                        $quickBox.show();
                        $('.showQuickPay').addClass('aOn');
                    } else {
                        $quickBox.hide();
                        $('.showQuickPay').removeClass('aOn');
                    }
                }
                return false;
            }).delegate('.quickBox', 'mouseenter', function(){
                if (Pay.Common.instance.handleArr['showQuickPay']) {
                    clearTimeout(Pay.Common.instance.handleArr['showQuickPay']);
                    Pay.Common.instance.handleArr['showQuickPay'] = null;
                }
            }).delegate('.qClose', 'click', function(){
                $('.quickBox').hide();
                $('.showQuickPay').removeClass('aOn');
                return false;
            }).delegate('tbody tr', 'mouseenter', function(){
                $(this).addClass('tr2');
            }).delegate('tbody tr', 'mouseleave', function(){
                $(this).removeClass('tr2');
            });
            setTimeout(function(){$this.trigger('update');}, 0);
        }
    },
    enableDocumentClick: function(){
        $(document).bind('click', function(evt){
            var $target = $(evt.target);
            if (!$target.closest('.quickBox').length) {
                $('.quickBox').hide();
                $('.showQuickPay').removeClass('aOn');
            }
        });
    },
    enableBtnHover: function(){
        $('body').delegate('.scrollBarDrag,.selectScrollDrag', 'mouseenter', function(){
            $(this).addClass('barOn');
        }).delegate('.scrollBarDrag,.selectScrollDrag', 'mouseleave', function(){
            $(this).removeClass('barOn');
        }).delegate('button', 'mouseenter', function(){
            $(this).addClass('bOn');
        }).delegate('button', 'mouseleave', function(){
            $(this).removeClass('bOn');
        });
    },
    enableBtnFocus: function(){
        $('body').delegate('.i2', 'focus', function(){
            $(this).removeClass('iOn').addClass('iOn');
        }).delegate('.i2', 'blur', function(){
            var value = $(this).val();
            if (value.length) {
                $(this).removeClass('iOn').addClass('ii');
            } else {
                $(this).removeClass('iOn ii');
            }
        });
    },

    // 登陆处理
    login_click: function() {
        var htmlArr = [];
        htmlArr.push('<div class="Tlogin">');
        htmlArr.push('    <form class="ajax_login_form" method="POST">');
        htmlArr.push('    <p class="pWarn"></p>');
        htmlArr.push('    <p id="p1">');
        htmlArr.push('        <label>通行证：</label>');
        htmlArr.push('        <span>账号</span>');
        htmlArr.push('        <input class="i ii code" name="code" type="text" value="" tabindex="1" />');
        htmlArr.push('        <a class="del" href="javascript:void(0)" style="display:none;">清除</a>');
        htmlArr.push('        <a class="aRg" href="' + (window.PlatInfo.register||'') + '" target="_blank">账号注册</a>');
        htmlArr.push('    </p>');
        htmlArr.push('    <p id="p2">');
        htmlArr.push('        <label>密&nbsp;&nbsp;码：</label>');
        htmlArr.push('        <span>密码</span>');
        htmlArr.push('        <input class="i" name="password" type="password" tabindex="2" />');
        htmlArr.push('        <a class="aFd" href="' + (window.PlatInfo.getpwd||'') + '" target="_blank">找回密码</a>');
        htmlArr.push('    </p>');
        htmlArr.push('    <p class="btn">');
        htmlArr.push('        <button type="submit" tabindex="3" >立即登录</button>');
        htmlArr.push('    </p>');
        htmlArr.push('    </form>');
        htmlArr.push('</div>');

        Pay.Common.showPop(htmlArr.join(''), '欢迎登录');
        setTimeout(function(){$('input[name="code"],input[name="password"]').trigger('blur', 'nostate');Pay.Ulib.cursorMoveEnd($('.ajax_login_form .code').get(0));}, 20);
    },
    // 登陆处理
    login2_click: function(evt, label) {
        var $hLoginWrap = $('.hLoginWrap');
        if ($hLoginWrap.length) {
            if (!$hLoginWrap.find('.Tlogin').length) {
                var keepcode = Pay.Ulib.getCookie('KEEPCODE') || '';
                var htmlArr = [];
                htmlArr.push('<div class="Tlogin">');
                htmlArr.push('    <form class="ajax_login2_form" method="POST">');
                htmlArr.push('    <p class="pWarn" style="display:block;"></p>');
                htmlArr.push('    <p id="p1">');
                htmlArr.push('        <label>通行证：</label>');
                htmlArr.push('        <input class="i ii" name="code" type="text" value="' + keepcode + '" tabindex="1" data-placeholder="账号" />');
                htmlArr.push('        <a class="del" href="javascript:void(0)" style="' + (keepcode ? 'display:block' : 'display:none') + ';">清除</a>');
                htmlArr.push('    </p>');
                htmlArr.push('    <p id="p2">');
                htmlArr.push('        <label>密&nbsp;&nbsp;码：</label>');
                htmlArr.push('        <input class="i" name="password" type="password" tabindex="2" value="" data-placeholder="密码" />');
                htmlArr.push('    </p>');
                if (window.PlatInfo.seccode) {
                    htmlArr.push('    <p id="validate">');
                    htmlArr.push('        <label>验证码：</label>');
                    htmlArr.push('        <input class="i ii" name="vcode" type="text" value="" tabindex="3" data-placeholder="验证码" />');
                    htmlArr.push('        <img class="vcodeItem" width="64" height="32" src="/verifyCode.php?r=' + Math.random() + '" title="点击刷新验证码" alt="点击刷新验证码" />');
                    htmlArr.push('    </p>');
                }
                htmlArr.push('    <p id="pCheck">');
                htmlArr.push('        <input type="checkbox" checked="checked" value="1" name="keeplive" style="display: none;">');
                htmlArr.push('        <label class="checkbox checked checkboxItem" data-name="keeplive" data-checkedCls="checked">记住账号</label>');
                htmlArr.push('        <a href="' + (window.PlatInfo.getpwd||'') + '" target="_blank">找回密码</a>');
                htmlArr.push('    </p>');
                htmlArr.push('    <p class="btn">');
                htmlArr.push('        <button type="submit" tabindex="4" >立即登录</button>');
                htmlArr.push('    </p>');
                if (window.PlatInfo.code == 'game2') {
                    var calurl = window.location.protocol == 'https:' ? 'https://passport.game2.cn' : 'http://www.game2.cn';
                    htmlArr.push('    <div class="otherLogin" data-otherlogin-config=\'{"remark":"::'+encodeURIComponent(calurl)+'"}\'></div>');
                }
                htmlArr.push('    </form>');
                htmlArr.push('</div>');
                $hLoginWrap.html(htmlArr.join(''));
                $hLoginWrap.find('input[type=text],input[type=password]').trigger('init');
                setTimeout(function(){$hLoginWrap.find('input[name="code"],input[name="password"]').trigger('blur', 'nostate');}, 20);
                if (window.PlatInfo.code == 'game2') {
                    setTimeout(function(){window.J && window.J.OtherLogin && window.J.OtherLogin.initOtherLogin();}, 0);
                }
            }
            var $ajaxLogin = $('.header .login .ajax_login2');
            if (label == 'show') {
                if ($ajaxLogin.hasClass('aOn')) {
                    $hLoginWrap.stop().css({display:'block', top:'30px'});
                } else {
                    $ajaxLogin.addClass("aOn");
                    $hLoginWrap.stop(true).css({display:'block', top:'-270px'}).animate({"top":"30px"});
                }
            } else if (label == 'hide'){
                $ajaxLogin.removeClass("aOn");
                $hLoginWrap.stop().animate({"top":"-270px"}, function(){$(this).hide();});
            } else {
                if ($ajaxLogin.hasClass('aOn')) {
                    $ajaxLogin.removeClass("aOn");
                    $hLoginWrap.stop().animate({"top":"-270px"}, function(){$(this).hide();});
                } else {
                    $ajaxLogin.addClass("aOn");
                    $hLoginWrap.stop().css({display:'block', top:'-270px'}).animate({"top":"30px"});
                }
            }
        }
        return false;
    },

    // 退出处理
    signout_click: function() {
        Pay.Common.Lib.signout(function(result){
            window.location = '/pay/';
        });
    },
    // 刷新验证码
    vcodeItem_click: function(evt) {
        var $that = $(this);
        var src = $that.attr('src').split('?')[0];
        src += '?r=' + Math.random();
        $that.attr('src', src);
        return false;
    }
});
//}}}


// 初始化
$(document).ready(function(){
    var pay_comon_config = window.pay_comon_config || {};
    new Pay.Common(pay_comon_config);
});

