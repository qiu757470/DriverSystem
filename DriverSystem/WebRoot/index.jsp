<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="imagetoolbar" content="no">
<meta name="msthemecompatible" content="no">
<meta name="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<meta name="theme-color" content="#ffffff">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<link sizes="57x57" href="apple-touch-icon-57x57.png"
	rel="apple-touch-icon">
<link sizes="114x114" href="apple-touch-icon-114x114.png"
	rel="apple-touch-icon">
<link sizes="72x72" href="apple-touch-icon-72x72.png"
	rel="apple-touch-icon">
<link sizes="144x144" href="apple-touch-icon-144x144.png"
	rel="apple-touch-icon">
<link sizes="60x60" href="apple-touch-icon-60x60.png"
	rel="apple-touch-icon">
<link sizes="120x120" href="apple-touch-icon-120x120.png"
	rel="apple-touch-icon">
<link sizes="76x76" href="apple-touch-icon-76x76.png"
	rel="apple-touch-icon">
<link sizes="152x152" href="apple-touch-icon-152x152.png"
	rel="apple-touch-icon">
<link sizes="180x180" href="apple-touch-icon-180x180.png"
	rel="apple-touch-icon">
<link sizes="192x192" href="favicon-192x192.png" rel="icon"
	type="image/png">
<link sizes="160x160" href="favicon-160x160.png" rel="icon"
	type="image/png">
<link sizes="96x96" href="favicon-96x96.png" rel="icon" type="image/png">
<link sizes="16x16" href="favicon-16x16.png" rel="icon" type="image/png">
<link sizes="32x32" href="favicon-32x32.png" rel="icon" type="image/png">
<link rel="manifest" href="manifest.json">
<title>驾校管理系统</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="assets/styles/app.min.css" rel="stylesheet">
<style type="text/css">
	html,body{
		font-family: "微软雅黑";
	}
</style>
</head>

