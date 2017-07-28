package com.fangjt.fastdfs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  
* @Description: 
* fastdfs 连接池
* @author fang 
*
 */

public enum ConnectionPool {
	instance ;
	/** 连接池默认最小连接数 */  
    private int minPoolSize = 10;  
    private FastDFSStorage fastDFSClient ;
	private Object obj = new Object();
	//被使用的连接池
	private ConcurrentHashMap<StorageClient1,Object> busyConnectionPool = null;
	//空闲链接
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;
	
	private ConnectionPool(){
        init();
	}
	
	//初始化连接池
    private void init() {
    	fastDFSClient = FastDFSStorage.getInstance();
    	busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
    	idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(minPoolSize);
        for(int i=0; i<minPoolSize; i++){
            try {
				idleConnectionPool.add(fastDFSClient.getStorage());
			} catch (Exception e) {
				System.err.println("初始化创建fastdfs-client失败,请检查链接设置");
				e.printStackTrace();
			}
            System.out.println("------------------------- :connection "+i);
        }
    }
    
    
    /**
     * 取出链接f
     */
    public StorageClient1 checkOut(int waitTime){
    	StorageClient1 storageClient1 = null;
    	try {
			storageClient1 = idleConnectionPool.poll(waitTime,TimeUnit.SECONDS);
			if(storageClient1 != null){
				busyConnectionPool.put(storageClient1, obj);
			}else{
				drop(storageClient1);
				return checkOut(waitTime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return storageClient1;
    }
    
    /**
     * 回收链接
     */
    public void checkin(StorageClient1 storageClinet){
    	if(busyConnectionPool.remove(storageClinet)!=null){
    		idleConnectionPool.add(storageClinet);
    	}
    }
    /**
     *  如果连接无效则抛弃，新建连接来补充到池里
     */
	public void drop(StorageClient1 storageClinet) {
		if (busyConnectionPool.remove(storageClinet) != null) {
			try {
				idleConnectionPool.add(fastDFSClient.getStorage());
			} catch (Exception e) {
				System.err.println("创建fastdfs-client失败,请检查链接设置");
				e.printStackTrace();
			}
		}
	}
    
}