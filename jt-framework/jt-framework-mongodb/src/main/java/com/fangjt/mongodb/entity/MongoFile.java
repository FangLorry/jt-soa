package com.fangjt.mongodb.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 存储文件信息
 * @author fang
 *
 */
@Document(collection="file")
public class MongoFile {
	/**
	 * Id
	 */
	private String id ;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件fastdfsId
	 */
	private String fileId ;
	/**
	 * 文件大小
	 */
	private long fileSize;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件Md5验证
	 */
	private String md5;
	
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime + ", fileName=" + fileName
				+ ", fileId=" + fileId + ", fileSize=" + fileSize + ", fileType=" + fileType + ", md5=" + md5 + "]";
	}
	
	
}
