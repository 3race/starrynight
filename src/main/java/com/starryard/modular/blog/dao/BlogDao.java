package com.starryard.modular.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starryard.modular.blog.entity.Content;

public interface BlogDao extends JpaRepository<Content, Integer> {

}
