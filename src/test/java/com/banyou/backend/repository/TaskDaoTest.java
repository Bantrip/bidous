/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.banyou.backend.entity.Product;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TaskDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private ProductDao productDao;

	@Test
	public void findTasksByUserId() throws Exception {
		Product product = productDao.findOne(1L);
	
		assertThat(product).isNotNull();	
		}
}
