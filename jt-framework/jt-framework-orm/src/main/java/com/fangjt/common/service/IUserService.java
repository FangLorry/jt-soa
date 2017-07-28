package com.fangjt.common.service;

import com.fangjt.common.entity.User;

public interface IUserService extends IBaseService<User,String> {
	User findUserByUsername(String username);
}
