// func.js

/*
 * 表彰验证, 根据 input 标签的 required 属性检查是否符合
 * 参数: 
 * 		e : 表单元素
 * 返回值:
 * 		返回是否符合的标志
 */
function formcheck(e) {
	if (!e)
		return false;
	var inputs = $(e).serializeArray();
	for (var i = 0; i < inputs.length; ++i) {
		if (inputs[i].required && (!inputs[i].value || inputs[i].value == '')) {
			inputs[i].focus();
			return false;
		}
	}
	return true;
}

/*
 * 用户登录函数, ajax 异步提交
 * 参数:
 * 		e : 表单元素, 非 id
 * 		dst : 一个 a 元素的 id, 提交成功之后的跳转链接
 * 返回值:
 * 		因本函数使用异步提交, 故无视该表单的原始提交动作, 返回 false 即可
 */
function userLogin(e, dst) {
	// 检查表单
	if (!formcheck(e))
		return false;
	// 获取值
	var a = $("#account").val();
	var p = $("#password").val();
	var c = $("#captcha").val();
	// post 提交
	$.post("doLogin", {
		account : a,
		password : p,
		captcha : c
	}, function(data) {
		// 回调函数, 请求结束后进行检查
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法验证, 请稍候重试!');
		} else if (data == "success") {
			window.location = $('#' + dst).attr('href');
		} else if (data == 'no_such_account') {
			showTips('tips', '提示:', '账号不存在');
		} else if (data == 'wrong_password') {
			showTips('tips', '提示:', '密码错误');
		} else if (data == 'wrong_captcha'){
			showTips('tips', '提示:', '验证码错误');
		}
	});
	return false;
}

/*
 * 添加商品类型操作
 */
function addComdty(e) {
	$('#tips').children().remove();
	
	if (!e)
		return false;

	var bc = $('#barcode').val();
	var cn = $('#commodity_name').val();
	var bn = $('#brand_name').val();
	var sl = $('#shelf_life').val();
	var re = $('#remark').val();

	$.post('doInput', {
		barcode : bc,
		commodityName : cn,
		brandName : bn,
		shelfLife : sl,
		remark : re
	}, function(data) {
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
		} else {
			//showTips('tips', '提示:', '操作成功!');
			//console.log(data);
			//var data = '{"login":"请先登录!","commodity_name":"商品名称未填写！","barcode":"条形码未填写！","brand_name":"商品品牌未填写！","shelf_life":"保质期未填写！"}';
			var map = data;
			var flag = true;
			for(var i in map) {
				if(map[i] && map[i] != '') {
					flag = false;
					showTooltip(i, 5000, map[i]);
				}
			}
			
			if(flag) {
				showTips('tips', '提示:', '操作成功!');
			}
			
//			showTooltip('barcode', 5000, '二维码不正确');
//			showTooltip('comdty_name', 5000, '商品名称不正确');
//			showTooltip('brand_name', 5000, '品牌名不正确');
//			showTooltip('shelf_life', 5000, '保质期不正确');
			
			//showTips('tips', '提示:', '操作失败!');
		}
	});

	return false;
}


/*
 * 更新商品类型操作
 */
function updateComdty(cid) {
	$('#tips').children().remove();
	
	if (!cid || cid == '')
		return false;

	var bc = $('#barcode').val();
	var cn = $('#commodity_name').val();
	var bn = $('#brand_name').val();
	var sl = $('#shelf_life').val();
	var re = $('#remark').val();

	$.post('doUpdate', {
		id 				: cid,
		barcode 		: bc,
		commodityName 	: cn,
		brandName 		: bn,
		shelfLife 		: sl,
		remark 			: re
	}, function(data) {
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
		} else {
			//showTips('tips', '提示:', '操作成功!');
			var map = data;
			var flag = true;
			for(var i in map) {
				if(map[i] && map[i] != '') {
					flag = false;
					showTooltip(i, 5000, map[i]);
				}
			}
			
			if(flag) {
				showTips('tips', '提示:', '操作成功!');
			}
		}
	});

	return false;
}

