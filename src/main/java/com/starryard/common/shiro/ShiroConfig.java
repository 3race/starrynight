package com.starryard.common.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		Map<String,String> filter = new LinkedHashMap<>();
		filter.put("/admin/**", "user");// 认证访问
		filter.put("/**", "anon"); // 匿名访问
		filter.put("/logout", "logout"); // 退出登录
		bean.setFilterChainDefinitionMap(filter);
		bean.setLoginUrl("/login"); // 登录页面
		bean.setSuccessUrl("/admin"); // 成功页面
		bean.setUnauthorizedUrl("/error"); //错误页面
		return bean;
	}
	
	@Bean
	public HashedCredentialsMatcher credentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(2);
		return matcher;
	}
	
	@Bean
	public Realm realm() {
		DefaultRealm realm = new DefaultRealm();
		//realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(realm());
		return manager;
	}
	
	/** Shiro Bean生命周期处理*/
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
