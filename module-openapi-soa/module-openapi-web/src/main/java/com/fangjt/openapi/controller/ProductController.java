package com.fangjt.openapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fangjt.cache.JedisUtils;
import com.fangjt.common.orm.Filter;
import com.fangjt.common.orm.Page;
import com.fangjt.common.orm.SelectOptionBuilder;
import com.fangjt.common.orm.SelectPageOption;
import com.fangjt.common.orm.Sequencer;
import com.fangjt.common.vo.Message;
import com.fangjt.common.vo.MessageData;
import com.fangjt.mq.service.MqProducer;
import com.fangjt.openapi.entity.Product;
import com.fangjt.openapi.service.IProductService;
/**
 * 图像识别Demo
 * @author fang
 *
 */
@Controller
@RequestMapping(value="/openapi/v1/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private JedisUtils jedisUtils;
	@Autowired
	private MqProducer mqProducer;

	
	@ResponseBody
	@RequestMapping(value="/list.jhtml",method=RequestMethod.POST)
	public MessageData list(Model model,
			HttpServletRequest request ,
			@RequestParam(required=false,defaultValue="1") int pageNum,
			@RequestParam(required=false,defaultValue="10") int pageSize,
			@RequestParam(required=false,defaultValue="") String search
			){
//		String value = jedisUtils.get("123");
//		Map<String,Object> msg = new HashMap<String,Object>();
//        msg.put("data","hello,rabbmitmq!");
//		mqProducer.sendMsg("test_queue_key", msg);
		SelectPageOption pageOption = new SelectPageOption(pageNum,pageSize);
		if(StringUtils.isNotBlank(search)){
			List<Filter> flist = new ArrayList<Filter>();
			flist.add(Filter.like("name", "%"+search+"%"));
			pageOption.setfList(flist);
		}
		pageOption.setSelColumns("name,price,desc_,content_,headUrl,bodyUrls");
		SelectOptionBuilder slectOptionBuilder = new SelectOptionBuilder(pageOption);
		List<Sequencer> slist = new ArrayList<Sequencer>();
		slist.add(Sequencer.desc("createTime"));
		slectOptionBuilder.sequencer(slist);
		Page<Product> page =productService.findPage(slectOptionBuilder);
		List<JSONObject> result = new ArrayList<JSONObject>();
		for(Product product : page.getRows()){
			JSONObject jsonItem = new JSONObject(); 
			jsonItem.put("name", product.getName());
			jsonItem.put("price", product.getPrice());
			jsonItem.put("headUrl", product.getHeadUrl());
			jsonItem.put("desc", product.getDesc_());
			jsonItem.put("content", product.getContent_());
			jsonItem.put("bodyUrls", product.getBodyUrls());
			result.add(jsonItem);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("size", page.getTotal());
		jsonObj.put("list", result);
		return Message.success("查询成功!",jsonObj);
	}
	@ResponseBody
	@RequestMapping(value="/sendMsg",method=RequestMethod.POST)
	public MessageData list(Model model,String msg){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("data",msg);
		if(msg.indexOf("error") > 0){
			obj.put("error","error code");
		}
		mqProducer.sendMsg("test_queue_key", obj);
		return Message.success("发送成功!");
	}
	
	
	
	
}