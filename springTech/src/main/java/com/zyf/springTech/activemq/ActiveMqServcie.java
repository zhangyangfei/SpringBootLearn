package com.zyf.springTech.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServcie {

	@Autowired
	private JmsTemplate jmsTemplate = null;

	// 发送到默认地址
	public void send() {
		jmsTemplate.convertAndSend("今天是个好天气...");
	}

	// 监听默认地址
	@JmsListener(destination = "${spring.jms.template.default-destination}")
	public void recevie(String msg) {
		System.out.println(msg);
	}

	// 发送到指定地址
	public void sendDest() {
		// 发送到"my-dest"，监听此地址的消费方都会接受到消息
		jmsTemplate.convertAndSend("my-dest", "我是发送到my-dest的测试消息...");
	}

	// 监听指定地址
	@JmsListener(destination = "my-dest")
	public void recevieDest(String msg) {
		System.out.println("recevieDest监听my-dest，接收到消息："+msg);
	}

	// 监听指定地址
	@JmsListener(destination = "my-dest")
	public void recevieDest2(String msg) {
		System.out.println("recevieDest2监听my-dest，接收到消息："+msg);
	}
}
