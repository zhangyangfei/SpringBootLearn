package com.zyf.springTrans.redispubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/** Redis消息监听器2 */
@Component
public class RedisMessageListener2 implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("********* Redis消息监听器2 ******** 开始 ********");
		// 消息体
		String body = new String(message.getBody());
		// 渠道名称
		String topic = new String(pattern);
		System.out.println("消息体 = " + body);
		System.out.println("渠道名称 = " + topic);
		System.out.println("********* Redis消息监听器2 ******** 结束 ********");
	}

}
