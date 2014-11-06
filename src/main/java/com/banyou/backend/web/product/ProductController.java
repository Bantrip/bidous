/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.product;

import java.util.List;

import com.banyou.backend.entity.Dest;
import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.entity.Tag;
import com.banyou.backend.service.product.ProductService;
import com.banyou.backend.web.AjaxResponse;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;

/**
 * 商品管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /product/ 
 * Create page : GET /product/create 
 * Create action :POST /product/create 
 * Update page : GET /product/update/{id} 
 * Update action :POST /product/update 
 * Delete action : GET /product/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {
private Logger log=LoggerFactory.getLogger(getClass());
	private static final String PAGE_SIZE = "-1";

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			Model model, ServletRequest request) {
		Page<Product> products = productService.findProducts(pageSize, pageNo);
		model.addAttribute("products", products);
		return "product/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action", "create");
		return "product/edit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST,produces={"application/json"})
	public ModelAndView create(Product newProduct
			,@RequestParam(value="imageUrls") String[] imageUrls
			,@RequestParam("destIds") List<Long> destIds
			, @RequestParam("tagIds") List<Long> tagIds
			) {
		ModelAndView mv = new ModelAndView();
		//AjaxResponse<Object> ret = new AjaxResponse<Object>();
		//mv.addObject("result", ret);
		log.info("mv model is {}", mv.getModel());
		try {
			//add image
			String pics=StringUtils.join(imageUrls,Product.PIC_SPLIT);
			
			newProduct.setPics(pics);
			//add dest
			List<Dest> dests=Lists.newArrayList();
			for(Long destId:destIds){
				if(destId!=null){
					dests.add(new Dest(destId));
				}
			}
			newProduct.setDests(dests);
			
			//add dest
			List<Tag> tags=Lists.newArrayList();
			for(Long tagId:tagIds){
				if(tagId!=null){
					tags.add(new Tag(tagId));
				}
			}
			newProduct.setTags(tags);
			
			productService.saveProduct(newProduct);
			mv.addObject("code", AjaxResponse.SUCCESS);
			mv.addObject("message", "创建成功");
			mv.addObject("product",null);
		} catch (Exception ex) {
			log.error("save error",ex);
			mv.addObject("code", AjaxResponse.ERROR);
			mv.addObject("message", ex.getMessage());
		}
		return mv;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("action", "update");
		return "product/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product,
			//@RequestParam(value = "productImages") List<Long> checkedRoleList,
			RedirectAttributes redirectAttributes) {
		productService.saveProduct(product);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/product/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getTask(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			Product product = productService.getProduct(id);

			model.addAttribute("product", product);
		}
	}

}
