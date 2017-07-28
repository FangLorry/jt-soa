package com.fangjt.common.orm;

import java.util.List;

//sql过滤器
public class SelectOption {
	private List<Filter> fList ;
	private List<Sequencer> sList;
	private String selColumns;
	public SelectOption(){}
	public SelectOption(List<Filter> fList,List<Sequencer> sList,String selColumns){
		this.fList = fList;
		this.sList = sList;
		this.selColumns = selColumns;
	}
	public List<Filter> getfList() {
		return fList;
	}
	public void setfList(List<Filter> fList) {
		this.fList = fList;
	}
	public List<Sequencer> getsList() {
		return sList;
	}
	public void setsList(List<Sequencer> sList) {
		this.sList = sList;
	}
	public String getSelColumns() {
		return selColumns;
	}
	public void setSelColumns(String selColumns) {
		this.selColumns = selColumns;
	}
	
	
}
