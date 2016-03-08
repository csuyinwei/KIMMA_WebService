<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<meta content="IE=edge" http-equiv="X-UA-Compatible" />
	<meta content="width=device-width, initial-scale=0.5, maximum-scale=1, user-scalable=1" name="viewport">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	
	<title>管理员登录</title>
	
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<link href="../css/main.css" rel="stylesheet" type="text/css" />
	
	<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../js/func.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function () {
			var ns = getUrlPara('nextstep');
			if(ns && ns != null) $("#dstpage").attr('href', ns);
		});
	</script>


<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
                        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
                <![endif]-->
</head>
<body>
	<div class="container-fluid" style="margin-top:30px;">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">管理员登录</h3>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span1"></div>

			<div class="span10">
				<hr />
				<div class="center">
					<div id="tips" class="center"></div>
					<a id="dstpage" href="../index" style="display: none;"></a>
					<form id="loginform" class="form-horizontal"
						onsubmit="return userLogin(this, 'dstpage');" style="margin-left: -15%;">
						<div class="control-group">
							<label class="control-label" for="inputEmail">账号</label>

							<div class="controls">
								<input id="account" name="account" required type="text"
									placeholder="账号" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputPassword">密码</label>

							<div class="controls">
								<input id="password" name="password" required type="password"
									placeholder="密码" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">验证码</label>
							<div class="controls">
								<input id="captcha" name="captcha" required type="text" placeholder="验证码" style="float:left;"/>
							
								<div class="input-wrapper captcha clearfix js-refresh-captcha" style="float:left;margin-left:15px;">		
									<img id="verificationimage0" src="/cclms/randomverification" 
									class="js-captcha-img" height="20" width="70" 
									onClick="javascript:this.src='/cclms/randomverification?tm='+Math.random();">
								</div>
							</div>
							
						</div>
						<div class="control-group">
							<div class="controls" style="font-size:10px;">（单击图片更换验证码）</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label class="checkbox"><input type="checkbox" /> 记住我</label>
								<button type="submit" class="btn btn-primary">登陆</button>
								<button type="reset" class="btn offset2">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="span1"></div>
		</div>


	</div>
</body>
</html>