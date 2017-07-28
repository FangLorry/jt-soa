package com.fangjt.fastdfs;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @Description: fastdfs 存储服务器工具类
 * @author fang
 *
 */
@Configuration
public class FastDFSStorage {
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;

	@Value("#{fdfsProperties['fdfs.pool.connect_timeout']}")
	private int connect_timeout;
	@Value("#{fdfsProperties['fdfs.pool.network_timeout']}")
	private int network_timeout;
	@Value("#{fdfsProperties['fdfs.pool.tracker_server']}")
	private String tracker_server;
	@Value("#{fdfsProperties['fdfs.pool.http.tracker_server_port']}")
	private int port;
	
	private static Object lock = new Object();
	
	private static FastDFSStorage fastDFSStorage;
	
	public static FastDFSStorage getInstance(){
		synchronized(lock){
			if(fastDFSStorage == null){
				return new FastDFSStorage();
			}
		}
		return fastDFSStorage;
	}

	public StorageClient1 getStorage() throws Exception {
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		StorageClient1 storage = new StorageClient1(trackerServer, storageServer);
		if (trackerServer != null) {
			try {
				trackerServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return storage;
	}

	private void initClinetGlobal() {
		// 连接超时时间
		ClientGlobal.setG_connect_timeout(connect_timeout);
		// 网络超时时间
		ClientGlobal.setG_network_timeout(network_timeout);
		ClientGlobal.setG_anti_steal_token(false);
		// 字符集
		ClientGlobal.setG_charset("UTF-8");
		ClientGlobal.setG_secret_key(null);
		// HTTP访问服务的端口号
		ClientGlobal.setG_tracker_http_port(port);

		InetSocketAddress[] trackerServers = new InetSocketAddress[2];
		trackerServers[0] = new InetSocketAddress(tracker_server, port);
		trackerServers[1] = new InetSocketAddress(tracker_server, port);
		TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
		// tracker server 集群
		ClientGlobal.setG_tracker_group(trackerGroup);

	}
	
	@Bean(name="sdf")
	public Object initGloble(){
		initClinetGlobal();
		System.err.println(port);
		return null;
	}

	@Bean(name = "fdfsProperties")
	public PropertiesFactoryBean redisProperties() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:/fdfs.properties");
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocations(resources);
		return propertiesFactoryBean;
	}

}
