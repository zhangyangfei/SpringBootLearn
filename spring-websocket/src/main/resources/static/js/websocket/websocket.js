/**
 * websocket共通js
 */

var websocket = null;
var serverEndpoint = "ws";
// 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	// 创建WebSocket对象,连接服务器端点【浏览器访问要同下面的一样】
//	websocket = new WebSocket("ws://192.168.30.42:8180/"+serverEndpoint);
	websocket = new WebSocket("ws://"+window.location.host+"/"+serverEndpoint);
} else {
	alert('本浏览器不支持websocket')
}

// 连接发生错误的回调方法
websocket.onerror = function() {
	appendMessage("error");
};

// 连接成功建立的回调方法
websocket.onopen = function(event) {
	appendMessage("成功连接站点："+serverEndpoint);
}

// 接收到消息的回调方法
websocket.onmessage = function(event) {
	appendMessage(event.data);
}

// 连接关闭的回调方法
websocket.onclose = function() {
	appendMessage("关闭连接站点："+serverEndpoint);
}

// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
// 防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
	websocket.close();
}

// 将消息显示在网页上
function appendMessage(message) {
	// 调用页面的全局函数
	showMsg(message);
}

// 关闭连接
function closeWebSocket() {
	websocket.close();
}

// 发送消息
function sendMessage(message) {
	websocket.send(message);
}

//重连
function reconnect() {
	if(websocket.OPEN !== websocket.readyState){
		websocket = new WebSocket("ws://"+window.location.host+"/"+serverEndpoint);
	}
}

/*
webSocket的readyState属性用来定义连接状态，该属性的值有下面几种：

0 ：对应常量websocket.CONNECTING (numeric value 0)，
正在建立连接连接，还没有完成。The connection has not yet been established.
1 ：对应常量websocket.OPEN (numeric value 1)，
连接成功建立，可以进行通信。The WebSocket connection is established and communication is possible.
2 ：对应常量websocket.CLOSING (numeric value 2)
连接正在进行关闭握手，即将关闭。The connection is going through the closing handshake.
3 : 对应常量websocket.CLOSED (numeric value 3)
连接已经关闭或者根本没有建立。The connection has been closed or could not be opened.
*/
