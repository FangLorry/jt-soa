package com.fangjt.admin.entity.codeGen;

import java.util.List;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;
import com.google.common.collect.Lists;

/**
 * 数据模型 
 * @author fang 20170802
 *
 */
@Table(name = "jt_model")
public class Model extends BaseEntity<String> {
	@Transit
	private static final long serialVersionUID = 2819593584579295199L;
	@Column
	private String name; 	// 名称
	@Column
	private String comments;		// 描述
	@Column
	private String className;		// 实体类名称
	@Column
	private String parentTable;		// 关联父表
	@Column
	private String parentTableFk;		// 关联父表外键

	private List<ModelField> columnList = Lists.newArrayList();	// 表列

	private Model parent;	// 父表对象
	private List<Model> childList = Lists.newArrayList();	// 子表列表
	@Column
	protected String remarks;	// 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentTable() {
		return parentTable;
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	public String getParentTableFk() {
		return parentTableFk;
	}

	public void setParentTableFk(String parentTableFk) {
		this.parentTableFk = parentTableFk;
	}

	public List<ModelField> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ModelField> columnList) {
		this.columnList = columnList;
	}

	public Model getParent() {
		return parent;
	}

	public void setParent(Model parent) {
		this.parent = parent;
	}

	public List<Model> getChildList() {
		return childList;
	}

	public void setChildList(List<Model> childList) {
		this.childList = childList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
