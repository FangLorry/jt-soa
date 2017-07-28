package com.fangjt.common.entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Id;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.utils.CamelUtils;

import net.sf.json.JSONObject;

  
/** 
 * 基类
 * 
 */  
public class BaseEntity<ID> implements Serializable{  
  
	@Transit
    private static final long serialVersionUID = 1L;  
	@Id
    private ID id;
	@Column(name="create_time")
    private Date createTime ;
	@Column(name="update_time")
    private Date updateTime ;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    

	/** 
     * 获取POJO对应的表名 
     * 需要POJO中的属性定义@Table(name) 
     * @return 
     */  
    public String tablename()  {  
        Table table = this.getClass().getAnnotation(Table.class);  
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
    
    public List<Field> getAllDeclareFields(){
    	List<Field> fields = new ArrayList<Field>();
    	for(Field field : this.getClass().getDeclaredFields()){
			if(field.isAnnotationPresent(Transit.class)){
				continue;
			}
			fields.add(field);
    	}
    	Class superClass = this.getClass().getSuperclass();
    	if(superClass != null){
    		for(Field field : superClass.getDeclaredFields()){
    			if(field.isAnnotationPresent(Transit.class)){
    				continue;
    			}
    			fields.add(field);
    		}
    	}
    	return fields;
    }
  
    /** 
     * 用于存放POJO的列信息 
     */  
    @Transit
    private transient static Map<Class<? extends BaseEntity>,List<String>> columnMap = new HashMap<Class<? extends BaseEntity>, List<String>>();  
    /**
     * 存放实体属性
     * */
    @Transit
    private transient static Map<Class<? extends BaseEntity>,List<String>> attrMap = new HashMap<Class<? extends BaseEntity>, List<String>>(); 
    
    public Map<Class<? extends BaseEntity>,List<String>> getColumnMap(){
    	return columnMap;
    }
    public Map<Class<? extends BaseEntity>,List<String>> getAttrMap(){
    	return attrMap;
    }
      
    private boolean isNull(String fieldname) {  
    	Field field = null;
        try {  
            field = this.getClass().getDeclaredField(fieldname); 
            return isNull(field);  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (NoSuchFieldException e) {
			try {
				field = this.getClass().getSuperclass().getDeclaredField(fieldname);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} 
        	return isNull(field); 
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        }  
          
        return false;  
    }  
      
    private boolean isNull(Field field) {  
        try {  
            field.setAccessible(true);  
            return field.get(this) == null;  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
          
        return false;  
    }  
      
    /** 
     * 用于计算类定义 
     * 需要POJO中的属性定义@Column(name) 
     */  
    public void caculationColumnList() {  
        if(columnMap.containsKey(this.getClass()) )  
            return;  
          
        Field[] fields = this.getClass().getDeclaredFields();  
        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        List<String> columnList = new ArrayList<String>(fields.length+superFields.length);  
        List<String> attrList = new ArrayList<String>(fields.length+superFields.length);  
          
        for(Field field : fields) {  
        	if(field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)){
        		attrList.add(field.getName());
        		String columnName = operateColumn(field);
            	if(!columnName.isEmpty()){
            		columnList.add(columnName);
            	}
        	}
        	
        }  
        for(Field field : superFields) {  
        	if(field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)){
        		attrList.add(field.getName());
        		String columnName = operateColumn(field);
            	if(!columnName.isEmpty()){
            		columnList.add(columnName);
            	}
        	}
        }  
        attrMap.put(this.getClass(), attrList) ;
        columnMap.put(this.getClass(), columnList);  
    }  
    
    public String operateColumn(Field field){
		for (Annotation annotation : field.getAnnotations()){
			if(annotation.getClass().equals(Column.class)){
				Column column = (Column)annotation;
				return column.name()!=""?column.name():CamelUtils.toSqlColumn(field.getName()) ; 
			}
		}
		return CamelUtils.toSqlColumn(field.getName());
    }
      
    /** 
     * 获取用于WHERE的 有值字段表 
     * @return 
     */  
    public List<WhereColumn> returnWhereColumnsName() {  
        Field[] fields = this.getClass().getDeclaredFields();  
        List<WhereColumn> columnList = new ArrayList<WhereColumn>(fields.length);  
          
        for(Field field : fields) {  
            if(field.isAnnotationPresent(Column.class) && !isNull(field))   
                columnList.add(new WhereColumn(field.getName(), field.getGenericType().equals(String.class)));  
        }  
          
        return columnList;  
    }  
      
    /** 
     * Where条件信息 
     * @author HUYAO 
     * 
     */  
    public class WhereColumn {  
        public String name;  
        public boolean isString;  
          
        public WhereColumn(String name,boolean isString) {  
            this.name = name;  
            this.isString = isString;  
        }  
    }  
      
    /** 
     * 用于获取Insert的字段累加 
     * @return 
     */  
    public String returnInsertColumnsName() {  
        StringBuilder sb = new StringBuilder();  
          
        List<String> list = columnMap.get(this.getClass());  
        int i = 0;  
        for(String column : list) {  
            //if(isNull(column))  
              //  continue;  
              
            if(i++ != 0)  
                sb.append(',');  
            sb.append(column);  
        }  
        return sb.toString();  
    }  
      
    /** 
     * 用于获取Insert的字段映射累加 #{}
     * @return 
     */  
    public String returnInsertColumnsDefine() {  
        StringBuilder sb = new StringBuilder();  
          
        List<String> list = attrMap.get(this.getClass());  
        int i = 0;  
        for(String column : list) {  
           // if(isNull(column))  
              //  continue;  
              
            if(i++ != 0)  
                sb.append(',');  
            sb.append("#{").append(column).append('}');  
        }  
        return sb.toString();  
    }  
      
    /** 
     * 用于获取Update Set的字段累加 
     * @return 
     */  
    public String returnUpdateSet() {  
        StringBuilder sb = new StringBuilder();  
          
        List<String> list = attrMap.get(this.getClass());  
        int i = 0;  
        for(String column : list) {  
            if(isNull(column))  
                continue;  
              
            if(i++ != 0)  
                sb.append(',');  
            sb.append(CamelUtils.toSqlColumn(column)).append("=#{").append(column).append('}'); 
           // sb.append(column).append("=#{").append(column).append('}');  CamelUtils.toSqlColumn(field.getName())
        }  
        return sb.toString();  
    }  
      
  
  //  public Integer getId(){return 0;}  
  
    /** 
     * 转化POJO为JSON格式 
     * 需要org.json包支持,可以在json官网下载源码,或自己实现json编码 
     * @return 
     */  
    public String toJSONString() {  
        JSONObject json = JSONObject.fromObject(this);  
        return json.toString();  
    }  
      
    /** 
     * 打印类字段信息 
     */  
    @Override  
    public String toString() {  
        Field[] fields = this.getClass().getDeclaredFields();  
        StringBuilder sb = new StringBuilder();  
        sb.append('[');  
        for(Field f : fields) {  
            if(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()))  
                continue;  
            Object value = null;  
            try {  
                f.setAccessible(true);  
                value = f.get(this);  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
            if(value != null)  
                sb.append(f.getName()).append('=').append(value).append(',');  
        }  
        sb.append(']');  
          
        return sb.toString();  
    }  
      
} 
