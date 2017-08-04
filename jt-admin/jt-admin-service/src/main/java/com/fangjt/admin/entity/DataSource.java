package com.fangjt.admin.entity;

import com.fangjt.common.annotation.Column;
import com.fangjt.common.annotation.Table;
import com.fangjt.common.annotation.Transit;
import com.fangjt.common.entity.BaseEntity;

/**
 * 数据源
 * @author fang
 *
 */
@Table(name="jt_data_source")
public class DataSource extends BaseEntity<String> {
	@Transit
	private static final long serialVersionUID = 1L;
	@Column
	private String name;		// name
	@Column
	private String driverClassName;		// driver_class_name
	@Column
	private String url;		// url
	@Column
	private String username;		// username
	@Column
	private String password;		// password
	@Column
	private Integer maxActive;		// max_active
	@Column
	private java.math.BigDecimal initialSize;		// initial_size
	@Column
	private java.math.BigDecimal maxWait;		// max_wait
	@Column
	private java.math.BigDecimal minIdle;		// min_idle
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}
	public java.math.BigDecimal getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(java.math.BigDecimal initialSize) {
		this.initialSize = initialSize;
	}
	public java.math.BigDecimal getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(java.math.BigDecimal maxWait) {
		this.maxWait = maxWait;
	}
	public java.math.BigDecimal getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(java.math.BigDecimal minIdle) {
		this.minIdle = minIdle;
	}
	
	
}
