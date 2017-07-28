package com.fangjt.common.service;

import java.io.Serializable;
import java.util.List;

import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.Page;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.Sequencer;


public abstract interface IBaseService<T, ID extends Serializable> {
	//获取所有行
	 public abstract List<T> findAll();
	 //按条件获取所有行
	 public abstract List<T> findList(T obj);
	 //加上过滤条件
	 public abstract  List<T> findList(SelectOptionBuilder selectOptionBuilder);
	 public abstract  List<T> findList(Filter filter);
	 public abstract  List<T> findList(Filter filter,Sequencer sequencer );
	 //加上过滤条件--分页
	 public abstract  Page<T> findPage(SelectOptionBuilder selectOptionBuilder);
	 //获取所有行
	 public abstract T findOne(ID id);
	 //获取分页行
	// public abstract Page<T> findPage(T obj);
	 //更新,增加实体
	 public abstract void update(T obj); 
	 //按实体删除
	 public abstract void del(T obj);
	 //按Id删除
	 public abstract void delOne(ID id);
	
}
