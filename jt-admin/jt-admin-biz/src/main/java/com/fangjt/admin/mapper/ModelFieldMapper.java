package com.fangjt.admin.mapper;

import java.util.List;

import com.fangjt.admin.entity.codeGen.ModelField;

public interface ModelFieldMapper {
	/**
	 * 根据模型获取模型各个列集合
	 * @param modelId
	 * @return
	 */
	public List<ModelField> findListByModelId(String modelId);
}
