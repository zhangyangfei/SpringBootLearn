$(function() {
	alert("新建");
	var params = {
		id:15,
		name:'zhangsan',
		note:'session操作_ajax',
		sex:2
	};
	$("#button").click(function() {
		$.post({
			url : "/mvcsession/ms2",
			// 此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			// 将JSON转化为字符串传递
			data : JSON.stringify(params),
			// 成功后的方法
			success : function(result) {
				if(result != null){
//						alert(result.note);
					console.log(result);
				}
			}
		});
	});
});