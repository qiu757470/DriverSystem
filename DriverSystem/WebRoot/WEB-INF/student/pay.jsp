<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0024)http://pay.game2.cn/pay/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>在线支付</title>
	<meta name="keywords" content="充值中心,帐户充值,游戏充值,支付中心，汇款充值,帐户管理">
		<meta name="description" content="充值中心,帐户充值,游戏充值,支付中心，汇款充值,帐户管理">
			<link rel="shortcut icon"
				href="http://pay.game2.cn/images/game2/favicon.ico"
				type="pay/image/vnd.microsoft.icon">
				<link rel="icon" href="http://pay.game2.cn/images/game2/favicon.ico"
					type="pay/image/vnd.microsoft.icon">
					<link href="pay/global.css" type="text/css"
						rel="stylesheet">
						<link href="pay/pay.css" type="text/css"
							rel="stylesheet">
							<link href="pay/tan.css" type="text/css"
								rel="stylesheet">

								<script type="text/javascript">
    window.PlatInfo = {
        id: 1,
        name: '学生平台',
        code: 'game2',
        domain: '.game2.cn',
        site: 'http://www.game2.cn/',
        register: 'http://www.game2.cn/register/',
        getpwd: 'http://pay.game2.cn/payPwd/',
        passport: 'http://pay.game2.cn/',
        interface: '/api/',
        currencyname: 'G宝',
        currencyscale: '1',
        kfcall: "<a class=\"c2\" href=\"http:\/\/www.game2.cn\/serverCenter\/\" target=\"_blank\">400-9933-555<\/a>",
        kfurl: 'http://chat.53kf.com/webCompany.php?arg=xmkf&style=1',
        gamecate: true,
        seccode: true    };

    </script>
								<script type="text/javascript"
									src="pay/jquery-1.7.2.min.js"></script>
								<script type="text/javascript"
									src="pay/md5.js"></script>
								<script type="text/javascript"
									src="pay/J.OtherLogin.js"></script>
								<script type="text/javascript"
									src="pay/Pay.Devel.js"></script>
								<script type="text/javascript"
									src="pay/Pay.Common.js"></script>
</head>

