package com.fangjt.common.orm;

//sql 分页过滤器
public class SelectPageOption extends SelectOption {
	
	private int pageNum;
	private int pageSize;
	public SelectPageOption(){}
	public SelectPageOption(int pageNum,int pageSize){
		if(pageNum<1){
			pageNum=1;
		}
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	};
	public int getStartIndex() {
		return pageSize*(pageNum-1);
	}
	
	
	
}
