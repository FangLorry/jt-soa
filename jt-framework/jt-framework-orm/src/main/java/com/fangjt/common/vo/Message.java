package com.fangjt.common.vo;

/**
 * 使用ThreadLocal防止多线程问题 edit by fang 160722
 */
public class Message {
	private static ThreadLocal<MessageData> mapThreadLocal = new ThreadLocal<MessageData>();
	private static MessageData map;
	static {
		initMessage();
	}

	public static MessageData initMessage() {
		map = mapThreadLocal.get();
		if (map == null) {
			map = new MessageData();
			mapThreadLocal.set(map);
		}
		return map;
	}

	public static MessageData success(String cont) {
		map.setMsg(cont); 
		map.setResult_code(0);
		if(map.getObj() != null){
			map.setObj(null);
		}
		return map;
	}
	
	public static MessageData success(String cont,Object obj) {
		map.setMsg(cont); 
		map.setResult_code(0);
		map.setObj(obj); 
		return map;
	}

	public static MessageData error(String cont) {
		map.setMsg(cont); 
		map.setResult_code(1);
		if(map.getObj() != null){
			map.setObj(null);
		}
		return map;
	}

}
