package com.fangjt.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.fangjt.admin.entity.codeGen.Model;

public interface ModelMapper {
	
	public Model selectByPrimaryKey(@Param(value = "modelId") String modelId);
}
