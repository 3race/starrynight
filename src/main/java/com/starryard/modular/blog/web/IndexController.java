package com.starryard.modular.blog.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping("/")
	public void index(HttpServletResponse resp) {
		try {
			resp.sendRedirect("/blog/list/0");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
