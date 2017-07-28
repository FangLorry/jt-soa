package com.fangjt.mq.service;

public interface MqProducer {
	public void sendMsg(String queryKey,Object obj);
}
