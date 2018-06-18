package com.starryard.modular.blog.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starryard.modular.blog.dao.BlogDao;
import com.starryard.modular.blog.entity.Content;

import lombok.val;

@Controller
@RequestMapping("blog")
public class BlogController {
	
	private final static String PREFIX = "/blog/";
	private final static String MARK = "<!--markdown-->";
	
	
	@Autowired
	private BlogDao blog;
	
	
	@GetMapping("list/{page}")
	public ModelAndView list(@PathVariable(required=false) Integer page, ModelAndView mv) {
		mv.setViewName(PREFIX+"list");
		val size = 10;
		val pageable = PageRequest.of(Optional.of(page).orElse(0), size, Direction.DESC, "cid");
		val pageContent = blog.findAll(pageable);
		mv.addObject("list",pageContent.getContent());
		val prev = pageContent.getPageable().previousOrFirst();
		val next = pageContent.getPageable().next();
		mv.addObject("prev",prev.getPageNumber());
		mv.addObject("next",pageContent.getTotalPages() > next.getPageNumber()?next.getPageNumber():pageContent.getNumber());
		return mv;
	}
	
	@GetMapping("article/{cid}")
	public ModelAndView page(@PathVariable Integer cid,ModelAndView mv) {
		mv.setViewName(PREFIX+"page");
		val opt = blog.findById(cid);
		if(opt.isPresent()) {
			val sb = new StringBuilder(opt.get().getText());
			opt.get().setText(sb.substring(sb.indexOf(MARK)+MARK.length()));
		};
		mv.addObject("content",opt.orElse(new Content()));
		return mv;
	}
}
