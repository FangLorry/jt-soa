package com.fangjt.common.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjt.common.annotation.Id;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.entity.BaseEntity;
import com.fangjt.common.mapper.BaseMapper;
import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.Page;
import com.fangjt.common.orm.SelectOption;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.Sequencer;
import com.fangjt.common.service.IBaseService;
import com.fangjt.common.utils.ReflactUtils;


@Service
public class BaseService<T extends BaseEntity, ID extends Serializable> implements IBaseService<T, ID> {

	@Autowired
	private BaseMapper baseMapper;

	private Class<T> entityClass;

	public BaseService() {
		Type type = getClass().getGenericSuperclass();

		if ((type instanceof ParameterizedType)) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = ((Class<T>) parameterizedType[0]);
		}
	}
	

	@Override
	public T findOne(ID id) {
		try {
			T obj = entityClass.newInstance();
			obj.setId(id);
			return map2T( findOne(obj));
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public T map2T(Map<String,Object> result){
		T obj = null;
		try {
			obj = entityClass.newInstance();
			List<Field> fields = obj.getAllDeclareFields();
			for(Field field : fields){
				try {
					field.setAccessible(true);
					field.set(obj, result.get(ReflactUtils.operateColumn(field)));
				} catch (Exception e) {
					continue;
				}
			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return obj;
	}

	//findAll
	public List<T> findAll() {
		List<T> resultList = new ArrayList<T>();
		try {
			T obj = entityClass.newInstance();
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list =  (List<Map<String, Object>>) find(obj);
			for(Map<String, Object> map : list){
				resultList.add(map2T(map));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public List<T> findList(T obj) {
		List<T> resultList = new ArrayList<T>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list =  (List<Map<String, Object>>) find(obj);
		for(Map<String, Object> map : list){
			resultList.add(map2T(map));
		}
		return resultList;
	}
	
	public List<T> findList(SelectOptionBuilder selectOptionBuilder){
		List<T> resultList = new ArrayList<T>();
		try {
			T obj = entityClass.newInstance();
			List<Map<String, Object>> list =  (List<Map<String, Object>>) baseMapper.findCon(obj, selectOptionBuilder);
			for(Map<String, Object> map : list){
				resultList.add(map2T(map));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	public Page<T> findPage(SelectOptionBuilder selectOptionBuilder){
		Page<T> page = new Page<T>();
		page.setRows(findList(selectOptionBuilder));
		
		try {
			T obj = entityClass.newInstance();
			page.setTotal(baseMapper.count(obj, selectOptionBuilder));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return page;
	}
	public List<T> findList(Filter filter){
		List<Filter> flist = new ArrayList<Filter>(){
			{
				add(filter);
			}
		};
		SelectOptionBuilder sob = new SelectOptionBuilder(new SelectOption());
		return findList(sob.filter(flist));
	}
	public List<T> findList(Filter filter,Sequencer sequencer){
		List<Filter> flist = new ArrayList<Filter>(){
			{
				add(filter);
			}
		};
		List<Sequencer> slist = new ArrayList<Sequencer>(){
			{
				add(sequencer);
			}
		};
		return findList(new SelectOptionBuilder(new SelectOption()).filter(flist).sequencer(slist));
	}
	
	private List<T> find(T obj) {
		return baseMapper.find(obj);
	}
	private Map<String,Object> findOne(T obj) {
		List<Map<String,Object>> list = baseMapper.findOne(obj);
		if(list.size() ==0){
			return null;
		}
		return list.get(0);
	}

	public List<T> selectByPage(T obj) {
		return baseMapper.selectByPage(obj);
	}

	public void update(T obj)  {
		if (obj.getId() == null) {
			obj.setId(uuid());
			obj.setCreateTime(new Date());
			obj.setUpdateTime(new Date());
			baseMapper.insert(obj);
		} else {
			baseMapper.update(obj);
		}
	}

	public void del(T obj) {
		baseMapper.delete(obj);
	}
	
	public void delOne(ID id){
		try {
			T obj = entityClass.newInstance();
			obj.setId(id);
			del(obj);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	  /** 
     * 获取POJO中的主键字段名 
     * 需要定义@Id 
     * @return 
     */  
    public String id() {  
        for(Field field : getAllDeclareFields()) {  
            if(field.isAnnotationPresent(Id.class))  
                return field.getName();  
        }  
          
        throw new RuntimeException("undefine POJO @Id");  
    } 

    private List<Field> getAllDeclareFields(){
    	List<Field> fields = new ArrayList<Field>();
    	fields.addAll(Arrays.asList(entityClass.getDeclaredFields()));
    	Class superClass = entityClass.getSuperclass();
    	if(superClass != null){
    		fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
    	}
    	return fields;
    }
	
    /**
     * 获取表名
     * */
	 public String tablename()  {  
	        Table table = entityClass.getAnnotation(Table.class);  
	        if(table != null)  
	            return table.name();
			else
				try {
					throw new Exception("undefine POJO @Table, need Tablename(@Table(name))");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}  
	    } 

	private String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}





}
