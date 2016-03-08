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

    <title>Add Page</title>
    
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<link href="../css/main.css" rel="stylesheet" type="text/css" />
	
	<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../js/json2.js" type="text/javascript"></script>
	<script src="../js/func.js" type="text/javascript"></script>


    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
                        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
                <![endif]-->
</head>

<body>
    <div class="container-fluid" style="margin-top:20px;">


        <div class="row-fluid">
            <div class="span12">
                <h3 class="text-center">条目添加</h3>
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
                
				<form class="form-horizontal" method="post" onsubmit="return addComdty(this);" style="margin-left: -15%;">
                    <div class="control-group">
                        <label class="control-label" for="barcode">二维码</label>

                        <div class="controls">
                            <input id="barcode" name="barcode" required type="text" placeholder="二维码" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="commodity_name">名称</label>

                        <div class="controls">
                            <input id="commodity_name" name="commodity_name" required  type="text" placeholder="名称" autocomplete="off" />
                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label" for="brand_name">品牌名</label>

                        <div class="controls">
                            <input id="brand_name" name="brand_name" required  type="text" placeholder="品牌名"  autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="shelf_life">保质期</label>

                        <div class="controls">
                            <input id="shelf_life" name="shelf_life" required  type="text" placeholder="保质期" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="remark">备注</label>

                        <div class="controls">
                            <input id="remark" name="remark" type="text" placeholder="备注" autocomplete="off" />
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary">提交</button>
                            <button type="reset" class="btn offset2">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        <hr/>
        <div style="  display: table;margin: auto;">
      
         	<a href="list"><button class="btn btn-success" title="管理所有商品类型">返回</button></a>
         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           	<a href="../index"><button class="btn btn-inverse">首页</button></a>
            
           
        </div>
    </div>
</body>
</html>