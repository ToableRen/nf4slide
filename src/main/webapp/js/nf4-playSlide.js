var isDisplayPro = "true,";
var isDisplayNum = "true,";
$("#nf4-menu__play_slides").click(function () {
    alert("test");
});

function playSlide() {
    var playHtmlCode = "";
    $(".nf4-slide").each(function () { //循环每一个幻灯片
        var ExId = $(this).attr("id").substr(6);
        playHtmlCode = playHtmlCode + "<section> <div style=\"height:700px;width:960px\">";
        //先文
        $("#tabs-" + ExId + " .nf4-text").each(function () { //循环幻灯片内每一个文本组件x`
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
        $("#tabs-" + ExId + " .nf4-image").each(function () { //循环幻灯片内每一个文本组件
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            if ($(this).width() != 0) {
                styleCode = styleCode + "width:" + $(this).width() + "px" + ";";
            }
            if ($(this).height() != 0) {
                styleCode = styleCode + "height:" + $(this).height() + "px" + ";";
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


        //代码
        $("#tabs-" + ExId + " .nf4-code").each(function () {//循环幻灯片内每一个代码组件
            $("span").remove(".cke_reset");
            $("span").css("float", "left");
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
        $("#tabs-" + ExId + " .nf4-shape").each(function () { //循环幻灯片内每一个文本组件
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
        $("#tabs-" + ExId + " .nf4-line").each(function () { //循环幻灯片内每一个文本组件
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
        $("#tabs-" + ExId + " .nf4-draw").each(function () { //循环幻灯片内每一个文本组件
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
    return playHtmlCode;
}

$(function () {
    $(".nf4-radio").buttonset();
});
$("#dialog-form").dialog({
    autoOpen: false,
    height: 500,
    width: 500,
    modal: true,
    dialogClass: "no-close",
    show: {
        effect: "blind",
        duration: 500
    },
    hide: {
        effect: "explode",
        duration: 500
    },
    close: function () {
        $(this).dialog("close");
    }
});

$("#nf4-navbar__settings").button().click(function () {
    $("#dialog-form").dialog("open");
});
$("#closeDialog").button().click(function () {
    $("#dialog-form").dialog("close");
});
$("#proTrue").click(function () {
    isDisplayPro = "true,";
});
$("#proFalse").click(function () {
    isDisplayPro = "false,";
});
$("#numTrue").click(function () {
    isDisplayNum = "true,";
});
$("#numFalse").click(function () {
    isDisplayNum = "false,";
});

$("#menu__share").click(function () {

})
