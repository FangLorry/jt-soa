package com.fangjt.common.exception;

/**
 * 参数验证失败异常
 * @author fangjt
 *
 */
public class ValitationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public String message ;
	
	public ValitationException(){
		message = super.getMessage();
	}
	
	public ValitationException(String message){
		this.message = message;
	}
}
