/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.front;

import com.banyou.backend.entity.Product;
import com.banyou.backend.service.product.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

/**
 * 商品管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /product/ Create page : GET /product/create Create action
 * :POST /product/create Update page : GET /product/update/{id} Update action
 * :POST /product/update Delete action : GET /product/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/front/detail")
public class DetailController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id")Long id, Model model) {
		Product product=productService.getFrontProduct(id);
		model.addAttribute("result",product );
		return "front/detail";
	}
	

	


}
