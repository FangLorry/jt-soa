package com.fangjt.cache;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Jedis 工具类
 * @author yinls
 * @date 2016-6-12
 *
 */
@Component
public class JedisUtils {

    public static final Logger log = LoggerFactory.getLogger(JedisUtils.class);

    @Resource(name="jedisBoocuPool")
    public JedisPool jedisPool;

    /**
     * 从池里获取jedis实例
     * @return
     * @throws JedisException
     */
    public Jedis getJedis() throws JedisException {
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            log.warn("failed: jedisPool getResource.", e);
            release(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * 释放链接
     * @param jedis
     * @param isBroken
     */
    public void release(Jedis jedis) {
        if (jedis != null) {
        	//jedis.disconnect();
        	jedisPool.returnResource(jedis);
        }
    }

    /**
     * 集合设置值
     * @param key
     * @param value
     * @return
     */
    public boolean hset(String key,String field, String value) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key, field, value);
            return true;
        } catch (JedisException e) {
            log.error("hset error: ", e);
        } finally {
            release(jedis);
        }
        return false;
    }
    /**
     * 集合设置值
     * @param key
     * @param value
     * @return
     */
    public boolean hset(String key,String field, String value,int expired) {
    	Jedis jedis = null;
    	try {
    		jedis = getJedis();
    		jedis.hset(key, field, value);
    		return true;
    	} catch (JedisException e) {
    		log.error("hset error: ", e);
    	} finally {
    		release(jedis);
    	}
    	return false;
    }
    
    /**
     * 集合獲取值
     * @param key
     * @param value
     * @return
     */
    public String hget(String key,String field) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key, field);
        } catch (JedisException e) {
            log.error("hget error: ", e);
        } finally {
            release(jedis);
        }
        return "";
    }
    
    /**
     * 集合判斷值是否存在
     * @param key
     * @param value
     * @return
     */
    public boolean hexists(String key,String field) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hexists(key, field);
        } catch (JedisException e) {
            log.error("hget error: ", e);
        } finally {
            release(jedis);
        }
        return false;
    }
    
    public boolean set(String key, String value) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            return true;
        } catch (JedisException e) {
            log.error("set error: ", e);
        } finally {
            release(jedis);
        }
        return false;
    }

    /**
     * 获取值
     * @param key
     * @param defaultValue
     * @return
     */
    public String get(String key) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (JedisException e) {
            log.error("get error: ", e);
        } finally {
            release(jedis);
        }
        return "";
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public boolean del(String key) {
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
            return true;
        } catch (JedisException e) {
            log.error("del error: ", e);
        } finally {
            release(jedis);
        }
        return false;
    }
    
    /**
     * 设置值（同时设置有效时间）
     * @param key
     * @param value
     * @param seconds 有效时间 单位s
     * @return 1：设置了有效时间  0：没有设置有效时间/不能设置有效时间 
     */
    public long setCache(String key, String value,int seconds) {
    	if (StringUtils.isEmpty(key) || seconds == 0) {  
            return 0;  
        } 
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            return jedis.expire(key, seconds);
        } catch (JedisException e) {
            log.error("get error: ", e);
        } finally {
            release(jedis);
        }
        return 0;
    }
    
    /**
     * 设置值（同时设置失效时间点）
     * @param key
     * @param value
     * @param expireDate 失效时间yyyy-MM-dd HH:mm:ss
     * @return 1：设置了失效时间  0：没有设置失效时间/不能设置失效时间
     */
    public long setCacheDate(String key, String value,Date expireDate) {
    	if (StringUtils.isEmpty(key) || expireDate ==null) {  
            return 0;  
        }  
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            Timestamp unixTime = Timestamp.valueOf(DateUtils.formatDate(expireDate, "yyyy-MM-dd HH:mm:ss")  );
            return jedis.expireAt(key, Long.valueOf(unixTime.toString()));
        } catch (JedisException e) {
            log.error("get error: ", e);
        } finally {
            release(jedis);
        }
        return 0;
    }
    
    /**
     * 设置List
     * @param key
     * @param values
     * @return
     */
    public boolean addList(String key, List<String> values) {  
    	if (StringUtils.isEmpty(key) || values == null) { 
    		log.error("addList: key or values is null");
            return false;  
        }  
    	Jedis jedis = null;
        try {  
        	jedis = getJedis();
            for (String value : values) {
            	jedis.lpush(key, value);
			}
            return true;  
        } catch (Exception e) {  
            log.error("addList error: ", e);  
        } finally {
            release(jedis);
        }
        return false;  
    }
    
    /**
     * 获取List：start=null && end=null返回所有
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String key,Long start,Long end){
    	if (StringUtils.isEmpty(key)) { 
    		log.error("getList: key is null");
            return null;  
        } 
    	if(start == null){
    		start = 0L;
    	}
    	if(end == null){
    		end = -1L;
    	}
    	Jedis jedis = null;
        try {  
        	jedis = getJedis(); 
            return jedis.lrange(key, start, end);  
        } catch (Exception e) {  
            log.error("addList error: ", e);  
        } finally {
            release(jedis);
        }
        return null;   
    } 
    
    /**
     * 设置List（同时设置过期时间）
     * @param key
     * @param seconds 单位s
     * @param values
     * @return
     */
    public boolean addListCache(String key, int seconds, List<String> values) {  
    	if (StringUtils.isEmpty(key) || values == null) { 
    		log.error("addListCache: key or values is null");
            return false;  
        }  
    	Jedis jedis = null;
        try {  
            boolean result = this.addList(key, values);
            if(result){ 
            	jedis = getJedis();
                long i = jedis.expire(key, seconds);  
                return i==1;  
            }  
        } catch (Exception e) {  
            log.error("addList error: ", e);  
        } finally {
            release(jedis);
        }
        return false;  
    }
    
}