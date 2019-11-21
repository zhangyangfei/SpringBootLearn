package com.zyf.springTech.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activemq")
public class MessageController {

	@Autowired
	private MessageProducer messageProducer;

	@RequestMapping("/send")
	public String sendMessage() {
		MessageRequestDto req = new MessageRequestDto();
		req.setId("a000001");
		req.setData("zhangsan");
//		messageProducer.send(req);
		messageProducer.send2(req);
		return "activemq/send";
	}

}
