package com.fangjt.common.swagger;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConf {
	public Docket addUserDocket(){
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		ApiInfo apiInfo = new ApiInfo(
				"restful API",
				"API 接口文档",
				"V0.0.1",
				"fangjiatai.xin",
				"1002574911@qq.com",
				"",
				""
				);
		docket.apiInfo(apiInfo);
		return docket;
	}
}