<body class="page">
	<div class="bar-social">
		<div class="container">
			<div class="row">
				<div class="col-md-6 hidden-xs">
					<p class="bar-social__text"> </p>
				</div>
				<div class="col-md-6">
					<ul class="bar-social__list">
						<li class="bar-social__list-item"><a href=""
							class="bar-social__link"> <svg class="bar-social__icon">
									<use xlink:href="assets/images/icon.svg#icon_vk"></use></svg>
						</a></li>
						<li class="bar-social__list-item"><a href=""
							class="bar-social__link"> <svg class="bar-social__icon">
									<use xlink:href="assets/images/icon.svg#icon_facebook"></use></svg>
						</a></li>
						<li class="bar-social__list-item"><a href=""
							class="bar-social__link"> <svg class="bar-social__icon">
									<use xlink:href="assets/images/icon.svg#icon_twitter"></use></svg>
						</a></li>
						<li class="bar-social__list-item"><a href=""
							class="bar-social__link"> <svg class="bar-social__icon">
									<use xlink:href="assets/images/icon.svg#icon_instagram"></use></svg>
						</a></li>
						<li class="bar-social__list-item"><a href=""
							class="bar-social__link"> <svg class="bar-social__icon">
									<use xlink:href="assets/images/icon.svg#icon_ok"></use></svg>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="header">
		<div class="header__row container">
			<div class="row">
				<div class="col-md-5" style="width: 25%">
					<div class="logo">
						<a href="index.html" class="logo__link"> <svg
								class="logo__icon">
								<use xlink:href="assets/images/icon.svg#icon_logo"></use></svg> <span
							class="logo__text">驾校培训管理系统</span>
						</a>
					</div>
				</div>
				<div class="header__contact col-md-3">
					<div class="header__address">
						<svg class="header__pin">
							<use xlink:href="assets/images/icon.svg#icon_pin"></use></svg>
						福建 厦门, 思明区

					</div>
				</div>
				<div class="header__contact col-md-2">
					<a href="tel:+908887775544" class="header__phone"> <svg
							class="header__phone-icon">
							<use xlink:href="assets/images/icon.svg#icon_phone"></use></svg>18359256139
					</a>
				</div>
				<div class="header__contact col-md-2">
					<a class="btn header__callback" href="main/toLogin"> <span
						class="btn__text">登陆</span>
					</a>
				</div>
				<div class="header__contact col-md-2">
					<a class="btn header__callback" href="main/toRegister"> <span
						class="btn__text">注册</span>
					</a>
				</div>
			</div>
		</div>
		<div class="nav">
			<div class="nav__btn-wrap">
				<button class="btn nav__btn" type="button">
					<span class="btn__text"> <span class="nav__icon"></span>
					</span>
				</button>
			</div>

		</div>
	</div>

	<div class="numbers">
		<div class="container">
			<div class="row">
				<ul class="numbers__list">
					<li class="numbers__list-item col-md-3">
						<h3 class="numbers__value">100+</h3>
						<p class="numbers__desc">平时毕业时间</p>
					</li>
					<li class="numbers__list-item col-md-3">
						<h3 class="numbers__value">7</h3>
						<p class="numbers__desc">平均个人经验</p>
					</li>
					<li class="numbers__list-item col-md-3">
						<h3 class="numbers__value">578</h3>
						<p class="numbers__desc">教练人数</p>
					</li>
					<li class="numbers__list-item col-md-3">
						<h3 class="numbers__value">32</h3>
						<p class="numbers__desc">驾校个数</p>
					</li>
				</ul>
			</div>
		</div>
	</div>


	<div class="instructor">
		<div class="container">
			<h2 class="title">
				<span class="title__mark">优秀学员</span>
			</h2>
			<p class="desc">2017年第一季度优秀学员、优秀教练员表彰大会在正友驾校会议室顺利召开。</p>
			<div class="instructor__list">
				<div id="instructorSlider" class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<div class="instructor__list-item row">
								<figure class="instructor__fig col-md-5 col-lg-6">
									<img src="assets/images/reviewer/b1.jpg" alt="" />
								</figure>
								<div class="instructor__body col-md-7 col-lg-6">
									<h4 class="instructor__name">学员：桂文彪</h4>
									<div class="instructor__type">拿证时间：3个月</div>
									<blockquote class="instructor__quote">
										<div class="instructor__quote-inner">
											“我终于拿到驾驶证了！”一位名叫桂文彪的残疾学员高兴地说。在我校参加驾驶培训的首批残疾学员龚建平、桂文彪等人顺利通过科目三考试后，在我校客服中心会员俱乐部领到了C5机动车驾照。
										</div>
									</blockquote>
								</div>
							</div>
						</div>
						<div class="swiper-slide">
							<div class="instructor__list-item row">
								<figure class="instructor__fig col-md-5 col-lg-6">
									<img src="assets/images/reviewer/b2.png" alt="" />
								</figure>
								<div class="instructor__body col-md-7 col-lg-6">
									<h4 class="instructor__name">学员：吴小珊</h4>
									<div class="instructor__type">拿证时间：2个月</div>
									<blockquote class="instructor__quote">
										<div class="instructor__quote-inner">我顺利通过了科目一理论考试。不久，我怀着既兴奋又紧张的心情走进了驾校的桩训场地，在教学六队的桩训场上找到了620教学车辆，于是开始了我的学车生涯。
											我的第一个教练是魏濛教练，她总是面带笑容，待人谦和、为人善良，从来不会跟学员乱发脾气，很有亲切感，总是耐心细致的给我们指导和讲解，强调正确的操作技巧，要求手脚配合要协调。</div>
									</blockquote>
								</div>
							</div>
						</div>
						<div class="swiper-slide">
							<div class="instructor__list-item row">
								<figure class="instructor__fig col-md-5 col-lg-6">
									<img src="assets/images/reviewer/b1.jpg" alt="" />
								</figure>
								<div class="instructor__body col-md-7 col-lg-6">
									<h4 class="instructor__name">Ivanov Alexey</h4>
									<div class="instructor__type">Theory instructor</div>
									<blockquote class="instructor__quote">
										<div class="instructor__quote-inner">Lorem ipsum dolor
											sit amet, consectetur adipiscing elit. Praesent nec fermentum
											ex. Nam tincidunt, diam quis elementum accumsan, urna magna
											elementum elit, varius&nbsp;efficitur neque ex et nisi.</div>
									</blockquote>
								</div>
							</div>
						</div>
						<div class="swiper-slide">
							<div class="instructor__list-item row">
								<figure class="instructor__fig col-md-5 col-lg-6">
									<img src="assets/images/reviewer/b1.jpg" alt="" />
								</figure>
								<div class="instructor__body col-md-7 col-lg-6">
									<h4 class="instructor__name">Ivanov Alexey</h4>
									<div class="instructor__type">Theory instructor</div>
									<blockquote class="instructor__quote">
										<div class="instructor__quote-inner">Lorem ipsum dolor
											sit amet, consectetur adipiscing elit. Praesent nec fermentum
											ex. Nam tincidunt, diam quis elementum accumsan, urna magna
											elementum elit, varius&nbsp;efficitur neque ex et nisi.</div>
									</blockquote>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-md-offset-5 col-lg-offset-6"
				style="margin: 100px 0">
				<div class="instructor__pager">
					<div class="instructor__pager-item">
						<img src="assets/images/reviewer/r1.png" alt="Ivanov Alexey" />
					</div>
					<div class="instructor__pager-item">
						<img src="assets/images/reviewer/r2.png" alt="Ivanov Alexey" />
					</div>
					<!-- <div class="instructor__pager-item">
						<img src="assets/images/reviewer/r3.png" alt="Ivanov Alexey" />
					</div>
					<div class="instructor__pager-item">
						<img src="assets/images/reviewer/r4.png" alt="Ivanov Alexey" />
					</div> -->
				</div>
			</div>
		</div>
	</div>




	<div class="course course_index">
		<div class="container">
			<h2 class="title title_theme_white">
				<span class="title__mark">科目介绍</span>
			</h2>
			<p class="desc course__desc">考试科目内容及合格标准全国统一，该考试分为理论知识、场地驾驶技能、道路驾驶技能及文明驾驶相关知识三个科目四项考试。</p>
			<ul class="course__list row">
				<li class="course__list-item col-md-4">
					<div class="course__item">
						<div class="course__item-head">
							<svg class="course__icon-guard">
								<use xlink:href="assets/images/icon.svg#icon_guard"></use></svg>
							<h3 class="course__item-name">科目一介绍</h3>
						</div>
						<div class="course__price">
							<div class="course__price-item">
								<div class="course__price-kind">自动档</div>
								<div class="course__price-value">300</div>
							</div>
							<div class="course__price-item">
								<div class="course__price-kind">Mechanics</div>
								<div class="course__price-value">400</div>
							</div>
						</div>
						<div class="course__item-popup">
							<div class="course__item-popup-name">科目一文科考试时间安排在工作日进行，采用电脑无纸化考试的形式，考试时间为45分钟，100道选择判断题，满分为100分，90分为合格，缺考或不合格者，间隔15天后重排考试。 
								对驾驶人进行交通法规，车辆使用，安全驾驶等方面进行考核，是交通标志、法规、安全意识等内容的考试，考查交通法规基础常识。</div>

						</div>
					</div>
				</li>
				<li class="course__list-item col-md-4">
					<div class="course__item">
						<div class="course__item-head">
							<svg class="course__icon-road">
								<use xlink:href="assets/images/icon.svg#icon_road"></use></svg>
							<h3 class="course__item-name">科目二介绍</h3>
						</div>
						<div class="course__price">
							<div class="course__price-item">
								<div class="course__price-kind">自动档</div>
								<div class="course__price-value">300</div>
							</div>
							<div class="course__price-item">
								<div class="course__price-kind">手动档</div>
								<div class="course__price-value">400</div>
							</div>
						</div>
						<div class="course__item-popup">
							<div class="course__item-popup-name">科目二考核机动车驾驶人操控车辆完成侧方移位、倒车入库和正确判断车身空间位置的能力。从起点倒入乙库停正，再经过二进二退移位到甲库停正，前进穿过乙库至路上，倒入甲库停正，前进返回起点。</div>

						</div>
					</div>
				</li>
				<li class="course__list-item col-md-4">
					<div class="course__item">
						<div class="course__item-head">
							<svg class="course__icon-logo">
								<use xlink:href="assets/images/icon.svg#icon_logo"></use></svg>
							<h3 class="course__item-name">科目三介绍</h3>
						</div>
						<div class="course__price">
							<div class="course__price-item">
								<div class="course__price-kind">自动挡</div>
								<div class="course__price-value">300</div>
							</div>
							<div class="course__price-item">
								<div class="course__price-kind">手动挡</div>
								<div class="course__price-value">400</div>
							</div>
						</div>
						<div class="course__item-popup">
							<div class="course__item-popup-name">科目三：桩考合格后，由考场另行安排时间参加“九选三”场内道路考试，主要是考一些驾车时的基本功，考试缺考者，间隔一个月后重排考试；不合格者，间隔20天后方可重新安排补考。</div>

						</div>
					</div>
				</li>
				<li class="course__list-item col-md-4">
					<div class="course__item">
						<div class="course__item-head">
							<svg class="course__icon-child">
								<use xlink:href="assets/images/icon.svg#icon_child"></use></svg>
							<h3 class="course__item-name">科目四介绍</h3>
						</div>
						<div class="course__price">
							<div class="course__price-item">
								<div class="course__price-kind">自动挡</div>
								<div class="course__price-value">300</div>
							</div>
							<div class="course__price-item">
								<div class="course__price-kind">手动挡</div>
								<div class="course__price-value">400</div>
							</div>
						</div>
						<div class="course__item-popup">
							<div class="course__item-popup-name">科目四考试即安全文明驾驶常识考试，在路考合格后进行。根据公安部123号令，实际上并未有科目四的说法，正式的名称为“安全文明驾驶常识考试”，因大部分学员以及驾校为区分科目三路考，习惯称之为科目四。</div>

						</div>
					</div>
				</li>

			</ul>
		</div>
	</div>
	<div class="gallery">
		<div class="gallery__container container">
			<h2 class="title gallery__title">
				<span class="title__mark">优秀</span>教练和驾校
			</h2>
			<p class="desc">亲,在找驾校教练吗?不要再发愁了,我们为您精选了全国最好的驾校教练员,良好的服务态度和多年驾驶经验,为学员提供手把手的指导。</p>
			<div class="filter gallery__filter">
				<span data-filter=".gallery__list-item_cars" class="filter__item">
					<span class="filter__item-text">优秀驾校</span>
				</span> <span data-filter=".gallery__list-item_autodrom"
					class="filter__item"> <span class="filter__item-text">优秀教练</span>
				</span>
			</div>
			<div class="gallery__list row">

				<div
					class="gallery__list-item gallery__list-item_cars col-xs-6 col-md-3">
					<figure class="gallery__fig">
						<a href="assets/photo/thumb2.jpg" class="gallery__fig-link"> <img
							src="assets/photo/thumb2.jpg" alt="" />
						</a>
					</figure>
				</div>
				<div
					class="gallery__list-item gallery__list-item_autodrom col-xs-6 col-md-3">
					<figure class="gallery__fig">
						<a href="assets/photo/thumb3.jpg" class="gallery__fig-link"> <img
							src="assets/photo/thumb3.jpg" alt="" />
						</a>
					</figure>
				</div>

				<div
					class="gallery__list-item gallery__list-item_cars col-xs-6 col-md-3">
					<figure class="gallery__fig">
						<a href="assets/photo/thumb5.jpg" class="gallery__fig-link"> <img
							src="assets/photo/thumb5.jpg" alt="" />
						</a>
					</figure>
				</div>
				<div
					class="gallery__list-item gallery__list-item_autodrom col-xs-6 col-md-3">
					<figure class="gallery__fig">
						<a href="assets/photo/thumb6.jpg" class="gallery__fig-link"> <img
							src="assets/photo/thumb6.jpg" alt="" />
						</a>
					</figure>
				</div>

				<div
					class="gallery__list-item gallery__list-item_cars col-xs-6 col-md-3">
					<figure class="gallery__fig">
						<a href="assets/photo/thumb8.jpg" class="gallery__fig-link"> <img
							src="assets/photo/thumb8.jpg" alt="" />
						</a>
					</figure>
				</div>
			</div>
		</div>
	</div>

	<div class="popup"></div>


	<div class="faq">
		<div class="container">
			<h2 class="title">
				<span class="title__mark">一些问题</span>
			</h2>
			<ul class="faq__list">
				<li class="faq__item faq__item_active row">
					<div class="col-md-7">
						<div class="faq__question">驾校学车有没有发票？可以开什么项目的？</div>
						<div class="faq__answer">有。在学车完成后凭收据到驾校财务室换取正规发票，项目只能是培训费。</div>
					</div>
				</li>
				<li class="faq__item row">
					<div class="col-md-7">
						<div class="faq__question">驾校报名后自己不学转给其他人学可以吗？</div>
						<div class="faq__answer">可以的，需要到驾校手续厅办理相应手续。</div>
					</div>
				</li>
				<li class="faq__item row">
					<div class="col-md-7">
						<div class="faq__question">出国了，车没学完怎么办？</div>
						<div class="faq__answer">1、三年内回国的，可以回来接着学；2、不打算回来的，扣减相应费用后退学即可。</div>
					</div>
				</li>
				<li class="faq__item row">
					<div class="col-md-7">
						<div class="faq__question">不想再在这个学校学了，太慢了两个月了没拿到驾照怎么办？</div>
						<div class="faq__answer">1、新交规实施后学时延长、考试科目增加所以两个月无法拿到驾照，一般两个多月以上；2、尽量不要转校，一是手续繁琐，二是扣除相应费用，三是想要转过去的那个驾校不一定像别人说的好，转校之前请您致电问清虚实后再做考虑；
							3、驾校现在是北京第二大驾校，而且效率最高，拿照速度最快（其他驾校在现在5月份招生旺季，学车报名后一般需要1-2个月才能安排上法培，我校报名学员最快9天即可上法培）。</div>
					</div>
				</li>
				<li class="faq__item row">
					<div class="col-md-7">
						<div class="faq__question">驾校学完车驾照可以迁回老家吗？</div>
						<div class="faq__answer">可以，带上身份证原件及正反面复印件一份、一寸白底照片3张、驾照正副本，在老家的车管所直接办理即可。</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer">
		<div class="nav">
			<div class="nav__btn-wrap">
				<button class="btn nav__btn" type="button">
					<span class="btn__text"> <span class="nav__icon"></span>
					</span>
				</button>
			</div>

		</div>
		<div class="footer__body container">
			<div class="row">
				<div class="footer__section col-md-3">
					<div class="footer__about">
						<h4 class="footer__title">关于</h4>
						<p class="footer__text">在过去的10年里，他们一直在为厦门市提供8个分支机构，并完成了500万名学生的学习。我们有一个经验丰富的团队
							还有认证的训练师，他们会帮助你从开始到结束驾驶课.</p>
						<div class="logo footer__logo">
							<a href="index.html" class="logo__link"> <svg
									class="logo__icon">
									<use xlink:href="assets/images/icon.svg#icon_logo"></use></svg> <span
								class="logo__text">驾校培训管理系统</span>
							</a>
						</div>
					</div>
				</div>
				<div class="footer__section col-md-3">
					<div class="footer__blog">
						<h4 class="footer__title">论坛</h4>
						<article class="footer__post">
							<a href="" class="footer__post-wrap">
								<h5 class="footer__post-title">原来网上报名学车是酱紫的！</h5>
								<p class="footer__text">驾照自学直考即将开始试点、先培训后付费模式也在逐步推行，一系列的驾培行业热点新闻让学车是一种怎样的体验再次进入大众热议的范畴...</p>
							</a>
						</article>
						<article class="footer__post">
							<a href="" class="footer__post-wrap">
								<h5 class="footer__post-title">学车是一种投资，早投资早收益！</h5>
								<p class="footer__text">不得不说，每一个选择学车考驾照的人都是明智的，因为学车不仅是一种选择更是一种投资，并且这种投资可以说是稳赚不赔的，学车考驾照，首先你多了一本证，其次你掌握了一项技能，这对你个人来说肯定是有益无害，并且这项技能这个小黑本，也会给你生活带来精彩的变化，所以，你还能说这项投资不划算吗...</p>
							</a>
						</article>

						<a href="" class="footer__more">看到所有的帖子 <svg
								class="footer__more-icon">
								<use xlink:href="assets/images/icon.svg#icon_right"></use></svg>
						</a>
					</div>
				</div>
				<div class="footer__section col-md-3">
					<div class="footer__instagramm">
						<h4 class="footer__title">图片分享</h4>
						<ul class="footer__instagramm-list">
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i1.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i1.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i2.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i2.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i3.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i3.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i4.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i4.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i5.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i5.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i6.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i6.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i7.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i7.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i8.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i8.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
							<li class="footer__instagramm-item"><a
								href="assets/images/inst/i9.jpg" class="footer__instagramm-link">
									<svg class="zoom">
										<use xlink:href="assets/images/icon.svg#icon_search"></use></svg> <img
									src="assets/images/inst/i9.jpg" alt=""
									style="width: 50px;height: 50px" />
							</a></li>
						</ul>

					</div>
				</div>
				<div class="footer__section col-md-3">
					<div class="footer__hours">
						<h4 class="footer__title">开放时间</h4>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周一</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 18:00</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周二</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 18:00</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周三</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 18:00</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周四</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 18:00</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周五</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 18:00</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周六</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">08:00 - 13:30</div>
						</div>
						<div class="footer__hours-item">
							<div class="footer__hours-day">周日</div>
							<div class="footer__hours-separ"></div>
							<div class="footer__hours-time">关闭</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="dev">
		<div class="container">
			<div class="container">
				<div class="dev__item">
					版权所有：厦门市传一信息科技有限公司<a target="_blank"
						href="http://sc.chinaz.com/moban/"></a>
				</div>
			</div>
		</div>
	</div>

	<script>
		window.jQuery || document.write('<script src="assets/scripts/jquery-3.1.0.min.js"><\/script>');
	</script>
	<!---<script src="http://ditu.google.cn/maps/api/js?key=AIzaSyBRrnhl1mCfuWpxL4Ame0cyS8fcV7xoyLk"></script>--->
	<script src="assets/scripts/jquery.formstyler.js"></script>
	<script src="assets/scripts/jquery.countdown.js"></script>
	<script src="assets/scripts/jquery.magnific-popup.min.js"></script>
	<script src="assets/scripts/swiper.min.js"></script>
	<script src="assets/scripts/jquery.knob.js"></script>
	<script src="assets/scripts/rome.min.js"></script>
	<script src="assets/scripts/isotope.pkgd.min.js"></script>
	<script src="assets/scripts/app.min.js"></script>
</body>

</html>


