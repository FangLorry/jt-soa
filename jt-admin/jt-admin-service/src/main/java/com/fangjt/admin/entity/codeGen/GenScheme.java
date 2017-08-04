package com.fangjt.admin.entity.codeGen;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


/**
 * 生成方案Entity
 * @author 
 * @version 2013-10-15
 */
@Table(name="jt_gen_scheme")
public class GenScheme extends BaseEntity<String>{
	@Transit
	private static final long serialVersionUID = 1L;
	@Column
	private String name; 	// 名称
	@Column
	private String category;		// 分类
	@Column
	private String packageName;		// 生成包路径
	@Column
	private String moduleName;		// 生成模块名
	@Column
	private String functionName;		// 生成功能
	@Column
	private String functionNameSimple;		// 生成功能名（简写）
	@Column
	private String functionAuthor;		// 生成功能作者
	@Column
	protected String remarks;	// 备注
	
	private Model model ;
	
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFunctionNameSimple() {
		return functionNameSimple;
	}

	public void setFunctionNameSimple(String functionNameSimple) {
		this.functionNameSimple = functionNameSimple;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}


