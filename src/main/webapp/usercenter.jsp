<%@page import="com.qtu404.dataBase.DataBaseManager" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.qtu404.dataBase.SlideVo" %>
<%@ page import="com.qtu404.dataBase.SlideDao" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String s = (String) session.getAttribute("usrname");
    if (s == null || s.equals("")) {
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    } //获取session ID号
%>
<!DOCTYPE html>
<html lang=zh-cmn-Hans>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>园丁助手</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./css/usercenter.css" rel="stylesheet" type="text/css">
    <link href="./css/jquery-confirm.css" rel="stylesheet" type="text/css">
    <link href="./css/font/css/font-awesome.min.css" rel="stylesheet">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="application-name" content="随想">
    <meta name="msapplication-starturl" content="https://qtu404.com:8080">
    <!-- Chrome, Firsefox OS and Opera -->
    <meta name="theme-color" content="#444444"> <!-- Windows Phone -->
    <meta name="msapplication-navbutton-color" content="#444444">
    <!-- iOS Safari -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-title" content="随想">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <style>
        .jconfirm-box {
            width: 300px;
        }
    </style>
    <script>var userId = "<%=s%>"</script>
</head>
<body>
<div id="fake-body">
    <header class="Header">
        <div class="u-container Header-inner">
            <a href="usercenter.jsp">
                <i class="fa fa-paper-plane fa-lg u-logo"></i>
                <i class="u-logoName">NF4 园丁助手</i>
            </a>
            <div class="Header-userMenu">
                <div class=" UserMenu Popout">
                    <span class="UserMenu-name">
                        <i class="icon-logout" id="nf4-logout"></i>
                    </span>
                    <span class="UserMenu-name">Arcry</span>
                    <img class=" UserMenu-avatar" src="https://p0.ssl.qhimg.com/t01edbd29e4e35bad92.jpg" alt="avatar">
                    <a class="course">教程</a><!--href="tutorial.html"-->
                </div>
            </div>
        </div>
    </header>

    <div server-rendered="true" class="Usercenter">
        <ul class="SlideList u-clearfix" id="PPTList">

            <li class="SlideList-add col-3" id="add-PPT">

                <label class="Button Button--primary" id="addPPT" style="margin-top: 30px">
                <span>
                    <i class="icon-plus"></i>新建幻灯片</span>
                </label>

                <label style="margin-top: 70px" for="SlideImport-input" data-balloon="将PPT转为PDF后再导入，可获得更高的还原度哦！"
                       data-balloon-pos="right" data-balloon-length="medium"
                       class="Button Button--secondary Button--outline">
                    <span><i class="fa fa-file-o fa-lg"></i>选择pptx文件
                        <span class="import-type-hint"></span>
                    </span>
                </label>
                <label class="Button Button--primary" id="submit" style="margin-top: 10px">
                <span>
                    <i class="icon-upload"></i>导入选择的文件</span>
                </label>
                <!--提示测试-->
                <!--<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" title="Tooltip on bottom">-->
                <!--Tooltip on bottom-->
                <!--</button>-->
                <form id="nf4-from" action="fileUpload.do" enctype="multipart/form-data" method="post">
                    <input type="file" name="fileName" id="SlideImport-input">
                </form>
            </li>

        </ul>
    </div>
    <canvas id="canv"></canvas>
    <i class="fa fa-space-shuttle fa-rotate-270 fa-4x goTop" id="goTop"></i>
</div>

</body>
<!-- javascript依赖库导入 -->
<script src="./js/plugin/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./js/plugin/jquery-ui.min.js" type="text/javascript"></script>
<script src="./js/plugin/popper.js" type="text/javascript"></script>
<script src="./js/plugin/bootstrap.min.js" type="text/javascript"></script>
<script src="./js/jquery-confirm.js" type="text/javascript"></script>
<script src="./js/usercenter.js" type="text/javascript"></script>
<script src="./js/usercenter_js.js" type="text/javascript"></script>
<!--  ------------------------------------------------------------------------------------    -->
<script>
    <%ArrayList<SlideVo> slideVos = SlideDao.findAllSlideByUserId(s);%>
    <%if (slideVos.size()!=0) {
                    for (int i = 0; i < slideVos.size(); i++) {%>addAPPT("#add-PPT", "<%=slideVos.get(i).getSlideId()%>", "<%=slideVos.get(i).getName()%>");
    <%}
                    %>
    var maxSlideId = 0;
    maxSlideId = parseInt(maxSlideId) + 1;
    var userId = "<%=s%>";
    <%
}else{%>
    var maxSlideId = "100001";
    <%}%>
</script>

</html>