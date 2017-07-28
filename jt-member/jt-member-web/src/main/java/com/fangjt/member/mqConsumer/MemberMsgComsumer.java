package com.fangjt.member.mqConsumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

public class MemberMsgComsumer implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message msg, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		String str = new String(msg.getBody());
		try {
			JSONObject obj = JSONObject.parseObject(str);
			if (obj.containsKey("error")) {
				// channel.basicAck(msg.getMessageProperties().getDeliveryTag(),
				// false); // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
				// channel.basicNack(msg.getMessageProperties().getDeliveryTag(),
				// false, true); // ack返回false，并重新回到队列，api里面解释得很清楚
				channel.basicReject(msg.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
			}
			// 正常执行
			System.err.println("-------12313---接收到了33231:" + str);
			System.err.println("---------执行任务--------------");

		} catch (Exception ex) {
			System.err.println("---------报异常了--------------");
		}
	}

}