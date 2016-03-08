<%@page import="cn.edu.csu.pojo.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
			<%
			Admin admin = (Admin)session.getAttribute("admin");

		%>
		
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



<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">





<script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="js/func.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//document.body.scrollTo(document.body.scrollLeftMax / 2, 0);
		homePageSet();
	});
	$("#functable");
</script>


<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>


		<div style="height:72px; background: #428bca;">
			<div class="container" style=" width: 80%;">
				<div class="row">
					<div class="span12">
						<a class="logo pull-left" >
						<img src="img/logo.jpg" alt="CCLMS" title="CCLMS"></a>
						<!-- Top Nav Start -->
						<div class="pull-left">
							<div class="navbar" id="topnav">
								<div class="navbar-inner">
									<ul class="nav">
										<li><a  href="#"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;主页</a> 
										<li><a  href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<%=admin.getName()%></a></li>
										<li><a  href="admin/doLogout"><span class="glyphicon glyphicon-log-out">&nbsp;</span>注销</a></li>			
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	
	
	<div class="container-fluid">



		<div class="main-width">
		
		
		
		
		
		
		
			<h1 class="heading1" style="margin-top:80px;">
				<span class="maintext">
					<span class="glyphicon glyphicon-list"></span>
					功能选择
				</span>
				<span class="subtext">
					管理商品类型或者售货机
				</span>
			</h1>
			<div class="row-fluid">
				<table id="functable" style="width:100%;">
					<tbody>
					<tr>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="comdty_type/list">
										<button class="btn btn-large btn-primary btn-block">
										<span class="glyphicon glyphicon-folder-open">&nbsp;</span>
										管理商品条目
										</button>
									</a>
								</div>
							</div>
						</td>
						<td style="width:33.3%;">
							<div class="funcblock">
								<div class="funcinner">
									<a href="vender/list">
										<button class="btn btn-large btn-primary btn-block">
										<span class="glyphicon glyphicon-folder-open">&nbsp;</span>
										管理售货机
										</button>
									</a>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			
			<!-- Section Start-->
		<div class="container-fluid otherddetails" style="margin-top: 50px;">
			<div class="otherddetailspart">
				<div class="innerclass free">
					<h2>
						<span class="glyphicon glyphicon-tags">&nbsp;</span>
						添加
					</h2>
					添加一条新的记录
				</div>
			</div>
			<div class="otherddetailspart">
				<div class="innerclass payment">
					<h2>
						<span class="glyphicon glyphicon-remove">&nbsp;</span>
						删除
					</h2>
					删除一条旧的记录
				</div>
			</div>
			<div class="otherddetailspart">
				<div class="innerclass shipping">
					<h2>
						<span class="glyphicon glyphicon-search">&nbsp;</span>
						查询
					</h2>
					查询某一条记录
				</div>
			</div>
			<div class="otherddetailspart">
				<div class="innerclass choice">
					<h2>
						<span class="glyphicon glyphicon-edit">&nbsp;</span>
						修改
					</h2>
					修改某一条记录
				</div>
			</div>
		</div>
		<!-- Section End-->
		</div>




	</div>
</body>
</html>