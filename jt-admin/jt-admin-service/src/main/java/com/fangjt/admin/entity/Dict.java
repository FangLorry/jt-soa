package com.fangjt.admin.entity;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


/**
 * 字典Entity
 * @author lvyulin
 * @version 2013-05-15
 */
@Table(name="jt-dict")
public class Dict  extends BaseEntity<String>{

	@Transit
	private static final long serialVersionUID = 1L;
	@Column
	private String value;	// 数据值
	@Column
	private String label;	// 标签名
	@Column
	private String type;	// 类型
	@Column
	private String description;// 描述
	@Column
	private Integer sort;	// 排序
	@Column
	private String parentId;//父Id

	public Dict(){}
	public Dict(String value, String label){
		this.value = value;
		this.label = label;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		return label;
	}
}