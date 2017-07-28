package com.fangjt.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.fangjt.common.entity.BaseEntity;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.utils.CUDTemplate;
  
/** 
 * MyBatis CRUD基接口 
 * @author ALLEN.HU 
 * 
 * @param <T> 处理的POJO对象 
 */  
public interface BaseMapper<T extends BaseEntity> {  
      
    /** 
     * @param obj 
     * @return 
     */  
	@SelectProvider(type = CUDTemplate.class,method = "find")
	@ResultMap("result")
    public List<T> find(T obj);  
	
	@SelectProvider(type = CUDTemplate.class,method = "findCon")
	@ResultMap("result")
	public List<T> findCon(@Param("obj") T obj,@Param("selOption")SelectOptionBuilder selectOptionBuilder);  
	
	@SelectProvider(type = CUDTemplate.class,method = "count")
	public int count(@Param("obj") T obj,@Param("selOption")SelectOptionBuilder selectOptionBuilder); 
	
	@SelectProvider(type = CUDTemplate.class,method = "findOne")
	@ResultMap("result")
	public List<T> findOne(T obj);  
      
    public List<T> selectByPage(T obj);  
      
    //@SelectProvider(type = SelectTemplate.class,method = "count")  
    //public int count(T obj);  
      
    /** 
     * Insert语句从CUDTemplate类中生成 
     * @param obj 
     */  
    @InsertProvider(type = CUDTemplate.class,method = "insert")  
    public void insert(T obj);  
      
    /** 
     * Update语句从CUDTemplate类中生成 
     * @param obj 
     */  
    @UpdateProvider(type = CUDTemplate.class,method = "update")  
    public void update(T obj);  
      
    /** 
     * Delete语句从CUDTemplate类中生成 
     * @param obj 
     */  
    @DeleteProvider(type = CUDTemplate.class,method = "delete")  
    public void delete(T obj);  
}