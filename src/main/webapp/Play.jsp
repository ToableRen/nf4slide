<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.qtu404.present.slide.dao.impl.SlideDaoImpl" %>
<%@ page import="com.qtu404.present.slide.dao.SlideDao" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%
    request.setCharacterEncoding("utf-8");
    String userId = request.getParameter("userId");
    String slideId = request.getParameter("slideId");
    String control = request.getParameter("control");
    String userId_Session = (String) session.getAttribute("usrname");
    boolean flag = false;
    if (userId_Session != null && userId_Session.equals(userId)) {
        flag = true;
    }
%>
<%
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    SlideDao slideDao = (SlideDao) applicationContext.getBean("slideDao");
%>
<%= slideDao.fetchSlideById(slideId).getPlay()%>
<div id="dialog-form" class="container" title="网址分享"></div>
<link href="./css/plugin/jquery-ui_modify.min.css" rel="stylesheet">
<script src="./js/plugin/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./js/plugin/jquery-ui.min.js" type="text/javascript"></script>
<script src="./js/qrjs2.min.js" type="text/javascript"></script>
<script src="js/damoo.js" type="text/javascript"></script>
<script type="text/javascript">
    <%
        if(control!=null&&control.equals("true")){
        %>
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
        bullet_websocket.send(<%=userId%> +":" + <%=slideId%> +":" + $("#danmu-send-place-text").val());//发送给服务器
    });

    var damoo = new Damoo('danmuPlace', 'dm-canvas', 20);//初始化弹幕
    damoo.play();//激活弹幕
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
        if (<%=userId%>==msg[0] && <%=slideId%> == msg[1]
    )
        {
            damoo.emit({text: msg[2], color: randomHexColor()});
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


    <%
    if(!flag){//不是主讲人
        %>
    //Reveal.configure({ controls: false });//不可控制
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var msg = new Array();
        msg = event.data.split(":");
        if (<%=userId%>==msg[0] && <%=slideId%> == msg[1]
    )
        {
            Reveal.slide(msg[2]);
        }
    }
    $(".navigate-left").css("display", "none");
    $(".navigate-right").css("display", "none");
    <%
}
else{//主讲人
    %>
    //发送消息
    Reveal.addEventListener('slidechanged', function (event) {
        // event.previousSlide, event.currentSlide, event.indexh, event.indexv
        SlideNumNext = <%=userId%> +":" + <%=slideId%> +":" + event.indexh;
        send();
    });
    <%
}
}
%>
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

    <%
        if(control!=null&&control.equals("true")){
            %>
    $("#msg").html("分享幻灯片，并控制观看者的播放");
    <%
}
if(flag){
%>
    $("#dialog-form").dialog("open");
    <%
    }
%>
</script>