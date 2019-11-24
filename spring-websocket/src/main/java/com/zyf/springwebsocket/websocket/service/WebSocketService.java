package com.zyf.springwebsocket.websocket.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

@Service
@ServerEndpoint(value = "/ws") // 定义websocket服务端站点
public class WebSocketService {

	// 连接数
	private static int onlineCount = 0;

	private static CopyOnWriteArraySet<WebSocketService> webSocketServiceSet = new CopyOnWriteArraySet<WebSocketService>();

	// 连接会话
	private Session session;

	// 客户端连接成功响应事件
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		onlineCount++;
		webSocketServiceSet.add(this);
		sendSysMessage("ws站点新增一个连接，当前连接数：" + onlineCount);
	}

	// 接收客户端消息响应事件
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("ws站点接收消息：" + message);
//		sendMessage(message);
		sendGroupMessage(message);
	}

	// 客户端关闭连接响应事件
	@OnClose
	public void onClose() {
		onlineCount--;
		webSocketServiceSet.remove(this);
		System.out.println("ws站点关闭一个连接，当前连接数：" + onlineCount);
	}

	// 发送错误响应事件
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	// 发送消息到客户端
	private void sendMessage(String message) {
		doSend(message, session.getUserPrincipal().getName());
	}

	// 发送系统消息到客户端
	private void sendSysMessage(String message) {
		doSend(message, "系统消息");
	}

	// 群发消息到客户端
	private void sendGroupMessage(String message) {
		for (WebSocketService wss : webSocketServiceSet) {
			wss.doSend(message, session.getUserPrincipal().getName());
		}
	}

	// 发送
	private void doSend(String message, String user) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String dateNowStr = sdf.format(d);
		String msg = user + "（" + dateNowStr + "）：" + message;
		try {
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