function multiDelCheck() {
	var list = $(".wyCheckBox:checked");
	if(list && list.length > 0) {
		$("#multiDelAct").attr('ready', 'ok');
		$("#multiDelTips").html('你确定要删除所选择的数据吗?');
	} else {
		$("#multiDelAct").attr('ready', 'none');
		$("#multiDelTips").html('还没有选择数据!');
	}
	$("#myModalMulti").modal();
}

function multiDelCommdty(close) {
	close.click();
	if($("#multiDelAct").attr('ready') == 'none') return;
	var list = $(".wyCheckBox:checked");
	for(var i = 0; i < list.length; i++) {
		delCommdty(list[i], close);
	}
	$("#multiDelAct").attr('ready', 'none');
}

/*
 * 删除商品类型操作
 */
function delCommdty(elem, close) {
	if (!elem)
		return false;

	$.post('doDelete', {
		barcode : $(elem).attr('barcode')
	}, function(data) {
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
		} else if (data == "success") {
			//showTips('tips', '提示:', '操作成功!');
			var $e = $(elem).parent().parent().parent();
			//alert('xxx');
			var h = $e.height();
			$($e.children()).each(function(){
				$(this).remove();
			});
			$e.height(h);
			$e.html('<td colspan="4" style="background-color:black;"></td>');
			
			$e.animate({'height' : 0, opacity : 0}, 300, function(){
			  $(this).remove();
			});
		} else {
			showTips('tips', '提示:', '操作失败!');
		}
	});
	close.click();
	return false;
}


/*
 * 添加售货机
 */
function addVender(e) {
	$('#tips').children().remove();
	
	if (!e)
		return false;

	var vn = $('#vender_number').val();
	var vt = $('#vender_type').val();
	var sq = $('#salver_quantity').val();
	var cq = $('#channel_quantity').val();
	var cpc = $('#capacity_per_channel').val();
	var s = $('#status').val();
	
	$.post('doInput', {
		venderNumber : vn,
		venderType : vt,
		salverQuantity : sq,
		channelQuantity : cq,
		capacityPerChannel : cpc, 
		status : s
	}, function(data) {
		console.log(data);
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
		} else {
			//showTips('tips', '提示:', '操作成功!');
			console.log(data);
			var map = data;
			var flag = true;
			for(var i in map) {
				if(map[i] && map[i] != '') {
					flag = false;
					showTooltip(i, 5000, map[i]);
				}
			}
			
			if(flag) {
				showTips('tips', '提示:', '操作成功!');
			}
		}
	});

	return false;
}


/*
 * 添加售货机
 */
function updateVender(vid) {
	$('#tips').children().remove();
	
	if (!vid || vid == '')
		return false;

	var vn = $('#vender_number').val();
	var vt = $('#vender_type').val();
	var sq = $('#salver_quantity').val();
	var cq = $('#channel_quantity').val();
	var cpc = $('#capacity_per_channel').val();
	var s = $('#status').val();

	$.post('doUpdate', {
		id					: vid, 
		venderNumber 		: vn,
		venderType 			: vt,
		salverQuantity 		: sq,
		channelQuantity 	: cq,
		capacityPerChannel 	: cpc, 
		status : s
	}, function(data) {
		if (!data || data == '') {
			showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
		} else {
			//showTips('tips', '提示:', '操作成功!');
			var map = data;
			var flag = true;
			for(var i in map) {
				if(map[i] && map[i] != '') {
					flag = false;
					showTooltip(i, 5000, map[i]);
				}
			}
			
			if(flag) {
				showTips('tips', '提示:', '操作成功!');
			}
		}
	});

	return false;
}


function multiDelVender(close) {
	close.click();
	if($("#multiDelAct").attr('ready') == 'none') return;
	var list = $(".wyCheckBox:checked");
	for(var i = 0; i < list.length; i++) {
		delvender(list[i], close);
	}
	$("#multiDelAct").attr('ready', 'none');
}


