<%@page import="cn.edu.csu.pojo.CommodityType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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


<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<link href="../css/main.css" rel="stylesheet" type="text/css" />

<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../js/func.js" type="text/javascript"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->



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
				<h3 class="text-center">商品类型管理</h3>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span1"></div>

			<div class="span10">
				<hr />
				
				
						<%
							List<CommodityType> res = (List<CommodityType>)request.getAttribute("comdty_types");
							
							int pageNum = 0;
							int pageSize = 0;
							int pages = 0;
							int count = 0;
							String search = (String)request.getAttribute("search");
							String search_bak = search == null ? "" : search;
							if(search == null) search = "";
							else search = "search=" + search + "&";
							
							try {
								count = ((Long)request.getAttribute("count")).intValue();
								pageNum = (Integer)request.getAttribute("pageNum");
								pageSize = (Integer)request.getAttribute("pageSize");
								pages = ((Long)request.getAttribute("pages")).intValue();
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
							
							// 显示的页数, 如果记录超过此页数, 则只显示以下数量的翻页链接
							int PageCount = 0;
							
							if(pageNum > pages) pageNum = pages;
							if(pageNum < 1) pageNum = 1;
														
							int startPageNum = pageNum - (PageCount / 2);
							if(startPageNum < 1) {
								startPageNum = 1;
							}
							int endPageNum = startPageNum + PageCount;
							endPageNum = endPageNum < pages + 1 ? endPageNum : pages + 1;
							if(endPageNum <= startPageNum) endPageNum = startPageNum + 1;
							startPageNum = endPageNum - PageCount;
							startPageNum = startPageNum < 1 ? 1 : startPageNum;
							
							
							// 每页显示多少条记录, 共有以下选择
							List<Integer> pageSizeList = Arrays.asList(new Integer[]{10, 20, 50, 100});
							// 每页显示的记录数, 是上面列表的下标
							int pageSizeIndex = 0;
							if(pageSizeList.contains(pageSize)) {
								pageSizeIndex = pageSizeList.indexOf(pageSize);
							} else {
								if(pageSizeIndex >= pageSizeList.size() || 
										pageSizeIndex < 0) pageSizeIndex = 0;
							}
							
							
							if(res == null || res.size() == 0) {
						%>
							<center>
								<h1>暂无数据</h1>
								<hr>
								<br>
								<p>
									<button class="btn btn-success" type="button" onclick="javascript:window.location.href='/cclms/comdty_type/list'">返回</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="../index"><button class="btn btn-inverse">首页</button></a>
								</p>
							</center>
						<%
							} else {
						%>
				


				<div class="row-fluid">
					<div class="pull-left" style="width: 420px;font-size:16px;">
						<% 
						String s= search.substring(7);
						System.out.println(s);
						if(!s.equals("&")){%>
							"<%=s.substring(0,s.length()-1)%>"的搜索结果共<strong><%= count %></strong>条<%
						}
						else{
							%>所有商品类型共<strong><%=count%></strong>条<%
						}
						%>
						，每页显示 <select class="span3" onchange="window.location = 'list?<%= search %>pageNum=1&pageSize=' + this.value;">
						<%
							for(int i = 0; i < pageSizeList.size(); i++) {
								if(i == pageSizeIndex) {
						%>
							<option value="<%= pageSizeList.get(i) %>" selected><%= pageSizeList.get(i) %></option>
						<%
								} else {
						%>
							<option value="<%= pageSizeList.get(i) %>"><%= pageSizeList.get(i) %></option>
						<%
								}
							}
						%>
						</select> 条
					</div>
					<div class="pull-right" id="normal">
						<a href="../index"><button class="btn btn-inverse">首&nbsp;页</button></a>
						<a href="input"><button title="添加商品类型" class="btn btn-success">添&nbsp;加</button></a>
						<button onclick="mytoggle('#normal', '#batchopt', 'div[name=singleopt]', 'div[name=multiopt]');" type="button" class="btn btn-success">批量操作</button>
						<form onsubmit="return $('#search').val() &amp;&amp; $('#search').val() != '';" action="list" method="post" class="form-search" style="float:right;margin-left:4px;">
              				<input class="search-query" type="text" onblur="$(this).attr('placeholder', '搜索...');$(this).animate({'width':48}, 300);" onfocus="$(this).attr('placeholder', '请输入商品类型名称进行搜索 ...');$(this).animate({'width':260}, 300);" placeholder="搜索..." style="margin-bottom: 0px; width: 48px;" id="search" name="search">
							<input type="submit" value="搜&nbsp;索" onclick="var str = $(this).prev().val(); if(str &amp;&amp; str != '') window.location = 'list?search=' + str;" class="btn btn-success">
						</form>
					</div>
					<div id="batchopt" class="pull-right hide">
						<button class="btn btn-success" type="button"
							onclick="selectall(this, 'div[name=multiopt] input');">全选</button>
						<button class="btn btn-warning" type="button" onclick="multiDelCheck();">删&nbsp;除</button>
						<button class="btn btn-info" type="button"
							onclick="mytoggle('#normal', '#batchopt', 'div[name=singleopt]', 'div[name=multiopt]');">返&nbsp;回</button>
					</div>
				</div>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr class="header">
							<th style="width: 48px;">序号</th>
							<th style="width: 65px;">图片</th>
							<th style="width: 120px;">条形码</th>
							<th style="width: 160px;">名称</th>
							<th style="width: 160px;">品牌</th>
							<th style="width: 160px;">保质期</th>
							<th>备注</th>
							<th style="width: 60px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<%
								int index = (pageNum - 1) * pageSize;
								for (CommodityType ct : res) {
									index++;
							%>
							<tr class="item" id="<%= ct.getBarcode() %>">
								<td><%= index %></td>
								<td>
									<div
										style="width: 60px; height: 60px; background-image: url('../img/glyphicons-halflings.png');"></div>
								</td>
								
												
								<td title="<%= ct.getBarcode() %>"><%= ct.getBarcode() %></td>
									
												
								<td title="<%= ct.getCommodityName() %>"><%=ct.getCommodityName()%></td>
										
												
								<td title="<%=ct.getBrandName()%>"><%=ct.getBrandName()%></td>
										
												
								<td title="<%=ct.getShelfLife()%>"><%=ct.getShelfLife()%></td>
									
											
								<td title="<%=ct.getRemark()%>"><%if( ct.getRemark()==null ){%>无<%}else{%><%=ct.getRemark()%><%}%></td>
								

								<td>
									<div name="singleopt">
										<a href="update?barcode=<%= ct.getBarcode() %>&pageNum=<%= pageNum %>&pageSize=<%= pageSize %>&search=<%= search_bak %>">
											<button class="btn btn-success btn-small" style="min-width: 60px;margin-bottom: 10px;">编&nbsp;辑</button>
										</a>
										<br> 
										<a href="#myModal" role="button" data-toggle="modal" onclick="$('#delcommdtykey')[0].tag = this;" barcode="<%= ct.getBarcode() %>">
											<button class="btn btn-success btn-small" style="min-width: 60px;margin-bottom: 10px;">删&nbsp;除</button>
										</a>
										<br>
									</div>
									<div name="multiopt" class="hide">
										<input class="wyCheckBox" type="checkbox" value="" barcode="<%= ct.getBarcode() %>">
									</div>
								</td>
							</tr>
							<%
								}
						%>
					</tbody>
				</table>
				
				<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">警告</h3>
			        </div>
			
			        <div class="modal-body">
			            <p>你确定要删除此项吗?</p>
			        </div>
			
			        <div class="modal-footer">
			        	<input type="hidden" value="" id="delcommdtykey" tag="" >
			            <button class="btn btn-primary" onclick="delCommdty($('#delcommdtykey')[0].tag, $(this).next())">确定</button>
			            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
			        </div>
			    </div>
			    
			    <div id="myModalMulti" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalMultiLabel" aria-hidden="true">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalMultiLabel">警告</h3>
			        </div>
			
			        <div class="modal-body">
			            <p id="multiDelTips">你确定要删除所选择的数据吗?</p>
			        </div>
			
			        <div class="modal-footer">
			            <button id="multiDelAct" class="btn btn-primary" ready="none" onclick="multiDelCommdty($(this).next());">确定</button>
			            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
			        </div>
			    </div>
				
				
				<div class="row-fluid" style="margin-bottom: 32px;">
					<div class="span12">
						<div class="pagination pull-right" style="margin-bottom: 0px;">
							<form id="pageform" action="list" method="post" class="form-search">
								<input id="search" name="search" type="hidden" value="<%= search_bak %>">
								<input id="pageNum" name="pageNum" type="hidden" value="<%= pageNum %>">
								<input id="pageSize" name="pageSize" type="hidden" value="<%= pageSizeList.get(pageSizeIndex) %>">
								<ul>
								<%
									// 首页
									if(startPageNum > 1) {
								%>
									<li><a onclick="$('#pageNum').val('1');$('#pageform').submit();" href="#" title="首页">首页</a></li>	
								<%
									} else {
								%>
									<li class="disabled"><span title="首页">首页</span></li>	
								<%		
									}
								
									// 上一页
									if(startPageNum > 1) {
								%>
									<li><a onclick="$('#pageNum').val('<%= pageNum - 1 %>');$('#pageform').submit();" href="#" title="上一页">上一页</a></li>
								<%
									} else {
								%>
									<li class="disabled"><span title="上一页">上一页</span></li>
								<%		
									}
									
									
						
									
				
								%>
									<li><span>第<strong>&nbsp;<%=pageNum%>&nbsp;</strong>页，共<strong>&nbsp;<%=pages%>&nbsp;</strong>页</span></li>
								<%		
						
									
						
									
									// 下一页
									if(endPageNum <= pages ) {
								%>
									<li><a onclick="$('#pageNum').val('<%= pageNum + 1 %>');$('#pageform').submit();" href="#" title="下一页">下一页</a></li>
								<%
									} else {
								%>
									<li class="disabled"><span title="下一页">下一页</span></li>
								<%		
									}
									
									// 末页
									if(endPageNum < pages + 1) {
								%>
									<li><a onclick="$('#pageNum').val('<%= pages %>');$('#pageform').submit();" href="#" title="末页">末页</a></li>
								<%
									} else {
								%>
									<li class="disabled"><span title="末页">末页</span></li>
								<%		
									}
									
									if(pages > PageCount) {
								%>
									<li>
										<div class="input-prepend input-append">
											<span class="add-on">跳转到:</span>
											<input type="text"id="appendedPrependedInput" style="width: 40px;">
											<button type="button" class="btn" onclick="$('#pageNum').val('' + parseInt($(this).prev().val()));$('#pageform').submit();">Go!</button>
										</div>
									</li>
								<%	
									}
									
								%>
								</ul>
							</form>
						</div>
					</div>
				</div>
						<%
							}
						%>

			</div>

			<div class="span1"></div>
		</div>



	</div>
</body>
</html>