<%@page import="cn.edu.csu.pojo.Vender"%>
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
    <meta content="telephone=no" name="format-detection">

    <title>管理平台</title>
    
    
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<link href="../css/main.css" rel="stylesheet" type="text/css" />
	
	<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../js/func.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			$('#backlink').attr('href', 'list?pageNum=' + getUrlPara('pageNum') + 
					'&pageSize=' + getUrlPara('pageSize') + 
					'&search=' + getUrlPara('search') + 
					'#' + getUrlPara('barcode'));
			$('#pageNum').attr('value', getUrlPara('pageNum'));
			$('#pageSize').attr('value', getUrlPara('pageSize'));
			$('#search').attr('value', getUrlPara('search'));
		});
	</script>

    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
                        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
                <![endif]-->
    
</head>
<%
	Vender v = (Vender)request.getAttribute("vender");
	String pageNum = (String)request.getAttribute("pageNum");
%>
<body>
	<div class="container-fluid" style="margin-top:20px;">
        <div class="row-fluid">
            <div class="span12">
                <h3 class="text-center">信息更新</h3>
            </div>
        </div>

        <div class="row-fluid">
            <div class="span1"></div>

            <div class="span10">
                <hr />
            </div>

            <div class="span1"></div>
        </div>


        <div class="row-fluid">
            <div class="center">
            	<div id="tips" class="center"></div>
            	
<%
	if(v != null) {
%>
            	
				<form class="form-horizontal" method="post" onsubmit="return updateVender('<%= v.getId() %>');" style="margin-left: -15%;">
                 	<div class="control-group">
                        <label class="control-label" for="vender_number">售货机编号</label>

                        <div class="controls">
                            <input id="vender_number" name="vender_number" required type="text" placeholder="售货机编号" value="<%= v.getVenderNumber() %>" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="vender_type">售货机型号</label>

                        <div class="controls">
                            <input id="vender_type" name="vender_type" required type="text" placeholder="售货机型号" value="<%= v.getVenderType() %>" autocomplete="off" />
                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label" for="salver_quantity">托盘数</label>

                        <div class="controls">
                            <input id="salver_quantity" name="salver_quantity" required type="text" placeholder="托盘数" value="<%= v.getSalverQuantity() %>" onkeyup="if(/[^\d]/g.test(this.value)) showTooltip('salver_quantity', 1000, '只能输入数字'); value=value.replace(/[^\d]/g,'') "
      onbeforepaste="var str = clipboardData.getData('text');if(/[^\d]/g.test(str)) showTooltip('salver_quantity', 1000, '已过滤非数字字符!'); clipboardData.setData('text',str.replace(/[^\d]/g,''););" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="channel_quantity">货道数</label>

                        <div class="controls">
                            <input id="channel_quantity" name="channel_quantity" required type="text" placeholder="货道数" value="<%= v.getChannelQuantity() %>" onkeyup="if(/[^\d]/g.test(this.value)) showTooltip('channel_quantity', 1000, '只能输入数字'); value=value.replace(/[^\d]/g,'') "
      onbeforepaste="var str = clipboardData.getData('text');if(/[^\d]/g.test(str)) showTooltip('channel_quantity', 1000, '已过滤非数字字符!'); clipboardData.setData('text',str.replace(/[^\d]/g,''););" autocomplete="off"  />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="capacity_per_channel">货道容量</label>

                        <div class="controls">
                            <input id="capacity_per_channel" name="capacity_per_channel" required type="text" placeholder="货道容量" value="<%= v.getCapacityPerChannel() %>" onkeyup="if(/[^\d]/g.test(this.value)) showTooltip('capacity_per_channel', 1000, '只能输入数字'); value=value.replace(/[^\d]/g,'') "
      onbeforepaste="var str = clipboardData.getData('text');if(/[^\d]/g.test(str)) showTooltip('capacity_per_channel', 1000, '已过滤非数字字符!'); clipboardData.setData('text',str.replace(/[^\d]/g,''););" autocomplete="off" />
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label" for="status">状态</label>

                        <div class="controls">
                            <input id="status" name="status" type="text" required placeholder="状态" value="<%= v.getStatus() %>" onkeyup="if(/[^\d]/g.test(this.value)) showTooltip('status', 1000, '只能输入数字'); value=value.replace(/[^\d]/g,'') "
      onbeforepaste="var str = clipboardData.getData('text');if(/[^\d]/g.test(str)) showTooltip('status', 1000, '已过滤非数字字符!'); clipboardData.setData('text',str.replace(/[^\d]/g,''););" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary">提交</button>
                            <button type="reset" class="btn offset2">重置</button>
                        </div>
                    </div>
                </form>

<%
	} else {
%>
				<center>
					<h1>您的请求有误, 3秒跳转</h1>
				</center>
				<script type="text/javascript">
					setTimeout("window.location = '../index';", 3000);
				</script>
<%
	}
%>                

            </div>

        </div>
                <hr/>
        <div style="  display: table;margin: auto;">
        
				&nbsp;&nbsp;&nbsp;&nbsp;
				<form action="list<%= v == null ? "" : "#" + v.getVenderNumber() %>" method="post" class="form-search" style="float:left;margin:0px 4px;">
					<input id="pageNum" name="pageNum" type="hidden" value="">
					<input id="pageSize" name="pageSize" type="hidden" value="">
					<input id="search" name="search" type="hidden" value="">
					<a id="backlink" href="list<%= v == null ? "" : "#" + v.getVenderNumber() %>"><input type="submit" class="btn btn-success" title="返回管理页面" value="返回"></a>
				</form>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="../index"><button class="btn btn-inverse">首页</button></a>
        		

           
        </div>
    </div>
</body>
</html>