package com.fangjt.mq.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjt.mq.service.MqProducer;

@Service
public class MqProducerImpl implements MqProducer {

	@Autowired
	private AmqpTemplate ampqTemplate ; 
	
	@Override
	public void sendMsg(String queryKey, Object obj) {
		ampqTemplate.convertAndSend(queryKey, obj);
	}

}
