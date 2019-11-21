package com.zyf.springTech.activemq;

import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	private static final String DESTINATION_PRODUCER = "MY_DESTINATION_PRODUCER";
	private static final String DESTINATION_CONSUMER = "MY_DESTINATION_CONSUMER";
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(MessageRequestDto req) {
		jmsTemplate.send(DESTINATION_CONSUMER, session -> {
			TextMessage message = session.createTextMessage(req.getData());
			// 设置消息关联id，将请求和应答消息关联起来
			message.setJMSCorrelationID(req.getId());
			// 设置消息回复的目的地
			ActiveMQQueue replyTo = new ActiveMQQueue(DESTINATION_PRODUCER);
			message.setJMSReplyTo(replyTo);
			return message;
		});
	}

	// 用实体类发送数据也不靠谱，如果是不同的系统，实体类怎么转换呢
	// 用json字符串比较靠谱，两边组装和解析都按照json规范来
	
	public void send2(MessageRequestDto req) {
		req.setReplayDestination(DESTINATION_PRODUCER);
		jmsTemplate.convertAndSend(DESTINATION_CONSUMER, req);
	}
	@JmsListener(destination = DESTINATION_PRODUCER)
	public void onResponse(MessageResponseDto dto) {
		System.out.println("On Reply: " + dto);
	}
}
