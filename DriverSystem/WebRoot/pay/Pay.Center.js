/**
 * 支付中心页面js
 *
 * js依赖
 * jquery 1.4.2+
 */
Pay.Center = Pay.getClass();

// Pay.Center.Lib{{{
Pay.Center.Lib = Pay.Center.Lib || {};
Pay.$.extend(Pay.Center.Lib, {
    // paytarget,paychannel,serverId
    getChargeSet: function(data, callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getChargeSet'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    isActivateGame: function(data, callback){
        var path = window.location && window.location.pathname ? window.location.pathname + '' : '';
        if (path.match(/\/force\//)) data.force = 1;
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/isActivateGame'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    existUserCode: function(data, callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/existUserCode'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    getUserDjList: function(callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/getUserDjList'),
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    checkDjCode: function(data, callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/checkDjCode'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    getOrderData: function(){
        var returnArr = {};
        var fields = [['serverid', '#server_id'], ['usercode', '.usercode'], ['user_check', '#user_check'], ['gcodeid', '#gcodeid'], ['paychannel', '#lastChannel'], ['paytarget', '#lastTarget'], ['payamount', '#amount'], ['banktype', '#banktype'], ['userphone', '#user_phone'], ['daijin_code', '#daijin_code']];
        for (var i=0,len=fields.length; i<len; i++) {
            var $field = $(fields[i][1]);
            if ($field.length) {
                returnArr[fields[i][0]] = $field.val();
            } else {
                returnArr[fields[i][0]] = '';
            }
        }

        // 平台名称
        if (window.PlatInfo) {
            returnArr.platname = window.PlatInfo.name;
            returnArr.platcoin = window.PlatInfo.currencyname;
        }

        // 渠道名称
        if (returnArr.paychannel) {
            returnArr['paychannelname'] = $('#lastChannel').attr('data-name') || '';

            // 充值卡
            if (returnArr.paychannel == 'QUYOU-NET') {
                // qypay
                var $cardItem = $('.qypayDiv .cardDiv');
                if ($cardItem.length) {
                    var payCardArr = [];
                    $cardItem.each(function(){
                        payCardArr.push($(this).find('.card_no').val() + '|' + $(this).find('.card_pwd').val());
                    });
                    returnArr['paycard'] = payCardArr.join(',');
                }
            } else {
                var $singlePayItem = $('.singlePayItem[data-code="' + returnArr.paychannel + '"]:first');
                var bind_payment = $singlePayItem.length ? parseInt($singlePayItem.attr('data-bind_payment')) : 0;
                if ((Pay.Center.instance.config.switchConfig == '1' && bind_payment == '6') || bind_payment == '13') {
                    returnArr['bind_payment'] = bind_payment;
                    var $cardItem = $('.pay19Div .cardDiv');
                    if ($cardItem.length) {
                        var payCardArr = [];
                        $cardItem.each(function(){
                            payCardArr.push($(this).find('.card_no').val() + '|' + $(this).find('.card_pwd').val());
                        });
                        returnArr['paycard'] = payCardArr.join(',');
                    }
                }
            }
        }
        // 游戏区服名称
        if (returnArr.serverid) {
            returnArr['gamename']   = $('.selGameBtn').text().replace(/^\s*|\s*$/g, '');
            returnArr['servername'] = $('.selServerBtn').text().replace(/^\s*|\s*$/g, '');
        }
        // 银行类型
        if (returnArr.banktype) {
            returnArr['bankname'] = $('.bankItem[data-code="' + returnArr.banktype + '"]').attr('data-name');
        }
        // 充值套餐
        if (returnArr.payamount) {
            var $amountItem = $('.amountSelect ul li a[data-value="' + returnArr.payamount + '"]');
            if ($amountItem.length) {
                var gcoin = $amountItem.attr('data-gcoin');
                returnArr.showpayamount = $amountItem.text();
                returnArr.showpaypoint = $amountItem.attr('data-point') + gcoin;
            }
        }
        // 检测当前是否允许代金券
        var $payItem = $('.payItem[data-code="' + returnArr.paychannel + '"]');
        if (!$payItem.length) $payItem = $('.groupPayItem[data-code="' + returnArr.paychannel + '"]');
        var limitValue = parseInt($payItem.attr('data-voucher_limit')) || 0;
        if ($payItem.length && $payItem.attr('data-enable_voucher') == '0' && returnArr.paytarget == 'GAME' && returnArr.payamount > 0 && limitValue > 0 && returnArr.payamount >= limitValue) {
            var $daijin_amount = $('#daijin_amount');
            var voucher_limit = parseInt($daijin_amount.attr('data-voucher_limit')) || 0;
            var only_first    = parseInt($daijin_amount.attr('data-only_first')) || 0;
            // 使用代金券
            if ($('.showDaijinCont').length && $('.showDaijinCont').hasClass('aOn') && returnArr.payamount >= voucher_limit) {
                if (returnArr.daijin_code) {
                    returnArr.daijin_amount = parseFloat($('#daijin_amount').val()) || 0;
                    returnArr.only_first    = only_first;
                    returnArr.voucher_limit = voucher_limit;
                }
            }
        }

        // 记录登陆态
        returnArr.isLogin = window.UserInfo && window.UserInfo.id;
        returnArr.user_balance = window.UserInfo && window.UserInfo.balance;

        return returnArr;
    },
    checkPaypalRecord: function(data, callback){
        callback = callback || function(){};
        $.ajax({
            type: "GET",
            url: Pay.Ulib.getUrl('ajax', 'op/checkPaypalRecord'),
            data: data,
            dataType: 'json',
            success: function(result) {
                callback(result);
                return false;
            }
        });
        return false;
    },
    replacePlatData: function(str){
        if (window.PlatInfo) {
            return str.replace(/\r/g, '')
                      .replace(/\n/g, '<br />')
                      .replace(/\[PLATNAME\]/g, window.PlatInfo.name)
                      .replace(/\[PLATDOMAIN\]/g, window.PlatInfo.domain)
                      .replace(/@\./g, '@')
                      .replace(/\[PLATCODE\]/g, window.PlatInfo.code)
                      .replace(/\[PLATCURRENCYNAME\]/g, window.PlatInfo.currencyname);
        }
        return str;
    },
    allowPayRoof: function(){
        var returnVal = true;
        var usercode = $('.usercode').val();
        if (window.PlatInfo && window.PlatInfo.code == 'juu') {
            if (window.UserInfo && window.UserInfo.code && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                //juu币
                returnVal = true;
            } else {
                returnVal = false;
            }
        }

        return returnVal;
    }
});
//}}}


// Pay.Center {{{
Pay.Center.prototype = $.extend(Pay.Center.prototype, {
    moduleName: 'Pay.Center',  // 对应实例  Pay.Center.instance
    gid2Slist: {},  // 缓存游戏id对应区服数据
    playUgid2Slist: {}, // 缓存用户玩过的游戏id对应区服数据
    uactgid2List: {}, // 缓存用户激活游戏数据
    chargeSet: {},  // 充值套餐数据
    handleArr: {},// 固定时使用
    events: [
        ['.usercode_p', 'delegate|click|.aFor', 'usercodeFor_click'],
        ['.usercode_p', 'delegate|change|.usercode', 'payMod_checkPaypalRecord'],
        ['.usercode_p', 'delegate|keyup|.usercode', 'usercode_keyup'],
        ['.usercode_p', 'delegate|blur|.usercode', 'usercode_blur'],
        ['.usercode_p', 'delegate|focus|.usercode', 'usercode_focus'],
        ['.usercode_p', 'delegate|checkval|.usercode', 'usercode_checkval'],
        ['.usercode_p', 'delegate|checkval2|.usercode', 'usercode_checkval2'],
        ['.usercodeList', 'update', 'usercodeList_update'],
        ['.usercodeList', 'delegate|click|.usercodeItem', 'usercodeItem_click'],
        ['.lastTarget_p', 'delegate|click|.lastTargetItem', 'lastTargetItem_click'],

        ['.selGameBtn', 'click', 'selGameBtn_click'],
        ['.selGameBtn', 'update', 'selGameBtn_update'],

        ['.choiceDiv', 'updateScrollBar', 'choiceDiv_scrollBar'],
        ['.choiceCon_game .gTab', 'delegate|click|.gTabItem', 'choiceGameTab_click'],
        ['.choiceDiv', 'delegate|click|.gameItem', 'gameItem_click'],

        ['.selServerBtn', 'click', 'selServerBtn_click'],
        ['.allSer', 'render', 'serverList_render'],
        ['.allSer', 'render2', 'serverList_render2'],
        ['.choiceCon_server', 'delegate|click|.sTabItem', 'choiceSerTab_click'],
        ['.choiceCon_server', 'delegate|click|.serverItem', 'serverItem_click'],
        ['.choiceCon_server', 'delegate|click|.sTabItem2', 'choiceSerTab2_click'],

        ['.payMod', 'delegate|click|.payItem', 'payItem_click'],
        ['.payMod', 'checkPaypalRecord', 'payMod_checkPaypalRecord'],
        ['body', 'delegate|click|.groupPayItem', 'groupPayItem_click'],
        ['.bankList', 'delegate|click|.bankItem', 'bankItem_click'],
        ['.bankList', 'delegate|click|.moreBankItem', 'morebankItem_click'],
        ['.qypayDiv', 'delegate|click|.addItem', 'qypayDivAddItem_click'],
        ['.qypayDiv', 'delegate|click|.delItem', 'qypayDivDelItem_click'],
        ['.qypayDiv', 'delegate|blur|.card_no', 'cardno_blur'],
        ['.qypayDiv', 'delegate|blur|.card_pwd', 'cardpwd_blur'],

        ['.pay19Div', 'delegate|blur|.card_no', 'cardno2_blur'],
        ['.pay19Div', 'delegate|blur|.card_pwd', 'cardpwd2_blur'],

        ['.amountSelect', 'update', 'amountSelect_update'],
        ['.amountSelect', 'update2', 'amountSelect_update2'],
        ['.amountSelect .selectMain', 'click', 'amountSelect_click'],
        ['.amountSelect', 'change', 'amountSelect_change'],

        ['.showDaijinCont', 'click', 'showDaijinCont_click'],
        ['.daijinList', 'delegate|click|.daijinItem', 'daijinItem_click'],
        ['.daijinCont', 'delegate|click|.enterDaijinBtn', 'enterDaijinBtn_click'],
        ['.daijinCont', 'delegate|click|.modDaijinBtn', 'modDaijinBtn_click'],
        ['.daijin', 'update', 'daijin_update'],

        ['#user_phone', 'keyup', 'userphone_keyup'],
        ['#user_phone', 'focus', 'userphone_focus'],
        ['#user_phone', 'blur', 'userphone_blur'],

        ['.submiBtn', 'click', 'submiBtn_click']
    ],
    // 初始化函数
    init: function() {
        this.enableAllGameListInit();
        this.enableHasGameListInit();

        // 默认配置初始化
        this.enableConfgInit();

        this.enableDocumentClick();
        this.enableSearchGameItem();
        this.enableSearchServerItem();
        // 更多支付渠道处理
        this.enableMoreModHover();
        this.enableCheckUser();
        // 代金券列表
        this.enableDaijinList();
        // 为他人充值时，用户列表初始化
        this.enableUserListInit();
    },
    // 全部可充值游戏列表 初始化
    enableAllGameListInit: function(){
        var $allList = $('#allList');
        if ($allList.length) {
            var $allListCon = $allList.find('.listCon');
            var $allListScrollBar = $allList.find('.scrollBarWrap');
            var config = this.config;

            $('#allGameList').html('');
            $allListCon.addClass('loading');
            $allListScrollBar.hide();
            Pay.Common.Lib.getGameList(function(result){
                // 加载图标，要（全部游戏及玩过游戏，加载完才去除）
                config.loadedGameList = true;
                if (config.loadedUserPlayGameList || !Pay.Common.Lib.isLogin()) {
                    $allListCon.removeClass('loading');
                }
                if (result.status) {
                    var pcode = window.PlatInfo && window.PlatInfo.code;
                    var data = result.data, htmlArr=[];
                    var usercode = $('.usercode').val();
                    // 全部游戏显示 1.未登陆 2:登陆-非当前用户
                    var isShow = Pay.Common.Lib.isLogin() ? (window.UserInfo.code != usercode && window.UserInfo.showcode != usercode ? true : false) : true;
                    var hasTs = false;

                    for (var i=0,len=data.length; i<len; i++) {
                        if (data[i].code == 'tsqy' || data[i].code == 'tshg') continue; // 天书地域/回归不显示
                        htmlArr.push('<li class="' + (isShow ? 'gameItemAllow gameItemAllowShow' : '') + '" data-hasplay="0" data-type="' + data[i].code.substr(0, 1) + '" data-code="' + data[i].code + '" data-keywords="' + data[i].name + ',' + data[i].code + ',' + data[i].pinyin + '">');
                        htmlArr.push('    <a href="javascript:void(0)" class="gameItem" data-gameid="' + data[i].id + '" data-code="' + data[i].code + '">');
                        htmlArr.push('        <img width="74" height="74" src="/images/commom/transparent.gif" data-original="/images/' + pcode + '/game/pay_' + data[i].code + '.jpg">');
                        htmlArr.push('        <span>' + data[i].name + '</span>');
                        htmlArr.push('    </a>');
                        htmlArr.push('</li>');
                    }
                    var $gameItemList = $(htmlArr.join('')).appendTo($('#allGameList'));
                    $gameItemList.find('img').lazyLoadPlugin({container:'#allList'});

                    // 未登陆显示全部游戏，需更新滚动条
                    if (isShow) $('.choiceDiv').trigger('updateScrollBar');

                    // 默认值处理
                    if (config.selectedGameId && config.selectedGameId > 0) {
                        var selectedGameId = Pay.Common.Lib.getTsCodeByGid(config.selectedGameId) ? Pay.Common.Lib.getTsGidByCode('ts') : config.selectedGameId;
                        var $selectedGameItem = $('#allGameList .gameItem[data-gameid="' + selectedGameId + '"]:first');
                        if ($selectedGameItem.length) {
                            $selectedGameItem.trigger('click', ['nostate', config.selectedGameId]);
                            config.selectedGameId = -1;
                        }
                    }
                } else {
                    // 没有游戏列表
                    var $tabCustom = $('.allGame .gTabCustom');
                    if ((!$allListCon.hasClass('loading')) && $('#allGameList li').length < 1) {
                        $allListCon.find('.noInfo').html('暂无游戏信息。').show();
                    }
                }
            });
        }
    },
    // 玩过可充值游戏列表 初始化
    enableHasGameListInit: function(){
        if (Pay.Common.Lib.isLogin()) {
            var $allList = $('#allList');
            if ($allList.length) {
                var $allListCon = $allList.find('.listCon');
                var $allListScrollBar = $allList.find('.scrollBarWrap');
                var config = this.config;

                $('#allGameList').html('');
                $allListCon.addClass('loading');
                $allListScrollBar.hide();
                Pay.Common.Lib.getUserPlayGameList(function(result){
                    // 加载图标，要（全部游戏及玩过游戏，加载完才去除）
                    config.loadedUserPlayGameList = true;
                    if (config.loadedGameList) {
                        $allListCon.removeClass('loading');
                    }
                    if (result.status) {
                        var pcode = window.PlatInfo && window.PlatInfo.code;
                        var data = result.data, htmlArr=[], playUgid2Slist = {};;
                        var usercode = $('.usercode').val();
                        // 玩过游戏显示 1.登陆-当前用户
                        var isShow = Pay.Common.Lib.isLogin() && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode) ? true : false;

                        var tsSlist = [Pay.Common.Lib.getTsGidByCode('ts'), Pay.Common.Lib.getTsGidByCode('tsqy'), Pay.Common.Lib.getTsGidByCode('tshg')];
                        for (var i=0,len=data.length; i<len; i++) {
                            if (data[i].code != 'tsqy' && data[i].code != 'tshg') {  // 天书区域/回归不显示
                                htmlArr.push('<li class="' + (isShow ? 'gameItemAllow gameItemAllowShow' : '') + '" data-hasplay="1" data-type="' + data[i].code.substr(0, 1) + '" data-code="' + data[i].code + '" data-keywords="' + data[i].name + ',' + data[i].code + ',' + data[i].pinyin + '">');
                                htmlArr.push('    <a href="javascript:void(0)" class="gameItem" data-gameid="' + data[i].id + '" data-code="' + data[i].code + '">');
                                htmlArr.push('        <img width="74" height="74" src="/images/commom/transparent.gif" data-original="/images/' + pcode + '/game/pay_' + data[i].code + '.jpg">');
                                htmlArr.push('        <span>' + data[i].name + '</span>');
                                htmlArr.push('    </a>');
                                htmlArr.push('</li>');
                            }

                            // 将天书及地域/回归合在一起
                            if (Pay.Common.Lib.getTsCodeByGid(data[i].id)) {
                                for (var j=0,jlen=tsSlist.length;j<jlen;j++) {
                                    if (typeof(playUgid2Slist[tsSlist[j]]) == 'undefined') {
                                        playUgid2Slist[tsSlist[j]] = data[i].serverList;
                                    } else {
                                        playUgid2Slist[tsSlist[j]] = playUgid2Slist[tsSlist[j]].concat(data[i].serverList);
                                    }
                                }
                            } else {
                                playUgid2Slist[data[i].id] = data[i].serverList;
                            }
                        }
                        Pay.Center.instance.playUgid2Slist = playUgid2Slist;
                        var $gameItemList = $(htmlArr.join('')).appendTo($('#allGameList'));
                        $gameItemList.find('img').lazyLoadPlugin({container:'#allList'});

                        if (isShow) $('.choiceDiv').trigger('updateScrollBar');
                    } else {
                        // 没有游戏列表
                        var $tabCustom = $('.allGame .gTabCustom');
                        if ($tabCustom.length && $tabCustom.hasClass('aOn')) {
                            $allListCon.find('.noInfo').html('您最近没有玩过游戏，查看<a class="doGTabItem" href="javascript:void(0)">更多游戏</a>').show();
                        }
                    }
                    $('body').trigger('loadedUserPlayGameList');
                });
            }
        }
    },
    // 点击别的地方自动关闭
    enableDocumentClick: function(){
        $(document).bind('click', function(evt){
            var $target = $(evt.target);
            if ((!$target.closest('.choiceDiv').length) && (!$target.closest('.usercode').length)) {
                var $selGameBtn = $('.selGameBtn');
                if ($selGameBtn.length) {
                    // 按钮状态处理
                    var gameId = parseInt($selGameBtn.attr('data-gameid')) || 0;
                    if (gameId) {
                        $selGameBtn.removeClass('aOver').addClass('aOn');
                    } else {
                        $selGameBtn.removeClass('aOver aOn');
                    }
                }
                var $selServerBtn = $('.selServerBtn');
                if ($selServerBtn.length) {
                    var server_id = $('#server_id').val();
                    if (server_id) {
                        $selServerBtn.removeClass('aOver').addClass('aOn');
                    } else {
                        $selServerBtn.removeClass('aOver aOn');
                    }
                }
                $('.choiceDiv').hide();
            }
        });
    },
    // 搜索游戏处理
    enableSearchGameItem: function(){
        var $this = $('.searchGameItem');
        var handleBlur = null;
        var handleNoBlur = false;
        $this.bind('blur', function(){
            if (!handleNoBlur) {
                handleBlur = setTimeout(function(){
                    var $that = $('.searchGameItem');
                    if ($that.val() == '') {
                        $('.clearSearchGame').removeClass('aClose');
                    } else {
                        $('.clearSearchGame').addClass('aClose');
                    }
                }, 100);
            }
        }).bind('focus keyup', function(evt){
            var value = $(this).val();
            if (value.length) {
                $('.clearSearchGame').addClass('aClose');
            } else {
                $('.clearSearchGame').removeClass('aClose');
            }

            var $allGame = $('.allGame');
            if (value == '') {
                //$('.gTabCustom').attr('data-range', value);
                // 显示全部
                $allGame.find('.gTab .gTabItem').removeClass('aOn');
                $allGame.find('.gTabCustom').addClass('aOn').trigger('click');
            } else {
                //$('.gTabCustom').attr('data-range', value);
                // 切换到全部
                var $allGameList = $('#allGameList');
                var $switchGameList = $allGameList.find('li[data-hasplay="1"]');
                if ($switchGameList.length) $switchGameList.removeClass('gameItemAllow gameItemAllowShow');

                // 筛选显示
                var hasSearchData = false;
                key = ',' + value.toUpperCase();
                $allGameList.find('li[data-hasplay="0"]').each(function(){
                    var $that = $(this);
                    var range = ',' + $that.attr('data-keywords').toUpperCase() + ',';
                    if (range.indexOf(key) > -1) {
                        hasSearchData = true;
                        $that.addClass('gameItemAllow gameItemAllowShow');
                    } else {
                        $that.addClass('gameItemAllow').removeClass('gameItemAllowShow');
                    }
                });
                //$allGame.find('.gTab .gTabItem').removeClass('aOn').siblings('.gTabCustom').addClass('aOn').html('搜索结果');
                $allGame.find('.gTab .gTabItem').removeClass('aOn');
                $allGame.find('.gTabCustom').addClass('aOn').html('搜索结果');

                // 未找到游戏，显示提示处理
                if (!hasSearchData) {
                    if (window.PlatInfo && window.PlatInfo.gamecate) {
                        $allGame.find('.noInfo').html('没有找到 ”<strong>' + Pay.Ulib.encodeHtml(value) + '</strong>“ 相关的游戏<br>建议您 更换关键词 或 根据<a href="javascript:void(0)" class="doGTabItem">首字母筛选</a>').show();
                    } else {
                        $allGame.find('.noInfo').html('没有找到 ”<strong>' + Pay.Ulib.encodeHtml(value) + '</strong>“ 相关的游戏<br>建议您 更换关键词 或 查看<a href="javascript:void(0)" class="doGTabItem">全部游戏</a>').show();
                    }
                    $allGameList.hide();
                    $allGame.find('.scrollBarWrap').hide();
                } else {
                    $allGame.find('.noInfo').hide();
                    $allGameList.show();
                    $allGame.find('.scrollBarWrap').show();
                    $('.choiceDiv').trigger('updateScrollBar');
                }
            }

            //$('.choiceDiv').trigger('updateScrollBar');
        }).bind('keydown', function(evt){
            if (evt.keyCode == 13) {
                var $gameItemAllowShow = $('#allGameList .gameItemAllowShow');
                if ($gameItemAllowShow.length == 1) {
                    $gameItemAllowShow.find('.gameItem').trigger('click');
                    return false;
                }
            }
        });
        $('.choiceDiv').delegate('.clearSearchGame', 'click', function(){
            $('.searchGameItem').val('');
            setTimeout(function(){$('.searchGameItem').focus();}, 0);
            return false;
        }).delegate('.clearSearchGame', 'mousedown', function(){
            handleNoBlur = true;
            if (handleBlur) {clearTimeout(handleBlur); handleBlur = null;}
            setTimeout(function(){handleBlur = false;handleNoBlur=false;}, 100);
        }).delegate('.doGTabItem', 'click', function(){
            $('.searchGameItem').val('').trigger('keyup');
            setTimeout(function(){
                var $gTabItem = $('.gTabItem:eq(1)');
                if ($gTabItem.length) {
                    if ($gTabItem.css('display') == 'none') {
                        // 登陆用户给别人充值模式
                        $('.gTabCustom').trigger('click');
                    } else {
                        $gTabItem.trigger('click');}
                    }
            }, 0);
            return false;
        });
        return false;
    },
    // 搜索区服处理
    enableSearchServerItem: function(){
        var handleBlur = null;
        var handleNoBlur = false;
        $('.choiceDiv').delegate('.searchServerItem', 'blur', function(){
            var $that = $(this);
            if (!handleNoBlur) {
                handleBlur = setTimeout(function(){
                    if ($that.val() == '') {
                        $('.clearSearchServer').removeClass('aClose');
                    } else {
                        $('.clearSearchServer').addClass('aClose');
                    }
                }, 100);
            }
        }).delegate('.searchServerItem', 'focus keyup', function(){
            var $that = $(this);
            var value = $that.val().replace(/\D/, '');
            $that.val(value);
            if (value.length) {
                $('.clearSearchServer').addClass('aClose');
            } else {
                $('.clearSearchServer').removeClass('aClose');
            }

            var $allServer = $('.choiceCon_server');
            if (value == '') {
                // 显示全部
                $allServer.find('.gTab .sTabItem').removeClass('aOn').siblings('.sTabCustom').addClass('aOn').trigger('click');
            } else {
                // 切换到全部
                var $allServerList = $('#allServerList');
                var $switchServerList = $allServerList.find('li[data-hasplay="1"]');
                if ($switchServerList.length) $switchServerList.removeClass('serverItemAllow serverItemAllowShow');

                var $allSer = $('#allSer');
                // 筛选显示
                var hasSearchData = false;
                key = ',' + value.toUpperCase();
                $allServerList.find('li[data-hasplay="0"]').each(function(){
                    var $that = $(this);
                    var range = ',' + $that.attr('data-keywords').toUpperCase() + ',';
                    if (range.indexOf(key) > -1) {
                        hasSearchData = true;
                        $that.addClass('serverItemAllow serverItemAllowShow');
                    } else {
                        $that.addClass('serverItemAllow').removeClass('serverItemAllowShow');
                    }
                });
                $allServer.find('.gTab .sTabItem').removeClass('aOn').siblings('.sTabCustom').addClass('aOn').html('搜索结果');

                // 未找到游戏，显示提示处理
                if (!hasSearchData) {
                    $allSer.find('.noInfo').html('没有找到 ”<strong>' + Pay.Ulib.encodeHtml(value) + '</strong>“ 相关的区服<br />建议您 更换关键词 或 按<a href="javascript:void(0)" class="doSTabItem">区间</a>筛选。').show();
                    $allServerList.hide();
                    $allSer.find('.scrollBarWrap').hide();
                } else {
                    $allSer.find('.noInfo').hide();
                    $allServerList.show();
                    $allSer.find('.scrollBarWrap').show();
                    $('.choiceDiv').trigger('updateScrollBar');
                }

            }

            //$('.choiceDiv').trigger('updateScrollBar');
        }).delegate('.searchServerItem', 'keydown', function(evt){
            if (evt.keyCode == 13) {
                var $serverItemAllowShow = $('#allServerList .serverItemAllowShow');
                if ($serverItemAllowShow.length == 1) {
                    $serverItemAllowShow.find('.serverItem').trigger('click');
                    return false;
                }
            }
        });
        $('.choiceDiv').delegate('.clearSearchServer', 'click', function(){
            $('.searchServerItem').val('');
            setTimeout(function(){$('.searchServerItem').focus();}, 0);
            return false;
        }).delegate('.clearSearchServer', 'mousedown', function(){
            handleNoBlur = true;
            if (handleBlur) {clearTimeout(handleBlur); handleBlur = null;}
            setTimeout(function(){handleBlur = false;handleNoBlur=false;}, 100);
        }).delegate('.doSTabItem', 'click', function(){
            $('.searchServerItem').val('').trigger('keyup');
            setTimeout(function(){
                var $sTabItem = $('.sTabItem:eq(1)');
                if ($sTabItem.length) {
                    if ($sTabItem.css('display') == 'none') {
                        // 登陆用户给别人充值模式
                        $('.sTabCustom').trigger('click');
                    } else {
                        $sTabItem.trigger('click');}
                    }
            }, 0);
            return false;
        });
        return false;
    },
    // 更多支付渠道处理
    enableMoreModHover: function(){
        var $this = $('.moreMod');
        if ($this.length) {
            var handle = null;
            $this.bind('mouseover', function(){
                if (handle) {clearTimeout(handle);handle=null;}
                $this.addClass('moreModHover');
            }).bind('mouseout', function(){
                handle = setTimeout(function(){
                    $this.removeClass('moreModHover');
                }, 100);
            });
        }
    },
    enableCheckUser: function(){
        var checkUser = function(evt, evtlabel){
            var evtlabeltype   = typeof(evtlabel);
            var usercode       = $('.usercode').val();
            var lastTarget     = $('#lastTarget').val();

            usercode = window.UserInfo && window.UserInfo.code && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode) ? window.UserInfo.code : usercode;
            if (usercode) {
                if (lastTarget == 'GAME') {
                    var server_id      = $('#server_id').val();
                    if (server_id) {
                        var label = '_isActivateGame_' + usercode + '|_|' + server_id;
                        var $errmsg = $('.choiceT .errmsg').removeClass('suc warn').html('');
                        if (Pay.Center.instance.uactgid2List[label]) {
                            if (Pay.Center.instance.uactgid2List[label].status) {
                                $('#user_check').val(1);
                                $('#gcodeid').val(Pay.Center.instance.uactgid2List[label].roleId);
                            } else {
                                $('#user_check').val('');
                                $('#gcodeid').val('');
                                //$errmsg.addClass('warn').html('您在该区还没有 <a href="" target="_blank">创建角色</a>');
                                if (evtlabeltype == 'string' && evtlabel == 'keyup') {
                                    // keyup时，不显示
                                } else {
                                    $errmsg.addClass('warn').html('您在该区还没有创建角色');
                                }
                            }
                        } else {
                            Pay.Center.Lib.isActivateGame({userCode: usercode, serverId: server_id}, function(result){
                                if (result.status) {
                                    Pay.Center.instance.uactgid2List[label] = result;
                                    $('#user_check').val(1);
                                    $('#gcodeid').val(result.roleId);
                                } else {
                                    $('#user_check').val('');
                                    $('#gcodeid').val('');
                                    if (evtlabeltype == 'string' && evtlabel == 'keyup') {
                                        // keyup时，不显示
                                    } else {
                                        $errmsg.addClass('warn').html('您在该区还没有创建角色');
                                    }
                                }
                                if (evtlabeltype && evtlabeltype == 'function') evtlabel();
                            });
                            return;
                        }
                    } else {
                        $('#user_check').val('');
                        $('#gcodeid').val('');
                    }
                } else if (lastTarget == 'ROOF') {
                    var label = '_existUserCode_' + usercode;
                    if (Pay.Center.instance.uactgid2List[label]) {
                        $('#user_check').val(Pay.Center.instance.uactgid2List[label].status ? 1 : '');
                        $('#gcodeid').val('');
                    } else {
                        Pay.Center.Lib.existUserCode({userCode: usercode}, function(result){
                            Pay.Center.instance.uactgid2List[label] = result;
                            $('#user_check').val(result.status ? 1 : '');
                            $('#gcodeid').val('');
                            if (evtlabeltype && evtlabeltype == 'function') evtlabel();
                        });
                        return;
                    }
                } else {
                    $('#user_check').val('');
                    $('#gcodeid').val('');
                }
            } else {
                $('#user_check').val('');
                $('#gcodeid').val('');
                $('.choiceT .errmsg').removeClass('suc warn').html('');
            }

            //juu币
            if (!Pay.Center.Lib.allowPayRoof()) {
                $payRoof = $('.payItem[data-code="ROOF"]');
                if ($payRoof.length) $payRoof.hide();
            } else if ((!evtlabel) || evtlabel != 'lastTargetItem_click') {
                // 防止相互触发，产生循环(checkUser<-->lastTargetItem_click)
                $('.lastTarget_p .aOn').trigger('click', 'checkUser');
            }
            if (evtlabeltype && evtlabeltype == 'function') evtlabel();
        };
        var handle = null;
        $('.usercode_p').bind('checkUser', function(evt, label){
            $('#user_check').val('');
            if (handle) {clearTimeout(handle);handle = null;}
            handle = setTimeout(function(){checkUser(evt, label);}, 200);
            return false;
        }).delegate('.usercode', 'keyup', function(){
            //$('.usercode_p:first').trigger('checkUser', 'keyup');
        }).delegate('.usercode', 'blur', function(){
            $('.usercode_p:first').trigger('checkUser');
        }).trigger('checkUser');
    },
    enableDaijinList: function(){
        var $daijinList = $('.daijin .daijinList');
        if ($daijinList.length) {
            if (window.UserInfo && window.UserInfo.id) {
                Pay.Center.Lib.getUserDjList(function(result){
                    if (result.status) {
                        var data = result.data;
                        var htmlArr = [], tips='';
                        for (var i=0,len=data.length; i<len; i++) {
                            tips = '有效期至' + Pay.Ulib.dateFormat('Y-m-d', data[i].expireDate - 1);
                            data[i].only_first = data[i].only_first ? parseInt(data[i].only_first)||0 : 0;
                            data[i].voucher_limit = data[i].voucher_limit ? parseInt(data[i].voucher_limit)||0 : 0;
                            if (data[i].only_first || data[i].voucher_limit) {
                                tips += ' 注：';
                                if (data[i].only_first) {
                                    tips += '仅首充可用 ';
                                }
                                if (data[i].voucher_limit) {
                                    tips += '订单金额满' + data[i].voucher_limit + '元可用';
                                }
                            }
                            htmlArr.push('<a class="daijinItem isA" href="javascript:void(0)" alt="' + tips + '" title="' + tips + '" ');
                            htmlArr.push('  data-amount="' + data[i].amount + '" data-code="' + data[i].code + '" data-expireDate="' + data[i].expireDate + '" data-gameids="' + data[i].gameIds + '" data-only_first="' + data[i].only_first + '" data-voucher_limit="' + data[i].voucher_limit + '">');
                            htmlArr.push(data[i].amount + '元代金券'+(data[i].only_first ? '<font style="color:red;">(首)</font>' : '')+'<i class="iB"></i>');
                            htmlArr.push('</a>');
                        }
                        htmlArr.push('<a id="Ha" class="daijinItem isA2 daijinEnterItem" href="javascript:void(0)" data-type="enter">手动输入<i class="iB"></i></a>');
                        htmlArr.push('<span class="clear"></span>');
                        $daijinList.html(htmlArr.join(''));

                        // 代金券显示处理
                        var $daijinCont = $('.daijinCont');
                        if ($daijinCont.css('display') == 'none') {
                            $daijinList.show();
                            $daijinCont.find('.jinCheck').hide();
                        } else {
                            $daijinList.show().find('.daijinEnterItem').trigger('click');
                        }
                    }
                });
            }
        }
    },
    enableUserListInit: function(){
        var $usercodeList = $('.usercodeList');
        var userCodeLabel = Pay.Common.Lib.getUserCodeLabel();
        if (userCodeLabel && $usercodeList.length) {
            var userListInit = function(){
                var ulist = Pay.Ulib.getCookie(userCodeLabel);
                if (ulist && ulist.length > 0) {
                    var ulistArr = ulist.split(',');
                    var htmlArr = [];
                    for (var i=0,len=ulistArr.length; i<len; i++) {
                        htmlArr.push('<li class="usercodeItem" data-code="' + ulistArr[i] + '"><a href="javascript:void(0)">' + ulistArr[i] + '</a></li>');
                    }
                    htmlArr.push('<li class="clearUsercodeItem"><a href="javascript:void(0)">清除历史账号记录</a></li>');
                    $usercodeList.find('ul').html(htmlArr.join(''));
                } else {
                    $usercodeList.find('ul').html('');
                }
            };
            userListInit();

            $usercodeList.delegate('.clearUsercodeItem', 'click', function(){
                Pay.Ulib.setCookie(userCodeLabel, '', 1);
                userListInit();
                $usercodeList.trigger('update');
                return false;
            });
        }
    },
    enableConfgInit: function(){
        var config = this.config;
        // 默认选中支付渠道处理
        if (config.rtype) {
            if (/^\d+$/.test(config.rtype)) {
                // 数值
                var $payItem = $('.singlePayItem[data-id="' + config.rtype + '"]:first');
            } else {
                var $payItem = $('.singlePayItem[data-code="' + config.rtype + '"]:first');
            }
            if ($payItem.length) $payItem.trigger('click');
        }

        // 默认选中区服时，触发取游戏区服列表
        if (config.selectedGameId && config.selectedServerArea) {
            // 默认只选择游戏，没有区服，需手动触发
            var usercode = $('.usercode').val();
            if (window.UserInfo && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                $('.selGameBtn').attr('data-gameid', config.selectedGameId).trigger('update', 'hasPlay');
            } else {
                $('.selGameBtn').attr('data-gameid', config.selectedGameId).trigger('update');
            }
        }
    },


    // 充值账号处理
    usercodeFor_click: function(){
        var $aFor = $(this);
        var $usercodep = $('.usercode_p');
        var $usercode = $('.usercode_p').find('.usercode');
        var usercode = $aFor.attr('data-code') || '';
        usercode = Pay.Ulib.encodeHtml(usercode);
        var htmlArr = [];
        var isHide = $usercode.css('display') == 'none' ? true : false;
        if (isHide) {
            htmlArr.push('<label>充值账号：</label>');
            //htmlArr.push('<input type="text" value="' + usercode + '" name="usercode" class="i2 usercode">');
            htmlArr.push('<input type="text" value="" name="usercode" class="i2 usercode" autocomplete="off" />');
            htmlArr.push('<a href="javascript:void(0)" class="aFor" data-code="' + usercode + '">为自己充值</a>');
            htmlArr.push('<span class="errmsg"></span>');
            setTimeout(function(){$usercodep.find('.usercode').focus();}, 0);
        } else {
            htmlArr.push('<label>充值账号：</label>');
            htmlArr.push('<strong>' + usercode + '</strong>');

            if (window.UserInfo && window.UserInfo.oauthShow) {
                htmlArr.push('<a class="span3" href="javascript:void(0)" title="' + Pay.Ulib.encodeHtml(window.UserInfo.oauthShow.lo_title) + '">');
                htmlArr.push('    <var>(</var><i class="ico3 ico_' + window.UserInfo.oauthShow.lo + '"></i><strong>' + Pay.Ulib.encodeHtml(window.UserInfo.oauthShow.lo_name) + '</strong><var>)</var>');
                htmlArr.push('</a>');
            }

            htmlArr.push('<input type="hidden" value="' + usercode + '" name="usercode" class="i2 usercode" style="display:none;" />');
            htmlArr.push('<a href="javascript:void(0)" class="aFor" data-code="' + usercode + '">为他人充值</a>');
        }
        $usercodep.html(htmlArr.join(''));
        $('.usercode_p:first').trigger('checkUser');

        // 显示全部游戏
        $('.choiceCon_game').find('.gTabCustom').trigger('click');
        $('.choiceCon_server').find('.sTabCustom').trigger('click');

        // 更新选择游戏滚动
        $('.choiceDiv').trigger('updateScrollBar');
        $('.payMod').trigger('checkPaypalRecord');

        // 代金券检测
        var $daijin = $('.daijin');
        if ($daijin.length) $daijin.trigger('update');

        return false;
    },
    usercode_change: function(){
        $('.payMod').trigger('checkPaypalRecord');
    },
    // 充值目标（类型）处理
    lastTargetItem_click: function(evt, label){
        var type = $(this).attr('data-value');
        $(this).addClass('aOn').siblings('a').removeClass('aOn');
        $('.lastTargetCon_'+type).show().siblings('.lastTargetCon').hide();
        $('#lastTarget').val(type);
        var $payRoof = $('.payItem[data-code="ROOF"]');
        var $payPaypal = $('.payItem[data-code="PAYPAL"]');
        if (window.PlatInfo.code == 'juu') {
            //juu额外处理
            var $payItemList = $('.payMod .payItem');
            if ($payItemList.length) {
                // 隐藏非alipay,e-bank
                var allowPayChannels = ',GALIPAY,ALIPAY,E-BANK,';
                var setMoreMod = false;
                var onHide = false; // 是否有当前选中的渠道被隐藏
                $payItemList.each(function(){
                    var $that = $(this);
                    var code = $that.attr('data-code');
                    if ((!code) || !(allowPayChannels.indexOf(code) > 0 || (allowPayChannels.indexOf('E-BANK') > -1 && code.indexOf('E-BANK') > -1))) {
                        var $moreMod = $that.closest('.moreMod');
                        if (type == 'ROOF') {
                            $that.hide();
                            if ((!setMoreMod) && $moreMod.length) {
                                setMoreMod = true;
                                $moreMod.hide();
                                if ($moreMod.find('.moreSelectItem').hasClass('aOn')) onHide = true;
                            } else {
                                if ($that.hasClass('aOn')) onHide = true;
                            }
                        } else {
                            if (code == 'ROOF') {
                                Pay.Center.Lib.allowPayRoof() && $that.show();
                            } else {
                                $that.show();
                            }
                            if ((!setMoreMod) && $moreMod.length) {
                                setMoreMod = true;
                                $moreMod.show();
                            }
                        }
                    }
                });
                if (onHide) {
                    var $ebank = $('.payItem[data-code*="E-BANK"]:first');
                    var $alipay = $('.payItem[data-code="ALIPAY"]');
                    if ($ebank.length) {
                        $ebank.trigger('click');
                    } else if($alipay.length) {
                        $alipay.trigger('click');
                    } else {
                        $('#lastTarget').val('');
                    }
                }
            }
        } else {
            if (type == 'ROOF') {
                // 隐藏平台币支付方式，若当前已选中该支付方式，则切换到下一支付方式
                if ($payRoof.length) {
                    $payRoof.hide();
                    if ($payRoof.hasClass('aOn') || $('.moreSelectItem[data-code="ROOF"]').length) {
                        // 先网银
                        var $ebank = $('.payItem[data-code*="E-BANK"]:first');
                        if ($ebank.length) {
                            $ebank.trigger('click');
                        } else {
                            var $payItemList = $('.payItem');
                            if ($payItemList.length) {
                                for (var i=0,len=$payItemList.length; i<len; i++) {
                                    var $payItemTemp = $payItemList.eq(0);
                                    var payCodeTemp = $payItemTemp.attr('data-code') || '';
                                    if (payCodeTemp != 'ROOF' && payCodeTemp != 'PayPal') {
                                        $payItemTemp.trigger('click');
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                // 隐藏paypal支付方式，若当前已选中该支付方式，则切换到下一支付方式
                if ($payPaypal.length) {
                    $payPaypal.hide();
                    if ($('#lastChannel').val() == 'PAYPAL') {
                        // 先网银
                        var $ebank = $('.payItem[data-code*="E-BANK"]:first');
                        if ($ebank.length) {
                            $ebank.trigger('click');
                        } else {
                            var $payItemList = $('.payItem');
                            if ($payItemList.length) {
                                for (var i=0,len=$payItemList.length; i<len; i++) {
                                    var $payItemTemp = $payItemList.eq(0);
                                    var payCodeTemp = $payItemTemp.attr('data-code') || '';
                                    if (payCodeTemp != 'ROOF' && payCodeTemp != 'PayPal') {
                                        $payItemTemp.trigger('click');
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if ($payRoof.length && Pay.Center.Lib.allowPayRoof()) $payRoof.show();
                if ($payPaypal.length) $payPaypal.show();
            }
        }
        if ((!label) || label != 'checkUser') $('.usercode_p').trigger('checkUser', 'lastTargetItem_click');
        // 更新套餐
        $('.amountSelect').trigger('update');
        // 检测paypal备案
        $('.payMod').trigger('checkPaypalRecord');
        // 更新代金券
        var $daijin = $('.daijin');
        if ($daijin.length) $daijin.trigger('update');
        return false;
    },
    // 选择游戏按钮，显示列表处理
    selGameBtn_click: function(evt){
        var $this = $(this);
        var $choiceDiv = $('.choiceDiv');
        if ($choiceDiv.css('display') == 'none' || $('.choiceCon_game').css('display') == 'none') {
            // 按钮状态处理
            $this.removeClass('aOn').addClass('aOver');

            $choiceDiv.css('left', '106px').show();
            var $choiceCon_game = $('.choiceCon_game');
            $choiceCon_game.show().siblings('.choiceCon').hide();
            $('.choiceGame .gTab').css({zIndex:999});
            $choiceDiv.trigger('updateScrollBar');

            // 更新选择区服按钮状态
            var server_id = $('#server_id').val();
            if (server_id) {
                $('.selServerBtn').removeClass('aOver').addClass('aOn');
            } else {
                $('.selServerBtn').removeClass('aOver aOn');
            }
        } else {
            $choiceDiv.css('display', 'none');
            // 按钮状态处理
            var gameId = parseInt($(this).attr('data-gameid')) || 0;
            if (gameId) {
                $this.removeClass('aOver').addClass('aOn');
            } else {
                $this.removeClass('aOver aOn');
            }
        }
        return false;
    },
    // 选择游戏改变，更新区服列表列表处理  label:nostate，用于默认选中(需检测显示玩过区服还是全部区服)
    selGameBtn_update: function(evt, label){
        var $that = $(this);
        var gameId = parseInt($that.attr('data-gameid')) || 0;
        var instance = Pay.Center.instance;
        $('#allServerList').html('')
        $('.selServerBtn').removeClass('serDis');

        var $choiceCon_server = $('.choiceCon_server');
        // 天书初始化特殊处理 gameId有可能为地域区服
        var $choiceCon_server_gTab2  = $choiceCon_server.find('.gTab2');
        var $choiceCon_server_bigTab = $choiceCon_server.find('.bigTab');
        var $choiceCon_server_gTab   = $choiceCon_server.find('.gTab');
        var tsCode = Pay.Common.Lib.getTsCodeByGid(gameId);
        var hasPlay = (label && label == 'hasPlay') ? true : false;

        $choiceCon_server_gTab.find('.sTabCustom').attr('data-tabNum', 'all').html('全部区服');
        if (tsCode) {
            // 添加天书大tab
            if (!$choiceCon_server_bigTab.length) {
                // 默认显示区服列表
                var htmlTsType = [];
                htmlTsType.push('<div class="gTab2" style="display: none;">'); // 默认不显示, 由render2开启
                htmlTsType.push('    <a class="sTabItem2" data-type="play" href="javascript:void(0)">玩过区服</a>');
                htmlTsType.push('    <a class="sTabItem2 aOn" data-type="all" href="javascript:void(0)">区服列表</a>');
                htmlTsType.push('</div>');
                htmlTsType.push('<div class="bigTab" style="display: block;">');
                htmlTsType.push('    <a class="gameItem isA' + (tsCode == 'ts' ? ' aOn' : '') + '" href="javascript:void(0)" data-code="ts" data-gameid="125" data-name="天书世界">双线-区服<i class="iB"></i></a>');
                htmlTsType.push('    <a class="gameItem isA' + (tsCode == 'tsqy' ? ' aOn' : '') + '" href="javascript:void(0)" data-code="tsqy" data-gameid="' + Pay.Common.Lib.getTsGidByCode('tsqy') + '" data-name="天书世界">地域-区服<i class="iB"></i></a>');
                htmlTsType.push('    <a class="gameItem isA' + (tsCode == 'tshg' ? ' aOn' : '') + '" href="javascript:void(0)" data-code="tshg" data-gameid="' + Pay.Common.Lib.getTsGidByCode('tshg') + '" data-name="天书世界">回归-区服<i class="iB"></i></a>');
                htmlTsType.push('</div>');
                $choiceCon_server_gTab.show().before(htmlTsType.join(''));
            } else {
                if (label && label == 'bigTab') {
                    // 检测当前大标签是在玩过的区服还是区服列表
                    if ($choiceCon_server_gTab2.find('.aOn').attr('data-type') == 'all') {
                        // 区服列表标签
                        $choiceCon_server_gTab.show();
                        $choiceCon_server_bigTab.show();
                    } else {
                        $choiceCon_server_gTab.hide();
                        $choiceCon_server_bigTab.hide();
                    }
                } else {
                    $choiceCon_server_gTab2.find('.sTabItem2[data-type="all"]').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                    $choiceCon_server_gTab.show();
                    $choiceCon_server_bigTab.show();
                }

                $choiceCon_server_bigTab.find('.gameItem[data-code="'+tsCode+'"]').addClass('aOn').siblings('.gameItem').removeClass('aOn');
            }
        } else {
            $choiceCon_server_gTab.show();
            if ($choiceCon_server_gTab2.length) $choiceCon_server_gTab2.hide();
            if ($choiceCon_server_bigTab.length) $choiceCon_server_bigTab.hide();
        }

        // 显示玩过的区服
        if (hasPlay || tsCode) {
            if (instance.config.loadedUserPlayGameList) {
                // 玩过的区服加载完
                if (instance.playUgid2Slist && instance.playUgid2Slist[gameId]) {
                    $('.allSer').trigger('render2', [instance.playUgid2Slist[gameId], label]);
                }
            } else {
                // 等待玩过的区服加载完
                $('body').unbind('loadedUserPlayGameList').bind('loadedUserPlayGameList', function(){
                    var instance = Pay.Center.instance;
                    if (instance.playUgid2Slist && instance.playUgid2Slist[gameId]) {
                        $('.allSer').trigger('render2', [instance.playUgid2Slist[gameId], label]);
                    }
                    return false;
                });
            }
        }

        // 显示全部区服
        if (instance.gid2Slist && instance.gid2Slist[gameId]) {
            $('.allSer').trigger('render', [instance.gid2Slist[gameId]]);
        } else {
            var $allSerList = $('.allSerList');
            var $allSerListCon = $allSerList.find('.serCon');
            var $allSerListScrollBar = $allSerList.find('.scrollBarWrap');
            $allSerListCon.addClass('loading');
            $allSerListScrollBar.hide();
            Pay.Common.Lib.getServerListByGameId({gameId:gameId}, function(result){
                $allSerListCon.removeClass('loading');
                // 返回时，检测当前gameId,如果不一致，则过期为失效
                var newGameId = parseInt($that.attr('data-gameid'));
                if (newGameId == gameId) {
                    if (result.status) {
                        if (!Pay.Center.instance.gid2Slist[gameId]) {
                            Pay.Center.instance.gid2Slist[gameId] = result.data;
                            $('.allSer').trigger('render', [result.data]);
                        }
                    } else {
                        $('.allSer').trigger('render', []);
                    }
                } else {
                    // 已过期
                }
            });
        }

        return false;
    },
    choiceDiv_scrollBar: function(){
        var $this = $(this);
        if ($this.css('display') != 'none') {
            var scrollBarCls = ['#allList', '#hasList', '.allSerList', '.hasSerList'];
            for (var i=0,len=scrollBarCls.length; i<len; i++) {
                var $temp = $(scrollBarCls[i]);
                if ($temp.length && $temp.css('display') != 'none') {
                    $temp.scrollBarPlugin();
                }
            }
        }

        return false;
    },
    serverList_render: function(evt, data){
        // 显示全部区服
        var $allSer = $('#allSer');
        var $conServerTab = $('.choiceCon_server .gTab');
        var $allServerList = $('#allServerList');
        if (data && data.length) {
            var $selGameBtn = $('.selGameBtn');
            var gameid = parseInt($selGameBtn.attr('data-gameid')) || 0;
            var isGameTs = gameid && gameid == 125;
            var isGameJyjx = gameid && gameid == 155;
            var isGameZhtx = gameid && gameid == 147;
            var isGameDtx  = gameid && gameid == 184;
            var tsCode   = Pay.Common.Lib.getTsCodeByGid(gameid);
            var stepSize = 100, i=0, len=data.length;
            var jyjxBackServerNum = 0;
            for (var i=len; i>0; i--) {
                if (isGameJyjx && data[i-1].area >= 9999) {
                    if (data[i-1].area >= 20000) {
                        ++jyjxBackServerNum;
                    }
                } else {
                    break;
                }
            }
            // 体验服9999-19999
            var preServerArr = [];
            // 回归服20000以上
            var backServerArr = [];
            if (len > i) {
                if (jyjxBackServerNum) {
                    preServerArr  = data.slice(i-len, -jyjxBackServerNum);
                    if (len-1 > jyjxBackServerNum) {
                        backServerArr = data.slice(-jyjxBackServerNum);
                    }
                } else {
                    preServerArr = data.slice(i-len);
                }
                var dataNew  = data.slice(0, i);
                data = dataNew;
            }

            len = data.length;
            var maxArea = len > 0 ? data[len-1].area : 0;
            var stepLength = isGameJyjx ? 4 : 5;
            // 计算步长
            i = 0;
            do {
                i++;
            } while ((stepSize*i*stepLength) < maxArea);
            stepSize *= i;

            // 生成tab标签
            var htmlTabArr = [], tabNum=0, tabRange = [];
            var $hasTabCustom = $conServerTab.find('.sTabCustom');
            if ($hasTabCustom.length) {
                htmlTabArr.push('<a class="sTabItem sTabCustom" href="javascript:void(0)" data-tabNum="' + $hasTabCustom.attr('data-tabNum') + '">' + $hasTabCustom.html() + '</a>');
            } else {
                htmlTabArr.push('<a class="sTabItem sTabCustom" href="javascript:void(0)" data-tabNum="all">全部区服</a>');
            }

            if (tsCode && tsCode == 'tsqy') {
                var tabArr = [{key:'A,B,C,D,E', value:'安徽,北京,重庆'},                                                                           // A,B,C,D,E
                              {key:'F,G,H,I,J', value:'福建,广东,广西,贵州,甘肃,港澳台,河南,河北,湖南,湖北,黑龙江,海南,海外,江苏,浙江,江西,吉林'}, // F,G,H,I,J
                              {key:'K,L,M,N,O', value:'内蒙古,宁夏'},                                                                              // K,L,M,N,O
                              {key:'P,Q,R,S,T', value:'青海,山东,四川,陕西,山西,上海,天津'},                                                       // P,Q,R,S,T
                              {key:'W,X,Y,Z',   value:'辽宁,新疆,西藏,云南'}];                                                                     // W,X,Y,Z

                var j=0,jlen=0,k=0,klen=0,serverListProvince={},item;
                for (j=0,jlen=tabArr.length; j<jlen; j++) {
                    htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="' + j + '">' + tabArr[j].key.replace(/,/g, '') + '</a>');

                    item = tabArr[j].value.split(',');
                    for (k=0,klen=item.length; k<klen; k++) {
                        serverListProvince[item[k]] = {key:j, data:[]};
                    }
                }
                $conServerTab.html(htmlTabArr.join(''));

                // 添加区服列表
                var htmlSerArr = [];
                var firstArea = '';
                var provinceName = '';
                for (i=0; i<len; i++) {
                    provinceName = data[i].name.match(/\[([^\]]+)\]/);
                    if (typeof(serverListProvince[provinceName[1]]) != 'undefined') {
                        serverListProvince[provinceName[1]].data.push(data[i]);
                    } else {
                        serverListProvince['海外'].data.push(data[i]);
                    }

                    if (!firstArea) firstArea = data[i].area;
                }

                var item2;
                for (j=0,jlen=tabArr.length; j<jlen; j++) {
                    item = tabArr[j].value.split(',');
                    for (k=0,klen=item.length; k<klen; k++) {
                        item2 = serverListProvince[item[k]]['data'];
                        if (item2.length) {
                            for (i=0,len=item2.length; i<len; i++) {
                                htmlSerArr.push('<li data-hasplay="0" class="serverTabItem_' + serverListProvince[item[k]].key + '" data-keywords="' + item2[i].area + '">');
                                htmlSerArr.push('    <a class="serverItem" href="javascript:void(0)" data-id="' + item2[i].id + '" data-area="' + item2[i].area + '">' + item2[i].name + '</a>');
                                htmlSerArr.push('</li>');
                            }
                        }
                    }
                }
            } else {
                if ((!isGameJyjx) && !isGameTs) {
                    var countArr = {1:0, 2:0};
                    for (var i=len; i>0; i--) {
                        if (data[i-1].area >= 9999) {
                            ++countArr[2];
                        } else {
                            ++countArr[1];
                        }
                    }
                    var countArea = Math.ceil(countArr[1]/100)*100 + Math.ceil(countArr[2]/100)*100;
                    i = 0;
                    stepSize = 100;
                    do {
                        i++;
                    } while ((stepSize*i*5) < countArea);
                    stepSize *= i;

                    // 计算标签范围
                    tabRange = [];
                    tabRangeExist = {};
                    var endTabRange;
                    for (var i=0,len=data.length; i<len; i++) {
                        endTabRange = Math.ceil(data[i].area/stepSize)*stepSize;
                        if (typeof(tabRangeExist[endTabRange]) == 'undefined') {
                            tabRangeExist[endTabRange] = 1;
                            tabRange.push([tabRange.length+1, endTabRange-stepSize+1, endTabRange]);
                            if (isGameDtx && endTabRange >= 65500) {
                                htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="' + tabRange.length + '">体验服</a>');
                            } else if (isGameZhtx && endTabRange > 10000) {
                                htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="' + tabRange.length + '">留档服</a>');
                            } else {
                                htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="' + tabRange.length + '">' + (endTabRange-stepSize+1) + '-' + endTabRange + '</a>');
                            }
                        }
                    }
                } else {
                    maxArea = maxArea == 0 && preServerArr.length ? 1 : maxArea;
                    for (i=1; i<=maxArea; i+=stepSize) {
                        ++tabNum;
                        if (isGameTs) {
                            if (i == 1) {
                                tabRange.push([tabNum, i, i+stepSize]);
                            } else {
                                tabRange.push([tabNum, i+1, i+stepSize]);
                            }
                        } else {
                            tabRange.push([tabNum, i, i+stepSize-1]);
                        }
                        htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="' + tabNum + '">' + i + '-' + (i+stepSize-1) + '</a>');
                    }
                    // 回归服处理
                    if (backServerArr.length) {
                        tabRange.push([20000, 20001, 29999]);
                        htmlTabArr.push('<a class="sTabItem" href="javascript:void(0)" data-tabNum="20000">回归服</a>');
                    }
                }
                htmlTabArr.push('<div class="si">');
                htmlTabArr.push('    <span class="placeholder">输入区服编号</span>');
                htmlTabArr.push('    <input class="searchServerItem i2" type="text" name="" value="" data-placeholder="输入区服编号"/>');
                htmlTabArr.push('    <a class="clearSearchServer" href="javascript:void(0)">关闭</a>');
                htmlTabArr.push('</div>');
                $conServerTab.html(htmlTabArr.join(''));

                // 添加体验区服列表
                var htmlSerArr = [];
                if (preServerArr.length) {
                    for (var i=0,ilen=preServerArr.length; i<ilen; i++) {
                        htmlSerArr.push('<li data-hasplay="0" class="serverTabItem_1" data-keywords="' + preServerArr[i].area + '">');
                        htmlSerArr.push('    <a class="serverItem" href="javascript:void(0)" data-id="' + preServerArr[i].id + '" data-area="' + preServerArr[i].area + '">' + preServerArr[i].name + '</a>');
                        htmlSerArr.push('</li>');
                    }
                }

                var firstArea = '';
                for (i=0; i<len; i++) {
                    // 计算当前区服，所属标签
                    tabNum = 1;
                    for (var j=0,jlen=tabRange.length; j<jlen; j++) {
                        if (data[i].area >= tabRange[j][1] && data[i].area <= tabRange[j][2]) {
                            tabNum = (j+1);
                            break;
                        }
                    }
                    if (!firstArea) firstArea = data[i].area;

                    htmlSerArr.push('<li data-hasplay="0" class="serverTabItem_' + tabNum + '" data-keywords="' + (isGameTs ? (data[i].area - 1) : data[i].area) + '">');
                    htmlSerArr.push('    <a class="serverItem" href="javascript:void(0)" data-id="' + data[i].id + '" data-area="' + data[i].area + '">' + data[i].name + '</a>');
                    htmlSerArr.push('</li>');
                }

                // 添加回归服列表
                if (backServerArr.length) {
                    for (var i=0,ilen=backServerArr.length; i<ilen; i++) {
                        htmlSerArr.push('<li data-hasplay="0" class="serverTabItem_20000" data-keywords="' + backServerArr[i].area + '">');
                        htmlSerArr.push('    <a class="serverItem" href="javascript:void(0)" data-id="' + backServerArr[i].id + '" data-area="' + backServerArr[i].area + '">' + backServerArr[i].name + '</a>');
                        htmlSerArr.push('</li>');
                    }
                }

            }
            $allServerList.append(htmlSerArr.join(''));

            $('.choiceCon_server .gTab .sTabItem:first').trigger('click');

            var config = Pay.Center.instance.config;
            if (config.selectedServerArea && config.selectedServerArea > 0) {
                var $selectedServerItem = $allServerList.find('.serverItem[data-area="' + config.selectedServerArea + '"]:eq(0)');
                if ($selectedServerItem.length) {
                    $selectedServerItem.trigger('click');
                    setTimeout(function(){$selectedServerItem.trigger('click');}, 0);
                    Pay.Center.instance.config.selectedServerArea = -1;
                }
            } else if (data.length == 1) {
                // 区服只有一个，自动选中
                var $selectedServerItem = $allServerList.find('.serverItem[data-area="' + firstArea + '"]:eq(0)');
                if ($selectedServerItem.length) {
                    setTimeout(function(){$selectedServerItem.trigger('click');}, 0);
                }
            }

            $allSer.find('.noInfo').hide();
            $allServerList.show();
            $('.choiceDiv').trigger('updateScrollBar');
        } else {
            // 没有区服列表
            // 生成第一个tab标签
            var htmlTabArr = [];
            var $hasTabCustom = $conServerTab.find('.sTabCustom');
            if ($hasTabCustom.length) {
                htmlTabArr.push('<a class="sTabItem sTabCustom aOn" href="javascript:void(0)" data-tabNum="' + $hasTabCustom.attr('data-tabNum') +'">' + $hasTabCustom.html() + '</a>');
            } else {
                htmlTabArr.push('<a class="sTabItem sTabCustom aOn" href="javascript:void(0)" data-tabNum="all">全部区服</a>');
            }
            $conServerTab.html(htmlTabArr.join(''));

            // 提示没有区服信息
            $allSer.find('.noInfo').html('暂无区服信息。').show();
            $allServerList.hide();
            $allSer.find('.scrollBarWrap').hide();
        }

        return false;
    },
    // 玩过的区服渲染，仅占一个标签(玩过区服)
    serverList_render2: function(evt, data, label){
        // data 默认以降序排列
        var $that = $(this);
        // 显示全部区服
        if (data && data.length) {
            var gameId = parseInt($('.selGameBtn').attr('data-gameid')) || 0;
            var tsCode = Pay.Common.Lib.getTsCodeByGid(gameId);
            var bigTab = label && label == 'bigTab' ? true : false

            // 添加区服列表
            $allServerList = $('#allServerList');
            var htmlSerArr = [];
            for (var i=0,len=data.length; i<len; i++) {
                htmlSerArr.push('<li data-hasplay="1" data-keywords="' + data[i].area + '">');
                htmlSerArr.push('    <a class="serverItem" href="javascript:void(0)" data-id="' + data[i].id + '" data-area="' + data[i].area + '">' + data[i].name + '</a>');
                htmlSerArr.push('</li>');
            }
            $allServerList.append(htmlSerArr.join(''));

            if (!bigTab) {
                var config = Pay.Center.instance.config;
                if (config.selectedServerArea && !config.selectedServerAreaHas) {
                    // 有默认值，第一次不自动选中，防止覆盖到默认值
                    config.selectedServerAreaHas = true;
                } else if (data.length == 1) {
                    // 区服只有一个，自动选中
                    var $selectedServerItem = $allServerList.find('.serverItem:first');
                    if ($selectedServerItem.length) {
                        setTimeout(function(){$selectedServerItem.trigger('click', label);}, 0);
                    }
                }
            }

            // 更新玩过区服标签信息
            var $conServerTab = $('.choiceCon_server .gTab');
            var $conServerTab2 = $('.choiceCon_server .gTab2');
            if (!tsCode) {
                $conServerTab.find('.sTabCustom').attr('data-tabNum', 'play').html('玩过区服').trigger('click');
            } else if (!bigTab) {
                // 切到玩过的游戏区服
                var $conServerTab2 = $('.choiceCon_server .gTab2');
                $conServerTab2.show().find('.sTabItem2[data-type="play"]:first').trigger('click');
            }
        }
        return false;
    },

    // 选择区服标签处理
    choiceSerTab_click: function(evt, label){
        var $this = $(this);
        var gameId = parseInt($('.selGameBtn').attr('data-gameid')) || 0;
        var tsCode = Pay.Common.Lib.getTsCodeByGid(gameId);
        var bigTab = label && label == 'bigTab' ? true : false;
        if (!bigTab) $this.addClass('aOn').siblings('a').removeClass('aOn');

        var hasSearchData = false;
        var $allSer = $('#allSer');
        var $allServerList = $('#allServerList');
        if ($this.hasClass('sTabCustom')) {
            if (!bigTab) {
                // 当前是搜索结果状态
                var $searchServerItem = $('.searchServerItem');
                var sValue = $searchServerItem.val();
                if (sValue && sValue.length) {
                    setTimeout(function(){$searchServerItem.trigger('focus');}, 0);
                    return false;
                }
            }

            // 显示玩过的区服，如果没有切换到全部游戏
            var $choiceCon_server = $('.choiceCon_server');
            var $choiceCon_server_gTab2  = $choiceCon_server.find('.gTab2');
            var $choiceCon_server_bigTab = $choiceCon_server.find('.bigTab');
            var $choiceCon_server_gTab   = $choiceCon_server.find('.gTab');

            var $switchServerList = $allServerList.find('li[data-hasplay="1"]');
            var usercode = $('.usercode').val();
            if (window.UserInfo && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                if ($this.attr('data-tabNum') == 'play') {
                    // 切换到玩过的区服
                    $allServerList.find('li[data-hasplay="0"]').removeClass('serverItemAllow serverItemAllowShow');
                    $this.html('玩过区服');
                    $switchServerList.addClass('serverItemAllow serverItemAllowShow');

                    if (tsCode) {
                        $choiceCon_server_gTab2.show().find('data-type="play"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                        $choiceCon_server_bigTab.hide();
                        $choiceCon_server_gTab.hide();
                    } else {
                        $choiceCon_server_bigTab.hide();
                        $choiceCon_server_gTab2.hide().find('data-type="play"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                        $choiceCon_server_gTab.show();
                    }
                } else {
                    // 切换到全部的区服
                    $this.html('全部区服');
                    $switchServerList = $allServerList.find('li[data-hasplay="0"]').addClass('serverItemAllow serverItemAllowShow');

                    if (tsCode) {
                        $choiceCon_server_gTab2.show().find('data-type="all"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                        $choiceCon_server_bigTab.show();
                        $choiceCon_server_gTab.show();
                    } else {
                        $choiceCon_server_gTab2.hide().find('data-type="all"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                        $choiceCon_server_bigTab.hide();
                        $choiceCon_server_gTab.show();
                    }
                }
            } else {
                // 只显示全部区服
                $this.html('全部区服');
                $switchServerList = $allServerList.find('li[data-hasplay="0"]').addClass('serverItemAllow serverItemAllowShow');

                if (tsCode) {
                    $choiceCon_server_gTab2.hide().find('data-type="all"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                    $choiceCon_server_bigTab.show();
                    $choiceCon_server_gTab.show();
                } else {
                    $choiceCon_server_gTab2.hide().find('data-type="all"').addClass('aOn').siblings('.sTabItem2').removeClass('aOn');
                    $choiceCon_server_bigTab.hide();
                    $choiceCon_server_gTab.show();
                }
            }

            hasSearchData = $switchServerList.length > 0 ? true : false;
        } else {
            // 区服范围查找，先切换到全部区服
            var $switchServerList = $allServerList.find('li[data-hasplay="1"]');
            if ($switchServerList.length) {
                $switchServerList.removeClass('serverItemAllow serverItemAllowShow');
            }

            var $toswitchServerList = $allServerList.find('li[data-hasplay="0"]');
            if ($toswitchServerList.length) {
                $toswitchServerList.addClass('serverItemAllow').removeClass('serverItemAllowShow');
            }

            var tabNum = parseInt($this.attr('data-tabNum')) || 0;
            var $tabItemList = $allServerList.find('.serverTabItem_'+tabNum);
            if ($tabItemList.length) {
                $tabItemList.addClass('serverItemAllowShow');
                hasSearchData = true;
            }
        }

        // 未找到区服，显示提示处理
        if (!hasSearchData) {
            $allSer.find('.noInfo').html('未找到区服信息。').show();
            $allServerList.hide();
            $allSer.find('.scrollBarWrap').hide();
        } else {
            $allSer.find('.noInfo').hide();
            $allServerList.show();
            $allSer.find('.scrollBarWrap').show();
            $('.choiceDiv').trigger('updateScrollBar');
        }
        return false;
    },
    serverItem_click: function(evt, label){
        var $this = $(this);
        var id = parseInt($this.attr('data-id')) || 0;
        $('#server_id').val(id);
        if (!(label && label == 'bigTab')) $('.choiceDiv').hide();
        $('.selServerBtn').html($this.text()).addClass('aOn');
        // 更新套餐
        $('.amountSelect').trigger('update');
        $('.usercode_p').trigger('checkUser');
        // 检测paypal备案
        $('.payMod').trigger('checkPaypalRecord');
        // 更新代金券
        var $daijin = $('.daijin');
        if ($daijin.length) $daijin.trigger('update');
        return false;
    },
    choiceSerTab2_click: function(evt, label){
        var $this = $(this);
        var type  = $this.attr('data-type') || '';
        $this.addClass('aOn').siblings('a').removeClass('aOn');
        var $gTab2 = $this.closest('.gTab2');
        if (type == 'all') {
            $gTab2.nextAll('.bigTab,.gTab').show();
            // 切换标签更新内容
            $('.choiceCon_server .gTab .sTabCustom:first').attr('data-tabNum', 'all').html('全部区服');
            $('.choiceCon_server .gTab .aOn:first').trigger('click');
        } else {
            // 切换到玩过的游戏
            $gTab2.nextAll('.bigTab,.gTab').hide();
            $('.choiceCon_server .gTab .sTabCustom:first').attr('data-tabNum', 'play').html('玩过区服').trigger('click', 'bigTab');
        }

        return false;
    },
    // 选择区服按钮，显示列表处理
    selServerBtn_click: function(evt, label){
        var $this = $(this);
        var $selGameBtn = $('.selGameBtn');
        var gameid = parseInt($selGameBtn.attr('data-gameid')) || 0;
        if (gameid > 0) {
            $('.choiceT .errmsg').removeClass('suc warn').html('');
            var $choiceDiv = $('.choiceDiv');
            if ($choiceDiv.css('display') == 'none' || $('.choiceCon_server').css('display') == 'none') {
                // 显示区服列表
                $this.removeClass('aOn').addClass('aOver');

                $choiceDiv.css('left', '330px').show();
                var $choiceCon_server = $('.choiceCon_server');
                $choiceCon_server.show().siblings('.choiceCon').hide();
                $choiceDiv.trigger('updateScrollBar');

                // 更新选择游戏按钮状态
                $('.selGameBtn').removeClass('aOver').addClass('aOn');
            } else {
                // 隐藏区服列表
                $choiceDiv.css('display', 'none');
                var server_id = $('#server_id').val();
                if (server_id) {
                    $this.removeClass('aOver').addClass('aOn');
                } else {
                    $this.removeClass('aOver aOn');
                }
            }
        } else {
            $this.removeClass('aOver aOn');
            $('.choiceT .errmsg').removeClass('suc').addClass('warn').html('请选择您要充值的游戏');
            $('.selGameBtn').trigger('click');
        }
        return false;
    },
    // 选择某个游戏处理
    gameItem_click: function(evt, type, selectedGameId){
        var $this = $(this);
        // 获取当前选择的游戏id，设置到“选择游戏”按钮上
        var gameid = parseInt($this.attr('data-gameid')) || 0;
        var gamename = $this.attr('data-name') || $this.text();
        var $selGameBtn = $('.selGameBtn');
        var selected = $selGameBtn.attr('data-gameid') || 0;
        var isBigTab = !!$this.closest('.bigTab').length;
        if (isBigTab && selected && selected == gameid) return false;
        $selGameBtn.attr('data-gameid', selectedGameId||gameid).html(gamename).addClass('aOn');

        // 列表弹窗显示隐藏处理
        if (isBigTab) {
            $selGameBtn.trigger('update', 'bigTab');
            return false;
        }

        $('.choiceCon_game').hide();
        $('.choiceDiv').hide();

        // 检测paypal备案
        $('.payMod').trigger('checkPaypalRecord');
        // 更新代金券
        var $daijin = $('.daijin');
        if ($daijin.length) $daijin.trigger('update');

        $('.choiceT .errmsg').html('');

        // 更新区服列表
        if (type == 'nostate') {
            // 默认选择游戏时使用
            var config = Pay.Center.instance.config;
            if (!(config.selectedGameId && config.selectedServerArea)) {
                // 默认只选择游戏，没有区服，需手动触发
                var usercode = $('.usercode').val();
                if (window.UserInfo && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                    $selGameBtn.trigger('update', 'hasPlay');
                } else {
                    $selGameBtn.trigger('update');
                }
            }
        } else {
            // 选择游戏改变，清空当前选择的区服
            $('.selServerBtn').removeClass('serDis').html('选择区服');
            $('#server_id').val('');

            var $li = $this.closest('li');
            if ($li.attr('data-hasplay') == '1') {
                // 直接更新区服
                $selGameBtn.trigger('update', 'hasPlay');
                $('.selServerBtn').trigger('click', 'hasPlay');
            } else {
                // ajax更新区服信息
                $selGameBtn.trigger('update');
                $('.selServerBtn').trigger('click');
            }
        }

        $('.usercode_p').trigger('checkUser');

        return false;
    },
    // 选择游戏标签处理
    choiceGameTab_click: function(){
        var $this = $(this);
        var $allList = $('#allList');
        var $gameItemList = $('#allGameList');
        var range = $this.attr('data-range');
        $this.addClass('aOn').siblings('a').removeClass('aOn');
        var hasSearchData = false;
        if (range) {
            // 切换到全部游戏 查找
            var $switchGameList = $gameItemList.find('li[data-hasplay="1"]');
            if ($switchGameList.length) $switchGameList.removeClass('gameItemAllow gameItemAllowShow');

            range = ',' + range.toUpperCase() + ',';
            $gameItemList.find('li[data-hasplay="0"]').each(function(){
                var val = $(this).attr('data-type').toUpperCase();
                if (range.indexOf(','+val+',') > -1) {
                    hasSearchData = true;
                    $(this).addClass('gameItemAllow gameItemAllowShow');
                } else {
                    $(this).addClass('gameItemAllow').removeClass('gameItemAllowShow');
                }
            });
        } else {
            var isLogin = Pay.Common.Lib.isLogin();
            var usercode = $('.usercode').val();
            // 当前是搜索结果状态
            var $searchGameItem = $('.searchGameItem');
            var sValue = $searchGameItem.val();
            if (sValue.length) {
                setTimeout(function(){$searchGameItem.trigger('focus');}, 0);
                if (isLogin) {
                    if (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode) {
                        $('.gTabItem').not('.gTabCustom').show();
                    } else if (!(window.PlatInfo && window.PlatInfo.gamecate)) {
                        $('.gTabItem').not('.gTabCustom').hide();
                    }
                }
                return false;
            }

            // 显示全部处理
            if (isLogin && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                // 切换到玩过的游戏 显示全部
                var $switchGameList = $gameItemList.find('li[data-hasplay="0"]');
                if ($switchGameList.length) $switchGameList.removeClass('gameItemAllow gameItemAllowShow');

                // 显示玩过的游戏
                $('.gTabCustom').html('玩过游戏');
                var $toswitchGameList = $gameItemList.find('li[data-hasplay="1"]');
                if ($toswitchGameList.length) {
                    $toswitchGameList.addClass('gameItemAllow gameItemAllowShow');
                    hasSearchData = true;
                }
                $('.gTabItem').not('.gTabCustom').show();
            } else {
                // 切换到全部游戏 显示全部
                var $switchGameList = $gameItemList.find('li[data-hasplay="1"]');
                if ($switchGameList.length) $switchGameList.removeClass('gameItemAllow gameItemAllowShow');

                if (window.PlatInfo && window.PlatInfo.gamecate) {
                    $('.gTabCustom').html('热门游戏');
                } else {
                    $('.gTabCustom').html('全部游戏');
                    isLogin && $('.gTabItem').not('.gTabCustom').hide();
                }
                var $toswitchGameList = $gameItemList.find('li[data-hasplay="0"]');
                if ($toswitchGameList.length) {
                    $toswitchGameList.addClass('gameItemAllow gameItemAllowShow');
                    hasSearchData = true;
                }
            }
        }

        // 未找到游戏，显示提示处理
        if (!hasSearchData) {
            var $gTabCustom = $('.gTabCustom');
            if ($gTabCustom.length && $gTabCustom.hasClass('aOn') && $gTabCustom.text() == '玩过游戏') {
                $allList.find('.noInfo').html('您最近没有玩过游戏，查看<a class="doGTabItem" href="javascript:void(0)">更多游戏</a>').show();
            } else {
                $allList.find('.noInfo').html('未找到游戏信息。').show();
            }
            $gameItemList.hide();
            $allList.find('.scrollBarWrap').hide();
        } else {
            $allList.find('.noInfo').hide();
            $gameItemList.show();
            $allList.find('.scrollBarWrap').show();
            $('.choiceDiv').trigger('updateScrollBar');
        }

        return false;
    },
    // 选择支付渠道处理
    payItem_click: function(){
        var $this = $(this);
        var payDesc = Pay.Center.instance.config.payDesc;
        var payTips = Pay.Center.instance.config.payTips;
        var $moreMod = $this.closest('.moreMod');
        var group = parseInt($this.attr('data-group')) || 0;
        var code  = $this.attr('data-code') || '';
        // 平台币支付先检测是否设置支付密码
        if (!group && code == 'ROOF' && ((!window.UserInfo) || !window.UserInfo.haspaypwd)) {
            // 设置支付密码
            $('#paypop_msg').undelegate('.setPaypwdItem', 'click').delegate('.setPaypwdItem', 'click', function(){
                var kfcall = '';
                if (window.PlatInfo && window.PlatInfo.kfcall) {
                    var callArr = window.PlatInfo.kfcall.replace(/\r/g, '').split("\n");
                    kfcall = callArr[0].replace(/^\s*|\s*$/g, '');
                }
                var kfurl = '';
                if (window.PlatInfo && window.PlatInfo.kfurl) {
                    kfurl = window.PlatInfo.kfurl;
                }

                var htmlArr = [];
                htmlArr.push('<div class="tTip">');
                htmlArr.push('    <h5>请您在打开的密码设置页面完成支付密码设置。</h5>');
                htmlArr.push('    <p>');
                htmlArr.push('        设置完成前请不要关闭或刷新此窗口。<br>');
                htmlArr.push('        设置失败时，可拨打<strong>' + kfcall + '</strong>，或<a href="' + kfurl + '" target="_blank">点击这里</a>联系客服。');
                htmlArr.push('    </p>');
                htmlArr.push('</div>');
                htmlArr.push('<div class="tBtn">');
                htmlArr.push('    <a href="javascript:/*点击选择其他支付方式*/" class="al closePop">选择其他支付方式</a>');
                htmlArr.push('    <a href="javascript:/*点击完成设置*/" class="b1" onclick="window.location.assign(window.location.href);return false;">完成设置</a>');
                htmlArr.push('</div>');
                Pay.Common.showPop(htmlArr.join(''));
            });

            // 先检测是否设置支付密码
            var htmlArr = [];
            htmlArr.push('<div class="tTip">');
            htmlArr.push('    <h5>您还未设置支付密码！<br />为了您的账号安全，请立即设置。</h5>');
            htmlArr.push('</div>');
            htmlArr.push('<div class="tBtn">');
            htmlArr.push('    <a class="al setPaypwdItem" href="/payPwd/" target="_blank">设置支付密码</a>');
            htmlArr.push('    <a class="b1 closePop" href="javascript:/*点击关闭*/">返回充值中心</a>');
            htmlArr.push('</div>');
            Pay.Common.showPop(htmlArr.join(''));
            return false;
        }


        // 清除支付渠道选中状态
        $('.payMod .payItem,.moreSelectItem').removeClass('aOn');

        var lastTarget = $('#lastTarget').val();
        //检测是否在更多里
        if ($moreMod.length) {
            var payName = $this.text() + '<i class="iB"></i>';
            $moreMod.find('.moreSelectItem').attr('data-code', code).html(payName).addClass('aOn');
            $moreMod.removeClass('moreModHover');
            if (lastTarget == 'ROOF') {
                $this.hide().siblings('.payItem').not('.payItem[data-code="ROOF"]').not('.payItem[data-code="PAYPAL"]').show();
            } else {
                $this.hide().siblings('.payItem').show();
            }
        } else {
            if (!$moreMod.length) $moreMod = $('.moreMod');
            if ($moreMod.length) {
                $moreMod.find('.moreSelectItem').attr('data-code', '').html('更多方式<i class="iB"></i>');
                if (lastTarget == 'ROOF') {
                    $moreMod.find('.payItem').not('.payItem[data-code="ROOF"]').not('.payItem[data-code="PAYPAL"]').show();
                } else {
                    $moreMod.find('.payItem').show();
                }
            }
            $this.addClass('aOn');
        }

        // 充值支付，隐藏支付币渠道

        // 分组支付渠道显示处理
        if (!group) {
            $('#lastChannel').attr('data-name', $this.attr('data-name')).val(code);
            if (code && payDesc && payDesc[code]) {
                $('.payTip p:first').html(Pay.Center.Lib.replacePlatData(payDesc[code]));
            } else {
                $('.payTip p:first').html('');
            }
            if (code && payTips && payTips[code]) {
                $('.payTips').html(Pay.Center.Lib.replacePlatData(payTips[code])).show();
            } else {
                $('.payTips').html('').hide();
            }
            $('.amountSelect').trigger('update');
            $('.groupPay').hide();

            if (code.indexOf('E-BANK') > -1) {
                var $bankList = $('.bankList');
                $bankList.show().trigger('scroll');

                // 选择默认银行
                var $selectBank = $bankList.find('.aOn');
                if (!$selectBank.length) {
                    $selectBank = $bankList.find('.bankItem:first');
                }
                $selectBank.trigger('click');
            } else if (code == 'QUYOU-NET') {
                $('.qypayDiv').show();
            } else if (code == 'PAYPAL') {
                $('.payMod').trigger('checkPaypalRecord');
            } else if (code == 'ROOF') {
                var $myBalance = $('.myBalance');
                if ($myBalance.length) $myBalance.show();
            } else if (/^SHENGPAYCARD_.+/.test(code) && code != 'SHENGPAYCARD_WXZF') {
                $('.pay19Div').show();
            }
        } else {
            var $groupPayItem = $('.groupPay_'+group);
            $groupPayItem.show().siblings('.groupPay').hide();

            // 选择默认分组支付渠道，不能触发分组渠道，不然会死循环
            var $selectItem = $groupPayItem.find('.aOn');
            if (!$selectItem.length) {
                $selectItem = $groupPayItem.find('.groupPayItem:first').addClass('aOn');
            }
            var code = $selectItem.attr('data-code') || '';
            var bind_payment = parseInt($selectItem.attr('data-bind_payment')) || 0;
            if ((bind_payment == 6 && Pay.Center.instance.config.switchConfig == '1') || bind_payment == 13) {
                // 19pay
                $('.pay19Div').show();
            }

            $('#lastChannel').attr('data-name', $selectItem.attr('data-name')).val(code);
            if (code && payDesc && payDesc[code]) {
                $('.payTip p:first').html(Pay.Center.Lib.replacePlatData(payDesc[code]));
            } else {
                $('.payTip p:first').html('');
            }
            if (code && payTips && payTips[code]) {
                $('.payTips').html(Pay.Center.Lib.replacePlatData(payTips[code])).show();
            } else {
                $('.payTips').html('').hide();
            }
            $('.amountSelect').trigger('update');
        }

        return false;
    },
    // paypal备案检查
    payMod_checkPaypalRecord: function(){
        var usercode = $.trim($('.usercode').val());
        var serverid = $('#server_id').val() || '-1';
        var lastTarget = $('#lastTarget').val();
        var lastChannel = $('#lastChannel').val();
        if (usercode && serverid && lastTarget == 'GAME' && lastChannel == 'PAYPAL') {
            Pay.Center.Lib.checkPaypalRecord({usercode:usercode, serverid:serverid}, function(result){
                if (!result.status) {
                    if (result.msg.indexOf('尚未申请PayPal') > -1) {
                        result.msg += '<a target="_blank" class="aPay" href="/paypal/">请先申请备案</a>';
                    }
                    $('.paypalRecord').html(result.msg).show();
                } else {
                    //$('.paypalRecord').hide();
                    $('.paypalRecord').html(result.msg).show();
                }
            });
        } else {
            $('.paypalRecord').hide();
        }

        if (!$(this).hasClass('usercode')) {
            return false;
        }
    },
    usercode_keyup: function(){
        var usercode = $(this).val();
        $(this).val(usercode.replace(/[\u4E00-\u9FA5]/g, ''));
        $('.usercodeList').trigger('update');
    },
    usercode_blur: function(){
        var usercode = $(this).val();
        usercode = usercode.replace(/[\u4E00-\u9FA5]/g, '');
        $(this).val(usercode);
        if (usercode && window.UserInfo && window.UserInfo.code && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
            $('.usercode_p .aFor').trigger('click');
        } else {
            $('.usercodeList').trigger('update', 'hide');
            $(this).trigger('checkval');

            var $daijin = $('.daijin');
            if ($daijin.length) $daijin.trigger('update');
        }
    },
    usercode_checkval: function(){
        var $that = $(this);
        // 账号检测
        if (Pay.Center.instance.handleArr.usercode_checkval) {
            clearTimeout(Pay.Center.instance.handleArr.usercode_checkval);
            Pay.Center.instance.handleArr.usercode_checkval = null;
        }
        Pay.Center.instance.handleArr.usercode_checkval = setTimeout(function(){$that.trigger('checkval2');}, 100);
        return false;
    },
    usercode_checkval2: function(){
        var $that = $(this).removeClass('iErr');
        // 账号检测
        var usercode = $that.val();
        var $errmsg = $('.usercode_p .errmsg').removeClass('suc warn').html('');
        if (!usercode) {
            $errmsg.removeClass('suc').addClass('warn').html('请填写您要充值的账号');
            $that.addClass('iErr');
        } else if (/^[a-z_][a-z_0-9]*$/i.test(usercode)) {
            if (usercode.length > 20 || usercode.length < 4) {
                $errmsg.removeClass('suc').addClass('warn').html('您填写的账号有误');
                $that.addClass('iErr');
            }
        } else if (!(/^1(3|5|8|4|7)\d{9}$/.test(usercode)) && !(usercode.indexOf('@')>0)) {
            $errmsg.removeClass('suc').addClass('warn').html('您填写的账号有误');
            $that.addClass('iErr');
        }
        return false;
    },
    usercode_focus: function(){
        // 显示下拉用户列表
        $('.usercodeList').trigger('update');
    },
    usercodeList_update: function(evt, label){
        var $this = $(this);
        if (Pay.Center.instance.handleArr.usercodeList_hide) {
            clearTimeout(Pay.Center.instance.handleArr.usercodeList_hide);
            Pay.Center.instance.handleArr.usercodeList_hide = null;
        }
        if (label == 'hide') {
            Pay.Center.instance.handleArr.usercodeList_hide = setTimeout(function(){$('.usercodeList').hide();}, 200);
        } else {
            var $usercodeListLi = $this.find('.usercodeItem');
            if ($usercodeListLi.length) {
                var $usercode = $('.usercode');
                var usercode = $usercode.val();
                if (usercode.length) {
                    if ($usercode.attr('data-isfocus')) {
                        $this.hide();
                    } else {
                        var hasData = false;
                        $usercodeListLi.each(function(){
                            var $that = $(this);
                            var code = $(this).attr('data-code');
                            if (code && code.indexOf(usercode) > -1) {
                                hasData = true;
                                $that.show();
                            } else {
                                $that.hide();
                            }
                        });
                        if (hasData) {
                            $this.show();
                        } else {
                            $this.hide();
                        }
                    }
                } else {
                    $this.hide();
                }
            } else {
                $this.hide();
            }
        }
    },
    usercodeItem_click: function(){
        var value = $(this).attr('data-code');
        if (value.length) {
            $('.usercodeList').hide();
            $('.usercode').attr('data-isfocus', 1).val(value).trigger('checkval');
            setTimeout(function(){Pay.Ulib.cursorMoveEnd($('.usercode').get(0));}, 0);
            setTimeout(function(){$('.usercode').removeAttr('data-isfocus');}, 10);
        }
        return false;
    },
    // 分组支付渠道选择处理
    groupPayItem_click: function(){
        var $this = $(this);
        var $groupPay = $this.closest('.groupPay');
        $groupPay.find('.groupPayItem').removeClass('aOn');
        $this.addClass('aOn');
        var groupid = parseInt($groupPay.attr('data-group')) || 0;
        $('.payItem[data-group="' + groupid + '"]').trigger('click');

        var code = $this.attr('data-code')||'';
        $('#lastChannel').attr('data-name', $this.attr('data-name')).val(code);
        var payDesc = Pay.Center.instance.config.payDesc;
        var payTips = Pay.Center.instance.config.payTips;
        if (code && payDesc && payDesc[code]) {
            $('.payTip p:first').html(Pay.Center.Lib.replacePlatData(payDesc[code]));
        } else {
            $('.payTip p:first').html('');
        }
        if (code && payTips && payTips[code]) {
            $('.payTips').html(Pay.Center.Lib.replacePlatData(payTips[code])).show();
        } else {
            $('.payTips').html('').hide();
        }
        $('.amountSelect').trigger('update');

        return false;
    },
    // 选择银行列表处理
    bankItem_click: function(){
        var $this = $(this);
        var code = $this.attr('data-code');
        $('.bankList').find('.bankItem').removeClass('aOn');
        $this.addClass('aOn');
        $('#banktype').val(code);
        return false;
    },
    // 显示更多银行
    morebankItem_click: function(){
        var $this = $(this);
        var $bankCon = $('.bankList .bankCont');
        if ($bankCon.hasClass('bankCon')) {
            $bankCon.removeClass('bankCon');
            $this.html('收起更多银行');
        } else {
            $bankCon.addClass('bankCon');
            $this.html('选择更多银行');
        }
        return false;
    },
    // 添加趣游卡
    qypayDivAddItem_click: function(){
        var $this = $(this);
        var $cardDiv = $('.qypayDiv .cardDiv');
        if ($cardDiv.length < 3) {
            var numIdx = 0;
            var $lastCardDiv = null;
            $cardDiv.each(function(i){
                var $this = $(this);
                ++numIdx;
                $this.find('label:eq(0)').html('充值卡号' + numIdx + '：');
                $this.find('label:eq(1)').html('确认密码' + numIdx + '：');
                $lastCardDiv = $this;
            });
            ++numIdx;

            var htmlTpl = [];
            htmlTpl.push('<div class="cardDiv cd">');
            htmlTpl.push('    <p>');
            htmlTpl.push('        <label>充值卡号' + numIdx + '：</label>');
            htmlTpl.push('        <input type="text" class="i2 card_no" maxlength="16" value="" />');
            htmlTpl.push('        <a title="最多支持三张卡充值" alt="最多支持三张卡充值" href="javascript:void(0)" class="add delItem">-移除卡号</a>');
            htmlTpl.push('        <span class="errmsg"></span>');
            htmlTpl.push('    </p>');
            htmlTpl.push('    <p>');
            htmlTpl.push('        <label>确认密码' + numIdx + '：</label>');
            htmlTpl.push('        <input type="password" class="i2 card_pwd" maxlength="16" value="" />');
            htmlTpl.push('        <span class="errmsg"></span>');
            htmlTpl.push('    </p>');
            htmlTpl.push('</div>');
            $lastCardDiv.after(htmlTpl.join(''));

            if (numIdx >= 3) {
                $this.hide();
            } else {
                $this.show();
            }
        } else {
            Pay.Common.showMsg('对不起，最多只能填写3张卡！');
        }
        return false;
    },
    // 删除趣游卡
    qypayDivDelItem_click: function(){
        $(this).closest('.cardDiv').remove();
        // 更新编号
        var $cardDiv = $('.qypayDiv .cardDiv');
        if ($cardDiv.length == 1) {
            $cardDiv.find('label:eq(0)').html('充值卡号：');
            $cardDiv.find('label:eq(1)').html('确认密码：');
        } else {
            var numIdx = 0;
            $cardDiv.each(function(i){
                var $this = $(this);
                ++numIdx;
                $this.find('label:eq(0)').html('充值卡号' + numIdx + '：');
                $this.find('label:eq(1)').html('确认密码' + numIdx + '：');
            });
        }
        $('.qypayDiv .addItem').show();
        return false;
    },
    cardno_blur: function(){
        var $that = $(this);
        var $errmsg = $that.closest('p').find('.errmsg');
        var cardno = $that.val();
        if (cardno.length) {
            if (!/^\d{16}$/.test(cardno)) {
                $errmsg.removeClass('suc').addClass('warn').html('您填写的趣游一卡通卡号有误');
                $that.addClass('iErr');
            } else {
                $errmsg.removeClass('suc warn').html('');
                $that.removeClass('iErr');
            }
        } else {
            $errmsg.removeClass('suc').addClass('warn').html('请填写趣游一卡通卡号');
            $that.addClass('iErr');
        }
    },
    cardpwd_blur: function(){
        var $that = $(this);
        var $errmsg = $that.closest('p').find('.errmsg');
        var cardno = $that.val();
        if (cardno.length) {
            if (!/^\d{16}$/.test(cardno)) {
                $errmsg.removeClass('suc').addClass('warn').html('您填写的趣游一卡通密码有误');
                $that.addClass('iErr');
            } else {
                $errmsg.removeClass('suc warn').html('');
                $that.removeClass('iErr');
            }
        } else {
            $errmsg.removeClass('suc').addClass('warn').html('请填写趣游一卡通密码');
            $that.addClass('iErr');
        }
    },
    cardno2_blur: function(){
        var $that = $(this);
        var $errmsg = $that.closest('p').find('.errmsg');
        var cardno = $that.val();
        if (cardno.length) {
            if (!/^[a-zA-Z0-9]{14,}$/.test(cardno)) {
                $errmsg.removeClass('suc').addClass('warn').html('您填写的充值卡号有误');
                $that.addClass('iErr');
            } else {
                $errmsg.removeClass('suc warn').html('');
                $that.removeClass('iErr');
            }
        } else {
            $errmsg.removeClass('suc').addClass('warn').html('请填写充值卡号');
            $that.addClass('iErr');
        }
    },
    cardpwd2_blur: function(){
        var $that = $(this);
        var $errmsg = $that.closest('p').find('.errmsg');
        var cardno = $that.val();
        if (cardno.length) {
            if (!/^[a-zA-Z0-9]{8,}$/.test(cardno)) {
                $errmsg.removeClass('suc').addClass('warn').html('您填写的充值卡密码有误');
                $that.addClass('iErr');
            } else {
                $errmsg.removeClass('suc warn').html('');
                $that.removeClass('iErr');
            }
        } else {
            $errmsg.removeClass('suc').addClass('warn').html('请填写充值卡密码');
            $that.addClass('iErr');
        }
    },

    showDaijinCont_click: function(){
        var $this = $(this);
        var $daijinCont = $('.daijinCont');
        if ($daijinCont.css('display') == 'none') {
            $this.addClass('aOn');
            $this.attr('alt', '取消').attr('title', '取消');
            $daijinCont.show();
            if ($daijinCont.hasClass('noLog')) {
                setTimeout(function(){$('.enterDaijinInput').focus();},0);
            }
        } else {
            $this.removeClass('aOn');
            $this.attr('alt', '使用').attr('title', '使用');
            $daijinCont.hide();
        }
        return false;
    },
    daijinItem_click: function(){
        var $this = $(this);
        var $daijinCont = $this.closest('.daijinCont');
        if ($this.hasClass('daijinEnterItem')) {
            // 手动输入
            var $jinCheck = $daijinCont.find('.jinCheck');
            $jinCheck.show();
            $this.addClass('aOn').siblings('.daijinItem').removeClass('aOn');
            $('#daijin_amount').val($jinCheck.attr('data-amount')||'').attr('data-only_first', $jinCheck.attr('data-only_first')||'').attr('data-voucher_limit', $jinCheck.attr('data-voucher_limit')||'');
            $('#daijin_code').val($jinCheck.attr('data-code')||'');
            setTimeout(function(){$('.enterDaijinInput').focus();}, 0);
        } else {
            // 检测是否给自己使用
            var usercode = $('.usercode').val();
            if (!usercode) {
                Pay.Common.showMsg('请先填写用户账号。');
                return false;
            }
            if (usercode != $('.usercode_p .aFor').attr('data-code')) {
                Pay.Common.showMsg('代金券只适用于其绑定账号充值使用。');
                return false;
            }
            var gameid = parseInt($('.selGameBtn').attr('data-gameid')) || 0;
            if (!gameid) {
                Pay.Common.showMsg('请先选择游戏。');
                return false;
            }

            // 检测代金券订单金额
            var amount = parseInt($('#amount').val()) || 0;
            var voucher_limit = parseInt($this.attr('data-voucher_limit')) || 0;
            if (voucher_limit > 0 && voucher_limit > amount) {
                Pay.Common.showMsg('订单金额满' + voucher_limit + '元可用');
                return false;
            }

            $daijinCont.find('.jinCheck').hide();

            var gameids = $this.attr('data-gameids');
            if (gameids && gameid && (gameids.indexOf('0') > -1 || gameids.indexOf(gameid) > -1)) {
                $this.addClass('aOn').siblings('.daijinItem').removeClass('aOn');
                $('#daijin_amount').val($this.attr('data-amount')).attr('data-only_first', $this.attr('data-only_first')||'').attr('data-voucher_limit', $this.attr('data-voucher_limit')||'');
                $('#daijin_code').val($this.attr('data-code'));
            } else {
                $this.find('.daijinItem').removeClass('aOn');
                $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
                $('#daijin_code').val('');
                Pay.Common.showMsg('该代金券不能在此游戏使用。');
            }
        }
        return false;
    },
    enterDaijinBtn_click: function(){
        var $daijinCont = $(this).closest('.daijinCont');
        var $input  = $daijinCont.find('.enterDaijinInput');
        var $msg    = $daijinCont.find('.enterDaijinMsg');
        var value = $input.val();
        var djReg = /^(M|L)[A-Za-z0-9]{15}$/i;

        // 检测代金券格式
        if ((!value) && !djReg.test(value)) {
            $msg.html('代金券有误，请重新填写！');
            setTimeout(function(){$('.enterDaijinInput').focus();}, 0);
            return false;
        }

        // 检测代金券使用条件
        var usercode = $('.usercode').val();
        if (!usercode) {
            $msg.html('请先填写充值账号！');
            return false;
        }

        var serverid = $('#server_id').val();
        if (!serverid) {
            $msg.html('请先选择区服！');
            return false;
        }

        var lastTarget = $('#lastTarget').val();
        if ((!lastTarget) && lastTarget == 'ROOF') {
            $msg.html('平台充值不允许使用！');
            return false;
        }

        var lastChannel = $('#lastChannel').val();
        if (!lastChannel) {
            $msg.html('请先选择渠道！');
            return false;
        }

        var amount = $('#amount').val();

        // 检测该渠道是否允许使用代金券
        var daijinChannelInfo = [0, 0];
        $('.payItem[data-code="'+lastChannel+'"]').each(function(){
            var enable_voucher = $(this).attr('data-enable_voucher') == '0' || false;
            var voucher_limit  = parseInt($(this).attr('data-voucher_limit')) || 0;
            if (enable_voucher && voucher_limit > 0) {
                daijinChannelInfo = [enable_voucher, voucher_limit];
            }
        });
        if ((!daijinChannelInfo[0]) && (!daijinChannelInfo[1])) {
            $('.groupPayItem[data-code="'+lastChannel+'"]').each(function(){
                var enable_voucher = $(this).attr('data-enable_voucher') == '0' || false;
                var voucher_limit  = parseInt($(this).attr('data-voucher_limit')) || 0;
                if (enable_voucher && voucher_limit > 0) {
                    daijinChannelInfo = [enable_voucher, voucher_limit];
                }
            });
            if ((!daijinChannelInfo[0]) && (!daijinChannelInfo[1])) {
                $msg.html('该渠道暂不支持使用代金券！');
                return false;
            }
        }

        $msg.html('');
        Pay.Center.Lib.checkDjCode({userCode:usercode, serverId:serverid, daijinCode:value, amount:amount}, function(result){
            if (result.status) {
                result.only_first = result.only_first ? parseInt(result.only_first)||0 : 0;
                result.voucher_limit = result.voucher_limit ? parseInt(result.voucher_limit)||0 : 0;
                var htmlArr = [];
                htmlArr.push('<strong>' + Pay.Ulib.encodeHtml(value) + (result.only_first ? '<font style="color:red;">(首)</font>' : '') + '</strong>');
                htmlArr.push('<button class="modDaijinBtn">修改</button>');
                htmlArr.push('<span class="enterDaijinMsg">' + result.amount + '元代金券</span>');
                $daijinCont.find('.jinCheck').attr('data-code', value).attr('data-usercode', usercode).attr('data-amount', result.amount).attr('data-only_first', result.only_first).attr('data-voucher_limit', result.voucher_limit).html(htmlArr.join(''));

                // 清除代金券
                $('#daijin_amount').val(result.amount).attr('data-only_first', result.only_first).attr('data-voucher_limit', result.voucher_limit);
                $('#daijin_code').val(value);
            } else {
                $msg.html(result.msg);
            }
            return false;
        });
        return false;
    },
    modDaijinBtn_click: function(evt, label){
        var $jinCheck = $(this).closest('.jinCheck');
        var code = $jinCheck.find('strong').text().replace('(首)', '');
        var htmlArr = [];
        htmlArr.push('<div class="jinCheck">');
        htmlArr.push('    <input type="text" class="i2 enterDaijinInput" value="' + Pay.Ulib.encodeHtml(code) + '" />');
        htmlArr.push('    <button class="enterDaijinBtn">确定</button>');
        htmlArr.push('    <span class="enterDaijinMsg"></span>');
        htmlArr.push('</div>');
        $jinCheck.replaceWith(htmlArr.join(''));
        // 清除代金券
        $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
        $('#daijin_code').val('');
        if ((!label) || label != 'nostate') {
            setTimeout(function(){Pay.Ulib.cursorMoveEnd($jinCheck.find('.enterDaijinInput').get(0));}, 0);
        }
        return false;
    },
    daijin_update: function(evt, amount){
        // 显示代金券: 充值到游戏,充值渠道,充值金额
        var lastTarget  = $('#lastTarget').val();
        var lastChannel = $('#lastChannel').val();
        var $payItem = $('.payItem[data-code="' + lastChannel + '"]');
        if (!$payItem.length) $payItem = $('.groupPayItem[data-code="' + lastChannel + '"]');
        amount = typeof(amount) == 'undefined' ? parseInt($('#amount').val()) : parseInt(amount);
        if (amount > 0) {
            // 检测当前是否允许代金券
            if (lastTarget == 'GAME' && $payItem.length && $payItem.attr('data-enable_voucher') == '0') {
                var limitValue = parseInt($payItem.attr('data-voucher_limit'));
                if (limitValue <= amount) {
                    // 隐藏不符合条件的代金券
                    var usercode = $('.usercode').val();
                    if (usercode && window.UserInfo && (window.UserInfo.code == usercode || window.UserInfo.showcode == usercode)) {
                        // 筛选游戏
                        var gameid = parseInt($('.selGameBtn').attr('data-gameid')) || 0;
                        var $daijinItems = $('.daijinList .daijinItem').not('.daijinEnterItem');
                        if ($daijinItems.length) {
                            $daijinItems.each(function(){
                                var $that = $(this);
                                var gameIds = ',' + $that.attr('data-gameIds') + ',';
                                var voucher_limit = parseInt($that.attr('data-voucher_limit')) || 0;
                                if ((gameIds.indexOf(',0,') > -1 || gameIds.indexOf(','+gameid+',') > -1) && amount >= voucher_limit) {
                                    $that.show();
                                } else {
                                    $that.hide();
                                    if ($that.hasClass('aOn')) {
                                        $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
                                        $('.daijin_code').val('');
                                    }
                                }
                            });
                        }
                    } else {
                        // 仅显示输入
                        var $daijinItems = $('.daijinList .daijinItem').not('.daijinEnterItem');
                        if ($daijinItems.length) {
                            $daijinItems.hide();
                            if ($daijinItems.hasClass('aOn')) {
                                $daijinItems.removeClass('aOn');
                                $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
                                $('.daijin_code').val('');
                            }
                        }
                    }

                    var $modDaijinBtn = $('.jinCheck .modDaijinBtn');
                    if ($modDaijinBtn.length) {
                        $modDaijinBtn.trigger('click', 'nostate');
                    }
                    $('.daijin').show();
                } else {
                    $('.daijin').hide();
                    $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
                    $('.daijin_code').val('');
                }
            } else {
                $('.daijin').hide();
                $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
                $('.daijin_code').val('');
            }
        } else {
            $('.daijin').hide();
            $('#daijin_amount').val('').attr('data-only_first', '').attr('data-voucher_limit', '');
            $('.daijin_code').val('');
        }

        return false;
    },
    // 管理更新套餐列表，防止同一时间更新多次
    amountSelect_update: function(){
        var $that = $(this);
        if (Pay.Center.instance.chargeSet_handle) {
            clearTimeout(Pay.Center.instance.chargeSet_handle);
            Pay.Center.instance.chargeSet_handle = null;
        }
        Pay.Center.instance.chargeSet_handle = setTimeout(function(){$that.trigger('update2');}, 20);
        return false;
    },
    // 实时更新套餐列表
    amountSelect_update2: function(){
        var $this = $(this);
        $('.payRmb .errmsg').removeClass('suc warn').html('');

        $('#amount').val('');
        // 清空套餐
        var clearAmount = function(){
            var htmlArr = ['<li><a data-value="" href="javascript:void(0)">选择套餐</a></li>'];
            $('.pay_point_p').hide();
            $this.find('ul').html(htmlArr.join('')).find('li:first a').trigger('click');
            Pay.Center.instance.chargeSet = undefined;
            var $daijin = $('.daijin');
            if ($daijin.length) $daijin.trigger('update', 0);
        };
        // 充值到哪里
        var lastTarget = $('#lastTarget').val();
        // 充值方式
        var lastChannel = $('#lastChannel').val();
        if (!lastTarget || !lastChannel) {
            clearAmount();
            return false;
        }

        // 若充值到游戏，需选择游戏区服
        var serverId = $('#server_id').val();
        if (!serverId && lastTarget == 'GAME') {
            clearAmount();
            return false;
        }
        var gameId = parseInt($('.selGameBtn').attr('data-gameid')) || 0;

        var maxCharge = parseInt(Pay.Center.instance.config.maxCharge) || 99.9;
        // 默认先中之前已选择好的套餐
        var amount = parseInt($('#amount').val()) || 0;
        if (maxCharge < amount) maxCharge = amount - 0.01;

        Pay.Center.Lib.getChargeSet({paytarget: lastTarget, paychannel:lastChannel, gameId:gameId, serverId:serverId, maxCharge:maxCharge}, function(result){
            if (result.status) {
                Pay.Center.instance.chargeSet = result;

                //var htmlArr = ['<li><a data-value="" href="javascript:void(0)">选择套餐</a></li>'];
                var htmlArr = [];
                var data = result.data, selectedFlag=false;
                //10RMB兑换100元宝
                for (var i=0,len=data.length; i<len; i++) {
                    var item = data[i];
                    if ((!selectedFlag) && (item[0] > maxCharge || i == (len-1))) {
                        selectedFlag = true;
                        htmlArr.push('<li class="selected"><a href="javascript:void(0);" data-value="' + item[0] + '" data-point="' + item[1] + '" data-gcoin="' + result.gcoin + '" selected="selected">' + item[0] + result.pcoin + '兑换' + item[1] + result.gcoin + '</a></li>');
                    } else {
                        htmlArr.push('<li><a href="javascript:void(0);" data-value="' + item[0] + '" data-point="' + item[1] + '" data-gcoin="' + result.gcoin + '" selected="selected">' + item[0] + result.pcoin + '兑换' + item[1] + result.gcoin + '</a></li>');
                    }
                }
                $this.find('ul').html(htmlArr.join(''));;

                // 选择默认套餐
                var $selectedItem = $this.find('.selected a');
                if (!$selectedItem.length) {
                    $selectedItem = $this.find('li:first a');
                }
                $selectedItem.trigger('click');
                var $daijin = $('.daijin');
                if ($daijin.length) $daijin.trigger('update', parseInt($selectedItem.attr('data-value')) || 0);
            } else {
                // 清空套餐
                clearAmount();
                Pay.Common.showMsg('系统繁忙！');
            }
        });

        return false;
    },
    amountSelect_click: function(){
        var $this = $(this);
        var $li = $this.parent().find('li');
        if ($li.length <= 1) {
            var serverId = $('#server_id').val();
            var lastTarget = $('#lastTarget').val();
            if (lastTarget == 'GAME' && !serverId) {
                var gameid = parseInt($('.selGameBtn').attr('data-gameid')) || 0;
                if (gameid > 0) {
                    $('.payRmb .errmsg').removeClass('suc').addClass('warn').html('请选择您要充值的区服');
                } else {
                    $('.payRmb .errmsg').removeClass('suc').addClass('warn').html('请选择您要充值的游戏');
                }
                return false;
            }

            var lastChannel = $('#lastChannel').val();
            if (!lastChannel) {
                $('.payRmb .errmsg').removeClass('suc').addClass('warn').html('请选择充值方式');
                return false;
            }
        }
        $('.payRmb .errmsg').removeClass('suc warn').html('');
    },
    amountSelect_change: function(evt, value){
        var $this = $(this);
        if (value) {
            // 若从平台充出，检测余额是否足够
            var $item = $(this).find('.selectCon a[data-value="'+value+'"]');
            if ($item.length) {
                var point = $item.attr('data-point');
                var gcoin  = $item.attr('data-gcoin');
                var $pay_point_p = $('.pay_point_p');
                $pay_point_p.find('strong').html(point);
                $pay_point_p.find('i').html(gcoin);
                $('.pay_point_p').show();
                $this.find('.selectMain').addClass('aOn');
            } else {
                return false;
            }
        } else {
            $('.pay_point_p').hide();
            $this.find('.selectMain').removeClass('aOn');
        }

        var $daijin = $('.daijin');
        if ($daijin.length) $daijin.trigger('update', value);
        return false;
    },
    userphone_keyup: function(){
        var value = $(this).val();
        $(this).val(value.replace(/\D/g, ''));
    },
    userphone_blur: function(){
        var $that = $(this);
        var $errmsg = $that.closest('p').find('.errmsg');
        var userphone = $that.val();
        var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
        if (userphone && !phoneReg.test(userphone)) {
            $errmsg.removeClass('suc').addClass('warn').html('请填写正确的手机号');
            $that.addClass('iErr');
        } else {
            $errmsg.removeClass('suc warn').html('建议填写，以便在遇到问题时我们能及时与您沟通');
            $that.removeClass('iErr');
        }
        $(this).removeClass('iOn');
    },
    // 表单确认处理
    submiBtn_click: function(){
        if (Pay.Center.instance.handleArr.submiBtn_lock) return false;
        Pay.Center.instance.handleArr.submiBtn_lock = true;
        // 按钮锁定，防止弹窗出现前点击多次
        var setTimeoutUnlock = function(){
            if (Pay.Center.instance.handleArr.submiBtn_lock_handle) {
                clearTimeout(Pay.Center.instance.handleArr.submiBtn_lock_handle);
                Pay.Center.instance.handleArr.submiBtn_lock_handle = null;
            }
            Pay.Center.instance.handleArr.submiBtn_lock = false;
        };
        Pay.Center.instance.handleArr.submiBtn_lock_handle = setTimeout(function(){setTimeoutUnlock();}, 5000);  // 5秒后自动解锁

        var usercheck = parseInt($('#user_check').val()) || 0;
        if (usercheck) {
            // 获取订单数据
            var postData = Pay.Center.Lib.getOrderData();
            // 确认订单
            Pay.Common.Lib.submitPay.comfirmOrder(postData);
            // 清除解锁定时器
            setTimeoutUnlock();
        } else {
            // 防止提交时，checkUser的请求未返回，导致订单检测不通过
            $('.usercode_p:first').trigger('checkUser', function(){
                // 获取订单数据
                var postData = Pay.Center.Lib.getOrderData();
                // 确认订单
                Pay.Common.Lib.submitPay.comfirmOrder(postData);
                // 清除解锁定时器
                setTimeoutUnlock();
            });
        }
        return false;
    }

});
//}}}


// 初始化
$(document).ready(function(){
    var pay_center_config = window.pay_center_config || {};

    var forceGame = Pay.Common.Lib.getForceGame();
    if (forceGame) {
        pay_center_config.selectedGameId = forceGame.info.id;
        pay_center_config.selectedServerArea = forceGame.server.area;
    }

    new Pay.Center(pay_center_config);
});

