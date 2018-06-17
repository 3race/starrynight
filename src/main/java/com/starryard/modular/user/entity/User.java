package com.starryard.modular.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="typecho_users")
public class User {
	@Id
	/** uid*/
	private Integer uid;
	/** 用户名*/
	@Column(name="name")
	private String username;
	/** 展示名,Column注解中name写驼峰，两个单词会被“_”拆分*/
	@Column(name="screenname")
	private String screenName;
	/** 密码*/
	private String password;
	/** 组*/
	private String group;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
}
