/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductImage;

import org.slf4j.Logger;
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ProductImageDaoTest extends SpringTransactionalTestCase {
private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductImageDao productImageDao;

	@Test
	public void findProductImageById() throws Exception {
		ProductImage image = productImageDao.findOne(1L);
	
		assertThat(image).isNotNull();	
		logger.info("find image by id=1,found {}",image);
		}
	@Test
	public void saveProductImage() throws Exception {
		ProductImage image = new ProductImage();
		image.setIndex(22);
		image.setUrl("http://tbcdn.com/a.jpg");
		image.setProduct(new Product());
		image.getProduct().setId(1L);
		productImageDao.save(image);
		image=productImageDao.findOne(image.getId());
		assertThat(image).isNotNull();	
		logger.info("find image by id={},found {}",image.getId(),image);
		}
	@Test
	public void findProductImageByProductId() throws Exception {
		List<ProductImage> images=productImageDao.findByProductId(1L);
		assertThat(images).isNotNull();	
		logger.info("find image by productId=1,found {}",images);
		}
}
