package com.fangjt.common.orm;

import java.util.List;

//sql过滤器
public class Filter {
	public enum OperateEnum{
		eq(" = "),
		lt(" < "),
		gt(" > "),
		ue(" <> "),
		like(" like "),
		lte(" <= "),
		gte(" >= "),
		is_null(" is null "),
		not_null(" is not null "),
		or("or");
		private String value;
		OperateEnum(String value){
			this.value = value;
		}
		public String getValue(){
			return value;
		}
	}
	
	private String key;
	private OperateEnum operateEnum;
	private String value;
	private boolean ignoreCase;
	private List<Filter> filterList ;
	
	public Filter(){}
	public Filter(String key ,OperateEnum operateEnum,String value){
		this.key=key;
		this.operateEnum = operateEnum;
		this.value = value;
	}
	public Filter(String key ,OperateEnum operateEnum,String value, boolean ignoreCase){
		this.key=key;
		this.operateEnum = operateEnum;
		this.value = value;
		this.ignoreCase = ignoreCase;
	}
	
	public Filter(List<Filter> filterList,OperateEnum operateEnum){
		this.operateEnum = operateEnum;
		this.filterList = filterList;
	}
	
	//等于
	public static Filter eq(String key,String value){
		return new Filter( key ,OperateEnum.eq, value);
	}
	public static Filter eq(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.eq, value,ignoreCase);
	}
	//小于
	public static Filter lt(String key,String value){
		return new Filter( key ,OperateEnum.lt, value);
	}
	public static Filter lt(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.lt, value, ignoreCase);
	}
	public static Filter lte(String key,String value){
		return new Filter( key ,OperateEnum.lte, value);
	}
	public static Filter lte(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.lte, value, ignoreCase);
	}
	//大于
	public static Filter gt(String key,String value){
		return new Filter( key ,OperateEnum.gt, value);
	}
	public static Filter gt(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.gt, value, ignoreCase);
	}
	public static Filter gte(String key,String value){
		return new Filter( key ,OperateEnum.gte, value);
	}
	public static Filter gte(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.gte, value, ignoreCase);
	}
	//不等于
	public static Filter ue(String key,String value){
		return new Filter( key ,OperateEnum.ue, value);
	}
	public static Filter ue(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.ue, value, ignoreCase);
	}
	//空判断
	public static Filter isNull(String key,boolean ignoreCase){
		return new Filter( key ,OperateEnum.is_null, null, ignoreCase);
	}
	public static Filter isNull(String key){
		return new Filter( key ,OperateEnum.is_null, null);
	}
	public static Filter notNull(String key){
		return new Filter( key ,OperateEnum.not_null, null);
	}
	public static Filter notNull(String key,boolean ignoreCase){
		return new Filter( key ,OperateEnum.not_null, null, ignoreCase);
	}
	//模糊匹配
	public static Filter like(String key,String value){
		return new Filter( key ,OperateEnum.like, value);
	}
	public static Filter like(String key,String value,boolean ignoreCase){
		return new Filter( key ,OperateEnum.like, value, ignoreCase);
	}
	public static Filter or(List<Filter> flist){
		return new Filter(flist,OperateEnum.or);
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public OperateEnum getOperateEnum() {
		return operateEnum;
	}
	public void setOperateEnum(OperateEnum operateEnum) {
		this.operateEnum = operateEnum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	public List<Filter> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}
	
	
}
