package com.zyf.springTech.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//消息生产者
@Service
public class MessageProducer {

	// 生产者地址
	private static final String DESTINATION_PRODUCER = "MY_DESTINATION_PRODUCER";
	// 生产者接收回复地址
	private static final String DESTINATION_PRODUCER_REPLY = "DESTINATION_PRODUCER_REPLY";
	// 消费者地址
	private static final String DESTINATION_CONSUMER = "MY_DESTINATION_CONSUMER";

	@Autowired
	private JmsTemplate jmsTemplate;

	// 发送消息
	public void send(MessageRequestDto req) {
		jmsTemplate.send(DESTINATION_CONSUMER, session -> {
			// 设置业务数据（可以是json）
			TextMessage message = session.createTextMessage(req.getData());
			// 设置消息关联id，将请求和应答消息关联起来
			message.setJMSCorrelationID(req.getId());
			// 设置消息回复的目的地
			ActiveMQQueue replyTo = new ActiveMQQueue(DESTINATION_PRODUCER_REPLY);
			message.setJMSReplyTo(replyTo);
			return message;
		});
	}

	// 接收应答
	@JmsListener(destination = DESTINATION_PRODUCER_REPLY)
	public void onResponse(TextMessage message) throws JMSException {
		System.out.println("生产方接收到应答，关联id：" + message.getJMSCorrelationID());
		System.out.println("生产方接收到应答，消息内容：" + message.getText());
	}
}
