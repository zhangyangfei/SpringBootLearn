package com.zyf.springwebsocket.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	// http://localhost:8080/websocket/ws1
	@RequestMapping("/ws1")
	public String ws1() {
		return "websocket/ws1";
	}
}
