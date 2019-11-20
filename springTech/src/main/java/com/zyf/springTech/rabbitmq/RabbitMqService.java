package com.zyf.springTech.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate = null;

	String msgRouting = "msgrouting";

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.out.println("消息成功消费");
		} else {
			System.out.println("消息消费失败:" + cause);
		}
	}

	// 发送消息
	public void sendMsg() {
		// 设置回调
		rabbitTemplate.setConfirmCallback(this);
		// 发送消息，通过msgRouting确定队列
		rabbitTemplate.convertAndSend(msgRouting, "发送到msgrouting的一条消息");
	}
}
