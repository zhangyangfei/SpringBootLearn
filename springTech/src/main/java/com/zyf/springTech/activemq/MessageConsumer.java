package com.zyf.springTech.activemq;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//消息消费者
@Service
public class MessageConsumer {

	// 消费者地址
	private static final String DESTINATION_CONSUMER = "MY_DESTINATION_CONSUMER";

	@Autowired
	private JmsTemplate jmsTemplate;

	// 接收消息
	@JmsListener(destination = DESTINATION_CONSUMER)
	public void onRequest(TextMessage message) throws JMSException {
		System.out.println("消费方接收到关联id：" + message.getJMSCorrelationID());
		System.out.println("消费方接收到消息内容：" + message.getText());
		reply(message);
	}

	// 应答
	private void reply(TextMessage receivemessage) throws JMSException {
		// 获取消息回复目的地和关联id，向回复目的地发送回复消息
		Queue replyTo = (Queue) receivemessage.getJMSReplyTo();
		jmsTemplate.send(replyTo.getQueueName(), session -> {
			// 设置业务数据（可以是json）
			TextMessage message = session.createTextMessage("你好，zhangsan，来信已经收到");
			// 设置消息关联id，将请求和应答消息关联起来
			message.setJMSCorrelationID(receivemessage.getJMSCorrelationID());
			return message;
		});
	}

}
