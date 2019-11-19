package com.zyf.springTech.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServcie {

	@Autowired
	private JmsTemplate jmsTemplate = null;
	
	public void send() {
		jmsTemplate.convertAndSend("今天是个好天气...");
	}
	
	@JmsListener(destination = "${spring.jms.template.default-destination}")
	public void recevie(String msg) {
		System.out.println(msg);
	}
}
