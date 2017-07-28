package com.fangjt.common.utils;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fangjt.common.entity.BaseEntity;
import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.Filter.OperateEnum;
import com.fangjt.common.orm.SelectOption;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.SelectPageOption;
import com.fangjt.common.orm.Sequencer;


//动态生成sql语句工具类
public class CUDTemplate<T extends BaseEntity, ID extends Serializable> {

	public String find(T obj) {
		StringBuilder sql = new StringBuilder("select * from "+obj.tablename() +" where 1=1 ");
		List<Field> fieldList =  obj.getAllDeclareFields();
		for(Field field : fieldList){
			 try {
				 field.setAccessible(true);
				if(field.get(obj)!=null){
					sql.append(" and "+CamelUtils.toSqlColumn( ReflactUtils.operateColumn(field)) +"=#{"+field.getName()+"}") ;
				 }
			} catch (IllegalArgumentException e) {
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				//e.printStackTrace();
			}
		}
		return sql.toString();
		
	}
	
	public String findCon(Map<String, Object> param) {
		T obj = (T) param.get("obj");
		SelectOptionBuilder sob = (SelectOptionBuilder)param.get("selOption"); 
		SelectOption selectOption = sob.getSelectOption();
		List<Sequencer> sequencers = selectOption.getsList();//排序条件
		List<Filter> filters = selectOption.getfList();//过滤条件
		String columns = selectOption.getSelColumns()!=null?selectOption.getSelColumns():"*";//选择列
		StringBuilder sql = new StringBuilder("select "+ CamelUtils.toSqlColumn(columns)+" from "+obj.tablename() +" where 1=1 ");
		if(filters!=null){
			for(Filter filter : filters){
				OperateEnum operate = filter.getOperateEnum();
				switch(operate){
				case or:
					List<Filter> orList = filter.getFilterList();
					sql.append(" and ( 1=2 ");
					for(Filter orFilter : orList){
						sql.append(" or "+ CamelUtils.toSqlColumn(orFilter.getKey()) +orFilter.getOperateEnum().getValue()+"'"+orFilter.getValue()+"'");
					}
					sql.append(" ) ");
					break;
				case is_null:
				case not_null:
					sql.append(" and "+ CamelUtils.toSqlColumn(filter.getKey()) +operate.getValue());
					break;
				default:
					sql.append(" and "+ CamelUtils.toSqlColumn(filter.getKey()) +filter.getOperateEnum().getValue()+"'"+filter.getValue()+"'");
					break;
				}
			}
		}
		if(sequencers!=null){
			if(sequencers!=null && sequencers.size()>0){
				sql.append(" order by ");
				for(int i = 0;i< sequencers.size() ;i++){
					sql.append(CamelUtils.toSqlColumn(sequencers.get(i).getKey())+" "+sequencers.get(i).getSequencerDirection().toString()+(i!=(sequencers.size()-1)? ", ":" "));
				}
			}
		}
		//是否分页  fang 20160908
		if(selectOption instanceof SelectPageOption){
			SelectPageOption selectPageOption = (SelectPageOption)selectOption;
			sql.append(" limit "+selectPageOption.getStartIndex()+","+selectPageOption.getPageSize());
		}
		System.err.println(sql.toString());
		return sql.toString();
	}
	public String count(Map<String, Object> param) {
		T obj = (T) param.get("obj");
		SelectOptionBuilder sob = (SelectOptionBuilder)param.get("selOption"); 
		SelectOption selectOption = sob.getSelectOption();
		List<Filter> filters = selectOption.getfList();//过滤条件
		StringBuilder sql = new StringBuilder("select count(1) from "+obj.tablename() +" where 1=1 ");
		if(filters!=null){
			for(Filter filter : filters){
				OperateEnum operate = filter.getOperateEnum();
				switch(operate){
				case or:
					List<Filter> orList = filter.getFilterList();
					sql.append(" and ( 1=2 ");
					for(Filter orFilter : orList){
						sql.append(" or "+ CamelUtils.toSqlColumn(orFilter.getKey()) +orFilter.getOperateEnum().getValue()+"'"+orFilter.getValue()+"'");
					}
					sql.append(" ) ");
					break;
				case is_null:
				case not_null:
					sql.append(" and "+ CamelUtils.toSqlColumn(filter.getKey()) +operate.getValue());
					break;
				default:
					sql.append(" and "+ CamelUtils.toSqlColumn(filter.getKey()) +filter.getOperateEnum().getValue()+"'"+filter.getValue()+"'");
					break;
				}
				
			}
		}
		System.err.println(sql.toString());
		return sql.toString();
	}
	public String findOne(T obj) {
		return "select * from "+obj.tablename()+" where "+obj.id()+"=#{"+obj.id()+"}";
	}
	public String insert(T obj) {
		BEGIN();

		INSERT_INTO(obj.tablename());
		obj.caculationColumnList();
		VALUES(obj.returnInsertColumnsName(), obj.returnInsertColumnsDefine());

		return SQL();
	}

	public String update(T obj) {
		String idname = obj.id();
		BEGIN();
		UPDATE(obj.tablename());
		obj.caculationColumnList();
		SET(obj.returnUpdateSet());
		WHERE(idname + "=#{" + idname + "}");
		String sql = SQL();
		System.err.println(sql);
		return sql;
	}

	public String delete(T obj) {
		String idname = obj.id();

		BEGIN();

		DELETE_FROM(obj.tablename());
		WHERE(idname + "=#{" + idname + "}");

		return SQL();
	}


}