function delvender(elem, close) {
	if(!elem) return false;
	//if(!window.confirm('确定要删除吗?')) return false;
	$.post('doDelete', { 
		venderNumber : $(elem).attr('venderNumber')
		}, function(data){
				if (!data || data == '') {
					showTips('tips', '提示:', '服务器暂时无法访问, 请稍候重试!');
				} else if (data == "success") {
					
					var e = $(elem).parent().parent().parent();
					//alert('xxx');
					var h = e.height();
					$(e.children()).each(function(){
						$(this).remove();
					});
					e.height(h);
					e.html('<td colspan="7" style="background-color:black;"></td>');
					
					e.animate({'height' : 0, opacity : 0}, 300, function(){
					  e.remove();
					});
					
				} else {
					showTips('tips', '提示:', '操作失败!');
				}
			}
	);
	close.click();
}

/*
 * 在参数 id 指明的 div 元素内显示提示信息
 * 参数:
 * 		id	:	目标 div 的 id
 * 		c	:	提示信息的标题
 * 		s	:	需要加粗显示的提示信息
 */
function showTips(id, c, s) {
	var $dst = $('#' + id);
	if (!$dst)
		return;
	var str = '<div class="alert alert-error alert-dismissible fade in" role="alert">'
			+ '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>'
			+ c
			+ ((s && s != '' ? '<strong>' + s + '</strong>' : ''))
			+ '</div>';
	$dst.children().remove();
	$dst.html(str);
}

function showTooltip(id, time, content) {
	//$('#' + id).attr('data-original-title', content);
	//$('#' + id).attr('data-toggle', 'none');
	//$('#' + id).attr('data-placement', 'right');

	//$('#' + id).tooltip('show');
	
	var $e = $("#" + id); 
	wyShowTooltip($e, 'right', content);
	$e[0].old = $e[0].value;
	//clearTimeout($("#" + id).attr('tag'));
	//$("#" + id).attr('tag', setTimeout("wyDestroyTooltip($('#" + id + "'));", time));
	$e.bind('keyup', cancelTooltip);
}

function cancelTooltip() {
	//console.log('cancel');
	if(this.value != this.old) {
		$(this).unbind('keyup');
		wyDestroyTooltip($('#' + this.id));
	}
}

function wyShowTooltip($e, pos, ctx) {
	if(!$e) return ;
	
	if($e.next() && $e.next().attr('class') == 'wytooltip tooltip fade right in') {
		$e.next().remove();
	}
	
	var str = '<div class="wytooltip tooltip fade right in" style="top: 0px; left: 0px; display: block;"><div class="tooltip-arrow"></div><div class="tooltip-inner" style="font-size: 18px;">' + ctx + '</div></div>';
	var $dst = $(str);
	$e.after($dst);
	var tp = $e[0].getBoundingClientRect();
	console.log(tp);
	var p = {x : tp.left || tp.x, 
			y : tp.top || tp.y, 
			width : (tp.right - tp.left), 
			height : (tp.bottom - tp.top)};
	var left = 0;
	var top = 0;
	if(pos == 'top') {
		left = p.x + p.width / 2 - $dst.width() / 2;
		top = p.y - $dst.height();
	} else if(pos == 'right') {
		left = p.x + p.width;
		top = p.y + p.height / 2 - $dst.height() / 2;
	} else if(pos == 'bottom') {
		left = p.x + p.width / 2 - $dst.width() / 2;
		top = p.y - p.height;
	} else {
		left = p.x - $dst.width();
		top = p.y + p.height / 2 - $dst.height() / 2;
	}
	
	$dst.css('left', (left + $(window).scrollLeft()) + 'px');
	$dst.css('top', (top + $(window).scrollTop()) + 'px');
	
}

function wyHideTooltip($e) {
	if(!e) return;
	var dst = $e.next();
	if(dst && dst.attr('class') == 'wytooltip tooltip fade right in')
		dst.hide();
}

