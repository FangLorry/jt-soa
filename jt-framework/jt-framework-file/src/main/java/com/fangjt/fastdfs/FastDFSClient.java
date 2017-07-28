package com.fangjt.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.stereotype.Component;

import com.fangjt.common.orm.Filter;
import com.fangjt.common.utils.UUIDUtils;
import com.fangjt.mongodb.entity.MongoFile;
import com.fangjt.mongodb.utils.MongoDBUtils;

import net.sf.ehcache.pool.sizeof.SizeOf;

/**
 * 文件上传
 * TODO MD5验证Mongodb存储文件信息
 * @author fang
 *
 */
@Component
public class FastDFSClient {
	public FastDFSClient(){}

	public String uploadFile(byte[] buff, String file_ext_name) {
		return send(buff, file_ext_name, null);
	}

	public static byte[] downloadFile(String file_id) {

		if (file_id == null)
			return null;
		byte[] file_buff = null;
		;

		StorageClient1 storageClient1 = null;
		ConnectionPool pool = ConnectionPool.instance;
		storageClient1 = pool.checkOut(10);
		try {
			file_buff = storageClient1.download_file1(file_id);
			pool.checkin(storageClient1);
		} catch (IOException e) {
			// 如果出现了IO异常应该销毁此连接
			pool.drop(storageClient1);
			e.printStackTrace();
		} catch (Exception e) {
			pool.drop(storageClient1);
			e.printStackTrace();
		}

		return file_buff;
	}

	public static Boolean validFile(String file_id) {

		Boolean result = false;
		if (file_id == null)
			return false;
		byte[] file_buff = null;
		;

		StorageClient1 storageClient1 = null;
		ConnectionPool pool = ConnectionPool.instance;
		storageClient1 = pool.checkOut(10);
		try {
			file_buff = storageClient1.download_file1(file_id);
			if (file_buff.length > 0) {
				result = true;
			}
			pool.checkin(storageClient1);
		} catch (IOException e) {
			// 如果出现了IO异常应该销毁此连接
			pool.drop(storageClient1);
			e.printStackTrace();
		} catch (Exception e) {
			pool.drop(storageClient1);
			e.printStackTrace();
		}
		return result;
	}

	public static int deleteFile(String file_id) {
		if (file_id == null)
			return 0;

		int i = 0;

		StorageClient1 storageClient1 = null;
		ConnectionPool pool = ConnectionPool.instance;
		storageClient1 = pool.checkOut(10);
		try {
			i = storageClient1.delete_file1(file_id);
			pool.checkin(storageClient1);
		} catch (IOException e) {
			// 如果出现了IO异常应该销毁此连接
			pool.drop(storageClient1);
			e.printStackTrace();
		} catch (Exception e) {
			pool.drop(storageClient1);
			e.printStackTrace();
		}

		return i;
	}

	// 上传缓存数据到storage服务器
	private String send(byte[] buff, String file_ext_name, NameValuePair[] mate_list) {
		
		String upPath = null;
		StorageClient1 storageClient1 = null;
		ConnectionPool pool = ConnectionPool.instance;
		storageClient1 = pool.checkOut(10);
		try {
			upPath = storageClient1.upload_file1(buff, file_ext_name, mate_list);
			pool.checkin(storageClient1);
		} catch (IOException e) {
			// 如果出现了IO异常应该销毁此连接
			pool.drop(storageClient1);
			e.printStackTrace();
		} catch (Exception e) {
			pool.drop(storageClient1);
			e.printStackTrace();
		}
		
		return upPath;
	}

	
}
