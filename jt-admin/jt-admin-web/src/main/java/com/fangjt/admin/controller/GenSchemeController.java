package com.fangjt.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangjt.admin.entity.codeGen.GenScheme;
import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.admin.service.IGenSchemeService;
import com.fangjt.admin.service.IModelService;
import com.fangjt.common.vo.Message;
import com.fangjt.common.vo.MessageData;

@Controller
@RequestMapping("/admin/gen/scheme")
public class GenSchemeController {
	
	@Autowired
	private IModelService modelService ;
	@Autowired
	private IGenSchemeService genSchemeService ;
	
	@RequestMapping("genCode.do")
	public MessageData genCode(String modelId,String schemeId){
		Model model = modelService.findOne(modelId);
		GenScheme genScheme = genSchemeService.findOne(schemeId);
		if(model != null && genScheme!=null){
			genSchemeService.genCode(model,genScheme);
			return Message.success("操作成功");
		}
		return Message.error("参数有误");
	}
}
