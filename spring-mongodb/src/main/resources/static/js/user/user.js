$(function() {
	var user = {
		id : '16',
		name : 'lisi',
		age : 99,
		note : 'mongodb新建用户2'
	};
	var user2 = {
		id : '17',
		name : 'zhangyf',
		age : 101,
		note : 'mongodb新建用户3'
	};
	$("#insert").click(function() {
		$.post({
			url : "/user/insert",
			// 此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			// 将JSON转化为字符串传递
			data : JSON.stringify(user2),
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});

	$("#findById").click(function() {
		$.post({
			url : "/user/findById?id=" + $('#id').val(),
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
	
	$("#findUsers").click(function() {
		$.post({
			url : "/user/findUsers?name=zhang&note=2",
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
	
	$("#findUsers2").click(function() {
		$.post({
			url : "/user/findUsers2?name=zhang&age=99",
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
	
	$("#updateById").click(function() {
		$.post({
			url : "/user/updateById?id=17&age=18&note=更新测试2",
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
	$("#deleteById").click(function() {
		$.post({
			url : "/user/deleteById?id=17",
			// 成功后的方法
			success : function(result, status, jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
});