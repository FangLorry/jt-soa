package com.fangjt.cache;
 
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import redis.clients.jedis.JedisPool;


/**
 * @author fang
 * @param redis 初始化配置
 *
 */
@Configuration
public class JedisConfig {
	@Value("#{redisProperties['redis.pool.maxActive']}")
	private int maxActive ;//最大连接数
	
	@Value("#{redisProperties['redis.pool.maxIdle']}")
	private int maxIdle ;//最大空闲连接数
	
	@Value("#{redisProperties['redis.pool.minIdle']}")
	private int minIdle ;//初始化连接数
	
	@Value("#{redisProperties['redis.pool.maxWait']}")
	private long maxWait ;//最大等待时间
	
	@Value("#{redisProperties['redis.pool.testOnBorrow']}")
	private boolean testOnBorrow ;//对拿到的connection进行validateObject校验-
	
	@Value("#{redisProperties['redis.pool.testOnReturn']}")
	private boolean testOnReturn ;//在进行returnObject对返回的connection进行validateObject校验
	
	@Value("#{redisProperties['redis.pool.testWhileIdle']}")
	private boolean testWhileIdle ;//定时对线程池中空闲的链接进行validateObject校验
	
	@Value("#{redisProperties['redis.pool.host']}")
	private  String host ;//ip地址
	@Value("#{redisProperties['redis.pool.port']}")
	private  int port ;
	@Value("#{redisProperties['redis.pool.timeout']}")
	private  int timeout ;
	@Value("#{redisProperties['redis.pool.password']}")
	private  String password ;
	
	public GenericObjectPoolConfigWrapper genericObjectPoolConfigWrapper(){
		GenericObjectPoolConfigWrapper wapper = new GenericObjectPoolConfigWrapper();
		wapper.setMaxActive(maxActive);
		wapper.setMaxIdle(maxIdle);
		wapper.setMinIdle(minIdle);
		wapper.setMaxWait(maxWait);
		wapper.setTestOnReturn(testOnReturn);
		wapper.setTestOnBorrow(testOnBorrow);
		wapper.setTestWhileIdle(testWhileIdle);
		return wapper;
	}
	
	@Bean(name="jedisBoocuPool")
	public JedisPool jedisPool(){
		JedisPool jedisPool = new JedisPool( genericObjectPoolConfigWrapper().getConfig(), host,port,timeout,password);
		return jedisPool;
	}
	
	@Bean(name="redisProperties")
	public PropertiesFactoryBean redisProperties() throws IOException{
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
	    Resource[] resources=resolver.getResources("classpath*:/redis.properties");  
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocations(resources);
		return propertiesFactoryBean;
	}
	
}
