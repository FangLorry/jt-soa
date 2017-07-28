package com.fangjt.member.service;

import com.fangjt.common.service.IBaseService;
import com.fangjt.member.entity.Member;

public interface IMemberService extends  IBaseService<Member,String> {

	void insertMe();
}
