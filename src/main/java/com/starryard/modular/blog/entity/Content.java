package com.starryard.modular.blog.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.cache.annotation.CacheConfig;

import lombok.Data;

@Entity
@Table(name="typecho_contents")
@Data
@Cacheable(true)
@CacheConfig()
public class Content {
	@Id
	/** 序号*/
	private Integer cid;
	/** 标题*/
	private String title;
	/** 内容*/
	private String text;
	
}
