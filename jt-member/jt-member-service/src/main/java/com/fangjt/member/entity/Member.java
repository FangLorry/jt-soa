package com.fangjt.member.entity;

import javax.validation.constraints.NotBlank;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;

@Table(name="jt_member")
public class Member extends BaseEntity<String> {
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotBlank
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
