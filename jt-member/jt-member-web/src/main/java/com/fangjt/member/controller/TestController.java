package com.fangjt.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.SelectOption;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.Sequencer;
import com.fangjt.common.utils.UUIDUtils;
import com.fangjt.common.vo.Message;
import com.fangjt.fastdfs.FastDFSClient;
import com.fangjt.fastdfs.FileManagerUtils;
import com.fangjt.member.entity.Member;
import com.fangjt.member.service.IMemberService;
import com.fangjt.mongodb.entity.MongoFile;
import com.fangjt.mongodb.utils.MongoDBUtils;
import com.fangjt.openapi.entity.Product;
import com.fangjt.openapi.service.IProductService;

@Component
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private IMemberService memberService;
	@Reference(version="1.0.0")
	private IProductService productService;
	@Autowired
	private FileManagerUtils fileManagerUtils;
	@RequestMapping("testdubbo.do")
	@ResponseBody
	public void testdubbo() throws Exception{
		memberService.insertMe();
		if(productService==null){
			System.err.println("亲,您的dubbo服务未开启吧!");
			return ;
		}
		List<Product> productList = productService.findAll();
		System.err.println("鏈接dubbo成功!"+productList.size());
	}
	
	
	@RequestMapping("testmongo.do")
	@ResponseBody
	public void test(){
		//插入
//		File file = new File();
//		file.setId("12w32");
//		file.setCreateTime(new Date());
//		file.setFileId("2fileId");
//		MongoDBUtils.instance.insert(file);
		//查询
		//File file =MongoDBUtils.instance.findById(File.class , "123" );
		SelectOption so = new SelectOption();
		List<Filter> fList = new ArrayList<Filter>();
		fList.add(Filter.eq("fileId", "fileId"));
		List<Sequencer> sList = new ArrayList<Sequencer>();
		sList.add(Sequencer.asc("fileId"));
		so.setfList(fList);
		so.setsList(sList);
		SelectOptionBuilder sob =new SelectOptionBuilder(so);
		List<MongoFile> fileList = MongoDBUtils.instance.find(MongoFile.class,sob);
		System.err.println(fileList.get(0).getFileId());
		//删除
		MongoDBUtils.instance.deleteById(MongoFile.class, "12w32");
	}
	
	@ResponseBody
	@RequestMapping(value="/testImg",method=RequestMethod.POST)
	public Map<String, Object> testImg(Model model, MultipartFile img){
		File file = null;
		try {
			String filename = img.getOriginalFilename();
			file = File.createTempFile("temp",filename.substring(filename.lastIndexOf(".")) );
			img.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imgFileId = fileManagerUtils.uploadFile(file);
		return Message.success("上传成功!"+imgFileId);
	}
}
