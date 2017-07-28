package com.fangjt.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * 创建缓存工具类
 * */
public class EhcacheUtil {
	private static final CacheManager cacheManager  = CacheManager.getInstance();
	
	private static Cache cache = new Cache(new CacheConfiguration("systemCache",5000)
			.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO).timeoutMillis(300).timeToLiveSeconds(60*60));
	static {
		cacheManager.addCache(cache);
	}
	//增加/更新
	public static void updateItem(String key,Object item){
		delItem(key);
		Element element = new Element(key,item);
		cache.put(element);
	}
	
	public static void delItem(String key){
		if(cache.get(key)!=null){
			cache.remove(key);
		}
	}
	
	public static Object getItem(String key){
		Element element =  cache.get(key);
		if(element!=null){
			return element.getObjectValue();
		}
		return null;
	}
	
}
