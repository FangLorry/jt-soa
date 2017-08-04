package com.fangjt.admin.service;

import com.fangjt.admin.entity.codeGen.GenScheme;
import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.common.service.IBaseService;

public interface IGenSchemeService extends IBaseService<GenScheme,String> {

	/**
	 * 生成代码
	 * @param genScheme  代码生成方案
	 * @param model 实体
	 * 
	 */
	void genCode(Model model, GenScheme genScheme);

}
