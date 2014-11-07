/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.web.product;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.banyou.backend.entity.Dest;
import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.entity.Tag;
import com.banyou.backend.service.product.ProductService;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
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

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(
			Product newProduct,
			@RequestParam(value = "destIds", required = false) List<Long> destIds,
			@RequestParam(value = "tagIds", required = false) List<Long> tagIds,
			@RequestParam(value = "descInfos", required = false) List<String> descInfos) {
		ModelAndView mv = new ModelAndView();
		log.info("mv model is {}", mv.getModel());

		// add dest
		for (Long destId : Sets
				.newHashSet(destIds == null ? (List<Long>) Collections.EMPTY_LIST
						: destIds)) {
			if (destId != null) {
				newProduct.getDests().add(new Dest(destId));
			}
		}

		// add tag
		for (Long tagId : Sets
				.newHashSet(tagIds == null ? (List<Long>) Collections.EMPTY_LIST
						: tagIds)) {
			if (tagId != null) {
				newProduct.getTags().add(new Tag(tagId));
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
		productService.saveProduct(newProduct,descs);
		mv.addObject("message", "创建成功");

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
	public ModelAndView update(
			@ModelAttribute("product") Product product
			// @RequestParam(value = "productImages") List<Long>
			// checkedRoleList,
			,
			@RequestParam(value = "destIds", required = false) List<Long> destIds,
			@RequestParam(value = "tagIds", required = false) List<Long> tagIds,
			@RequestParam(value = "descInfos", required = false) List<String> descInfos) {
		ModelAndView mv = new ModelAndView();
		// add dest
		product.getDests().clear();
		for (Long destId : Sets
				.newHashSet(destIds == null ? (List<Long>) Collections.EMPTY_LIST
						: destIds)) {
			if (destId != null) {
				product.getDests().add(new Dest(destId));
			}
		}

		// add dest
		product.getTags().clear();
		for (Long tagId : Sets
				.newHashSet(tagIds == null ? (List<Long>) Collections.EMPTY_LIST
						: tagIds)) {
			if (tagId != null) {
				product.getTags().add(new Tag(tagId));
			}
		}

		// add desc
		product.getDescs().clear();
		List<ProductDesc> descs = Lists.newArrayList();
		for (String descInfo : descInfos == null ? (List<String>) Collections.EMPTY_LIST: descInfos) {
			if (descInfo != null) {
				descs.add(new ProductDesc(descInfo,descs.size()));
			}
		}

		productService.saveProduct(product,descs);
		mv.addObject("message", "更新成功");
		mv.setViewName("redirect:/product/");
		return mv;
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
