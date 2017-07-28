package com.fangjt.common.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用ThreadLocal防止多线程问题 edit by fang 160722
 */
public class Message {
	private static ThreadLocal<Map<String, Object>> mapThreadLocal = new ThreadLocal<Map<String, Object>>();
	private static Map<String, Object> map;

	static {
		initMessage();
	}

	public static Map<String, Object> initMessage() {
		map = mapThreadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			mapThreadLocal.set(map);
		}
		return map;
	}

	public static Map<String, Object> success(String cont) {
		map.put("msg", cont);
		map.put("result_code", 0);
		if(map.containsKey("data")){
			map.remove("data");
		}
		return map;
	}
	
	public static Map<String, Object> success(String cont,Object obj) {
		map.put("msg", cont);
		map.put("result_code", 0);
		map.put("data", obj);
		return map;
	}

	public static Map<String, Object> error(String cont) {
		map.put("msg", cont);
		map.put("result_code", 1);
		if(map.containsKey("data")){
			map.remove("data");
		}
		return map;
	}

}
