package com.starryard.modular.blog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="typecho_contents")
@Data
public class Content {
	@Id
	/** 序号*/
	private Integer cid;
	/** 标题*/
	private String title;
	/** 内容*/
	private String text;
	
}
