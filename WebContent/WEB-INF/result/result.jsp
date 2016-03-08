<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" />
    <meta content="IE=edge" http-equiv="X-UA-Compatible" />
    <meta content="width=device-width, initial-scale=0.1, maximum-scale=1.0, user-scalable=1" name="viewport"><!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection"><!-- 新 Bootstrap 核心 CSS 文件 -->
    
    <link href="../css/main.css" rel="stylesheet" type="text/css" />
    
    <script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../js/func.js" type="text/javascript"></script>

    <title>Test Page</title>

    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
                        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
                <![endif]-->
</head>

<%
	request.setCharacterEncoding("utf-8");
	String nextstep = request.getParameter("nextstep");
	if(nextstep == null || nextstep.equals("")) nextstep = "index";
%>

<body onload="delay3s()">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1 class="center">操作成功</h1>
    <br>
    <br>
    <br>
    <input id="count" type="hidden" value="0" />
    <div class="center"><span id="tips">0</span>秒后返回首页</div>
    <br>
    <span class="center">如果您的浏览器没有跳转, 请<a id="nextstep" href="<%= nextstep %>">点击此处</a></span>
</body>
</html>