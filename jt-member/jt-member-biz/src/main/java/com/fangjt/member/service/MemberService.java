package com.fangjt.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangjt.common.service.impl.BaseService;
import com.fangjt.member.entity.Member;
import com.fangjt.openapi.entity.Product;
import com.fangjt.openapi.service.IProductService;

@Service
public class MemberService extends BaseService<Member,String>  implements  IMemberService {
	
	@Reference(version="1.0.0")
	private IProductService productService;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Override
	public void insertMe(){
		Member member  = new Member();
		member.setName("123");
		update(member);
		
//		Product product = new Product();
//		product.setContent_("heheh");
//		product.setName("小");
//		try{
//			productService.update(product);
//			transactionTemplate.execute(new TransactionCallback<Integer>(){
//
//				@Override
//				public Integer doInTransaction(TransactionStatus arg0) {
//					// TODO Auto-generated method stub
//					update(member);
//					return 1;
//				}
//				
//			});
//		}catch(Exception ex){
//			System.err.println("----------------执行失败-------------");
//		}
		
		System.err.println("----------------还跑-------------");
		
		
	}
}
