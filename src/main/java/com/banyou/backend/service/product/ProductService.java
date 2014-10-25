/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.service.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.banyou.backend.entity.Product;
import com.banyou.backend.repository.ProductDao;

/**
 * 商品管理类
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class ProductService {

	private static Logger logger = LoggerFactory
			.getLogger(ProductService.class);

	private ProductDao productDao;

	public Product getProduct(Long id) {
		return productDao.findOne(id);
	}
/**
 * 保存商品
 * @param product
 */
	public void saveProduct(Product product) {
		productDao.save(product);
	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
