<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta content="IE=edge" http-equiv="X-UA-Compatible" />
<meta
	content="width=device-width, initial-scale=0.1, maximum-scale=1.0, user-scalable=1"
	name="viewport">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->


<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">

<title>管理平台</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css" />

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<link href="css/main.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="js/func.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//document.body.scrollTo(document.body.scrollLeftMax / 2, 0);
		homePageSet();
	})
</script>


<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<div class="container-fluid">

		<h3 class="center">冷链物流管理平台</h3>

		<hr class="main-width" />

		<div class="main-width">
			<div class="row-fluid">
				<div class="pull-left">
					<h5>请选择操作:</h5>
				</div>
				<div class="pull-right">
					<a href="login.jsp"><button class="btn btn-inverse"
							type="button">重新登录</button></a>
				</div>
			</div>
			<div class="row-fluid">
				<table style="width:100%;">
					<tbody>
					<tr>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="comdtylist.jsp">
										<button class="btn btn-large btn-primary btn-block">管理商品条目</button>
									</a>
								</div>
							</div>
						</td>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="comdty.jsp">
										<button class="btn btn-large btn-primary btn-block">添加商品条目</button>
									</a>
								</div>
							</div>
						</td>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="venderlist.jsp">
										<button class="btn btn-large btn-primary btn-block">管理售货机</button>
									</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="vender.jsp">
										<button class="btn btn-large btn-primary btn-block">添加售货机</button>
									</a>
								</div>
							</div>
						</td>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="query.jsp">
										<button class="btn btn-large btn-primary btn-block">查询</button>
									</a>
								</div>
							</div>
						</td>
						
					</tr>
					</tbody>
				</table>
				
			</div>

		</div>




	</div>
</body>
</html>