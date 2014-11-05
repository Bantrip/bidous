/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.product;


import com.banyou.backend.entity.Product;
import com.banyou.backend.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /product/
 * Create page : GET /product/create
 * Create action : POST /product/create
 * Update page : GET /product/update/{id}
 * Update action : POST /product/update
 * Delete action : GET /product/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

	private static final String PAGE_SIZE = "10";



	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize, Model model,
			ServletRequest request) {
		Page<Product> products = productService.findProducts(pageSize,pageNo);
		model.addAttribute("products", products);
		return "product/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action", "create");
		return "product/edit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Product newProduct, RedirectAttributes redirectAttributes) {
		productService.saveProduct(newProduct);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/product/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Product product=productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("action", "update");
		return "product/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("product") Product product,@RequestParam(value = "productImages") List<Long> checkedRoleList, RedirectAttributes redirectAttributes) {
		productService.saveProduct(product);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/product/";
	}



	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			Product product=productService.getProduct(id);
			
			model.addAttribute("product", product);
		}
	}

}
