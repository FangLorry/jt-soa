package com.fangjt.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.admin.mapper.ModelMapper;
import com.fangjt.admin.service.IModelService;
import com.fangjt.common.service.impl.BaseService;
@Service
public class ModelService extends BaseService<Model,String>  implements  IModelService {
	
	@Autowired
	private ModelMapper modelMapper ;
	
	@Override
	public Model selectByPrimaryKey(String modelId){
		return modelMapper.selectByPrimaryKey(modelId);
	}
}
