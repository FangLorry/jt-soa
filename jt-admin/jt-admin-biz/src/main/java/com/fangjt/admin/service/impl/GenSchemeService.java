package com.fangjt.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fangjt.admin.entity.codeGen.GenConfig;
import com.fangjt.admin.entity.codeGen.GenScheme;
import com.fangjt.admin.entity.codeGen.GenTemplate;
import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.admin.service.IGenSchemeService;
import com.fangjt.admin.utils.GenUtils;
import com.fangjt.common.service.impl.BaseService;

@Service
public class GenSchemeService extends BaseService<GenScheme, String> implements IGenSchemeService {

	@Override
	public void genCode(Model model, GenScheme genScheme) {
		StringBuilder result = new StringBuilder();
		
		GenUtils.initColumnField(model);

		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();

		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
		Map<String, Object> model1 = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList) {
			result.append(GenUtils.generateToFile(tpl, model1, true));
		}
		System.err.println( result.toString());
	}

}
