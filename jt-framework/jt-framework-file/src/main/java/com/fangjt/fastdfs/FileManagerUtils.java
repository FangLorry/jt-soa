package com.fangjt.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fangjt.common.orm.Filter;
import com.fangjt.common.utils.UUIDUtil;
import com.fangjt.mongodb.entity.MongoFile;
import com.fangjt.mongodb.utils.MongoDBUtils;

@Component
public class FileManagerUtils {

	@Autowired
	private FastDFSClient fastDFSClient;

	/**
	 * 上传文件
	 * 
	 * @param file
	 */
	public String uploadFile(File file) {
		MongoFile mongoFile = null;
		byte[] buff = getFileBuff(file);
		String md5 = "";
		try {
			md5 = byte2Md5(buff);
			mongoFile = findOne(md5);
			if (mongoFile != null) {
				return mongoFile.getFileId();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileId = fastDFSClient.uploadFile(buff,getFileExtName(file.getName()));
		// 將文件信息存到mongodb中
		mongoFile = new MongoFile();
		mongoFile.setFileId(fileId);
		mongoFile.setId(UUIDUtil.uuid());
		mongoFile.setCreateTime(new Date());
		mongoFile.setUpdateTime(new Date());
		mongoFile.setFileName(file.getName());
		mongoFile.setMd5(md5);
		mongoFile.setFileType("img");
		mongoFile.setFileSize(file.length());
		addFile(mongoFile);
		return fileId;
	}
	

	// 通过文件名称获取文件扩展名
	private String getFileExtName(String name) {
		String ext_name = null;
		if (name != null) {
			if (name.contains(".")) {
				ext_name = name.substring(name.indexOf(".") + 1);
			}
		}
		return ext_name;
	}
	
	// 将文件缓存到字节数组中
	private byte[] getFileBuff(File file) {
		byte[] buff = null;
		try {
			FileInputStream inputStream = new FileInputStream(file);
			buff = new byte[inputStream.available()];
			inputStream.read(buff);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buff;
	}

	/**
	 * 存儲文件信息
	 */
	private void addFile(MongoFile file) {
		MongoDBUtils.instance.insert(file);
	}

	/**
	 * 验证文件数据库该图片MD5串是否已存在
	 */
	private MongoFile findOne(String md5) {
		return MongoDBUtils.instance.findOne(MongoFile.class, Filter.eq("md5", md5));
	}

	/**
	 * byte[] => md5
	 */
	private String byte2Md5(byte[] fileBuff) throws NoSuchAlgorithmException, IOException {
		StringBuffer md5 = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(fileBuff, 0, fileBuff.length);
		byte[] mdbytes = md.digest();

		// convert the byte to hex format
		for (int i = 0; i < mdbytes.length; i++) {
			md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return md5.toString();
	}
}
