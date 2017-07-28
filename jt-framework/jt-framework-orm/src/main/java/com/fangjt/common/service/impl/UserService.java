package com.fangjt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjt.common.entity.User;
import com.fangjt.common.mapper.UserMapper;
import com.fangjt.common.service.IUserService;


@Service
public class UserService extends BaseService<User,String>  implements  IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUsername(username);
	}

}
