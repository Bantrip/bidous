/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.dest;


import com.banyou.backend.entity.Dest;
import com.banyou.backend.service.product.DestService;

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
 * List page : GET /dest/
 * Create page : GET /dest/create
 * Create action : POST /dest/create
 * Update page : GET /dest/update/{id}
 * Update action : POST /dest/update
 * Delete action : GET /dest/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/dest")
public class DestController {

	private static final String PAGE_SIZE = "-1";



	@Autowired
	private DestService service;

	@RequestMapping(value="list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize, Model model,
			ServletRequest request) {
		Page<Dest> dests = service.findDests(pageSize,pageNo);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("dest/list");
		mv.addObject("result", dests);
		return mv;
	}



}
