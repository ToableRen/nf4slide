//页面内容初始化
function pageContentInit(playSlideId, isControl, data) {
    var json = eval('(' + data + ')');
    $(".slides").html(json.play);//填充幻灯片内容
    $("#theme").attr("href", "js/plugin/reveal.js/css/theme/" + json.theme + ".css");//读取主题
    //config顺序
    var config = eval('(' + json.config + ')');
    Reveal.initialize({
        center: false,
        autoSlide: config.autoSlide,//自动播放
        transitionSpeed: config.transitionSpeed,//换页速度
        transition: config.transition,//换页方式
        progress: config.progress,//是否进度条
        slideNumber: config.slideNumber//是否显示页数
    });
    if (isControl == "true") {
        danmuInit(playSlideId, json.whoPlay);//弹幕初始化
        syncControlInit(playSlideId, json.whoPlay);//同步翻页初始化
    }
}

//得到参数
function getUrlParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        return null;

    }
}

// 弹幕初始化
function danmuInit(playSlideId, whoPlay) {
    /********************************************关于弹幕控制的部分*************************************************************/
    $("#send").css("display", "block");
    $("#send").click(function () {
        $("#danmu-send-place").css("display", "block");
        $("#send").css("display", "none");
    });
    $("#danmu-send-place-cancel").click(function () {
        $("#danmu-send-place").css("display", "none");
        $("#send").css("display", "block");
    });

    // With attributes
    $("#danmu-send-place-send").click(function () {
        bullet_websocket.send(playSlideId + ":" + $("#danmu-send-place-text").val());//发送给服务器
    });

    var damoo = new Damoo('danmuPlace', 'dm-canvas', 20);//初始化弹幕
    damoo.play();//激活弹幕

    /***************************************   websocket部分 **************************************************************/
    var bullet_websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        bullet_websocket = new WebSocket("ws://120.24.186.116/BulletWebSocket/websocket");
    }
    else {
        alert('Not support websocket')
    }
    //连接发生错误的回调方法

    bullet_websocket.onerror = function () {
        alert("弹幕功能发生错误");
    };

    //连接成功建立的回调方法
    bullet_websocket.onopen = function (event) {
        console.log("弹幕链接成功");
    }

    function randomHexColor() { //随机生成十六进制颜色
        var hex = Math.floor(Math.random() * 16777216).toString(16); //生成ffffff以内16进制数
        while (hex.length < 6) { //while循环判断hex位数，少于6位前面加0凑够6位
            hex = '0' + hex;
        }
        return '#' + hex; //返回‘#'开头16进制颜色
    }

    //接收到消息的回调方法
    bullet_websocket.onmessage = function (event) {
        var msg = new Array();
        msg = event.data.split(":");
        if (playSlideId == msg[0]) {
            damoo.emit({text: msg[1], color: randomHexColor()});
        }
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        bullet_websocket.close();
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }
}

// 同步控制初始化
function syncControlInit(playSlideId, whoPlay) {
    /********************************************关于换页控制的部分*************************************************************/
    var SlideNumNext = 0;
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://120.24.186.116/MyWebSocket/websocket");
    } else {
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = SlideNumNext;
        websocket.send(message);
    }

    $(".navigate-up").css("display", "none");
    $(".navigate-down").css("display", "none");

    $(".navigate-left").css("display", "none");
    $(".navigate-right").css("display", "none");

    //还需要判断是否是拥有者
    //Reveal.configure({ controls: false });//不可控制
    //接收到消息的回调方法
    if (whoPlay == "owner") {// 只有拥有者有权发出换页消息
        //发送消息
        Reveal.addEventListener('slidechanged', function (event) {
            // event.previousSlide, event.currentSlide, event.indexh, event.indexv
            SlideNumNext = playSlideId + ":" + event.indexh;
            send();
        });
    }

    websocket.onmessage = function (event) {
        var msg = new Array();
        msg = event.data.split(":");
        if (playSlideId == msg[0]) {
            Reveal.slide(msg[1]);
        }
    }


}

//二维码初始化
function dialogInit(isControl) {
    $("#dialog-form").dialog({
        autoOpen: false,
        height: 550,
        width: 350,
        modal: true,
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
    var dataUriPngImage = document.createElement("img"),
        u = window.location.href,
        s = QRCode.generatePNG(u, {
            ecclevel: "M",
            format: "html",
            fillcolor: "#FFFFFF",
            textcolor: "#373737",
            margin: 4,
            modulesize: 8
        });
    dataUriPngImage.src = s;
    dataUriPngImage.height = 300;
    dataUriPngImage.widht = 300;
    $("#dialog-form").append("<div style=\"font-size:130%\"id=\"msg\"></div>");
    document.getElementById('dialog-form').appendChild(dataUriPngImage);
    $("#dialog-form").append("<div style=\"font-size:130%\"align=\"center\">扫描上面的二维码，或者复制以下链接，将幻灯片分享给他人！</div><div style=\"font-size:130%\"align=\"center\">" + window.location.href + "</div>");
    if (isControl == "true") {
        $("#msg").html("分享幻灯片，并控制观看者的播放");
    }
    $("#dialog-form").dialog("open");
}
