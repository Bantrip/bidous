/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.front;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.banyou.backend.entity.Dest;
import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.entity.Tag;
import com.banyou.backend.entity.TagGroup;
import com.banyou.backend.service.product.DestService;
import com.banyou.backend.service.product.ProductService;
import com.banyou.backend.service.product.TagService;
import com.banyou.backend.web.UserContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

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
@RequestMapping(value = "/front")
public class DetailController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id")Long id, Model model) {
		Product product=productService.getFrontProduct(id);
		model.addAttribute("result",product );
		return "front/detail";
	}
	

	


}
