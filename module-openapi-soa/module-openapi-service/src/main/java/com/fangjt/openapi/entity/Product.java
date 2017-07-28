package com.fangjt.openapi.entity;

import java.math.BigDecimal;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;


@Table(name="ss_product")
public class Product extends BaseEntity<String> {
	/**
	 * 
	 */
	@Transit
	private static final long serialVersionUID = 1L;
	
	@Column
    private String name;
	@Column
    private BigDecimal price;
	@Column
    private String no;
	@Column(name="market_price")
    private BigDecimal marketPrice;
	@Column(name="chengben_price")
    private BigDecimal chengbenPrice;
	@Column(name="head_url")
    private String headUrl;//大图,封面图
	@Column(name="body_urls")
    private String bodyUrls;//详情多图,','号隔开
	@Column(name="desc")
    private String desc_;//简介
	@Column(name="content")
    private String content_;//详情
	
    public String getBodyUrls() {
		return bodyUrls;
	}

	public void setBodyUrls(String bodyUrls) {
		this.bodyUrls = bodyUrls;
	}

	public String getDesc_() {
		return desc_;
	}

	public void setDesc_(String desc_) {
		this.desc_ = desc_;
	}

	public String getContent_() {
		return content_;
	}

	public void setContent_(String content_) {
		this.content_ = content_;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getChengbenPrice() {
        return chengbenPrice;
    }

    public void setChengbenPrice(BigDecimal chengbenPrice) {
        this.chengbenPrice = chengbenPrice;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }
}