<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String slideId_String = request.getParameter("slideId");
	String flag = "";
	if (slideId_String != null) {
		flag = "true";
	}
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<!--[if lt IE9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
<!--低版本浏览器的支持-->

<head>
<!-- meta tags -->
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>NF4 园丁助手</title>

<!-- css库导入 -->
<!-- 本地库导入 -->
<link href="./css/plugin/jquery-ui_modify.min.css" rel="stylesheet">
<link href="./css/plugin/bootstrap.min.css" rel="stylesheet">
<link href="./css/font/css/font-awesome.min.css" rel="stylesheet">
<link
	href="./js/plugin/ckeditor/plugins/codesnippet/lib/highlight/styles/xcode.css"
	rel="stylesheet">
<!-- CDN结点导入 -->

<!-- css页面控制 -->
<link href="./css/index.css" rel="stylesheet">
<link href="./css/nf4-style-dx.css" rel="stylesheet">
<script src="js/plugin/reveal.js/lib/js/head.min.js"></script>
<script src="js/plugin/reveal.js/js/reveal.js"></script>
<script src="./js/plugin/jquery-3.2.1.min.js" type="text/javascript"></script>

<style>
.no-close .ui-dialog-titlebar-close {
  display: none;
}
</style>
</head>

<body>
	<script>
		var flag = "<%=flag%>";
		var slideId = "<%=slideId_String%>";
		var editHtml = new Array();
		;
		if (flag != "") {
			$.ajax({
				type : "post",
				url : "edit.do",
				async : false,
				data : {
					slideId : slideId
				},
				success : function(msg) {
					editHtml = msg.split("<--nf4-->");
				}
			});
		}
	</script>
	<!-- Layout /* Layout:[70px:90px:Flex:170px] */-->
	<div class="container-fluid nf4-layout">
		<div class="navbar nf4-navbar">
			<!-- 导航栏区域 -->

			<ul class="nav navbar-brand nf4-navbar__brand">
				<!-- 导航栏图标 -->
				<li class="nav-item"><span> <i
						class="fa fa-paper-plane fa-lg"></i> <span
						class="nf4-navbar__brand__font">NF4 园丁助手</span>
				</span></li>
			</ul>

			<div class="nav justify-content-end nf4-navbar-control">
				<!-- 导航栏按钮 -->
				<div class="nav-item nf4-navbar__button">
					<!-- 导航栏-帮助 -->
					<a class="nav-link" id="nf4-navbar__help"> <i
						class="fa fa-question fa-lg"></i>
					</a>
				</div>

				<div class="nav-item nf4-navbar__button">
					<!-- 导航栏-设置 -->
					<a class="nav-link" id="nf4-navbar__settings"> <i
						class="fa fa-gear fa-lg"></i>
					</a>
				</div>

				<div class="nav-item nf4-navbar-button__user">
					<!-- 导航栏-用户 -->
					<span class="nf4-navbar__user-name" id="nf4-navbar__user-name">Billow
						Tao</span>
					<!-- 导航栏-用户名 -->
					<div class="nf4-navbar__user-avator">
						<img class="user-avator__img" id="mavbar__user-img"
							src="./img/avator/avator_1.png" alt="User"></img>
					</div>
				</div>

			</div>

		</div>

		<div class="nf4-main">
			<!-- 操作区域主界面 -->
			<div class="nf4-menu">
				<!-- 主菜单区域 -->
				<div
					class="btn-group-vertical nf4-menu-control nf4-menu-button__group"
					role="group">
					<!-- 主菜单控制按钮 -->
					<button type="button" class="btn" id="nf4-menu-element">
						<!-- 元素栏菜单切换按钮 id="nf4-menu-element" -->
						<i class="fa fa-bars fa-lg"></i>
					</button>
					<button type="button" class="btn" id="nf4-menu__play_slides">
						<!-- 幻灯片播放按钮 id="menu__play_slides" -->
						<i class="fa fa-play fa-lg"></i>
					</button>
					<button type="button" class="btn" id="nf4-menu__full_screen">
						<!-- 全屏按钮 id="menu__full_screen" -->
						<i class="fa fa-expand fa-lg"></i>
					</button>
					<button type="button" class="btn" id="nf4-menu__save">
						<!-- 保存按钮  id="menu__save" -->
						<i class="fa fa-save fa-lg"></i>
					</button>
				</div>

				<div
					class="btn-group-vertical nf4-menu-settings nf4-menu-button__group"
					role="group">
					<!-- 菜单设置项区域 -->
					
					<button type="button" class="btn" id="menu__favorite">
						<!-- 收藏按钮 id="menu__favorite" -->
						<i class="fa fa-bookmark-o fa-lg"></i>
					</button>
					<button type="button" class="btn" id="menu__share">
						<!-- 分享按钮 id="menu__share" -->
						<i class="fa fa-share-square-o fa-lg"></i>
					</button>
				</div>

			</div>

			<div class="nf4-element">
				<!-- 元素内容区域 -->
				<div class="btn-group-vertical nf4-element__group">
					<!-- 元素块选择区 -->
					<button type="button" class="btn" id="nf4-element__text">
						<!-- 元素__文字 id="nf4-element__text" -->
						<i class="fa fa-font fa-2x"></i>
						<div class="nf4-element-name">文本</div>
					</button>

					<button type="button" class="btn" id="nf4-element__image">
						<!-- 元素__图片 id="nf4-element__image" -->
						<i class="fa fa-image fa-2x"></i>
						<div class="nf4-element-name">图片</div>
					</button>
					<!-- 图片传入接口 -->
					<form class="nf4-element__load-image" style="display:none">
						<input type="file" id="nf4-element__load-image"
							style="display:none" />
					</form>

					<button type="button" class="btn" id="nf4-element__shape"
						data-toggle="modal" data-target="#myShapeModal">
						<!-- 元素__形状 id="nf4-element__shape" -->
						<i class="fa fa-area-chart fa-2x"></i>
						<div class="nf4-element-name">形状</div>
					</button>

					<button type="button" class="btn" id="nf4-element__code">
						<!-- 元素__代码 id="nf4-element__code" -->
						<i class="fa fa-code fa-2x"></i>
						<div class="nf4-element-name">代码</div>
					</button>

					<!-- 测试
                    <button type="button" class="btn">
                        <i class="fa fa-ravelry fa-2x"></i>
                        <div class="nf4-element-name">测试</div>
                    </button>
					-->
				</div>

			</div>

			<div class="nf4-display">
				<!-- 中心显示区域 -->
				<div class="nf4-workspace__container">
					<div class="nf4-workspace"></div>
				</div>
			</div>

			<div class="nf4-browser">
				<!-- 幻灯片浏览区域 -->
				<div class="nf4-browser-view">
					<!-- 幻灯片视图区 -->
				</div>
				<div class="nf4-browser-control">
					<!-- 幻灯片浏览区按钮 -->
					<button type="button" class="btn" id="nf4-add-slides">
						<!-- 添加幻灯片按钮 id="nf4-add-slides" -->
						<span>+</span> <span>添加幻灯片</span>
					</button>
				</div>

			</div>
		</div>

		<!-- 页面控件区域 -->
		<div class="nf4-utilities__popovers">
			<div class="nf4-popovers-item" id="nf4-Popovers__on-save">
				<!-- 正在保存 -->
				<div class="nf4-popovers-item__sym">
					<span> <i class="fa fa-spinner fa-pulse fa-lg"></i>
					</span>
				</div>
				<div class="nf4-popovers-item__font">
					<span>正在保存...</span>
				</div>
				<div class="nf4-popovers-item__close">
					<span> <i class="fa fa-close fa-1x"></i>
					</span>
				</div>
			</div>

			<div class="nf4-popovers-item" id="nf4-Popovers__save-succeed">
				<!-- 保存成功 -->
				<div class="nf4-popovers-item__sym">
					<span> <i class="fa fa-check fa-lg"></i>
					</span>
				</div>
				<div class="nf4-popovers-item__font">
					<span>保存成功</span>
				</div>
				<div class="nf4-popovers-item__close">
					<span> <i class="fa fa-close fa-1x"></i>
					</span>
				</div>
			</div>

			<div class="nf4-popovers-item" id="nf4-Popovers__save-failure">
				<!-- 保存失败 -->
				<div class="nf4-popovers-item__sym">
					<span> <i class="fa fa-info fa-lg"></i>
					</span>
				</div>
				<div class="nf4-popovers-item__font">
					<span>保存失败</span>
				</div>
				<div class="nf4-popovers-item__close">
					<span> <i class="fa fa-close fa-1x"></i>
					</span>
				</div>
			</div>

			<div class="nf4-popovers-item" id="nf4-Popovers__auto-save">
				<!-- 自动保存 -->
				<div class="nf4-popovers-item__sym">
					<span> <i class="fa fa-clock-o fa-lg"></i>
					</span>
				</div>
				<div class="nf4-popovers-item__font">
					<span>自动保存成功</span>
				</div>
				<div class="nf4-popovers-item__close">
					<span> <i class="fa fa-close fa-1x"></i>
					</span>
				</div>
			</div>
		</div>
	</div>

	<!-- 滑动菜单组件 -->
	<div class="nf4-dropdownMenu">
		<div class="nf4-dropdownMenu__control">
			<div class="dropdown-panel nf4-dropdown__user">
				<span class="dropdown-panel__heading">我的账户</span>
				<div class="dropdown-panel__close">
					<span><i class="fa fa-close fa-lg"></i></span>
				</div>
			</div>
			<div class="dropdown-content nf4-dropdown__user">
				<div class="nf4-dropdown__user-content">
					<div class="nf4-dropdown__user-avator">
						<img class="user-avator__img" id="navbar__user-img"
							src="./img/avator/avator_1.png" alt="User"></img>
					</div>
					<span class="nf4-dropdown__name">Billows Tao</span>
				</div>
				<ul class="nf4-dropdown__user-control">
					<li><a href="usercenter.jsp"><span class="nf4-dropdown__link"
						id="nf4-link__user-center">查看个人中心</span></a></li>
					<li><a href="home.jsp?logout=true"><span class="nf4-dropdown__link" id="nf4-link__logout">注销</span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!--弹出设置窗口-->
	<div id="dialog-form" class="container" title="幻灯片播放设置"
		style="z-index: 600">
		<div style="width: 70%;left: 15%;position: absolute">
			<div class="form-group row">
				<label for="autoSlide" class="col-sm-4 text-center form-control">自动播放</label>
				<div class="col-sm-8">
					<select id="autoSlide" class="form-control text-center">
						<option value="autoSlide:0,">无自动播放</option>
						<option value="autoSlide:3000,">每3秒自动播放下一页</option>
						<option value="autoSlide:5000,">每5秒自动播放下一页</option>
						<option value="autoSlide:7000,">每7秒自动播放下一页</option>
					</select>
				</div>
			</div>
			<div class="form-group row ">
				<label for="themes" class="col-sm-4 text-center form-control">主题</label>
				<div class="col-sm-8">
					<select id="themes" class="form-control text-center">
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/sky.css">'
							style="color: darkgray ;background: lightblue ">天空风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/black.css">'
							style="color: white;background: black">酷黑风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/white.css">'
							style="color: black;background: white">炫白风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/league.css">'
							style="color: white ;background: Gray ">奶奶灰风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/beige.css">'
							style="color: darkred  ;background: Beige ">米色风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/moon.css">'
							style="color: floralwhite ;background: midnightblue ">夜色风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/serif.css">'
							style="color: darkgray ;background:moccasin ">卡布奇风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/simple.css">'
							style="color: darkslategrey  ;background: whitesmoke  ">简约风格
                    </option>
						<option
							value='<link rel="stylesheet" href="./js/plugin/reveal.js/css/theme/solarized.css">'
							style="color: darkgreen ;background: mintcream ">曝光风格
                    </option>
					</select>
				</div>
			</div>
			<div class="form-group row ">
				<label for="slideMethod" class="col-sm-4 text-center form-control">滑动特效</label>
				<div class="col-sm-8">
					<select id="slideMethod" class="form-control text-center">
						<option value="transition: 'slide',">滑动变换</option>
						<option value="transition: 'fade',">渐逝变换</option>
						<option value="transition: 'convex',">突出变换</option>
						<option value="transition: 'concave',">凹陷变换</option>
						<option value="transition: 'zoom',">缩放变换</option>
						<option value="transition: 'none',">无变换样式</option>
					</select>
				</div>
			</div>
			<div class="form-group row ">
				<label for="slideMethod" class="col-sm-6 text-center form-control">是否显示幻灯片编号</label>
				<div class="col-sm-6 nf4-radio">
					<label id="numTrue" class="btn btn-danger " style="width: 50%">
						<input type="radio" name="optionsNum" autocomplete="off" checked>是
					</label> <label id="numFalse" class="btn btn-danger" style="width: 50%">
						<input type="radio" name="optionsNum" autocomplete="off">否
					</label>
				</div>
			</div>
			<div class="form-group row ">
				<label for="slideSpeed" class="col-sm-4 text-center form-control">翻页速度</label>
				<div class="col-sm-8">
					<select id="slideSpeed" class="form-control text-center">
						<option value="transitionSpeed: 'default',">普通速度</option>
						<option value="transitionSpeed: 'fast',">较快的速度</option>
						<option value="transitionSpeed: 'slow',">较慢的速度</option>
					</select>
				</div>
			</div>
			<div class="form-group row ">
				<label for="slideMethod" class="col-sm-6 text-center form-control">是否显示进度条</label>
				<div class="col-sm-6 nf4-radio" id="nf4-proRadio">
					<label id="proTrue" class="btn btn-danger " style="width: 50%">
						<input type="radio" name="optionsPro" autocomplete="off" checked>是
					</label> <label id="proFalse" class="btn btn-danger" style="width: 50%">
						<input type="radio" name="optionsPro" autocomplete="off">否
					</label>
				</div>
			</div>
			<div class="row">
				<button id="closeDialog" class="btn btn-success btn-block">确定</button>
			</div>
		</div>
	</div>
	<!--形状模态框-->
	
	<div class="modal fade" id="myShapeModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">形状库</h4>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-96.000000, -480.000000)">
                                    <path
									d="M96,480 L96,496 L112,496 L112,480 L96,480 Z M96,480"
									id="Rectangle 152"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-192.000000, -480.000000)">
                                    <path
									d="M195.996203,480 C193.789161,480 192,481.78883 192,483.996203 L192,492.003797 C192,494.210839 193.78883,496 195.996203,496 L204.003797,496 C206.210839,496 208,494.21117 208,492.003797 L208,483.996203 C208,481.789161 206.21117,480 204.003797,480 L195.996203,480 Z M195.996203,480"
									id="Rectangle 153"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-480.000000, -480.000000)">
                                    <path
									d="M488,480 L496,496 L480,496 L488,480 Z M488,480"
									id="Triangle 155"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(0.000000, -480.000000)">
                                    <path
									d="M8,496 C12.4182782,496 16,492.418278 16,488 C16,483.581722 12.4182782,480 8,480 C3.58172178,480 0,483.581722 0,488 C0,492.418278 3.58172178,496 8,496 Z M8,496"
									id="Oval 151"></path>
                                </g>
                            </g>
                        </svg>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-144.000000, -96.000000)">
                                    <path
									d="M154,107 L144,107 L144,101 L154,101 L154,98 L160,104 L154,110 L154,107 L154,107 Z M154,107"
									id="Shape"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-384.000000, -480.000000)">
                                    <path
									d="M392,491.953125 L387.022583,495.99353 L389.032704,489.524391 L384.015259,486.030029 L390.052612,486.030029 L392,480 L393.984253,486.030029 L400.065063,486.030029 L394.967296,489.524392 L397.036133,495.99353 L392,491.953125 Z M392,491.953125"
									id="Star 240 copy"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg"
								xmlns:ev="http://www.w3.org/2001/xml-events"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
								id="7581697901" x="0px" y="0px" width="100%" height="100%"
								viewBox="0 0 56.84 49.636"
								enable-background="new 0 0 56.84 49.636" xml:space="preserve"
								preserveAspectRatio="none" class="element svg-element">
