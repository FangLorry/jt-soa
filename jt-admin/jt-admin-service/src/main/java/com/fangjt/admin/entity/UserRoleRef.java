package com.fangjt.admin.entity;

import javax.validation.constraints.NotBlank;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


@Table(name="jt_user_role_ref")
public class UserRoleRef extends BaseEntity<String> {

	/**
	 * 序列号
	 */
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotBlank
	private String userId ;
	
	@Column
	@NotBlank
	private String roleId ;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
}
