package com.fangjt.common.orm;

import java.util.List;

//sql过滤器建造器
public class SelectOptionBuilder {
	private SelectOption selectOption;
	public SelectOptionBuilder(SelectOption selectOption){
		this.selectOption = selectOption;
	} 
	public SelectOptionBuilder filter(List<Filter> flist){
		selectOption.setfList(flist);
		return this;
	}
	public SelectOptionBuilder sequencer(List<Sequencer> sList){
		selectOption.setsList(sList);
		return this;
	}
	public SelectOptionBuilder selColumn(String columns){
		selectOption.setSelColumns(columns);
		return this;
	}
	public SelectOption getSelectOption() {
		return selectOption;
	}
	
}
