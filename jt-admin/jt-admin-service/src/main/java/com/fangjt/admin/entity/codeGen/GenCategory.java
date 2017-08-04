
package com.fangjt.admin.entity.codeGen;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 生成方案Entity
 * @author 
 * @version 2013-10-15
 */
@XmlRootElement(name="category")
public class GenCategory {
	
	private static final long serialVersionUID = 1L;
	
	private String value;	// 数据值
	private String label;	// 标签名
	private String type;	// 类型
	private String description;// 描述
	private Integer sort;	// 排序
	private String parentId;//父Id
	
	
	private List<String> template;			// 主表模板
	private List<String> childTableTemplate;// 子表模板
	
	public static String CATEGORY_REF = "category-ref:";

	public GenCategory() {
		super();
	}

	
	@XmlAttribute(name = "value") 
	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}


	@XmlAttribute(name = "label")  
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



	@XmlElement(name = "template")
	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}
	
	@XmlElementWrapper(name = "childTable")
	@XmlElement(name = "template")
	public List<String> getChildTableTemplate() {
		return childTableTemplate;
	}

	public void setChildTableTemplate(List<String> childTableTemplate) {
		this.childTableTemplate = childTableTemplate;
	}
	
	
	
}


