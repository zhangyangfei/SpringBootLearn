package com.zyf.springTech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyf.springTech.activemq.ActiveMqServcie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTechApplicationTests {

	@Autowired
	private ActiveMqServcie activeMqServcie;

//	@Test
	public void send() {
		activeMqServcie.send();
	}

	@Test
	public void sendDest() {
		activeMqServcie.sendDest();
	}

}
