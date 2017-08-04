package com.fangjt.admin.entity;

import javax.validation.constraints.NotBlank;

import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;

@Table(name="jt_role")
public class Role extends BaseEntity<String> {

	@Transit
	private static final long serialVersionUID = -4587808229112966646L;
	
	@NotBlank
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
