package com.fangjt.mongodb.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.alibaba.fastjson.JSONObject;
import com.fangjt.common.annotation.Id;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.SelectOption;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.SelectPageOption;
import com.fangjt.common.orm.Sequencer;
import com.fangjt.common.orm.Sequencer.SequencerDirectionEnum;
import com.fangjt.common.orm.Filter.OperateEnum;
import com.fangjt.common.utils.CamelUtils;
import com.fangjt.common.utils.SpringContextUtils;
import com.fangjt.mongodb.entity.MongoFile;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

/**
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了<br>
 * 注意Mongo已经实现了连接池，并且是线程安全的。 <br>
 * 设计为单例模式， 因 MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可，<br>
 * Mongo有个内置的连接池（默认为10个） 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时，<br>
 * DB和DBCollection是绝对线程安全的<br>
 * 
 * @author zhoulingfei
 * @date 2015-5-29 上午11:49:49
 * @version 0.0.0
 * @Copyright (c)1997-2015 NavInfo Co.Ltd. All Rights Reserved.
 */
public enum MongoDBUtils {

	/**
	 * 定义一个枚举的元素，它代表此类的一个实例
	 */
	instance;

	private MongoTemplate mongoFileTemplate;

	static {
		instance.mongoFileTemplate = SpringContextUtils.getBean("mongoFileTemplate");
	}

	// ------------------------------------共用方法---------------------------------------------------

	/**
	 * 获取collection对象 - 指定Collection
	 * 
	 * @param collName
	 * @return
	 */
	public DBCollection getCollection(String collName) {
		if (null == collName || "".equals(collName)) {
			return null;
		}
		DBCollection dbConn = mongoFileTemplate.getCollection(collName);
		return dbConn;
	}

	/**
	 * 查询DB下的所有表名
	 */
	public Set<String> getAllCollections() {
		return mongoFileTemplate.getCollectionNames();
	}

	/**
	 * 查找对象 - 根据主键_id
	 * 
	 * @param <T>
	 * 
	 * @param id
	 * @return
	 */
	public <T> T findById(Class<T> className, String id) {
		return mongoFileTemplate.findById(id, className);
	}
	
	/**
	 * 是否存在
	 * 
	 * @param <T>
	 */
	public <T> boolean exist(Class<T> className, List<Filter> filter) {
		return mongoFileTemplate.exists(filter2Query(filter), className);
	}
	/**
	 * 是否存在
	 * 
	 * @param <T>
	 */
	public <T> boolean exist(Class<T> className,Filter filter) {
		List<Filter> flist = new ArrayList<Filter>();
		flist.add(filter);
		return mongoFileTemplate.exists(filter2Query(flist), className);
	}

	/**
	 * 统计数
	 * 
	 * @param <T>
	 */
	public <T> long getCount(Class<T> className, List<Filter> filter) {
		return mongoFileTemplate.count(filter2Query(filter), className);
	}
	/**
	 * 统计数
	 * 
	 * @param <T>
	 */
	public <T> long getCount(Class<T> className, Filter filter) {
		List<Filter> flist = new ArrayList<Filter>();
		flist.add(filter);
		return mongoFileTemplate.count(filter2Query(flist), className);
	}

	/**
	 * 条件查询
	 * 分页查询
	 * 
	 * @param <T>
	 */
	public <T> List<T> find(Class<T> className, SelectOptionBuilder sob) {
		Query query = selectOption2Query(sob);
		System.err.println(query.toString());
		return mongoFileTemplate.find(query, className);
	}
	
	public MongoFile findOne(Class<MongoFile> className, Filter filter) {
		List<Filter> flist = new ArrayList<Filter>();
		flist.add(filter);
		return mongoFileTemplate.findOne(filter2Query(flist), className);
	}

	/**
	 * 通过ID删除
	 * @param <T>
	 *
	 * @param coll
	 * @param id
	 * @return
	 */
	public <T> int deleteById(Class<T> className,  String id) {
		Query query = new Query();
		Criteria creteria = Criteria.where("id").is(id);
		query.addCriteria(creteria);
		return mongoFileTemplate.remove(query, className).getN();
	}

	/**
	 * FIXME
	 * @param <T>
	 *
	 * @return
	 */
	public <T> int update(Class<T> className,String id , JSONObject jsonObj) {
		Update update = new Update();
		for(String key :jsonObj.keySet()){
			update = update.set(key, jsonObj.get(key));
		}
		return mongoFileTemplate.updateFirst(new Query(Criteria.where("id").is(id)), update, className).getN();
	}
	
	/**
	 * 插入
	 * @param obj
	 */
	public void insert(Object obj) {
		mongoFileTemplate.insert(obj);
	}


	private Query filter2Query(List<Filter> filters) {
		Query query = new Query();
		for (Filter filter : filters) {
			Criteria cariteria = new Criteria();
			OperateEnum operate = filter.getOperateEnum();
			switch (operate) {
			case or:
				List<Filter> orList = filter.getFilterList();
				List<Criteria> orCriteriaArr = new ArrayList<Criteria>(orList.size());
				for (Filter orFilter : orList) {
					orCriteriaArr.add(Criteria.where(orFilter.getKey()).is(orFilter.getValue()));
				}
				break;
			case is_null:
				cariteria = Criteria.where(filter.getKey()).is("null");
			case not_null:
				cariteria = Criteria.where(filter.getKey()).is("not null");
				break;
			default:
				cariteria = Criteria.where(filter.getKey()).is(filter.getValue());
				break;
			}
			query.addCriteria(cariteria);
		}
		return query;
	}

	private Query selectOption2Query(SelectOptionBuilder selectOptionBuilder) {
		Query query = new Query();
		SelectOption selectOption = selectOptionBuilder.getSelectOption();
		List<Sequencer> sequencers = selectOption.getsList();// 排序条件
		List<Filter> filters = selectOption.getfList();// 过滤条件
		for (Filter filter : filters) {
			Criteria cariteria = new Criteria();
			OperateEnum operate = filter.getOperateEnum();
			switch (operate) {
			case or:
				List<Filter> orList = filter.getFilterList();
				List<Criteria> orCriteriaArr = new ArrayList<Criteria>(orList.size());
				for (Filter orFilter : orList) {
					orCriteriaArr.add(Criteria.where(orFilter.getKey()).is(orFilter.getValue()));
				}
				break;
			case is_null:
			case not_null:
				break;
			default:
				cariteria = Criteria.where(filter.getKey()).is(filter.getValue());
				break;
			}
			query.addCriteria(cariteria);
		}
		if (sequencers != null) {
			if (sequencers != null && sequencers.size() > 0) {
				for (int i = 0; i < sequencers.size(); i++) {
					Sequencer sequencer = sequencers.get(i);
					Direction direction = Direction.DESC;
					if(SequencerDirectionEnum.asc.equals(sequencer.getSequencerDirection())){
						direction = Direction.ASC;
					}
					query.with(new Sort(direction,sequencer.getKey()));
				}
			}
		}
		//是否分页  
		if(selectOption instanceof SelectPageOption){
			SelectPageOption selectPageOption = (SelectPageOption)selectOption;
			query.skip(selectPageOption.getStartIndex()).limit(selectPageOption.getPageSize());
		}
		return query;
	}

}
