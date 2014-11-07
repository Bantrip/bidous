/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.service.product;

import java.util.List;

import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.repository.ProductDao;
import com.banyou.backend.repository.ProductDescDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	private ProductDescDao productDescDao;

	public Product getProduct(Long id) {
		return productDao.findOne(id);
	}

	/**
	 * 
	 * @param pageSize
	 *            分页大小
	 * @param pageNo
	 *            第几页
	 * @return
	 */
	public Page<Product> findProducts(int pageSize, int pageNo) {
		Pageable page = pageSize > 0 ? new PageRequest(pageNo - 1, pageSize)
				: null;
		return productDao.findAll(page);
	}

	/**
	 * 保存商品
	 * 
	 * @param product
	 */
	public void saveProduct(Product product, List<ProductDesc> descs) {
		if(product.getId()!=null){
			productDescDao.deleteByProductId(product.getId());
		}
		productDao.save(product);

		for (ProductDesc desc : descs) {
			desc.setProduct(product);
			productDescDao.save(desc);
		}

	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	@Autowired
	public void setProductDescDao(ProductDescDao productDescDao) {
		this.productDescDao = productDescDao;
	}

}
