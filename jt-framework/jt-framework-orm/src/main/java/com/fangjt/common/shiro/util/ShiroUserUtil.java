package com.fangjt.common.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.fangjt.common.entity.User;

public class ShiroUserUtil {
	
	public ShiroUserUtil(){}
	
	//获取用户名
	public static String getUserName(){
		Subject subject = SecurityUtils.getSubject();
		return  (String)subject.getPrincipal();
	}
	
	//获取shiro的session
	public static Session getShiroSession(){
		return SecurityUtils.getSubject().getSession();
	}
	//获取当前用户
	public static User getCurrentUser(){
		return (User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
	}
	//设置当前用户
	public static void setCurrentUser(User user){
		SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
	}
}
