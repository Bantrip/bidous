/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.product;

import java.util.Collections;
import java.util.List;

import com.banyou.backend.entity.Dest;
import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.entity.Tag;
import com.banyou.backend.service.product.ProductService;
import com.banyou.backend.web.UserContext;
import com.google.common.collect.Lists;
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
@RequestMapping(value = "/product")
public class ProductController {
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final String PAGE_SIZE = "-1";

	@Autowired
	private ProductService productService;

	@RequestMapping(value="list")
	public ModelAndView list(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			Model model, ServletRequest request) {
		ModelAndView mv = new ModelAndView();
	
		Page<Product> products = productService.findProducts(pageSize, pageNo,UserContext.getUser());
		mv.addObject("result", products);
		mv.setViewName("product/list");
		return mv;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model) {
		return edit(null,model);
	}
	
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		if(id!=null){
			model.addAttribute("result",productService.getProduct(id) );
		}
		return "product/edit";
	}
	
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ModelAndView show(@ModelAttribute("product") Product product) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("result",product );
		
		return mv;
	}
	

	@RequestMapping(value = "save", method ={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView save(
			@ModelAttribute("product") Product product,
			@RequestParam(value = "destIds", required = false) List<Long> destIds,
			@RequestParam(value = "tagIds", required = false) List<Long> tagIds,
			@RequestParam(value = "descInfos", required = false) List<String> descInfos) {
		ModelAndView mv = new ModelAndView();
		log.info("mv model is {}", mv.getModel());
		if(product.getId()!=null){
			product.getDests().clear();
			product.getTags().clear();
		}
		
		// add dest
		for (Long destId : Sets
				.newHashSet(destIds == null ? (List<Long>) Collections.EMPTY_LIST
						: destIds)) {
			if (destId != null) {
				product.getDests().add(new Dest(destId));
			}
		}

		// add tag
		for (Long tagId : Sets
				.newHashSet(tagIds == null ? (List<Long>) Collections.EMPTY_LIST
						: tagIds)) {
			if (tagId != null) {
				product.getTags().add(new Tag(tagId));
			}
		}

		// add desc
		List<ProductDesc> descs = Lists.newArrayList();
		for (String descInfo : descInfos == null ? (List<String>) Collections.EMPTY_LIST
				: descInfos) {
			if (descInfo != null) {
				descs.add(new ProductDesc(descInfo, descs.size()));
			}
		}
		productService.saveProduct(product,descs,UserContext.getUser());
		mv.addObject("message", "保存成功");
		mv.setViewName("/product");
		return mv;
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = "pass", method ={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView pass(
			@ModelAttribute("product") Product product
		) {
		ModelAndView mv = new ModelAndView();
		productService.passProduct(product,UserContext.getUser());
		mv.addObject("message", "审核成功");
		mv.setViewName("/product");
		return mv;
	}
	
	
	
	@RequestMapping(value = "audit", method ={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView audit(
			@ModelAttribute("product") Product product
		) {
		ModelAndView mv = new ModelAndView();

		productService.passProduct(product,UserContext.getUser());

		mv.addObject("message", "提交审核通过");
		mv.setViewName("/product");
		return mv;
	}
	
	@RequestMapping(value = "delete", method ={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView delete(
			@ModelAttribute("product") Product product
		) {
		ModelAndView mv = new ModelAndView();
		productService.deleteProduct(product,UserContext.getUser());
		mv.addObject("message", "删除成功");
		mv.setViewName("/product");
		return mv;
	}
	
	

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getProduct(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			Product product = productService.getProduct(id);

			model.addAttribute("product", product);
		}
	}

}
