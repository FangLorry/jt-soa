package com.fangjt.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.fangjt.common.vo.Message;
import com.fangjt.common.vo.MessageData;


public class BaseController {
	
	@ExceptionHandler  
    public void exp(HttpServletRequest request, Exception ex,HttpServletResponse response) {  
		PrintWriter writer;
		try {
			writer = response.getWriter();
			MessageData result= Message.error(ex.getMessage());
			writer.write(JSON.toJSONString(result));
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