<body>
	<div class="bg01">
		<div class="wrap">
			<div class="header">
				<div class="uc">
					<div class="login" style="display: none">
						<div class="logA">
							<span>您还未登录，未登录也可充值哦！</span> <a class="a1 ajax_login2"
								href="javascript:void(0)">登录</a> <a class="a2"
								href="http://www.game2.cn/register/" target="_blank">注册</a>
						</div>
						<div class="logDrag hLoginWrap"
							style="display: none; top: -270px;">
							<div class="Tlogin">
								<form class="ajax_login2_form" method="post">
									<p class="pWarn" style="display:block;"></p>
									<p id="p1">
										<label>通行证：</label> <span class="placeholder">账号</span><input
											class="i ii" name="code" type="text" value="" tabindex="1"
											data-placeholder="账号"> <a class="del"
											href="javascript:void(0)" style="display:none;">清除</a>
									</p>
									<p id="p2">
										<label>密&nbsp;&nbsp;码：</label> <span class="placeholder">密码</span><input
											class="i" name="password" type="password" tabindex="2"
											value="" data-placeholder="密码">
									</p>
									<p id="validate">
										<label>验证码：</label> <span class="placeholder">验证码</span><input
											class="i ii" name="vcode" type="text" value="" tabindex="3"
											data-placeholder="验证码"> <img class="vcodeItem"
											width="64" height="32"
											src="pay/verifyCode.php" title="点击刷新验证码"
											alt="点击刷新验证码">
									</p>
									<p id="pCheck">
										<input type="checkbox" checked="checked" value="1"
											name="keeplive" style="display: none;"> <label
											class="checkbox checked checkboxItem" data-name="keeplive"
											data-checkedcls="checked">记住账号</label> <a
											href="http://pay.game2.cn/payPwd/" target="_blank">找回密码</a>
									</p>
									<p class="btn">
										<button type="submit" tabindex="4">立即登录</button>
									</p>
									<div class="otherLogin"
										data-otherlogin-config="{&quot;remark&quot;:&quot;::http%3A%2F%2Fwww.game2.cn&quot;}"
										data-init="1">
										<span class="otherLoginLabel">其他登录方式：</span>
										<div class="third">
											<a href="javascript:void(0)" title="微信登录" data-code="weixin"
												class="otherLoginItem icon3 weixin"></a><a
												href="javascript:void(0)" title="QQ登录" data-code="qq"
												class="otherLoginItem icon3 qq"></a><a
												href="javascript:void(0)" title="微博登录" data-code="weibo"
												class="otherLoginItem icon3 weibo"></a><a
												href="javascript:void(0)" title="支付宝登录" data-code="alipay"
												class="otherLoginItem icon3 alipay"></a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="logo" style="display: none">
					<a href="http://www.game2.cn/" target="_blank"><img
						src="pay/payLogo.jpg" width="155" height="52"></a>
					<h2>充值中心</h2>
				</div>

			</div>

			<div class="bigBox clearfix">


				<div class="payTab">
					<a class="aOn" href="javascript:void(0)">在线支付</a> <a
						href="http://pay.game2.cn/payoffline/">汇款支付</a>
				</div>

				<div class="payStep">
					<input type="hidden" id="server_id" value=""> <input
						type="hidden" id="user_check" name="user_check" value="">
							<input type="hidden" id="gcodeid" name="gcodeid" value="">

								<div class="nameDiv">
									<p class="usercode_p">
										<label>充值账号：</label> <input class="i2 usercode" type="text"
											name="usercode" value="" autocomplete="off"> <span
											class="errmsg"></span>
									</p>

									<div class="nameDrag usercodeList" style="display:none;">
										<ul></ul>
									</div>

								</div>
								<p class="lastTarget_p">
									<label>充值类型：</label> <a class="lastTargetItem isA aOn"
										href="javascript:void(0)" data-value="GAME">充值游戏<i
										class="iB"></i></a> <input type="hidden" id="lastTarget"
										name="lastTarget" value="GAME">
								</p>

								<div class="lastTargetCon lastTargetCon_ROOF choiceBox"
									style="display:none;">
									<div class="choiceT">
										<strong>充值G宝：</strong>
										<div class="gDiv">
											充值到平台可获得平台通用货币：G宝，充值无限额。 <em>1G宝 = 1元人民币</em>
										</div>
									</div>
								</div>

								


								</div>

								<dl class="payBank clearfix">
									<dt>支付方式：</dt>
									<dd>
										<div class="payMod">


											<a class="payItem isA singlePayItem aOn"
												href="javascript:void(0)" data-group="0" data-id="0"
												data-code="E-BANK" data-name="网上银行" data-bind_payment="2"
												data-enable_voucher="0" data-voucher_limit="10">网上银行<i
												class="iB"></i> <i class="iA">推荐</i>
											</a> <a class="payItem isA" href="javascript:void(0)"
												data-group="3" data-id="3" data-code="GALIPAY"
												data-name="支付宝" data-bind_payment="" data-enable_voucher=""
												data-voucher_limit="">支付宝<i class="iB"></i>
											</a> <a class="payItem isA singlePayItem"
												href="javascript:void(0)" data-group="0" data-id="40"
												data-code="SWIFTPASSCIB_WXPAY" data-name="微信扫码"
												data-bind_payment="16" data-enable_voucher="0"
												data-voucher_limit="10">微信扫码<i class="iB"></i>
											</a> <a class="payItem isA singlePayItem"
												href="javascript:void(0)" data-group="0" data-id="31"
												data-code="NETPAY" data-name="银联支付" data-bind_payment="11"
												data-enable_voucher="1" data-voucher_limit="0">银联支付<i
												class="iB"></i>
											</a> <a class="payItem isA" href="javascript:void(0)"
												data-group="1" data-id="1" data-code="PHONE"
												data-name="手机充值卡" data-bind_payment=""
												data-enable_voucher="" data-voucher_limit="">手机充值卡<i
												class="iB"></i>
											</a>







											<div class="moreMod">
												<a class="moreSelectItem isA2" href="javascript:void(0)"
													data-code="more">更多方式<i class="iB"></i></a>
												<div class="moreDiv">
													<a class="payItem singlePayItem" href="javascript:void(0)"
														data-group="0" data-id="14" data-code="QUYOU-NET"
														data-name="趣游一卡通" data-bind_payment="3"
														data-enable_voucher="1" data-voucher_limit="0">趣游一卡通<i
														class="iB"></i></a> <a class="payItem singlePayItem"
														href="javascript:void(0)" data-group="0" data-id="11"
														data-code="UMPAY" data-name="短信" data-bind_payment="5"
														data-enable_voucher="1" data-voucher_limit="0">短信<i
														class="iB"></i></a> <a class="payItem singlePayItem"
														href="javascript:void(0)" data-group="0" data-id="22"
														data-code="VNET" data-name="声讯电话" data-bind_payment="4"
														data-enable_voucher="1" data-voucher_limit="0">声讯电话<i
														class="iB"></i></a> <a class="payItem singlePayItem"
														href="javascript:void(0)" data-group="0" data-id="12"
														data-code="HEEPAY" data-name="骏网一卡通" data-bind_payment="7"
														data-enable_voucher="1" data-voucher_limit="0">骏网一卡通<i
														class="iB"></i></a> <a class="payItem singlePayItem"
														href="javascript:void(0)" data-group="0" data-id="16"
														data-code="PAYPAL" data-name="paypal支付"
														data-bind_payment="8" data-enable_voucher="1"
														data-voucher_limit="0">paypal支付<i class="iB"></i></a> <a
														class="payItem singlePayItem" href="javascript:void(0)"
														data-group="0" data-id="36" data-code="SHENGPAYCARD_SNDA"
														data-name="盛大卡" data-bind_payment="13"
														data-enable_voucher="1" data-voucher_limit="0">盛大卡<i
														class="iB"></i></a>
												</div>
											</div>

										</div>

										<div class="payIs payTips">
											您当前选择的是“<strong>网上银行</strong>”支付方式，同时支持储存卡和信用卡：
										</div>

										<div class="phonePay groupPay groupPay_3" data-group="3"
											style="display:none;">
											<ul>

												<li><a class="groupPayItem singlePayItem aOn"
													href="javascript:void(0)" data-id="1" data-code="ALIPAY"
													data-bind_payment="1" data-name="支付宝"
													data-enable_voucher="0" data-voucher_limit="10"> <img
														src="pay/ALIPAY.jpg" width="125"
														height="30"><var></var></a></li>
												<li><a class="groupPayItem singlePayItem"
													href="javascript:void(0)" data-id="44"
													data-code="ULINE_ALIPAY" data-bind_payment="17"
													data-name="支付宝扫码" data-enable_voucher="1"
													data-voucher_limit="0"> <img
														src="pay/ULINE_ALIPAY.jpg" width="125"
														height="30"><var></var></a></li>
											</ul>
										</div>
										<div class="phonePay groupPay groupPay_1" data-group="1"
											style="display:none;">
											<ul>

												<li><a class="groupPayItem singlePayItem aOn"
													href="javascript:void(0)" data-id="34"
													data-code="SHENGPAYCARD_UC" data-bind_payment="13"
													data-name="联通卡" data-enable_voucher="1"
													data-voucher_limit="0"> <img
														src="pay/SHENGPAYCARD_UC.jpg"
														width="125" height="30"><var></var></a></li>
												<li><a class="groupPayItem singlePayItem"
													href="javascript:void(0)" data-id="33"
													data-code="SHENGPAYCARD_CM" data-bind_payment="13"
													data-name="移动卡" data-enable_voucher="1"
													data-voucher_limit="0"> <img
														src="pay/SHENGPAYCARD_CM.jpg"
														width="125" height="30"><var></var></a></li>
												<li><a class="groupPayItem singlePayItem"
													href="javascript:void(0)" data-id="35"
													data-code="SHENGPAYCARD_CT" data-bind_payment="13"
													data-name="电信卡" data-enable_voucher="1"
													data-voucher_limit="0"> <img
														src="pay/SHENGPAYCARD_CT.jpg"
														width="125" height="30"><var></var></a></li>
											</ul>
										</div>

										<div class="groupPay bankList">
											<div class="bankCont bankCon">
												<ul class="lazyloadbg clearfix">
													<li><a class="bankItem aOn" href="javascript:void(0)"
														data-code="ICBCNETB2C" data-name="中国工商银行"><img
															src="pay/ICBCNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="CCBNETB2C" data-name="中国建设银行"><img
															src="pay/CCBNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="CMBCHINANETB2C" data-name="招商银行"><img
															src="pay/CMBCHINANETB2C.jpg"
															width="125" height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="ABCNETB2C" data-name="中国农业银行"><img
															src="pay/ABCNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="BOCNETB2C" data-name="中国银行"><img
															src="pay/BOCNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="BOCONETB2C" data-name="交通银行"><img
															src="pay/BOCONETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="CEBNETB2C" data-name="中国光大银行"><img
															src="pay/CEBNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="GDBNETB2C" data-name="广发银行"><img
															src="pay/GDBNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="POSTNETB2C" data-name="中国邮政"><img
															src="pay/POSTNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="ECITICNETB2C" data-name="中信银行"><img
															src="pay/ECITICNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="CIBNETB2C" data-name="兴业银行"><img
															src="pay/CIBNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="SPDBNETB2C" data-name="浦发银行"><img
															src="pay/SPDBNETB2C.jpg" width="125"
															height="30"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="CMBCNETB2C" data-name="中国民生银行"><img
															class="lazyLoadPlugin"
															src="pay/CMBCNETB2C.jpg"
															data-original="/images/commom/bank/CMBCNETB2C.jpg"
															width="125" height="30" style="display: inline;"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="PINGANBANKNET" data-name="平安银行"><img
															class="lazyLoadPlugin"
															src="pay/PINGANBANKNET.jpg"
															data-original="/images/commom/bank/PINGANBANKNET.jpg"
															width="125" height="30" style="display: inline;"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="SDBNETB2C" data-name="深圳发展银行"><img
															class="lazyLoadPlugin"
															src="pay/SDBNETB2C.jpg"
															data-original="/images/commom/bank/SDBNETB2C.jpg"
															width="125" height="30" style="display: inline;"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="SHBNETB2C" data-name="上海银行"><img
															class="lazyLoadPlugin"
															src="pay/SHBNETB2C.jpg"
															data-original="/images/commom/bank/SHBNETB2C.jpg"
															width="125" height="30" style="display: inline;"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="BCCBNETB2C" data-name="北京银行"><img
															class="lazyLoadPlugin"
															src="pay/BCCBNETB2C.jpg"
															data-original="/images/commom/bank/BCCBNETB2C.jpg"
															width="125" height="30" style="display: inline;"><var></var></a></li>
													<li><a class="bankItem" href="javascript:void(0)"
														data-code="MOREBANK" data-name="更多银行"><img
															src="pay/MOREBANK.jpg" width="125"
															height="30"><var></var></a></li>
												</ul>
											</div>
											<p>
												<a href="javascript:void(0)" class="moreBankItem">选择更多银行</a>
											</p>
											<input type="hidden" value="ICBCNETB2C" id="banktype"
												name="banktype">
										</div>

										<div class="groupPay qyCard qypayDiv" style="display:none;">
											<div class="cardDiv cd">
												<p>
													<label>充值卡号：</label> <input type="text" value=""
														maxlength="16" class="i2 card_no"> <a
														class="addItem add" href="javascript:void(0)"
														alt="最多支持三张卡充值" title="最多支持三张卡充值">+添加卡号</a> <span
														class="errmsg"></span>
												</p>
												<p>
													<label>确认密码：</label> <input type="password" maxlength="16"
														class="i2 card_pwd"> <span class="errmsg"></span>
												</p>
											</div>
											<div class="yu">
												<a href="http://pay.game2.cn/cardQuery/" target="_blank">点击查询卡内余额&gt;&gt;</a>
											</div>
										</div>

										<div class="groupPay qyCard pay19Div" style="display:none;">
											<div class="cardDiv cd">
												<p>
													<label>充值卡号：</label> <input type="text" value=""
														class="i2 card_no"> <span class="errmsg"></span>
												</p>
												<p>
													<label>确认密码：</label> <input type="password"
														class="i2 card_pwd"> <span class="errmsg"></span>
												</p>
											</div>
										</div>

										<div class="groupPay paypalRecord" style="display:none;">
											如果您尚未申请PayPal备案，<a target="_blank" class="aPay"
												href="http://pay.game2.cn/paypal/">请先申请备案</a>！
										</div>


										<input type="hidden" id="lastChannel" name="lastChannel"
											value="E-BANK" data-name="网上银行"> <input type="hidden"
											id="lastChannelVoucher" name="lastChannelVoucher" value="0">
												<input type="hidden" id="lastChannelVoucherLimit"
												name="lastChannelVoucherLimit" value="10">
									</dd>
								</dl>


								<dl class="payRmb clearfix">
									<dt>充值套餐：</dt>
									<dd>
										<div class="paySel">
											<div class="amountSelect selectPlugin" data-id="amount"
												data-name="amount" data-value="" data-triggertype="click"
												data-nodefault="1" data-showselected="1" selectidx="1">
												<a class="selectMain payA" href="javascript:void(0)"
													data-value="">选择套餐</a>
												<div class="selectWrap selBox" style="display:none;"
													data-scrollmaxheight="165">
													<div class="selectCon selList">
														<ul>
															<li class="selected"><a data-value=""
																href="javascript:void(0)">选择套餐</a></li>
														</ul>
													</div>
													<div class="selectScrollWrap scrollBar2">
														<a class="selectScrollUp" href="javascript:void(0);"></a>
														<div class="selectScrollTrack barM2">
															<a class="selectScrollDrag bar2"
																href="javascript:void(0);"></a>
														</div>
														<a class="selectScrollDown" href="javascript:void(0);"></a>
													</div>
												</div>
												<input type="hidden" value="" id="amount" name="amount">
											</div>
											<span class="errmsg"></span>
										</div>
										<div class="gPay pay_point_p" style="display:none;">
											<span>游戏货币：</span> <strong class="str2"></strong> <i>元宝</i> <em>（实时到账）</em>
										</div>
									</dd>
								</dl>

								<div class="daijin" style="display:none;">
									<h5>
										<a class="showDaijinCont" href="javascript:void(0)" alt="使用"
											title="使用">使用哥们网代金券</a>
									</h5>
									<div class="daijinCont noLog clearfix" style="display:none;">
										<div class="jinCheck">
											<input type="text" class="i2 enterDaijinInput" value="">
												<button class="enterDaijinBtn">确定</button> <span
												class="enterDaijinMsg"></span>
										</div>
									</div>
									<input type="hidden" id="daijin_amount" value=""
										data-only_first="" data-voucher_limit=""> <input
										type="hidden" id="daijin_code" name="daijinCode" value="">
								</div>


								<p class="contact">
									<label>联系手机：</label> <input class="i2" type="text"
										id="user_phone" value="" name="user_phone"> <span
										class="errmsg">建议填写，以便在遇到问题时我们能及时与您沟通</span>
								</p>

								<div class="payBtn">
									<a href="javascript:void(0)" class="submiBtn">下一步</a>
								</div>

								<div class="payTip">
									<h5>温馨提示：</h5>
									<p>
										·只需持有银行卡并开通网上银行服务，可选用此方式进行支付。<br>·为了您使用网上银行方便，建议使用IE浏览器访问此页，请勿在此通道中使用其他方式充值。
									</p>
								</div>
				</div>

			</div>

			<script type="text/javascript">
            // 配置
            window.pay_center_config = {
                maxCharge: '0',
                rtype: '',
                selectedUserCode: '',
                selectedGameId: '',
                selectedServerArea: '',
                payDesc: {"E-BANK":"\u00b7\u53ea\u9700\u6301\u6709\u94f6\u884c\u5361\u5e76\u5f00\u901a\u7f51\u4e0a\u94f6\u884c\u670d\u52a1\uff0c\u53ef\u9009\u7528\u6b64\u65b9\u5f0f\u8fdb\u884c\u652f\u4ed8\u3002\r\n\u00b7\u4e3a\u4e86\u60a8\u4f7f\u7528\u7f51\u4e0a\u94f6\u884c\u65b9\u4fbf\uff0c\u5efa\u8bae\u4f7f\u7528IE\u6d4f\u89c8\u5668\u8bbf\u95ee\u6b64\u9875\uff0c\u8bf7\u52ff\u5728\u6b64\u901a\u9053\u4e2d\u4f7f\u7528\u5176\u4ed6\u65b9\u5f0f\u5145\u503c\u3002","ALIPAY":"\u00b7\u51e1\u662f\u62e5\u6709\u652f\u4ed8\u5b9d\u8d26\u53f7\u7684\u7528\u6237\uff0c\u53ef\u7528\u652f\u4ed8\u5b9d\u4f59\u989d\u6216\u94f6\u884c\u5361\u8fdb\u884c\u5145\u503c\u3002\r\n\u00b7\u6709\u94f6\u884c\u5361\u5c31\u80fd\u4ed8,\u65e0\u9700\u5f00\u901a\u7f51\u94f6\uff0c\u4ec5\u9700\u5361\u53f7\u53ca\u94f6\u884c\u7ed1\u5b9a\u624b\u673a\u53f7\uff0c\u5c31\u53ef\u4ee5\u5145\u503c\u3002\r\n\u00b7\u82e5\u65e0\u6cd5\u5728\u7535\u8111\u7aef\u5b8c\u6210\u652f\u4ed8\uff0c\u4e5f\u53ef\u901a\u8fc7\u201c\u626b\u7801\u652f\u4ed8\u201d\u5728\u79fb\u52a8\u7aef\u652f\u4ed8\u5b9d\u94b1\u5305\u5b8c\u6210\u5145\u503c\u3002","SWIFTPASSCIB_WXPAY":"\u00b7\u9700\u5f00\u901a\u5fae\u4fe1\u652f\u4ed8\u529f\u80fd\uff0c\u7ed1\u5b9a\u94f6\u884c\u5361\u3002\r\n\u00b7\u8bf7\u4f7f\u7528\u5fae\u4fe1\u201c\u626b\u4e00\u626b\u201d\u529f\u80fd\uff0c\u8fdb\u884c\u4e8c\u7ef4\u7801\u626b\u63cf\u4ee5\u5b8c\u6210\u652f\u4ed8\u3002","NETPAY":"\u00b7\u7edf\u4e00\u3001\u5feb\u901f\u3001\u4fbf\u6377\uff0c\u65e0\u987b\u5f00\u901a\u7f51\u94f6\uff0c\u8ba9\u60a8\u5feb\u6377\u5b8c\u6210\u5145\u503c\u7684\u652f\u4ed8\u65b9\u5f0f\u3002","SHENGPAYCARD_UC":"\u00b7\u652f\u6301\u5168\u56fd\u901a\u7528\u7684\u8054\u901a\u5361\uff0c\u5361\u53f715\u4f4d\u3001\u5bc6\u780119\u4f4d\u3002\r\n\u00b7\u60a8\u53ef\u4ee5\u901a\u8fc7\u8425\u4e1a\u5385\u3001\u62a5\u520a\u4ead\u3001\u8d85\u5e02\u3001\u4fbf\u5229\u5e97\u7b49\u65b9\u5f0f\u8d2d\u4e70\u624b\u673a\u5145\u503c\u5361\u8fdb\u884c\u5145\u503c\u3002\r\n\u00b7\u8bf7\u60a8\u52a1\u5fc5\u9009\u62e9\u4e0e\u5361\u9762\u503c\u7b49\u989d\u7684\u5145\u503c\u91d1\u989d\uff0c\u5982\u679c\u9009\u62e9\u5145\u503c\u91d1\u989d\u4e0d\u4e00\u81f4\u4f1a\u5bfc\u81f4\u5361\u9762\u91d1\u989d\u4e22\u5931\u3002","SHENGPAYCARD_CM":"\u00b7\u652f\u6301\u5168\u56fd\u901a\u7528\u7684\u79fb\u52a8\u5145\u503c\u5361\uff0c\u5e8f\u5217\u53f717\u4f4d\u3001\u5bc6\u780118\u4f4d\u3002\r\n\u00b7\u652f\u6301\u5145\u503c\u5361\u7684\u9762\u989d\uff1a10\u5143\u300120\u5143\u300130\u5143\u300150\u5143\u3001100\u5143\u3001300\u5143\u3001500\u5143\u3002\r\n\u00b7\u8bf7\u60a8\u52a1\u5fc5\u9009\u62e9\u4e0e\u5361\u9762\u503c\u7b49\u989d\u7684\u5145\u503c\u91d1\u989d\uff0c\u5982\u679c\u9009\u62e9\u5145\u503c\u91d1\u989d\u4e0d\u4e00\u81f4\u4f1a\u5bfc\u81f4\u5361\u9762\u91d1\u989d\u4e22\u5931\u3002","SHENGPAYCARD_CT":"\u00b7\u652f\u6301\u5168\u56fd\u901a\u7528\u7684\u7535\u4fe1\u5361\uff0c\u5361\u53f719\u4f4d\u3001\u5bc6\u780118\u4f4d\u3002\r\n\u00b7\u60a8\u53ef\u4ee5\u901a\u8fc7\u8425\u4e1a\u5385\u3001\u62a5\u520a\u4ead\u3001\u8d85\u5e02\u3001\u4fbf\u5229\u5e97\u7b49\u65b9\u5f0f\u8d2d\u4e70\u624b\u673a\u5145\u503c\u5361\u8fdb\u884c\u5145\u503c\u3002\r\n\u00b7\u8bf7\u60a8\u52a1\u5fc5\u9009\u62e9\u4e0e\u5361\u9762\u503c\u7b49\u989d\u7684\u5145\u503c\u91d1\u989d\uff0c\u5982\u679c\u9009\u62e9\u5145\u503c\u91d1\u989d\u4e0d\u4e00\u81f4\u4f1a\u5bfc\u81f4\u5361\u9762\u91d1\u989d\u4e22\u5931\u3002","QUYOU-NET":"\u00b7\u8da3\u6e38\u4e00\u5361\u901a\u662f\u8da3\u6e38\u96c6\u56e2\u6709\u9650\u516c\u53f8\u72ec\u7acb\u53d1\u884c\uff0c\u76ee\u524d\u53ef\u5bf9\u65d7\u4e0b\u5e73\u53f0\u7684\u6240\u6709\u6e38\u620f\u8fdb\u884c\u5145\u503c\u3002\r\n\u00b7\u4f7f\u7528\u8da3\u6e38\u4e00\u5361\u901a\u5145\u503c\u5e73\u53f0\u6e38\u620f\u4ea7\u54c1\uff0c\u5373\u65f6\u5230\u8d26\u3001\u65b9\u4fbf\u5feb\u6377\u3002","UMPAY":"\u56e0\u8fd0\u8425\u5546\u4f1a\u6536\u53d6\u670d\u52a1\u8d39\uff0c\u8bf7\u6ce8\u610f\u60a8\u7684\u6263\u8d39\u91d1\u989d\u548c\u5145\u503c\u91d1\u989d\u3002\r\n\u00b7\u77ed\u4fe1\u5145\u503c\u64cd\u4f5c\u9700\u572830\u5206\u949f\u5185\u8fdb\u884c\uff0c\u8fc7\u671f\u9700\u91cd\u65b0\u63d0\u4ea4\u3002\r\n\u00b7\u5355\u4e2a\u624b\u673a\u53f7\u65e5\u5145\u503c\u9650\u989d50\u5143\uff08\u5b9e\u9645\u5230\u8d2625\u5143\uff09\uff0c\u6708\u9650\u989d100\u5143\uff08\u5b9e\u9645\u5230\u8d2650\u5143\uff09\u3002\r\n\u00b7\u6bcf\u4e2a\u7528\u6237\u4f7f\u7528\u624b\u673a\u53f7\u6570\u91cf\u5728\u4e00\u4e2a\u6708\u4e4b\u5185\u9650\u5236\u57282\u4e2a\u4ee5\u5185\u3002\r\n\u00b7\u51e1\u662f4.18-5.17\u6d3b\u52a8\u671f\u95f4\u5145\u503c\u7684\u7528\u6237\uff0c\u5145\u503c\u6210\u529f\u4fbf\u53ef\u62bd\u5956\uff0c\u597d\u793c\u4e0d\u65ad\u3002<a href=\"http:\/\/old.zhaoka.com\/active\/huodong201604!index.htm?cp_id=696\" target=\"_blank\">\u67e5\u770b<\/","VNET":"\u00b7\u7528\u6237\u62e8\u6253\u5f53\u5730\u7684\u70ed\u7ebf\u53f7\u7801\u6309\u8bed\u97f3\u63d0\u793a\u64cd\u4f5c\u5373\u53ef\u8d2d\u4e70\u6210\u529f V \u5e01\uff0c\u8bf7\u60a8\u6ce8\u610f\u8bb0\u5f55\u6e05\u5361\u53f7\u548c\u5bc6\u7801\u3002 \r\n\u00b7\u5168\u56fd\u56fa\u5b9a\u7535\u8bdd\u3001\u5c0f\u7075\u901a\u70ed\u7ebf\u53f7\u7801\u67e5\u8be2\uff0c\u8bf7<a href=\"http:\/\/map.vnetone.com\/?f=48\" target=\"_blank\">\u70b9\u51fb\u8fd9\u91cc<\/a>\u3002","HEEPAY":"\u00b7\u652f\u6301\u9a8f\u7f51\u5361\u7684\u5b9e\u4f53\u5361\u3001\u865a\u4f53\u5361\u8fdb\u884c\u5145\u503c\u3002 \r\n\u00b7\u60a8\u53ef\u4ee5\u901a\u8fc7\u8425\u4e1a\u5385\u3001\u62a5\u520a\u4ead\u3001\u8d85\u5e02\u3001\u4fbf\u5229\u5e97\u7b49\u65b9\u5f0f\u8d2d\u4e70\u3002\r\n\u00b7\u8bf7\u9009\u62e9\u4e0e\u60a8\u4f7f\u7528\u7684\u5145\u503c\u5361\u76f8\u540c\u7684\u9762\u989d\u652f\u4ed8\uff0c\u5982\u679c\u9009\u62e9\u5145\u503c\u91d1\u989d\u4e0d\u4e00\u81f4\u4f1a\u5bfc\u81f4\u5361\u9762\u91d1\u989d\u4e22\u5931\u3002","PAYPAL":"\u00b7[PLATNAME]\u552f\u4e00\u5b98\u65b9Paypal\u8d26\u6237\uff1a<font style=\"color:#FE8F00;\">zhifu@game2.cn<\/font>\u3002\r\n\u00b7\u5355\u7b1420\u7f8e\u5143\u8d77\u53d7\u7406\u3002\r\n\u00b7\u76ee\u524d\u53ea\u652f\u6301\u7f8e\u5143\u652f\u4ed8\uff0c\u7f8e\u5143\u548c\u4eba\u6c11\u5e01\u5151\u6362\u6bd4\u73871:6\u3002\r\n\u00b7\u82e5\u5c1a\u672a\u7533\u8bf7Paypal\u5907\u6848\uff0c\u8bf7\u5148<a href=\"\/paypal\/\" target=\"_blank\">\u7533\u8bf7\u5907\u6848<\/a>\u3002","SHENGPAYCARD_SNDA":"\u00b7\u652f\u6301\u5f00\u5934\u4e3a\u201cCA\u201d\u3001\u201cCS\u201d\u3001\u201cS1\u201d\u3001\u201cS2\u201d\u3001\u201cS3\u201d\u7684\u76db\u5927\u4e00\u5361\u901a\uff1b\u5f00\u5934\u4e3a\u201cYC\u201d\u3001\u201cYD\u201d\u7684\u76db\u5927\u4e92\u52a8\u5a31\u4e50\u5361\uff1b\r\n\u3000\u5f00\u5934\u4e3a\u201c801335\u201d\u3001\u201c801336\u201d\u3001\u201c801337\u201d\u3001\u201c801338\u201d\u3001\u201c801340\u201d\u7684\u76db\u4ed8\u901a\u5a31\u4e50\u4e00\u5361\u901a\u3002\r\n\u00b7\u652f\u6301\u5145\u503c\u5361\u7684\u9762\u989d\uff1a10\u5143\u300130\u5143\u300150\u5143\u3001100\u5143\u3001500\u5143\u3002\r\n\u00b7\u652f\u6301\u5355\u5361\u591a\u6b21\u652f\u4ed8\uff0c\u76f4\u81f3\u5361\u5185\u4f59\u989d\u4e3a\u96f6\u3002","ULINE_ALIPAY":"\u00b7\u51e1\u662f\u62e5\u6709\u652f\u4ed8\u5b9d\u8d26\u53f7\u7684\u7528\u6237\uff0c\u53ef\u7528\u652f\u4ed8\u5b9d\u4f59\u989d\u6216\u94f6\u884c\u5361\u8fdb\u884c\u5145\u503c\u3002\r\n\u00b7\u6709\u94f6\u884c\u5361\u5c31\u80fd\u4ed8,\u65e0\u9700\u5f00\u901a\u7f51\u94f6\uff0c\u4ec5\u9700\u5361\u53f7\u53ca\u94f6\u884c\u7ed1\u5b9a\u624b\u673a\u53f7\uff0c\u5c31\u53ef\u4ee5\u5145\u503c\u3002\r\n\u00b7\u82e5\u65e0\u6cd5\u5728\u7535\u8111\u7aef\u5b8c\u6210\u652f\u4ed8\uff0c\u4e5f\u53ef\u901a\u8fc7\u201c\u626b\u7801\u652f\u4ed8\u201d\u5728\u79fb\u52a8\u7aef\u652f\u4ed8\u5b9d\u94b1\u5305\u5b8c\u6210\u5145\u503c\u3002"},
                payTips: {"E-BANK":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f\u201c<strong>\u7f51\u4e0a\u94f6\u884c<\/strong>\u201d\u652f\u4ed8\u65b9\u5f0f\uff0c\u540c\u65f6\u652f\u6301\u50a8\u5b58\u5361\u548c\u4fe1\u7528\u5361\uff1a","ALIPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u652f\u4ed8\u5b9d\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u540c\u65f6\u652f\u6301\u652f\u4ed8\u5b9d\u4f59\u989d\u5728\u7ebf\/\u626b\u63cf\u652f\u4ed8\u548c\u94f6\u884c\u5361\u652f\u4ed8\u3002\u8bf7\u9009\u62e9\u652f\u4ed8\u7c7b\u578b\uff1a","SWIFTPASSCIB_WXPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f\u201c<strong>\u5fae\u4fe1\u626b\u7801<\/strong>\u201d\u652f\u4ed8\u65b9\u5f0f\uff0c\u901a\u8fc7\u5fae\u4fe1-\u626b\u4e00\u626b\u529f\u80fd\u5feb\u901f\u5b8c\u6210\u652f\u4ed8\u3002","NETPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f\u201c<strong>\u94f6\u8054\u652f\u4ed8<\/strong>\u201d\u652f\u4ed8\u65b9\u5f0f\uff0c\u65e0\u9700\u7f51\u94f6\uff0c\u6301\u6709\u94f6\u8054\u5361\u5373\u53ef\uff1a","SHENGPAYCARD_UC":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u624b\u673a\u5145\u503c\u5361\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u5361\u9762\u503c\u987b\u548c\u5145\u503c\u91d1\u989d\u4e00\u81f4\uff0c\u8bf7\u9009\u62e9\u5361\u7c7b\u578b\uff1a","SHENGPAYCARD_CM":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u624b\u673a\u5145\u503c\u5361\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u5361\u9762\u503c\u987b\u548c\u5145\u503c\u91d1\u989d\u4e00\u81f4\uff0c\u8bf7\u9009\u62e9\u5361\u7c7b\u578b\uff1a","SHENGPAYCARD_CT":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u624b\u673a\u5145\u503c\u5361\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u5361\u9762\u503c\u987b\u548c\u5145\u503c\u91d1\u989d\u4e00\u81f4\uff0c\u8bf7\u9009\u62e9\u5361\u7c7b\u578b\uff1a","QUYOU-NET":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f\u201c<strong>\u8da3\u6e38\u4e00\u5361\u901a<\/strong>\u201d\u652f\u4ed8\u65b9\u5f0f\uff0c\u901a\u8fc7\u586b\u5199\u5145\u503c\u5361\u7684\u8d26\u53f7\u5bc6\u7801\u8fdb\u884c\u5145\u503c\uff1a","UMPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u624b\u673a\u77ed\u4fe1\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u4ee5\u77ed\u4fe1\u6263\u8d39\u7684\u5f62\u5f0f\u8fdb\u884c\u5145\u503c\uff1a","VNET":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u58f0\u8baf\u7535\u8bdd\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u901a\u8fc7\u56fa\u5b9a\u7535\u8bdd\u8d2d\u4e70V\u5e01\u8fdb\u884c\u5145\u503c\u3002","HEEPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u9a8f\u7f51\u4e00\u5361\u901a\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u540c\u65f6\u652f\u6301\u5b9e\u7269\u5361\u3001\u865a\u62df\u5361:","PAYPAL":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201cpaypal\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u5916\u5e01\u5145\u503c\u5de5\u5177\uff0c\u76ee\u524d\u53ea\u652f\u6301\u7f8e\u5143\u652f\u4ed8\u3002","SHENGPAYCARD_SNDA":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f\u201c<strong>\u76db\u5927\u5361<\/strong>\u201d\u652f\u4ed8\u65b9\u5f0f\uff0c\u901a\u8fc7\u586b\u5199\u5145\u503c\u5361\u7684\u8d26\u53f7\u5bc6\u7801\u8fdb\u884c\u5145\u503c\uff1a","ULINE_ALIPAY":"\u60a8\u5f53\u524d\u9009\u62e9\u7684\u662f<strong>\u201c\u652f\u4ed8\u5b9d\u201d<\/strong>\u652f\u4ed8\u65b9\u5f0f\uff0c\u540c\u65f6\u652f\u6301\u652f\u4ed8\u5b9d\u4f59\u989d\u5728\u7ebf\/\u626b\u63cf\u652f\u4ed8\u548c\u94f6\u884c\u5361\u652f\u4ed8\u3002\u8bf7\u9009\u62e9\u652f\u4ed8\u7c7b\u578b\uff1a"},
                switchConfig: '0'
            };
        </script>
			<script type="text/javascript"
				src="pay/Pay.Center.js"></script>
		</div>

		<div id="footer" style="display: none;">
			<div class="fLogo fl">
				<a href="http://www.game2.cn/"><img
					src="pay/footLogo.jpg" width="146" height="85"></a>
			</div>
			<div class="fDiv fl">
				<p class="footP1">
					<a href="http://www.g2.cn/swhz/swhz/shangwuhezuo-4434.htm"
						target="_blank">商务合作</a> <span>|</span> <a
						href="http://www.g2.cn/jrwm/syzp-4460.htm" target="_blank">人才招聘</a>
					<span>|</span> <a href="http://www.g2.cn/jiazhang/" target="_blank">家长监护</a>
					<span>|</span> <a href="http://www.g2.cn/dispute.htm"
						target="_blank">纠纷处理</a>
				</p>
				<p>抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防受骗上当。适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。</p>
				<p>
					<a href="http://www.g2.cn/images/GroupWWW.jpg" target="_blank">闽网文[2015]1355-018号</a>
					<a href="http://www.g2.cn/images/GroupICP.jpg" target="_blank">闽B2-20120072</a>
					<a href="http://www.miibeian.gov.cn/" target="_blank">闽ICP备12006790号-9</a>
					<a target="_blank"
						href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=35020602000046"><img
						style="vertical-align:top;" src="pay/ghs.png">闽公网安备
							35020602000046号</a>
				</p>
				<p>
					<a href="http://www.g2.cn/" target="_blank">趣游（厦门）科技有限公司</a>
					Copyright <span style="font-family:arial;">©</span> 2001-2016
					Game2.cn 版权所有 未经授权禁止转载、摘编、复制或建立镜像
				</p>
			</div>
			<div class="fIco fl">
				<a
					href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202012071300019"
					target="_blank"><img src="pay/ico1.jpg"
					width="37" height="41"></a>
			</div>
		</div>
	</div>

	<div class="csFloat">
		<ul>
			<li><a class="c1"
				href="http://chat.53kf.com/webCompany.php?arg=xmkf&amp;style=1"
				target="_blank">在线客服</a></li>
			<li><a class="c2" href="http://www.game2.cn/serverCenter/"
				target="_blank">400-9933-555</a></li>
			<li><a class="c3"
				href="http://pay.game2.cn/tutorial/game2/wy.html" target="_blank">常见问题</a></li>
		</ul>
	</div>


	<iframe id="alpha" frameborder="0" marginheight="0" marginwidth="0"
		scrolling="no" src="pay/blank.html"
		style="display:none; width:100%; height:1340px;"></iframe>
	<div id="paypop_msg" class="paypop tan" style="display:none;">
		<h3>
			<span>温馨提示</span><a href="javascript:void(0)" class="closePop">关闭</a>
		</h3>
		<div class="paypop_content tanM"></div>
	</div>




	<script type="text/javascript" src="pay/stat_uid.js"></script>
	<script type="text/javascript">$(function(){stat_uid(4);});</script>


	<div class="__stat_uid_div"
		style="width: 1px; height: 1px; overflow: hidden; position: absolute; left: -100%; top: 0px;">
		<iframe scrolling="no" frameborder="0"
			style="width: 0; height: 0; opacity: 0;"
			src="pay/stat_uid.html" marginwidth="0"
			marginheight="0"></iframe>
	</div>
</body>
</html>
