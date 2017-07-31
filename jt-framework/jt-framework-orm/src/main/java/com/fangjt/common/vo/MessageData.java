package com.fangjt.common.vo;

/**
 * 使用ThreadLocal防止多线程问题 edit by fang 160722
 */
public class MessageData {
	
	private String msg ;
	private int result_code ;
	private Object obj = null;
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResult_code() {
		return result_code;
	}

	public void setResult_code(int result_code) {
		this.result_code = result_code;
	}
	
	public boolean checkIsSuccess(){
		return result_code == 0 ;
	}

	
	

}
