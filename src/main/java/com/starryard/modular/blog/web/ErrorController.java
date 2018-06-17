package com.starryard.modular.blog.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@ExceptionHandler
	public ModelAndView handleAllException(RuntimeException e) {
		Logger.getLogger(this.getClass().getName()).info(e.getMessage());
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("message",e.getMessage());
		return mv;
	}
}
