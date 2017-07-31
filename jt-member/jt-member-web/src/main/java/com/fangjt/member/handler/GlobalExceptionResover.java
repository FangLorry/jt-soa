package com.fangjt.member.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.fangjt.common.vo.Message;

@Component
public class GlobalExceptionResover extends SimpleMappingExceptionResolver {
	
	public static final Log log = LogFactory.getLog(GlobalExceptionResover.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = null;
		String accept = request.getHeader("accept");
        if (accept != null && !(accept.indexOf("application/json") > -1 
        		|| (request.getHeader("X-Requested-With") != null 
        		&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            mv = super.doResolveException(request, response, handler, ex);
        } else {
            try { 
            	// json 请求返回
                PrintWriter writer = response.getWriter();  
                writer.write(JSON.toJSONString(Message.error(ex.getMessage()) ));
                writer.flush();
            } catch (IOException e) {
            	if (log.isInfoEnabled()) {
            		log.info(e.getMessage());
            	}
            }
        }
        doLog((HandlerMethod) handler, ex);
        return mv;
	}
	
	/**
	 * 记录异常日志
	 * 
	 * @param handler
	 * @param excetpion
	 */
	private void doLog(HandlerMethod handler, Exception excetpion) {
       // if (log.isEnabledExceptionDb()) {
        	//mongo 异常信息日志入库TODO
      //  }
	}	
}
