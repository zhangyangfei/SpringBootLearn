$(function() {
	var user = {
		id : 15,
		name : 'zhangsan',
		note : 'rest风格mvc',
		sex : 2
	};
	$("#insert").click(function() {
		$.post({
			url : "/user",
			// 此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			// 将JSON转化为字符串传递
			data : JSON.stringify(user),
			// 成功后的方法
			success : function(result,status,jqXHR) {
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});

	$("#getUser").click(function() {
		$.post({
			url : "/user/1",
			success : function(result,status,jqXHR) {
				var issuccess = jqXHR.getResponseHeader("issuccess"); //获取响应头数据
				var sta = jqXHR.status;// 获取HttpStatus
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});

	$("#getUsers").click(function() {
		$.get({
			url : "/user/1/zhang",
			success : function(result) {
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});

	var user2 = {
		id : 15,
		name : 'zhangsan',
		note : 'rest风格更新put',
		sex : 2
	};
	$("#update").click(function() {
		$.ajax({
			url : "/user/1",
			type : 'PUT',
			// 此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			// 将JSON转化为字符串传递
			data : JSON.stringify(user2),
			// 成功后的方法
			success : function(result) {
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});

	$("#updatepatch").click(function() {
		$.ajax({
			url : "/user/1/zhang/部分更新",
			type : "PATCH",
			success : function(result) {
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
	$("#delete").click(function() {
		$.ajax({
			url : "/user/1",
			type : "DELETE",
			success : function(result) {
				if (result != null) {
					alert(JSON.stringify(result));
				}
			}
		});
	});
});