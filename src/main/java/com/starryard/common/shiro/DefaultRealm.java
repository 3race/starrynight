package com.starryard.common.shiro;

import java.util.Optional;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.util.StringUtils;

import com.starryard.modular.user.dao.UserDao;
import com.starryard.modular.user.entity.User;

public class DefaultRealm extends AuthorizingRealm {
	
	private String name = "DefaultRealm";
	
	public String getName() {
		return name;
	}


	@Autowired
	UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 授权，用于资源
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 认证，用于登录
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		if(StringUtils.isEmpty(upToken.getUsername()))throw new AccountException("Null usernames are not allowed by this realm.");
		User upUser = new User();
		upUser.setUsername(upToken.getUsername());
		Optional<User> user = userDao.findOne(Example.of(upUser));
		if(!user.isPresent())throw new UnknownAccountException("No account found for user [" + upToken.getUsername() + "]");
		AuthenticationInfo info = new SimpleAuthenticationInfo(upToken.getUsername(),user.get().getPassword().toCharArray(),getName());
		return info;
	}

}
