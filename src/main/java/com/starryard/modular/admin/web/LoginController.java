package com.starryard.modular.admin.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starryard.modular.user.entity.User;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mv, User user) {
		if(user.getUsername() == null) return new ModelAndView("login");
		
		mv.setViewName("/admin/index");
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(),
				user.getPassword().toCharArray());
		
		Subject subject = SecurityUtils.getSubject();
		
		subject.login(token);
		
		mv.addObject("user",user.getUsername());
		
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mv, User user) {
		mv.setViewName("/index");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return mv;
	}
}
