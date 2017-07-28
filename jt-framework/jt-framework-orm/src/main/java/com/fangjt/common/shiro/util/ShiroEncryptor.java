package com.fangjt.common.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroEncryptor {
	/**
	 * hashType:加密方式
	 * pwd:加密字符
	 * salt:加密盐
	 * */
	public static SimpleHash encrypt(String hashType,String str,String salt){
		String hashSalt = new SimpleHash(hashType, salt).toString();
		return new SimpleHash(hashType, str, hashSalt);
	}
	public static SimpleHash encrypt(String str,String salt){
		String hashSalt = new SimpleHash("MD5", salt).toString();
		return new SimpleHash("MD5", str, hashSalt);
	}
}
