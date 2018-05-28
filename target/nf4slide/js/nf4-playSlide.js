var playHtmlCode;
var beforeCode;
var afterCode;
var isDisplayPro = "progress: true,";
var isDisplayNum = "slideNumber: true,";
$("#nf4-menu__play_slides").click(function() {
	playSlide();
	var win = window.open("");
	win.document.write(beforeCode + playHtmlCode + afterCode);
	win.document.close();
});
function playSlide() {
	var plug = $("#autoSlide option:selected").val() + $("#slideMethod option:selected").val() + $("#slideSpeed option:selected").val() + isDisplayPro + isDisplayNum;
	beforeCode = "<!doctype html><html>" +
			"<head><meta charset=\"utf-8\">" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">" +
			"<title>播放</title><link rel=\"stylesheet\" href=\"./js/plugin/reveal.js/css/reveal.css\">" + $("#themes option:selected").val() + 
			"<link rel=\"stylesheet\" href=\"./js/plugin/reveal.js/lib/css/zenburn.css\">" +
			"<style>p{ }.reveal section img {margin:0px;background:none;border:none;box-shadow:none;max-width: 100%;max-height:100%;}</style>" +
			"</head>" +
			"<body>" +
			"<image id=\"send\"src=\"img/damoo.png\" style=\"cursor:pointer;position:absolute;bottom:4%;right:120px;z-index: 997;height:75px;width:75px;display:none\"/>"+
			"<div id=\"danmuPlace\" style=\"position:absolute;width:100%;height:75%;top:100px\"></div>" +
			"<div class=\"btn-group\" role=\"\"  id=\"danmu-send-place\"style=\"position:absolute;bottom:0px;width:100%;height: 10%;background:white;z-index: 998;display:none\">" +
			"<input  placeholder=\"请输入弹幕内容\" class=\"\" id=\"danmu-send-place-text\"type=\"text\" style=\"border-bottom-left-radius:15px;border-top-left-radius:15px;border:1px solid gray;position:absolute;top:15%;left:2%;font-size: 150%;height:70%;width:70%\">" +
			"<button id=\"danmu-send-place-send\" class=\"\" style=\"cursor:pointer;position:absolute;top:15%;font-size: 150%;height:75%;width:13%;left:72%;border:1px solid gray;\">发送</button>" +
			"<button id=\"danmu-send-place-cancel\" class=\"\" style=\"cursor:pointer;border-bottom-right-radius:15px;border-top-right-radius:15px;position:absolute;top:15%;font-size: 150%;height:75%;width:13%;left:85%;border:1px solid gray;\">关闭</button>" +
			"</div>" +
			"<div class=\"reveal\">" +
			"<div class=\"slides\" id=\"slides\">";
	afterCode = "</div></div><script src=\"./js/plugin/reveal.js/lib/js/head.min.js\"></script><script src=\"./js/plugin/reveal.js/js/reveal.js\"></script><script>Reveal.initialize({" + plug + "cnter:false,dependencies: [{ src: 'plugin/markdown/marked.js' },{ src: 'plugin/markdown/markdown.js' },{ src: 'plugin/notes/notes.js', async: true },{ src: 'plugin/highlight/highlight.js', async: true, callback: function() { } }]});</script></body></html>";
	playHtmlCode = "";
	$(".nf4-slide").each(function() { //循环每一个幻灯片
		var ExId = $(this).attr("id").substr(6);
		playHtmlCode = playHtmlCode + "<section> <div style=\"height:700px;width:960px\">";
		//先文
		$("#tabs-" + ExId + " .nf4-text").each(function() { //循环幻灯片内每一个文本组件x`
			var styleCode = "position:absolute;";
			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
			styleCode = styleCode + "left:" + $(this).css("left") + ";";
			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + "align=\"left\">";
			styleCode = "";

			var container = $("#" + $(this).attr("id") + " .nf4-text-editor").html(); //第二层
			styleCode = styleCode + "width:" + $(this).css("width") + ";";
			styleCode = styleCode + "height:" + $(this).css("height") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + ">" + container;
			playHtmlCode = playHtmlCode + "</div></div>";
		});
		//图片
		$("#tabs-" + ExId + " .nf4-image").each(function() { //循环幻灯片内每一个文本组件
			var styleCode = "position:absolute;";
			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
			styleCode = styleCode + "left:" + $(this).css("left") + ";";
			if( $(this).width()!=0){
				styleCode = styleCode + "width:" + $(this).width()+"px" + ";";
			}
			if( $(this).height()!=0){
				styleCode = styleCode + "height:" + $(this).height()+"px" + ";";
			}
			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
			styleCode = styleCode + " transform:" + $(this).css("transform") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
			styleCode = "";

			//第二层
			var opacity = $("#" + $(this).attr("id") + " .nf4-image-editor").css("opacity");
			styleCode = styleCode + "width:100%;";
			styleCode = styleCode + "height:100%;";
			styleCode = styleCode + "opacity:" + opacity + ";";
			styleCode = "style=\"" + styleCode + "\"";
			var src = $("#" + $(this).attr("id") + " .nf4-image-editor").attr("src");
			src = "src=\"" + src + "\"";
			playHtmlCode = playHtmlCode + "<img  " + styleCode + src + ">";
			playHtmlCode = playHtmlCode + "</div>";
		});

//		//代码
//		var ExCodeId = 1;
//		$("#tabs-" + ExId + " .nf4-code").each(function() { //循环幻灯片内每一个代码组件
//			var styleCode = "position:absolute;";
//			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
//			styleCode = styleCode + "left:" + $(this).css("left") + ";";
//			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
//			styleCode = "style=\"" + styleCode + "\"";
//			playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
////			html2canvas($("#nf4-code-2"), {
////				onrendered : function(canvas) {
////					var coding = canvas.toDataURL();
////					console.log(coding);
////					var str = "";
////					str = "<img src=\"" + coding + "\">";
////					alert("begin");
////					playHtmlCode = playHtmlCode +str;
////					alert("end");
////				},allowTaint:true
////			});
//			
//			styleCode = "";
//			var container = $("#" + $(this).attr("id")).html(); //第二层
//			styleCode = styleCode + "width:" + $(this).css("width") + ";";
//			styleCode = styleCode + "height:" + $(this).css("height") + ";";
//			styleCode = "style=\"" + styleCode + "\"";
//			playHtmlCode = playHtmlCode +container;
//			playHtmlCode = playHtmlCode + "</div>";
//			ExCodeId++;
//		});
		
		//代码
        $("#tabs-" + ExId + " .nf4-code").each(function () {//循环幻灯片内每一个代码组件
            $("span").remove(".cke_reset");
            $("span").css("float","left");
            var CODE = $(".nf4-code .cke_widget_wrapper ").html();
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";";//第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            // var container = $("#" + $(this).attr("id")).html();//第二层
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">" + CODE;
            playHtmlCode = playHtmlCode + "</div></div>";

        });

		
		//形状nf4-shape
		$("#tabs-" + ExId + " .nf4-shape").each(function() { //循环幻灯片内每一个文本组件
			var styleCode = "position:absolute;";
			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
			styleCode = styleCode + "left:" + $(this).css("left") + ";";
			styleCode = styleCode + "width:" + $(this).css("width") + ";";
			styleCode = styleCode + "height:" + $(this).css("height") + ";";
			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
			styleCode = "";

			//第二层
			var container = $("#" + $(this).attr("id")).html(); //第二层

			playHtmlCode = playHtmlCode + container;
			playHtmlCode = playHtmlCode + "</div>";
		});

		//线条nf4-shape
		$("#tabs-" + ExId + " .nf4-line").each(function() { //循环幻灯片内每一个文本组件
			var styleCode = "position:absolute;";
			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
			styleCode = styleCode + "left:" + $(this).css("left") + ";";
			styleCode = styleCode + "width:" + $(this).css("width") + ";";
			styleCode = styleCode + "height:" + $(this).css("height") + ";";
			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
			styleCode = "";

			//第二层
			var container = $("#" + $(this).attr("id")).html(); //第二层

			playHtmlCode = playHtmlCode + container;
			playHtmlCode = playHtmlCode + "</div>";
		});

		//绘画nf4-draw
		$("#tabs-" + ExId + " .nf4-draw").each(function() { //循环幻灯片内每一个文本组件
			var styleCode = "position:absolute;";
			styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
			styleCode = styleCode + "left:" + $(this).css("left") + ";";
			styleCode = styleCode + "width:" + $(this).css("width") + ";";
			styleCode = styleCode + "height:" + $(this).css("height") + ";";
			styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
			styleCode = "style=\"" + styleCode + "\"";
			playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
			styleCode = "";

			//第二层
			var container = $("#" + $(this).attr("id")).html(); //第二层

			playHtmlCode = playHtmlCode + container;
			playHtmlCode = playHtmlCode + "</div>";
		});
		playHtmlCode = playHtmlCode + "</div></section>";
	});
}

$(function() {
	$(".nf4-radio").buttonset();
});
$("#dialog-form").dialog({
	autoOpen : false,
	height : 500,
	width : 500,
	modal : true,
	dialogClass : "no-close",
	show : {
		effect : "blind",
		duration : 500
	},
	hide : {
		effect : "explode",
		duration : 500
	},
	close : function() {
		$(this).dialog("close");
	}
});

$("#nf4-navbar__settings").button().click(function() {
	$("#dialog-form").dialog("open");
});
$("#closeDialog").button().click(function() {
	$("#dialog-form").dialog("close");
});
$("#proTrue").click(function() {
	isDisplayPro = "progress: true,";
});
$("#proFalse").click(function() {
	isDisplayPro = "progress: false,";
});
$("#numTrue").click(function() {
	isDisplayNum = "slideNumber: true,";
});
$("#numFalse").click(function() {
	isDisplayNum = "slideNumber: false,";
});

$("#menu__share").click(function(){
	
})
