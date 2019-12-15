$(function() {
	var user = {
		id : '16',
		name : 'lisi',
		age : 99,
		note : 'mongodb新建用户2'
	};
	$("#insert").click(function() {
		$.post({
			url : "/user/insert",
			// 此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			// 将JSON转化为字符串传递
			data : JSON.stringify(user),
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
			url : "/user/findUsers?name=li&note=2",
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