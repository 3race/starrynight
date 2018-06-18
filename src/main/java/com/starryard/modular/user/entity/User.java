package com.starryard.modular.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="typecho_users")
@Data
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
	
}
