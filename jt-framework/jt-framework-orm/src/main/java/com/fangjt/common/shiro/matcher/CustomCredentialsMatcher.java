package com.fangjt.common.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.fangjt.common.entity.User;
import com.fangjt.common.service.IUserService;
import com.fangjt.common.shiro.util.ShiroEncryptor;
import com.fangjt.common.utils.EhcacheUtil;


public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

	@Autowired
	private IUserService userService;
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		String username = usernamePasswordToken.getUsername();
		User user = userService.findUserByUsername(username);
		Object tokenCredentials = usernamePasswordToken.getPassword();  
		if(user!=null){
			tokenCredentials = ShiroEncryptor.encrypt(super.getHashAlgorithmName(),String.valueOf(usernamePasswordToken.getPassword()),user.getSalt()); 
		}
        Object accountCredentials = getCredentials(info);  
        Object count = EhcacheUtil.getItem(username); 
        if(count==null){
        	count=0;
        }
        int countInt = (int)count;
        if(1==user.getLock()){
        	throw new LockedAccountException();
        }
        //5次输入错误将无法抛出此异常
        if(countInt >= 5){
        	throw new ExcessiveAttemptsException();
        }
        EhcacheUtil.updateItem(username, countInt+1);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
        boolean isEqual = equals(tokenCredentials, accountCredentials); 
        if(isEqual){
        	EhcacheUtil.delItem(username);
        }
    	return isEqual;
        
	}
	

}
