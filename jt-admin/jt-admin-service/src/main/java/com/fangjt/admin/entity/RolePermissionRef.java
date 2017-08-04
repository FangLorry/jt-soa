package com.fangjt.admin.entity;

import javax.validation.constraints.NotBlank;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


@Table(name="jt_role_permission_ref")
public class RolePermissionRef extends BaseEntity<String> {

	/**
	 * 序列号
	 */
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotBlank
	private String roleId ;
	
	@Column
	@NotBlank
	private String permissionId ;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	

	
	
}