<path fill="currentColor"
									d="M28.487,10.847C21.13-6.364,0.18-2.348,0.08,17.628C0,33.538,27.699,46.784,28.531,49.636  C29.285,46.675,57,33.785,56.92,17.509C56.823-2.517,35.506-5.678,28.487,10.847z"></path>
</svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-720.000000, -528.000000)">
                                    <path
									d="M728,544 C732.418278,544 736,540.418278 736,536 C736,531.581722 732.418278,528 728,528 C723.581722,528 720,531.581722 720,536 C720,540.418278 723.581722,544 728,544 L728,544 Z M732,536 L725,540 L725,532 L732,536 L732,536 Z M732,536"
									id="Shape"></path>
                                </g>
                            </g>
                        </svg>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-288.000000, -528.000000)">
                                    <path
									d="M296,544 C291.581722,544 288,540.418278 288,536 C288,531.581722 291.581722,528 296,528 C300.418278,528 304,531.581722 304,536 C304,540.418278 300.418278,544 296,544 L296,544 Z M299,532 L297,532 L297,536 L297,540 L299,540 L299,532 L299,532 Z M295,532 L293,532 L293,536 L293,540 L295,540 L295,532 L295,532 Z M295,532"
									id="Shape copy 3"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-720.000000, -192.000000)">
                                    <path
									d="M728,205 C727.092687,205 726.225692,204.86437 725.430064,204.617506 C724.617765,205.250987 723.248775,206.131104 721.912598,206.131104 C722.612692,205.468456 722.810743,204.184192 722.854217,203.228743 C721.703165,202.248586 721,200.938813 721,199.5 C721,196.462434 724.134007,194 728,194 C731.865993,194 735,196.462434 735,199.5 C735,202.537566 731.865993,205 728,205 Z M728,205"
									id="Oval 247"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-288.000000, -48.000000)">
                                    <path
									d="M290,51 L290,58 L302,58 L302,51 Z M290.006845,50 L301.993155,50 C302.550051,50 303,50.446616 303,50.9975446 L303,58.0024554 C303,58.5536886 302.54922,59 301.993155,59 L290.006845,59 C289.449949,59 289,58.553384 289,58.0024554 L289,50.9975446 C289,50.4463114 289.45078,50 290.006845,50 Z M289,61 L289,60 L295,60 L295,61 L297,61 L297,60 L303,60 L303,61 C303,61.5522847 302.552285,62 302,62 L290,62 C289.447715,62 289,61.5522847 289,61 Z M289,61"
									id="Rectangle 130"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-384.000000, -48.000000)">
                                    <path
									d="M388.999615,48 L395.000385,48 C396.112567,48 397,48.8927712 397,49.9940603 L397,62.0059397 C397,63.1054862 396.104742,64 395.000385,64 L388.999615,64 C387.887433,64 387,63.1072288 387,62.0059397 L387,49.9940603 C387,48.8945138 387.895258,48 388.999615,48 Z M388,51 L388,61 L396,61 L396,51 Z M391,62 L391,63 L393,63 L393,62 L391,62 Z M390,49 L390,50 L394,50 L394,49 L390,49 Z M390,49"
									id="Rectangle 129"></path>
                                </g>
                            </g>
                        </svg>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-432.000000, -48.000000)">
                                    <path
									d="M437,49 L437,54 L443,54 L443,49 Z M436.99703,48 L443.00297,48 C443.546964,48 444,48.4481055 444,49.0008717 L444,62.9991283 C444,63.5553691 443.553614,64 443.00297,64 L436.99703,64 C436.453036,64 436,63.5518945 436,62.9991283 L436,49.0008717 C436,48.4446309 436.446386,48 436.99703,48 Z M440,62 C441.656854,62 443,60.6568543 443,59 C443,57.3431457 441.656854,56 440,56 C438.343146,56 437,57.3431457 437,59 C437,60.6568543 438.343146,62 440,62 Z M440,60 C439.447715,60 439,59.5522848 439,59 C439,58.4477152 439.447715,58 440,58 C440.552285,58 441,58.4477152 441,59 C441,59.5522848 440.552285,60 440,60 Z M440,60"
									id="Rectangle 127"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-336.000000, -192.000000)">
                                    <path
									d="M347.999959,195 L350,195 L350,196 L349,196 L349,207.001498 C349,207.552511 348.554265,208 348.004423,208 L338.995577,208 C338.444837,208 338,207.552955 338,207.001498 L338,196 L337,196 L337,195 L338.995577,195 L339.000042,195 L339,194.990631 L339,193.009369 C339,192.443353 339.446616,192 339.997545,192 L347.002455,192 C347.553689,192 348,192.45191 348,193.009369 L348,194.990631 Z M340,194 L340,195 L347,195 L347,194 C347,193.447715 346.552285,193 346,193 L341,193 C340.447715,193 340,193.447715 340,194 Z M339,196 L339,207 L348,207 L348,196 Z M341,197 L342,197 L342,206 L341,206 Z M343,197 L344,197 L344,206 L343,206 Z M345,197 L345,206 L346,206 L346,197 L345,197 Z M345,197"
									id="Rectangle 159"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-336.000000, 0.000000)">
                                    <path
									d="M345.731959,8.48453617 L350.681755,12.7272182 C350.501681,12.8954635 350.259495,13 349.993155,13 L338.006845,13 C337.739189,13 337.496237,12.8970552 337.316068,12.7290845 L342.268041,8.48453617 L344,10.0000001 Z M344,9 L337.318245,3.27278178 C337.498319,3.10453648 337.740505,3 338.006845,3 L349.993155,3 C350.260811,3 350.503763,3.10294483 350.683932,3.27091553 Z M351,12.1856084 L346.167358,8.07885766 L351,3.875422 L351,12.1856084 L351,12.1856084 Z M337,12.1856079 L337,3.87815189 L341.832642,8.07885742 L337,12.1856079 L337,12.1856079 Z M337,12.1856079"
									id="Shape"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-480.000000, -192.000000)">
                                    <path
									d="M486,197.8 L486,205.598145 L485.999914,205.598093 C486.007349,206.311236 485.532942,207.115366 484.550427,207.62066 C483.185882,208.322426 481.750973,207.979941 481.225759,207.169611 C480.700546,206.359281 481.085029,205.081106 482.449573,204.37934 C483.367261,203.907386 484.316775,203.907734 485,204.205054 L485,198 L485,194 L495,192 L495,193 L495,196 L495,204.600464 L494.999937,204.600489 C495.006283,205.313042 494.531839,206.115934 493.550427,206.62066 C492.185882,207.322426 490.750973,206.979941 490.225759,206.169611 C489.700546,205.359281 490.085029,204.081106 491.449573,203.37934 C492.367261,202.907386 493.316775,202.907734 494,203.205054 L494,196.2 Z M486,197.8"
									id="Rectangle 233"></path>
                                </g>
                            </g>
                        </svg>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-768.000000, -96.000000)">
                                    <path
									d="M776,108 C778.209139,108 780,106.209139 780,104 C780,101.790861 778.209139,100 776,100 C773.790861,100 772,101.790861 772,104 C772,106.209139 773.790861,108 776,108 Z M780.890749,100.523464 C781.405567,101.246403 781.764097,102.088347 781.917042,103 L784,103 L784,105 L781.917042,105 C781.706284,106.256253 781.105149,107.380135 780.940826,107.526613 L782.363961,108.949748 L780.949748,110.363961 L779.476536,108.89075 C778.753613,109.405561 777.911662,109.764096 777,109.917042 L777,112 L775,112 L775,109.917042 C774.088338,109.764096 773.246387,109.405561 772.523464,108.89075 L771.050253,110.363961 L769.636039,108.949748 L771.109251,107.476536 C770.594433,106.753597 770.235903,105.911653 770.082958,105 L768,105 L768,103 L770.082958,103 C770.293716,101.743747 770.894851,100.619865 771.059174,100.473387 L769.636039,99.0502526 L771.050253,97.636039 L772.523464,99.1092504 C773.246387,98.5944389 774.088338,98.2359041 775,98.0829584 L775,96 L777,96 L777,98.0829584 C777.911662,98.2359041 778.753613,98.5944389 779.476536,99.1092505 L780.949748,97.636039 L782.363961,99.0502526 L781.009978,100.404236 Z M780.890749,100.523464"
									id="Oval 228"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-432.000000, -48.000000)">
                                    <path
									d="M437,49 L437,54 L443,54 L443,49 Z M436.99703,48 L443.00297,48 C443.546964,48 444,48.4481055 444,49.0008717 L444,62.9991283 C444,63.5553691 443.553614,64 443.00297,64 L436.99703,64 C436.453036,64 436,63.5518945 436,62.9991283 L436,49.0008717 C436,48.4446309 436.446386,48 436.99703,48 Z M440,62 C441.656854,62 443,60.6568543 443,59 C443,57.3431457 441.656854,56 440,56 C438.343146,56 437,57.3431457 437,59 C437,60.6568543 438.343146,62 440,62 Z M440,60 C439.447715,60 439,59.5522848 439,59 C439,58.4477152 439.447715,58 440,58 C440.552285,58 441,58.4477152 441,59 C441,59.5522848 440.552285,60 440,60 Z M440,60"
									id="Rectangle 127"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg" height="100%"
								version="1.1" viewBox="0 0 16 16" width="100%"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:sketch="http://www.bohemiancoding.com/sketch/ns"
								xmlns:xlink="http://www.w3.org/1999/xlink"
								preserveAspectRatio="none">
								<title></title>
                            <defs></defs>
                            <g fill="none" fill-rule="evenodd"
									id="Icons with numbers" stroke="none" stroke-width="1">
                                <g fill="currentColor" id="Group"
									transform="translate(-528.000000, -192.000000)">
                                    <path
									d="M528,194 L544,194 L544,206 L528,206 Z M529,195 L529,205 L543,205 L543,195 Z M530.006104,204.026611 C530.006104,204.026611 531.046143,197.003662 532.590332,197.003662 C534.134521,197.003662 534.810913,201.95752 536.053223,201.95752 C537.295532,201.95752 537.201904,200.021851 538.111328,200.021851 C539.020752,200.021851 542.151123,204.026611 542.151123,204.026611 Z M540.5,199 C539.671573,199 539,198.328427 539,197.5 C539,196.671573 539.671573,196 540.5,196 C541.328427,196 542,196.671573 542,197.5 C542,198.328427 541.328427,199 540.5,199 Z M540.5,199"
									id="Oval 236"></path>
                                </g>
                            </g>
                        </svg>
						</div>
						<div class="col-md-3">
							<svg data-dismiss="modal" class="index-svg"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"
								width="100%" height="100%" preserveAspectRatio="none">
                            <circle cx="50" cy="50" r="40"
									fill="currentColor"></circle>
                            <path d="M 100 50 L 85 40 L 85 60 Z"
									fill="currentColor"></path>
                        </svg>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<!--图片属性模态框-->
	<button id="ImageProperty" data-toggle="modal"
		data-target="#ImageModel" style="display: none"></button>
	<div class="modal fade" id="ImageModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="ImageLabel">图片属性</h4>
				</div>
				<div class="container-fluid">
					<div class="row">
						<ul>
							<li>透明度 <span id="title">0</span>
								<div class="scale_panel">
									<span class="r">100</span>0
									<div class="scale" id="bar">
										<div></div>
										<span id="btn"></span>
									</div>
								</div>
							</li>
							<li>饱和度 <span id="title2">0</span>
								<div class="scale_panel">
									<span class="r">100</span>0
									<div class="scale" id="bar2">
										<div></div>
										<span id="btn2"></span>
									</div>
								</div>
							</li>
							<li>色相反转 <span id="title4">0</span>
								<div class="scale_panel">
									<span class="r">100</span>0
									<div class="scale" id="bar4">
										<div></div>
										<span id="btn4"></span>
									</div>
								</div>
							</li>
						</ul>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button id="ImagePropertyButton" type="button"
						class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- javascript依赖库导入 -->
	<script src="./js/plugin/jquery-ui.min.js" type="text/javascript"></script>
	<script src="./js/html2canvas.min.js" type="text/javascript"></script>
	<script src="./js/plugin/popper.min.js" type="text/javascript"></script>
	<script src="./js/plugin/bootstrap.min.js" type="text/javascript"></script>
	<script
		src="./js/plugin/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
	<!-- 页面javascript控制 -->
	<script src="./js/index_js.js" type="text/javascript"></script>
	<!-- CKEditor延迟加载 -->
	<script src="./js/plugin/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script src="./js/nf4-script-dx.js" type="text/javascript"></script>
	<script src="./js/nf4-playSlide.js" type="text/javascript"></script>
	<script type="text/javascript">
		for (i = 0; i < editHtml.length - 1; i++) {
			addSlide(".nf4-workspace", ".nf4-browser-view", "reAdd", editHtml[i]);
		}
		changeSilde("tabs-1");
	</script>
	<!-- 无javascript提示 -->
	<noscript>
		<p>本页面需要启用javascript支持</p>
	</noscript>

</body>

<!-- 

    icon
fa fa-compress 缩小屏幕
fa fa-image
fa fa-bookmark bookmark-o 书签
fa fa-code 代码
fa fa-close 关闭
fa fa-edit 关闭
fa fa-download 下载
fa fa-remove 移出
fa fa-sign-in 登录
fa fa-sign-out 注销
fa fa-adjust 调整
fa fa-keyboard-o 键盘
fa fa-magic 风格
question
question-circle
question-circle-o 帮助
refresh 刷新
share-square-o 分享
trash 垃圾桶
trash-o 垃圾桶
home 首页
user-circle 用户
font 字体
image 图片
table 表格

合成图标：
square 
square-o 矩形
star
star-o 星
circle 圆

-->


</html>