function wyDestroyTooltip($e) {
	if(!$e) return;
	var dst = $e.next();
	if(dst && dst.attr('class') == 'wytooltip tooltip fade right in')
		dst.remove();
}

/*
 * 切换两个 div
 * 参数:
 * 		a	:	第一个 div 的 jquery 属性选择器
 * 		b	:	第二个 div 的 jquery 属性选择器
 * 		c	:	跟随第一个 div 进行显示/隐藏的 div 的 jquery 属性选择器, 可以是选取多个元素的选择器, 可为空
 * 		d	:	跟随第二个 div 进行显示/隐藏的 div 的 jquery 属性选择器, 可以是选取多个元素的选择器, 可为空
 */
function mytoggle(a, b, c, d) {
	if (!a || !b)
		return;
	
	var $aa = $(a);
	var $bb = $(b);
	if (!$aa || !$bb)
		return;
	if (!$aa || !$bb) {
		//console.log('none');
		return;
	} else {
		
		if ($aa.hasClass('hide')) {
			//$aa.attr('class', $aa.attr('class').replace('hide', ''));
			$aa.removeClass('hide');
			//$bb.attr('class', $bb.attr('class') + ' hide');
			$bb.addClass('hide');
			mymultitoggle(c, d);
		} else {
			//$bb.attr('class', $bb.attr('class').replace('hide', ''));
			$bb.removeClass('hide');
			//$aa.attr('class', $aa.attr('class') + ' hide');
			$aa.addClass('hide');
			mymultitoggle(d, c);
		}

	}
}

/*
 * 显示第一类 div, 隐藏第一类 div
 * 参数:
 * 		a	:	要显示的 div 的 jquery 属性选择器, 可以是选取多个元素的选择器, 可为空
 * 		b	:	要隐藏的 div 的 jquery 属性选择器, 可以是选取多个元素的选择器, 可为空
 */
function mymultitoggle(a, b) {
	var $aa = $(a);
	var $bb = $(b);
	if($bb) {
		$bb.each(function(){
			//this.className = this.className + ' hide';
			$(this).addClass('hide');
		});
	}
	if($aa) {
		$aa.each(function(){
			//var str = this.className.replace('hide', '');
			//while(str.indexOf('  ') != -1) str = str.replace('  ', ' ');
			//this.className = str;
			$(this).removeClass('hide');
		});
	}
}

function selectall(e, dst) {
	if(!e) return false;
	if(e.innerHTML == '全选') {
		$(dst).each(function(){
			this.checked = true;
		});
		e.innerHTML = '取消全选';
	} else {
		$(dst).each(function(){
			this.checked = false;
		});
		e.innerHTML = '全选';
	}
}

function delay3s() {
	document.getElementById('count').value = '0';
	document.getElementById('tips').innerHTML = '3';
	setTimeout("timerfunc()", 1000);
}

function timerfunc() {
	if (!document.getElementById('count').value) {
		document.getElementById('count').value = '1';
		document.getElementById('tips').innerHTML = '3';
	} else {
		var i = parseInt(document.getElementById('count').value);
		++i;
		document.getElementById('count').value = '' + i;
		document.getElementById('tips').innerHTML = '' + (3 - i);
		if (i >= 3)
			document.getElementById('nextstep').click();
		else
			setTimeout("timerfunc()", 1000);
	}
}

function switchSelect(str, tid, val, pid, index) {
	$('#' + tid).text(str);
	$('#' + tid).attr('tag', val);
	var panels = $('#' + pid).children();
	console.log(panels);
	for (var i = 0; i < panels.length; ++i) {
		if (i == index)
			panels[i].className = "";
		else
			panels[i].className = "hide";
	}
}

function homePageSet() {
	$(".funcinner").each(function() {
		var e = $(this).parent();
		e.height(parseInt(e.width()) / 3 + 30);
		
		// e = $(this);
		// e.css('height', parseInt(e.css('width')) / 2);

		e = $(this).children().children();
		e.height(parseInt(e.width()) / 3);
	});
}

function getUrlPara(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = decodeURIComponent(window.location.search.substr(1)).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
