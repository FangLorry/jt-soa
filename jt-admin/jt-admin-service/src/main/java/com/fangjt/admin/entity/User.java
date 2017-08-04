package com.fangjt.admin.entity;

import javax.validation.constraints.NotBlank;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


@Table(name="jt_user")
public class User extends BaseEntity<String> {

	/**
	 * 序列号
	 */
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotBlank
	private String username ;
	
	@Column
	@NotBlank
	private String pwd ;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
