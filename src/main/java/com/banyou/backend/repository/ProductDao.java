/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.banyou.backend.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Long> {
	
}
