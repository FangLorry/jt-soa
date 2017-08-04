package com.fangjt.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangjt.admin.entity.codeGen.GenScheme;
import com.fangjt.admin.entity.codeGen.Model;
import com.fangjt.admin.service.IGenSchemeService;
import com.fangjt.admin.service.IModelService;

@Controller
@RequestMapping("/admin/gen/model")
public class ModelController {
	
	@Autowired
	private IModelService modelService ;
	@Autowired
	private IGenSchemeService schemeService ;
	
	@RequestMapping("addModel.do")
	public void addModel(){
		Model model = new Model();
		model.setName("test");
		model.setComments("测试表");
		model.setClassName("Test");
		modelService.update(model);
	}
	
	@RequestMapping("genCode.do")
	public void genCode(){
		Model model = modelService.selectByPrimaryKey("65091c76653841fdbf620e907979fa9e");
		GenScheme genScheme = schemeService.findOne("123123");
		genScheme.setModel(model);
		schemeService.genCode(model, genScheme);
	}
}
