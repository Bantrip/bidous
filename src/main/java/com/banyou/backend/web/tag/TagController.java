/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.tag;


import com.banyou.backend.entity.Tag;
import com.banyou.backend.service.product.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

/**
 * 商品管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /tag
 * Create page : GET /tag/create
 * Create action : POST /tag/create
 * Update page : GET /tag/update/{id}
 * Update action : POST /tag/update
 * Delete action : GET /tag/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController {

	private static final String PAGE_SIZE = "-1";



	@Autowired
	private TagService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize, Model model,
			ServletRequest request) {
		Page<Tag> tags = service.findTags(pageSize,pageNo);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("tag/list");
		mv.addObject("result", tags);
		return mv;
	}



}
