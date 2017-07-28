package com.fangjt.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fangjt.common.entity.User;
import com.fangjt.common.service.IUserService;


public class AutoShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.err.println("-----------------进入授权");
		if(principals==null){
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.err.println("-----------------进入认证");
		UsernamePasswordToken usernamePasswordToken  =(UsernamePasswordToken)token;
		User user = userService.findUserByUsername(usernamePasswordToken.getUsername()); 
		if(user!=null){
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),getName());
			return simpleAuthenticationInfo;
		}
		return null;
	}

}
