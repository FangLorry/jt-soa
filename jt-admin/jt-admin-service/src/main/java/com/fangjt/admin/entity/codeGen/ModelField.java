package com.fangjt.admin.entity.codeGen;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;

/**
 * 属性
 * @author fang 20170802
 *
 */
@Table(name = "jt_model_field")
public class ModelField extends BaseEntity<String> {
	/**
	 * 
	 */
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
	private String name;	//名字
	@Column
	private String modelId;	//表Id
	
	@Column
	private String jdbcType;	// JDBC类型
	@Column
	private String myBatisJdbcType =""; //myBatis_jdbc_type
	@Column
	private String javaType;	// JAVA类型
	@Column
	private String javaField;	// JAVA字段名
	@Column
	private Integer isPk = 0;		// 是否主键（1：主键）
	@Column
	private Integer isNull = 0;		// 是否可为空（1：可为空；0：不为空）
	@Column
	private Integer isInsert = 0;	// 是否为插入字段（1：插入字段）
	@Column
	private Integer isEdit = 0;		// 是否编辑字段（1：编辑字段）
	@Column
	private Integer isList = 0;		// 是否列表字段（1：列表字段）
	@Column
	private Integer isQuery = 0;		// 是否查询字段（1：查询字段）
	@Column
	private String queryType ;	// 查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE等）
	@Column
	private String htmlShowType ;	// 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
	@Column
	private String dictType = "";	// 字典类型
	@Column
	private Integer sort = 0;		// 排序（升序）
	@Column
	private String remark ;		// 备注
	@Column
	private String comments ;		// 注释
	
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getHtmlShowType() {
		return htmlShowType;
	}
	public void setHtmlShowType(String htmlShowType) {
		this.htmlShowType = htmlShowType;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getMyBatisJdbcType() {
		return myBatisJdbcType;
	}
	public void setMyBatisJdbcType(String myBatisJdbcType) {
		this.myBatisJdbcType = myBatisJdbcType;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getJavaField() {
		return javaField;
	}
	public void setJavaField(String javaField) {
		this.javaField = javaField;
	}
	public Integer getIsPk() {
		return isPk;
	}
	public void setIsPk(Integer isPk) {
		this.isPk = isPk;
	}
	public Integer getIsNull() {
		return isNull;
	}
	public void setIsNull(Integer isNull) {
		this.isNull = isNull;
	}
	public Integer getIsInsert() {
		return isInsert;
	}
	public void setIsInsert(Integer isInsert) {
		this.isInsert = isInsert;
	}
	public Integer getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}
	public Integer getIsList() {
		return isList;
	}
	public void setIsList(Integer isList) {
		this.isList = isList;
	}
	public Integer getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(Integer isQuery) {
		this.isQuery = isQuery;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
