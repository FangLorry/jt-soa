package com.fangjt.common.mapper;

import com.fangjt.common.entity.User;

public interface UserMapper {
	User findUserByUsername(String username);
}