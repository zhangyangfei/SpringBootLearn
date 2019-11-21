package com.zyf.springTech.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

	private static final String DESTINATION_CONSUMER = "MY_DESTINATION_CONSUMER";

	@Autowired
	private JmsTemplate jmsTemplate;

//	@JmsListener(destination = DESTINATION_CONSUMER)
//	public void onRequest(TextMessage message) throws JMSException {
//		// 获取消息回复目的地和关联id，向回复目的地发送回复消息
//		Destination replyTo = message.getJMSReplyTo();
//		MessageResponseDto response = new MessageResponseDto();
//		response.setId(message.getJMSCorrelationID());
//		response.setSuccess(true);
//		jmsTemplate.convertAndSend(replyTo, response);
//	}
	
	@JmsListener(destination = DESTINATION_CONSUMER)
	public void onRequest2(MessageRequestDto req){
		MessageResponseDto response = new MessageResponseDto();
		response.setId(req.getId());
		response.setSuccess(true);
		jmsTemplate.convertAndSend(req.getReplayDestination(), response);
	}
}
