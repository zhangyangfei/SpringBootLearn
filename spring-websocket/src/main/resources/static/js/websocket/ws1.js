$(function() {

	$("#send").click(function() {
		var message = $("#message").val();
		sendMessage(message);
		$("#message").val("");
	});

	$("#close").click(function() {
		closeWebSocket();
	});
	
	$("#reconnect").click(function() {
		reconnect();
	});

	
});
//想要js之间互相调用的function，那么函数就必须是全局的
function showMsg(message) {
	var context = $("#context").html() + "<br/>" + message;
	$("#context").html(context);
}