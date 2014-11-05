/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductImage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ProductImageDaoTest extends SpringTransactionalTestCase {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductImageDao productImageDao;

	@Test
	public void findProductImageById() throws Exception {
		ProductImage image = productImageDao.findOne(1L);

		assertThat(image).isNotNull();
		logger.info("find image by id=1,found {}", image);
	}

	@Test
	public void saveProductImage() throws Exception {
		Long productId = 1L;
		// init size
		int size = productImageDao.findByProductId(productId).size();
		// save image
		ProductImage image = new ProductImage();
		image.setIndex(22);
		image.setUrl("http://tbcdn.com/a.jpg");
		image.setProduct(new Product());
		image.getProduct().setId(productId);
		productImageDao.save(image);
		image = productImageDao.findOne(image.getId());
		assertThat(image).isNotNull();
		logger.info("find image by id={},found {}", image.getId(), image);
		assertThat(productImageDao.findByProductId(productId).size() - size)
				.isEqualTo(1);
	}

	@Test
	public void findProductImageByProductId() throws Exception {
		List<ProductImage> images = productImageDao.findByProductId(1L);
		assertThat(images).isNotNull();
		logger.info("find image by productId=1,found {}", images);
	}
	
	@Test
	public void deleteProductImageByProductId() throws Exception {
		Long productId=1L;
		int size=productImageDao.deleteByProductId(productId);
		assertThat( productImageDao.findByProductId(productId)).isEmpty();
		logger.info("delete {} images from product {} ",size,productId);
	}
	
	
}
