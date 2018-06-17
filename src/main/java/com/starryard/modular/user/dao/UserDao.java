package com.starryard.modular.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starryard.modular.user.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
