package com.zyf.springTech.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// ActiveMQ 消息控制器
@Controller
@RequestMapping("/activemq")
public class MessageController {

	@Autowired
	private MessageProducer messageProducer;

	@RequestMapping("/send")
	public String sendMessage() {
		MessageRequestDto req = new MessageRequestDto();
		req.setId("a000001");
		req.setData("你好，我是zhangsan");
		messageProducer.send(req);
		return "activemq/send";
	}

}
