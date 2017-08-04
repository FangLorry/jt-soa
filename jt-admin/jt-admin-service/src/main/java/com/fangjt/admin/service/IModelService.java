package com.fangjt.admin.service;

import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.common.service.IBaseService;

public interface IModelService extends IBaseService<Model,String> {

	Model selectByPrimaryKey(String modelId);

}
