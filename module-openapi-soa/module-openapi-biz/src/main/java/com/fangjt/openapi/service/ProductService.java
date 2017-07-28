package com.fangjt.openapi.service;


import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.fangjt.common.service.impl.BaseService;
import com.fangjt.openapi.entity.Product;

@Component
@Service(version="1.0.0")
public class ProductService extends BaseService<Product,String>  implements  IProductService{
}
